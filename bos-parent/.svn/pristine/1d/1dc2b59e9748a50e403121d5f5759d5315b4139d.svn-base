<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div data-option="region:'center'">
		<table id="grid"></table>
	</div>
</body>
<script type="text/javascript">
$('#grid').datagrid({ 

	url:'data.json', 

	columns:[[ 

	{field:'code',title:'Code',width:100}, 

	{field:'name',title:'Name',width:100}, 

	{field:'sex',title:'Sex',width:100,align:'right'} 

	]] 

	}); 
/* { "id":"132", "pId":"13", "name":"测试自动建表","page":"http://localhost/demo/datagrid.jsp"} */

</script>
</html>