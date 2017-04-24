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
	<c:forEach var="it" items="${ob.roleAuthorizes}">
		$("#free_${it.auth_id}").attr("checked","checked"); 
	</c:forEach>
});
</script>


<form id="inputForm" action="${ctx}/system/role/saveAuthorize.do"  >

<input type="hidden" name="id" value="${ob.id}" />
<div class="content" style="margin-left: 20px">
	<c:forEach var="it" items="${auths}">
		<p><input type="checkbox" id="free_${it.id}" name="auths" value="${it.id}" >${it.description} [${it.module}]</p> 
	</c:forEach>
</div>

	<p style="text-align:center" ><button class="btn btn-success" id="free_submit" type="submit"> 保存 </button>
	</p>
</form>
