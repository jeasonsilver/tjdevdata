<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script>

var setting = {
		view: {
			selectedMulti: false
		},
		check: {
			enable: false//是否显示checkbox
		},
		async: {
			enable: true,
			url:"${ctx}/system/department/ajax_tree_dbt.do?check=true"
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
			onAsyncSuccess: zTreeOnAsyncSuccess,  //异步加载成功
			onClick :click
		}
	};
//异步加载成功后    展开所有节点
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
	treeObj.expandAll(true);
};
//
function click(event, treeId, treeNode, msg) {
   $("#${param.lookupId}").val(treeNode.id);//查找带回id
   $("#${param.lookupName}").val(treeNode.deptname);//查找带回name
   Free.closeThisDialog();
};


var treeObj = null;
$(function ()
{	
	treeObj = $.fn.zTree.init($("#menuTree"), setting);
});
</script>

<style type="text/css">
ul.ztree {
    background: none repeat scroll 0 0 #f0f6e4;
    border: 1px solid #617775;
    height: 360px;
    margin-top: 10px;
    overflow-x: auto;
    overflow-y: scroll;
    width: 490px;
}
	</style>
    <ul id="menuTree" class="ztree" style="float:left;overflow-x:hidden;height:250px"></ul>