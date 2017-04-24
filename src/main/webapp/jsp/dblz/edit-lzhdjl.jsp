<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>


	<script type="text/javascript">
		var count = 1;
		$(function(){
			Free.validateSubmitAndClose($("#inputForm"));
				
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
				
				$('#file_upload').uploadify({  
	                'debug'         : false,  
	                'auto'          : false,             //是否自动上传,  
	                'buttonClass'   : 'haha',           //按钮辅助class  
	                'buttonText'    : '选择附件',       //按钮文字  
	                'height'        : 30,               //按钮高度  
	                'width'         : 100,              //按钮宽度  
	                'checkExisting' : 'check-exists.php',//是否检测图片存在,不检测:false  
	                'fileObjName'   : 'files',           //默认 Filedata, $_FILES控件名称  
	                'fileSizeLimit' : '1024000KB',          //文件大小限制 0为无限制 默认KB  
	                'fileTypeDesc'  : 'All Files',       //图片选择描述  
	                'fileTypeExts'  : '*.xls;*.xlsx;*.dbf;*.zip;',//文件后缀限制 默认：'*.*'  
	                'formData'      : {'someKey' : 'someValue', 'someOtherKey' : 1},//传输数据JSON格式  
	                //'overrideEvents': ['onUploadProgress'],  // The progress will not be updated  
	                //'progressData' : 'speed',             //默认percentage 进度显示方式  
	                'queueID'       : 'queue',              //默认队列ID  
	                'queueSizeLimit': 20,                   //一个队列上传文件数限制  
	                'removeCompleted' : true,               //完成时是否清除队列 默认true  
	                'removeTimeout'   : 3,                  //完成时清除队列显示秒数,默认3秒  
	                'requeueErrors'   : false,              //队列上传出错，是否继续回滚队列  
	                'successTimeout'  : 5,                  //上传超时  
	                'muti':true,
	                'uploadLimit'     : 99,                 //允许上传的最多张数  
	                'swf'  : '${ctx}/ui/uploadify/scripts/uploadify.swf', //swfUpload  
	                'uploader': '${ctx}/dblz/lzhdjl/ajax_upload.do;jsessionid=<%=session.getId()%>', //服务器端脚本  
	  
	  
	                //修改formData数据  
	                'onUploadStart' : function(file) {  
	                    //$("#file_upload").uploadify("settings", "someOtherKey", 2);  
	                },  
	                //删除时触发  
	                'onCancel' : function(file) {  
	                    //alert('The file ' + file.name + '--' + file.size + ' was cancelled.');  
	                },  
	                //清除队列  
	                'onClearQueue' : function(queueItemCount) {  
	                    //alert(queueItemCount + ' file(s) were removed from the queue');  
	                },  
	                //调用destroy是触发  
	                'onDestroy' : function() {  
	                    //alert('我被销毁了');  
	                },  
	                //每次初始化一个队列是触发  
	                'onInit' : function(instance){  
	                    //alert('The queue ID is ' + instance.settings.queueID);  
	                },  
	                //上传成功  
	                'onUploadSuccess' : function(file, data, response) {  
	                	$("#filelist").append('<label>'+file.name +' <a href="">删除</a><input type="hidden" name="filename" value="'+file.name +'"/><input type="hidden" name="fileurl" value="'+data +'"/></label>');
	                },  
	                //上传错误  
	                'onUploadError' : function(file, errorCode, errorMsg, errorString) {  
	                	$.ligerDialog.error('文件名：'+file.name +'，上传失败！'); 
	                },  
	                //上传汇总  
	                'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {  
	                    $('#progress').html(totalBytesUploaded + ' bytes uploaded of ' + totalBytesTotal + ' bytes.');  
	                },  
	                //上传完成  
	                'onUploadComplete' : function(file) {
	                	
	                },  
	               
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
    width: 686px;  
}  
</style>

<form class="span12"  method="post" id="inputForm" action="${ctx}/dblz/lzhdjl/save.do" >
 <div class="row">
	<input type="hidden" name="id" value="${ob.id}" />
	<input type="hidden" name="lzid" value="${ob.lzid}" />
	<input type="hidden" name="rdname" value="${ob.rdname}" />
	<input type="hidden" name="xxzt" value="${ob.xxzt}" />

	<div class="row" style="margin-top: 10px">
		<label class="span2 " for="">标题<font color="red">*</font></label><div class="span4">
			<input name="lzbt" value="${ob.lzbt}" type="text"  maxlength="100" validate="{required:true}"/>
		</div>
		
		<label class="span2 " for="">人大代表姓名<font color="red">*</font></label><div class="span4">
			<div class="input-append">
				<input name="rdrealname" id="rdrealname" value="${ob.rdrealname}" type="text"  maxlength="30" validate="{required:true}"  class="input-medium" readonly="readonly" />
				<a id="freeLookup_rdrealname" class="btn freeLookup" lookupId="users" lookupName="rdrealname"  href="javascript:void(0);">选择</a>
			</div>
		</div>
		
		<label class="span2 " for="">履职类型<font color="red">*</font></label><div class="span4">
			<g:select name="lzlx" sectionname="履职类型" defaultName="请选择..." value="${ob.lzlx}"/>
		</div>
		
			<label class="span2 " for="">履职日期<font color="red">*</font></label>
			<div class="span4" id="datetimepicker" >
				<input name="lzrq" value="${ob.lzrq}" placeholder="选择日期..." type="text"  maxlength="30" validate="{required:true}"/>
				<span class="add-on"><i class="icon-th"></i>
			</div>
		
		<label class="span2 " for="">发布人姓名<font color="red">*</font></label><div class="span4">
			<input name="fbrxm" value="${ob.fbrxm}" type="text"  maxlength="30" validate="{required:true}"/>
		</div>
		
		<label class="span2 " for="">活动组织单位<font color="red">*</font></label><div class="span4">
			<input name="hdzzdwid" id="hdzzdwid" value="${ob.hdzzdwid}" type="hidden"  />
			<div class="input-append">
			<input class="input-medium" name="hdzzdw" id="hdzzdw" value="${ob.hdzzdw}" type="text" maxlength="30" readonly="readonly" validate="{required:true}"/>
			<a id="freeLookup" class="btn" lookupId="hdzzdwid" lookupName="hdzzdw" href="javascript:void(0);">选择</a>
			</div>
		</div>
		
		<label class="span2 " for="" style="margin-top: 50px">履职内容<font color="red">*</font></label><div class="span10">
			<textarea  name="lznr" style="width:702px;height:150px" validate="{required:true}">${ob.lznr}</textarea>
		</div>
		
		<label class="span2 " style="margin-top: 30px" for="">上传附件</label>
		<div class="span10" style="margin-top: 10px">
			<div id="queue"></div>  
       		 <input id="file_upload" name="file_upload" type="file" multiple="true"/>  
		</div>
		
		<label class="span2 " for="">附件列表</label>
		<div class="span10" id="filelist">
			<c:forEach var="element" items="${fileList}">
				<label>${element.filename}  <a href="">删除</a><input type="hidden" name="filename" value="${element.filename}"/><input type="hidden" name="fileurl" value="${element.fileurl}"/></label>
			</c:forEach>
		</div>
		
		<div class="span12 text-center" style="margin-top: 20px;margin-bottom: 20px">		
			<button class="btn btn-success" id="free_submit" type="button" onclick="$('#file_upload').uploadify('upload', '*');"> <i class="icon-upload icon-white" ></i>上传附件 </button>
			<button class="btn btn-success" id="free_submit" type="submit"><i class="icon-ok icon-white" ></i> 保存 </button>
		</div>
	</div>
</form>

