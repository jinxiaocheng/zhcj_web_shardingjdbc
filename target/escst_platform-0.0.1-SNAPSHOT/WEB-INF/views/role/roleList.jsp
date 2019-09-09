<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<%@ include file="/common/common_tag.jsp"%>--%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>角色管理(整)</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
  </head>
  <style>
  html,body{
    width:100%;
    height:100%;
  }
  </style>
<body>

  <div class="container-full">

    <div class="menu-left-tree  fl">
        <ul id="orgTree" class="ztree"></ul>
    </div>
    <div class="videoplay">
         <iframe id="orgviewocx" name="orgviewocx" scrolling="no" width="100%" height="100%" frameborder="0"></iframe>
         <!-- <object class="js-vss-ocx" classid="clsid:9ECD2A40-1222-432E-A4D4-154C7CAB9DE3" id="orgviewocx" name="orgviewocx" width="100%" height="100%">
         </object> -->
    </div>
    </div>
  <script>var ctx = "${ctx}",type = "role",userId = "${userId}";</script>
  <!--[if gte IE 9]>
  <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
  <![endif]-->
  <![if !IE]>
  <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
  <![endif]>
  <script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
  <script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
  <script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
  <script type="text/javascript" src="${ctx}/resources/static/js/new_orgview.js?v=${v}"></script>
  <script>
      $(function () {
          orgview.mScontains();
          orgview.videotree();
      })
  </script>
</body>
</html>












