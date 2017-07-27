package cn.bos.action.qupai;


import java.sql.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.bos.domain.base.Customers;
import cn.bos.domain.base.Region;
import cn.bos.domain.qupai.Noticebill;
import cn.bos.domain.user.User;
import cn.bos.web.action.BaseAction;

@Controller
@Scope("prototype")
@Namespace("/qp")
@ParentPackage("bos")
public class NoticebillAction extends BaseAction<Noticebill>{
	//noticebill_findOneCustomer   ,params={"includeProperties","id,name"}
	@Action(value="noticebill_findOneCustomer" ,results={@Result(name="json" ,type="fastJson")})
	public String findOneCustomer(){
		Customers customers = WebClient.create(basePath+"/customer/findOneCustomerByTelephone/"+model.getTelephone()).accept(MediaType.APPLICATION_JSON).get(Customers.class);
	
		if(customers!=null){
			Noticebill nb = new Noticebill();
			nb.setCustomerId(customers.getId());
			nb.setCustomerName(customers.getName());
			pushToValueStack(nb);
		}else{
			pushToValueStack(null);
		}
		
		
		return "json";
	}
	
	//noticebill_save
	@Action(value="noticebill_save" ,results={@Result(name="save",location="/WEB-INF/pages/qupai/noticebill_add.jsp")})
	public String save(){
		//获取前台封装的数据
		String province = (String) getParameter("qprovince");
		String city = (String) getParameter("qcity");
		String district  = (String) getParameter("qdistrict");
		model.setPickaddress(province+city+district+model.getPickaddress());
		//获取当前操作人信息
		User user = (User) getSessionAttribute("loginUser");
		model.setUser(user);
		model.setPickdate(new Date(System.currentTimeMillis()));
		//保存订单操作
		facadService.getNoticebillService().save(province,city,district,model);
		
		return "save";
	}
}
