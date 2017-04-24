<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@ include file="/common/import-basic-js-css.jsp"%>

<script type="text/javascript">

$(function (){
	ligergrid =  $("#maingrid").ligerGrid({
            columns: 
				[ 
					{display : '通知名称',width: '100%',name : 'wjmc',align:'left',render:sliceLongName}

            ],
            url: '${ctx}/hywj/hywjxx/ajax_list.do',
            pageSize: 5, sortName: 'id',
            width: '99%', height: '97%', 
            pageParmName:'page',
            enabledSort:false,
        });

    });
$(function(){
	
	
});
</script>

        <div id="maingrid" ></div>


