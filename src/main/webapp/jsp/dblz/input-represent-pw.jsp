<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script src="${ctx}/ui/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>
<link href="${ctx}/ui/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<script>



$(function(){
	Free.validateSubmitAndClose($("#inputForm"));
	
	$("#freeLookup").click(function(){
		lookupDialog = new Dialog({type:'url',value:'${ctx}/jsp/system/tree-department-select.jsp?lookupId='+$(this).attr("lookupId")+"&lookupName="+$(this).attr("lookupName")},
				{modal:true});
		lookupDialog.show();
	});
	
	$('#datetimepicker').datetimepicker({  
        format: 'yyyy-MM-dd',  
        language: 'zh-CN',  
        pickDate: true,  
        pickTime: true,  
        hourStep: 1,  
        minuteStep: 15,  
        secondStep: 30,  
        inputMask: true  
      }); 
	
	
	$("#file_upload").uploadify({
        height        : 20,
        width         : 120,
        swf: '${ctx}/ui/uploadify/scripts/uploadify.swf',
        uploader      : '${ctx}/dblz/representinfo/ajax_upload.do;jsessionid=<%=session.getId()%>',
        'fileTypeDesc'  : 'All Files',       //图片选择描述  
        'fileTypeExts'  : '*.jpg;*.png;',//文件后缀限制 默认：'*.*'  
        'multi': true,
        'buttonText': '上传照片',
        'onUploadSuccess':function(file, data, response){
             $("#filename").html(file.name);
             $("#filename_hidden").val(file.name);
             $("#zp_hidden").val(data);
        }
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
<form  class="form-horizontal"  id="inputForm"  action="${ctx}/dblz/representinfo/save.do">
<input type="hidden" name="id" value="${ob.id}" />
	<div class="control-group">
		<label class="control-label" for="">新密码</label>
		<div class="controls">
			<input name="password"  class="span4"  type="text"  validate="{required:true}" />
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<button class="btn btn-success" id="free_submit" type="submit"> 保存 </button>
		</div>
	</div>
</form>

