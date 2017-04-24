/**
 * Free UI 的核心共用方法
 *
 */
var Free={
	ajaxError:function(xhr, ajaxOptions, thrownError){
		if (Dialog){
			new Dialog("<div>Http status: " + xhr.status + " " + xhr.statusText + "</div>" 
					+ "<div>ajaxOptions: "+ajaxOptions + "</div>"
					+ "<div>thrownError: "+thrownError + "</div>"
					+ "<div>"+xhr.responseText+"</div>",{title:"程序出错",width:1000,height:360}).show();
		}else{
			alert("Http status: " + xhr.status + " " + xhr.statusText + "\najaxOptions: " + ajaxOptions + "\nthrownError:"+thrownError + "\n" +xhr.responseText);	
		}
		
	},
	ajax:function(par){
		var as = true;
		if (par.async=="false"){
			as = false;
		}
		$.ajax({
			   type: "POST",
			   url: par.url,
			   data: par.data,
			   success: par.success,
			   async: as,
			   error:Free.ajaxError
		   });
	},
	//关闭当前的对话框，方便使用
	closeThisDialog:function(){	
		//查询当前所有dialog并取dialogid最大的一个，也就是最前面的dialog并关闭
		var did =0;
		$('.dialog').each(function(i){
			if(did < (this.id.substring(7)*1))
				did = (this.id.substring(7)*1);
		 })
		$('#dialog-'+did).fadeOut('slow', function(){$(this).remove();});
	    $('#dialog-'+did+"-overlay").fadeOut('slow', function(){$(this).remove();});
	},
	//关闭所有对话框，方便使用
	closeAllDialog:function(){	
		//查询当前所有dialog并取dialogid最大的一个，也就是最前面的dialog并关闭
		var did =0;
		$('.dialog').each(function(i){
			if(did < (this.id.substring(7)*1))
				did = (this.id.substring(7)*1);
			
			$('#dialog-'+did).fadeOut('slow', function(){$(this).remove();});
		    $('#dialog-'+did+"-overlay").fadeOut('slow', function(){$(this).remove();});
		 })
	},
	//字符串格式化函数String.format
	replace : function(src) {
		if (arguments.length == 0)
			return null;
		var args = Array.prototype.slice.call(arguments, 1);
		return src.replace(/\{(\d+)\}/g, function(m, i) {
			return args[i];
		});
	},
	validateSubmitAndClose:function(inputForm,msg){
		if (!msg){
			msg="保存成功";
		}
        $.metadata.setType("attr", "validate");
		inputForm.validate({
		        submitHandler:function(form){ 
		          	$.ajax({
		       		   type: "POST",
		       		   url: inputForm.attr("action"),
		       		   data: inputForm.serialize(),
		      			error:Free.ajaxError,
		       		   success: function(data){
		       	     	if(data=='ok'){
		       	     		freeAlert(msg,function(){
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
	},
	getCodeNames:function(){
		var arg="";
		 for (var i = 1; i < arguments.length; i++) {
			 arg += "&sectionName=" + arguments[i];
         }
		Free.ajax({async:"false",
			url:arguments[0]+"/system/code/getNames.do?a=a"+arg,
			success:function(data){
				Free.CodeNames=data;
				console.info(data);
			}
		});
	},
	CodeNames:{},
	getCodeName:function(sectionName,code){
		var res;
		$.each(Free.CodeNames, function(i,val){   
		      if(i==sectionName){
		    		$.each(val, function(ii,ival){   
		  		      if(ii==code){
		  			     res=ival;
		  		    }   
		  		});
		      }   
		});
		return res;
	},
	closewindow:function(){
		window.close();
	}
};
//调用： 
//var time1 = new Date().Format("yyyy-MM-dd");
//var time2 = new Date().Format("yyyy-MM-dd hh:mm:ss");  
Date.prototype.Format = function (fmt) { 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}