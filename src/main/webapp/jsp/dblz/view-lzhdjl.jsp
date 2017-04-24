<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<script src="${ctx}/ui/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>
<link href="${ctx}/ui/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
	<script type="text/javascript">
		var count = 1;
		$(function(){
				$.metadata.setType("attr", "validate");
				$("#inputForm").validate({     
					 submitHandler: function(form){
							$.ajax(
									{
							       		   type: "POST",
							       		   url: $("#inputForm").attr("action"),
							       		   data: $("#inputForm").serialize(),
							      		   error:Free.ajaxError,
							       		   success: function(data){
							       	     	if(data=='ok'){
							       	     		alert("保存成功");
							       	     	}
							       		}
							         }
							);
						}
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
				
				$("#freeLookup").click(function(){
					lookupDialog = new Dialog({type:'url',value:'${ctx}/jsp/system/tree-department-select.jsp?lookupId='+$(this).attr("lookupId")+"&lookupName="+$(this).attr("lookupName")},
							{modal:true});
					lookupDialog.show();
				});
				 
			});
		
		
		
	</script>

<style type="text/css">  
#queue {  
    background-color: #FFF;  
    border-radius: 3px;  
    box-shadow: 0 1px 3px rgba(0,0,0,0.25);  
    height: 103px;  
    margin-bottom: 10px;  
    overflow: auto;  
    padding: 5px 10px;  
    width: 702px;  
}  
</style>

<form class="span12"  method="post" id="inputForm" action="${ctx}/dblz/lzhdjl/save.do" >
 <div class="row">
	<input type="hidden" name="id" value="${ob.id}" />
	<input type="hidden" name="lzid" value="${ob.lzid}" />
	<input type="hidden" name="rdname" value="${ob.rdname}" />
	<input type="hidden" name="xxzt" value="${ob.xxzt}" />

	<div class="row" style="margin-top: 10px">
		<label class="span2 " for="">标题</label><div class="span4">
			<input name="lzbt" value="${ob.lzbt}" type="text"  maxlength="100" validate="{required:true}"/>
		</div>
		
		<label class="span2 " for="">人大代表姓名</label><div class="span4">
			<input name="rdrealname" value="${ob.rdrealname}" type="text"  maxlength="30" validate="{required:true}"/>
		</div>
		
		<label class="span2 " for="">履职类型</label><div class="span4">
			<g:select name="lzlx" sectionname="履职类型" defaultName="请选择..." value="${ob.lzlx}"/>
		</div>
		
			<label class="span2 " for="">履职日期</label>
			<div class="span4" >
				<input name="lzrq" value="${ob.lzrq}" placeholder="选择日期..." type="text"  maxlength="30" validate="{required:true}" class="add-on"/>
			</div>
		
		<label class="span2 " for="">发布人姓名</label><div class="span4">
			<input name="fbrxm" value="${ob.fbrxm}" type="text"  maxlength="30" validate="{required:true}"/>
		</div>
		
		<label class="span2 " for="">活动组织单位</label><div class="span4">
			<input name="hdzzdw" id="hdzzdw" value="${ob.hdzzdw}" type="text" maxlength="30"  validate="{required:true}"/>
		</div>
		
		<label class="span2 " for="">履职内容</label><div class="span10">
			<textarea  name="lznr" style="width:702px;height:150px" validate="{required:true}">${ob.lznr}</textarea>
		</div>
		
		<label class="span2 " for="" style="margin-top: 20px">附件列表</label>
		<div class="span10" id="filelist" style="margin-top: 20px">
			<c:forEach var="element" items="${fileList}">
				<label><a>${element.filename}  <input type="hidden" name="filename" value="${element.filename}"/><input type="hidden" name="fileurl" value="${element.fileurl}"/></a></label>
			</c:forEach>
		</div>
	</div>
</form>

