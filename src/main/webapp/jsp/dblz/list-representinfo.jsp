<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/import-basic-js-css.jsp"%>

<script src="${ctx}/ui/queryplugin/jquery.autocomplete-1.1.3.js" type="text/javascript"></script>
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
					{display : '代表姓名',
						render:function(row){
							return"<a href='javascript:void(0);' onclick='f_view("+row.id+")'>"+sliceLongName2(row.xm)+"</a>";
						},width:160}, 
					 //{display : '代表证号',name : 'dbzh'}, 
					 {display : '代表团',name : 'dbt',width:160},
					 //{display : '单位名称',name : 'dwmc'},
					 {display : '职务',name : 'zw',align:"left",render:sliceLongName},
				 {
                     display: '操作', isAllowHide: false,
                     render: function (row)
                     {
                         var html ="";
                         html+='<button title="修改代表信息" class="btn btn-mini btn-info" onclick="f_edit({0})" type="button"><i class="icon-edit icon-white" ></i></button>&nbsp;';
                         html+='<button title="修改密码" class="btn btn-mini btn-info" onclick="f_editPw({0})" type="button"><i class="icon-edit icon-white" ></i></button>&nbsp;';
                         html+='<button class="btn btn-mini btn-danger" onclick="f_delete({0})" type="button"><i class="icon-remove icon-white" ></i></button>';
                         return String.format(html,row.id);
                     }, width: 160
                 }

            ],
            url: '${ctx}/dblz/representinfo/ajax_list.do',
            pageSize: 20, sortName: 'id',
            width: '99%', height: '98%', 
            checkbox : false,
            pageParmName:'page',
            enabledSort:false,
            onAfterShowData:function()  
            {                                  
                $(".l-grid-row-cell-inner").css("height","auto"); //单元格高度自动化，撑开  
                var i=0;  
                $("tr",".l-grid2","#maingrid").each(function ()  
                {                                              
                    $($("tr",".l-grid1","#maingrid")[i]).css("height",$(this).height()); //2个表格的tr高度一致  
                    i++;                          
                });                                              
            }
        });
	
	
	var onAutocompleteSelect =function(value, data) {//根据返回结果自定义一些操作
		  
		}; 
        var options = {
            serviceUrl: '${ctx}/dblz/representinfo/getXm.do?',//获取数据的后台页面
            width: 220,//提示框的宽度
            delimiter: /(,|;)\s*/,//分隔符
            onSelect: onAutocompleteSelect,//选中之后的回调函数
            deferRequestBy: 100, //单位微秒
            noCache: false //是否启用缓存 默认是开启缓存的
        };
   		a1 = $('#gover_search_key').autocomplete(options);
		
    });
function f_query(){
	ligergrid.set('parms',$('#query-form').serializeArray());
	if (ligergrid.get("page") > 1)
		ligergrid.changePage("first");
	else
		ligergrid.loadData();
}
function f_edit(id){
  var newDialog = new Dialog({type:'url',value:'${ctx}/dblz/representinfo/input.do?id='+id},
				{title:"编辑代表信息",modal:true,width:1000,height:430}).show();
}
function f_editPw(id){
	  var newDialog = new Dialog({type:'url',value:'${ctx}/dblz/representinfo/inputPw.do?id='+id},
				{title:"修改密码",modal:true,width:500,height:300}).show();
}
function f_view(id){
	  var newDialog = new Dialog({type:'iframe',value:'${ctx}/dblz/representinfo/view.do?id='+id},
					{title:'代表基本信息',modal:true,width:1000,height:430}).show();
	}
function f_add(){
	var newDialog = new Dialog({type:'url',value:'${ctx}/jsp/dblz/input-represent.jsp'},
		{title:"新增代表信息",modal:true,width:1000,height:430}).show();
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
			<form class="form-horizontal" onsubmit="return false;" id="query-form" method="get" >
					<i class="icon-search"></i>代表姓名：
					<input type="text" placeholder="请输入关键词直接搜索" name="search_LIKE_xm" id="gover_search_key"/>
					代表团：
					<g:select name="search_EQ_dbtid" sectionname="代表团" defaultName="请选择..." value="${ob.mz}" />
					<button type="button" onclick="f_query();" class="btn btn-danger btn-small"><i class="icon-search  icon-white"></i> 查询</button>
					<button type="button" onclick="f_add();"  class="btn btn-danger btn-small"><i class="icon-plus icon-white"></i> 新增</button>
					
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