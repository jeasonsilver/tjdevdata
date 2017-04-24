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
				 {display : '标题',align:"left",name : 'lzbt',render:function(row){
						return "<a target='blank' href='${ctx}/dblz/lzjycg/view.do?id="+row.id+"' >"+sliceLongName2(row.lzbt)+"</a>";
					}}, 
				{display : '发布人',width: 160,render: function (row)
	             {
                 	if(row.hdzzdw != null && row.hdzzdw != ''){
                 		return row.hdzzdw;
                 	}else{
                 		return row.fbrxm;
                 	}
                    
                 }},
				 {display : '发布日期',width: 160,render: function (row)
                     {
                        return new Date(row.cjsj).Format("yyyy-MM-dd hh:mm:ss");
                        
                     },width: 160}

            ],
            url: '${ctx}/dblz/lzjycg/ajax_list_sh.do',
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
  	var newDialog = new Dialog({type:'url',value:'${ctx}/dblz/lzjycg/input.do?id='+id},
				{title:"履职体会信息编辑",modal:true,width:1000,height:430}).show();
}
function f_view(id){
	var newDialog = new Dialog({type:'iframe',value:'${ctx}/dblz/lzjycg/view.do?id='+id},
					{title:"履职体会信息",modal:true,width:1000,height:430}).show();
}
function f_delete(id){
	  freeConfirm("是否将此信息删除?",function(){
	  Free.ajax({
         	   url: '${ctx}/dblz/lzjycg/delete.do',
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
					代表姓名：
					<input type="text" name="search_LIKE_rdrealname">
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