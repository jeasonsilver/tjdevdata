<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="com.free.common.Const" %>
<%@ include file="/common/taglibs.jsp"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="shortcut icon" href="${ctx}/ui/favicon.png" /> 
<link href="${ctx}/ui/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/ui/css/layout.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/ui/dialog.css" rel="stylesheet" type="text/css" />

<script src="${ctx}/ui/free.core.js"></script>
<script src="${ctx}/ui/jquery-1.9.0.min.js"></script>
<script src="${ctx}/ui/free.grid.js"></script>
<script src="${ctx}/ui/free.dialog.js"></script>
<script src="${ctx}/ui/free.form.js"></script>

<script src="${ctx}/ui/jquery-validation/jquery.validate.min.js"></script>
<script src="${ctx}/ui/jquery-validation/jquery.metadata.js"></script>
<script src="${ctx}/ui/jquery-validation/messages_cn.js"></script>

<link href="${ctx}/ui/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<script src="${ctx}/ui/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<!-- zTree插件 -->
<link href="${ctx}/ui/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/ui/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/ui/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx}/ui/ztree/js/jquery.ztree.exedit-3.5.js"></script>

<script >


$(function(){
	var contentHeight = getHeight() -61-29-28-31-10; //($(window).height()-61-29-28-31-5-5);
	//var contentWidth =($(window).width()-$('.lef').width());
	$('.lef_but').css("height",contentHeight);
	$.ajax({
		   type: "POST",
		   url: "${ctx}/system/menu/ajax_all.do",
		   data: {},
		   success: function(data){
			   var s="";
			   $.each(data,function(i,o){//生成横向导航
				   if (o.pid==0){
					   s+='<a class="nav_but" href="javascript:void(0);" menuid="'+o.id+'" >'+o.menuname+'</a>';
				   }
			   });
				$(".nav").prepend(s);
				$(".nav_but").click(function(){//横向导航点击
					$this = $(this);
					$(".lef_header").text($this.text());
					createMenu(data,$this.attr('menuid'));
					$(".nav_but").removeClass("nav_but_clicked");
					$this.addClass("nav_but_clicked");
					//document.getElementById("titleSpan").innerHTML=$(this).text();
						
					
				});

				$(".nav_but").each(function(j,dom){//默认显示我的平台
					$this = $(this);
				   if ($this.attr("menuid")==115){
						$(".lef_header").text($this.text());
						createMenu(data,$this.attr('menuid'));
				   	}
				});
		   }
	  });

	   $(".rig").css("height",contentHeight+31+5+5-5);
	   //$(".rig").css("width","1200px");
	   
	
});

function addParam(url,o){
	if (url.indexOf("http")>=0){
		   
	}else{
		
		   if(url.indexOf("&")>=0)
		   {
			   
			   url= "<%=Const.GBASE_BI_URL%>" +url + "&free_menuTitleName="+o.menuname;	
		   } else {
			   url="${ctx}" + url + "?free_menuTitleName="+encodeURI(o.menuname);
			   	
		   }
		   
			
	}
	return url;
}
function createMenu(data,id){//生成左侧菜单
   $(".lef_but dl dd").remove();
   var str="";
   $.each(data,function(i,o){//生成一级菜单
	   if (id==o.pid){
		   var url1="";
		   if(o.url){
			   url1=addParam(o.url,o)+'"';
		   }
		   str+='<dd id="'+o.id+'" ><a class="lef_but_01" id="'+o.id+'" href="javascript:void(0);" url="'+url1+'" >'+o.menuname+'</a></dd>';
		  /*  $.each(data,function(j,io){//生成二级菜单
			   if (io.pid==o.id){
				   if(io.menuname != "全国人大常委会专题讲座")
				   		str+='<dd pid="'+io.pid+'" style="display:none" ><a class="lef_but_02" href="javascript:void(0);" url="'+addParam(io.url,io)+'" >'+io.menuname+'</a></dd>';
				   	else
				   		str+='<dd pid="'+io.pid+'" style="display:none" ><a class="lef_but_03" href="javascript:void(0);" url="'+addParam(io.url,io)+'" >'+io.menuname+'</a></dd>';	
				   	
			   	}
			}); */
	   }
   });
   $(".lef_but dl").append(str);
   $(".lef_but dl dd a").click(function(){//左一级菜单点击
	  // $(".rig").load($(this).attr("url"));
		$this = $(this);
		 if ($this.attr("class")!="lef_but_01"){
			$(".lef_but dl dd a").removeClass("lef_but_02_click");
			$this.addClass("lef_but_02_click");
		} 
	  if ($(this).attr("url")){
		   $("#iframe1").attr("src",$(this).attr("url"));
		  // alert($(this).attr("url"));
		
		//   alert("come 0");    
	  }
	 // alert("come 1");
   });
   $(".lef_but dl dd").click(function(){
	   if (!$(this).attr("pid")){
		   $("dd[pid]").hide();
	   }
	   var id =$(this).attr("id");
	   $("dd[pid]").each(function(j,dom){
		   if ($(dom).attr("pid")==id){
			   $(this).show(400);
			  
		   	}
		});
	   
		$("#iframe1").attr("src",$(this).attr("url"));
		
		document.getElementById("titleSpan").innerHTML= $(".lef_header").text()+ "  >  " + $(this).text(); 
		
		
		 
		
		
		
	});
   //点击横向导航菜单后自动打开左侧第一个一级菜单,第一个二级菜单
    // if ($(".lef_but dl dd:first a").attr("url")==""){
    	//alert("come 3");
    	//document.getElementById("titleSpan").innerHTML= $(".lef_header").text();
	 //如果第一个一级菜单有url就直接打开，没 有就打开他的下级子菜单
	//   $(".lef_but dl dd:first").click();
	  //alert("come 31");
	  
	  // $(".lef_but dl dd:eq(1) a").click();
	  // alert("come 32");
	  
  // }else{ 
	   $(".lef_but dl dd:first a").click();
	 //  alert("come 4");
  // } 
 // alert("dddd="+ $(".lef_header").text()+ "  >  " + $(this).text()); 
  // initLefHeight();
}

