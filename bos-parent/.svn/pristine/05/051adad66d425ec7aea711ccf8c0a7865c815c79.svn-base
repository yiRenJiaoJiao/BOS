<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找回密码主页</title>
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style_grey.css" />

<link id="easyuiTheme" rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>

<style>
input[type=text] {
	width: 80%;
	height: 25px;
	font-size: 12pt;
	font-weight: bold;
	margin-left: 45px;
	padding: 3px;
	border-width: 0;
}

input[type=password] {
	width: 80%;
	height: 25px;
	font-size: 12pt;
	font-weight: bold;
	margin-left: 45px;
	padding: 3px;
	border-width: 0;
}

#loginform\:codeInput {
	margin-left: 1px;
	margin-top: 1px;
}

#loginform\:vCode {
	margin: 0px 0 0 60px;
	height: 34px;
}
</style>

</head>
<body>
	<div
		style="width: 900px; height: 50px; position: absolute; text-align: left; left: 50%; top: 50%; margin-left: -450px;; margin-top: -280px;">
		<img src="${pageContext.request.contextPath }/images/logo.png" style="border-width: 0; margin-left: 0;" />
		<span style="float: right; margin-top: 35px; color: #488ED5;">新BOS系统以宅急送开发的ERP系统为基础，致力于便捷、安全、稳定等方面的客户体验</span>
	</div>
	<!-- 验证码发送 -->
	<div class="main-inner" id="mainCnt"
		style="width: 900px; height: 440px; overflow: hidden; position: absolute; left: 50%; top: 50%; margin-left: -450px; margin-top: -220px; background-image: url('${pageContext.request.contextPath }/images/bg_login.jpg')">
		<div id="loginBlock" class="login"
			style="margin-top: 80px; height: 255px;">
			<div class="loginFunc">
				<div id="lbNormal" class="loginFuncMobile">密码找回</div>
			</div>
			<div class="loginForm">
				<form id="newsmsform" name="loginform" method="post" class="niceform" action="">
					<div id="idInputLine" class="loginFormIpt showPlaceholder"
						style="margin-top: 5px;">
						<input id="loginform:idInput" type="text" name="telephone"
							class="loginFormTdIpt" maxlength="50"/>
						<label for="idInput" class="placeholder" id="idPlaceholder">手机号：</label>
					</div>
					<div class="forgetPwdLine"></div>
					<div id="pwdInputLine" class="loginFormIpt showPlaceholder">
						<input id="loginform:pwdInput" class="loginFormTdIpt" type="text"
							name="checkcode"/>
						<label for="pwdInput" class="placeholder" id="pwdPlaceholder">验证码：</label>
						
					</div>
						<div class="loginFormIpt loginFormIptWiotTh"
						style="margin-top:58px;">
						<a href="javascript:void(0);" id="loginform:j_id19" name="loginform:j_id19">
						<span
							id="go" class="btn btn-login"
							style="margin-top:-36px;margin-right: 155px">发送验证码</span>
						<!-- <span id="con" class="btn btn-login" style="margin-top:-36px;" onclick="">确认</span> -->
						<!-- <a href="#" name="reSetPwd" id="reSetPwd"> -->
					
						<a href="#" id="reSetPwd" name="reSetPwd">
							<span id="con" class="btn btn-login" style="margin-top:-36px;" >确认</span>
						</a>
						
					</div>
				</form>
			</div>

		</div>
	</div>
	
	
	<!-- 弹窗 -->
			
	<div id="reSetPwdcon" class="easyui-window" title="找回密码" collapsible="false" minimizable="false" modal="true" closed="true" resizable="false"
        maximizable="false" icon="icon-save"  style="width: 400px; height: 180px; padding: 5px;
        background: #fafafa"	> 
<!-- style="width:360px;height:160px;" data-options="iconCls:'icon-save',modal:true ,closed:true" -->
				<div class="easyui-layout" data-options="fit:true"> 
					<div data-options="region:'center'"> 
					<table>
						<tr>
							<td>新密码：</td>
							<td><input type="text" name="newpwd" id="textpwd"/></td>
						</tr>
						<tr>
							<td>重复密码：</td>
							<td><input type="text" name="renewpwd" id="retextpwd"/></td>
						</tr>
					</table>
				</div> 
					
					<div data-options="region:'south',split:true" style="height:50px">
						<a id="reSetSubmit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>
						<a id="reset" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a>
						
					</div> 
					<span id="rePassworderror"></span>
				</div> 
			
			</div> 
		
	
	
	<div
		style="width: 900px; height: 50px; position: absolute; text-align: left; left: 50%; top: 50%; margin-left: -450px;; margin-top: 220px;">
		<span style="color: #488ED5;">Powered By www.itcast.cn</span><span
			style="color: #488ED5;margin-left:10px;">推荐浏览器（右键链接-目标另存为）：<a
			href="http://download.firefox.com.cn/releases/full/23.0/zh-CN/Firefox-full-latest.exe">Firefox</a>
		</span><span style="float: right; color: #488ED5;">宅急送BOS系统</span>
	</div>
</body>

