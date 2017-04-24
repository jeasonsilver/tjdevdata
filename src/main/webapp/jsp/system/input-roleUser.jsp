<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script>

var setting = {
		check: {
			enable: true//是否显示checkbox
		},
		async: {
			enable: true,
			url:"${ctx}/system/department/ajax_depUserTree.do"
		},
		data: {
			key:{
				name:"deptname"//显示名字
			},
			simpleData: {
				enable: true,//true时下面的设置生效
				idKey: "id",//id
				pIdKey: "fid",//pid
			}
		},
		callback: {
			onAsyncSuccess: zTreeOnAsyncSuccess//异步加载成功
		}
	};
//异步加载成功后    展开所有节点
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
	zTree.expandAll(true);
	//将外部的值带回并递归查找选中
	var nodes = zTree.getNodes();
	  Free.ajax({
      	   url: '${ctx}/system/role/fetchRoleUsers.do',
      	   data: {id:${param.id}},
      	   success: function(data){
      		 checkNode (nodes,data);
       	   }
	  });
	
};

function checkNode (nodes,users){
	for (var i=0; i<nodes.length; i++) {
		if (nodes[i].children.length>0){
			checkNode (nodes[i].children,users);
		}else{
			 $.each(users,function(j,o){
				if (nodes[i].id==o.userid){
					zTree.checkNode(nodes[i], true, false);
				}
      		 });
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
function saveUser(){
	var nodes = zTree.getCheckedNodes(true);
	var text="";
    for (var i = 0; i < nodes.length; i++)
    {
        if (nodes[i].iconSkin){//只取用户节点
	        text += nodes[i].id + ",";
        }
    }
	  Free.ajax({
	   	   url: '${ctx}/system/role/saveRoleUser.do',
	   	   data: {id:${param.id},users:text},
	   	   success: function(data){
	   		 if (data=="ok"){
	   			alert("保存成功");
	   			Free.closeThisDialog();
	   		 }
	      }
	  });
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
		人员姓名：
			<input type="text" id="xm" style="width: 213px;" >
			<input type="button" onclick="search()"   class="btn btn-danger" value="查询" >
			<input type="button" onclick="saveUser()"   class="btn btn-success" value="保存" >
		
</form>	

    <ul id="menuTree" class="ztree" style="float:left;overflow-x:hidden;height:250px"></ul>
