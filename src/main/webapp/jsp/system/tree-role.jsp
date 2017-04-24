<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script>

var setting = {
		check: {
			enable: true//是否显示checkbox
		},
		async: {
			enable: true,
			url:"${ctx}/system/role/ajax_all_list.do"
		},
		data: {
			key:{
				name:"rolename"//显示名字
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
	//zTree.expandAll(true);
	//将外部的值带回并递归查找选中
	var nodes = zTree.getNodes();
	var s ="${userRole}".split(',');
	if (s.length>0)
	checkNode (nodes,s);
};

function checkNode (nodes,s){
	
	for (var i=0; i<nodes.length; i++) {
		
			for (var j=0; j<s.length; j++) {
				if (nodes[i].id==s[j]){
					zTree.checkNode(nodes[i], true, false);
				}
			}
		
	}
}
var zTree = null;
$(function ()
{	
	zTree = $.fn.zTree.init($("#roleTree"), setting);
});

function confirm(){
	//提取选中
	var nodes = zTree.getCheckedNodes(true);
	var ids = "";
	 for (var i = 0; i < nodes.length; i++)
	 {
	        //if (nodes[i].iconSkin){//只取用户节点
		        ids += nodes[i].id + ",";
	        //}
	 }
	 $("#roleids").val(ids);

	$.ajax({
		type: 'POST',
	    url: '${ctx}/system/role/chooseUserRole.do',
	    data: $('#inputForm').serialize(),
	    success:function(data){
	    	if(data == "success"){
	    		alert("设置成功");
	    		Free.closeThisDialog();
	    	}
	    }
	   
	});
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
 <ul id="roleTree" class="ztree" style="float:left;overflow-x:hidden;height:250px"></ul>
<form class="form-horizontal" id="inputForm" method="get" action="stat-system" >
	 <input name="userid" type="hidden" value="${userid}"/>
	 <input name="roleids" id="roleids" type="hidden" value=""/>
	<input type="button" onclick="confirm()"   class="btn btn-success" value="确定" style="margin: 5px 0px 0px 450px"/>
</form>	
	
   
