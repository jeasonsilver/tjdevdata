<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录信息统计</title>
<style type="text/css">  
        table {  
            border: 1px solid #B1CDE3;  
            padding:0;   
            margin:0 auto;  
            border-collapse: collapse;  
        }  
          
        td {  
            border: 1px solid #B1CDE3;  
            background: #fff;  
            font-size:14px;  
            padding: 3px 3px 3px 8px;  
            color: #4f6b72;  
        }  
        .table_left{
        	width:20%
        }
        
        .table_right{
        	width:80%
        }
        .table_cols{
        	width:150px
        }
        A:link {
		 color: #2136C7;
		 TEXT-DECORATION: none
		}
		A:visited {
		 COLOR: #2136C7;
		 TEXT-DECORATION: none
		}
		A:hover {
		 COLOR: #ff7f24;
		 text-decoration: underline;
		}
		A:active {
		 COLOR: #ff7f24;  
		 text-decoration: underline;
		}
</style>
</head>
<body>
	<table style="margin-top: 60px">
		<tr><th colspan="6">人员登录情况</th></tr>
		<tr>
			<td class="table_cols">代表总数：</td><td class="table_cols">其中已登录数：</td>
			<td class="table_cols">市人大工作人员总数：</td><td class="table_cols">其中已登录数：</td>
			<td class="table_cols">全体工作人员总数：</td><td class="table_cols">其中已登录数：</td>
		</tr>
		<tr>
			<td>${dbcount}</td>
			<td><a href="${ctx}/system/stat/dbStat.do" target="_blank">${dbdlcount}</a></td>
			<td>${srdcount}</td>
			<td><a href="${ctx}/system/stat/srdStat.do" target="_blank">${srddlcount}</a></td>
			<td>${gzrycount}</td>
			<td><a href="${ctx}/system/stat/gzryStat.do" target="_blank">${gzrydlcount}</a></td>
		</tr>
	</table>
</body>
</html>