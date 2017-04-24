<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>工作人员登录情况</title>
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
	<table style="margin: 60px 0px 0px 400px;float: left;">
		<tr><th colspan="2">已登录工作人员</th></tr>
		<tr><td class="table_cols">部门：</td><td class="table_cols">姓名：</td></tr>
		<c:forEach items="${gzry_ydl}" var="element">
			<tr><td class="table_cols">${element.deptname}</td><td class="table_cols">${element.xm}</td></tr>
		</c:forEach>
	</table>
	<table style="margin: 60px 0px 0px 60px;float: left;">
		<tr><th colspan="2">未登录工作人员</th></tr>
		<tr><td class="table_cols">部门：</td><td class="table_cols">姓名：</td></tr>
		<c:forEach items="${gzry_wdl}" var="element">
			<tr><td class="table_cols">${element.deptname}</td><td class="table_cols">${element.xm}</td></tr>
		</c:forEach>
	</table>
</body>
</html>