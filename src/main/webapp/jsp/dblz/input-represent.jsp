<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script src="${ctx}/ui/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>
<link href="${ctx}/ui/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<script>



$(function(){
	$.metadata.setType("attr", "validate");
	$("#inputForm").validate({
		rules:{
			loginname:{
				isRepeat:""
			}
		},
		submitHandler:function(){ 
          	$.ajax({
       		   type: "POST",
       		   url: $("#inputForm").attr("action"),
       		   data: $("#inputForm").serialize(),
      			error:Free.ajaxError,
       		   success: function(data){
       	     	if(data=='ok'){
       	     		freeAlert("保存成功！",function(){
	       	     		if(f_query){
	       	    	 		f_query();//重新加载grid
	       	    	 	}
	       	    	 	Free.closeAllDialog();
       	     		});  
       	     	}else{
       	     		alert("保存失败");
       	     	}
       		}
         });
        }
	});
	//Free.validateSubmitAndClose($("#inputForm"));
	
	
	
	$("#freeLookup").click(function(){
		lookupDialog = new Dialog({type:'url',value:'${ctx}/jsp/system/tree-department-select-dbt.jsp?lookupId='+$(this).attr("lookupId")+"&lookupName="+$(this).attr("lookupName")},
				{modal:true});
		lookupDialog.show();
	});
	
	$("#freeLookup1").click(function(){
		var deptid = $("#zyzid").val();
		lookupDialog = new Dialog({type:'url',value:'${ctx}/jsp/system/tree-department-select-multi-zyz.jsp?lookupId='
							+$(this).attr("lookupId")+"&lookupName="+$(this).attr("lookupName")+"&deptid="+deptid},
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
             $("#filename").html(file.name+"<a class='jq_fj' href='javascript:void(0);' onClick='deleteTempAccessory();' >删除</a>");
             $("#filename_hidden").val(file.name);
             $("#zp_hidden").val(data);
        }
    }); 
});


jQuery.validator.addMethod("isRepeat", function(value, element) { 

	return checkRepeat(value,$("#userid").val());
}, "该用户名已存在！");

