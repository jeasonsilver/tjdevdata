<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script>

$(function(){
	Free.validateSubmitAndClose($("#inputForm"));
});
</script>


<form id="inputForm" class="form-horizontal" action="${ctx}/system/authorize/save.do">

<input type="hidden" name="id" value="${ob.id}" />
	<div class="control-group">
		<label class="control-label" for="">模块</label>
		<div class="controls">
			<input name="module" class="span4" value="${ob.module}" type="text" validate="{required:true}"  maxlength="100" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">描述</label>
		<div class="controls">
			<input name="description" class="span4" value="${ob.description}" type="text" validate="{required:true}"  maxlength="50" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">顺序</label>
		<div class="controls">
			<input name="sx" class="span4" value="${ob.sx}" type="text"  maxlength="4" />
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<button class="btn btn-success" id="free_submit" type="submit"> 保存 </button>
			<button class="btn" type="reset"> 重置 </button>
		</div>
	</div>

</form>
