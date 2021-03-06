package cn.bos.web.action.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.aspectj.weaver.MethodDelegateTypeMunger;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import cn.bos.domain.base.Customers;
import cn.bos.domain.base.DecidedZone;
import cn.bos.domain.base.Staff;
import cn.bos.web.action.BaseAction;

@Controller
@Scope("prototype")
@Namespace("/bc")
@ParentPackage("bos")
@SuppressWarnings("all")
public class DecidedZoneAction extends BaseAction<DecidedZone>{
	
	
	
	
	@Action(value="decidedzone_assigncustomerstodecidedzone" ,results={@Result(name="assigncustomerstodecidedzone",location="/WEB-INF/pages/base/decidedzone.jsp")})
	public String assigncustomerstodecidedzone(){
		String[] ids = ServletActionContext.getRequest().getParameterValues("customerIds");
		if(ids!=null&&ids.length!=0){
			StringBuffer sb = new StringBuffer();
			for (String id : ids) {
				sb.append(id).append(",");
			}
			 WebClient.create(basePath+"/customer/updateCustomer/"+sb.toString().substring(0,sb.length()-1)+"/"+model.getId()).put(null);
		}else{
			 WebClient.create(basePath+"/customer/updateCustomer/"+model.getId()).put(null);
		}
		
		 return "assigncustomerstodecidedzone";
		 
		
	}
	
	
	@Action(value="decidedZone_getInUseAssosiation" ,results={@Result(name="getInUseAssosiation",type="fastJson",params={"includeProperties","id,name"})})
	public String getInUseAssosiation(){
		
		List<Customers> list =  (List<Customers>) WebClient.create(basePath+"/customer/getIsInAssosiationCustomer/"+model.getId()).accept(MediaType.APPLICATION_JSON).getCollection(Customers.class);
		System.out.println("InUseCustomer"+list);
		
		pushToValueStack(list);
		return "getInUseAssosiation";
	}
	
	@Action(value="decidedZone_getNoAssosiation" ,results={@Result(name="getNoAssosiation",type="fastJson",params={"includeProperties","id,name"})})
	public String getNoAssosiation(){
		List<Customers> list =  (List<Customers>)WebClient.create(basePath+"/customer/getNoAssasiationCustomer").accept(MediaType.APPLICATION_JSON).getCollection(Customers.class);
		System.out.println("NoUseCustomer"+list);
		
		pushToValueStack(list);
		return "getNoAssosiation";
	}
	
	
//	decidedZone_updateDecidedZone
	/**
	 * 根据s保存decidedZone修改的数据
	 * @return
	 */
	@Action(value="decidedZone_updateDecidedZone" ,results={@Result(name="updateDecidedZone",location="/WEB-INF/pages/base/decidedzone.jsp")})
	public String updateDecidedZone(){
		facadService.getDecidedZoneService().saveDecidedZone(model);
		return "updateDecidedZone";
	}
	
	/**
	 * 根据id获取decidedZone
	 * @return
	 */
	@Action(value="decidedZone_findDecidedZoneById" ,results={@Result(name="findDecidedZoneById",type="fastJson")})
	public String findDecidedZoneById(){
		DecidedZone decidedZone = facadService.getDecidedZoneService().findOneById(model.getId());
		pushToValueStack(decidedZone);
		return "findDecidedZoneById";
	}

	//3)若此组存在与其关联的收派地址,取派人员信息和分区地址信息，则提示用户是否继续,继续则删除定区。否则取消删除操作。
//4)系统删除该定区，并且删除该定区和收派地址、分区地址,取派人员信息的关联，并记录下操作人，操作时间，操作单位。
	@Action(value="decidedZone_deleteDecidedZoneAssociation")
	public String deleteDecidedZoneAssociation(){
		try {
			facadService.getDecidedZoneService().deleteDecidedZoneAndAssocistion(model.getId());
			pushToValueStack(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			new RuntimeException("DecidedZone关联删除失败");
			pushToValueStack(false);
		}
		return "json";
	}
	
	
	
	
	@Action(value="decidedZone_deleteDecidedZone")
	public String deleteDecidedZone(){
		DecidedZone decidedZone = facadService.getDecidedZoneService().findOneAssociationById(model.getId());
		if(decidedZone.getStaff()!=null){
			pushToValueStack(false);
		}else if(decidedZone.getSubareas()!=null){
			pushToValueStack(false);
		}else{
			facadService.getDecidedZoneService().deleteDecidedZone(model.getId());
			pushToValueStack(true);
		}
		
		return "json";
	}
	
	
	
	
	/**
	 * 获取快递员的结果集，,并且进行分页查询,results={@Result(name="findAll",type="fastJson",params={"root","map"})}
	 */
	@Action(value="decidedZone_finAll")
	public String findAll(){
		
		try {
			Specification<DecidedZone> specification = getSpecification();
			//获取分页结果集对象Page<T>
			String  page= facadService.getDecidedZoneService().pageQuery(getPageRequest(),specification);
			HttpServletResponse response = getResponse();
			response.setContentType("text/json;charset=utf-8");
			response.getWriter().print(page);
		} catch (Exception e) {
			e.printStackTrace();
			new RuntimeException("区域分页获取数据异常");
		}
		//setPage1(page);
		return NONE;
	}
	
	@Action(value="decidedZone_uniqueId" )
	public String uniqueId(){
		DecidedZone decidedZone = facadService.getDecidedZoneService().findOneById(model.getId());
		if(decidedZone==null){
			pushToValueStack(true);
		}else{
			pushToValueStack(false);
		}
		return "json";
	}
	
	
	@Action(value="decidedZone_addDecidedZone" ,results={@Result(name="addDecidedZone" ,location="/WEB-INF/pages/base/decidedzone.jsp")})
	public String addDecidedZone(){
		String[] sid = getRequest().getParameterValues("sid");
		facadService.getDecidedZoneService().save(sid,model);
		return "addDecidedZone";
	}
	
	/**
	 * 封装多条件查询的条件
	 * @return
	 */
	private Specification<DecidedZone> getSpecification() {
		Specification<DecidedZone> specification = new Specification<DecidedZone>(){
			@Override
			public Predicate toPredicate(Root<DecidedZone> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				//自身的条件
				ArrayList<Predicate> list = new ArrayList<Predicate>();
				
				if(StringUtils.isNotBlank(model.getId())){
					Predicate p1 = cb.equal(root.get("id").as(String.class), model.getId());
					list.add(p1);
				}
				if(model.getStaff()!=null){
					Join<DecidedZone, Staff> join = root.join(root.getModel().getSingularAttribute("staff",Staff.class),JoinType.LEFT);
					if(StringUtils.isNotBlank(model.getStaff().getStation())){
						Predicate p2 = cb.like(join.get("station").as(String.class), model.getStaff().getStation());
						list.add(p2);
					}
				}
				
				String assosiation = (String) getParameter("isAssosiatonSubarea");
				if(StringUtils.isNotBlank(assosiation)){
					if("1".equals(assosiation)){
					
					Predicate p3 = cb.isNotEmpty(root.get("subareas").as(Set.class));
						list.add(p3);
					}else if("0".equals(assosiation)){
						Predicate p4 = cb.isEmpty(root.get("subareas").as(Set.class));
						list.add(p4);
					}
				}
				
				//集合转换成数组
				Predicate ps[] = new Predicate[list.size()];
				return cb.and(list.toArray(ps));
			}
			
		};
		return specification;
	}
	
}
