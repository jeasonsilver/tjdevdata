<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/import-basic-js-css.jsp"%>
<script type="text/javascript">
$(function(){
	freeAlert("您的履职经验已经提交审核！",function(){
		 window.location.href="${ctx}/dblz/lzjycg/addpage.do?free_menuTitleName=发布履职经验";
	})
});
</script>
</head>
<body>

</body>
</html>