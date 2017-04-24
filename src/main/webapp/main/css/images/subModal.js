function addEvent(obj, evType, fn){
 if (obj.addEventListener){
    obj.addEventListener(evType, fn, true);
    return true;
 } else if (obj.attachEvent){
    var r = obj.attachEvent("on"+evType, fn);
    return r;
 } else {
    return false;
 }
}
function removeEvent(obj, evType, fn, useCapture){
  if (obj.removeEventListener){
    obj.removeEventListener(evType, fn, useCapture);
    return true;
  } else if (obj.detachEvent){
    var r = obj.detachEvent("on"+evType, fn);
    return r;
  } else {
    alert("Handler could not be removed");
  }
}

function getViewportHeight() {
	if (window.innerHeight!=window.undefined) return (window.innerHeight+20);
	if (document.compatMode=='CSS1Compat') return (document.documentElement.clientHeight+20);
	if (document.body) return (document.body.clientHeight + 20); 

	return window.undefined; 
}
function getViewportWidth() {
	if (window.innerWidth!=window.undefined) return window.innerWidth; 
	if (document.compatMode=='CSS1Compat') return document.documentElement.clientWidth; 
	if (document.body) return document.body.clientWidth; 
	return window.undefined; 
}

var gPopupMask = null;
var gPopupContainer = null;
var gPopFrame = null;
var gReturnFunc;
var gPopupIsShown = false;

var gHideSelects = false;

var winWidth = "200px";
var winHeihgt = "200px";


var gTabIndexes = new Array();
var gTabbableTags = new Array("A","BUTTON","TEXTAREA","INPUT","IFRAME");	

if (!document.all) {
	document.onkeypress = keyDownHandler;
}

function initPopUp() {
	

	gPopupMask = document.getElementById("popupMask");
	gPopupContainer = document.getElementById("popupContainer");
	gPopFrame = document.getElementById("popupFrame");
	gAlertHtml = 	document.getElementById("alertHTML");
	
	var brsVersion = parseInt(window.navigator.appVersion.charAt(0), 10);
	if (brsVersion <= 6 && window.navigator.userAgent.indexOf("MSIE") > -1) {
		gHideSelects = true;
	}
}



function showPopAlert(title,message,width,height,returnFunc)
{
	gReturnFunc = returnFunc;
	InitPop(width,height);
	document.getElementById("popupTitle").innerHTML =title;
	alertHTML.style.width = winWidth;
	alertHTML.style.height = winHeight;
	alertHTML.innerHTML = message;
		if (gHideSelects == true) {
		hideSelectBoxes();
	}
}

function InitPop(width,height)
{

	gPopupIsShown = true;
	disableTabIndexes();
	gPopupMask.style.display = "block";
	gPopupContainer.style.display = "block";
	centerPopWin(width, height);
	
	//var titleBarHeight = parseInt(document.getElementById("popupTitleBar").offsetHeight, 10);
	
	gPopupContainer.style.width = width + "px";
	gPopupContainer.style.height = height + "px";
	winWidth = width + "px";
	winHeight = (height) + "px";
	document.body.style.overflow="hidden"
	window.scrollTo(0,0);
	

}



function showPopWin(url, width, height, returnFunc) {
	
	
	InitPop(width,height);
	
	gPopFrame.style.width = winWidth;
	gPopFrame.style.height = winHeight;
	
	gPopFrame.src = url;
	
	gReturnFunc = returnFunc;
	
	if (gHideSelects == true) {
	    
		hideSelectBoxes();
	}
	//window.setTimeout("setPopTitle();", 600);
}

var gi = 0;
function centerPopWin(width, height) {
	if (gPopupIsShown == true) {
		if (width == null || isNaN(width)) {
			width = gPopupContainer.offsetWidth;
		}
		if (height == null) {
			height = gPopupContainer.offsetHeight;
		}
		
		var fullHeight = getViewportHeight();
		var fullWidth = getViewportWidth();
		
		var theBody = document.documentElement;
		
		var scTop = parseInt(theBody.scrollTop,10);
		var scLeft = parseInt(theBody.scrollLeft,10);
		
		gPopupMask.style.height = fullHeight + "px";
		gPopupMask.style.width = fullWidth + "px";
		gPopupMask.style.top = scTop + "px";
		gPopupMask.style.left = scLeft + "px";
		
		//window.status = gPopupMask.style.top + " " + gPopupMask.style.left + " " + gi++;
		
		//var titleBarHeight = parseInt(document.getElementById("popupTitleBar").offsetHeight, 10);
		
		gPopupContainer.style.top = (scTop + ((fullHeight - (height)) / 2)-50) + "px";//弹出框的高度位置
		gPopupContainer.style.left =  (scLeft + ((fullWidth - width) / 2)) + "px";
	}
}