<script type="text/javascript">
	if(window.self != window.top){
		window.top.location = window.location;
	}
	var active = true;
	var second = 120; // 倒计时120秒
	var secondInterval;
	$(function(){
		$("#go").click(function(){
			if(active == false) {
				return;
			}
			//  发送短信给用户手机..
				// 1 发送一个HTTP请求，通知服务器 发送短信给目标用户 
					var regex = /^1(3|5|7|8)\d{9}$/;
					var telephone =$("input[name='telephone']").val();
					if(regex.test(telephone)) {
						// 校验通过
						$.ajax({
							method: 'POST',
							url: '${pageContext.request.contextPath }/user/user_sendSms',
							data : {
								telephone : telephone
							}, 
							success:function(data) {
								if(data){
								    alert("发送短信成功!");
								    active = true;
									  if(active){
									      active = false;
											secondInterval = setInterval(function() {
												if(second < 0) {
													// 倒计时结束，允许重新发送 
													$("#go").html("重发验证码");
													active = true;
													second = 120;
													// 关闭定时器
													clearInterval(secondInterval);
													secondInterval = undefined;
												} else {
													// 继续计时
														$("#go").html(second + "秒后重发");
														second--;
												}
											}, 1000);
									  }
								}else{
									alert("发送短信出错，请联系管理员");
									active = false;
								}
							}
						});
					} else {
						// 校验失败 
						alert("手机号非法，请重新输入 ");
						return;
					}
			// 2 显示倒计时按钮，120秒后，允许重新发送 
// 		  if(active){
// 		      active = false;
// 				secondInterval = setInterval(function() {
// 					if(second < 0) {
// 						// 倒计时结束，允许重新发送 
// 						$("#go").html("重发验证码");
// 						active = true;
// 						second = 120;
// 						// 关闭定时器
// 						clearInterval(secondInterval);
// 						secondInterval = undefined;
// 					} else {
// 						// 继续计时
// 							$("#go").html(second + "秒后重发");
// 							second--;
// 					}
// 				}, 1000);
// 		  }
		});
	/* $.post("${pageCotext.request.contextPath}/user/user_smsPassword",{"checkcode":$("input[name='checkcode']").val(),"telephone":$("input[name='telephone']").val()},function(data){
		alert(data);
	}) */
			
			
	
		
	});
	
	
	
	$(function(){
		
		$("#con").click(function(){
			
		 $.post("${pageContext.request.contextPath}/user/user_smsPassword",{"checkcode":$("input[name='checkcode']").val(),"telephone":$("input[name='telephone']").val()},function(data){
				//0,用户不存在1.验证码已经失效，2.验证码输入错误，3.验证码正确
			 if(data=="3"){
				    // 弹出窗体 进行新密码修改操作...
				    $("#reSetPwdcon").window('open'); // open a window 
				 //  location.href="${pageContext.request.contextPath}/reSetPwd.jsp?telephone="+$("input[name='telephone']").val();
			 }else if(data=="0"){
					$.messager.alert('警告','该用户不存在，请输入正确的手机号','warning');  
				}else if(data=="1"){
					$.messager.alert('错误','验证码已经失效,请重新发送!','error');  
				}else if(data=="2"){
					$.messager.alert('错误','验证码输入错误,请重新发送!','error');  
				}
			}); 
		
		}); 
		
		/* $("#con").click(function(){
			$.post("${pageContext.request.contextPath}/user/user_smsPassword", 
				{"checkcode":$("input[name='checkcode']").val(),"telephone":$("input[name='telephone']").val()},
			   function(data){
			     alert(data); // John
			   }, "json");
		}); */
		
		
	});	
	
	
	
	
 	$(function(){

		var flag = false;
			
		$("input[name='newpwd']").blur(function(){
			if($(this).val()==""){
				flag = false;
				$("#passworderror").html("密码不能为空");
				return ;
			}else{
				
				var reg = /\s+/;
				if(reg.test($(this).val())){
					$("#passworderror").html("密码不可以包含空字符");
					flag = false;
					return ;
				}
				//var regex =  /^[\w]{6,20}$/;
				var regex = /^[0-9A-Za_z]{6,16}$/;
				if(!regex.test($(this).val())){
					
					$("#passworderror").html("密码必须是3到16位的数字或者字母");
					flag = false;
					return ;
				}
				flag = true;
				$("#passworderror").html("");
			}
			flag =true;
		});		
			
			$("input[name='renewpwd']").blur(function(){
				var password = $("input[name='newpwd']").val();
				var rePassword = $("input[name='renewpwd").val();
				if(password==rePassword){
					flag = true;
					$("#rePassworderror").html("");
				}else{
					flag = false;
					$("#rePassworderror").html("两次密码不一致，请重新录入");
					$("input[name='newpwd']").val("");
					$("input[name='renewpwd']").val("");
					//$("#passworderror").html("");
					return;
				}
				flag = true;
			});
			
			
			$("#reSetSubmit").click(function(){
				
				if(flag){
					$.post("${pageContext.request.contextPath}/user/user_reSetPwd",
							{"newpwd":$("input[name='newpwd']").val(),
							"renewpwd":$("input[name='renewpwd']").val(),
							"telephone":$("input[name='telephone']").val()},
							function(data){
						if(data){
							 $("#reSetPwdcon").window('close');
							// close a window 
							location.href="${pageCotext.request.contextPath}/login.jsp";
						}else{
							 $("#reSetPwdcon").window('close'); // close a window 
							$.messager.alert('通知','密码更新失败','info'); 
						}
					});  
				}
			});
			
			
			$("#reset").click(function(){
				$("input[name='newpwd']").val("");
				$("input[name='renewpwd']").val("");
			});
		
	}); 
		
		
</script>

</html>