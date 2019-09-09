<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<%@ include file="/common/common_tag.jsp"%>--%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>工程结构显示</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js"></script>
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
        <ul id="strutureTree" class="ztree"></ul>
    </div>
    <div class="videoplay">
        <iframe id="menuviewocx" name="menuviewocx" scrolling="no" width="100%" height="100%" frameborder="0"></iframe>
        <!-- <object class="js-vss-ocx" classid="clsid:9ECD2A40-1222-432E-A4D4-154C7CAB9DE3" id="menuviewocx" name="menuviewocx" width="100%" height="100%">
        </object> -->
    </div>
    <script src="${ctx}/resources/static/js/lib/require.js" data-main="${ctx}/resources/static/js/structure"></script>
    <script>
        var ctx = "${ctx}";

        var parent=window.dialogArguments;
        alert(parent);
    </script>

</body>
</html>












