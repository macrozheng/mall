/*
== malihu jquery custom scrollbar plugin ==
Plugin URI: http://manos.malihu.gr/jquery-custom-content-scroller
*/



/*
CONTENTS: 
	1. BASIC STYLE - Plugin's basic/essential CSS properties (normally, should not be edited). 
	2. VERTICAL SCROLLBAR - Positioning and dimensions of vertical scrollbar. 
	3. HORIZONTAL SCROLLBAR - Positioning and dimensions of horizontal scrollbar.
	4. VERTICAL AND HORIZONTAL SCROLLBARS - Positioning and dimensions of 2-axis scrollbars. 
	5. TRANSITIONS - CSS3 transitions for hover events, auto-expanded and auto-hidden scrollbars. 
	6. SCROLLBAR COLORS, OPACITY AND BACKGROUNDS 
		6.1 THEMES - Scrollbar colors, opacity, dimensions, backgrounds etc. via ready-to-use themes.
*/



/* 
------------------------------------------------------------------------------------------------------------------------
1. BASIC STYLE  
------------------------------------------------------------------------------------------------------------------------
*/

	.mCustomScrollbar{ -ms-touch-action: pinch-zoom; touch-action: pinch-zoom; /* direct pointer events to js */ }
	.mCustomScrollbar.mCS_no_scrollbar, .mCustomScrollbar.mCS_touch_action{ -ms-touch-action: auto; touch-action: auto; }
	
	.mCustomScrollBox{ /* contains plugin's markup */
		position: relative;
		overflow: hidden;
		height: 100%;
		max-width: 100%;
		outline: none;
		direction: ltr;
	}

	.mCSB_container{ /* contains the original content */
		overflow: hidden;
		width: auto;
		height: auto;
	}



/* 
------------------------------------------------------------------------------------------------------------------------
2. VERTICAL SCROLLBAR 
y-axis
------------------------------------------------------------------------------------------------------------------------
*/

	.mCSB_inside > .mCSB_container{ margin-right: 30px; }

	.mCSB_container.mCS_no_scrollbar_y.mCS_y_hidden{ margin-right: 0; } /* non-visible scrollbar */
	
	.mCS-dir-rtl > .mCSB_inside > .mCSB_container{ /* RTL direction/left-side scrollbar */
		margin-right: 0;
		margin-left: 30px;
	}
	
	.mCS-dir-rtl > .mCSB_inside > .mCSB_container.mCS_no_scrollbar_y.mCS_y_hidden{ margin-left: 0; } /* RTL direction/left-side scrollbar */

	.mCSB_scrollTools{ /* contains scrollbar markup (draggable element, dragger rail, buttons etc.) */
		position: absolute;
		width: 16px;
		height: auto;
		left: auto;
		top: 0;
		right: 0;
		bottom: 0;
	}

	.mCSB_outside + .mCSB_scrollTools{ right: -26px; } /* scrollbar position: outside */
	
	.mCS-dir-rtl > .mCSB_inside > .mCSB_scrollTools, 
	.mCS-dir-rtl > .mCSB_outside + .mCSB_scrollTools{ /* RTL direction/left-side scrollbar */
		right: auto;
		left: 0;
	}
	
	.mCS-dir-rtl > .mCSB_outside + .mCSB_scrollTools{ left: -26px; } /* RTL direction/left-side scrollbar (scrollbar position: outside) */

	.mCSB_scrollTools .mCSB_draggerContainer{ /* contains the draggable element and dragger rail markup */
		position: absolute;
		top: 0;
		left: 0;
		bottom: 0;
		right: 0; 
		height: auto;
	}

	.mCSB_scrollTools a + .mCSB_draggerContainer{ margin: 20px 0; }

	.mCSB_scrollTools .mCSB_draggerRail{
		width: 2px;
		height: 100%;
		margin: 0 auto;
		-webkit-border-radius: 16px; -moz-border-radius: 16px; border-radius: 16px;
	}

	.mCSB_scrollTools .mCSB_dragger{ /* the draggable element */
		cursor: pointer;
		width: 100%;
		height: 30px; /* minimum dragger height */
		z-index: 1;
	}

	.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{ /* the dragger element */
		position: relative;
		width: 4px;
		height: 100%;
		margin: 0 auto;
		-webkit-border-radius: 16px; -moz-border-radius: 16px; border-radius: 16px;
		text-align: center;
	}
	
	.mCSB_scrollTools_vertical.mCSB_scrollTools_onDrag_expand .mCSB_dragger.mCSB_dragger_onDrag_expanded .mCSB_dragger_bar, 
	.mCSB_scrollTools_vertical.mCSB_scrollTools_onDrag_expand .mCSB_draggerContainer:hover .mCSB_dragger .mCSB_dragger_bar{ width: 12px; /* auto-expanded scrollbar */ }
	
	.mCSB_scrollTools_vertical.mCSB_scrollTools_onDrag_expand .mCSB_dragger.mCSB_dragger_onDrag_expanded + .mCSB_draggerRail, 
	.mCSB_scrollTools_vertical.mCSB_scrollTools_onDrag_expand .mCSB_draggerContainer:hover .mCSB_draggerRail{ width: 8px; /* auto-expanded scrollbar */ }

	.mCSB_scrollTools .mCSB_buttonUp,
	.mCSB_scrollTools .mCSB_buttonDown{
		display: block;
		position: absolute;
		height: 20px;
		width: 100%;
		overflow: hidden;
		margin: 0 auto;
		cursor: pointer;
	}

	.mCSB_scrollTools .mCSB_buttonDown{ bottom: 0; }



/* 
------------------------------------------------------------------------------------------------------------------------
3. HORIZONTAL SCROLLBAR 
x-axis
------------------------------------------------------------------------------------------------------------------------
*/

	.mCSB_horizontal.mCSB_inside > .mCSB_container{
		margin-right: 0;
		margin-bottom: 30px;
	}
	
	.mCSB_horizontal.mCSB_outside > .mCSB_container{ min-height: 100%; }

	.mCSB_horizontal > .mCSB_container.mCS_no_scrollbar_x.mCS_x_hidden{ margin-bottom: 0; } /* non-visible scrollbar */

	.mCSB_scrollTools.mCSB_scrollTools_horizontal{
		width: auto;
		height: 16px;
		top: auto;
		right: 0;
		bottom: 0;
		left: 0;
	}

	.mCustomScrollBox + .mCSB_scrollTools.mCSB_scrollTools_horizontal,
	.mCustomScrollBox + .mCSB_scrollTools + .mCSB_scrollTools.mCSB_scrollTools_horizontal{ bottom: -26px; } /* scrollbar position: outside */

	.mCSB_scrollTools.mCSB_scrollTools_horizontal a + .mCSB_draggerContainer{ margin: 0 20px; }

	.mCSB_scrollTools.mCSB_scrollTools_horizontal .mCSB_draggerRail{
		width: 100%;
		height: 2px;
		margin: 7px 0;
	}

	.mCSB_scrollTools.mCSB_scrollTools_horizontal .mCSB_dragger{
		width: 30px; /* minimum dragger width */
		height: 100%;
		left: 0;
	}

	.mCSB_scrollTools.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar{
		width: 100%;
		height: 4px;
		margin: 6px auto;
	}
	
	.mCSB_scrollTools_horizontal.mCSB_scrollTools_onDrag_expand .mCSB_dragger.mCSB_dragger_onDrag_expanded .mCSB_dragger_bar, 
	.mCSB_scrollTools_horizontal.mCSB_scrollTools_onDrag_expand .mCSB_draggerContainer:hover .mCSB_dragger .mCSB_dragger_bar{
		height: 12px; /* auto-expanded scrollbar */
		margin: 2px auto;
	}
	
	.mCSB_scrollTools_horizontal.mCSB_scrollTools_onDrag_expand .mCSB_dragger.mCSB_dragger_onDrag_expanded + .mCSB_draggerRail, 
	.mCSB_scrollTools_horizontal.mCSB_scrollTools_onDrag_expand .mCSB_draggerContainer:hover .mCSB_draggerRail{
		height: 8px; /* auto-expanded scrollbar */
		margin: 4px 0;
	}

	.mCSB_scrollTools.mCSB_scrollTools_horizontal .mCSB_buttonLeft,
	.mCSB_scrollTools.mCSB_scrollTools_horizontal .mCSB_buttonRight{
		display: block;
		position: absolute;
		width: 20px;
		height: 100%;
		overflow: hidden;
		margin: 0 auto;
		cursor: pointer;
	}
	
	.mCSB_scrollTools.mCSB_scrollTools_horizontal .mCSB_buttonLeft{ left: 0; }

	.mCSB_scrollTools.mCSB_scrollTools_horizontal .mCSB_buttonRight{ right: 0; }



