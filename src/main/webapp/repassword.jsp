<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>天津市人大代表履职服务平台</title>
<link rel="shortcut icon" href="${ctx}/ui/favicon.ico" /> 
<link href="${ctx}/ui/css/layout.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/ui/jquery-1.9.0.min.js"></script> 
<script src="${ctx}/ui/free.core.js"></script>
<script src="${ctx}/ui/free.form.js"></script> 
<link href="${ctx}/ui/dialog.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/ui/free.dialog.js"></script>
<script src="${ctx}/ui/jquery-validation/jquery.validate.min.js"></script>
<script src="${ctx}/ui/jquery-validation/jquery.metadata.js"></script>
<script src="${ctx}/ui/jquery-validation/messages_cn.js"></script>

<script type="text/javascript">
	
 	$(function(){
 		
 		$.ajaxSetup({
 		    async: false             //默认为true，改为false  则为同步请求
 		});
 		
 		$.metadata.setType("attr", "validate");
 		$("#inputForm").validate({
 			rules:{
 				loginname:{
 					isRepeat:""
 				}
 			}
 		});
 	});
 	
 	jQuery.validator.addMethod("isRepeat", function(value, element) {  
 		return checkRepeat(value,$("#userid").val());
 	}, "该用户名已存在！");
 	
 	function checkRepeat(loginname,id){
 		var bool = false;
 		 $.ajax({
    		   type: "POST",
    		   url: '${ctx}/system/user/checkRepeak-loginname-id.do',
    		   data: {'id':id,'loginname':loginname},
    		   success: function(data){
    	     	if(data=='true'){
    	     		bool = false;
    	     	}else{
    	     		bool = true;
    	     	}
    		}});
 		 return bool;
 	}
	
</script>

</head>
<body>
<form action="${ctx}/system/user/changeUser.do" id="inputForm"   method="post">
    <input type="hidden" name="id" id="userid" value="${currentUser.id}"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td align="center" valign="top"><table width="50%" border="0" align="center" cellpadding="0" cellspacing="0" background="${ctx}/ui/images/cen/rig/header_bg.jpg">
	      <tr>
	        <td align="left" valign="middle" background="${ctx}/ui/images/cen/rig/header_bg_lef02.jpg" style="background-repeat:no-repeat;background-position:left" >&nbsp;&nbsp;<img src="${ctx}/ui/images/cen/rig/ico_01.png" width="16" height="16" align="absmiddle" />&nbsp;&nbsp;<span class="text_white16">修改密码</span></td>
	        <td width="8" align="right" valign="top" background="${ctx}/ui/images/cen/rig/header_bg.jpg" ;><img src="${ctx}/ui/images/cen/rig/header_bg_rig.jpg" width="8" height="35" /></td>
	      </tr>
	    </table>
	      </li>
	      <ul>
	        <li>
	          <table width="50%" align="center" class="tab_pag">
	            <tr class="tab_white02">
	              <td class="red">您需要重置用户名、密码！</td>
	            </tr>
	            <tr class="tab_grey02">
	              <td height="40"><img src="${ctx}/ui/images/cen/rig/procedure/ico.png" width="14" height="14" align="absmiddle" />&nbsp;用&nbsp;户&nbsp;名 ：
	                <input name="loginname" id="loginname" type="text" class="text_box" value="${currentUser.loginname}" validate="{required:true,minlength:4}"/></td>
	            </tr>
	            <tr class="tab_white02">
	              <td height="40"><img src="${ctx}/ui/images/cen/rig/procedure/ico.png" width="14" height="14" align="absmiddle" />&nbsp;旧&nbsp;密&nbsp;码&nbsp;：
					<input name="oldpassword" id="oldpassword" class="text_box" type="password" validate="{required:true}"   />
	            </tr>
	            
	            <tr class="tab_grey02">
	              <td height="40"><img src="${ctx}/ui/images/cen/rig/procedure/ico.png" width="14" height="14" align="absmiddle" />&nbsp;密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;：
					<input name="password" id="password" type="password" class="text_box"  validate="{required:true,minlength:4,maxlength:16}"/></td>
	            </tr>
	            <tr class="tab_white02">
	              <td height="40"><img src="${ctx}/ui/images/cen/rig/procedure/ico.png" width="14" height="14" align="absmiddle" />&nbsp;确认密码：
	              <input name="repassword" id="repassword" type="password" class="text_box"  validate="{required:true,minlength:4,maxlength:16,equalTo:'#password'}"/></td>
	            </tr>
	            <tr class="tab_white03">
	              <td class="tab_white03"><a class="red_but" href="javascript:save();">确认</a><a class="red_but" href="javascript:history.go(-1)">返回</a></td>
	            </tr>
	          </table>
	        </li>
	      </ul>
	      <ul>
	        <li></li>
	      </ul>
	      <ul>
	        <li></li>
	    </ul></td>
	  </tr>
	</table>
</form>
<script type="text/javascript">
	function save(){
		if($("#loginname").val() != '' && $("#password").val() != '' && $("#loginname").val() == $("#password").val()){
			alert("用户名与密码相同，请更换！");
			return;
		}
		$("#inputForm").submit();
	}
</script>
</body>
</html>