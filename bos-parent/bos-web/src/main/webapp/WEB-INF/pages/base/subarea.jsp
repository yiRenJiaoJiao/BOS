<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理分区</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
	
<script
	src="${pageContext.request.contextPath }/js/upload/jquery.ocupload-1.1.2.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath }/js/formToJson/toJson.js"
	type="text/javascript"></script>
	
<script type="text/javascript">

	var editIndex;


	function doAdd(){
		$('#addSubareaWindow').window("open");
	}
	
	function doSave(){
		//alert("保存...");
			$('#grid').datagrid('endEdit',editIndex);
	}
	function doCancel(){
		//取消
		$('#grid').datagrid('cancelEdit',editIndex);
	}
	
	
	function doDelete(){
		//alert("删除...");
		var arr = $('#grid').datagrid('getSelections');  
		if(arr.length>0){
			var a = new Array();
			for (var i = 0; i < arr.length; i++) {
				a.push(arr[i].id);
			}
			var ids = a.join(',');
			$.post("${pageContext.request.contextPath}/bc/subarea_deleteSubarea",{"ids":ids},function(data){
				if(data){
					$.messager.alert('通知','删除成功','info'); 
					$('#grid').datagrid('reload');
				}else{
					$.messager.alert('通知','删除失败','info'); 
					$('#grid').datagrid('reload');
				}
			});
			
		}else{
			$.messager.alert('通知','您还没有选中要删除的项，请选中后再操作','info'); 
		}
		
		
		
		
	}
	
	function doSearch(){
		$('#searchWindow').window("open");
	}
	
	function doExport(){
		
		if($("#searchForm").form('validate')){
			$("#searchForm").submit();
		}
		
		//alert("导出");
	/* 	$.post("${pageContext.request.contextPath}/bc/subarea_export",function(data){
			if(data){
				$.messager.alert('通知','导出数据成功','info');  
			}else{
				$.messager.alert('通知','导出数据失败','info');  
			}
		}) */
		
	}
	
