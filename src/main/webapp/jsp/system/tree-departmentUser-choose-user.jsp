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
	//将外部的值带回并递归查找选中
	var nodes = zTree.getNodes();
	var s ="${param.deptid}";
	checkNode (nodes,s);
	zTree.expandAll(true);
};

function checkNode (nodes,s){
	for (var i=0; i<nodes.length; i++) {
		if(nodes[i].id == s){
			zTree.checkNode(nodes[i], true, true);
			
		}else if (nodes[i].children.length>0){
			checkNode (nodes[i].children,s);
		}
		
	}
}
var zTree = null;
$(function ()
{	
	zTree = $.fn.zTree.init($("#menuTree1"), setting);
	$("#backGrid").click(function(){
		   $(".rig").load("${ctx}/jsp/system/list-department.jsp");			
	});
});

function save(){
	var nodes = zTree.getCheckedNodes(true);
	var text="";
	var name="";
	var map = new Map();
    for (var i = 0; i < nodes.length; i++)
    {
        if (nodes[i].iconSkin){//只取用户节点
        	if(!map.containsKey(nodes[i].id)){
		        text += nodes[i].id + ",";
		        name += nodes[i].deptname + ",";
		      //放入查重标志 名称-id
     	        map.put(nodes[i].id,true);
        	}
        }
    }
    
	$.ajax({
		   type: "POST",
		   url: "${ctx}/system/department/save_user_dept.do",
		   data: {"deptid":$("#deptid").val(),"userids":text},
			error:Free.ajaxError,
		   success: function(data){
	     	if(data=='ok'){
	     		freeAlert("保存成功！",function(){
    	     		if(f_query){
    	    	 		f_query();//重新加载grid
    	    	 	}
    	    	 	Free.closeAllDialog();
	     		});  
	     	}else{
	     		alert("保存失败");
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
    overflow-x: auto;
    overflow-y: scroll;
    width: 490px;
}
	</style>
	<input name="deptid" id="deptid" value="${param.deptid}" type="hidden"/>

    <ul id="menuTree1" class="ztree" style="float:left;overflow-x:hidden;height:350px;width: 400px;margin-top: 0px"></ul>
    <div class="controls">
			<button class="btn btn-success" id="free_submit" type="button" style="float: left;margin-top: 20px" onclick="save();"> 确定 </button>
	</div>
