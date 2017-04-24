<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.free.common.utils.Encryption" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html >
<html>
<head>
<title>大数据平台</title>
<link rel="shortcut icon" href="${ctx}/ui/favicon.png" /> 
<link href="${ctx}/ui/css/login.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/ui/jquery-1.9.0.min.js"></script> 
<script src="${ctx}/ui/free.form.js"></script> 
<link href="${ctx}/ui/dialog.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/ui/free.dialog.js"></script>
<script src="${ctx}/ui/JQuery.md5.js"></script> 
    
    <script type="text/javascript">
		
	    if( window.top != window.self ){
	    	window.top.location.href='login.jsp';
	     }
		
	    document.onkeydown = function (e) { 
	    	var theEvent = window.event || e; 
	    	var code = theEvent.keyCode || theEvent.which; 
	    	if (code == 13) { 
	    		$('.login_but').click();
	    	} 
	    } 
		$(function(){
			$("#loginname").focus();
			 $("#loginname").val(getCookie("loginname"));
			$("#password").val(getCookie("password")); 
			$('.login_but').click(function(){
				 if($('form').validate()){
					if($("#remberPwd").prop("checked")==true){
						setCookie("loginname",$("#loginname").val());
						setCookie("password",$("#password").val());
					} 
					
					 // f.password.value=hex_md5(f.loginname.value);
					 // f.submit();//提交表单
					
					 f.password.value=hex_md5(f.password.value);
					 //var upw = document.getElementById("password").value;
					 //alert("upw=" + upw);
					$('form').submit();
				}
			});
			if('${message}'!=''){
				//$('#message').show();
				alert('${message}');
				//$('#message').append('${message}');
			} 
		})
	
	function setCookie(name,value)
		{
		    var Days = 30; 
		    var exp  = new Date();    
		    exp.setTime(exp.getTime() + Days*24*60*60*1000);
		    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
		}
		 
		
		function getCookie(name)
		{
		    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
		    if(arr != null) return unescape(arr[2]); return null;
		}
    </script>
</head>
<body>
<div id="title">
  <img id="top_icon" src="${ctx}/ui/images/login/top_icon.png" width="72" height="69" style="margin-left: 8px;" <%--align="absmiddle" --%>/>
    <div id="title_text">大数据平台</div>
</div>
<div id="cen">

<form name="f" action="${ctx}/system/user/login.do" method="post" >
  <div id="login">
    <ul>
      <li id="login_header"></li>
      <li class="but_text">用户名
        <input name="loginname" id="loginname" type="text" class="but" validate="{required:true}" label="用户名"/>
      </li>
      <li class="but_text">密&emsp;码
		 <input name="password" id="password"  type="password" class="but"  validate="{required:true}" maxlength="16"  label="密码" />
      </li>
      <!-- <li class="but_text" >登入方式&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="uname" value="0" checked/>&nbsp;领导登入&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="uname" value="1" />&nbsp;一般登入</li> --> 
      <input type="hidden" name="uname" value="0" />
      <li id="login_user">
       <span id="message" style="display:none"><img src="${ctx}/ui/login/images/login/cancel.png" width="20" height="20" align="absmiddle" />&nbsp;</span><br /> 
      <a class="login_but submit" href="javascript:return false;">登录</a> 
      <a class="but_01" href="javascript:window.close();">取消</a></li>
      
      
    </ul>
  </div>
  </form>
</div>
<div id="footer">2017 Copyright 公安部第一研究所</div>

</body>
</html>
