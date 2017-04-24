<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script>

$(function(){
	if ("${param.pid}"!=""){
		$("#pid").val("${param.pid}");
	}
	Free.validateSubmitAndClose($("#inputForm"));
	$("#free_treeReFresh").click(function(){
		if (zTree){
			zTree.reAsyncChildNodes(null, "refresh");
		}
	})
});
</script>
<form id="inputForm" class="form-horizontal"  action="${ctx}/system/menu/save.do">

	<div class="control-group">
		<label class="control-label" for="">菜单ID</label>
		<div class="controls">
			<input name="id" class="span6" value="${ob.id}" type="text" readonly="readonly" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">菜单名称</label>
		<div class="controls">
			<input name="menuname" class="span6" value="${ob.menuname}" type="text" validate="{required:true}"  maxlength="50" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">菜单url</label>
		<div class="controls">
			<input name="url" class="span6" value="${ob.url}" type="text"  maxlength="100" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">父级菜单ID</label>
		<div class="controls">
			<input name="pid" id="pid" class="span6" value="${ob.pid}" type="text" validate="{required:true}"  maxlength="20" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">是否显示</label>
		<div class="controls">
			<input name="sfxs" class="span6" value="${ob.sfxs}" type="text"  maxlength="2" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">顺序</label>
		<div class="controls">
			<input name="sx" class="span6"  value="${ob.sx}" type="text"   maxlength="2" />
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<button class="btn btn-success" id="free_submit" type="submit"> 保存 </button>
			<button class="btn" id="free_treeReFresh"  type="button" > 刷新菜单 </button>
		</div>
	</div>


</form>
