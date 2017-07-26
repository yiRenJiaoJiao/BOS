<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理定区/调度排班</title>
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
	src="${pageContext.request.contextPath }/js/formToJson/toJson.js"
	type="text/javascript"></script>
	
<script type="text/javascript">
	function doAdd(){
		$('#subareaGrid').datagrid({'url':'${pageContext.request.contextPath }/bc/subarea_findAllSubareaNoAssosiation'});
		$("input[name='id']").removeAttr('readonly');
		$('#pid').validatebox({required: true, validType: ['postID','uniquePostID']});
		$('#addDecidedzoneWindow').window("open");
	}
	
	function doEdit(){
	//	alert("修改...");
	
		var arr = $('#grid').datagrid('getSelections');
		if(arr.length==0){
			$.messager.alert('通知','您还没有选中要修改的行','info'); 
		}else if(arr.length>1){
			$.messager.alert('通知','您一次只能修改一行','info');
			$("#grid").datagrid("clearSelections");
		}else{
			if(arr.length==1){
				id = arr[0].id;
				$('#subareaGrid').datagrid({'url':'${pageContext.request.contextPath }/bc/subarea_findAllSubareasById?id='+id});
				$("input[name='id']").attr('readonly',true);
				$("input[name='id']").validatebox({required: true,validType:''});
				
				$("input[name='id']").val(arr[0].id);
				$("input[name='name']").val(arr[0].name);
				$("#staffID").combobox('setValues', [arr[0].staff.id]);
				$("input[name='staff.telephone']").val(arr[0].staff.telephone);
				
				
				$.post("${pageContext.request.contextPath}/bc/subareas_findSubareas",{"decidedZoneId":id},function(data){
					  //循环数据找出列表中ID和需要选中数据的ID相等的数据并选中
					 var rows = $("#subareaGrid").datagrid("getRows");
					 for (var i = 0; i < rows.length; i++) {
						 var rowId = rows[i].sid;
						 for (var j = 0; j < data.length; j++) {
							if(rowId==data[j].sid){
								 var index = $("#subareaGrid").datagrid("getRowIndex",rows[i])
						          $("#subareaGrid").datagrid("checkRow",index);
							}
						}
						 
					}
				});
				$('#addDecidedzoneWindow').window("open");
				$("#grid").datagrid("clearSelections");
				//
			//});
			}
		}
	}
	
	function doDelete(){
	//	alert("删除...");
		var arr = $('#grid').datagrid('getSelections');
		if(arr.length!=0){
			if(arr.length=1){
				var id = arr[0].id;
				$.post("${pageContext.request.contextPath}/bc/decidedZone_deleteDecidedZone",{"id":id},function(data){
					if(data){
						$.messager.alert('通知','删除成功','info'); 
					}else{
						
						$.messager.confirm('Confirm', '此组存在与其关联的收派地址,取派人员信息和分区地址信息,确认要删除吗', function(r){
							if (r){ 
								$.post("${pageContext.request.contextPath}/bc/decidedZone_deleteDecidedZoneAssociation",{"id":id},function(data){
									if(data){
										$.messager.alert('通知','删除成功','info'); 
										$("#grid").datagrid("reload");
										$("#grid").datagrid("clearSelections");
										
									}else{
										$.messager.alert('通知','系统正忙','info'); 
										$("#grid").datagrid("clearSelections");
									}
								})
							} 
							
						});
					}
				})
			}else{
				$.messager.alert('通知','您一次只能选择一个','info'); 
			}
		}else{
			$.messager.alert('通知','您还没有选中，请选中后再进行操作','info'); 
		}
	}
	
	function doSearch(){
		$('#searchWindow').window("open");
	}
	
	function doAssociations(){
		var arr = $('#grid').datagrid('getSelections');
		if(arr.length==0){
			$.messager.alert('通知','您还没有选中要操作的行','info');
		}else if(arr.length>1){
			$.messager.alert('通知','您一次只能操作一行','info');
		}else{
			var id = arr[0].id;
			
			//清空表单
			$("#associationSelect").empty();
			$("#noassociationSelect").empty();
			$.post("${pageContext.request.contextPath}/bc/decidedZone_getInUseAssosiation",{"id":id},function(data){
				//获取定区的已经关联的客户
				$(data).each(function(){
					$("#associationSelect").append("<option value="+this.id+">"+this.name+"</option>");
				});
				
			});
			
			
			//获取未关联的客户
			$.post("${pageContext.request.contextPath}/bc/decidedZone_getNoAssosiation",{"id":id},function(data){
				//获取定区的已经关联的客户
				$(data).each(function(){
					$("#noassociationSelect").append("<option value="+this.id+">"+this.name+"</option>");
				});
				
			});
		
		//	$('#searchWindow').window("open");
		}
		$('#customerWindow').window('open');
	}
	
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
		text : '修改',
		iconCls : 'icon-edit',
		handler : doEdit
	},{
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-association',
		text : '关联客户',
		iconCls : 'icon-sum',
		handler : doAssociations
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		title : '定区编号',
	//	checkbox : true,
		width : 120,
		align : 'center',
	
	
	},{
		field : 'name',
		title : '定区名称',
		width : 120,
		align : 'center',
	
	}, {
		field : 'staff.name',
		title : '负责人',
		width : 120,
		align : 'center',
	
		formatter : function(data,row ,index){
			return row.staff.name;
		}
	}, {
		field : 'staff.telephone',
		title : '联系电话',
		width : 120,
		align : 'center',
	
		formatter : function(data,row ,index){
			return row.staff.telephone;
		}
	}, {
		field : 'staff.station',
		title : '所属公司',
		width : 120,
		align : 'center',
	
		formatter : function(data,row ,index){
			return row.staff.station;
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
			pageList: [5,15,20],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/bc/decidedZone_finAll",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow,
			onClickRow:onClickRow,
			singleSelect:true
		});
		
		// 添加、修改定区
		$('#addDecidedzoneWindow').window({
	        title: '添加修改定区',
	        width: 600,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	        
	    });
		
		// 查询定区
		$('#searchWindow').window({
	        title: '查询定区',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false,
	        formatter : function(data,row, index){
				if(data=="1"){
					return "有";
				}else{
					return "无";
				}
			}
	    });
		
		
		$("#btn").click(function(){
			alert("执行查询...");
		});
		
		
	});
	
	function onClickRow(rowIndex, rowData){
		
	}

	function doDblClickRow(rowIndex, rowData){
		//alert("双击表格数据...");
		
			//清空关联区域的数据?????????
			
			
				/* $('#association_subarea').datagrid('loadData', { total: 0, rows: [] });
				$('#association_customer').datagrid('loadData', { total: 0, rows: [] }); */
			$('#association_subarea').datagrid( {
				fit : true,
				border : true,
				rownumbers : true,
				striped : true,
				
				url : "${pageContext.request.contextPath}/bc/subarea_findAllByDecidedZoneId?decidedZoneId="+rowData.id,
				columns : [ [{
					field : 'id',
					title : '分拣编号',
					width : 120,
					align : 'center'
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
					align : 'center'
				}, {
					field : 'startnum',
					title : '起始号',
					width : 100,
					align : 'center'
				}, {
					field : 'endnum',
					title : '终止号',
					width : 100,
					align : 'center'
				} , {
					field : 'single',
					title : '单双号',
					width : 100,
					align : 'center'
				} , {
					field : 'position',
					title : '位置',
					width : 200,
					align : 'center'
				} ] ],
				onBeforeLoad:function(){
					var allrows = $('#association_subarea').datagrid('getRows');
					if(allrows.length!=0){
						for (var i = 1; i < allrows.length; i++) {
							 var rowIndex = $('#association_subarea').datagrid('getRowIndex', allrows[i]);
							$('#association_subarea').datagrid('deleteRow',rowIndex);
						}
					}
					
				//	$('#association_subarea').datagrid('loadData', { total: 0, rows: [] });
					return true;
				}
			});
		
		$("#grid").datagrid('clearSelections');
		
		$('#association_customer').datagrid( {
			fit : true,
			border : true,
			rownumbers : true,
			striped : true,
			url : "${pageContext.request.contextPath}/bc/decidedZone_getInUseAssosiation?id="+rowData.id,
			columns : [[{
				field : 'id',
				title : '客户编号',
				width : 120,
				align : 'center'
			},{
				field : 'name',
				title : '客户名称',
				width : 120,
				align : 'center'
			}, {
				field : 'station',
				title : '所属单位',
				width : 120,
				align : 'center'
			}]],
			onBeforeLoad:function(){

				var allrows1 = $('#association_customer').datagrid('getRows');
				if(allrows1.length!=0){
					for (var j = 1; j < allrows1.length; j++) {
						 var rowIndex = $('#association_subarea').datagrid('getRowIndex', allrows1[j]);
						$('#association_customer').datagrid('deleteRow',rowIndex);
					}
				}  
				//$('#association_customer').datagrid('loadData', { total: 0, rows: [] });
				return true;
			}
		});
		
	}
	
	
	function saveFormBtn(){
		var flag = $('#addForm').form('validate')
		if(flag){
			$("#addForm").submit();
			$('#addDecidedzoneWindow').window("close");
			$("#grid").datagrid("reload");
		}
	}

	
	
	$.extend($.fn.validatebox.defaults.rules, { 
		uniqueId: { 
		validator: function(value,param){ 
			var flag ;
			
			$.ajax({
				   type: "POST",
				   url: "${pageContext.request.contextPath}/bc/decidedZone_uniqueId",
				   data: {"id":value},
				   timeout : 60000,
				   async: false,
				   success: function(data){
					 flag =data;
				   }
				});
			return flag;
		}, 
		message: '您输入的编码已经存在' 
		} ,
		
		telephone: { 

		validator: function(value,param){ 

			var regex = /^1[3|5|7|8]\d{9}$/;
			return regex.test(value);
		}, 
		
		message: '您录入的不是手机号，请重新录入' 

		},
	}); 
	

	function updateFormBtn(){
		if($('#updateForm').form('validate')){
			$("#updateForm").submit();
		}
		$('#updateDecidedzoneWindow').window("close");
	}

	
	
	
		$(function(){
			//通过输入的staffid,获取电话号码
			$('#staffID').combobox({  	
				onSelect: function(record){ 
					$.post("${pageContext.request.contextPath}/bc/staff_finTelephone",{"id":record.id},function(data){
						$("input[name='staff.telephone']").val(data['telephone']);
					});
			      } ,
			      //此处框体中的值取消不了，咋回事？
			     onUnselect:function(record){
			    	  $("input[name='staff.telephone']").val("");
			      }
			});
			
			$("#associationBtn").click(function(){
				var arr = $('#grid').datagrid('getSelections');
				$("#customerDecidedZoneId").val(arr[0].id);
				$("#associationSelect option").attr("selected","selected");
				$("#customerForm").submit();
				$('#searchWindow').window("close");
			});
			
			$("#toRight").click(function(){
				$("#associationSelect").append($("#noassociationSelect option:selected"));
			});
			
			$("#toLeft").click(function(){
				$("#noassociationSelect").append($("#associationSelect option:selected"));
				
			});
		
		});	
		
		//关闭关联窗口清空表单
		  function closeAssosiationWindow(){
			$('#customerForm').form('clear');
		}  
		
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<div region="south" border="false" style="height:150px">
		<div id="tabs" fit="true" class="easyui-tabs">
			<div title="关联分区" id="subArea"
				style="width:100%;height:100%;overflow:hidden">
				<table id="association_subarea"></table>
			</div>	
			<div title="关联客户" id="customers"
				style="width:100%;height:100%;overflow:hidden">
				<table id="association_customer"></table>
			</div>	
		</div>
	</div>
	
	<!-- 添加 修改分区 -->
	<div class="easyui-window" title="定区添加修改" id="addDecidedzoneWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="saveBtn" onclick="saveFormBtn()" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="addForm" action="${pageContext.request.contextPath }/bc/decidedZone_addDecidedZone" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">定区信息</td>
					</tr>
					<tr>
						<td>定区编码</td>
						<td><input type="text" name="id" id="addDEcidedZoneID"  class="easyui-validatebox" data-options="required:true,validType:'uniqueId'"/></td>
					</tr>
					<tr>
						<td>定区名称</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>选择负责人</td>
						<td>
							<input class="easyui-combobox" name="staff.id"  id="staffID" 
    							data-options="valueField:'id',textField:'name',url:'${pageContext.request.contextPath }/bc/staff_findAllStaff'" />  
						</td>
					</tr>
					<tr>
						<td>电话</td>
						<td>
							<input id="telephone" name="staff.telephone"  />  
						</td>
					</tr>
					<tr height="300">
						<td valign="top">关联分区</td>
						<td>
						<%-- url='${pageContext.request.contextPath }/bc/subarea_findAllSubareaNoAssosiation' --%>
							<table id="subareaGrid"  class="easyui-datagrid" border="false" style="width:300px;height:300px"  data-options="fitColumns:true,singleSelect:false">
								<thead>  
							        <tr>  
							            <th data-options="field:'sid',width:30,checkbox:true">编号</th>  
							            <th data-options="field:'addresskey',width:150">关键字</th>  
							            <th data-options="field:'position',width:200,align:'right'">位置</th>  
							        </tr>  
							    </thead> 
							</table>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<!-- 查询定区 -->
	<div class="easyui-window" title="查询定区窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="searchForm">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">查询条件</td>
					</tr>
					<tr>
						<td>定区编码</td>
						<td><input type="text" name="id"/></td>
					</tr>
					<tr>
						<td>所属单位</td>
						<td><input type="text" name="staff.station"/></td>
					</tr>
					
					
					
					<tr>
						<td>是否关联分区</td>
						<td>
						<select id="assosiatonSubarea" name="isAssosiatonSubarea" class="easyui-combobox" style="width:200px;"> 
							<option value="">--请选择--</option> 
							<option value="1">有</option> 
							<option value="0">无</option> 
						</select> 
						<!-- <input type="text" name="subareaName" class="easyui-validatebox" required="true"/></td> -->
					</tr>
					<tr>
						<td colspan="2"><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> </td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<!-- 关联客户窗口 -->
	<div class="easyui-window" title="关联客户窗口" id="customerWindow" collapsible="false" closed="true" minimizable="false" data-options="onBeforeClose:closeAssosiationWindow" maximizable="false" style="top:20px;left:200px;width: 400px;height: 300px;">
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="customerForm" action="${pageContext.request.contextPath }/bc/decidedzone_assigncustomerstodecidedzone.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="3">关联客户</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="id" id="customerDecidedZoneId" />
							<select id="noassociationSelect" multiple="multiple" size="10"></select>
						</td>
						<td>
							<input type="button" value="》》" id="toRight"><br/>
							<input type="button" value="《《" id="toLeft">
						</td>
						<td>
							<select id="associationSelect" name="customerIds" multiple="multiple" size="10"></select>
						</td>
						
						
					</tr>
					<tr>
						<td colspan="3"><a id="associationBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">关联客户</a> </td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>