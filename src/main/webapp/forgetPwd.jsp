<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>天津市人大代表履职服务平台</title>
<link rel="shortcut icon" href="${ctx}/ui/favicon.ico" /> 
<%@ include file="/common/import-basic-js-css.jsp"%>
<script>
	$(function(){
		 $.metadata.setType("attr", "validate");
			$("#inputForm").validate({
			        submitHandler:function(form){ 
			          	$.ajax({
			       		   type: "POST",
			       		   url:$("#inputForm").attr("action"),
			       		   data:$("#inputForm").serialize(),
			      			error:Free.ajaxError,
			       		   success: function(data){
				       	     	if(data=='ok'){
				       	     		freeAlert("您的密码已经重置成功，稍后会发送到您的手机上，请您保持手机开启状态！"); 
				       	     		$("#xm").val("");
				       	     		$("#yddh").val("");
				       	     	}else if("nouser"){
				       	     		freeAlert("您提供的信息不准确，无法重置密码！");
					       	     	$("#xm").val("");
				       	     		$("#yddh").val("");
				       	     	}else{
				       	     		freeAlert("重置密码失败！");
				       	     	}
			       		}
			         });
			        }    
			    });
	});
</script>
</head>
<body>
<form  class="form-horizontal"  id="inputForm"  action="${ctx}/system/user/forgetPwd.do" method="post" style="padding:100px 0 0 500px">
	<div class="form-group">
		<label for="xm">姓名</label>
		<input name="xm" id="xm" class="span4" type="text" validate="{required:true}" placeholder="请输入姓名"  />
	</div>
	<br/>
	<div class="form-group">
		<label for="yddh">电话号码<span style="color: red;">(您在本平台注册的手机号)</span></label>
		<input name="yddh" id="yddh" class="span4" type="text" validate="{required:true}" placeholder="请输入手机号"  />
		
	</div>
	<br/>
	<div class="form-group">
		<button class="btn btn-success" id="free_submit" type="submit">找回密码 </button>
		<a class="btn btn-success" href="login.jsp" >回到首页</a>
	</div>
</form>
</body>
</html>