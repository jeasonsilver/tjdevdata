<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script>

$("#inputForm").form({callback:function(){
	if (treeObj){
		treeObj.reAsyncChildNodes(null, "refresh");
	}
}});

$(function(){
	if ("${param.pid}"!=""){
		$("#pid").val("${param.pid}");
	}
	if ("${param.type}"!=""){
		$("#type").val("${param.type}");
	}
    $("#tid").select2({});
});
</script>
<form method="post" id="inputForm" class="form-horizontal" action="${ctx}/system/group/save.do" >
<input type="hidden" name="id" value="${ob.id}" />
	<div class="control-group">
		<label class="control-label" for="">人员</label>
		<div class="controls">
		    <select id="tid" name="tid" <c:if test="${!empty multiple}">multiple="multiple"</c:if>  style="width:286px" >
               <c:forEach items="${users}" var="u" >
               <option value="${u.id}" >${u.xm}</option>
               </c:forEach>
            </select>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">父级ID</label>
		<div class="controls">
			<input name="pid" id="pid" class="span4" value="${ob.pid}" type="text" readonly="readonly"  maxlength="20" />
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<button class="btn btn-success" id="free_submit" type="button"> 保存 </button>
			<button class="btn" type="reset"> 重置 </button>
		</div>
	</div>

</form>
