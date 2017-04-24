<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
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
				 {display : '登录名',name : 'loginname'}, 
				 {display : '姓名',name : 'xm'}, 
				 {display : 'Email',name : 'dzyz',width :"20%"}, 
				 {display : '手机',name : 'yddh'},
				 {
                     display: '操作', isAllowHide: false,
                     render: function (row)
                     {
                         var html ="";
                         html+='<button class="btn btn-mini btn-info" onclick="f_role({0})" type="button">角色</button>&nbsp;';
                         html+='<button class="btn btn-mini btn-info" onclick="f_edit({0})" type="button">编辑</button>&nbsp;';
                         html+='<button class="btn btn-mini btn-info" onclick="f_editPw({0})" type="button">修改密码</button>&nbsp;';
                         html+='<button class="btn btn-mini btn-danger" onclick="f_delete({0})" type="button">删除</button>';
                        return Free.replace(html,row.id);
                     }, width: 200
                 }

            ],
            url: '${ctx}/system/user/ajax_list.do',
            pageSize: 10, sortName: 'id',
            width: '99%', height: '98%', 
            checkbox : false,
            pageParmName:'page'
        });

	$("#freeLookup2").click(function(){
		lookupDialog = new Dialog({type:'url',value:'${ctx}/jsp/system/tree-department-select.jsp?lookupId='+$(this).attr("lookupId")+"&lookupName="+$(this).attr("lookupName")},
				{modal:true});
		lookupDialog.show();
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
  var newDialog = new Dialog({type:'iframe',value:'${ctx}/system/user/input.do?id='+id},
				{modal:true,width:990,height:350}).show();
}

function f_role(id){
	  var newDialog = new Dialog({type:'url',value:'${ctx}/system/role/userRole.do?userid='+id},
					{modal:true,width:500,height:350}).show();
}

function f_editPw(id){
	  var newDialog = new Dialog({type:'url',value:'${ctx}/dblz/representinfo/inputPw.do?id='+id},
				{title:"修改密码",modal:true,width:500,height:300}).show();
}
function f_add(){
	var newDialog = new Dialog({type:'iframe',value:'${ctx}/jsp/system/input-user.jsp'},
		{modal:true,width:990,height:350}).show();
}
function f_delete(id){
	  freeConfirm("是否将此信息删除?",function(){
	  Free.ajax({
       	   url: '${ctx}/system/user/delete.do',
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
						姓名：
						<input type="text" class="span2" placeholder="模糊查询..." name="search_LIKE_xm">
						部门：
						<input type="hidden" name="deptid" id="deptid" value=""/>
						<div class="input-append">
						<input type="text" class="span2" name="deptname" id="deptname" readOnly="true"/><a id="freeLookup2" class="btn" lookupId="deptid" lookupName="deptname" href="javascript:void(0);">选择</a>
						</div>
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
