//右下角弹窗JS代码
var j = jQuery.noConflict();
var showadbox = function(boxad11id, tipheight) {
        if(!boxad11id) boxad11id='boxad11';
        if(!tipheight) tipheight=150;

        var objclientheigth = document.getElementById(boxad11id).clientHeight;
        j('#'+boxad11id).height(objclientheigth+2);
        if(objclientheigth < tipheight) { 
                setTimeout(function(){showadbox(boxad11id, tipheight)}, 5);
        }
}

var closead11adbox = function(boxad11id) {
        if(!boxad11id) boxad11id='boxad11';
        var objclientheigth = document.getElementById(boxad11id).clientHeight;
        /*
        j('#'+boxad11id).height(objclientheigth-2);
        if(objclientheigth>1 && objclientheigth<=202) {
                setTimeout(function(){closead11adbox(boxad11id)},5);        
        }*/
        j('#'+boxad11id).height(1);
        if(objclientheigth<=1) {
                j('#'+boxad11id).hide();
        }        
}

var zoomad11InBox = function(boxad11id) {
        if(!boxad11id) boxad11id='boxad11';
        var objclientheigth = document.getElementById(boxad11id).clientHeight;
        j('#'+boxad11id).height(49);
        /*
        if(objclientheigth>=30 && objclientheigth<=202) {
                setTimeout(function(){zoomad11InBox(boxad11id)},5);        
        }*/
}

var zoomad11OutBox = function(boxad11id, tipheight) {
        if(!boxad11id) boxad11id='boxad11';
        if(!tipheight) tipheight=150;

        var objclientheigth = document.getElementById(boxad11id).clientHeight;
        j('#'+boxad11id).height(objclientheigth+150);
        if(objclientheigth < tipheight) {
                setTimeout(function(){zoomad11OutBox(boxad11id, tipheight)},5);        
        }        
}

j(function(){

        if(j.browser.msie) { //对于不同浏览弹出高度的区别IE为210,其他194
                var tipheight = 150;
        } else {
                var tipheight = 350;
        }
        if(j.cookie('hasshow') != 1) {
                j('#boxad11').show();
                showadbox('boxad11', tipheight);        
        }
        j('#closead11').click(function(){
                closead11adbox('boxad11');
                j.cookie('hasshow', 1);
        });
        j('#zoomad11').click(function(){
                if(document.getElementById('boxad11').clientHeight >= tipheight) { 
                        zoomad11InBox('boxad11');
                        j('#zoomad11').attr("title", "点击展开");
						j('#zoomad11 img').attr("src", "css/images/max.jpg");
                } else {
                        zoomad11OutBox('boxad11', tipheight);
                        j('#zoomad11').attr("title", "点击收缩");
						j('#zoomad11 img').attr("src", "css/images/mini.jpg");
                }
        });
        
});