//取页面内高度
function getHeight() {
	if(navigator.userAgent.indexOf('MSIE') >= 0){
		return document.compatMode == "CSS1Compat"?document.documentElement.clientHeight:document.body.clientHeight;
	} else {
		return self.innerHeight;
	}
}

function initLefHeight(){
	var obj = document.getElementById("lefMenu");
	var h = obj.offsetHeight;
	obj.style.height=h-10;
}

function changePwd(){
	new Dialog({type:'url',value:'${ctx}/system/user/inputChangePwd.do'},
				{title:"密码修改",modal:true}).show();
}
function changeLoginName(){
	new Dialog({type:'url',value:'${ctx}/system/user/inputLoginName.do'},
				{title:"用户名修改",modal:true}).show();
}
//从7块导航中打开菜单
 function openMenu(id){
	 iframe1.src="${ctx}/index.jsp";
	$(".nav_but").each(function(dom){
		$this = $(this);
	   if ($this.attr("menuid")==id){
		   $this.click();
	   }
	});
} 
</script>
</head>

 <body style="background-color: #fff;">
<div class="header">
 <div class="logo">
	<ul class="nav_rig" style="margin:30px auto 0px 10px;color: white;">
	   
	    <li>
	    	<span class="text_grey" ><font color="white"><shiro:principal property="xm" />&nbsp;你好！</font></span>
		   <%--  <a class="ico_01" href="${ctx}/main/pag_but.jsp" target="iframe1" title="回到主页" ></a>
		    <a href="${ctx}/main/pag_but.jsp" target="iframe1" style="color: white;"><div style="float: left;padding-top: 6px;">回到主页</div></a> --%>
		    <!-- <a class="ico_02" href="#"></a> -->
		    <a class="ico_03" href="javascript:void(0);" title="修改密码" onClick="changePwd()" ></a>
		     <a href="javascript:void(0);" target="iframe1" style="color: white;" onClick="changePwd()" ><div style="float: left;padding-top: 6px">修改密码</div></a>
		    <a class="ico_03" href="javascript:void(0);" title="修改用户名" onClick="changeLoginName()" ></a>
		   <a href="javascript:void(0);" target="iframe1" style="color: white;" onClick="changeLoginName()"> <div style="float: left;padding-top: 6px">修改用户名</div></a>
		    <a class="ico_04" href="${ctx}/system/user/logout.do" title="退出系统" ></a>
		     <a href="${ctx}/system/user/logout.do" target="iframe1" style="color: white;"><div style="float: left;padding-top: 6px">退出系统</div></a>
		    
	    </li>
  	</ul>
  	</div>
</div>
<div class="nav"> 
  
</div>
<div class="cen" >
  <div class="lef" id="lefMenu">
    <ul>
      <li class="lef_header"></li>
      <li class="lef_but">
        <dl> 
         
        </dl>
      </li>
    </ul>
  </div>
  <div class="rig">
  <!-- <input type="button" style="height:20px;width:20px" style="margin-left:0;overflow:visible;"  onclick="accordion(this)" class="btnimg1"/> -->
    <a href='javascript:void(0);' class='zPushBtn'  tabindex='-1'onselectstart='return false' onClick="accordion(this)">
    	<img src="${ctx}/ui/images/cen/rig_but/btnlr.png" width="20"height="20" /></a>
    <img src="${ctx}/ui/images/cen/rig/ico_01.png" width="16" height="16" align="absmiddle" /><span id="titleSpan"></span>
  	<iframe id="iframe1" name="iframe1" src="" frameborder="0" style="width:100%;height:100%;OVERFLOW-Y: auto; OVERFLOW-X:auto;"></iframe>
 
  </div>
</div>
<div class="footer">版权所有：公安部第一研究所</div>
 <script>
 $(window).resize(function() {
		var contentHeight = getHeight() -61-29-28-31-10; 
		$('.lef_but').css("height",contentHeight);
	});
  /*  window.onload=function() 
{
	   alert("加载完成！");
	 var h=document.getElementById('lefMenu').clientHeight;
		if(h>480){
			document.getElementById('scr').style.overflowY="scroll";
		} 
}  */
function ShowWait(){
	    divWait.style.display = "block";
}

function HideWait()
{
	  divWait.style.display = "none";
	     	
}
	
function accordion(obj)
{
	  switch (obj.className) {//根据样式更改背景
      case "btnimg1":
          obj.className = "btnimg2";
          break;
      case "btnimg2":
          obj.className = "btnimg1";
          break;
  }
	if ( $(".lef").width() >0){
		$(".lef").css("width","0px");
		$(".rig").css("margin-left","0px");
	} else {
		$(".lef").css("width","240px");
		$(".rig").css("margin-left","240px");
	}		
}


</script>
</body>

</html>
