<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>NiceBoat CMS </title>
    <link href="${ligerUIUrl}/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <link href="${ligerUIUrl}/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" /> 
    <link rel="stylesheet" type="text/css" id="mylink"/>       
    <script src="${ctx}/ui/jquery-2.0.3.min.js"></script>    
    <script src="${ligerUIUrl}/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script> 
    <script src="${ligerUIUrl}/lib/json2.js"></script>
    <script src="${ctx}/ui/layer/layer.min.js"></script>
    <script src="${ctx}/ui/jquery.form.js"></script>
    <script src="${ctx}/ui/jquery-validation/jquery.metadata.js"></script>
    <script src="${ctx}/ui/jquery-validation/jquery.validate.min.js"></script>
    <script src="${ctx}/ui/jquery-validation/messages_cn.js"></script>
    <script type="text/javascript">
    $(function () {	
    	
    	//alert($("#phone", window.parent.document).val());
    	//alert(window.parent.document.a);
        //关闭iframe
        $('#closeIframe').click(function(){
        	alert(parent.layer.a);
        	alert(parent.layer.$("#phone").val());
        });   
    });
  //获取当前窗口索引
    var index = parent.layer.getFrameIndex(window.name);
    //让层自适应iframe
    $('#add').on('click', function(){
    	$('body').append('<p>新元素</p>');
    	parent.layer.iframeAuto(index);
    });
    //在父层再弹出一个层
    $('#new').on('click', function(){
    	parent.$.layer({
    	type : 2,
    	title : 'iframe子父操作，该窗口创建于：'+ window.name,
    	fix: false,
    	iframe : {src : 'iframe.html'},
    	shadeClose: true,
    	maxmin: true,
    	zIndex: parent.layer.zIndex,
    	area: ['1000px' , '500px'],
    	offset : [(20 + 40*(window.name.match(/\d+/)[0]|0)) + 'px', '']
    	});
    });
    </script>
    <style type="text/css">
           body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>
</head>
<body>
dialog
<input type="text" id='aa' >
<input type="button" value="closeIframe" id="closeIframe" >
</body>
</html>