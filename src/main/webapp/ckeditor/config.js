﻿/*
Copyright (c) 2003-2010, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config ) {
	config.language = 'zh-cn'; config.uiColor = '#f7f5f4';
	config.width = '99.7%'; 
	if (config.height == ''){
		config.height = '400px';
	}
	config.toolbarStartupExpanded = true;//工具栏默认展开
	config.fillEmptyBlocks = false;
	config.removePlugins = 'elementspath,scayt';
	config.basicEntities = false;
	config.disableNativeSpellChecker = false;
	config.resize_dir = 'vertical';
	config.keystrokes =[[ CKEDITOR.CTRL + 13 /*Enter*/, 'maximize' ]];	
	config.extraPlugins = 'tableresize';
	config.extraPlugins += (config.extraPlugins ?',lineheight' :'lineheight');
	config.enterMode = CKEDITOR.ENTER_P;
	config.shiftEnterMode = CKEDITOR.ENTER_BR;
	config.font_names='宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;'+ config.font_names;
	config.font_defaultLabel = '仿宋';
	config.fontSize_defaultLabel = '18';
	config.image_previewText='&nbsp;';
	config.toolbar_default = [
		['Source','-','Templates','Preview'],
	    ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print'],
	    ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],['ShowBlocks'],['Image','Capture','Flash'],['Maximize'],
	    '/',
	    ['Bold','Italic','Underline','Strike','-'],
	    ['Subscript','Superscript','-'],
	    ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
	    ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	    ['Link','Unlink','Anchor'],
	    ['Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
	    '/',
	    ['Styles','Format','Font','FontSize','lineheight'],
	    ['TextColor','BGColor']
	];
	config.toolbar = 'default';
	
	//文字的默認式樣 plugins/font/plugin.js
	config.font_style = {
	element : 'span',
	styles : { 'font-family' : '#(family)' },
	overrides : [ { element : 'font', attributes : { 'face' : null } } ]
	};
	
	//設置字體大小時 使用的式樣 plugins/font/plugin.js
	config.fontSize_style = {
	element : 'span',
	styles : { 'font-size' : '#(size)' },
	overrides : [ { element : 'font', attributes : { 'size' : null } } ]
	};
	
	if(config.ckfinderPath){
		config.filebrowserBrowseUrl = config.ckfinderPath+'/ckfinder.html?type=files&start=files:'+config.ckfinderUploadPath;
		config.filebrowserImageBrowseUrl = config.ckfinderPath+'/ckfinder.html?type=images&start=images:'+config.ckfinderUploadPath;
		config.filebrowserFlashBrowseUrl = config.ckfinderPath+'/ckfinder.html?type=flash&start=flash:'+config.ckfinderUploadPath;
		config.filebrowserUploadUrl = config.ckfinderPath+'/core/connector/java/connector.java?command=QuickUpload&type=files&currentFolder='+config.ckfinderUploadPath;
		config.filebrowserImageUploadUrl = config.ckfinderPath+'/core/connector/java/connector.java?command=QuickUpload&type=images&currentFolder='+config.ckfinderUploadPath;
		config.filebrowserFlashUploadUrl = config.ckfinderPath+'/core/connector/java/connector.java?command=QuickUpload&type=flash&currentFolder='+config.ckfinderUploadPath;
		config.filebrowserWindowWidth = '1000';
		config.filebrowserWindowHeight = '700';
	}
};
CKEDITOR.stylesSet.add( 'default', [
	/* Block Styles */
	{ name : '首行缩进'	, element : 'p', styles : { 'text-indent' : '29pt' } },
	/* Inline Styles */
	{ name : '标注黄色'	, element : 'span', styles : { 'background-color' : 'Yellow' } },
	{ name : '标注绿色'	, element : 'span', styles : { 'background-color' : 'Lime' } },
	/* Object Styles */
	{ name : '图片左对齐', element : 'img', attributes : { 'style' : 'padding: 5px; margin-right: 5px', 'border' : '2', 'align' : 'left' } },
	{ name : '图片有对齐', element : 'img', attributes : { 'style' : 'padding: 5px; margin-left: 5px', 'border' : '2', 'align' : 'right' } },
	{ name : '无边界表格', element : 'table', styles: { 'border-style': 'hidden', 'background-color' : '#E6E6FA' } }
]);


