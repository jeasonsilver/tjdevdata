<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/import-basic-js-css.jsp"%>
<script>

$(function(){
	
	$.metadata.setType("attr", "validate");
	$("#inputForm").validate({
		rules:{
			loginname:{
				isRepeat:""
			},
			sf:{
				 required: true
			}
		}
	});

	$("#freeLookup").click(function(){
		var deptid = $("#deptid").val();
		lookupDialog = new Dialog({type:'url',value:'${ctx}/jsp/system/tree-department-select-multi.jsp?lookupId='
								+$(this).attr("lookupId")+"&lookupName="+$(this).attr("lookupName")+"&deptid="+deptid},
				{modal:true},{height:300});
		lookupDialog.show();
	});
	$("#freeLookup2").click(function(){
		lookupDialog = new Dialog({type:'url',value:'${ctx}/jsp/system/tree-department-select.jsp?lookupId='+$(this).attr("lookupId")+"&lookupName="+$(this).attr("lookupName")},
				{modal:true});
		lookupDialog.show();
	});
	setTimeout(function(){
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
	
	},10);
	
	
	//提交期间
	var sf = "${ob.representflag}";
	var arr=new Array();
	arr=sf.split(';');
	$("input[name='sf']").each(function(){
		for(var i=0;i<arr.length;i++)
		{
			if($(this).val() == arr[i]){
				$(this).attr("checked",'checked');
			}
		}
		
	});
	
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
</script>
</head>
<body>
<form  class="span12" id="inputForm"  action="${ctx}/system/user/save.do" method="post">
<input type="hidden" name="id" id="userid" value="${ob.id}" />
<input type="hidden" name="representflag" value="${ob.representflag}" />
  <div class="row">
     <legend>个人信息</legend><br/>
    <label class="span2 " for="">登录名</label><div class="span4">
		<input name="loginname"  id="loginname" placeholder="必添项" value="${ob.loginname}" type="text" validate="{required:true}"  />
	</div>
		<label class="span2 " for="">部门</label><div class="span4">
		<input name="deptid_group" id="deptid" value="${ob.deptid_group}" type="hidden" />
	   <div class="input-append">
		 	<input class="input-medium"  name="deptname_group" id="deptname" readonly="readonly"  value="${ob.deptname_group}" type="text" />
			<a id="freeLookup" class="btn" lookupId="deptid" lookupName="deptname" href="javascript:void(0);">选择</a>
		</div>
	</div>
	<label class="span2 " for="">发布部门</label>
	<div class="span4">
		<input name="fbdwid" id="fbdwid" value="${ob.fbdwid}" type="hidden"  />
	   <div class="input-append">
		 	<input class="input-medium"  name="fbdw" id="fbdw" readonly="readonly"  value="${ob.fbdw}" type="text"  />
			<a id="freeLookup2" class="btn" lookupId="fbdwid" lookupName="fbdw" href="javascript:void(0);">选择</a>
		</div>
	</div>
	
		<label class="span2 " for="">代表证号</label><div class="span4">
		<input name="dbzh" value="${ob.dbzh}" type="text" label="代表证号" />
	</div>
	
		<label class="span2 " for="">姓名</label><div class="span4">
		<input name="xm" value="${ob.xm}" placeholder="必添项"  type="text" label="姓名" validate="{required:true}"/>
	</div>
	
		<label class="span2 " for="">性别</label><div class="span4">
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
	
		<label class="span2 " for="">文化程度</label><div class="span4">
		<input name="whcd" value="${ob.whcd}" type="text" label="文化程度" />
	</div>
	
	
		<label class="span2 " for="">政治面目(党派)</label><div class="span4">
		<input name="zzmm" value="${ob.zzmm}" type="text" label="政治面目" />
	</div>
	
		<label class="span2 " for="">代表团</label><div class="span4">
		<input name="dbt" value="${ob.dbt}" type="text" label="代表团" />
	</div>
	
		<label class="span2 " for="">文件送达地</label><div class="span4">
		<input name="wjsdd" value="${ob.wjsdd}" type="text" label="文件送达地" />
	</div>
	
		<label class="span2 " for="">技术职称</label><div class="span4">
		<input name="jszc" value="${ob.jszc}" type="text" label="技术职称" />
	</div>
	
		<label class="span2 " for="">职业</label><div class="span4">
		<input name="zy" value="${ob.zy}" type="text" label="职业" />
	</div>
	
		<label class="span2 " for="">职务</label><div class="span4">
		<input name="zw" value="${ob.zw}" type="text" label="职务" />
	</div>
	
		<label class="span2 " for="">单位名称</label><div class="span4">
		<input name="dwmc" value="${ob.dwmc}" type="text" label="单位名称" />
	</div>
	
		<label class="span2 " for="">单位电话</label><div class="span4">
		<input name="dwdh" value="${ob.dwdh}" type="text" label="单位电话" />
	</div>
	
		<label class="span2 " for="">单位地址</label><div class="span4">
		<input name="dwdz" value="${ob.dwdz}" type="text" label="单位地址" />
	</div>
	
		<label class="span2 " for="">单位邮编</label><div class="span4">
		<input name="dwmb" value="${ob.dwmb}" type="text" label="单位邮编" />
	</div>
	
		<label class="span2 " for="">电子邮件</label><div class="span4">
		<input name="dzyz" value="${ob.dzyz}" type="text" label="电子邮件" />
	</div>
	
		<label class="span2 " for="">传真</label><div class="span4">
		<input name="cz" value="${ob.cz}" type="text" label="传真" />
	</div>
	
		<label class="span2 " for="">家庭住址</label><div class="span4">
		<input name="jtzz" value="${ob.jtzz}" type="text" label="家庭住址" />
	</div>
	
		<label class="span2 " for="">家庭邮编</label><div class="span4">
		<input name="jtyb" value="${ob.jtyb}" type="text" label="家庭邮编" />
	</div>
	
		<label class="span2 " for="">家庭电话</label><div class="span4">
		<input name="jtdh" value="${ob.jtdh}" type="text" label="家庭电话" />
	</div>
	
		<label class="span2 " for="">移动电话</label><div class="span4">
		<input name="yddh" value="${ob.yddh}" type="text" label="移动电话" validate="{required:true}"/>
	</div>
	
		<label class="span2 " for="">是否调出或逝世</label><div class="span4">
		<input name="sfdc" value="${ob.sfdc}" type="text" label="是否调出或逝世" />
	</div>
	<p >
		<label class="span2 " for="">备注</label><div class="span10">
		<input name="memo" value="${ob.memo}" type="text" label="备注" />
	</div>
	
		<label class="span2 " for="">照片</label>
		
	<div class="span10">
		<input id="file_upload" name="file_upload" type="file" multiple="true"/><div id="filename">${ob.filename}</div>
		<input type="hidden" name="zp" id="zp_hidden" value="${ob.zp}"/>
		<input type="hidden" name="filename" id="filename_hidden" value="${ob.filename}"/>
		<c:if test="${!empty ob.filename}">
			<a class="jq_fj" href='javascript:void(0);' onClick='deleteAccessory(${ob.id});' >删除</a>
		</c:if>
	</div>
	
		<label class="span2 " for="">是否启用</label><div class="span4">
		<input name="sfqy" value="${ob.sfqy}" type="text" label="是否启用" />
	</div>
	
	<label class="span2 " for="">用户身份</label><div class="span4">
		<input type="checkbox" name="sf" value="0"/>工作人员
		<input type="checkbox" name="sf" value="1"/>管理人员
		<input type="checkbox" name="sf" value="2"/>外勤人员
	</div>
	
	<c:if test="${readonly!=true}">
    <div class="span12 text-center">
	    <div class="form-actions">
		  <button id="free_submit" type="submit" class="btn btn-success">保存</button>
		  <button type="reset" class="btn">重置</button>

		</div>
    </div>
    </c:if>
  </div>
</form>
</body>	

</html>
