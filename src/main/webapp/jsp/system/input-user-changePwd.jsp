<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script>

$(function(){
	Free.validateSubmitAndClose($("#inputForm"));

});
</script>
<style>

.form-horizontal .control-label {
    width: 100px;
}
.form-horizontal .controls {
    margin-left: 110px;
}
</style>
<form  class="form-horizontal"  id="inputForm"  action="${ctx}/system/user/changePwd.do">
<input type="hidden" name="id" id="id" value="${ob.id}" />
<input type="hidden" name="loginname" id="loginname" value="${ob.loginname}" />
  <div class="control-group">
		<label class="control-label" for="">旧密码</label>
		<div class="controls">
			<input name="oldpassword" class="span4" type="password" validate="{required:true}"   />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">新密码</label>
		<div class="controls">
			<input name="password" id="password" class="span4"  type="password"  validate="{required:true,minlength:4,maxlength:16}" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">确认密码</label>
		<div class="controls">
			<input name="repassword" id="repassword"  class="span4"  type="password"  validate="{required:true,minlength:4,maxlength:16,equalTo:'#password'}" />
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
		if($("#loginname").val() != '' && $("#password").val() != '' && $("#loginname").val() == $("#password").val()){
			alert("用户名与密码相同，请更换密码！");
			return;
		}
		$("#inputForm").submit();
	}
</script>