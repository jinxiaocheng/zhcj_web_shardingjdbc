<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>缓存管理</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/layui/css/layui.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jsonview/jquery.jsonview.min.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/redis/list.css?v=${v}"/>
</head>
<body class="body_bg">
<div class="biyue_body">
    <div class="biyue_tool">
        <label class="layui-form-label">key</label>
        <div class="layui-input-inline">
            <input id="key" type="text" placeholder="请输入key" autocomplete="off" class="layui-input">
        </div>
        <button class="layui-btn layui-btn-primary search"><i class="layui-icon">&#xe615;</i>  搜索</button>
        <button class="layui-btn layui-btn-danger del" ><i class="layui-icon">&#xe640;</i>删除</button>
    </div>
    <div class="biyue_main">
        <table id="table-list" lay-filter="table-list"></table>
    </div>
</div>

<div id="look">

</div>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="look">查看</a>
</script>

<script>
    var ctx = "${ctx}";
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jsonview/jquery.jsonview.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/redis/list.js?v=${v}"></script>
</body>
</html>
