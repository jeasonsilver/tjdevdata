<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/import-basic-js-css.jsp"%>
<style>
table {  
    border: 1px solid #B1CDE3;  
    padding:0;   
    margin:0 auto;  
    border-collapse: collapse;  
}  
.table th, .table td {
	padding: 4px;
	text-align: center;
	vertical-align: middle;
	border: 1px solid #B1CDE3; 
	font-size:13px;
	color: #4f6b72;
}
.fBold{
	font-weight:bold;
}
</style>
</head>
	<body>
		<div class="container-fluid">
			<!-- 标题 -->
			<div class="row">
				<div class="span12">
					<h3 style="width:100%;text-align:center;">${year }年X季度栏目统计报表</h3>
				</div>
			</div>
			<!-- 统计报表内容 -->
			<div class="row">
				<div class="span12" style="width:90%;text-align:center;">
					<table class="table" border="1" style="padding:3px;text-align:center;">
						<thead class="fBold">
							<tr>
								<c:forEach items ="${modularVOHeadList }" var="modularVOHead" varStatus="modularVOHeadStatus">
									<td colspan="${fn:length(modularVOHead.modularDetailList) }" class="fBold">${modularVOHead.modularName }</td>
									<c:forEach items ="${modularVOHead.modularDetailList }" var = "modularDetail" varStatus="modularDetailHeadStatus">
									</c:forEach>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach items ="${modularVOHeadList }" var="modularVOHead" varStatus="modularVOHeadStatus">
									<c:forEach items ="${modularVOHead.modularDetailList }" var = "modularDetail" varStatus="modularDetailHeadStatus">
										<td>${modularDetail.pageName }</td>
									</c:forEach>
								</c:forEach>
							</tr>
						
							<!-- <tr>
								<td>一级栏目</td>
								<td>二级栏目</td>
								<td>第一季度</td>
								<td>第二季度</td>
								<td>第三季度</td>
								<td>第四季度</td>
								<td>合	计</td>
							</tr> -->
						</thead>
						<tbody>
							
						</tbody>
					</table>
				</div>
			</div>
			
		</div>
	</body>
</html>