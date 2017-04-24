<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script>



$(function(){

	Free.validateSubmitAndClose($("#inputForm"));
	
});
</script>

<form id="inputForm" class="form-horizontal"  action="${ctx}/system/code/save.do"  >

<input type="hidden" name="id" value="${ob.id}" />
	<div class="control-group">
		<label class="control-label" for="">种类名称</label>
		<div class="controls">
			<input name="sectionname" class="span4" value="${ob.sectionname}" type="text" validate="{required:true}"   />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">代码</label>
		<div class="controls">
			<input name="code" class="span4" value="${ob.code}" type="text"   />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">代码名</label>
		<div class="controls">
			<input name="codename" class="span4" value="${ob.codename}" type="text"   />
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<button class="btn btn-success" id="free_submit" type="submit"> 保存 </button>
		</div>
	</div>

</form>
