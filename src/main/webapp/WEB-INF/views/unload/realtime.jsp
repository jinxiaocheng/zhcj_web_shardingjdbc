<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>升降机实时数据</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqgrid/css/ui.jqgrid.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.structure.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}"/>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}"></script>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
</head>
<body class="body_bg">
<div class="ui-report">
    <div class="response-head titlePanel">
        <input type="hidden" id="equipmentId" value="${equipmentId}"/>
        <div class="ui-filter">
            <label>采集开始时间:</label>
            <input type="text" id="startDate" class="form-control w-110" value="${startDate}" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'})"/>
        </div>
        <div class="ui-filter">
            <label>采集结束时间:</label>
            <input type="text" id="endDate" class="form-control w-110" value="${endDate}" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'})"/>
        </div>
        <div class="ui-filter" style="margin:0px 15px;">
            <a id="search_link" class="btn btn-primary"><i class="fa fa-search"></i>查询</a>
        </div>
    </div>
    <div class="tools-btn" id="tools-btn" style=" top: 36px;right: 20px;">
        <div class="btn-group" style="margin:0px 15px;">
            <a id="export_link" class="btn btn-uirole"><i class="fa fa-download"></i>导出</a>
        </div>
    </div>
    <div class="jqGrid_wrapper">
        <table class="gridTable table-condensed" id="gridTable" aa="1"></table>
        <div class="gridPage" id="gridPager">
        </div>
    </div>
</div>
<script>
    var ctx = "${ctx}";
</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jqgrid/js/jquery.jqGrid.src.js?v=${v}"></script>
<script type="text/javascript">
    // 设置表格
    function getGridHead() {
        return [
            {'label': '采集时间', 'name': 'time', 'index': 'time'},
            {'label': '高度(m)', 'name': 'height', 'index': 'height', 'width':80},
            {'label': '重量(KG)', 'name': 'weight', 'index': 'weight', 'width':80},
            {'label': '倾斜(°)', 'name': 'obliquity', 'index': 'obliquity', 'width':80},
            {'label': '速度(m/s)', 'name': 'speed', 'index': 'speed', 'width':80},
            {'label': '人数', 'name': 'peopleNum', 'index': 'peopleNum', 'width':80},
            //{'label': '司机名称', 'name': 'driverName', 'index': 'driverName', 'width':80},
            //{'label': '司机身份证号', 'name': 'driverNo', 'index': 'driverNo'},
            //{'label': '楼层', 'name': 'floorNo', 'index': 'floorNo', 'width':80},
            {'label': '前门锁报警开关', 'name': 'frontDoorLockState', 'index': 'frontDoorLockState','formatter': 'select', 'editoptions': {value: "1:开;2:关"}, 'width':80},
            {'label': '后门锁报警开关', 'name': 'backDoorLockState', 'index': 'backDoorLockState','formatter': 'select', 'editoptions': {value: "1:开;2:关"}, 'width':80},
            {'label': '高限位报警开关', 'name': 'highLimitState', 'index': 'highLimitState','formatter': 'select', 'editoptions': {value: "1:开;2:关"}, 'width':80},
            {'label': '低限位报警开关', 'name': 'lowLimitState', 'index': 'lowLimitState','formatter': 'select', 'editoptions': {value: "1:开;2:关"}, 'width':80}
        ];
    };
    // 点击查询，获取查询所需要的条件，重新加载表格
    function getGridParamJson() {
        return {'startDate': $('#startDate').val(), 'endDate': $('#endDate').val(), 'equipmentId' : $('#equipmentId').val()};
    };
    //获取Url
    function getGridUrl() {
        return ctx + "/unload/queryRealtimeList";
    };

    //导出
    $("#export_link").click(function () {
        var search=window.location.search.replace("?","").split("&");
        var searchObj={};
        for(var i in search){
            searchObj[search[i].split("=")[0]]=search[i].split("=")[1];
        }
        window.location.href = ctx + '/equipment/exportExcel?type=4&startDate='+$('#startDate').val()+'&endDate='+$('#endDate').val()+'&equipmentId='+$('#equipmentId').val();
    });
</script>
<script type="text/javascript" src="${ctx}/resources/static/js/tablecommon.js?v=${v}"></script>
</body>
</html>