/* 
------------------------------------------------------------------------------------------------------------------------
4. VERTICAL AND HORIZONTAL SCROLLBARS 
yx-axis 
------------------------------------------------------------------------------------------------------------------------
*/

	.mCSB_container_wrapper{
		position: absolute;
		height: auto;
		width: auto;
		overflow: hidden;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		margin-right: 30px;
		margin-bottom: 30px;
	}
	
	.mCSB_container_wrapper > .mCSB_container{
		padding-right: 30px;
		padding-bottom: 30px;
		-webkit-box-sizing: border-box; -moz-box-sizing: border-box; box-sizing: border-box;
	}
	
	.mCSB_vertical_horizontal > .mCSB_scrollTools.mCSB_scrollTools_vertical{ bottom: 20px; }
	
	.mCSB_vertical_horizontal > .mCSB_scrollTools.mCSB_scrollTools_horizontal{ right: 20px; }
	
	/* non-visible horizontal scrollbar */
	.mCSB_container_wrapper.mCS_no_scrollbar_x.mCS_x_hidden + .mCSB_scrollTools.mCSB_scrollTools_vertical{ bottom: 0; }
	
	/* non-visible vertical scrollbar/RTL direction/left-side scrollbar */
	.mCSB_container_wrapper.mCS_no_scrollbar_y.mCS_y_hidden + .mCSB_scrollTools ~ .mCSB_scrollTools.mCSB_scrollTools_horizontal, 
	.mCS-dir-rtl > .mCustomScrollBox.mCSB_vertical_horizontal.mCSB_inside > .mCSB_scrollTools.mCSB_scrollTools_horizontal{ right: 0; }
	
	/* RTL direction/left-side scrollbar */
	.mCS-dir-rtl > .mCustomScrollBox.mCSB_vertical_horizontal.mCSB_inside > .mCSB_scrollTools.mCSB_scrollTools_horizontal{ left: 20px; }
	
	/* non-visible scrollbar/RTL direction/left-side scrollbar */
	.mCS-dir-rtl > .mCustomScrollBox.mCSB_vertical_horizontal.mCSB_inside > .mCSB_container_wrapper.mCS_no_scrollbar_y.mCS_y_hidden + .mCSB_scrollTools ~ .mCSB_scrollTools.mCSB_scrollTools_horizontal{ left: 0; }
	
	.mCS-dir-rtl > .mCSB_inside > .mCSB_container_wrapper{ /* RTL direction/left-side scrollbar */
		margin-right: 0;
		margin-left: 30px;
	}
	
	.mCSB_container_wrapper.mCS_no_scrollbar_y.mCS_y_hidden > .mCSB_container{ padding-right: 0; }
	
	.mCSB_container_wrapper.mCS_no_scrollbar_x.mCS_x_hidden > .mCSB_container{ padding-bottom: 0; }
	
	.mCustomScrollBox.mCSB_vertical_horizontal.mCSB_inside > .mCSB_container_wrapper.mCS_no_scrollbar_y.mCS_y_hidden{
		margin-right: 0; /* non-visible scrollbar */
		margin-left: 0;
	}
	
	/* non-visible horizontal scrollbar */
	.mCustomScrollBox.mCSB_vertical_horizontal.mCSB_inside > .mCSB_container_wrapper.mCS_no_scrollbar_x.mCS_x_hidden{ margin-bottom: 0; }



/* 
------------------------------------------------------------------------------------------------------------------------
5. TRANSITIONS  
------------------------------------------------------------------------------------------------------------------------
*/

	.mCSB_scrollTools, 
	.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCSB_scrollTools .mCSB_buttonUp,
	.mCSB_scrollTools .mCSB_buttonDown,
	.mCSB_scrollTools .mCSB_buttonLeft,
	.mCSB_scrollTools .mCSB_buttonRight{
		-webkit-transition: opacity .2s ease-in-out, background-color .2s ease-in-out;
		-moz-transition: opacity .2s ease-in-out, background-color .2s ease-in-out;
		-o-transition: opacity .2s ease-in-out, background-color .2s ease-in-out;
		transition: opacity .2s ease-in-out, background-color .2s ease-in-out;
	}
	
	.mCSB_scrollTools_vertical.mCSB_scrollTools_onDrag_expand .mCSB_dragger_bar, /* auto-expanded scrollbar */
	.mCSB_scrollTools_vertical.mCSB_scrollTools_onDrag_expand .mCSB_draggerRail, 
	.mCSB_scrollTools_horizontal.mCSB_scrollTools_onDrag_expand .mCSB_dragger_bar, 
	.mCSB_scrollTools_horizontal.mCSB_scrollTools_onDrag_expand .mCSB_draggerRail{
		-webkit-transition: width .2s ease-out .2s, height .2s ease-out .2s, 
					margin-left .2s ease-out .2s, margin-right .2s ease-out .2s, 
					margin-top .2s ease-out .2s, margin-bottom .2s ease-out .2s,
					opacity .2s ease-in-out, background-color .2s ease-in-out; 
		-moz-transition: width .2s ease-out .2s, height .2s ease-out .2s, 
					margin-left .2s ease-out .2s, margin-right .2s ease-out .2s, 
					margin-top .2s ease-out .2s, margin-bottom .2s ease-out .2s,
					opacity .2s ease-in-out, background-color .2s ease-in-out; 
		-o-transition: width .2s ease-out .2s, height .2s ease-out .2s, 
					margin-left .2s ease-out .2s, margin-right .2s ease-out .2s, 
					margin-top .2s ease-out .2s, margin-bottom .2s ease-out .2s,
					opacity .2s ease-in-out, background-color .2s ease-in-out; 
		transition: width .2s ease-out .2s, height .2s ease-out .2s, 
					margin-left .2s ease-out .2s, margin-right .2s ease-out .2s, 
					margin-top .2s ease-out .2s, margin-bottom .2s ease-out .2s,
					opacity .2s ease-in-out, background-color .2s ease-in-out; 
	}



