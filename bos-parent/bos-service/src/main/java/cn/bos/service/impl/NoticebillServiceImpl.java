package cn.bos.service.impl;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bos.dao.DecidedZoneDao;
import cn.bos.dao.NoticebillDao;
import cn.bos.dao.RegionDao;
import cn.bos.dao.WorkbillDao;
import cn.bos.domain.base.Customers;
import cn.bos.domain.base.DecidedZone;
import cn.bos.domain.base.Region;
import cn.bos.domain.base.Staff;
import cn.bos.domain.base.Subarea;
import cn.bos.domain.qupai.Noticebill;
import cn.bos.domain.qupai.Workbill;
import cn.bos.service.BaseServiceInterface;
import cn.bos.service.NoticebillService;

@Service
@Transactional
public class NoticebillServiceImpl implements NoticebillService {
	@Autowired
	private NoticebillDao noticebillDao;
	@Autowired
	private DecidedZoneDao decidedZoneDao;
	
	@Autowired
	private RegionDao regionDao;
	@Autowired
	private WorkbillDao workbillDao;
	
	@Autowired
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate jmsTemplate;
	
	@Override
	public void save(String province, String city, String district, Noticebill model) {
		//新老客户更新和修改操作
		//判断是否是新用户
		Integer customerId = model.getCustomerId();
		if(customerId!=null){
			//更新crm中的客户信息
			 WebClient.create(BaseServiceInterface.BASEPATH
					+"/customer/updateCustomersAdressById/"+customerId+"/"+model.getPickaddress()).put(null);
			
		}else{
			//到crm中添加客户信息
			Customers c = new Customers();
			c.setName(model.getCustomerName());
			c.setStation("上海");
			c.setTelephone(model.getTelephone());
			c.setAddress(model.getPickaddress());
			 Response post = WebClient.create(BaseServiceInterface.BASEPATH
						+"/customer/save").post(c);
			 //如果没有要进行添加，并且返回新客户的id
			 Customers customer = post.readEntity(Customers.class);
			 model.setCustomerId(customer.getId());
		}
		
		//保存订单
		noticebillDao.saveAndFlush(model);//将model进行持久化
		
		//根据新老客户完成自动分单
		//根据地址的完全匹配进行自动分单，根据地址获取对象
		 final Customers customers = WebClient.create(BaseServiceInterface.BASEPATH
					+"/customer/findOneCustomersByAddress/"+model.getPickaddress()).get(Customers.class);
		 
		 if(customers!=null){
			 //获取联系人
				String decidedzoneId = customers.getDecidedzoneId();
				
				if(StringUtils.isNotBlank(decidedzoneId)){
					DecidedZone decidedZone = decidedZoneDao.findOne(decidedzoneId);
					final Staff staff = decidedZone.getStaff();
					model.setStaff(staff);
					model.setOrdertype("自动");
					//产生工单
					Workbill workbill = new Workbill();
					//发送信息
					workbill.setAttachbilltimes(0);
					workbill.setBuildtime(new Date(System.currentTimeMillis()));
					workbill.setId(null);
					workbill.setNoticebill(model);
					workbill.setPickstate("新");
					workbill.setRemark(model.getRemark());
					workbill.setStaff(staff);
					workbill.setType("新单");
					workbillDao.save(workbill);
					//发送短信
					//将电话好密码写入mq消息队列中
					jmsTemplate.send("bos_staff", new MessageCreator() {
						
						@Override
						public Message createMessage(Session session) throws JMSException {
							MapMessage mapMessage = session.createMapMessage();
							mapMessage.setString("telephone",staff.getTelephone());
							mapMessage.setString("customer",customers.getName());
							Date date = new Date(System.currentTimeMillis());
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
							String time = sdf.format(date);
							mapMessage.setString("time", time);
							return mapMessage;
						}
					});
					return;
				}
		 }
		//如果地址不能够完全匹配，那么根据通过分区中的属性解析详细地址,通过解析出的省市区,关键字,辅助关键字,起始号段,终止号段,单双号解析出来,如果有多条相 
		//同关键字,则通过辅助关键字匹配。
		Region  region =regionDao.findRegionByadress(province,city,district);
		Set<Subarea> subareas = region.getSubareas();
		if(subareas!=null){
			for (Subarea subarea : subareas) {
				if(model.getPickaddress().contains(subarea.getAddresskey())){
					DecidedZone decidedZone = subarea.getDecidedZone();
					if(decidedZone!=null){
						final Staff staff = decidedZone.getStaff();
						model.setStaff(staff);
						model.setOrdertype("自动");
						//产生工单
						Workbill workbill = new Workbill();
						//发送信息
						workbill.setAttachbilltimes(0);
						workbill.setBuildtime(new Date(System.currentTimeMillis()));
						workbill.setId(null);
						workbill.setNoticebill(model);
						workbill.setPickstate("新");
						workbill.setRemark(model.getRemark());
						workbill.setStaff(staff);
						workbill.setType("新单");
						workbillDao.save(workbill);
						//发送短信
						//将电话好密码写入mq消息队列中
						jmsTemplate.send("bos_staff", new MessageCreator() {
							
							@Override
							public Message createMessage(Session session) throws JMSException {
								MapMessage mapMessage = session.createMapMessage();
								mapMessage.setString("telephone",staff.getTelephone());
								mapMessage.setString("customer",customers.getName());
								Date date = new Date(System.currentTimeMillis());
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
								String time = sdf.format(date);
								mapMessage.setString("time", time);
								return mapMessage;
							}
						});
						return;
					}
				}
			}
			
		}	 
		//否则采用人工分单
		model.setOrdertype("手工");
		
	}

}
