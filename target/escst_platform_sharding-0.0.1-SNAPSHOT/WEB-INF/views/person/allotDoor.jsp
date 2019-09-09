<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>分配门</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/layui/css/layui.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/layui_open.css?v=${v}.1"/>
</head>
<body class="body_bg layui-form" lay-filter="biyueOpen">
    <div class="biyue_body">
        <div class="layui-form-item" id="rights">
            <label class="layui-form-label">门选择:</label>
            <div class="layui-input-block">
                <span style="    line-height: 38px;color: #c4c4c4;letter-spacing: 1px;">暂无门</span>
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>
    </div>
    <div class="biyue_footer">
        <button class="layui-btn layui-btn-normal" lay-submit lay-filter="add" id="submit">提交</button>
    </div>
<script>var ctx='${ctx}',userId='${userId}';</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/person/allotDoor.js?v=${v}"></script>
<script>
    $(function () {
        var element = layui.element;
        var biyue=new biYue();
        var searchObj=biyue.urlSearch();
        addDoor.getDoor();
        addDoor.upData();
        biyue.verify();
    })
</script>
</body>
</html>
