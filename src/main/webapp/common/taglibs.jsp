<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="elf" uri="/elf"%>
<%@ taglib prefix="g" uri="/gdata"%>
<%@ page import="com.free.common.Const" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ligerUIUrl" value="${pageContext.request.contextPath}/ui/ligerUI"/>
<c:set var="artDialogUrl" value="${pageContext.request.contextPath}/ui/artDialog"/>
<c:set var="maxLength" value="25"></c:set>
<% 

	//if (request.getSession().getAttribute("userSession")==null){
	//	response.sendRedirect("login.htm");
	//}
//out.print(request.getServletPath()); %> 