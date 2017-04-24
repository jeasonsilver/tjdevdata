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
				 {display : 'ID',name : 'id',width :"5%"}, 
				 {display : '类型名称',name : 'sectionname'}, 
				 {display : '代码',name : 'code'}, 
				 {display : '代码值',name : 'codename'}, 
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
            url: '${ctx}/system/code/ajax_list.do',
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
  var newDialog = new Dialog({type:'url',value:'${ctx}/system/code/input.do?id='+id},
				{modal:true,width:600,height:350}).show();
}

function f_add(){
	var newDialog = new Dialog({type:'url',value:'${ctx}/jsp/system/input-code.jsp'},
		{modal:true,width:600,height:350}).show();
}
function f_delete(id){
	  freeConfirm("是否将此信息删除?",function(){
	  Free.ajax({
       	   url: '${ctx}/system/code/delete.do',
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
			<form class="span12 form-horizontal" id="query-form" method="get" action="stat-system">
						登录名：
						<input type="text" class="span2" placeholder="精确查询..." name="search_EQ_loginname">
						用户名：
						<input type="text" class="span2" placeholder="模糊查询..." name="search_LIKE_xm">
					
							<input type="button" onclick="f_query();" value="查询" class="btn btn-danger">
							<button class="btn" type="reset">重置</button>
							<input type="button" onclick="f_add();" value="新增" class="btn btn-success">
					
			</form>
            </td>
          </tr>
        </tbody></table>
        </li>
        <li>
        <div id="maingrid" ></div>
        </li>
	</ul>
</body>	

</html>
