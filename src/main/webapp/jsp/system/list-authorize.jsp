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
	              {display : '模块',name : 'module'}, 
	              {display : '描述',name : 'description'}, 
	              {display : '顺序',name : 'sx'}, 
	              {
                     display: '操作', isAllowHide: false,
                     render: function (row)
                     {
                         var html ="";
                        html+='<button class="btn btn-mini btn-info" onclick="f_edit({0})" type="button">编辑</button>&nbsp;';
                         html+='<button class="btn btn-mini btn-danger" onclick="f_delete({0})" type="button">删除</button>';
                        return Free.replace(html,row.id);
                     }, width: 160
                 }

            ],
            url: '${ctx}/system/authorize/ajax_list.do',
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
  var newDialog = new Dialog({type:'url',value:'${ctx}/system/authorize/input.do?id='+id},
				{modal:true,width:600,height:350}).show();
}

function f_add(){
	var newDialog = new Dialog({type:'url',value:'${ctx}/jsp/system/input-authorize.jsp'},
		{modal:true,width:600,height:350}).show();
}
function f_delete(id){
	  freeConfirm("是否将此信息删除?",function(){
	  Free.ajax({
       	   url: '${ctx}/system/authorize/delete.do',
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
				<form class="form-horizontal" onsubmit="return false;" id="query-form" method="get" action="stat-system">
					模块：
							<input type="text" placeholder="模糊查询..." name="search_LIKE_module">
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
