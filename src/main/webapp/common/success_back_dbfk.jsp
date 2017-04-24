<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/import-basic-js-css.jsp"%>
<script type="text/javascript">
var jyFlg = '${jyFlg}';
$(function(){
	freeAlert("保存成功",function(){
		if(jyFlg == 'wdjy'){
			window.location.href="${ctx}/yajy/suggestion/my_list.do?free_menuTitleName=我的建议";
		}else{
			window.location.href="${ctx}/yajy/suggestion/list_dbfk.do";
		}
	});
});
</script>
</head>
<body>

</body>
</html>