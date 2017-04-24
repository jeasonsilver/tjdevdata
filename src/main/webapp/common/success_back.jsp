<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/import-basic-js-css.jsp"%>
<script type="text/javascript">
$(function(){
	freeAlert("保存成功",function(){
		 //window.location.href="${ctx}/yajy/proposal/list.do?ex_form="+'${form_values}';
		 window.close();
	});
});
</script>
</head>
<body>

</body>
</html>