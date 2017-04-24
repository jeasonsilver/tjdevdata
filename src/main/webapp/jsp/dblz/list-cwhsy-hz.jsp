<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/import-basic-js-css.jsp"%>
<link href="${ctx}/ui/queryplugin/styles.css" rel="stylesheet" type="text/css" media="screen"/>

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
					{display : '标题',name : 'bt',
						render:function(row){
							return"<a target='blank' href='${ctx}/dblz/cwhsy/view.do?id="+row.id+"' >"+sliceLongName2(row.bt)+"</a>";
						},align:"left"}, 
						 {display : '发布单位',name : 'fbdw',width:"120"},
						 {display : '发布日期',name : 'fbrq',width:"120"}

            ],
            url: '${ctx}/dblz/cwhsy/ajax_list_hz.do',
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
  var newDialog = new Dialog({type:'iframe',value:'${ctx}/dblz/cwhsy/edit.do?id='+id},
				{title:"编辑常委会审议",modal:true,width:1000,height:450}).show();
}

function f_add(){
	var newDialog = new Dialog({type:'iframe',value:'${ctx}/dblz/cwhsy/input.do'},
		{title:"新增常委会审议",modal:true,width:1000,height:450}).show();
}

function f_view(id){
	  var newDialog = new Dialog({type:'iframe',value:'${ctx}/dblz/cwhsy/view.do?id='+id},
					{title:'/dblz/cwhsy/list-hz.do',modal:true,width:1000,height:450}).show();
}

function f_delete(id){
	  freeConfirm("是否将此信息删除?",function(){
	  Free.ajax({
         	   url: '${ctx}/dblz/cwhsy/delete.do',
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
			<form class="form-horizontal" onsubmit="return false;" id="query-form" method="get" >
					<i class="icon-search"></i>标题：
					<input type="text" placeholder="请输入关键词直接搜索" name="search_LIKE_bt" id="gover_search_key"/>
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