<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script>

var setting = {
		check: {
			enable: true//是否显示checkbox
		},
		async: {
			enable: true,
			url:"${ctx}/system/menu/ajax_all.do?check=true&roleid=${ob.id}"
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
		}
	};
var treeObj = null;
$(function ()
{	
	treeObj = $.fn.zTree.init($("#menuTree"), setting);
	 $(".freeGrid_submit").click(function(){
		 var nodes = treeObj.getCheckedNodes(true);
		 var text="";
         for (var i = 0; i < nodes.length; i++)
         {
             text += nodes[i].id + ",";
         }
		$.ajax({
 			   type: "POST",
			   url: "${ctx}/system/role/saveMenu.do",
			   data: {menus:text,id:'${ob.id}'},
			   success: function(data){
				   alert("保存成功");
		   			Free.closeThisDialog();
			   },
			   async: false
			});
	 });
});
</script>
<style>
<!--
ul.ztree {
    background: none repeat scroll 0 0 #f0f6e4;
    border: 1px solid #617775;
    height: 360px;
    margin-top: 10px;
    overflow-x: auto;
    overflow-y: scroll;
    width: 490px;
}
-->
</style>
<ul id="menuTree" class="ztree" style="overflow-y:scroll;height: 250px;background-color: #FFF"></ul>
<p style="text-align:center" >
	<a class="btn btn-success freeGrid_submit" href="javascript:void(0);">保存</a>
</p>

