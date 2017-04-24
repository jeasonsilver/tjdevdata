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
	<div class="container">
		<!-- <div class="row">
			<div class="span8"></div>
			<div class="span4">
				年份 <select>
						<option value="2012">2012</option>
						<option value="2013">2013</option>
						<option value="2014">2014</option>
						<option value="2015">2015</option>
						<option value="2016">2016</option>
					</select>
			</div>
		</div> -->
		<!-- 标题 -->
		<div class="row">
			<div class="span12">
				<h3 style="width:100%;text-align:center;">${year }年各季度栏目统计报表</h3>
			</div>
		</div>
		<!-- 统计报表内容 -->
		<div class="row">
			<div class="span12">
				<table class="table" border="1" style="padding:3px;text-align:center;">
					<thead class="fBold">
						<tr>
							<td>一级栏目</td>
							<td>二级栏目</td>
							<td><a href="${ctx}/system/modular/modularDept.do?year=${year }&quarter=1" target="_blank">第一季度</a></td>
							<td><a href="${ctx}/system/modular/modularDept.do?year=${year }&quarter=2" target="_blank">第二季度</a></td>
							<td><a href="${ctx}/system/modular/modularDept.do?year=${year }&quarter=3" target="_blank">第三季度</a></td>
							<td><a href="${ctx}/system/modular/modularDept.do?year=${year }&quarter=4" target="_blank">第四季度</a></td>
							<td>合	计</td>
						</tr>
					</thead>
					<tbody>
							<c:forEach items ="${modularVOHeadList }" var="modularVOHead" varStatus="modularVOHeadStatus">
								<c:forEach items ="${modularVOHead.modularDetailList }" var = "modularDetail" varStatus="modularDetailHeadStatus">
									<tr>
										<c:if test="${modularDetailHeadStatus.count == 1}">
											<td rowspan="${fn:length(modularVOHead.modularDetailList) }" class="fBold">${modularVOHead.modularName }</td>
										</c:if>
										<td class="fBold">${modularDetail.pageName }</td>
										<!-- 数据部分 -->
										<c:forEach items ="${modularVODataList }" var = "modularVOData" varStatus="modularVODataStatus">
											<c:forEach items ="${modularVOData.modularDetailList }" var = "modularDetailData" varStatus="modularDetailDataStatus">
												<c:if test="${modularVOHeadStatus.count == modularVODataStatus.count && modularDetailHeadStatus.count == modularDetailDataStatus.count }">
													<c:forEach items="${modularDetailData.modularInfoList }" var = "modularInfoData">
														<td>${modularInfoData.count }</td>
													</c:forEach>
													<td>${modularDetailData.totalCount }</td>	
												</c:if>
												
											</c:forEach>
										</c:forEach>
									</tr>
								</c:forEach>
							</c:forEach>
							
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>

	
