package cn.bos.action.interceptor;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.bos.domain.user.User;
@Component
public class UserAction_login_interceptor extends MethodFilterInterceptor{
	
	/**
	 * 登陆拦截器
	 */
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		if (user==null) {
			//未登陆，跳转到登陆页面
			ActionSupport as = (ActionSupport) invocation.getAction();
			as.addActionError("您还没有登陆，请登陆后再进行操作");
			return "no_login";
		}
		return invocation.invoke();
	}
	
}
