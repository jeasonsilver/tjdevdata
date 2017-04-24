<%-- <%@ page language="java" pageEncoding="UTF-8"%>
<%  //String s = request.getParameter("free_menuTitleName"); 
//String s=new String((request.getParameter("free_menuTitleName")).getBytes("iso-8859-1"),"utf-8");
String s=request.getParameter("free_menuTitleName");

%>
<%@ include file="/common/taglibs.jsp" %>  

<table background="${ctx}/ui/images/cen/rig/header_bg.jpg" width="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="left" valign="middle" height="35px" background="${ctx}/ui/images/cen/rig/header_bg_lef.jpg" style="background-repeat:no-repeat;background-position:left" >&nbsp;&nbsp;<img src="${ctx}/ui/images/cen/rig/ico_01.png" width="16" height="16" align="absmiddle" />&nbsp;&nbsp;
    <span class="text_white16"><%=s%></span></td>
    <td width="8" align="right" valign="top" background="${ctx}/ui/images/cen/rig/header_bg.jpg" ;><img src="${ctx}/ui/images/cen/rig/header_bg_rig.jpg" width="8" height="35" /></td>
  </tr>
</table> --%>