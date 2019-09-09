<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>检查部位</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/tableform.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/accident/add.css?v=${v}">
	<!--[if lt IE 9]>
	<script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
	<script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
	<script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
	<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
	<![endif]-->
</head>

<body>
	<input type="hidden" id="parentId" value="${parentId }"/>
			<div class="row add_list ">
		        <div class="form-group col-xs-4 area">
		            <label  class="col-xs-4">区域</label>
		            <button class="btn btn-default dropdown-toggle col-xs-8" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
		                请选择区域
		                <span class="caret"></span>
		            </button>
		            <ul class="dropdown-menu" aria-labelledby="dropdownMenu3">
		            </ul>
		        </div>
		        <div class="form-group col-xs-4 site">
		            <label  class="col-xs-4">工地</label>
		            <button class="btn btn-default dropdown-toggle col-xs-8" id="dropdownMenu4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
		                请选择工地
		                <span class="caret"></span>
		            </button>
		            <ul class="dropdown-menu" aria-labelledby="dropdownMenu4">
		            </ul>
		        </div>
		</div>
      <div class="up_load_execl">  
         <a href="javascript:;" class="file btn-show-blue">上传文件
           <input id="file_id" name="file" type="file" accept=".xlsx, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" />
         </a>
         <span class="tip-file" id="textName"></span>
      </div>  
      <div class="footer_btn"><a class="btn btn_submit btn-show-blue" id="do_import_btn">提交</a></div>
      <%--警告--%>
	<div class="alert alert-danger alert-dismissable error">
	    <button type="button" class="close" aria-hidden="true">
	        &times;
	    </button>
	    <span>警告！请不要提交。</span>
	</div>
      <script>
          var ctx = "${ctx}";
          var userId = "${userId}";
      </script>
	<!--[if gte IE 9]>
	<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
	<![endif]-->
	<![if !IE]>
	<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
	<![endif]>
      <script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
	  <script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
      <script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
      <script type="text/javascript" src="${ctx}/resources/static/upload/ajaxfileupload.js?v=${v}"></script>
      <script  type="text/javascript"src="${ctx}/resources/static/js/common/area.js?v=${v}"></script>
      <script>
    //提示框
      $(".close").click(function(){
          $(".error").fadeOut("fast");
      });
    
      	function id(a) {
    	//a代表工地id，将你们需要执行的函数放入其中
    	}
    	init(id);
    	
          $(function(){
              $("#do_import_btn").on("click",function(){
            	  if(constructionId==""||constructionId==null){
                      $(".error").fadeIn("fast");
                      $(".error span").html("请先选择工地！");
                      return
                  }
                  var file = $("#file_id").val();
                  if (file == "") {
                      layer.msg('请选择上传的文件！', {time: 2000, icon: 0});
                      return;
                  }
                  var url = ctx + "/projectStructure/saveFile";
                  var data = {"constructionId" : constructionId,"parentId":$("#parentId").val()};
                  $.ajaxFileUpload({
                      url:url,                              //后台请求地址
                      data:data,                            //自定义参数
                      secureuri:false,                      //是否启用安全提交，默认为false
                      type:"post",                          //当要提交自定义参数时，这个参数要设置成post
                      fileElementId:"file_id",              //需要上传的文件域的ID
                      dataType: 'json',                     //服务器返回的数据类型
                      success: function (data, status) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                          if(data.status == 1){
                              alert("数据导入成功！");
                              layer_close();
                          } else {
                              alert(data.msg);
                          }
                      },
                      error: function (data, status, e) {   //提交失败自动执行的处理函数
                          alert(e);
                      }
                  });

              });

              $("#file_id").change(function(){
            	  $(".up_load_execl").css("background","none");
            	  $("#textName").html($(this).val());
              });

              /*关闭弹出框口*/
              function layer_close(){
                  var index = parent.layer.getFrameIndex(window.name);
                  parent.layer.close(index);
              }
          });
      </script>
</body>
</html>
