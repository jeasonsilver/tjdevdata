<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/import-basic-js-css.jsp"%>

<script type="text/javascript">
//字符串格式化函数String.format
String.format = function(src) {
	if (arguments.length == 0)
		return null;
	var args = Array.prototype.slice.call(arguments, 1);
	return src.replace(/\{(\d+)\}/g, function(m, i) {
		return args[i];
	});
};
$(function (){
	ligergrid =  $("#maingrid").ligerGrid({
            columns: 
				[ 
				{display : ' ', 
					render: function (row)
				     {
				    return '<i class="icon-list-alt"></i>';
				}, width: 30},
				 {display : '标题',name : 'lzbt',width:"30%",render:function(row){
						return"<a href='javascript:void(0);' onclick='f_view("+row.id+")'>"+sliceLongName2(row.lzbt)+"</a>";
					}}, 
				 {display : '活动组织单位',name : 'hdzzdw'}, 
				 {display : '履职日期',name : 'lzrq'},
				 {
                     display: '操作', isAllowHide: false,
                     render: function (row)
                     {
                         var html ="";
                         html+='<button class="btn btn-mini btn-info" onclick="f_view({0})" type="button"><i class="icon-search icon-white" ></i></button>&nbsp;';
                         html+='<button class="btn btn-mini btn-info" onclick="f_edit({0})" type="button"><i class="icon-edit icon-white" ></i></button>&nbsp;';
                         html+='<button class="btn btn-mini btn-danger" onclick="f_delete({0})" type="button"><i class="icon-remove icon-white" ></i></button>&nbsp;';
                         
                         return String.format(html,row.id);
                     }, width: 160
                 }

            ],
            url: '${ctx}/dblz/lzhdjl/my_ajax_list.do',
            pageSize: 20, sortName: 'id',
            width: '99%', height: '98%', 
            checkbox : false,
            pageParmName:'page',
            enabledSort:false
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
  var newDialog = new Dialog({type:'url',value:'${ctx}/dblz/lzhdjl/input.do?id='+id},
				{title:"履职信息编辑",modal:true,width:1000,height:430}).show();
}
function f_view(id){
	var newDialog = new Dialog({type:'iframe',value:'${ctx}/dblz/lzhdjl/view.do?id='+id},
			{title:"履职信息",modal:true,width:1000,height:430}).show();
}
function f_delete(id){
	  freeConfirm("是否将此信息删除?",function(){
	  Free.ajax({
         	   url: '${ctx}/dblz/lzhdjl/delete.do',
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
					<i class="icon-search"></i>标题：
					<input type="text" placeholder="模糊查询..." name="search_LIKE_lzbt">
					活动组织单位：
					<input type="text" name="search_LIKE_hdzzdw">
					<button type="button" onclick="f_query();" class="btn btn-danger btn-small"><i class="icon-search  icon-white"></i> 查询</button>
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