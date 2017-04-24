<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script>

$(function(){
	
	
		
	Free.validateSubmitAndClose($("#inputForm"));
	
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
<style>

.form-horizontal .control-label {
    width: 100px;
}
.form-horizontal .controls {
    margin-left: 110px;
}
</style>
<form  class="form-horizontal"  id="inputForm"  action="${ctx}/system/user/changeLoginName.do">
<input type="hidden" name="id" id="userid" value="${ob.id}" />
  	<div class="control-group">
		<label class="control-label" for="">用户密码</label>
		<div class="controls">
			<input name="oldpassword" id="oldpassword" class="span4" type="password" validate="{required:true}"   />
		</div>
	</div>
	
	<div class="control-group">
		<label class="control-label" for="">新用户名</label>
		<div class="controls">
			<input type="text" class="span4" name="loginname" id="loginname" validate="{required:true,minlength:4}"/>
		</div>
	</div>
	
	<div class="control-group">
		<div class="controls">
			<button class="btn btn-success" id="free_submit" type="button" onclick="save();"> 保存 </button>
		</div>
	</div>
</form>

<script type="text/javascript">
	function save(){
		if($("#loginname").val() != '' && $("#oldpassword").val() != '' && $("#loginname").val() == $("#oldpassword").val()){
			alert("用户名与密码相同，请更换密码！");
			return;
		}
		$("#inputForm").submit();
	}
</script>
