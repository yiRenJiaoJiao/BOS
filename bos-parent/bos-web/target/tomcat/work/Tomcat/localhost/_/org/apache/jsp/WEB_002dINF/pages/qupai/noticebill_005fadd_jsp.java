/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2017-07-26 11:33:39 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.pages.qupai;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class noticebill_005fadd_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>添加业务受理单</title>\r\n");
      out.write("<!-- 导入jquery核心类库 -->\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery-1.8.3.js\"></script>\r\n");
      out.write("<!-- 导入easyui类库 -->\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/themes/default/easyui.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/themes/icon.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/ext/portal.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/default.css\">\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/jquery.easyui.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/ext/jquery.portal.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/ext/jquery.cookie.js\"></script>\r\n");
      out.write("<script\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/locale/easyui-lang-zh_CN.js\"\r\n");
      out.write("\ttype=\"text/javascript\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t$(\"body\").css({visibility:\"visible\"});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// 对save按钮条件 点击事件\r\n");
      out.write("\t\t$('#save').click(function(){\r\n");
      out.write("\t\t\t// 对form 进行校验\r\n");
      out.write("\t\t\tif($('#noticebillForm').form('validate')){\r\n");
      out.write("\t\t\t\t$('#noticebillForm').submit();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tloadcity(0,province)\r\n");
      out.write("\t\t\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\tfunction loadcity(pid,target){\r\n");
      out.write("\t\ttarget.length=1;\r\n");
      out.write("\t\tdistrict.length = 1;\r\n");
      out.write("\t\tif(pid==\"none\"){\r\n");
      out.write("\t\t\treturn ;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl: \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/qp/loadCityAction_loadcity\",\r\n");
      out.write("\t\t\ttype:\"post\",\r\n");
      out.write("\t\t\tdata:{\"pid\":pid},\r\n");
      out.write("\t\t\tsuccess: function(data){\r\n");
      out.write("\t\t   \t\t$(data).each(function(){\r\n");
      out.write("\t\t   \t\t\t$(target).append(\"<option value=\"+this.id+\">\"+this.name+\"</option>\");\r\n");
      out.write("\t\t   \t\t});\r\n");
      out.write("\t\t}});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"easyui-layout\" style=\"visibility:hidden;\">\r\n");
      out.write("\t<div region=\"north\" style=\"height:31px;overflow:hidden;\" split=\"false\"\r\n");
      out.write("\t\tborder=\"false\">\r\n");
      out.write("\t\t<div class=\"datagrid-toolbar\">\r\n");
      out.write("\t\t\t<a id=\"save\" icon=\"icon-save\" href=\"#\" class=\"easyui-linkbutton\"\r\n");
      out.write("\t\t\t\tplain=\"true\">新单</a>\r\n");
      out.write("\t\t\t<a id=\"edit\" icon=\"icon-edit\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/page_qupai_noticebill.action\" class=\"easyui-linkbutton\"\r\n");
      out.write("\t\t\t\tplain=\"true\">工单操作</a>\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div region=\"center\" style=\"overflow:auto;padding:5px;\" border=\"false\">\r\n");
      out.write("\t\t<form id=\"noticebillForm\" action=\"\" method=\"post\">\r\n");
      out.write("\t\t\t<table class=\"table-edit\" width=\"95%\" align=\"center\">\r\n");
      out.write("\t\t\t\t<tr class=\"title\">\r\n");
      out.write("\t\t\t\t\t<td colspan=\"4\">客户信息</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>来电号码:</td>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" class=\"easyui-validatebox\" name=\"telephone\"\r\n");
      out.write("\t\t\t\t\t\trequired=\"true\" /></td>\r\n");
      out.write("\t\t\t\t\t<td>客户编号:</td>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" class=\"easyui-validatebox\"  name=\"customerId\"\r\n");
      out.write("\t\t\t\t\t\trequired=\"true\" /></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>客户姓名:</td>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" class=\"easyui-validatebox\" name=\"customerName\"\r\n");
      out.write("\t\t\t\t\t\trequired=\"true\" /></td>\r\n");
      out.write("\t\t\t\t\t<td>联系人:</td>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" class=\"easyui-validatebox\" name=\"delegater\"\r\n");
      out.write("\t\t\t\t\t\trequired=\"true\" /></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr class=\"title\">\r\n");
      out.write("\t\t\t\t\t<td colspan=\"4\">货物信息</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>品名:</td>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" class=\"easyui-validatebox\" name=\"product\"\r\n");
      out.write("\t\t\t\t\t\trequired=\"true\" /></td>\r\n");
      out.write("\t\t\t\t\t<td>件数:</td>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" class=\"easyui-numberbox\" name=\"num\"\r\n");
      out.write("\t\t\t\t\t\trequired=\"true\" /></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>重量:</td>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" class=\"easyui-numberbox\" name=\"weight\"\r\n");
      out.write("\t\t\t\t\t\trequired=\"true\" /></td>\r\n");
      out.write("\t\t\t\t\t<td>体积:</td>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" class=\"easyui-validatebox\" name=\"volume\"\r\n");
      out.write("\t\t\t\t\t\trequired=\"true\" /></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>取件地址</td>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"3\">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" id=\"ssq\" name=\"ssq\"/>\r\n");
      out.write("\t\t\t\t\t\t<select id=\"province\" name=\"province\" onchange=\"loadcity(value,city)\">\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"none\">--请选择省--</option>\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t<select id=\"city\" name=\"city\" onchange=\"loadcity(value,district)\">\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"none\">--请选择市--</option>\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t<select id=\"district\" name=\"district\" >\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"none\">--请选择县--</option>\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" class=\"easyui-validatebox\" name=\"pickaddress\" required=\"true\" size=\"68\"/>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>到达城市:</td>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" class=\"easyui-validatebox\" name=\"arrivecity\"\r\n");
      out.write("\t\t\t\t\t\trequired=\"true\" /></td>\r\n");
      out.write("\t\t\t\t\t<td>预约取件时间:</td>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" class=\"easyui-datebox\" name=\"pickdate\"\r\n");
      out.write("\t\t\t\t\t\tdata-options=\"required:true, editable:false\" /></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>备注:</td>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"3\"><textarea rows=\"5\" cols=\"80\" type=\"text\" class=\"easyui-validatebox\" name=\"remark\"\r\n");
      out.write("\t\t\t\t\t\trequired=\"true\" ></textarea></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}