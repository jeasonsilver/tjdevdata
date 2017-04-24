<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/import-basic-js-css.jsp"%>
<script type="text/javascript">
$(function(){
	freeAlert("操作失败！请联系管理员！",function(){
		parent.location.reload();
	})
});
</script>
</head>
<body>

</body>
</html>