function checkRepeat(loginname,id){
	var bool = false;
	if(id == "" || id == null){
		$.ajax({
			   type: "POST",
			   async: false,
			   url: '${ctx}/system/user/checkRepeak-loginname.do',
			   data: {'id':id,'loginname':loginname},
			   success: function(data){
		     	if(data=='true'){
		     		bool = false;
		     	}else{
		     		bool = true;
		     	}
		}});
	}else{
		$.ajax({
			   type: "POST",
			   async: false,
			   url: '${ctx}/system/user/checkRepeak-loginname-id.do',
			   data: {'id':id,'loginname':loginname},
			   success: function(data){
		     	if(data=='true'){
		     		bool = false;
		     	}else{
		     		bool = true;
		     	}
		}});
	}
	 
	 return bool;
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
   	   url: '${ctx}/dblz/representinfo/ajax_deleteFile.do;',
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
<form class="span12" id="inputForm"  action="${ctx}/dblz/representinfo/save.do" >
<input type="hidden" name="id" id="userid" value="${ob.id}" />
<input type="hidden" name="representflag" value="1"/>
<input type="hidden" name="sfqy" value="1"/>

<div class="row">
<fieldset>
	 <legend>个人信息</legend><br/>
	 <c:if test="${ob.zp != null}">
	 	<label class="span2 " for="">照片：</label>
		<div class="span4">
			<img src="/pic/${ob.zp}" style="width:150px;height:220px"/>
		</div>
	 </c:if>
	
	
	<label class="span2 " for="">姓名：</label>
	<div class="span4">
		<input name="xm" value="${ob.xm}" type="text" label="姓名" validate="{required:true}"/>
	</div>
	
	<label class="span2 " for="">性别：</label>
	<div class="span4">
		<g:select name="xb" sectionname="性别" defaultName="请选择..." value="${ob.xb}" />
	</div>
	
	<label class="span2 " for="" >出生日期：</label>
	<div class="span4">
		<input name="csrq" value="${ob.csrq}" placeholder="选择日期..." type="text"  maxlength="30"/>
	</div>
	
	
	<label class="span2 " for="">民族：</label>
	<div class="span4">
		<g:select name="mz" sectionname="民族" defaultName="请选择..." value="${ob.mz}" />
	</div>
	
	<label class="span2 " for="">文化程度：</label>
	<div class="span4">
		<input name="whcd" type="text"  value="${ob.whcd}" />
	</div>
	
	<label class="span2 " for="">党派：</label>
	<div class="span4">
		<input name="zzmm" type="text"  value="${ob.zzmm}" />
	</div>	
	
	<legend>身份信息</legend><br/>
	
	<label class="span2 " for="">代表登录名：</label>
	<div class="span4">
		<input name="loginname" id="loginname" placeholder="必添项" value="${ob.loginname}" type="text" validate="{required:true}"  />
	</div>
	
	<label class="span2 " for="">代表团：</label>
	<div class="span4">
		<input name="dbtid" id="dbtid" value="${ob.dbtid}" type="hidden"  />
		<div class="input-append">
		 	<input class="input-medium"  name="dbt" id="dbt" readonly="readonly"  value="${ob.dbt}" type="text"  validate="{required:true}"/>
			<a id="freeLookup" class="btn" lookupId="dbtid" lookupName="dbt" href="javascript:void(0);">选择</a>
		</div>
	</div>
	
	
	<label class="span2 " for="">代表证号：</label>
	<div class="span4">
		<input name="dbzh" value="${ob.dbzh}" type="text" label="代表证号" />
	</div>
	
	<label class="span2 " for="">职业：</label>
	<div class="span4">
		<input name="zy" value="${ob.zy}" type="text" label="职业" />
	</div>
	
	<label class="span2 " for="">职务：</label>
	<div class="span4">
		<input name="zw" value="${ob.zw}" type="text" label="职务" />
	</div>
	
	<label class="span2 " for="">手机：</label>
	<div class="span4">
		<input name="yddh" value="${ob.yddh}" placeholder="必添项" type="text" label="联系电话" validate="{required:true}"/>
	</div>
	
	<label class="span2 " for="">通讯地址：</label>
	<div class="span4">
		<input name="dwdz" value="${ob.dwdz}" type="text" label="通讯地址" />
	</div>
	
	<label class="span2 " for="">邮箱：</label>
	<div class="span4">
		<input name="dzyz" value="${ob.dzyz}" type="text" label="邮箱" />
	</div>
	
	<label class="span2 " for="">专业组：</label>
	<input name="zyzid_group" id="zyzid" value="${ob.zyzid_group}" type="hidden"  />
	   <div class="input-append span10">
		 	<input class="input-medium"  name="zyzname_group" id="zyzname" readonly="readonly"  value="${ob.zyzname_group}" type="text"  />
			<a id="freeLookup1" class="btn" lookupId="zyzid" lookupName="zyzname" href="javascript:void(0);">选择</a>
		</div>
	
	<label class="span2 " for="">照片：</label>
	<div class="span10">
		<input id="file_upload" name="file_upload" type="file" multiple="true"/><div id="filename">${ob.filename}</div>
		<input type="hidden" name="zp" id="zp_hidden" value="${ob.zp}"/>
		<input type="hidden" name="filename" id="filename_hidden" value="${ob.filename}"/>
		<c:if test="${!empty ob.filename}">
			<a class="jq_fj" href='javascript:void(0);' onClick='deleteAccessory(${ob.id});' >删除</a>
		</c:if>
	</div>
	
	
	<label class="span2 " for="">备注：</label>
	<div class="span10" >
		<textarea  name="memo" style="width:702px;height:100px" label="备注">${ob.memo}</textarea>
	</div>
	
	
	
	
	<div class="span12 text-center" style="margin-top: 10px;margin-bottom: 20px">
		<button class="btn btn-success" id="free_submit" type="submit"><i class="icon-ok icon-white" ></i> 保存 </button>
		&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn" type="button" onclick="Free.closeThisDialog();"> <i class="icon-ban-circle" ></i>关闭 </button>
	</div>
</fieldset>
</div>

</form>
