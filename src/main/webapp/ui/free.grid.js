/*


*/
;(function ($) {
	var local_opts ;
    $.fn.reload=function (){
        var $this =$(this);
    	getData(local_opts,$this);
	}

    function deleteData(opts,$this,id){
    	 var url;
    	 $.each(opts.actions,function(i,o){
         	if(o.deletable){
         		url = o.url;
         	}
         });
    	 if(confirm("是否将此信息删除?")){
             $.ajax({
          	   type: "POST",
          	   url: url,
          	   data: {id:id},
          	   success: function(data){
          		   if (data='ok'){
                       getData(opts,$this);
          		   }
          	   }
             });
    	 }
    }
	//ajax得到数据并填充
	function getData(opts,$this){ 
    	//$("#g1 tbody",$this).html("");//清空原数据
		var searchOpts = $this.find("form").serializeArray();
        var tbody=""
        var parame = {page:opts.page,pagesize:opts.pagesize};
        //从json读取的字段
        if (searchOpts){
	        $.each(searchOpts,function(i,o){
	        	parame[o.name]=o.value;
	        });
        }
        $.ajax({
     	   type: "POST",
     	   url: opts.url,
     	   data: parame,
     	   success: function(data){
     		  var o;
     		   for(var i=0;i<opts.pagesize;i++){
     			o=data.list[i];
               	$.each(opts.filds,function(j,f){
               		if (data.list[i]){
               			var tdValue="";
               			if(o[f.name]){
               				tdValue = o[f.name];
               			}
                   		$this.find(".fGridData tbody").children().eq(i).children().eq(j).text(tdValue);
                   		
                   		// 显示按钮
           				if (f.name==''){
           	       			var temp=""
        	       			 $.each(opts.actions,function(i,to){ 
        	    	         		temp+= "<a class='freeGrid_editBut"+i+"' href='javascript:void(0);' id='"+o.id+"'>"+to.name+"</a> ";
        	       	         });
        	           		$this.find(".fGridData tbody").children().eq(i).children().eq(j).html(temp);
           				}
               		}else{
               			//如果数组中
                   		$this.find(".fGridData tbody").children().eq(i).children().eq(j).html(" ");
               		}
                  });
               };

 			 $.each(opts.actions,function(i,to){
 				$(".freeGrid_editBut"+i).click(to.fun);
 			 });
             $("#g1",$this).append(tbody);
             if(data.pager.pageNumber==1){//第一页时
            	 $(".freeGrid_firstPage",$this).addClass('freeGrid_firstPage_disable');
            	 $(".freeGrid_prePage",$this).addClass('freeGrid_prePage_disable');
             }else{
            	 $(".freeGrid_firstPage",$this).removeClass('freeGrid_firstPage_disable');
            	 $(".freeGrid_prePage",$this).removeClass('freeGrid_prePage_disable');
             }
             if(data.pager.pageNumber==data.pager.pageCount){
            	 $(".freeGrid_lastPage",$this).addClass('freeGrid_lastPage_disable');
            	 $(".freeGrid_nextPage",$this).addClass('freeGrid_nextPage_disable');
             }else{
            	 $(".freeGrid_lastPage",$this).removeClass('freeGrid_lastPage_disable');
            	 $(".freeGrid_nextPage",$this).removeClass('freeGrid_nextPage_disable');
             }
        	 $(".freeGrid_totalPages",$this).text(data.pager.pageCount);
        	 $(".freeGrid_page",$this).text(data.pager.pageNumber);
        	 $(".freeGrid_totalCount",$this).text(data.pager.recordCount);

     	   }
     	});
	}
    $.fn.grid = function (options) {
        var defualts = {page:1,pagesize:10,title:''};
        var opts = $.extend({}, defualts, options);
        local_opts = opts;
        var $this =$(this);
        var th="<thead>";
        $.each(opts.filds,function(i,o){
        	th+="<th style='width:"+o.width+"px' class='tab_grey_header'>"+o.display+"</th>";
        });
        th+="</thead>";
        for (var j = 0; j < opts.pagesize; j++) {
        	th+="<tr>";
        	$.each(opts.filds,function(i,o){
            	th+="<td style='height:28px' ></td>";
            });
        	th+="</tr>";
        }
        
        var table="<table class='tab fGridData' id='g1' >"+
        th+
        "</table>";
        var pagebar='<table width="100%" border="0" cellspacing="0" cellpadding="0">'
            +'<tr>'
        +'<td background="'+opts.imagesrc+'/ui/images/cen/rig/footer_bg.jpg" width="10px" align="left" valign="top"><img src="'+opts.imagesrc+'/ui/images/cen/rig/footer_bg_lef.jpg" width="10" height="46" /></td>'
        +'<td align="center" background="'+opts.imagesrc+'/ui/images/cen/rig/footer_bg.jpg">'
        +'<a class="freeGrid_firstPage" >首页</a>'
        +'<a class="freeGrid_prePage" >&nbsp;</a><a class="freeGrid_page" ></a>'
        +'<a class="freeGrid_nextPage" >&nbsp;</a>'
        +'<a class="freeGrid_lastPage" >尾页</a>&nbsp;<span class="text_grey14">共</span><span class="text_red14 freeGrid_totalCount"></span><span class="text_grey14 ">条记录&nbsp;&nbsp;'
        +'共</span><span class="text_red14 freeGrid_totalPages"></span><span class="text_grey14">页&nbsp;&nbsp;跳转到：</span>'
        +'<input name="" type="text" class="but_text" /><a class="but_go" >Go</a></td>'
        +'<td background="'+opts.imagesrc+'/ui/images/cen/rig/footer_bg.jpg" width="10px" align="right" valign="top"><img src="'+opts.imagesrc+'/ui/images/cen/rig/footer_bg_rig.jpg" width="10" height="46" /></td>'
        +'</tr>'
        +'</table>';
        
        var ul='<ul>'
        +'<table width="100%" border="0" cellspacing="0" cellpadding="0">'
        +'<tr>'
        +'  <td width="8px" align="left" valign="top" background="'+opts.imagesrc+'/ui/images/cen/rig/header_bg.jpg" ;><img src="'+opts.imagesrc+'/ui/images/cen/rig/header_bg_lef.jpg" width="8" height="35" /></td>'
        +'  <td width="98%" align="left" background="'+opts.imagesrc+'/ui/images/cen/rig/header_bg.jpg" ;><img src="'+opts.imagesrc+'/ui/images/cen/rig/ico_01.png" width="16" height="16" align="absmiddle" />&nbsp;&nbsp;<span class="text_white16">'+opts.title+'</span></td>'
        +'  <td width="8px" align="right" valign="top" background="'+opts.imagesrc+'/ui/images/cen/rig/header_bg.jpg" ;><img src="'+opts.imagesrc+'/ui/images/cen/rig/header_bg_rig.jpg" width="8" height="35" /></td>'
        +'</tr>'
        +'</table>'
        +'';
        $this.append(ul);
        var form = $this.find('form');
        $this.find('ul').append(form);
        $this.find('ul').append(table);
        $this.find('ul').append(pagebar);
        getData(opts,$this);
        
        $(".freeGrid_nextPage",$this).click(function(){
        	if($(this).attr('class').indexOf('freeGrid_nextPage_disable')>0)
        		return false;
        	opts.page+=1;
            getData(opts,$this);
        });
        $(".freeGrid_prePage",$this).click(function(){
        	if($(this).attr('class').indexOf('freeGrid_prePage_disable')>0)
        		return false;
        	opts.page-=1;
            getData(opts,$this);
        });
        $(".freeGrid_firstPage",$this).click(function(){
        	if($(this).attr('class').indexOf('freeGrid_firstPage_disable')>0)
        		return false;
        	
        	opts.page=1;
            getData(opts,$this);
        });
        $(".freeGrid_lastPage",$this).click(function(){
        	if($(this).attr('class').indexOf('freeGrid_lastPage_disable')>0)
        		return false;
        	
        	opts.page=$(".freeGrid_totalPages").text();
            getData(opts,$this);
        });

        $this.find("form .fGridSearch").click(function(){
            getData(opts,$this);
        })
        return $this;
    };
})(jQuery);