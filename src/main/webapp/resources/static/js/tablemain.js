/**
 * Created by Administrator on 2017/3/9.
 */
require.config({
    paths:{
        jquery:"./lib/jquery-2.2.3.min",
        nicescroll:"./lib/jquery.nicescroll",
        Valid:"./lib/Validform_v5.3.2_min",
        upLoad:"./lib/jquery.uploadView",
        tableForm:"tableForm"
    }
})
require(['jquery','nicescroll','Valid','upLoad','tableForm'],function($,nicescroll,Valid,upLoad,tableForm){
	$(function(){
		 $("html").niceScroll({
			   styler:"fb",
			   cursorcolor:"#cccccc",
			   cursorwidth: '5',
		       cursorborderradius: '5px',
			   background: '',
			   autohidemode: false, 
			   spacebarenabled:false, 
			   cursorborder: '0', 
			   zindex: '1000'
        });
		tableForm.P_form(); 
		var inputXt = $("input[class*=inputxt]");
		var inputSt = $("select[class*=inputxt]");
		inputXt.each(function(){
			That=$(this);
			Tlib ="请填写";
			messaggTip(That,Tlib);        
	    });
		inputSt.each(function(){
			That=$(this);
			Tlib ="请选择";
			messaggTip(That,Tlib);
		});
		function messaggTip(That,Tlib){
			var Tagtrue = That.attr("auto_color_flag");
			var titleLabel = That.parent("td").prev("td").text();
			if(Tagtrue){
				if(!That.attr("nullmsg")){
					That.attr("nullmsg",Tlib+titleLabel);
					That.parent("td").prev("td").prepend("<i class='i-red'>*&nbsp;</i>");
				}
			 }else{
				 That.removeAttr("datatype","nullmsg","errormsg");
			}
		};
		tableForm.Up_img();

	});
	
});










	



