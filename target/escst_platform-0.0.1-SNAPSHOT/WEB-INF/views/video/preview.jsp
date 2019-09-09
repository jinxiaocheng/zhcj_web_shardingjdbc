<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<%@ include file="/common/common_tag.jsp"%>--%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>视频显示</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/layer/skin/layer.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/video/preview.css?v=${v}" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
  </head>
  <style>
  html,body{
    widht:100%;
    height:100%;
  }
  </style>
<body>

  <div class="container-full">

    <div class="menu-left-tree  fl">
        <ul id="orgnaizeTree" class="ztree"></ul>
    </div>
    <div class="videoplay">
      <a href="${ctx}/resources/cmsocx.exe" style="position: absolute;top: 40%;left: 50%;margin-left: -50px;letter-spacing: 1px;">点击下载视频插件</a>
         <object class="js-vss-ocx" classid="clsid:9ECD2A40-1222-432E-A4D4-154C7CAB9DE3" id="previewocx" name="previewocx" width="100%" height="100%">
         </object>
    </div>
  </div>
  <script>
      var ctx = "${ctx}",ver="${v}";
  </script>
  <script src="${ctx}/resources/static/js/lib/require.js" data-main="${ctx}/resources/static/js/main"></script>

</body>
</html>












