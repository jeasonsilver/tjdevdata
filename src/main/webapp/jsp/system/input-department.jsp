<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<script>

$("#inputForm").form({callback:function(){
	if (treeObj){
		treeObj.reAsyncChildNodes(null, "refresh");
	}
}});

$(function(){
	if ("${param.fid}"!=""){
		$("#fid").val("${param.fid}");
	}
});
</script>
<form method="post" id="inputForm" class="form-horizontal" action="${ctx}/system/department/save.do" >
<input type="hidden" name="id" value="${ob.id}" />
	<div class="control-group">
		<label class="control-label" for="">部门名称</label>
		<div class="controls">
			<input name="deptname" class="span4" value="${ob.deptname}" type="text" validate="{required:true}"  maxlength="30" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">部门电话</label>
		<div class="controls">
			<input name="deptphone" class="span4" value="${ob.deptphone}" type="text"   maxlength="20" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">部门地址</label>
		<div class="controls">
			<input name="deptadress" class="span4" value="${ob.deptadress}" type="text"   maxlength="30" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">部门负责人</label>
		<div class="controls">
			<input name="deptfzr" class="span4" value="${ob.deptfzr}" type="text"   maxlength="10" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">部门联系人</label>
		<div class="controls">
			<input name="deptlxr" class="span4" value="${ob.deptlxr}" type="text"   maxlength="10" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">父级部门ID</label>
		<div class="controls">
			<input name="fid" id="fid" class="span4" value="${ob.fid}" type="text"   maxlength="20" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">排序</label>
		<div class="controls">
			<input name="ordercol" id="ordercol" class="span4" value="${ob.ordercol}" type="text"   maxlength="20" validate="{required:true,digits:true}"/>
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<button class="btn btn-success" id="free_submit" type="button"> 保存 </button>
			<button class="btn" type="reset"> 重置 </button>
		</div>
	</div>

</form>
