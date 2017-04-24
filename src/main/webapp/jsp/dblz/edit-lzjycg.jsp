<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html><!-- html5标签 不能省略 -->
<html>
<head>
<%@ include file="/common/import-basic-js-css.jsp"%>
	<script type="text/javascript">
		var count = 1;
		$(function(){
				$.metadata.setType("attr", "validate");
				$("#inputForm").validate();
				
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
				
				$("#freeLookup").click(function(){
					lookupDialog = new Dialog({type:'url',value:'${ctx}/jsp/system/tree-department-select.jsp?lookupId='+$(this).attr("lookupId")+"&lookupName="+$(this).attr("lookupName")},
							{modal:true});
					lookupDialog.show();
				});
				
				$("#freeLookup_rdrealname").click(function(){
					//得到当前的所选定的人
					var users="${ob.rdrealname}";
					lookupDialog = new Dialog({type:'url',value:'${ctx}/jsp/system/tree-departmentUser-select.jsp?lookupId='
							+$(this).attr("lookupId")+"&lookupName="+$(this).attr("lookupName")+"&users="+users},
							{modal:true,width:510,height:320});
					lookupDialog.show();
				});
				
				$("#file_upload").uploadify({
			        height        : 20,
			        width         : 120,
			        swf: '${ctx}/ui/uploadify/scripts/uploadify.swf',
			        uploader      : '${ctx}/dblz/lzjycg/ajax_upload.do;jsessionid=<%=session.getId()%>',
			        'fileTypeDesc'  : 'All Files',       //图片选择描述    
			        'multi': true,
			        'buttonText': '上传附件',
			        'onUploadSuccess':function(file, data, response){
			             $("#filename").html(file.name+"<a class='jq_fj' href='javascript:void(0);' onClick='deleteTempAccessory();' >删除</a>");
			             $("#filename_hidden").val(file.name);
			             $("#zp_hidden").val(data);
			        }
			    });
				 
				
				var contentCkeditor = CKEDITOR.replace("lznr");
				contentCkeditor.config.height = "150px";//
				contentCkeditor.config.ckfinderPath="${ctx}/ckfinder";
				var date = new Date(), year = date.getFullYear(), month = (date.getMonth()+1)>9?date.getMonth()+1:"0"+(date.getMonth()+1);
				contentCkeditor.config.ckfinderUploadPath="/cms/article/"+year+"/"+month+"/";//
				
			});
		
		function jump(){
			location.href="${ctx}/dblz/lzjycg/addpage.do?free_menuTitleName=发布履职经验";
		}

		//删除附件
		function deleteTempAccessory(){
			$("#filename").html("");
			$("#filename_hidden").val("");
		    $("#zp_hidden").val("");
		}
		//删除附件
		function deleteAccessory(id){
		  	  Free.ajax({
		       	   url: '${ctx}/dblz/lzjycg/ajax_deleteFile.do;',
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

<style type="text/css">  
#queue {  
    background-color: #FFF;  
    border-radius: 3px;  
    box-shadow: 0 1px 3px rgba(0,0,0,0.25);  
    height: 53px;  
    margin-bottom: 10px;  
    overflow: auto;  
    padding: 5px 10px;  
    width: 686px;  
}  
</style>
</head>
<body>

<form class="span12"  method="post" id="inputForm" action="${ctx}/dblz/lzjycg/save_bypop.do" >
	<input type="hidden" name="id" value="${ob.id}" />
	<input type="hidden" name="lzid" value="${ob.lzid}" />
	<input type="hidden" name="rdname" value="${ob.rdname}" />
	<input type="hidden" name="xxzt" value="${ob.xxzt}" />
	<input type="hidden" name="shzt" value="${ob.shzt}" />
	<input type="hidden" name="contentid" value="${ob.contentid}" />
	<input type="hidden" name="content.id" value="${ob.content.id}" />


	<div class="row" style="margin-top: 10px">
	
		<label class="span2 " for="">标题<font color="red">*</font></label>
		<div class="span10">
			<input name="lzbt" value="${ob.lzbt}" type="text"  maxlength="100" validate="{required:true}" style="width: 702px"/>
		</div>
		<label class="span2 " for="">来源<font color="red">*</font></label>
		<label class="span10" style="padding-top: 4px;padding-bottom: 4px">
			<input name="ly" id="ly" type="radio" checked="checked" value="1"/>个人发布
          	<input name="ly" id="ly" type="radio" value="2"/>单位发布
		</label>
		

			<label class="span2 " for="">发布人<font color="red">*</font></label><div class="span4">
				<input name="fbrxm" value="${ob.fbrxm}" type="text"  maxlength="30" validate="{required:true}" readonly="readonly"/>
				<input name="fbrid" type="hidden" value="${ob.fbrid}"/>
			</div>

		<label class="span2 " for="">发布单位<font color="red">*</font></label><div class="span4">
			<input  name="hdzzdw" id="hdzzdw" value="${ob.hdzzdw}" type="text" maxlength="30" />
		</div>
		
		<label class="span2 ">上传附件</label>
		<div class="span10" style="margin-top: 10px;white-space:nowrap">
			<table>
			<tr>
			<td style="width: 140px"><input id="file_upload" name="file_upload" type="file" multiple="true"/>
			<input type="hidden" name="fj" id="zp_hidden" value="${ob.fj}"/>
			<input type="hidden" name="filename" id="filename_hidden" value="${ob.filename}"/></td>
			<td><div id="filename" style="float: left;">${ob.filename}</div>
				<c:if test="${!empty ob.filename}">
					<a class="jq_fj" href='javascript:void(0);' onClick='deleteAccessory(${ob.id});' >删除</a>
				</c:if>
			 </td>
			</tr>
			</table>
		</div>
		
		<label class="span2 " for="" style="margin-top: 50px">内容<font color="red">*</font></label><div class="span10">
			<textarea  name="content.lznr" id="lznr" style="width:702px;height:150px">${ob.content.lznr}</textarea>
		</div>
		
		
		
		<div class="span12 text-center" style="margin-top: 20px;margin-bottom: 20px">		
			<button class="btn btn-success" id="free_submit" type="submit"><i class="icon-ok icon-white" ></i> 保存 </button>
		</div>
	</div>
</form>
</body>
</html>