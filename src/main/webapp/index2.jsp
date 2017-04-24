<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<body>
<h2>Hello World!</h2>

<shiro:hasPermission name="user.do?myjsp1">  
user.do?myjsp1
</shiro:hasPermission> 
<shiro:hasPermission name="user.do?myjsp">  
user.do?myjsp
</shiro:hasPermission>


<a href="${ctx}/user/logout.do">退出登录</a>
</body>
</html>
