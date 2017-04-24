<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script>

$("#inputForm").form({callback:function(){
	g.reload();//重新加载grid
	newDialog.close();
}});

$(function(){
	
});
</script>

<form id="inputForm"  action="${ctx}/system/user/saveRole.do" >
<input type="hidden" name="id" value="${ob.id}" />
<div class="content">

	<c:forEach items="${roles}" var="role">
	<input type="checkbox" name="role" value="${role.id}" 
		<c:forEach items="${ob.userRoles}" var="ur">
		<c:if test="${ur.roleid==role.id}">checked="checked"</c:if>
		</c:forEach>
	 >${role.rolename}<br/>
	</c:forEach>
</div>

<a class="red_but freeGrid_submit" href="javascript:void(0);">保存</a>
</form>
