<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script>

var setting = {
		view: {
			selectedMulti: false
		},
		check: {
			enable: true,//是否显示checkbox
			chkboxType: { "Y": "", "N": "" }
		},
		async: {
			enable: true,
			url:"${ctx}/system/department/ajax_tree_zyz.do?check=true"
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
			onAsyncSuccess: zTreeOnAsyncSuccess  //异步加载成功
		}
	};
//异步加载成功后    展开所有节点
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
	treeObj.expandAll(true);
	var nodes = treeObj.getNodes();
	var s ="${param.deptid}".split(',');
	if (s.length>0)
	checkNode (nodes,s);
};

function checkNode (nodes,s){
	for (var i=0; i<nodes.length; i++) {
		if (nodes[i].children.length>0){
			for (var j=0; j<s.length; j++) {
				if (nodes[i].id==s[j]){
					treeObj.checkNode(nodes[i], true, false);
				}
			}
			checkNode (nodes[i].children,s);
		}else{
			for (var j=0; j<s.length; j++) {
				if (nodes[i].id==s[j]){
					treeObj.checkNode(nodes[i], true, false);
				}
			}
		}
	}
}

var treeObj = null;
$(function ()
{	
	treeObj = $.fn.zTree.init($("#menuTree"), setting);
});

function lookUpUser(){
	var nodes = treeObj.getCheckedNodes(true);
	var text="";
	var name="";
    for (var i = 0; i < nodes.length; i++)
    {
        //if (nodes[i].iconSkin){//只取用户节点
	        text += nodes[i].id + ",";
	        name += nodes[i].deptname + ",";
        //}
    }
    $("#${param.lookupId}").val(text);//查找带回userid
    $("#${param.lookupName}").val(name);//查找带回userid
    lookupDialog.close();
}

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
	
    <ul id="menuTree" class="ztree" style="float:left;overflow-x:hidden;height:240px"></ul>
    <input type="button" onclick="lookUpUser()"   class="btn btn-success" value="确定" style="float: right;">