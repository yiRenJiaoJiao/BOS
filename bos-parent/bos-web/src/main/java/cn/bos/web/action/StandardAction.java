package cn.bos.web.action;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

import cn.bos.domain.base.Standard;
import cn.bos.domain.user.User;
import cn.bos.utils.DataContent;

@Controller
@Namespace("/bc")
@Scope("prototype")
@ParentPackage("bos")
public class StandardAction extends BaseAction<Standard>{
	
	/**
	 * 根据收派标准的名称判断标准是否唯一
	 * @return
	 */
	@Action(value="standard_nameIsUnique")
	public String nameIsUnique(){
		Standard standard = facadService.getStandardService().findOneByName(model);
		if(standard!=null){
			pushToValueStack("0");
		}
		return "json";
	}
	
	/**
	 * 修改收派标准
	 * @return
	 */
	@Action(value="standard_updateStandard" ,results={@Result(name="standard",location="/WEB-INF/pages/base/standard.jsp" )})
	public String updateStandard(){
		model.setOperationtime(new Date());
		facadService.getStandardService().save(model);
		return "standard";
	}
	/**
	 * 查询快递标准
	 * @return
	 */
	@Action(value="standard_findStandardByid")
	public String findStandardByid(){
		int id = Integer.parseInt((String)getParameter("id"));
		Standard  standard = facadService.getStandardService().findOne(id);
		
		//putToMap("standard", standard);
		pushToValueStack(standard);
		return "json";
	}
	
	/**
	 * 还原
	 * @return
	 */
	@Action(value="standard_restoreStandard")
	public String restoreStandard(){
		try {
			String ids = (String) getParameter("ids");
			String[] arr = ids.split(",");
			facadService.getStandardService().updateDelTagByIds(arr);
			pushToValueStack("1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pushToValueStack("0");
		}
		return "json";
	}
	
	/**
	 * 作废
	 * @return
	 */
	@Action(value="standard_deletStandard")
	public String deletStandard(){
		try {
			String ids = (String) getParameter("ids");
			String[] arr = ids.split(",");
			facadService.getStandardService().updateDelTagById(arr);
			
			pushToValueStack(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pushToValueStack(false);
		}
		return "json";
	}
	
	/**
	 * 收派标准添加
	 * @return
	 */
	@Action(value="standard_save" ,results={@Result(name="standard",location="/WEB-INF/pages/base/standard.jsp" )})
	public String save(){
		User user = (User) getSessionAttribute("loginUser");
		model.setOperator(user.getUsername());
	//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:dd");
		Date date = new Date();
		model.setOperationtime(date);
		facadService.getStandardService().save(model);
		return "standard";
	}
	
	/**
	 * 收派标准分页查询
	 * @return
	 */
	@Action(value="standard_findAll")
	public String findAll(){
		PageRequest request = new PageRequest(page-1, rows);
		 Page<Standard> page = facadService.getStandardService().findAll(request);
		HashMap<String, Object> map = new HashMap<>();
		map.put("total", page.getTotalElements());
		map.put("rows", page.getContent());
		pushToValueStack(map);
		return "json";
	}
}
