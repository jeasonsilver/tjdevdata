<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
<title>先导工程二阶段信息共享平台</title>
<link rel="shortcut icon" href="${ctx}/ui/favicon.png" /> 
<%@ include file="/common/import-basic-js-css.jsp"%>
<link href="${ctx}/ui/dialog.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/main/css/layout.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
function openjt(url){
	 //alert('11');
	 window.open (url, 'newwindow', 'height=700, width=700, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no')
	//showModalDialog(url, 'newwindow', 'dialogWidth:700px;dialogHeight:500px;dialogLeft:200px;dialogTop:150px;center:yes;help:yes;resizable:yes;status:no;scroll:no;');
}
//window.onload=
function init()
{
	
	$.ajax({
		   type: "POST",
		   url: "${ctx}/system/menu/childMenu.do",
		   data: {"pid":"0"},
		   success: function(data){
		   initFirstMenu(data,"mainId");
		  // createChildMenu();
		   }
	  });
	//openjt(url);
};

function createChildMenu(lst)
{
	for(var i = 0; i <lst.length; i++ )
	{
		var id = lst[i]["id"]; 
		var obj = document.getElementById(id);
		getChildMenu(id,obj);
	}
	/* var arr =document.getElementsByName("ulName");
	alert(arr.length);
	 for(var i=0; i<arr.length; i++)
	{
		getChildMenu(arr[i].id,arr[i]);
	} 
	 
	 alert("come!"); */
} 

function getChildMenu(menuId,obj)
{ 
	//alert("menuId="+ menuId);
	$.ajax({
		   type: "POST",
		   url: "${ctx}/system/menu/childMenu.do",
		   data: {"pid":menuId},
		   success: function(data){
		   obj.innerHTML = createUL(data,obj);
		   }
	  });
}


function initFirstMenu(data, divId)
{
	var lst = eval(data);
	//alert("mainMenu-size="+lst.length);
	//alert(lst[0]["id"]);
	//alert(lst[0]["menuname"]);
	var  w = Math.ceil(lst.length/3 )*216;
	var obj = document.getElementById("mainId");
	var h = obj.offsetHeight;
	var  mTop = (h-w-50)/2;
	if (mTop < 10){
		
		obj.style.height=h-50-mTop + 70;
		obj.style.marginTop = 35; 
	} else {
	obj.style.height=h-50-mTop;
	obj.style.marginTop = mTop; 
	}
	var divObj = document.getElementById(divId);
	var str = "<table class='rig_nav' style='width: 1098px; height:" + w + "'px' border='0' align='center'><tr>";
	
	for(var i = 0; i <lst.length; i++ )
	{
		var menuId = lst[i]["id"];
		str += "<td align='center'>" 
		          +"<table class='b_table' id='c" +(i+1)+ "'>"
		          +"<tr><td class='title1' colspan='2' width='150px'>" + lst[i]["menuname"] + "</td></tr>"
		         
		          +"<tr><td style='width:140px'><a class='img" +(i+1)+ "'  href='#'></a></td><td><ul name='ulName' id='"+lst[i]["id"] + "'></ul>"
		         
	   +"</td></tr>"
	          +"</table></td>";
	  // alert("str"+ i +"="+ str);
	    if ((i+1)%3 == 0 && (i+1)!=lst.length){
	    	str +="</tr><tr>";
	    }
	    
	    if((i+1) == lst.length)
	    {   if(lst.length %3 == 0)
	    	{
	    		str += "</tr>";
	    	}
	    else if(3- (lst.length %3) ==1)
	    	{	str +="<td></td></tr>";}
	    	else {str +="<td></td></tr>";}
	    }
	    
	}
	
	//alert("str=" + str);
	divObj.innerHTML = str;	
	createChildMenu(lst);
	
}

