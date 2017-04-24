<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/import-basic-js-css.jsp"%>
<script>

$("#inputForm").form({callback:function(){
	if(f_query){
		f_query();//重新加载grid
	}
	Free.closeThisDialog();
}});

$(function(){
	
	$("#freeLookup").click(function(){
		lookupDialog = new Dialog({type:'url',value:'${ctx}/jsp/system/tree-department-select.jsp?lookupId='+$(this).attr("lookupId")+"&lookupName="+$(this).attr("lookupName")},
				{modal:true});
		lookupDialog.show();
	});
});
</script>
<style type="text/css">  
 
.haha{  
    color:#FFFFFF;  
}  
#queue {  
    background-color: #FFF;  
    border-radius: 3px;  
    box-shadow: 0 1px 3px rgba(0,0,0,0.25);  
    height: 50px;  
    margin-bottom: 10px;  
    overflow: auto;  
    padding: 5px 10px;  
    width: 70%;  
}  
</style>
</head>
<body>
<form class="span12" id="inputForm"  action="${ctx}/dblz/representinfo/save.do" >
<input type="hidden" name="id" value="${ob.id}" />
<input type="hidden" name="representflag" value="1"/>
<input type="hidden" name="sfqy" value="1"/>

<div class="row">
<fieldset>
	<legend>个人信息</legend><br/>
	<label class="span2 " >照片：</label>
	<div class="span4">
		<img src="/pic/${ob.zp}" style="width:150px;height:220px"/>
	</div>
	<label class="span2 " >姓名：</label>
	<label class="span4">
		&nbsp;${ob.xm}
	</label>
	
	<label class="span2 " >性别：</label>
	<label class="span4">
		&nbsp;${ob.xb}
	</label>
	
	<label class="span2 "  >出生日期：</label>
	<label class="span4" >
		&nbsp;${ob.csrq}
	</label>
	
	
	<label class="span2 " >民族：</label>
	<label class="span4">
		&nbsp;${ob.mz}
	</label>
	
	<label class="span2 " >文化程度：</label>
	<label class="span4">
		&nbsp;${ob.whcd}
	</label>
	
	<label class="span2 " >党派：</label>
	<label class="span4">
		&nbsp;${ob.zzmm}
	</label>	
	
	<legend>身份信息</legend><br/>
	
<%-- 	<label class="span2 " >代表团：</label>
	<label class="span4">
		&nbsp;${ob.deptname}
	</label> --%>
	
	<c:if test="ob.representflag!=1">
		<label class="span2 " >代表证号：</label>
		<label class="span4">
			&nbsp;${ob.dbzh}
		</label>
	</c:if>
	<%--<label class="span2 " >职业：</label>
	<label class="span4">
		&nbsp;${ob.zy}
	</label>--%>
	
	<label class="span2 " >职务：</label>
	<label class="span4">
		&nbsp;${ob.zw}
	</label>
	
	<label class="span2 " >通讯地址：</label>
	<label class="span4">
		&nbsp;${ob.dwdz}
	</label>
	
	<label class="span2 " >邮箱：</label>
	<label class="span4">
		&nbsp;${ob.dzyz}
	</label>
	
	<c:if test="ob.representflag!=1">
		<label class="span2 " >专业组：</label>
		<label class="span4">
			&nbsp;${ob.zyz}
		</label>
	</c:if>
	
	<label class="span2 "  style="margin-bottom: 20px">备注：</label>
	<label class="span10" style="margin-bottom: 20px">
		&nbsp;${ob.memo}
	</label>
</fieldset>
</div>

</form>
</body>	

</html>
