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
<input type="hidden" name="id" value="${ob.id}" />
  <div class="control-group">
		<label class="control-label" for="">旧密码</label>
		<div class="controls">
			<input name="oldpassword" class="span4" type="password" validate="{required:true}"   />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">新密码</label>
		<div class="controls">
			<input name="password"  class="span4"  type="password"  validate="{required:true}" />
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<button class="btn btn-success" id="free_submit" type="submit"> 保存 </button>
		</div>
	</div>
</form>