function createUL(data, obj)
{
	var lst = eval(data);
	//alert(lst.length);
	
	var strUl ="";
	var num = lst.length;
	var flag = false;
	if(num >4){
		num=4;
		flag = true;
	}
    for(var i=0; i<num; i++){
    	 var url = addParam(lst[i]["url"],lst[i]["menuname"]);
    	 if(url.indexOf("fagai")>=0){
    		 //建通要求弹出窗口 by lw 20170105
    		// strUl += "<li><a href='" + url + "'  target='_blank' >"+ lst[i]['menuname'] + "</a></li>";
    		 strUl += "<li><a  href='javascript:void(0)' onclick='openjt("+"\""+url+"\""+")' >"+ lst[i]['menuname'] + "</a></li>";
    		 //   strUl += "<li><a  href='javascript:openjt("+url+")' >"+ lst[i]['menuname'] + "</a></li>";
    		// alert(strUl);
    	 }else{
    		 strUl += "<li><a href='" + url + "'  target='_blank' >"+ lst[i]['menuname'] + "</a></li>";
    		 
    	 }
    	
    }
    
    if(flag){
    //	strUl += "<li><a href='${ctx}/index.jsp' target='_blank'>更多...</a></li>";
    		
    }
   
    
    return strUl;
	
}

function addParam(url, menuname){
	
	if (url.indexOf("http")>=0){
		   
	}else{
		 if(url.indexOf("&")>=0)
		   {
			   
			  // url= "<%=Const.GBASE_BI_URL%>" +url+ "&free_menuTitleName="+ menuname;
			 url= "<%=Const.GBASE_BI_URL%>" +url;
		   } else if(url.indexOf("/")>=0) {
			   //url="${ctx}" + url+ "?free_menuTitleName="+ menuname;
			   url="${ctx}" + url;
			   	
		   } else if (url=="") {
			   
			   url="javascript:void(0);";
		   }
	}
	return url;
}

function openMenu1(id){
	
	window.location.href='index.jsp';
	
} 
 

</script>

</head>

<body onload="init()">
	<!-- <div class="nav_title">&nbsp;&nbsp;天津开发区管委会数据平台</div> -->
	<div style="height:100%;"><div class="nav_title" style="background-color:#999999">
	<ul class="nav_rig" style="margin:18px auto 0px 10px;color: white;">
	   
	    <li><span class="text_grey" ><font color="white">欢迎：&nbsp;<shiro:principal property="xm" /></font></span>
		   <%--  <a class="ico_01" href="${ctx}/index.jsp" target="iframe1" title="回到主页" ></a>
		    <a href="${ctx}/index.jsp" target="iframe1" style="color: white;"><span style="float: left;padding-top: 5px;">回到主页</span></a>
		   --%>
		    <a class="ico_04" href="${ctx}/system/user/logout.do" title="退出系统" ></a>
		     <a href="${ctx}/system/user/logout.do" target="iframe1" style="color: white;"><span style="float: left;padding-top: 5px">退出系统</span></a>
		    
	    </li>
  	</ul>
  	</div>
  	 <div id="mainId" style="height:100%;margin-top:35px;">

	
   
   <!--  <table class="rig_nav" style="width: 880px; height: 328px" border="0" align="center">

		<tr>
			<td align="center">

				<table class="b_table" id="c1">
					<tr>
						<td class="title1" colspan="2">人口数据</td>
					</tr>
					<tr>
						<td><img class="img1" /></td>
						<td><ul>
								<li><a href="http://www.baidu.com" target="_blank">人口分布分析</a></li>


								<li><a href="#">老龄化分析</a></li>

								<li><a href="#">男女比例分析</a></li>



							</ul></td>
					</tr>
				</table>

			</td>
			
			<td align="center">
				<table class="b_table" style="background-color: 623DBE;">
					<tr>
						<td colspan="2" class="title1">呼叫中心数据</td>
					</tr>
					<tr>
						<td><img class="img2" /></td>
						<td><ul>
								<li><a href="http://www.baidu.com" target="_blank">人口分布分析</a></li>


								<li><a href="#">老龄化分析</a></li>

								<li><a href="#">男女比例分析</a></li>



							</ul></td>
					</tr>
				</table>
			</td>
			<td align="center">



				<table class="b_table" style="background-color: 00A402;">
					<tr>
						<td colspan="2" class="title1">经济数据</td>
					</tr>
					<tr>
						<td><img class="img2" /></td>
						<td><ul>
								<li><a href="http://www.baidu.com" target="_blank">人口分布分析</a></li>


								<li><a href="#">老龄化分析</a></li>

								<li><a href="#">男女比例分析</a></li>
								<li><a href="http://www.baidu.com" target="_blank">人口分布分析</a></li>


								<li><a href="#">老龄化分析</a></li>


							</ul></td>
					</tr>
				</table>
			</td>
		</tr>

	</table> -->
    </div>
	</div>
</body>

</html>
