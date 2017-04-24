<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<%@ include file="/common/import-basic-js-css.jsp"%>
<link href="${ctx}/ui/dialog.css" rel="stylesheet" type="text/css" />
<link href="css/layout.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript">
        function showjy() {
            var dg = new top.Dialog();
            dg.URL = "pop2/postdef.aspx?Page=admin";
            dg.Width = "326px";
            dg.Height = "100PX";
            dg.ShowButtonRow = false;
            dg.ShowMessageRow = false;
            dg.ShowCloseButton = false;
            dg.OKEvent = null; //点击确定后调用的方法
            dg.CancelEvent = function () {
            };
            dg.show();
        }
        function showadminjy() {
            var dg = new top.Dialog();
            dg.URL = "pop2/postadmin.aspx?Page=admin";
            dg.Width = "326px";
            dg.Height = "100PX";
            dg.ShowButtonRow = false;
            dg.ShowMessageRow = false;
            dg.ShowCloseButton = false;
            dg.OKEvent = null; //点击确定后调用的方法
            dg.CancelEvent = function () {
            };
            dg.show();
        }
        //function OpenFrame_Radio(id, cata_id) {
        //    art.dialog.open('', { height: '80%', width: '76%', title: "通知公告", lock: true }, false); //打开子窗体
        //}
        //function OpenFrame(id) {
        //    art.dialog.open('', { height: '80%', width: '76%', title: "人大机关党的群众路线教育实践活动专栏", lock: true }, false); //打开子窗体
        //}
        function nyzy(mvar) {
            if (mvar == 1) {
                document.getElementById("left").className = "side2";
                document.getElementById("ss").className = "left_ss3";
                document.getElementById("mn").className = "main3";
            }
            else {
                document.getElementById("left").className = "side";
                document.getElementById("ss").className = "left_ss";
                document.getElementById("mn").className = "main";
            }
        }
        function openMenu(id){
        	parent.openMenu(id);
        }
        $(function(){
        });
    </script>
    <style type="text/css">
dl, dt, dd {
	padding: 0px;
	margin: 0px;
	list-style-type: none;
}
a {
	color: #333333;
	text-decoration: none;
}
#boxad11 {
	PADDING: 1px 1px 0px 1px;
	FONT-SIZE: 12px;
	Z-INDEX: 300;
	FLOAT: right;
	OVERFLOW: hidden;
	WIDTH: 232px;
	HEIGHT: auto;
	RIGHT: 0px;
	BOTTOM: 23px;
	POSITION: fixed;
	margin: 0px;
	_POSITION: absolute; /*兼容IE6*/
}
#boxad12 {
	background: url(css/images/newinf_bot.png);
	Z-INDEX: 300;
	padding: 0px;
	margin: 0px;
	FLOAT: right;
	OVERFLOW: hidden;
	WIDTH: 232px;
	HEIGHT: 18px;
	RIGHT: 0px;
	BOTTOM: 23px;
	POSITION: fixed;
	_POSITION: absolute; /*兼容IE6*/
 _TOP:expression(offsetParent.scrollTop+document.documentElement.clientHeight-this.offsetHeight); /*兼容IE6*/
}
/*tit样式*/
#boxad11 dt, #boxad11 dt #AD_tit {
	height: 42px;
	line-height: 28px;
}
#boxad11 dt {
	width: 232px;
	background-image: url(css/images/newinf_top.png);
	background-repeat: repeat-x;
	background-position: right top;
}
#boxad11 dt #AD_tit {
	display: block;
	overflow: hidden;
}
#boxad11 dd {
	width: 212px;
	height: auto;
	display: block;
	margin: 0 auto;
	overflow: hidden;
	padding: 5px 10px 5px 10px;
}
#AD_tit {
	width: 70%;
	float: left;
	color: #203D5F;
	font-size: 10pt;
	text-indent: 0.8em;
	font-weight: bold;
}
#closead11, #zoomad11 {
	width: 20px;
	height: 20px;
	line-height: 28px;
	display: block;
	float: right;
	margin-right: 8px;
}
#closead11 {
	width: 20px;
	float: right;
	margin-top: 17px
}
#zoomad11 {
	float: right;
	margin-top: 17px
}
#closead11 img {
	margin-top: 0px
}
#zoomad11 img {
	margin-top: 0px
}
.newsb {
	background: url(css/images/newinf_bot.png);
	height: 18px;
	width: 232px;
}
.lzsl {
	height: 40px;
	width: 200px;
	margin: 5px auto;
	background: url(css/images/dblzsl.jpg);
	display: block;
}
.hdzl {
	height: 40px;
	width: 200px;
	margin: 5px auto;
	background: url(css/images/czbsy.jpg);
	display: block;
}
.pttjjb {
	height: 40px;
	width: 200px;
	margin: 5px auto;
	background: url(css/images/pttjjb.jpg);
	display: block;
}
.wsdc {
	height: 40px;
	width: 200px;
	margin: 5px auto;
	background: url(css/images/wsdc.jpg);
	display: block;
}
.ybw {
	height: 40px;
	width: 200px;
	margin: 5px auto;
	background: url(css/images/ybw.jpg);
	display: block;
}
.rdzt {
	height: 40px;
	width: 200px;
	margin: 5px auto;
	background: url(css/images/dfjs.jpg);
	display: block;
}
.newstable td {
	width: 200px;
	height: 22px;
	border-bottom: 1px dashed #666;
}
.newsb {
	background: url(css/images/newinf_bot.png);
	height: 18px;
	width: 232px;
	display: block;
}

