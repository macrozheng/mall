/**
*	jprogress v0.1
*	日期：2014-03-03
*	作者：张恕征
*	邮箱：469741414@qq.com
*	主页：http://www.zhangshuzheng.cn/
*	Copyright (c) 2014, Zhang Shuzheng All rights reserved.
*	
*/
(function($){

	$.extend({
		progress:function(){
			/*面板样式*/
			var style = '<style type="text/css">\n'+
							'#progress{-webkit-pointer-events:none;}\n'+
							'#progress #progress_bar{position:fixed;z-index:9;top:0;left:0;width:0;height:2px;background:#00bfff;color:#00bfff;-webkit-transition-duration:.1s;}}\n'+
							'#progress #progress_peg{display:block;position:absolute;right:0;width:100px;height:100%;box-shadow:0 0 10px, 0 0 5px;-webkit-transform:rotate(3deg) translate(0px, -4px);}\n'+
						'</style>';
			$('head').append(style);

			//页面插入空下拉列表html
			var $progress = $('<div id="progress"><div id="progress_bar"><div id="progress_peg"></div></div></div>');
			$('body').append($progress);
		}
	});
})(jQuery);
