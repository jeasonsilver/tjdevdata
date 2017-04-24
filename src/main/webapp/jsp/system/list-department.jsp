<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<script type="text/javascript">
	var filds = [ 
	              {display : '部门名称',name : 'deptname',width : 100}, 
	              {display : '部门电话',name : 'deptphone',width : 100}, 
	              {display : '部门地址',name : 'deptadress',width : 100}, 
	              {display : '部门负责人',name : 'deptfzr',width : 100}, 
	              {display : '部门联系人',name : 'deptlxr',width : 100}, 
	              {display : '父级部门ID',name : 'fid',width : 100}, 
	              {display : '操作',name:'',width : 100}, 
	            ];
	var actions = [
	              {name : '修改',fun:function(){
	   					newDialog = new Dialog({type:'url',value:'${ctx}/system/department/input.do?id='+$(this).attr('id')},
	   							{modal:true,width:550});
	   					newDialog.show();
	              }}
	            ];
	var g;
	var newDialog;
	$(function() {
		g = $("#maingrid").grid({
			title : "部门管理",
			url : "${ctx}/system/department/ajax_list.do",
			filds : filds,
			actions:actions,
			imagesrc:'${ctx}'
		});
		$(".freeGrid_new").click(function(){
			newDialog = new Dialog({type:'url',value:'${ctx}/jsp/system/input-department.jsp'},
					{modal:true});
			newDialog.show();
			
		});
		$(".freeGrid_tree").click(function(){
			   $(".rig").load("${ctx}/jsp/system/tree-department.jsp");			
		});

	});
	function closeDialog() {
		g.reload();//重新加载grid
	}
</script>
	<div id="maingrid" style="margin: 0; padding: 0">
		<form action="">
			<table class="tab">
	          <tr class="tab_grey02">
	            <td>
				</td>
	          </tr>
	          <tr class="tab_white02">
	            <td>
		            <a class="red_but fGridSearch" href="javascript:void(0);">查询</a>
		            <a class="red_but freeGrid_new" href="javascript:void(0);">新增</a>
		            <a class="red_but freeGrid_tree" href="javascript:void(0);">树型</a>
	            </td>
	          </tr>
	        </table>
		</form>
	</div>

