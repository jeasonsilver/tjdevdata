<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/import-basic-js-css.jsp"%>
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
			url:"${ctx}/system/menu/ajax_tree.do"
		},
		data: {
			key:{
				name:"menuname"
			},
			simpleData: {
				enable: true,//true时下面的设置生效
				idKey: "id",//id
				pIdKey: "pid",//pid
			}
		},
		callback: {
			onAsyncSuccess: zTreeOnAsyncSuccess,//异步加载成功
			beforeRemove: beforeRemove//删除前
		}
	};
//异步加载成功后    展开所有节点
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
	zTree.expandAll(true);
};
var newDialog;
// 焦点	
function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
		+ "' title='add node' onfocus='this.blur();'></span>";
	var editStr = "<span class='button edit' id='editBtn_" + treeNode.tId
		+ "' title='edit node' onfocus='this.blur();'></span>";
	sObj.after(addStr+editStr);
	var btn = $("#addBtn_"+treeNode.tId);
	if (btn) btn.bind("click", function(){//新增时
		$("#inputPage").load('${ctx}/jsp/system/input-menu.jsp?pid='+treeNode.id);
	});
	var editBtn = $("#editBtn_"+treeNode.tId);
	if (editBtn) editBtn.bind("click", function(){//编辑时
		$("#inputPage").load('${ctx}/system/menu/input.do?id='+treeNode.id);
	});
};
//移开焦点	
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.tId).unbind().remove();
	$("#editBtn_"+treeNode.tId).unbind().remove();
};
//点击节点
function onClick(event, treeId, treeNode) {
	$("#inputPage").load('${ctx}/system/menu/input.do?id='+treeNode.id);
};

function beforeRemove(treeId, treeNode) {
	if (confirm("确认删除菜单-- " + treeNode.menuname + " 吗？")){
		Free.ajax({
			   type: "POST",
			   url: "${ctx}/system/menu/delete.do",
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

var zTree = null;
$(function ()
{	
	zTree = $.fn.zTree.init($("#menuTree"), setting);

	
});
</script>
</head>
<body>

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
<ul id="menuTree" class="ztree" style="float: left;overflow-x:hidden ;overflow-y:scroll;height: 350px;width:400px;"></ul>
<div id="inputPage" style="float: left; width: 340px;margin:20px">
</div>
</body>	
</html>