<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/import-basic-js-css.jsp"%>
<link href="${ctx}/ui/select2/select2.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/ui/select2/select2.js" type="text/javascript"></script>
<script>

var setting = {
		view: {
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: false
		},
		edit:{
			enable: true,
			showRemoveBtn: true,
			showRenameBtn: false
		},
		check: {
			enable: false//是否显示checkbox
		},
		async: {
			enable: true,
			url:"${ctx}/system/group/ajax_tree.do?check=true"
		},
		data: {
			key:{
				name:"name"
			},
			simpleData: {
				enable: true,//true时下面的设置生效
				idKey: "id",//id
				pIdKey: "pid",//pid
			}
		},
		callback: {
			onAsyncSuccess: zTreeOnAsyncSuccess, //异步加载成功
			beforeRemove: beforeRemove//删除前
		}
	};
//异步加载成功后    展开所有节点
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
	treeObj.expandAll(true);
};
var newDialog;
// 焦点	
function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
		+ "' title='增加人员' onfocus='this.blur();'></span><span class='button add' id='addGroupBtn_" + treeNode.tId
		+ "' title='增加组' onfocus='this.blur();'></span>";
	var editStr = "<span class='button edit' id='editBtn_" + treeNode.tId
		+ "' title='edit node' onfocus='this.blur();'></span>";
	sObj.after(addStr+editStr);
	var btn = $("#addBtn_"+treeNode.tId);
	if (btn) btn.bind("click", function(){
		$("#inputPage").load('${ctx}/system/group/input.do?pid='+treeNode.id+'&type='+treeNode.type);
	});
	//增加组按钮
	var addGroupBtn = $("#addGroupBtn_"+treeNode.tId);
	if (addGroupBtn) addGroupBtn.bind("click", function(){
		$("#inputPage").load('${ctx}/system/group/inputGroup.do?pid='+treeNode.id+'&type='+treeNode.type);
	});
	var editBtn = $("#editBtn_"+treeNode.tId);
	if (editBtn) editBtn.bind("click", function(){
		$("#inputPage").load('${ctx}/system/group/input.do?id='+treeNode.id);
	});
};
//移开焦点	
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.tId).unbind().remove();
	$("#editBtn_"+treeNode.tId).unbind().remove();
	$("#addGroupBtn_"+treeNode.tId).unbind().remove();
};
function beforeRemove(treeId, treeNode) {
	if (confirm("确认删除-- " + treeNode.name + " 吗？")){
		Free.ajax({
			   type: "POST",
			   url: "${ctx}/system/group/delete.do",
			   data: {id:treeNode.id},
			   async:false,
			   success: function(data){
				   if(data=="ok"){
					   alert("删除成功");
				   }
			   }
		   })
	}else{
		return false;
	}
}
var treeObj = null;
$(function ()
{	
	treeObj = $.fn.zTree.init($("#menuTree"), setting);

	$("#backGrid").click(function(){
		   $(".rig").load("${ctx}/jsp/system/list-group.jsp");			
	});
});
</script>
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
ul.ztree {
    background: none repeat scroll 0 0 #f0f6e4;
    border: 1px solid #ccc;
    height: 360px;
    margin-top: 10px;
    overflow-x: auto;
    overflow-y: scroll;
    width: 490px;
}
</style>
</head>
<body>
    <ul id="menuTree" class="ztree" style="float: left;overflow-x:hidden ;overflow-y:scroll;height: 350px;width:400px;"></ul>
<div id="inputPage" style="float: left; width: 340px;margin:20px">
</div>
</body>	
</html>