//双击事件
	function doDblClickRow(rowIndex, rowData){
		editIndex = rowIndex;
		$('#grid').datagrid('beginEdit',rowIndex);
	}	
	
	//编辑后事件
	function onAfterEdit(rowIndex, rowData, changes){
		$.post("${pageContext.request.contextPath}/bc/subarea_saveEdit",rowData,function(data){
			if(data){
				$.messager.alert('通知','数据保存成功','info');  
			}else{
				$.messager.alert('通知','数据保存失败','info');  
			}
		});
	}
	
	/* function doImport(){
		alert("导入");
		
	} */
	
	//工具栏
	var toolbar = [ {
		id : 'button-search',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doSearch
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-edit',	
		text : '保存',
		iconCls : 'icon-edit',
		handler : doSave
	},{
		id : 'button-cancel',	
		text : '取消',
		iconCls : 'icon-cancel',
		handler : doCancel
	},
	
	{
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-import',
		text : '导入',
		iconCls : 'icon-redo'
		//handler : doImport
	},{
		id : 'button-export',
		text : '导出',
		iconCls : 'icon-undo',
		handler : doExport
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : 'showid',
		title : '分拣编号',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.id;
		}
	},{
		field : 'province',
		title : '省',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.region.province;
		}
	}, {
		field : 'city',
		title : '市',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.region.city;
		}
	}, {
		field : 'district',
		title : '区',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.region.district;
		}
	}, {
		field : 'addresskey',
		title : '关键字',
		width : 120,
		align : 'center',
		editor:{
			type: 'validatebox',
			options: {
				 required:"true"
			}
		}
	}, {
		field : 'startnum',
		title : '起始号',
		width : 100,
		align : 'center',
		editor:{
			type: 'validatebox',
			options: {
				 required:"true"
			}
		}
	}, {
		field : 'endnum',
		title : '终止号',
		width : 100,
		align : 'center',
		editor:{
			type: 'validatebox',
			options: {
				 required:"true"
			}
		}
	} , {
		field : 'single',
		title : '单双号',
		width : 100,
		align : 'center',
		editor:{
			type: 'validatebox',
			options: {
				 required:"true"
			}
		}
	} , {
		field : 'position',
		title : '位置',
		width : 200,
		align : 'center',
		editor:{
			type: 'validatebox',
			options: {
				 required:"true"
			}
		}
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 收派标准数据表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : true,
			rownumbers : true,
			striped : true,
			pageList: [30,50,100],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/bc/subarea_finAll",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow ,
			onAfterEdit: function(rowIndex, rowData, changes){
			
				$.post("${pageContext.request.contextPath}/bc/subarea_saveEdit",rowData,function(data){
					if(data){
						$.messager.alert('通知','数据保存成功','info');  
					}else{
						$.messager.alert('通知','数据保存失败','info');  
					}
				})
			}
			
		});
		
		// 添加、修改分区
		$('#addSubareaWindow').window({
	        title: '添加修改分区',
	        width: 600,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	        
	    });
		
		// 查询分区
		$('#searchWindow').window({
	        title: '查询分区',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		$("#save").click(function(){
			if($("#subareaForm").form("validate")){
				$("#subareaForm").submit();
			}
			$('#grid').datagrid('clearSelections');
		});
		
		$("#btn").click(function(){
			alert("执行查询...");
			var qjson={"province":$("#qprovince").val(),"city":$("#qcity").val(),"district":$("#qdistrict").val(),
					 "decidedzone.id":$("#qdecidedzone").val(),"addresskey":$("#qaddresskey").combobox('getValue')};
			//$("input[name='haspda']:checked").val(),
			$('#grid').datagrid('load',qjson);  
			$("#searchWindow").window("close");
			
		});
	/* 	$("#save").click(function(){
			
		}); */
		
		 $("#button-import").upload({
	            name: 'upload',
	            action: '${pageContext.request.contextPath}/bc/subarea_uploadSubArea',
	            enctype: 'multipart/form-data',
	            onSelect: function() {
	            	var re =/^(.+\.xls|.+\.xlsx)$/;//文件名满足.xls和 .xlsx
	            	if(re.test(this.filename())){
	            		this.submit(); //提交
	            	}else{
	            		$.messager.alert("警告","上传的文件名必须是以.xls或者是.xlsx结尾");
	            	}
	            	
	            },
	            onComplete:function(response){
	            	if(response=="true"){
	            		$.messager.alert('通知','批量导入成功','info');  
	            	}else{
	            		$.messager.alert('通知','批量导入失败','info');  
	            	}
	            }
	    });
		
		//校验编码是否唯一
		/* $("input[name=id]").blur(function(){
			$.post("",{},function(data){
				if(data){
					
				}else{
					$.messager.alert('通知','编码已经存在','info');  
				}
			});
		}) */
		
		
		//将查询表单的数据转换成json格式
		
		
		//条件查询
		 	$("#querybtn").click(function(){
		 		var qjson = $("#searchForm").serializeJson();
				$('#grid').datagrid('load',qjson);  
				$("#searchWindow").window("close");
			}); 
		
		
	});
	
	
	
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<!-- 添加 修改分区 -->
	<div class="easyui-window" title="分区添加修改" id="addSubareaWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="subareaForm" action="${pageContext.request.contextPath }/bc/subarea_saveSubarea" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">分区信息</td>
					</tr>
				<!-- 	<tr>
						<td>分拣编码</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr> -->
					<tr>
						<td>选择区域</td>
						<td>
							<input class="easyui-combobox" name="region.id"  
    							data-options="mode:'remote' ,valueField:'id',textField:'name',url:'${pageContext.request.contextPath }/bc/region_findRegionsList'" />  
						</td>
					</tr>
					<tr>
						<td>关键字</td>
						<td><input type="text" name="addresskey" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>起始号</td>
						<td><input type="text" name="startnum" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>终止号</td>
						<td><input type="text" name="endnum" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单双号</td>
						<td>
							<select class="easyui-combobox" name="single" style="width:150px;">  
							    <option value="0">单双号</option>  
							    <option value="1">单号</option>  
							    <option value="2">双号</option>  
							</select> 
						</td>
					</tr>
					<tr>
						<td>位置信息</td>
						<td><input type="text" name="position" class="easyui-validatebox" required="true" style="width:250px;"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	
	<!-- 查询分区 -->
	<div class="easyui-window" title="查询分区窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="searchForm" action="${pageContext.request.contextPath}/bc/subarea_export" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">查询条件</td>
					</tr>
			
					<tr>
						<td>省</td>
						<td><input type="text"  name="region.province"  /></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input type="text" name="region.city" /></td>
					</tr>
					<tr>
						<td>区(县)</td>
						<td><input type="text"  name="region.district"  /></td>
					</tr>
					<tr>
						<td>定区编码</td>
						<td><input type="text" name="decidedZone.id"  /></td>
					</tr>
					<tr>
						<td>关键字</td>
						<td><input type="text" name="addresskey"  /></td>
					</tr>
					<tr>
						<td colspan="2"><a id="querybtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> </td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>