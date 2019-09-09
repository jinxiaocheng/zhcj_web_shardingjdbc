<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>导入进度计划</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/tableform.css?v=${v}" rel="stylesheet" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
</head>

<body>
      <div class="up_load_execl">  
         <a href="javascript:;" class="file btn-show-blue">上传文件
           <input id="file_id" name="file" type="file" accept=".xlsx, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" />
         </a>
         <span class="tip-file" id="textName"></span>
      </div>  
      <div class="footer_btn"><a class="btn btn_submit btn-show-blue" id="do_import_btn">提交</a></div>
      <script>
          var ctx = "${ctx}";
          var userId = "${userId}";
          var constructionId='${constructionId}';
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
      <script>

          $(function(){
              $("#do_import_btn").on("click",function(){
                  var file = $("#file_id").val();
                  if (file == "") {
                      layer.msg('请选择上传的文件！', {time: 2000, icon: 0});
                      return;
                  }
                  var url = ctx + "/scheduledPlan/saveFile";
                  var data = {"constructionId" : constructionId};
                  $.ajaxFileUpload({
                      url:url,                              //后台请求地址
                      data:data,                            //自定义参数
                      secureuri:false,                      //是否启用安全提交，默认为false
                      type:"post",                          //当要提交自定义参数时，这个参数要设置成post
                      fileElementId:"file_id",              //需要上传的文件域的ID
                      dataType: 'text',                     //服务器返回的数据类型
                      success: function (data, status) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                          data = $.parseJSON(data)
                          if(data.status == 1){
                              layer.alert("数据导入成功！",function(){layer_close();});
                          } else {
                              layer.alert(data.msg);
                          }
                      },
                      error: function (data, status, e) {   //提交失败自动执行的处理函数
                          layer.alert(String(e));
                      }
                  });

              });

              $("#file_id").change(function(){
                  $(".up_load_execl").css("background","none");
                  $("#textName").html($(this).val());
              });

              /*关闭弹出框口*/
              function layer_close(){
                  parent.tableReload(0);
                  parent.layer.closeAll();
              }
          });
</script>
</body>
</html>
