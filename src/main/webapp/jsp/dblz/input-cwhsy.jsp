<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/import-basic-js-css.jsp"%>
<script>
$(function(){
	//Free.validateSubmitAndClose($("#inputForm"));
	
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
        uploader      : '${ctx}/dblz/cwhsy/ajax_upload.do;jsessionid=<%=session.getId()%>',
        'fileTypeDesc'  : 'All Files',       //图片选择描述    
        'multi': false,
        'buttonText': '上传附件',
        'onUploadSuccess':function(file, data, response){
             $("#filename").html(file.name+"<a class='jq_fj' href='javascript:void(0);' onClick='deleteTempAccessory();' >删除</a>");
             $("#filename_hidden").val(file.name);
             $("#zp_hidden").val(data);
        }
    }); 
	
	 $.metadata.setType("attr", "validate");
	 $("#inputForm").validate();
});

//删除附件
function deleteTempAccessory(){
	$("#filename").html("");
	$("#filename_hidden").val("");
  $("#zp_hidden").val("");
}
//删除附件
function deleteAccessory(id){
	  Free.ajax({
       	   url: '${ctx}/dblz/cwhsy/ajax_deleteFile.do;',
       	   data: {id:id},
       	   success: function(data){
       		   if (data='ok'){
       				deleteTempAccessory();
         			$(".jq_fj").remove();
       		   }
	         }
		  });
}
</script>
<script type="text/javascript" src="${ctx}/ckeditor/ckeditor.js"></script>
</head>
<body>
<form method="post" id="inputForm" action="${ctx}/dblz/cwhsy/save.do" >
<input type="hidden" name="id" value="${ob.id}" />
<input type="hidden" name="content.id" value="${ob.content.id}"/>
<input type="hidden" name="cjr" value="${ob.cjr}" />
<input type="hidden" name="djl" value="${ob.djl}" />

<div class="row" style="width: 1000px;margin: 20px 20px 0 0">	
		<label class="span2 " for="">标题：</label><div class="span4" >
		<input name="bt" value="${ob.bt}" type="text" label="标题" maxlength="100" validate="{required:true}" />
	</div>
	
	
		<label class="span2 " for="">发布日期：</label><div class="span4" id="datetimepicker">
		<input name="fbrq"  value="${ob.fbrq}" type="text" maxlength="30" validate="{required:true}" class="add-on"/>
		
	</div>
	
	<label class="span2 " for="">信息来源：</label><div class="span10" >
		<input name="xxly" value="${ob.xxly}" type="text" label="信息来源" maxlength="100" />
	</div>
	
	<label class="span2 " for="">内容：</label><div class="span10" >
		<textarea  name="content.nr" id="nr" label="内容" class="ckeditor">${ob.content.nr}</textarea>
	</div>
	
	<label class="span2 " for="">附件：</label>
	<div class="span10">
		<input id="file_upload" name="file_upload" type="file" multiple="true"/><div id="filename">${ob.filename}</div>
		<input type="hidden" name="fj" id="zp_hidden" value="${ob.fj}"/>
		<input type="hidden" name="filename" id="filename_hidden" value="${ob.filename}"/>
		<c:if test="${!empty ob.filename}">
			<a class="jq_fj" href='javascript:void(0);' onClick='deleteAccessory(${ob.id});' >删除</a>
		</c:if>
	</div>
	
	<label class="span2 " for="">排序：</label>
	<div class="span10">
		<input name="ordercol" type="text" value="${ob.ordercol}" validate="{required:true,digits:true}"/>
	</div>
	<!-- 
		<label class="span2 " for="">优先：</label>
		<div class="span10">
			<select name="zdflag" id="zdflag">
				<option value="2">否</option>
				<option value="1">是</option>
			</select>
		</div>
	 -->
</div>

	<div class="span12 text-center" style="margin-top: 10px;margin-bottom: 20px">
		<button class="btn btn-success" id="free_submit" type="submit"><i class="icon-ok icon-white" ></i> 保存 </button>
		&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn" type="button" onclick="Free.closewindow();"> <i class="icon-ban-circle" ></i>关闭 </button>
	</div>
</form>

<script type="text/javascript">
var contentCkeditor = CKEDITOR.replace("nr");
contentCkeditor.config.height = "";//
contentCkeditor.config.ckfinderPath="${ctx}/ckfinder";
contentCkeditor.config.basicEntities = false;
var date = new Date(), year = date.getFullYear(), month = (date.getMonth()+1)>9?date.getMonth()+1:"0"+(date.getMonth()+1);
contentCkeditor.config.ckfinderUploadPath="/cms/article/"+year+"/"+month+"/";//
$(function(){
	//$("#zdflag").val("${ob.zdflag}");
});
</script>
</body>