.uboxstyle {
	width: 190px;
	height: 23px;
	float: right;
	z-index: 1000;
}
#uboxstyle {
	width: 190px;
	height: 23px;
	float: left;
	display: block;
	z-index: 1000;
}
#uboxstyle select 
{
    display: none;
}
#uboxstyle .select_box {
	width: 190px;
	height: 23px;
	float: left;
	background-image: url(css/images/select_bg.jpg);
	background-repeat: no-repeat;
	background-position: 0px 0px;

}
#uboxstyle div.tag_select {
	display: block;
	color: #333333;
	width: 180px;
	height: 23px;
	padding: 0;
	line-height: 23px;
	background-color: transparent;
	background-repeat: no-repeat;
	background-position: 0px 0px;
	text-indent: 28px;
	overflow: hidden;
}
#uboxstyle div.tag_select_hover {
	display: block;
	color: #333333;
	width: 180px;
	height: 23px;
	padding: 0;
	line-height: 23px;
	background-color: transparent;
	background-repeat: no-repeat;
	background-position: 0px 0px;
	text-indent: 28px;
	overflow: hidden;

}
#uboxstyle div.tag_select_open {
	display: block;
	color: #333333;
	width: 180px;
	height: 23px;
	padding: 0;
	line-height: 23px;
	background-color: transparent;
	background-repeat: no-repeat;
	background-position: 0px 0px;
	text-indent: 28px;
	overflow: hidden;

}
#uboxstyle ul.tag_options {
	padding: 0;
	margin: 0;
	list-style: none;
	width: 200px;
	padding: 0;
	margin: 0;
	border: 1px solid #666;
	z-index: 10;
	position: relative;
	left: 3px;
	overflow: hidden;
}
#uboxstyle ul.tag_options li {
	display: block;
	width: 200px;
	padding: 0;
	height: 23px;
	text-decoration: none;
	text-indent:8px;
	line-height: 23px;
	color: #000000;
	text-align: left;
	background-color: #FFF;
	z-index: 100;
	overflow:hidden;
}
#uboxstyle ul.tag_options li.open_hover {
	color: #FFF;
	background-color: #E08568;
	font-weight: bold;
	text-indent:8px;
}
#uboxstyle ul.tag_options li.open_selected {
	color: #000
	text-indent:8px;
}
</style>
</head>

<body>
<table class="rig_nav" width="950px" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="220" colspan="5" align="center" valign="bottom"><table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center" valign="bottom"><a class="rig_nav_05" onclick="openMenu(133);" href="javascript:void(0);'">重要文库</a></td>
    <td align="center" valign="bottom"><a class="rig_nav_02" onclick="openMenu(2);" href="javascript:void(0);'">会议文件</a></td>
    <td align="center" valign="bottom"><a class="rig_nav_09" onclick="openMenu(5);" href="javascript:void(0);">学习资料</a></td>
    <td align="center" valign="bottom"><a class="rig_nav_01" onclick="openMenu(6);" href="javascript:void(0);'">议案建议</a></td>
  </tr>
</table></td>
      </tr>
      <tr>
    <td height="200" align="center" valign="bottom"><a class="rig_nav_03" onclick="openMenu(3);" href="javascript:void(0);'">履职交流</a></td>
    <td align="center" valign="bottom"><a class="rig_nav_04" onclick="openMenu(4);" href="javascript:void(0);'">信息动态</a></td>
    <td align="center" valign="bottom"><a class="rig_nav_06" onclick="openMenu(1);" href="javascript:void(0);'">意见征集</a></td>
    <td align="center" valign="bottom"><a class="rig_nav_07" onclick="openMenu(43);" href="javascript:void(0);'">我的平台</a></td>
    <td align="center" valign="bottom"><a class="rig_nav_08" onclick="openMenu(115);" href="${ctx}/jsp/myplatform/anno-list.jsp?free_menuTitleName=我的公告">通知公告</a></td>
    </tr>
    </table>
     
</body>
</html>
