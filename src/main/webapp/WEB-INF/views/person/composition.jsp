<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>人员结构</title>
  <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
  <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
  <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}" />
  <link rel="stylesheet" href="${ctx}/resources/static/plugins/layui/css/layui.css?v=${v}"/>
  <link rel="stylesheet" href="${ctx}/resources/static/css/common/layui_list.css?v=${v}"/>
  <link rel="stylesheet" href="${ctx}/resources/static/css/gzm/nowData.css?v=${v}"/>
  <style>
    .biyue_main .layui-border-box{margin: 0}
  </style>
  <!--[if lt IE 9]>
  <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
  <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
  <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
  <![endif]-->
</head>
<body class="biyue-bg">
<div class="biyue-left-ztree biyue-box">
  <ul id="menuTree" class="ztree"></ul>
</div>
<div class="biyue-right-table biyue-box"><div class="biyue_body">
  <div class="biyue_main" style="top: 0;padding: 0">
    <table id="table-list" lay-filter="table-list"></table>
  </div>
</div></div>
<script>
    var ctx = "${ctx}",userId = "${userId}";
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/person/composition.js?v=${v}"></script>
<script>
    $(function () {
        biyue.listCommon();
        biyue.biyueControl();
        biyueList.getZtree();
    })
</script>
</body>
</html>
