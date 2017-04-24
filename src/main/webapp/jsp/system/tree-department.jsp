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
			url:"${ctx}/system/department/ajax_tree.do?check=true"
		},
		data: {
			key:{
				name:"deptname"
			},
			simpleData: {
				enable: true,//true时下面的设置生效
				idKey: "id",//id
				pIdKey: "fid",//pid
			}
		},
		callback: {
			onAsyncSuccess: zTreeOnAsyncSuccess,//异步加载成功
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
		+ "' title='新增部门' onfocus='this.blur();'></span>";
	var editStr = "<span class='button edit' id='editBtn_" + treeNode.tId
		+ "' title='修改部门' onfocus='this.blur();'></span>";
	var userStr = "<span class='button user' id='userBtn_" + treeNode.tId
	+ "' title='添加用户' onfocus='this.blur();'></span>";
	 
	sObj.after(addStr+editStr+userStr);
	var btn = $("#addBtn_"+treeNode.tId);
	if (btn) btn.bind("click", function(){
		$("#inputPage").load('${ctx}/jsp/system/input-department.jsp?fid='+treeNode.id);
	});
	var editBtn = $("#editBtn_"+treeNode.tId);
	if (editBtn) editBtn.bind("click", function(){
		$("#inputPage").load('${ctx}/system/department/input.do?id='+treeNode.id);
	});
	
	var userBtn = $("#userBtn_"+treeNode.tId);
	if (userBtn) userBtn.bind("click", function(){
		$("#inputPage").load('${ctx}/jsp/system/tree-departmentUser-choose-user.jsp?deptid='+treeNode.id);
	});
};
//移开焦点	
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.tId).unbind().remove();
	$("#editBtn_"+treeNode.tId).unbind().remove();
	$("#userBtn_"+treeNode.tId).unbind().remove();
};


function beforeRemove(treeId, treeNode) {
	var bool = false;
	if (confirm("确认删除部门-- " + treeNode.deptname + " 吗？")){
		$.ajax({
			   type: "POST",
			   url: "${ctx}/system/department/check.do",
			   data: {id:treeNode.id},
			   async:false,
			   success: function(data){
				   if(data=="ok"){
					   del(treeId, treeNode);
					   bool = true;
				   }else{
					   alert("当前部门中存在人员，不能删除!");
				   }
			   }
		});
		
	}else{
		return bool;
	}
	return bool;
}

function del(treeId, treeNode){
	$.ajax({
		   type: "POST",
		   url: "${ctx}/system/department/delete.do",
		   data: {id:treeNode.id},
		   async:false,
		   success: function(data){
			   if(data=="ok"){
				   alert("删除成功");
			   }
		   }
	   });
}

var treeObj = null;
$(function ()
{	
	treeObj = $.fn.zTree.init($("#menuTree"), setting);

	$("#backGrid").click(function(){
		   $(".rig").load("${ctx}/jsp/system/list-department.jsp");			
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
<div id="inputPage" style="float: left;height: 350px;width:400px;margin:10px">
</div>
</body>	
</html>