/* 
------------------------------------------------------------------------------------------------------------------------
6. SCROLLBAR COLORS, OPACITY AND BACKGROUNDS  
------------------------------------------------------------------------------------------------------------------------
*/

	/* 
	----------------------------------------
	6.1 THEMES 
	----------------------------------------
	*/
	
	/* default theme ("light") */

	.mCSB_scrollTools{ opacity: 0.75; filter: "alpha(opacity=75)"; -ms-filter: "alpha(opacity=75)"; }
	
	.mCS-autoHide > .mCustomScrollBox > .mCSB_scrollTools,
	.mCS-autoHide > .mCustomScrollBox ~ .mCSB_scrollTools{ opacity: 0; filter: "alpha(opacity=0)"; -ms-filter: "alpha(opacity=0)"; }
	
	.mCustomScrollbar > .mCustomScrollBox > .mCSB_scrollTools.mCSB_scrollTools_onDrag,
	.mCustomScrollbar > .mCustomScrollBox ~ .mCSB_scrollTools.mCSB_scrollTools_onDrag,
	.mCustomScrollBox:hover > .mCSB_scrollTools,
	.mCustomScrollBox:hover ~ .mCSB_scrollTools,
	.mCS-autoHide:hover > .mCustomScrollBox > .mCSB_scrollTools,
	.mCS-autoHide:hover > .mCustomScrollBox ~ .mCSB_scrollTools{ opacity: 1; filter: "alpha(opacity=100)"; -ms-filter: "alpha(opacity=100)"; }

	.mCSB_scrollTools .mCSB_draggerRail{
		background-color: #000; background-color: rgba(0,0,0,0.4);
		filter: "alpha(opacity=40)"; -ms-filter: "alpha(opacity=40)"; 
	}

	.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{
		background-color: #fff; background-color: rgba(255,255,255,0.75);
		filter: "alpha(opacity=75)"; -ms-filter: "alpha(opacity=75)"; 
	}

	.mCSB_scrollTools .mCSB_dragger:hover .mCSB_dragger_bar{
		background-color: #fff; background-color: rgba(255,255,255,0.85);
		filter: "alpha(opacity=85)"; -ms-filter: "alpha(opacity=85)"; 
	}
	.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar{
		background-color: #fff; background-color: rgba(255,255,255,0.9);
		filter: "alpha(opacity=90)"; -ms-filter: "alpha(opacity=90)"; 
	}

	.mCSB_scrollTools .mCSB_buttonUp,
	.mCSB_scrollTools .mCSB_buttonDown,
	.mCSB_scrollTools .mCSB_buttonLeft,
	.mCSB_scrollTools .mCSB_buttonRight{
		background-image: url(mCSB_buttons.png); /* css sprites */
		background-repeat: no-repeat;
		opacity: 0.4; filter: "alpha(opacity=40)"; -ms-filter: "alpha(opacity=40)"; 
	}

	.mCSB_scrollTools .mCSB_buttonUp{
		background-position: 0 0;
		/* 
		sprites locations 
		light: 0 0, -16px 0, -32px 0, -48px 0, 0 -72px, -16px -72px, -32px -72px
		dark: -80px 0, -96px 0, -112px 0, -128px 0, -80px -72px, -96px -72px, -112px -72px
		*/
	}

	.mCSB_scrollTools .mCSB_buttonDown{
		background-position: 0 -20px;
		/* 
		sprites locations
		light: 0 -20px, -16px -20px, -32px -20px, -48px -20px, 0 -92px, -16px -92px, -32px -92px
		dark: -80px -20px, -96px -20px, -112px -20px, -128px -20px, -80px -92px, -96px -92px, -112 -92px
		*/
	}

	.mCSB_scrollTools .mCSB_buttonLeft{
		background-position: 0 -40px;
		/* 
		sprites locations 
		light: 0 -40px, -20px -40px, -40px -40px, -60px -40px, 0 -112px, -20px -112px, -40px -112px
		dark: -80px -40px, -100px -40px, -120px -40px, -140px -40px, -80px -112px, -100px -112px, -120px -112px
		*/
	}

	.mCSB_scrollTools .mCSB_buttonRight{
		background-position: 0 -56px;
		/* 
		sprites locations 
		light: 0 -56px, -20px -56px, -40px -56px, -60px -56px, 0 -128px, -20px -128px, -40px -128px
		dark: -80px -56px, -100px -56px, -120px -56px, -140px -56px, -80px -128px, -100px -128px, -120px -128px
		*/
	}

	.mCSB_scrollTools .mCSB_buttonUp:hover,
	.mCSB_scrollTools .mCSB_buttonDown:hover,
	.mCSB_scrollTools .mCSB_buttonLeft:hover,
	.mCSB_scrollTools .mCSB_buttonRight:hover{ opacity: 0.75; filter: "alpha(opacity=75)"; -ms-filter: "alpha(opacity=75)"; }

	.mCSB_scrollTools .mCSB_buttonUp:active,
	.mCSB_scrollTools .mCSB_buttonDown:active,
	.mCSB_scrollTools .mCSB_buttonLeft:active,
	.mCSB_scrollTools .mCSB_buttonRight:active{ opacity: 0.9; filter: "alpha(opacity=90)"; -ms-filter: "alpha(opacity=90)"; }
	

	/* theme: "dark" */

	.mCS-dark.mCSB_scrollTools .mCSB_draggerRail{ background-color: #000; background-color: rgba(0,0,0,0.15); }

	.mCS-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{ background-color: #000; background-color: rgba(0,0,0,0.75); }

	.mCS-dark.mCSB_scrollTools .mCSB_dragger:hover .mCSB_dragger_bar{ background-color: rgba(0,0,0,0.85); }

	.mCS-dark.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCS-dark.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar{ background-color: rgba(0,0,0,0.9); }

	.mCS-dark.mCSB_scrollTools .mCSB_buttonUp{ background-position: -80px 0; }

	.mCS-dark.mCSB_scrollTools .mCSB_buttonDown{ background-position: -80px -20px; }

	.mCS-dark.mCSB_scrollTools .mCSB_buttonLeft{ background-position: -80px -40px; }

	.mCS-dark.mCSB_scrollTools .mCSB_buttonRight{ background-position: -80px -56px; }
	
	/* ---------------------------------------- */
	


	/* theme: "light-2", "dark-2" */

	.mCS-light-2.mCSB_scrollTools .mCSB_draggerRail, 
	.mCS-dark-2.mCSB_scrollTools .mCSB_draggerRail{
		width: 4px;
		background-color: #fff; background-color: rgba(255,255,255,0.1);
		-webkit-border-radius: 1px; -moz-border-radius: 1px; border-radius: 1px;
	}

	.mCS-light-2.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-dark-2.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{
		width: 4px;
		background-color: #fff; background-color: rgba(255,255,255,0.75);
		-webkit-border-radius: 1px; -moz-border-radius: 1px; border-radius: 1px;
	}

	.mCS-light-2.mCSB_scrollTools_horizontal .mCSB_draggerRail, 
	.mCS-dark-2.mCSB_scrollTools_horizontal .mCSB_draggerRail, 
	.mCS-light-2.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-dark-2.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar{
		width: 100%;
		height: 4px;
		margin: 6px auto;
	}

	.mCS-light-2.mCSB_scrollTools .mCSB_dragger:hover .mCSB_dragger_bar{ background-color: #fff; background-color: rgba(255,255,255,0.85); }

	.mCS-light-2.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCS-light-2.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar{ background-color: #fff; background-color: rgba(255,255,255,0.9); }

	.mCS-light-2.mCSB_scrollTools .mCSB_buttonUp{ background-position: -32px 0; }

	.mCS-light-2.mCSB_scrollTools .mCSB_buttonDown{	background-position: -32px -20px; }

	.mCS-light-2.mCSB_scrollTools .mCSB_buttonLeft{	background-position: -40px -40px; }

	.mCS-light-2.mCSB_scrollTools .mCSB_buttonRight{ background-position: -40px -56px; }
	
	
	/* theme: "dark-2" */

	.mCS-dark-2.mCSB_scrollTools .mCSB_draggerRail{
		background-color: #000; background-color: rgba(0,0,0,0.1);
		-webkit-border-radius: 1px; -moz-border-radius: 1px; border-radius: 1px;
	}

	.mCS-dark-2.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{
		background-color: #000; background-color: rgba(0,0,0,0.75);
		-webkit-border-radius: 1px; -moz-border-radius: 1px; border-radius: 1px;
	}

	.mCS-dark-2.mCSB_scrollTools .mCSB_dragger:hover .mCSB_dragger_bar{ background-color: #000; background-color: rgba(0,0,0,0.85); }

	.mCS-dark-2.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCS-dark-2.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar{ background-color: #000; background-color: rgba(0,0,0,0.9); }

	.mCS-dark-2.mCSB_scrollTools .mCSB_buttonUp{ background-position: -112px 0; }

	.mCS-dark-2.mCSB_scrollTools .mCSB_buttonDown{ background-position: -112px -20px; }

	.mCS-dark-2.mCSB_scrollTools .mCSB_buttonLeft{ background-position: -120px -40px; }

	.mCS-dark-2.mCSB_scrollTools .mCSB_buttonRight{	background-position: -120px -56px; }
	
	/* ---------------------------------------- */
	


	/* theme: "light-thick", "dark-thick" */

	.mCS-light-thick.mCSB_scrollTools .mCSB_draggerRail, 
	.mCS-dark-thick.mCSB_scrollTools .mCSB_draggerRail{
		width: 4px;
		background-color: #fff; background-color: rgba(255,255,255,0.1);
		-webkit-border-radius: 2px; -moz-border-radius: 2px; border-radius: 2px;
	}

	.mCS-light-thick.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-dark-thick.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{
		width: 6px;
		background-color: #fff; background-color: rgba(255,255,255,0.75);
		-webkit-border-radius: 2px; -moz-border-radius: 2px; border-radius: 2px;
	}

	.mCS-light-thick.mCSB_scrollTools_horizontal .mCSB_draggerRail, 
	.mCS-dark-thick.mCSB_scrollTools_horizontal .mCSB_draggerRail{
		width: 100%;
		height: 4px;
		margin: 6px 0;
	}

	.mCS-light-thick.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-dark-thick.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar{
		width: 100%;
		height: 6px;
		margin: 5px auto;
	}

	.mCS-light-thick.mCSB_scrollTools .mCSB_dragger:hover .mCSB_dragger_bar{ background-color: #fff; background-color: rgba(255,255,255,0.85); }

	.mCS-light-thick.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCS-light-thick.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar{ background-color: #fff; background-color: rgba(255,255,255,0.9); }

	.mCS-light-thick.mCSB_scrollTools .mCSB_buttonUp{ background-position: -16px 0; }

	.mCS-light-thick.mCSB_scrollTools .mCSB_buttonDown{	background-position: -16px -20px; }

	.mCS-light-thick.mCSB_scrollTools .mCSB_buttonLeft{	background-position: -20px -40px; }

	.mCS-light-thick.mCSB_scrollTools .mCSB_buttonRight{ background-position: -20px -56px; }


	/* theme: "dark-thick" */
	
	.mCS-dark-thick.mCSB_scrollTools .mCSB_draggerRail{
		background-color: #000; background-color: rgba(0,0,0,0.1);
		-webkit-border-radius: 2px; -moz-border-radius: 2px; border-radius: 2px;
	}

	.mCS-dark-thick.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{
		background-color: #000; background-color: rgba(0,0,0,0.75);
		-webkit-border-radius: 2px; -moz-border-radius: 2px; border-radius: 2px;
	}

	.mCS-dark-thick.mCSB_scrollTools .mCSB_dragger:hover .mCSB_dragger_bar{ background-color: #000; background-color: rgba(0,0,0,0.85); }

	.mCS-dark-thick.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCS-dark-thick.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar{ background-color: #000; background-color: rgba(0,0,0,0.9); }

	.mCS-dark-thick.mCSB_scrollTools .mCSB_buttonUp{ background-position: -96px 0; }

	.mCS-dark-thick.mCSB_scrollTools .mCSB_buttonDown{ background-position: -96px -20px; }

	.mCS-dark-thick.mCSB_scrollTools .mCSB_buttonLeft{ background-position: -100px -40px; }

	.mCS-dark-thick.mCSB_scrollTools .mCSB_buttonRight{	background-position: -100px -56px; }
	
	/* ---------------------------------------- */
	


	/* theme: "light-thin", "dark-thin" */
	
	.mCS-light-thin.mCSB_scrollTools .mCSB_draggerRail{ background-color: #fff; background-color: rgba(255,255,255,0.1); }

	.mCS-light-thin.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-dark-thin.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{ width: 2px; }

	.mCS-light-thin.mCSB_scrollTools_horizontal .mCSB_draggerRail, 
	.mCS-dark-thin.mCSB_scrollTools_horizontal .mCSB_draggerRail{ width: 100%; }

	.mCS-light-thin.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-dark-thin.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar{
		width: 100%;
		height: 2px;
		margin: 7px auto;
	}


	/* theme "dark-thin" */
	
	.mCS-dark-thin.mCSB_scrollTools .mCSB_draggerRail{ background-color: #000; background-color: rgba(0,0,0,0.15); }

	.mCS-dark-thin.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{ background-color: #000; background-color: rgba(0,0,0,0.75); }
	
	.mCS-dark-thin.mCSB_scrollTools .mCSB_dragger:hover .mCSB_dragger_bar{ background-color: #000; background-color: rgba(0,0,0,0.85); }
	
	.mCS-dark-thin.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCS-dark-thin.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar{ background-color: #000; background-color: rgba(0,0,0,0.9); }
	
	.mCS-dark-thin.mCSB_scrollTools .mCSB_buttonUp{	background-position: -80px 0; }

	.mCS-dark-thin.mCSB_scrollTools .mCSB_buttonDown{ background-position: -80px -20px; }

	.mCS-dark-thin.mCSB_scrollTools .mCSB_buttonLeft{ background-position: -80px -40px; }

	.mCS-dark-thin.mCSB_scrollTools .mCSB_buttonRight{ background-position: -80px -56px; }
	
	/* ---------------------------------------- */
	
	
	
	/* theme "rounded", "rounded-dark", "rounded-dots", "rounded-dots-dark" */
	
	.mCS-rounded.mCSB_scrollTools .mCSB_draggerRail{ background-color: #fff; background-color: rgba(255,255,255,0.15); }
	
	.mCS-rounded.mCSB_scrollTools .mCSB_dragger, 
	.mCS-rounded-dark.mCSB_scrollTools .mCSB_dragger, 
	.mCS-rounded-dots.mCSB_scrollTools .mCSB_dragger, 
	.mCS-rounded-dots-dark.mCSB_scrollTools .mCSB_dragger{ height: 14px; }
	
	.mCS-rounded.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-rounded-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-rounded-dots.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-rounded-dots-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{
		width: 14px;
		margin: 0 1px;
	}
	
	.mCS-rounded.mCSB_scrollTools_horizontal .mCSB_dragger, 
	.mCS-rounded-dark.mCSB_scrollTools_horizontal .mCSB_dragger, 
	.mCS-rounded-dots.mCSB_scrollTools_horizontal .mCSB_dragger, 
	.mCS-rounded-dots-dark.mCSB_scrollTools_horizontal .mCSB_dragger{ width: 14px; }
	
	.mCS-rounded.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-rounded-dark.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-rounded-dots.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-rounded-dots-dark.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar{
		height: 14px;
		margin: 1px 0;
	}
	
	.mCS-rounded.mCSB_scrollTools_vertical.mCSB_scrollTools_onDrag_expand .mCSB_dragger.mCSB_dragger_onDrag_expanded .mCSB_dragger_bar, 
	.mCS-rounded.mCSB_scrollTools_vertical.mCSB_scrollTools_onDrag_expand .mCSB_draggerContainer:hover .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-rounded-dark.mCSB_scrollTools_vertical.mCSB_scrollTools_onDrag_expand .mCSB_dragger.mCSB_dragger_onDrag_expanded .mCSB_dragger_bar, 
	.mCS-rounded-dark.mCSB_scrollTools_vertical.mCSB_scrollTools_onDrag_expand .mCSB_draggerContainer:hover .mCSB_dragger .mCSB_dragger_bar{
		width: 16px; /* auto-expanded scrollbar */
		height: 16px;
		margin: -1px 0;
	}
	
	.mCS-rounded.mCSB_scrollTools_vertical.mCSB_scrollTools_onDrag_expand .mCSB_dragger.mCSB_dragger_onDrag_expanded + .mCSB_draggerRail, 
	.mCS-rounded.mCSB_scrollTools_vertical.mCSB_scrollTools_onDrag_expand .mCSB_draggerContainer:hover .mCSB_draggerRail, 
	.mCS-rounded-dark.mCSB_scrollTools_vertical.mCSB_scrollTools_onDrag_expand .mCSB_dragger.mCSB_dragger_onDrag_expanded + .mCSB_draggerRail, 
	.mCS-rounded-dark.mCSB_scrollTools_vertical.mCSB_scrollTools_onDrag_expand .mCSB_draggerContainer:hover .mCSB_draggerRail{ width: 4px; /* auto-expanded scrollbar */ }
	
	.mCS-rounded.mCSB_scrollTools_horizontal.mCSB_scrollTools_onDrag_expand .mCSB_dragger.mCSB_dragger_onDrag_expanded .mCSB_dragger_bar, 
	.mCS-rounded.mCSB_scrollTools_horizontal.mCSB_scrollTools_onDrag_expand .mCSB_draggerContainer:hover .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-rounded-dark.mCSB_scrollTools_horizontal.mCSB_scrollTools_onDrag_expand .mCSB_dragger.mCSB_dragger_onDrag_expanded .mCSB_dragger_bar, 
	.mCS-rounded-dark.mCSB_scrollTools_horizontal.mCSB_scrollTools_onDrag_expand .mCSB_draggerContainer:hover .mCSB_dragger .mCSB_dragger_bar{
		height: 16px; /* auto-expanded scrollbar */
		width: 16px;
		margin: 0 -1px;
	}
	
	.mCS-rounded.mCSB_scrollTools_horizontal.mCSB_scrollTools_onDrag_expand .mCSB_dragger.mCSB_dragger_onDrag_expanded + .mCSB_draggerRail, 
	.mCS-rounded.mCSB_scrollTools_horizontal.mCSB_scrollTools_onDrag_expand .mCSB_draggerContainer:hover .mCSB_draggerRail, 
	.mCS-rounded-dark.mCSB_scrollTools_horizontal.mCSB_scrollTools_onDrag_expand .mCSB_dragger.mCSB_dragger_onDrag_expanded + .mCSB_draggerRail, 
	.mCS-rounded-dark.mCSB_scrollTools_horizontal.mCSB_scrollTools_onDrag_expand .mCSB_draggerContainer:hover .mCSB_draggerRail{
		height: 4px; /* auto-expanded scrollbar */
		margin: 6px 0;
	}
	
	.mCS-rounded.mCSB_scrollTools .mCSB_buttonUp{ background-position: 0 -72px; }
	
	.mCS-rounded.mCSB_scrollTools .mCSB_buttonDown{ background-position: 0 -92px; }
	
	.mCS-rounded.mCSB_scrollTools .mCSB_buttonLeft{ background-position: 0 -112px; }
	
	.mCS-rounded.mCSB_scrollTools .mCSB_buttonRight{ background-position: 0 -128px; }
	
	
	/* theme "rounded-dark", "rounded-dots-dark" */
	
	.mCS-rounded-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-rounded-dots-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{ background-color: #000; background-color: rgba(0,0,0,0.75); }
	
	.mCS-rounded-dark.mCSB_scrollTools .mCSB_draggerRail{ background-color: #000; background-color: rgba(0,0,0,0.15); }
	
	.mCS-rounded-dark.mCSB_scrollTools .mCSB_dragger:hover .mCSB_dragger_bar, 
	.mCS-rounded-dots-dark.mCSB_scrollTools .mCSB_dragger:hover .mCSB_dragger_bar{ background-color: #000; background-color: rgba(0,0,0,0.85); }
	
	.mCS-rounded-dark.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCS-rounded-dark.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar, 
	.mCS-rounded-dots-dark.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCS-rounded-dots-dark.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar{ background-color: #000; background-color: rgba(0,0,0,0.9); }
	
	.mCS-rounded-dark.mCSB_scrollTools .mCSB_buttonUp{ background-position: -80px -72px; }
	
	.mCS-rounded-dark.mCSB_scrollTools .mCSB_buttonDown{ background-position: -80px -92px; }
	
	.mCS-rounded-dark.mCSB_scrollTools .mCSB_buttonLeft{ background-position: -80px -112px; }
	
	.mCS-rounded-dark.mCSB_scrollTools .mCSB_buttonRight{ background-position: -80px -128px; }
	
	
	/* theme "rounded-dots", "rounded-dots-dark" */
	
	.mCS-rounded-dots.mCSB_scrollTools_vertical .mCSB_draggerRail, 
	.mCS-rounded-dots-dark.mCSB_scrollTools_vertical .mCSB_draggerRail{ width: 4px; }
	
	.mCS-rounded-dots.mCSB_scrollTools .mCSB_draggerRail, 
	.mCS-rounded-dots-dark.mCSB_scrollTools .mCSB_draggerRail, 
	.mCS-rounded-dots.mCSB_scrollTools_horizontal .mCSB_draggerRail, 
	.mCS-rounded-dots-dark.mCSB_scrollTools_horizontal .mCSB_draggerRail{
		background-color: transparent;
		background-position: center;
	}
	
	.mCS-rounded-dots.mCSB_scrollTools .mCSB_draggerRail, 
	.mCS-rounded-dots-dark.mCSB_scrollTools .mCSB_draggerRail{
		background-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAgAAAAICAYAAADED76LAAAANElEQVQYV2NkIAAYiVbw//9/Y6DiM1ANJoyMjGdBbLgJQAX/kU0DKgDLkaQAvxW4HEvQFwCRcxIJK1XznAAAAABJRU5ErkJggg==");
		background-repeat: repeat-y;
		opacity: 0.3;
		filter: "alpha(opacity=30)"; -ms-filter: "alpha(opacity=30)"; 
	}
	
	.mCS-rounded-dots.mCSB_scrollTools_horizontal .mCSB_draggerRail, 
	.mCS-rounded-dots-dark.mCSB_scrollTools_horizontal .mCSB_draggerRail{
		height: 4px;
		margin: 6px 0;
		background-repeat: repeat-x;
	}
	
	.mCS-rounded-dots.mCSB_scrollTools .mCSB_buttonUp{ background-position: -16px -72px; }
	
	.mCS-rounded-dots.mCSB_scrollTools .mCSB_buttonDown{ background-position: -16px -92px; }
	
	.mCS-rounded-dots.mCSB_scrollTools .mCSB_buttonLeft{ background-position: -20px -112px; }
	
	.mCS-rounded-dots.mCSB_scrollTools .mCSB_buttonRight{ background-position: -20px -128px; }
	
	
	/* theme "rounded-dots-dark" */
	
	.mCS-rounded-dots-dark.mCSB_scrollTools .mCSB_draggerRail{
		background-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAgAAAAICAYAAADED76LAAAALElEQVQYV2NkIAAYSVFgDFR8BqrBBEifBbGRTfiPZhpYjiQFBK3A6l6CvgAAE9kGCd1mvgEAAAAASUVORK5CYII=");
	}
	
	.mCS-rounded-dots-dark.mCSB_scrollTools .mCSB_buttonUp{ background-position: -96px -72px; }
	
	.mCS-rounded-dots-dark.mCSB_scrollTools .mCSB_buttonDown{ background-position: -96px -92px; }
	
	.mCS-rounded-dots-dark.mCSB_scrollTools .mCSB_buttonLeft{ background-position: -100px -112px; }
	
	.mCS-rounded-dots-dark.mCSB_scrollTools .mCSB_buttonRight{ background-position: -100px -128px; }
	
	/* ---------------------------------------- */
	
	
	
	/* theme "3d", "3d-dark", "3d-thick", "3d-thick-dark" */
	
	.mCS-3d.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-3d-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-3d-thick.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-3d-thick-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{
		background-repeat: repeat-y;
		background-image: -moz-linear-gradient(left, rgba(255,255,255,0.5) 0%, rgba(255,255,255,0) 100%);
		background-image: -webkit-gradient(linear, left top, right top, color-stop(0%,rgba(255,255,255,0.5)), color-stop(100%,rgba(255,255,255,0)));
		background-image: -webkit-linear-gradient(left, rgba(255,255,255,0.5) 0%,rgba(255,255,255,0) 100%);
		background-image: -o-linear-gradient(left, rgba(255,255,255,0.5) 0%,rgba(255,255,255,0) 100%);
		background-image: -ms-linear-gradient(left, rgba(255,255,255,0.5) 0%,rgba(255,255,255,0) 100%);
		background-image: linear-gradient(to right, rgba(255,255,255,0.5) 0%,rgba(255,255,255,0) 100%);
	}
	
	.mCS-3d.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-3d-dark.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-3d-thick.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-3d-thick-dark.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar{
		background-repeat: repeat-x;
		background-image: -moz-linear-gradient(top, rgba(255,255,255,0.5) 0%, rgba(255,255,255,0) 100%);
		background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,0.5)), color-stop(100%,rgba(255,255,255,0)));
		background-image: -webkit-linear-gradient(top, rgba(255,255,255,0.5) 0%,rgba(255,255,255,0) 100%);
		background-image: -o-linear-gradient(top, rgba(255,255,255,0.5) 0%,rgba(255,255,255,0) 100%);
		background-image: -ms-linear-gradient(top, rgba(255,255,255,0.5) 0%,rgba(255,255,255,0) 100%);
		background-image: linear-gradient(to bottom, rgba(255,255,255,0.5) 0%,rgba(255,255,255,0) 100%);
	}
	
	
	/* theme "3d", "3d-dark" */
	
	.mCS-3d.mCSB_scrollTools_vertical .mCSB_dragger, 
	.mCS-3d-dark.mCSB_scrollTools_vertical .mCSB_dragger{ height: 70px; }
	
	.mCS-3d.mCSB_scrollTools_horizontal .mCSB_dragger, 
	.mCS-3d-dark.mCSB_scrollTools_horizontal .mCSB_dragger{ width: 70px; }
	
	.mCS-3d.mCSB_scrollTools, 
	.mCS-3d-dark.mCSB_scrollTools{
		opacity: 1;
		filter: "alpha(opacity=30)"; -ms-filter: "alpha(opacity=30)"; 
	}
	
	.mCS-3d.mCSB_scrollTools .mCSB_draggerRail, 
	.mCS-3d.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-3d-dark.mCSB_scrollTools .mCSB_draggerRail, 
	.mCS-3d-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{ -webkit-border-radius: 16px; -moz-border-radius: 16px; border-radius: 16px; }
	
	.mCS-3d.mCSB_scrollTools .mCSB_draggerRail, 
	.mCS-3d-dark.mCSB_scrollTools .mCSB_draggerRail{
		width: 8px;
		background-color: #000; background-color: rgba(0,0,0,0.2);
		box-shadow: inset 1px 0 1px rgba(0,0,0,0.5), inset -1px 0 1px rgba(255,255,255,0.2);
	}
	
	.mCS-3d.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 	 
	.mCS-3d.mCSB_scrollTools .mCSB_dragger:hover .mCSB_dragger_bar, 
	.mCS-3d.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCS-3d.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar, 
	.mCS-3d-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-3d-dark.mCSB_scrollTools .mCSB_dragger:hover .mCSB_dragger_bar, 
	.mCS-3d-dark.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCS-3d-dark.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar{ background-color: #555; }

	.mCS-3d.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-3d-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{ width: 8px; }

	.mCS-3d.mCSB_scrollTools_horizontal .mCSB_draggerRail, 
	.mCS-3d-dark.mCSB_scrollTools_horizontal .mCSB_draggerRail{
		width: 100%;
		height: 8px;
		margin: 4px 0;
		box-shadow: inset 0 1px 1px rgba(0,0,0,0.5), inset 0 -1px 1px rgba(255,255,255,0.2);
	}

	.mCS-3d.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-3d-dark.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar{
		width: 100%;
		height: 8px;
		margin: 4px auto;
	}
	
	.mCS-3d.mCSB_scrollTools .mCSB_buttonUp{ background-position: -32px -72px; }
	
	.mCS-3d.mCSB_scrollTools .mCSB_buttonDown{ background-position: -32px -92px; }
	
	.mCS-3d.mCSB_scrollTools .mCSB_buttonLeft{ background-position: -40px -112px; }
	
	.mCS-3d.mCSB_scrollTools .mCSB_buttonRight{ background-position: -40px -128px; }
	
	
	/* theme "3d-dark" */
	
	.mCS-3d-dark.mCSB_scrollTools .mCSB_draggerRail{
		background-color: #000; background-color: rgba(0,0,0,0.1);
		box-shadow: inset 1px 0 1px rgba(0,0,0,0.1);
	}
	
	.mCS-3d-dark.mCSB_scrollTools_horizontal .mCSB_draggerRail{ box-shadow: inset 0 1px 1px rgba(0,0,0,0.1); }
	
	.mCS-3d-dark.mCSB_scrollTools .mCSB_buttonUp{ background-position: -112px -72px; }

	.mCS-3d-dark.mCSB_scrollTools .mCSB_buttonDown{ background-position: -112px -92px; }

	.mCS-3d-dark.mCSB_scrollTools .mCSB_buttonLeft{ background-position: -120px -112px; }

	.mCS-3d-dark.mCSB_scrollTools .mCSB_buttonRight{	background-position: -120px -128px; }
	
	/* ---------------------------------------- */
	
	
	
	/* theme: "3d-thick", "3d-thick-dark" */
	
	.mCS-3d-thick.mCSB_scrollTools, 
	.mCS-3d-thick-dark.mCSB_scrollTools{
		opacity: 1;
		filter: "alpha(opacity=30)"; -ms-filter: "alpha(opacity=30)"; 
	}
	
	.mCS-3d-thick.mCSB_scrollTools, 
	.mCS-3d-thick-dark.mCSB_scrollTools, 
	.mCS-3d-thick.mCSB_scrollTools .mCSB_draggerContainer, 
	.mCS-3d-thick-dark.mCSB_scrollTools .mCSB_draggerContainer{ -webkit-border-radius: 7px; -moz-border-radius: 7px; border-radius: 7px; }
	
	.mCS-3d-thick.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-3d-thick-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{ -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px; }
	
	.mCSB_inside + .mCS-3d-thick.mCSB_scrollTools_vertical, 
	.mCSB_inside + .mCS-3d-thick-dark.mCSB_scrollTools_vertical{ right: 1px; }
	
	.mCS-3d-thick.mCSB_scrollTools_vertical, 
	.mCS-3d-thick-dark.mCSB_scrollTools_vertical{ box-shadow: inset 1px 0 1px rgba(0,0,0,0.1), inset 0 0 14px rgba(0,0,0,0.5); }
	
	.mCS-3d-thick.mCSB_scrollTools_horizontal, 
	.mCS-3d-thick-dark.mCSB_scrollTools_horizontal{
		bottom: 1px;
		box-shadow: inset 0 1px 1px rgba(0,0,0,0.1), inset 0 0 14px rgba(0,0,0,0.5);
	}
	
	.mCS-3d-thick.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-3d-thick-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{
		box-shadow: inset 1px 0 0 rgba(255,255,255,0.4);
		width: 12px;
		margin: 2px;
		position: absolute;
		height: auto;
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;
	}
	
	.mCS-3d-thick.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-3d-thick-dark.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar{ box-shadow: inset 0 1px 0 rgba(255,255,255,0.4); }
	
	.mCS-3d-thick.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar,  
	.mCS-3d-thick.mCSB_scrollTools .mCSB_dragger:hover .mCSB_dragger_bar, 
	.mCS-3d-thick.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCS-3d-thick.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar{ background-color: #555; }
	
	.mCS-3d-thick.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-3d-thick-dark.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar{
		height: 12px;
		width: auto;
	}
	
	.mCS-3d-thick.mCSB_scrollTools .mCSB_draggerContainer{
		background-color: #000; background-color: rgba(0,0,0,0.05);
		box-shadow: inset 1px 1px 16px rgba(0,0,0,0.1);
	}
	
	.mCS-3d-thick.mCSB_scrollTools .mCSB_draggerRail{ background-color: transparent; }
	
	.mCS-3d-thick.mCSB_scrollTools .mCSB_buttonUp{ background-position: -32px -72px; }
	
	.mCS-3d-thick.mCSB_scrollTools .mCSB_buttonDown{ background-position: -32px -92px; }

	.mCS-3d-thick.mCSB_scrollTools .mCSB_buttonLeft{ background-position: -40px -112px; }

	.mCS-3d-thick.mCSB_scrollTools .mCSB_buttonRight{	background-position: -40px -128px; }
	
	
	/* theme: "3d-thick-dark" */
	
	.mCS-3d-thick-dark.mCSB_scrollTools{ box-shadow: inset 0 0 14px rgba(0,0,0,0.2); }
	
	.mCS-3d-thick-dark.mCSB_scrollTools_horizontal{ box-shadow: inset 0 1px 1px rgba(0,0,0,0.1), inset 0 0 14px rgba(0,0,0,0.2); }
	
	.mCS-3d-thick-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{ box-shadow: inset 1px 0 0 rgba(255,255,255,0.4), inset -1px 0 0 rgba(0,0,0,0.2); }
	 
	.mCS-3d-thick-dark.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar{ box-shadow: inset 0 1px 0 rgba(255,255,255,0.4), inset 0 -1px 0 rgba(0,0,0,0.2); }
	
	.mCS-3d-thick-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar,  
	.mCS-3d-thick-dark.mCSB_scrollTools .mCSB_dragger:hover .mCSB_dragger_bar, 
	.mCS-3d-thick-dark.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCS-3d-thick-dark.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar{ background-color: #777; }
	
	.mCS-3d-thick-dark.mCSB_scrollTools .mCSB_draggerContainer{
		background-color: #fff; background-color: rgba(0,0,0,0.05);
		box-shadow: inset 1px 1px 16px rgba(0,0,0,0.1);
	}
	
	.mCS-3d-thick-dark.mCSB_scrollTools .mCSB_draggerRail{ background-color: transparent; }
	
	.mCS-3d-thick-dark.mCSB_scrollTools .mCSB_buttonUp{ background-position: -112px -72px; }
	
	.mCS-3d-thick-dark.mCSB_scrollTools .mCSB_buttonDown{ background-position: -112px -92px; }

	.mCS-3d-thick-dark.mCSB_scrollTools .mCSB_buttonLeft{ background-position: -120px -112px; }

	.mCS-3d-thick-dark.mCSB_scrollTools .mCSB_buttonRight{	background-position: -120px -128px; }
	
	/* ---------------------------------------- */
	
	
	
	/* theme: "minimal", "minimal-dark" */
	
	.mCSB_outside + .mCS-minimal.mCSB_scrollTools_vertical, 
	.mCSB_outside + .mCS-minimal-dark.mCSB_scrollTools_vertical{
		right: 0; 
		margin: 12px 0; 
	}
	
	.mCustomScrollBox.mCS-minimal + .mCSB_scrollTools.mCSB_scrollTools_horizontal, 
	.mCustomScrollBox.mCS-minimal + .mCSB_scrollTools + .mCSB_scrollTools.mCSB_scrollTools_horizontal, 
	.mCustomScrollBox.mCS-minimal-dark + .mCSB_scrollTools.mCSB_scrollTools_horizontal, 
	.mCustomScrollBox.mCS-minimal-dark + .mCSB_scrollTools + .mCSB_scrollTools.mCSB_scrollTools_horizontal{
		bottom: 0; 
		margin: 0 12px; 
	}
	
	/* RTL direction/left-side scrollbar */
	.mCS-dir-rtl > .mCSB_outside + .mCS-minimal.mCSB_scrollTools_vertical, 
	.mCS-dir-rtl > .mCSB_outside + .mCS-minimal-dark.mCSB_scrollTools_vertical{
		left: 0; 
		right: auto;
	}
	
	.mCS-minimal.mCSB_scrollTools .mCSB_draggerRail, 
	.mCS-minimal-dark.mCSB_scrollTools .mCSB_draggerRail{ background-color: transparent; }
	
	.mCS-minimal.mCSB_scrollTools_vertical .mCSB_dragger, 
	.mCS-minimal-dark.mCSB_scrollTools_vertical .mCSB_dragger{ height: 50px; }
	
	.mCS-minimal.mCSB_scrollTools_horizontal .mCSB_dragger, 
	.mCS-minimal-dark.mCSB_scrollTools_horizontal .mCSB_dragger{ width: 50px; }
	
	.mCS-minimal.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{
		background-color: #fff; background-color: rgba(255,255,255,0.2);
		filter: "alpha(opacity=20)"; -ms-filter: "alpha(opacity=20)"; 
	}
	
	.mCS-minimal.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCS-minimal.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar{
		background-color: #fff; background-color: rgba(255,255,255,0.5);
		filter: "alpha(opacity=50)"; -ms-filter: "alpha(opacity=50)"; 
	}
	
	
	/* theme: "minimal-dark" */
	
	.mCS-minimal-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{
		background-color: #000; background-color: rgba(0,0,0,0.2);
		filter: "alpha(opacity=20)"; -ms-filter: "alpha(opacity=20)"; 
	}
	
	.mCS-minimal-dark.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCS-minimal-dark.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar{
		background-color: #000; background-color: rgba(0,0,0,0.5);
		filter: "alpha(opacity=50)"; -ms-filter: "alpha(opacity=50)"; 
	}
	
	/* ---------------------------------------- */
	
	
	
	/* theme "light-3", "dark-3" */
	
	.mCS-light-3.mCSB_scrollTools .mCSB_draggerRail, 
	.mCS-dark-3.mCSB_scrollTools .mCSB_draggerRail{
		width: 6px;
		background-color: #000; background-color: rgba(0,0,0,0.2);
	}

	.mCS-light-3.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-dark-3.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{ width: 6px; }

	.mCS-light-3.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-dark-3.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-light-3.mCSB_scrollTools_horizontal .mCSB_draggerRail, 
	.mCS-dark-3.mCSB_scrollTools_horizontal .mCSB_draggerRail{
		width: 100%;
		height: 6px;
		margin: 5px 0;
	}
	
	.mCS-light-3.mCSB_scrollTools_vertical.mCSB_scrollTools_onDrag_expand .mCSB_dragger.mCSB_dragger_onDrag_expanded + .mCSB_draggerRail, 
	.mCS-light-3.mCSB_scrollTools_vertical.mCSB_scrollTools_onDrag_expand .mCSB_draggerContainer:hover .mCSB_draggerRail, 
	.mCS-dark-3.mCSB_scrollTools_vertical.mCSB_scrollTools_onDrag_expand .mCSB_dragger.mCSB_dragger_onDrag_expanded + .mCSB_draggerRail, 
	.mCS-dark-3.mCSB_scrollTools_vertical.mCSB_scrollTools_onDrag_expand .mCSB_draggerContainer:hover .mCSB_draggerRail{
		width: 12px;
	}
	
	.mCS-light-3.mCSB_scrollTools_horizontal.mCSB_scrollTools_onDrag_expand .mCSB_dragger.mCSB_dragger_onDrag_expanded + .mCSB_draggerRail, 
	.mCS-light-3.mCSB_scrollTools_horizontal.mCSB_scrollTools_onDrag_expand .mCSB_draggerContainer:hover .mCSB_draggerRail, 
	.mCS-dark-3.mCSB_scrollTools_horizontal.mCSB_scrollTools_onDrag_expand .mCSB_dragger.mCSB_dragger_onDrag_expanded + .mCSB_draggerRail, 
	.mCS-dark-3.mCSB_scrollTools_horizontal.mCSB_scrollTools_onDrag_expand .mCSB_draggerContainer:hover .mCSB_draggerRail{
		height: 12px;
		margin: 2px 0;
	}
	
	.mCS-light-3.mCSB_scrollTools .mCSB_buttonUp{ background-position: -32px -72px; }
	
	.mCS-light-3.mCSB_scrollTools .mCSB_buttonDown{ background-position: -32px -92px; }
	
	.mCS-light-3.mCSB_scrollTools .mCSB_buttonLeft{ background-position: -40px -112px; }
	
	.mCS-light-3.mCSB_scrollTools .mCSB_buttonRight{ background-position: -40px -128px; }
	
	
	/* theme "dark-3" */
	
	.mCS-dark-3.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{ background-color: #000; background-color: rgba(0,0,0,0.75); }

	.mCS-dark-3.mCSB_scrollTools .mCSB_dragger:hover .mCSB_dragger_bar{ background-color: #000; background-color: rgba(0,0,0,0.85); }

	.mCS-dark-3.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCS-dark-3.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar{ background-color: #000; background-color: rgba(0,0,0,0.9); }
	
	.mCS-dark-3.mCSB_scrollTools .mCSB_draggerRail{ background-color: #000; background-color: rgba(0,0,0,0.1); }
	
	.mCS-dark-3.mCSB_scrollTools .mCSB_buttonUp{ background-position: -112px -72px; }

	.mCS-dark-3.mCSB_scrollTools .mCSB_buttonDown{ background-position: -112px -92px; }

	.mCS-dark-3.mCSB_scrollTools .mCSB_buttonLeft{ background-position: -120px -112px; }

	.mCS-dark-3.mCSB_scrollTools .mCSB_buttonRight{	background-position: -120px -128px; }
	
	/* ---------------------------------------- */
	
	
	
	/* theme "inset", "inset-dark", "inset-2", "inset-2-dark", "inset-3", "inset-3-dark" */
	
	.mCS-inset.mCSB_scrollTools .mCSB_draggerRail, 
	.mCS-inset-dark.mCSB_scrollTools .mCSB_draggerRail, 
	.mCS-inset-2.mCSB_scrollTools .mCSB_draggerRail, 
	.mCS-inset-2-dark.mCSB_scrollTools .mCSB_draggerRail, 
	.mCS-inset-3.mCSB_scrollTools .mCSB_draggerRail, 
	.mCS-inset-3-dark.mCSB_scrollTools .mCSB_draggerRail{
		width: 12px;
		background-color: #000; background-color: rgba(0,0,0,0.2);
	}

	.mCS-inset.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-inset-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-inset-2.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-inset-2-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-inset-3.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-inset-3-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{ 
		width: 6px;
		margin: 3px 5px;
		position: absolute;
		height: auto;
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;
	}

	.mCS-inset.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-inset-dark.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-inset-2.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-inset-2-dark.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-inset-3.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-inset-3-dark.mCSB_scrollTools_horizontal .mCSB_dragger .mCSB_dragger_bar{
		height: 6px;
		margin: 5px 3px;
		position: absolute;
		width: auto;
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;
	}
	
	.mCS-inset.mCSB_scrollTools_horizontal .mCSB_draggerRail, 
	.mCS-inset-dark.mCSB_scrollTools_horizontal .mCSB_draggerRail, 
	.mCS-inset-2.mCSB_scrollTools_horizontal .mCSB_draggerRail, 
	.mCS-inset-2-dark.mCSB_scrollTools_horizontal .mCSB_draggerRail, 
	.mCS-inset-3.mCSB_scrollTools_horizontal .mCSB_draggerRail, 
	.mCS-inset-3-dark.mCSB_scrollTools_horizontal .mCSB_draggerRail{
		width: 100%;
		height: 12px;
		margin: 2px 0;
	}
	
	.mCS-inset.mCSB_scrollTools .mCSB_buttonUp, 
	.mCS-inset-2.mCSB_scrollTools .mCSB_buttonUp, 
	.mCS-inset-3.mCSB_scrollTools .mCSB_buttonUp{ background-position: -32px -72px; }
	
	.mCS-inset.mCSB_scrollTools .mCSB_buttonDown, 
	.mCS-inset-2.mCSB_scrollTools .mCSB_buttonDown, 
	.mCS-inset-3.mCSB_scrollTools .mCSB_buttonDown{ background-position: -32px -92px; }
	
	.mCS-inset.mCSB_scrollTools .mCSB_buttonLeft, 
	.mCS-inset-2.mCSB_scrollTools .mCSB_buttonLeft, 
	.mCS-inset-3.mCSB_scrollTools .mCSB_buttonLeft{ background-position: -40px -112px; }
	
	.mCS-inset.mCSB_scrollTools .mCSB_buttonRight, 
	.mCS-inset-2.mCSB_scrollTools .mCSB_buttonRight, 
	.mCS-inset-3.mCSB_scrollTools .mCSB_buttonRight{ background-position: -40px -128px; }
	
	
	/* theme "inset-dark", "inset-2-dark", "inset-3-dark" */
	
	.mCS-inset-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-inset-2-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar, 
	.mCS-inset-3-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{ background-color: #000; background-color: rgba(0,0,0,0.75); }

	.mCS-inset-dark.mCSB_scrollTools .mCSB_dragger:hover .mCSB_dragger_bar, 
	.mCS-inset-2-dark.mCSB_scrollTools .mCSB_dragger:hover .mCSB_dragger_bar, 
	.mCS-inset-3-dark.mCSB_scrollTools .mCSB_dragger:hover .mCSB_dragger_bar{ background-color: #000; background-color: rgba(0,0,0,0.85); }

	.mCS-inset-dark.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCS-inset-dark.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar, 
	.mCS-inset-2-dark.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCS-inset-2-dark.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar, 
	.mCS-inset-3-dark.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCS-inset-3-dark.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar{ background-color: #000; background-color: rgba(0,0,0,0.9); }
	
	.mCS-inset-dark.mCSB_scrollTools .mCSB_draggerRail, 
	.mCS-inset-2-dark.mCSB_scrollTools .mCSB_draggerRail, 
	.mCS-inset-3-dark.mCSB_scrollTools .mCSB_draggerRail{ background-color: #000; background-color: rgba(0,0,0,0.1); }
	
	.mCS-inset-dark.mCSB_scrollTools .mCSB_buttonUp, 
	.mCS-inset-2-dark.mCSB_scrollTools .mCSB_buttonUp, 
	.mCS-inset-3-dark.mCSB_scrollTools .mCSB_buttonUp{ background-position: -112px -72px; }

	.mCS-inset-dark.mCSB_scrollTools .mCSB_buttonDown, 
	.mCS-inset-2-dark.mCSB_scrollTools .mCSB_buttonDown, 
	.mCS-inset-3-dark.mCSB_scrollTools .mCSB_buttonDown{ background-position: -112px -92px; }

	.mCS-inset-dark.mCSB_scrollTools .mCSB_buttonLeft, 
	.mCS-inset-2-dark.mCSB_scrollTools .mCSB_buttonLeft, 
	.mCS-inset-3-dark.mCSB_scrollTools .mCSB_buttonLeft{ background-position: -120px -112px; }

	.mCS-inset-dark.mCSB_scrollTools .mCSB_buttonRight, 
	.mCS-inset-2-dark.mCSB_scrollTools .mCSB_buttonRight, 
	.mCS-inset-3-dark.mCSB_scrollTools .mCSB_buttonRight{	background-position: -120px -128px; }
	
	
	/* theme "inset-2", "inset-2-dark" */
	
	.mCS-inset-2.mCSB_scrollTools .mCSB_draggerRail, 
	.mCS-inset-2-dark.mCSB_scrollTools .mCSB_draggerRail{
		background-color: transparent;
		border-width: 1px;
		border-style: solid;
		border-color: #fff;
		border-color: rgba(255,255,255,0.2);
		-webkit-box-sizing: border-box; -moz-box-sizing: border-box; box-sizing: border-box;
	}
	
	.mCS-inset-2-dark.mCSB_scrollTools .mCSB_draggerRail{ border-color: #000; border-color: rgba(0,0,0,0.2); }
	
	
	/* theme "inset-3", "inset-3-dark" */
	
	.mCS-inset-3.mCSB_scrollTools .mCSB_draggerRail{ background-color: #fff; background-color: rgba(255,255,255,0.6); }
	
	.mCS-inset-3-dark.mCSB_scrollTools .mCSB_draggerRail{ background-color: #000; background-color: rgba(0,0,0,0.6); }
	
	.mCS-inset-3.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{ background-color: #000; background-color: rgba(0,0,0,0.75); }
	
	.mCS-inset-3.mCSB_scrollTools .mCSB_dragger:hover .mCSB_dragger_bar{ background-color: #000; background-color: rgba(0,0,0,0.85); }
	
	.mCS-inset-3.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCS-inset-3.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar{ background-color: #000; background-color: rgba(0,0,0,0.9); }
	
	.mCS-inset-3-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar{ background-color: #fff; background-color: rgba(255,255,255,0.75); }
	
	.mCS-inset-3-dark.mCSB_scrollTools .mCSB_dragger:hover .mCSB_dragger_bar{ background-color: #fff; background-color: rgba(255,255,255,0.85); }
	
	.mCS-inset-3-dark.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
	.mCS-inset-3-dark.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar{ background-color: #fff; background-color: rgba(255,255,255,0.9); }
	
	/* ---------------------------------------- */
