<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>  
<link href="${ctx}/ui/css/layout.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/ui/css/bootstrap.min.css" rel="stylesheet" type="text/css" />

<link href="${ctx}/ui/dialog.css" rel="stylesheet" type="text/css" />

<link href="${ctx}/ui/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/ui/ligerUI/skins/Gray2014/css/all.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/ui/jquery-1.9.0.min.js"></script>
<script src="${ctx}/ui/ligerUI/js/ligerui.min.js"></script>

<script src="${ctx}/ui/free.core.js"></script>
<script src="${ctx}/ui/free.dialog.js"></script>
<script src="${ctx}/ui/free.form.js"></script>
<script src="${ctx}/ui/free.map.js"></script>

<script src="${ctx}/ui/jquery-validation/jquery.validate.min.js"></script>
<script src="${ctx}/ui/jquery-validation/jquery.metadata.js"></script>
<script src="${ctx}/ui/jquery-validation/messages_cn.js"></script>

<link href="${ctx}/ui/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<script src="${ctx}/ui/uploadify/scripts/jquery.uploadify.js?r=<%=java.util.UUID.randomUUID()%>" type="text/javascript"></script>

<!-- zTree插件 -->
<link href="${ctx}/ui/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/ui/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/ui/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx}/ui/ztree/js/jquery.ztree.exedit-3.5.js"></script>

<!-- 日期控件 -->
<link href="${ctx}/ui/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/ui/css/datepicker.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/ui/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/ui/bootstrap-datetimepicker.zh-CN.js"></script>

<script type="text/javascript" src="${ctx}/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${ctx}/ckfinder/ckfinder.js"></script>
<script type="text/javascript">
function sliceLongName(rowData,index,value) {
	if (value.length > "${maxLength}")
		value = value.slice(0, parseInt("${maxLength}"))+"...";
	return value;
}
function sliceLongName2(value) {
	if (value.length > parseInt("${maxLength}"))
		value = value.slice(0, parseInt("${maxLength}"))+"...";
	return value;
}
</script>