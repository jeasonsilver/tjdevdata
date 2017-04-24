<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html >
<html>
<head>
<title>天津市人大代表履职服务平台</title>
<link rel="shortcut icon" href="${ctx}/ui/favicon.ico" /> 
<link href="${ctx}/ui/css/login.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/ui/jquery-1.9.0.min.js"></script> 
<script src="${ctx}/ui/free.form.js"></script> 
<link href="${ctx}/ui/dialog.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/ui/free.dialog.js"></script>
    
    <script type="text/javascript">
		// 跳出框架
	    if( window.top != window.self ){
	    	window.top.location.href='login.jsp';
	     }
		// 回车自动提交
	    document.onkeydown = function (e) { 
	    	var theEvent = window.event || e; 
	    	var code = theEvent.keyCode || theEvent.which; 
	    	if (code == 13) { 
	    		$('.login_but').click();
	    	} 
	    } 
		$(function(){
			$("#loginname").focus();//默认焦点
			$("#loginname").val(getCookie("loginname"));
			$("#password").val(getCookie("password"));
			$('.login_but').click(function(){
				if($('form').validate()){
					if($("#remberPwd").prop("checked")==true){//jquery1.9新方法
						setCookie("loginname",$("#loginname").val());
						setCookie("password",$("#password").val());
					}
					$('form').submit();
				}
			});
			if('${message}'!=''){
				$('#message').show();
				//登录失败：用户名或密码输入错误！
				$('#message').append('${message}');
			}
		})
		//写cookies函数
		//两个参数，一个是cookie的名称，一个是值
		function setCookie(name,value)
		{
		    var Days = 30; //此 cookie 将被保存 30 天
		    var exp  = new Date();    //new Date("December 31, 9998");
		    exp.setTime(exp.getTime() + Days*24*60*60*1000);
		    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
		}
		 
		//读取cookies函数
		function getCookie(name)
		{
		    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
		    if(arr != null) return unescape(arr[2]); return null;
		}
    </script>
</head>

<body>
<div id="light"> </div>
<div id="city"></div>

<form action="${ctx}/system/user/login.do" method="post" >
<ul id="login">
  <li id="but">
  	<span class="white">用户名：</span>
    <input name="loginname" id="loginname" type="text" class="but_01" validate="{required:true}" label="用户名"  />
    <br />
  	<span class="white">密&nbsp;&nbsp;&nbsp;码：</span>
    <input name="password" id="password" type="password" class="but_02" validate="{required:true}"  label="密码"  />
    
  </li>
  <li id="but02">
  	<span id="message" style="display:none"><img src="${ctx}/ui/login/images/login/cancel.png" width="20" height="20" align="absmiddle" />&nbsp;</span><br />
    <span style="visibility: hidden;">记记记记记</span><a  class="text_link" href="forgetPwd.jsp">忘记密码</a><a class="login_but submit" href="javascript:return false;"></a></li>
</ul>
</form>
</body>
</html>