<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>可视化配置</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/layui/css/layui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/layui_list.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/gzm/nowData.css?v=${v}"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
</head>
<body class="biyue-bg">
<div class="biyue_body">
    <div class="biyue_search layui-form">
        <div class="layui-inline">
            <label class="layui-form-label" style="line-height: 43px;">开始时间</label>
            <div class="layui-input-inline">
                <input type="text" name="startTime" placeholder="请输入开始时间" lay-verify="required" autocomplete="off" class="layui-input time1" id="start">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label" style="line-height: 43px;">结束时间</label>
            <div class="layui-input-inline">
                <input type="text" name="endTime" placeholder="请输入结束时间" lay-verify="required" autocomplete="off" class="layui-input time2" id="end">
            </div>
        </div>
        <button class="layui-btn layui-btn-primary search area" style="margin-top: 1px;" id="search"><i class=" layui-icon"></i> 搜索</button>
    </div>
    <div class="biyue_tool">
    </div>
    <div class="biyue_main">
        <table id="table-list" lay-filter="table-list"></table>
    </div>
</div>
<script>
    var ctx = "${ctx}",userId = "${userId}";
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lifting/dataHistory.js?v=${v}"></script>
<script>
    $(function () {
        //开始结束时间
        ;(function () {
            var date = new Date();
            var endTime = biyue.timeText(date) + '23:59:59';
            var startTime = biyue.timeText(new Date(date.setDate(date.getDate()-7))) + '00:00:00';
            biyue.timeSD({
                valueS:startTime,
                valueE:endTime||date
            });
            $("[name=\"startTime\"]").val(startTime);
            $("[name=\"endTime\"]").val(endTime);
        })();
        biyue.listCommon();
        biyue.biyueControl();
        biyueList.createTable();
    })
</script>
</body>
</html>
