<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找回密码主页</title>
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style_grey.css" />
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
<script type="text/javascript">
	if(window.self != window.top){
		window.top.location = window.location;
	}
	var flag = false;
	
	$(function(){
		
		$("input[name='password']").blur(function(){
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
		
		
		$("input[name='rePassword']").blur(function(){
			var password = $("input[name='password']").val();
			var rePassword = $("input[name='rePassword").val();
			if(password==rePassword){
				flag = true;
				$("#rePassworderror").html("");
			}else{
				flag = false;
				$("#rePassworderror").html("两次密码不一致，请重新录入");
				$("input[name='password']").val("");
				$("input[name='rePassword']").val("");
				//$("#passworderror").html("");
				return;
			}
			flag = true;
		});
		
		
		
			$("#reSetPwdCon").click(function(){
				
			
				/* if(flag){
					$.post("${pageCotext.request.contextPath}/user/user_reSetPwd",{"password":$("input[name='password']").val(""),"rePassword":$("input[name='rePassword']").val(""),"telephone":$("#telephone").val()},function(data){
						if(data){
							location.href="${pageCotext.request.contextPath}/login.jsp";
						}else{
							$.messager.alert('通知','密码更新失败','info'); 
						}
					});  */
				
				if(flag){
					$("#newsmsform").submit(); 
				}
				
			});
		
	});

</script>
</head>
<body>
	<div
		style="width: 900px; height: 50px; position: absolute; text-align: left; left: 50%; top: 50%; margin-left: -450px;; margin-top: -280px;">
		<img src="${pageContext.request.contextPath }/images/logo.png" style="border-width: 0; margin-left: 0;" />
		<span style="float: right; margin-top: 35px; color: #488ED5;">新BOS系统以宅急送开发的ERP系统为基础，致力于便捷、安全、稳定等方面的客户体验</span>
	</div>
	<div class="main-inner" id="mainCnt"
		style="width: 900px; height: 440px; overflow: hidden; position: absolute; left: 50%; top: 50%; margin-left: -450px; margin-top: -220px; background-image: url('${pageContext.request.contextPath }/images/bg_login.jpg')">
		<div id="loginBlock" class="login"
			style="margin-top: 80px; height: 255px;">
			<div class="loginFunc">
				<div id="lbNormal" class="loginFuncMobile">重置密码</div>
			</div>
			
			
			 <div class="loginForm">
				<form id="newsmsform" name="loginform" method="post" class="niceform"
					action="${pageCotext.request.contextPath}/user/user_reSetPwd">
					<s:hidden name="telephone"></s:hidden>
					<div id="idInputLine" class="loginFormIpt showPlaceholder"
						style="margin-top: 5px;">
						<input id="loginform:idInput" type="text" name="password"
							class="loginFormTdIpt" maxlength="50"/>
						<label for="idInput" class="placeholder" id="idPlaceholder">新密码：</label>
						
					</div>
					
					<div class="forgetPwdLine">
					</div>
					<div id="pwdInputLine" class="loginFormIpt showPlaceholder">
						<input id="loginform:pwdInput" class="loginFormTdIpt" type="text" name="rePassword"/>
						<label for="pwdInput" class="placeholder" id="pwdPlaceholder">确认密码：</label>
					</div>
				
				
					<div class="loginFormIpt loginFormIptWiotTh"	style="margin-top:58px;">
					
					<a href="#" id="reSetPwd" name="reSetPwd">
						<span id="reSetPwdCon" class="btn btn-login" style="margin-top:-36px;" >确认</span>
					</a>
					<span id="passworderror"></span>	
					<span id="rePassworderror"></span>
					</div>
					
				</form>
			</div> 
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



</html>