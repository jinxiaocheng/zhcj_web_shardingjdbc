/**
 * Created by Administrator on 2017/3/9.
 */
define(["jquery"],function(require){
	return tableForm = {
			  // 人员管理
			  P_form:function(){
				var P_userform=$("#personform").Validform({
					btnSubmit:"#personform_btn", 
			        tiptype:3,
			        label:".label",
			        showAllError:false,
			        datatype:{
			            "zh1-6" : /^[\u4E00-\u9FA5\uf900-\ufa2d]{1,6}$/,
			            "idCards" :/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
			            "telphone" :/(^[0-9]{3,4}[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/
			        },
			        ajaxPost:true,
			        callback : function(data) {
//			            if(data.status=="y"){
//			                alert(data.message);
//			                location.href = "ShipInfo/shipInfoListAction.do";
//			            }else{
//			                alert(data.message);
//			            }
			        }
			    });
				//$("#personform_btn").click(function(){
					//layer_close();
				//});
				/*关闭弹出框口*/
				function layer_close(){
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				};
			},
			//图片上传
			Up_img:function(){
				/*系统设置图片上传*/
				(function(){
				   show();	
				   var str = null;
				   maxBox();
				   $("#cate-add").click(function(){
					  str = '<li><div class="js_uploadBox"><div class="up_img js_showBox"><img src="" /></div><div class="up_img_zl"><a href="javascript:;" class="file btn-show-blue">上传证书<input type="file" name="" class="js_upFile" /></a></div></div><div class="tip-file"></div></li>';
					  $("#show-content-img").append(str);
					  maxBox(); 
				  	  show();
					  bindListener();	
					  $("body").animate({
						  'scrollTop' : "+=200px"
					  }, 200);
				    });
				    function bindListener(){
				       $(".tip-file").unbind().click(function(){
			              $(this).parents('li').remove();
			              maxBox(); 
			           });
			        };
			        function maxBox(){
				    	 if($("#show-content-img>li").length<1){
							$("#more-img-scroll").css("border","none");
						 }else{
							$("#more-img-scroll").css("border","1px solid #ccc");
						 }
				    };  
				    bindListener();
				    function show(){
			            $(".js_upFile").uploadView({
				        uploadBox: '.js_uploadBox',//设置上传框容器
				        showBox : '.js_showBox',//设置显示预览图片的容器
				        width : 100, //预览图片的宽度，单位px
				        height : 100, //预览图片的高度，单位px
				        allowType: ["gif", "jpeg", "jpg", "bmp", "png"], //允许上传图片的类型
				        maxSize :2, //允许上传图片的最大尺寸，单位M
				        success:function(e){
//				          alert('图片上传成功');
				        }
			          });
			       };
			       
				})();
			},
	}
       
   
});











