<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<script type="text/javascript" src="parser.js"></script>
		<script type="text/javascript">
		
				window.onload=function(){
				
				var sHref =window.location.search;
				var args = sHref.split("?");
				var vals=args[1];
				var url2="";
				url2=vals.replace("html","swf?");
				url2 = url2+args[2];
				url2=url2.substring(0,url2.lastIndexOf("&"));
				//var params=vals.split["&"];
				//var url =params[0];
				//var reportname=params[1];
				document.title=decodeURI(args[2].substring(args[2].lastIndexOf("=")+1,args[2].length));
				
				var object = document.createElement("OBJECT");
					object.setAttribute("classid","clsid:D27CDB6E-AE6D-11cf-96B8-444553540000");
					object.setAttribute("id","myobject");
					object.setAttribute("width","1209px");
					object.setAttribute("height","730px");
					object.setAttribute("name","myobject");
					object.setAttribute("codebase","http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab");
					object.setAttribute("id","myobject");
				var param1 = document.createElement("PARAM");
					param1.setAttribute("name","movie");
					param1.setAttribute("value",url2);
					object.appendChild(param1);
				var param2 = document.createElement("PARAM");
					param2.setAttribute("name","quality");
					param2.setAttribute("value","high");
					object.appendChild(param2);
				var param3 = document.createElement("PARAM");
					param3.setAttribute("id","gShowBgcolor");
					param3.setAttribute("name","bgcolor");
					param3.setAttribute("value","091a1a");
					object.appendChild(param3);
				var param4 = document.createElement("PARAM");
					param4.setAttribute("name","allowScriptAccess");
					param4.setAttribute("value","always");
					object.appendChild(param4);
				var param5 = document.createElement("PARAM");
					param5.setAttribute("name","wmode");
					param5.setAttribute("value","transparent");
					object.appendChild(param5);
					
					
				var embed = document.createElement("EMBED");
					embed.setAttribute("src", url2);
					embed.setAttribute("name", "myobject1");
					embed.setAttribute("width", "1024px");
					embed.setAttribute("height", "768px");
					embed.setAttribute("id", "myobject1");
					embed.setAttribute("align", "middle");
					embed.setAttribute("play", "true");
					embed.setAttribute("loop", "false");
					embed.setAttribute("quality", "high");
					embed.setAttribute("play", "true");
					embed.setAttribute("allowScriptAccess", "always");
					embed.setAttribute("wmode", "transparent");
					embed.setAttribute("type", "application/x-shockwave-flash");
					embed.setAttribute("pluginspage", "http://www.adobe.com/go/getflashplayer");
					object.appendChild(embed);
				var div=document.getElementById("flush");
					div.appendChild(object);
					
		
					}
		   function getOs(){  
					var OsObject = "";  
				   if(navigator.userAgent.indexOf("MSIE")>0) {  
						return "MSIE";  
				   }  
				   if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){  
						return "Firefox";  
				   }  
				   if(isSafari=navigator.userAgent.indexOf("Safari")>0) {  
						return "Safari";  
				   }   
				   if(isCamino=navigator.userAgent.indexOf("Camino")>0){  
						return "Camino";  
				   }  
				   if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){  
						return "Gecko";  
				   }  
			}  
		   
        </script>
	</head>
	<body>
		<div align="center">
			<table width="663" border="0">
				<tr>
					<td height="600">
						<div id="flush" align="center">


						</div>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>