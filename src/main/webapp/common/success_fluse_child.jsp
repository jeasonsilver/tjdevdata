<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/import-basic-js-css.jsp"%>
<script type="text/javascript">
$(function(){
	freeAlert("保存成功",function(){
		top.frames["iframe1"].reload();
		top.Free.closeThisDialog();
		
	});
});
</script>
</head>
<body>

</body>
</html>