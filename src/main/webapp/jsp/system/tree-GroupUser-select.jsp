<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script>

var setting = {
		check: {
			enable: true//是否显示checkbox
		},
		async: {
			enable: true,
			url:"${ctx}/system/group/ajax_tree.do"
		},
		data: {
			key:{
				name:"name"//显示名字
			},
			simpleData: {
				enable: true,//true时下面的设置生效
				idKey: "id",//id
				pIdKey: "pid",//pid
			}
		},
		callback: {
			onAsyncSuccess: zTreeOnAsyncSuccess//异步加载成功
		}
	};
//异步加载成功后    展开所有节点
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
	//zTree.expandAll(true);
	//将外部的值带回并递归查找选中
	var nodes = zTree.getNodes();
	var s ="${param.users}".split(',');
	if (s.length>0)
	checkNode (nodes,s);
};

function checkNode (nodes,s){
	for (var i=0; i<nodes.length; i++) {
		if (nodes[i].children.length>0){
			checkNode (nodes[i].children,s)
		}else{
			for (var j=0; j<s.length; j++) {
				if (nodes[i].id==s[j]){
					zTree.checkNode(nodes[i], true, false);
				}
			}
		}
	}
}
var zTree = null;
$(function ()
{	
	zTree = $.fn.zTree.init($("#menuTree"), setting);
	$("#backGrid").click(function(){
		   $(".rig").load("${ctx}/jsp/system/list-department.jsp");			
	});
});
function lookUpUser(){
	var nodes = zTree.getCheckedNodes(true);
	var text="";
	var name="";
    for (var i = 0; i < nodes.length; i++)
    {
        if (nodes[i].iconSkin){//只取用户节点
	        text += nodes[i].id + ",";
	        name += nodes[i].deptname + ",";
        }
    }
    $("#${param.lookupId}").val(text);//查找带回userid
    $("#${param.lookupName}").val(name);//查找带回userid
    lookupDialog.close();
}
function search(){
	setting.async.url="${ctx}/system/department/ajax_depUserTree.do?xm="+$("#xm").val();
	zTree = $.fn.zTree.init($("#menuTree"), setting);
}
</script>

	<style type="text/css">
.ztree li span.button.iconUser_ico_docu{margin-right:2px; background: url(${ctx}/ui/ztree/css/zTreeStyle/img/diy/ico_user.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}

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
	
<form class="form-horizontal" id="query-form" method="get" action="stat-system">
	人员姓名：<input type="text" id="xm" style="width: 213px;" >
			<input type="button" onclick="search()"   class="btn btn-danger" value="查询" >
			<input type="button" onclick="lookUpUser()"   class="btn btn-success" value="确定" >
</form>	

    <ul id="menuTree" class="ztree" style="float:left;overflow-x:hidden;height:250px"></ul>
