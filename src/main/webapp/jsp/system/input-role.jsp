<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script>

$("#inputForm").form({callback:function(){
	if(f_query){
		f_query();//重新加载grid
	}
	Free.closeThisDialog();
}});

$(function(){
	
	$(".freeGrid_submit").click(function(){
	});
});
</script>

<form id="inputForm" class="form-horizontal"  action="${ctx}/system/role/save.do"  >

<input type="hidden" name="id" value="${ob.id}" />
	<div class="control-group">
		<label class="control-label" for="">角色</label>
		<div class="controls">
			<input name="rolename" class="span4" value="${ob.rolename}" type="text" validate="{required:true}"   />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">描述</label>
		<div class="controls">
			<input name="description" class="span4" value="${ob.description}" type="text"   />
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<button class="btn btn-success" id="free_submit" type="submit"> 保存 </button>
		</div>
	</div>

</form>
