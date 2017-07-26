package cn.bos.action.qupai;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.bos.domain.base.Subarea;
import cn.bos.domain.qupai.City;
import cn.bos.redis.utils.RedisCRUD;
import cn.bos.web.action.BaseAction;

@Controller
@Scope("prototype")
@Namespace("/qp")
@ParentPackage("bos")
public class LoadCityAction extends BaseAction<City>{
	
	/**
	 * ,results={@Result(name="loadcity" ,type="fastJson")},params={"includeProperties","sid,addresskey,position"} 
	 * @return
	 * @throws Exception 
	 */
	@Action(value="loadCityAction_loadcity")
	public String loadcity() {
		try {
			String pid  = (String) getParameter("pid");
			String  list = facadService.getCityService().findCityByQid(Integer.parseInt(pid));
			
			getResponse().setContentType("text/json;charset=utf-8");
			getResponse().getWriter().print(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new RuntimeException("获取城市信息数据异常");
		}
		
		return NONE;
	}
	
}
