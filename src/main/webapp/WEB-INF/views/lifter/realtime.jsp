<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>升降机-监测设备-查看</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/layui/css/layui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/layui_list.css?v=${v}"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
</head>

<body class="body_bg">
<div class="biyue_body">
    <div class="biyue_search layui-form">
        <label class="layui-form-label" style="width: 100px">当前数据 时间:</label> ${startDate} 00:00:00 ~ ${endDate} 23:59:59
    </div>
    <input type="hidden" id="equipmentId" style="display: none" value="${equipmentId}"/>
    <input type="text" id="startDate" class="form-control h-28" style="display: none" value="${startDate} 00:00:00"/>
    <input type="text" id="endDate" class="form-control h-28" style="display: none" value="${endDate} 23:59:59"/>
    <div class="biyue_tool">
        <button class="layui-btn layui-btn-primary" id="warning_data"><i class="fa fa-warning"></i>预警数据</button>
        <button class="layui-btn layui-btn-primary" id="history_data"><i class="fa fa-history"></i>历史数据</button>
        <button class="layui-btn layui-btn-primary" id="export_link"><i class="fa fa-download"></i>导出</button>
    </div>
    <div class="biyue_main">
        <table id="table-list" lay-filter="table-list"></table>
    </div>
</div>

<script>
    var ctx = "${ctx}",userId="${userId}";
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lifter/realtime.js?v=${v}"></script>
<script type="text/javascript">
    $(function () {
        biyueList.createTable();
        biyue.listCommon();
        biyueList.toolsBtn();
    });
</script>

</body>
</html>