<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<%@ include file="/common/common_tag.jsp"%>--%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>检查部位显示</title>
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
        widht:100%;
        height:100%;
    }
</style>
<body>

<div class="container-full">

    <div class="menu-left-tree  fl" style="width: 100%">
        <ul id="projectTree" class="ztree"></ul>
    </div>
    <div class="videoplay">
        <!--  <a id="import_link" class="btn btn-warning"><i class="fa fa-sign-out"></i>导入</a> -->
    </div>
</div>
<script>
    var ctx = "${ctx}";
    var constructionId = "${constructionId}";
</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/new_projectview.js?v=${v}"></script>
<script>
    //页面架构
    projectview.mScontains();
    projectview.videotree();

</script>
</body>
</html>












