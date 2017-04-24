<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<html>
<head>
<title>天津市人大代表履职服务平台</title>
<link href="${ctx}/ui/css/detailPage_yuan.css" rel="stylesheet" type="text/css" />
</head>
<body>

<form class="span12"  method="post" id="inputForm" action="${ctx}/dblz/lzjycg/save-sh.do" >
 <div class="row">
	<input type="hidden" name="id" value="${ob.id}" />
	<input type="hidden" name="lzid" value="${ob.lzid}" />
	<input type="hidden" name="rdname" value="${ob.rdname}" />
	<input type="hidden" name="xxzt" value="${ob.xxzt}" />
	<input type="hidden" name="shzt" value = "1"/> 

		<table align="center" class="tab_pag" style="width: 970px">
				<tr class="tab_white02"><td width="12%">标题：</td><td width="88%">${ob.lzbt}</td></tr>
				<tr class="tab_grey02"><td >来源：</td><td>${ob.ly}</td></tr>
				<tr class="tab_white02"><td >发布人：</td><td>${ob.fbrxm}</td></tr>
				<tr class="tab_grey02"><td >发布单位：</td><td>${ob.hdzzdw}</td></tr>
				<tr class="tab_white02"><td >附件：</td><td><a href="${ctx}/dblz/lzjycg/download.do?id=${ob.id}">${ob.filename}</a></td></tr>
				<tr class="tab_grey02"><td >审核：</td><td><button class="btn btn-success" id="free_submit" type="submit"><i class="icon-ok icon-white" ></i> 通过 </button></td></tr>
				
				<tr class="tab_grey03"><td class="tab_grey03" colspan="2">${ob.content.lznr}</td></tr>
		</table>
		

</form>
</body>
</html>
