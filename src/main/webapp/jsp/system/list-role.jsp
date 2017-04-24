<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/import-basic-js-css.jsp"%>

<script type="text/javascript">
$(function (){
	ligergrid =  $("#maingrid").ligerGrid({
            columns: 
				[ 
	              {display : '角色',name : 'rolename'}, 
		          {display : '描述',name : 'description'},
				  {display : '操作',name : '', isAllowHide: false,
                     render: function (row)
                     {
                         var html ="";
                         html+='<button class="btn btn-mini btn-primary" onclick="f_roleUser({0})" type="button">授权用户</button>&nbsp;';
                         html+='<button class="btn btn-mini btn-info" onclick="f_roleMenus({0})" type="button">授权菜单</button>&nbsp;';
                         html+='<button class="btn btn-mini btn-warning" onclick="f_roleAuthorize({0})" type="button">授权功能</button>&nbsp;';
                         html+='<button class="btn btn-mini " onclick="f_edit({0})" type="button">编辑</button>&nbsp;';
                         html+='<button class="btn btn-mini btn-danger" onclick="f_delete({0})" type="button">删除</button>';
                         return Free.replace(html,row.id);
                     },width:"30%"
                 }

            ],
            url: '${ctx}/system/role/ajax_list.do',
            pageSize: 10, sortName: 'id',
            width: '99%', height: '98%', 
            checkbox : false,
            pageParmName:'page'
        });

    });
function f_query(){
	ligergrid.set('parms',$('#query-form').serializeArray());
	if (ligergrid.get("page") > 1)
		ligergrid.changePage("first");
	else
		ligergrid.loadData();
}
function f_edit(id){
  new Dialog({type:'url',value:'${ctx}/system/role/input.do?id='+id},
		{modal:true,width:600,height:250}).show();
}
function f_roleUser(id){
  new Dialog({type:'url',value:'${ctx}/jsp/system/input-roleUser.jsp?id='+id},
		{modal:true}).show();
}
function f_roleMenus(id){
  new Dialog({type:'url',value:'${ctx}/system/role/inputRoleMenu.do?id='+id},
		{modal:true}).show();
}
function f_roleAuthorize(id){
  new Dialog({type:'url',value:'${ctx}/system/role/inputRoleAuthorize.do?id='+id},
		{modal:true}).show();
}
function f_add(){
	new Dialog({type:'url',value:'${ctx}/jsp/system/input-role.jsp'},
		{modal:true,width:600,height:250}).show();
}
function f_delete(id){
	  freeConfirm("是否将此信息删除?",function(){
	  Free.ajax({
         	   url: '${ctx}/system/role/delete.do',
         	   data: {id:id},
         	   success: function(data){
         		   if (data='ok'){
         			 ligergrid.loadData();
         		   }
	         	   }
		  });
	});
}
</script>
</head>
<body>

<ul >
<li>
<%@ include file="/common/listTitle.jsp"%>
        </li>
        <li>
        <table class="tab">
          <tbody>
	          <tr class="tab_white02">
	            <td>     
				<form class="form-horizontal" id="query-form" method="get" action="stat-system">
					角色名：
							<input type="text" placeholder="精确查询..." name="search_EQ_rolename">
						<input type="button" onclick="f_query();" value="查询" class="btn btn-danger">
						<button class="btn" type="reset">重置</button>
						<input type="button" onclick="f_add();" value="新增" class="btn btn-success">
				</form>
	            </td>
	          </tr>
	        </tbody>
        </table>
        </li>
        <li>
        <div id="maingrid" ></div>
        </li>
	</ul>
</body>	

</html>
