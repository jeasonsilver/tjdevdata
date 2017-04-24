/*
 * 
 * 目前replace没写好，只直接一个对象的json required,如果maxlength写在input上
 * 
*/
;(function ($) {
	var msg="";
	//验证表单
	function validate($this){
        $("input",$this).each(function(i,o){
    		if ($(o).attr("validate")){
    			var s =$(o).attr('validate');
    			s =s.replace("{","{\"");
    			s =s.replace(",",",\"");
    			s =s.replace(":","\":");
    			var v =  $.parseJSON(s);//string转json,必须加双引号
    			if(v.required){
    				if ($(o).val()==""){
    					msg+=$(o).attr('label')+"不能为空\n";
    				}
    			}
    		}
    	});
    	if (msg!=""){
    		alert(msg);
    		msg="";
    		return false;//防止submit提交
    	}
    	return true;
	}
    $.fn.form = function (options) {
        var defualts = {validate:true};
        var opts = $.extend({}, defualts, options);
        var $this =$(this);
        

        $.metadata.setType("attr", "validate");
        /*
        var v = $this.validate({
            //调试状态，不会提交数据的
            debug: true,
            errorPlacement: function (lable, element)
            {
                element.css("border-color","red");
                element.css("border-width","1px 1px 1px 10px");
                lable.appendTo(element.parent());  
            },
            success: function (lable, element)
            {	
            },
            submitHandler: function ()
            {
            }
        });
        */
        var cl = function(){
	    	$.ajax({
	    		   type: "POST",
	    		   url: $this.attr("action"),
	    		   data: $this.serialize(),
				   error:Free.ajaxError,
	    		   success: function(data){
	    	     	if(data=='ok'){
	    	     		alert("保存成功!!!");
	    	     		opts.callback();
	    	     	}
	    		}
	    	});
	    	return false;//不能真提交	
        };

    	//console.info($this.find(":submit"));
       // $this.find(":submit").click(cl);
       // console.info($this.find(":submit").length);
       // console.info($this.find(".freeGrid_submit").length);
        $this.find("#free_submit").click(cl);
    };
    
    function a(){
    	alert("232");
    }

    $.fn.validate = function (options) {
        var defualts = {validate:true};
        var opts = $.extend({}, defualts, options);
        var $this =$(this);
        return validate($this)
    };
})(jQuery);