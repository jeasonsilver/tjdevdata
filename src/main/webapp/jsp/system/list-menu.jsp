<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<script type="text/javascript">
	var filds = [ 
	              {display : '菜单名称',name : 'menuname',width : 100}, 
	              {display : '菜单url',name : 'url',width : 200}, 
	              {display : '父级菜单ID',name : 'pid',width : 100}, 
	              {display : '是否显示',name : 'sfxs',width : 100}, 
	              {display : '顺序',name : 'sx',width : 100}, 
	              {display : '操作',name:'',width : 100}, 
	            ];
	var actions = [
	              {name : '修改',fun:function(){
	   					newDialog = new Dialog({type:'url',value:'${ctx}/system/menu/input.do?id='+$(this).attr('id')},
	   							{modal:true,width:550});
	   					newDialog.show();
	              }},
	              {name : '删除',fun:function(){
	            	  var id =$(this).attr('id');
	            	  freeConfirm("是否将此信息删除?",function(){
	            		  Free.ajax({
		                  	   url: '${ctx}/system/menu/delete.do',
		                  	   data: {id:id},
		                  	   success: function(data){
		                  		   if (data='ok'){
		                               g.reload();
		                  		   }
		                  	   }
	            		  });
		      			});
	              }}
	            ];
	var g;
	var newDialog;
	$(function() {
		g = $("#maingrid").grid({
			title : "菜单",
			url : "${ctx}/system/menu/ajax_list.do",
			filds : filds,
			actions:actions,
			imagesrc:'${ctx}'
		});
		$(".freeGrid_new").click(function(){
			newDialog = new Dialog({type:'url',value:'${ctx}/jsp/system/input-menu.jsp'},
					{modal:true});
			newDialog.show();
			
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
	            </td>
	          </tr>
	        </table>
		</form>
	</div>

