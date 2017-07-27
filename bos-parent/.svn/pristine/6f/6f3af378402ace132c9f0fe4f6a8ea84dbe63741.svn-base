package cn.bos.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import cn.bos.domain.base.Staff;
import cn.bos.domain.base.Standard;


@Controller
@Scope("prototype")
@Namespace("/bc")
@ParentPackage("bos")
public class StaffAction extends BaseAction<Staff>{
	
	/**
	 * 根据idf获取telephone
	 * @return
	 */
	@Action(value="staff_finTelephone" ,results={@Result(name="finTelephone",type="fastJson" ,params={"includeProperties","telephone"})})
	public String finTelephone(){
		Staff staff = facadService.getStaffService().findOneByTelephone(model.getId());
		if(staff!=null){
			pushToValueStack(staff);
		}else{
			pushToValueStack("");
			
		}
		
		return "finTelephone";
	}
	/**
	 * 获取staff的集合
	 * @return
	 */
	@Action(value="staff_findAllStaff" ,results={@Result(name="findAllStaff" ,type="fastJson",params={"includeProperties","id,name"})})
	public String findAllStaff(){
		List<Staff> list = facadService.getStaffService().findAllStaffInUse();
		pushToValueStack(list);
		return "findAllStaff";
	}
	
	/**
	 * 获取快递员的结果集，,并且进行分页查询,results={@Result(name="findAll",type="fastJson",params={"root","map"})}
	 */
	@Action(value="staff_findAll")
	public String findAll(){
		
		Specification<Staff> specification = new Specification<Staff>(){
			@Override
			public Predicate toPredicate(Root<Staff> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				ArrayList<Predicate> list = new ArrayList<Predicate>();
				if(StringUtils.isNotBlank(model.getId())){
					list.add(cb.equal(root.get("id").as(String.class), model.getId()));
				}
				if(StringUtils.isNotBlank(model.getName())){
					list.add(cb.like(root.get("name").as(String.class), model.getName()+"%"));
				}
				if(StringUtils.isNotBlank(model.getStandard())){
					list.add(cb.equal(root.get("standard").as(String.class),model.getStandard()));
				}
				if(StringUtils.isNotBlank(model.getStation())){
					list.add(cb.equal(root.get("station").as(String.class), model.getStation()));
				}
				if(StringUtils.isNotBlank(model.getTelephone())){
					list.add(cb.equal(root.get("telephone").as(String.class), model.getTelephone()));
				}
				if(model.getDeltag()!=null ){
					list.add(cb.equal(root.get("deltag").as(char.class), model.getDeltag()));
				}
				if(model.getHaspda()!=null){
					list.add(cb.equal(root.get("haspda").as(char.class),model.getHaspda()));
				}
				
				//集合转换成数组
				Predicate ps[] = new Predicate[list.size()];
				return cb.and(list.toArray(ps));
			}
			
		};
		
		//获取分页结果集对象Page<T>
		Page<Staff>  page= facadService.getStaffService().pageQuery(getPageRequest(),specification);
		setPage1(page);
		return "findAll";
	}
	
	/**
	 * 检查电话号码是否正确
	 * @return
	 */
	//statt_checkTelephone
	@Action(value="staff_checkTelephone" )
	public String checkTelephone(){
		String telephone = (String) getParameter("telephone");
		Staff staff  = facadService.getStaffService().findOneByTelephone(telephone);
		
		if(staff==null){
			pushToValueStack(true);
		}else{
			pushToValueStack(false);
		}
		return "json";
	}
	
	/**
	 * 获取标准
	 * @return
	 */
	@Action(value="staff_findStandard" ,results={@Result(name="json" ,type="fastJson",params={"includeProperties","name"})})
	public String findStandard(){
		HashMap<Object, Object> map = new HashMap<>();
		List<Standard> list = facadService.getStandardService().findList();
		
		/*if(list==null){
			for (Standard s : list) {
				map.put("id", s.getId());
				map.put("name", s.getName());
			}
		}else{
			map=null;
		}*/
		pushToValueStack(list);
		return "json";
	}
	
	/**
	 * 还原
	 * @return
	 */
	@Action(value="staff_restoreStandard" )
	public String restoreStandard(){
		try {
			String ids = (String) getParameter("ids");
			String[] arr = ids.split(",");
			facadService.getStaffService().updateDelTagByIds(arr);
			pushToValueStack("1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pushToValueStack("0");
		}
		return "json";
	}
	
	
	/**
	 * 根据id获取快递员的信息
	 * @return
	 */
	@Action(value="staff_findStaff")
	public String findStaff(){
		String id = (String) getParameter("id");
		HashMap<String, Object> map = new HashMap<>();
		Staff staff=null;
		if(id!=null){
			staff = facadService.getStaffService().findOne(id);
			map.put("staff", staff);
		}else{
			this.addActionError(this.getText("find.staff.error"));
			map.put("staff", "0");
		}
		pushToValueStack(staff);
		return "json";
	}
	
	/**
	 * 根据id批量作废收派标准
	 * @return
	 */
	@Action(value="staff_deleteStaff")
	public String deleteStaff(){
		try {
			String ids = (String) getParameter("ids");
			String[] arrId = ids.split(",");
			for (String id : arrId) {
				facadService.getStaffService().deleteStaffByUpdataDelTagTo1(id);
				//facadService.getStaffService().deleteStaff(id);
				pushToValueStack(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.addActionError(this.getText("delete.staff.error"));
			pushToValueStack(false);
		}
			
		return "json";
	}
	
	/**
	 * 保存前台的添加快递员的信息
	 * @return
	 * @throws Exception
	 */
	@Action(value="staff_addStaff",results={@Result(name="staffAll",location="/WEB-INF/pages/base/staff.jsp")})
	public String addStaff() throws Exception {
		String haspda = (String) getParameter("haspda");
		/*if(haspda!=null){
			model.setHaspda('1');
		}else{
			model.setHaspda('0');
		}*/
			model.setDeltag('1');
		facadService.getStaffService().save(model);
		return "staffAll";
	}
	
	
	
	/**
	 * 校验添加的编码是否唯一
	 * @return
	 */
	@Action(value="staff_checkId")
	public String checkId(){
		Staff staff = facadService.getStaffService().findOne(model.getId());
		if(staff==null){
			pushToValueStack(true);
		}else{
			pushToValueStack(false);
		}
		return "json";
	}
}
