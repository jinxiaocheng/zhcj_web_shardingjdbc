<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>门维护</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/layui/css/layui.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/layui_list.css?v=${v}"/>
</head>
<body class="body_bg">
<div class="biyue_body">
    <div class="biyue_search layui-form">
        <label class="layui-form-label">切换工地</label>
        <div class="layui-input-inline area">
            <select name="area" lay-search lay-filter="area">
                <option value="">选择区域</option>
            </select>
        </div>
        <div class="layui-input-inline site">
            <select name="site" lay-search lay-filter="site">
                <option value="">选择工地</option>
            </select>
        </div>
        <button class="layui-btn layui-btn-primary search" id="search"><i class="layui-icon"></i>  搜索</button>
    </div>
    <div class="biyue_tool">
        <button class="layui-btn layui-btn-primary" id="add_link"><i class="fa fa-plus"></i>新增</button>
    </div>
    <div class="biyue_main">
        <table id="table-list" lay-filter="table-list"></table>
    </div>
</div>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="edit">编辑</a>
</script>
<script>var ctx='${ctx}',userId='${userId}';</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/person/door.js?v=${v}"></script>
<script>
    $(function () {
        var element = layui.element;
        var biyue=new biYue();
        var area =biyue.area();
        area.area();
        door.createTable();

        //新增
        $("#add_link").click(function () {
            biyue.open({
                title:'新增门',
                url:'/door/toAddDoor'
            })
        })
    })
</script>
</body>
</html>
