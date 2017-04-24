<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>天津市人大代表履职服务平台</title>
<link href="${ctx}/ui/css/detailPage_yuan.css" rel="stylesheet" type="text/css" />
</head>
<body>
		<table align="center" class="tab_pag" style="width: 970px">
				<tr class="tab_white02"><td width="12%">标题：</td><td width="88%">${ob.bt}</td></tr>
				<tr class="tab_grey02"><td >发布人姓名：</td><td>${ob.fbrxm}</td></tr>
				<tr class="tab_white02"><td >发布单位：</td><td>${ob.fbdw}</td></tr>
				<tr class="tab_grey02"><td >信息来源：</td><td>${ob.xxly}</td></tr>
				<tr class="tab_white02"><td >发布日期：</td><td>${ob.fbrq}</td></tr>
				<tr class="tab_grey02"><td >附件：</td><td><a href="${ctx}/dblz/cwhsy/download.do?id=${ob.id}">${ob.filename}</a></td></tr>
				<tr class="tab_grey03"><td class="tab_grey03" colspan="2">${ob.content.nr}</td></tr>
		</table>

</body>