function hidePopWin(callReturnFunc) {
	gPopupIsShown = false;
	restoreTabIndexes();
	if (gPopupMask == null) {
		return;
	}
	gPopupMask.style.display = "none";
	gPopupContainer.style.display = "none";
	if (callReturnFunc == true && gReturnFunc != null) {
		
		eval(gReturnFunc);
		//alert(gReturnFunc);
		
	}
	gPopFrame.src = '';
	
	if (gHideSelects == true) {
		displaySelectBoxes();
				document.body.style.overflow="auto";
	}
}

function setPopTitle() {
	if (window.frames["popupFrame"].document.title == null) {
		window.setTimeout("setPopTitle();", 10);
	} else {
		document.getElementById("popupTitle").innerHTML = window.frames["popupFrame"].document.title;
	}
}

function keyDownHandler(e) {
    if (gPopupIsShown && e.keyCode == 9)  return false;
}

function disableTabIndexes() {
	if (document.all) {
		var i = 0;
		for (var j = 0; j < gTabbableTags.length; j++) {
			var tagElements = document.getElementsByTagName(gTabbableTags[j]);
			for (var k = 0 ; k < tagElements.length; k++) {
				gTabIndexes[i] = tagElements[k].tabIndex;
				tagElements[k].tabIndex="-1";
				i++;
			}
		}
	}
}

function restoreTabIndexes() {
	if (document.all) {
		var i = 0;
		for (var j = 0; j < gTabbableTags.length; j++) {
			var tagElements = document.getElementsByTagName(gTabbableTags[j]);
			for (var k = 0 ; k < tagElements.length; k++) {
				tagElements[k].tabIndex = gTabIndexes[i];
				tagElements[k].tabEnabled = true;
				i++;
			}
		}
	}
}

function hideSelectBoxes() {
	
	for(var i = 0; i < top.frames.cent.document.forms.length; i++) {
	
		for(var e = 0; e < top.frames.cent.document.forms[i].length; e++){
		
			if(top.frames.cent.document.forms[i].elements[e].tagName == "SELECT") {
			   
				top.frames.cent.document.forms[i].elements[e].style.visibility="hidden";
			}
		}
	}
}

function displaySelectBoxes() {
	

	for(var i = 0; i < top.frames.cent.document.forms.length; i++) {
		for(var e = 0; e < top.frames.cent.document.forms[i].length; e++){
			if(top.frames.cent.document.forms[i].elements[e].tagName == "SELECT") {
			top.frames.cent.document.forms[i].elements[e].style.visibility="visible";
			}
		}
	}
}


function loadCss(cssFile)
{
	
	var HeadElement = document.getElementsByTagName("HEAD")[0];
	var cssFileID   = "__TaskMenuCssFile__";
	while(document.getElementById("TaskMenuCssFile") != null)
	{
		try
		{	
			HeadElement.removeChild(document.getElementById(cssFileID ));	
		}		
		catch(ex)	
		{}
	}
	with(HeadElement.appendChild(document.createElement("LINK")))
	{
		id = cssFileID; href = cssFile; rel = "stylesheet"; type = "text/css";
	}
	
}

function DoInit()
{
	
loadCss('pop2/subModal.css');
document.write("<DIV id=popupMask></DIV>");
document.write("<DIV id=popupContainer>");
document.write("<DIV id=popupInner>");
document.write("<DIV id=alertHTML><IFRAME id=popupFrame name=popupFrame src=\"pop2/loading.html\" frameBorder=0 scrolling=none allowTransparency></IFRAME></DIV></DIV></DIV>");
addEvent(window, "load", initPopUp);	
addEvent(window, "resize", centerPopWin);
addEvent(window, "scroll", centerPopWin);
window.onscroll = centerPopWin;
}



DoInit();


