package cn.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import cn.bos.domain.base.Region;
import cn.bos.service.UserService;
import cn.bos.service.impl.FacadService;
import cn.bos.utils.DownLoadUtils;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	protected  String basePath ="http://localhost:9999/bos-crmSystem/ws/customerService";
	
	/**
	 * 获取上传文件
	 */
	protected File upload;
	public void setUpload(File upload) {
		this.upload = upload;
	}
	
	@Autowired
	protected FacadService facadService;
	
	/*@Autowired
	protected  UserService userService;*/
	
	protected T model;
	public T getModel(){
		return model;
	}
	
	//获取请求参数
	/**
	 *封装请求参数
	 * @return
	 */
	public Pageable getPageRequest(){
		Pageable request = new PageRequest(page-1, rows);
		return request;
	}
	private Page<T> page1;
	public void setPage1(Page page1) {
		this.page1 = page1;
	}

	/**
	 * 将结果集封装到map中
	 * @param page
	 */
	public HashMap<String, Object> getMap(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("total", page1.getTotalElements());
		map.put("rows", page1.getContent());
		return map;
	}
	
	/**
	 * 构造器初始化数据模型
	 */
	//在默认的构造器初始化数据模型
		public BaseAction() {
			//在子类初始化的时候，默认会调用父类的构造器
			//反射机制:获取具体的类型
			//得到带有泛型的类型，如BaseAction<Userinfo>
			Type superclass = this.getClass().getGenericSuperclass();
			//转换为参数化类型
			ParameterizedType parameterizedType = (ParameterizedType) superclass;
			//获取泛型的第一个参数的类型类，如Userinfo
			Class<T> modelClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
			
			//实例化数据模型类型
			try {
				model = modelClass.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
	 
		}
		
		
		/**
		 * 将对象放入request域中
		 * 
		 */
		
		public static void requestSetAttribute(String str , Object obj){
			ServletActionContext.getRequest().setAttribute(str, obj);
		}
		
		/**
		 * 获取值栈
		 * @return
		 */
		public static  ValueStack getValueStack(){
			return ActionContext.getContext().getValueStack();
		} 
		
		/**
		 * 将数据放入值栈root中的map
		 * @param name
		 * @param value
		 */
		public static  void setToValueStacke(String name,Object value){
			ActionContext.getContext().getValueStack().set(name, value);
		}
		
		/**
		 * 将数据放入map栈
		 * @param name
		 * @param value
		 */
		public static  void putToMap(String name,Object value){
			ActionContext.getContext().put(name, value);
		}
		
		/**
		 * 将数据压入栈顶
		 * @param obj
		 */
		public static  void pushToValueStack(Object obj){
			ActionContext.getContext().getValueStack().push(obj);
		}
		
		/**
		 * 通过action的特性将result结果压入栈顶
		 * 
		 */
		protected Object result;
		public  void setResult(Object result) {
			this.result = result;
		}
		
		/**
		 * 获取request
		 * @return
		 */
		public static  HttpServletRequest getRequest(){
			return ServletActionContext.getRequest();
		}
		
		public static void invalidateSession(){
			ServletActionContext.getRequest().getSession().invalidate();
		}
		
		/**
		 * 获取response;
		 * @return
		 */
		public  static HttpServletResponse getResponse(){
			return ServletActionContext.getResponse();
		}
		
		/**
		 * 根据key获取前台页面参数
		 * @param key
		 * @return
		 */
		public static  Object getParameter(String key){
			return getRequest().getParameter(key);
		}
		
		/**
		 * 获取session中的数据
		 * @param str
		 * @return
		 */
		public static  Object getSession(){
			return ServletActionContext.getRequest().getSession();
		}
		
		/**
		 * 将数据放入session中
		 * @param name
		 * @param obj
		 */
		public static  void setSessionAttribute(String name,Object obj){
			ServletActionContext.getRequest().getSession().setAttribute(name, obj);
		}
		/**
		 * 获取session中的属相值
		 * @param name
		 * @return
		 */
		public static  Object  getSessionAttribute(String name){
			return ServletActionContext.getRequest().getSession().getAttribute(name);
		}
		
		/**
		 * 移除session中的属性值
		 * @param str
		 */
		public static void removeSessionAttriute(String str){
			ServletActionContext.getRequest().removeAttribute(str);
		}
		
		//封装下载方法
		public void download(String filename,String path){
			HttpServletResponse response = getResponse();
			try {
				ServletContext context = ServletActionContext.getServletContext();
				response.setHeader("Content-Disposition","attachment;filename="+DownLoadUtils.getAttachmentFileName(filename, ServletActionContext.getRequest().getHeader("user-agent")));
				response.setContentType(context.getMimeType(filename));
				ServletOutputStream outputStream = response.getOutputStream();
				InputStream in = new FileInputStream(path);
				int len;
				byte[] bytes = new byte[1024 * 8];
				while ((len = in.read(bytes)) != -1) {
					outputStream.write(bytes, 0, len);
				}
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 获取日志对象
		 */
		protected Logger log = LoggerFactory.getLogger(BaseAction.class);
		
		//封装page分页查询，获取前台发送的数据
		
		protected int page;
		protected int rows;
		public void setRows(int rows) {
			this.rows = rows;
		}
		public void setPage(int page) {
			this.page = page;
		}
		
		
		
		//json 封装
		
		
}
