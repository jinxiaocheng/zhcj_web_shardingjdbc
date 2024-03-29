<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>历史任务预约</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqgrid/css/ui.jqgrid.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.structure.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}"/>
    <script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}" type="text/javascript"></script>

    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
</head>

<body class="body_bg">
<div class="ui-report">
    <!--更多查询条件-->
    <div class="form-inline response-head titlePanel" id="listTools">
        <button type="button" class="btn openSearchBtn" data-toggle="collapse"
                data-target="#search-input">
            查询条件 <span class="glyphicon glyphicon-menu-down"></span>
        </button>
        <div id="search-input" class="collapse">
            <div class="ui-filter area">
                <label>区域:</label>
                <div class="btn-group queryCondition">
                    <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
                       data-value="" id="area">请选择</a>
                    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                    </ul>
                </div>
            </div>
            <div class="ui-filter site">
                <label>工地:</label>
                <div class="btn-group queryCondition">
                    <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
                       data-value="" id="site">请选择</a>
                    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                            class="caret"></span></a>
                    <ul class="dropdown-menu" style="min-width:108px;max-height:448px;overflow: auto">
                    </ul>
                </div>
            </div>
            <div class="ui-filter">
                <label>设备类型:</label>
                <div class="btn-group queryCondition">
                    <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
                       data-value="" id="equipmentType">请选择</a>
                    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                            class="caret"></span></a>
                    <ul class="dropdown-menu" style="min-width:108px;">
                        <li><a data-value="">全部</a></li>
                        <li><a data-value="1">塔吊</a></li>
                        <li><a data-value="2">升降机</a></li>
                    </ul>
                </div>
            </div>
            <div class="ui-filter">
                <label>预约状态:</label>
                <div class="btn-group queryCondition">
                    <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
                       data-value="" id="status">请选择</a>
                    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                            class="caret"></span></a>
                    <ul class="dropdown-menu" style="min-width:108px;">
                        <li><a data-value="">全部</a></li>
                        <li><a data-value="1">待处理</a></li>
                        <li><a data-value="2">待审核</a></li>
                        <li><a data-value="3">处理中</a></li>
                        <li><a data-value="4">审核不通过</a></li>
                        <li><a data-value="5">已完成</a></li>
                    </ul>
                </div>
            </div>
            <div class="ui-filter" style="margin:0px 15px;">
                <a id="search_link" class="btn btn-primary" data-toggle="collapse" data-target="#search-input"><i class="fa fa-search"></i>查询</a>
            </div>
        </div>
        <div class="tools-btn" id="tools-btn">
            <div class="btn-group" style="margin:0px 15px;">
                <a id="view_link" class="btn btn-uisblue"><i class="fa fa-search"></i>查看</a>
            </div>
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
    var userId="${userId}";
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
<script src="${ctx}/resources/static/plugins/jqgrid/js/jquery.jqGrid.src.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/list_area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/popup.js?v=${v}"></script>
<script src="${ctx}/resources/static/js/tablecommon.js?v=${v}" type="text/javascript"></script>
<script type="text/javascript">
    init();
    //得到选中行的数据
    function check_data() {
        var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
        return $("#gridTable").jqGrid('getRowData',gr);
    }
    window.check_data=check_data;


    //查看
    $("#view_link").click(function () {
        var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
        var id = check_data().id;
        if (gr != null) {
            var _url = ctx + '/taskAppointment/toView?taskAppointmentId='+id;
            var _title = '任务查看';
            layer_showOdd(_title, _url,{w:'920px',h:'560px'});
        } else {
            layer.msg('您没有选中任何数据请选择后操作', {time: 2000, icon: 0});
        }

    });

    //查询
    $("#search_link").click(function () {
        var url = getGridUrl();
        var postParams = getGridParamJson();
        $("#gridTable").jqGrid("setGridParam", {
            url: url,
            postData: postParams,
            page: 1
        }).trigger("reloadGrid");
    });

    // 设置表格
    function getGridHead() {
        return [
            {'label': 'id', 'name': 'id', 'index': 'id', 'hidden': true},
            {'label': '预约设备', 'name': 'equipmentType', 'index': 'equipmentType', 'formatter': 'select',
                'editoptions': {value: "1:塔吊; 2:升降机"},'width':'100px'},
            {'label': '区域', 'name': 'areaName', 'index': 'areaName'},
            {'label': '工地名', 'name': 'constructionName', 'index': 'constructionName'},
            {'label': '检查部位', 'name': 'projectStructureName', 'index': 'projectStructureName'},
            {'label': '所属公司', 'name': 'projectCompanyName', 'index': 'projectCompanyName'},
            {'label': '任务状态', 'name': 'status', 'index': 'status', 'formatter': 'select',
                'editoptions': {value: "1:待处理; 2:待审核;3:处理中;4:审核不通过;5:已完成"},'width':'100px'},
            {'label': '预约时间', 'name': 'appointmentDate', 'index': 'appointmentDate'},
            {'label': '任务说明', 'name': 'remark', 'index': 'remark','width':'200px'},
            {'label': '处理人', 'name': 'handlePerson', 'index': 'handlePerson','width':'100px'},
            {'label': '预约人', 'name': 'appointmentName', 'index': 'appointmentName','width':'100px'},
            {'label': '预约人电话', 'name': 'moblie', 'index': 'moblie'},
            {'label': '创建时间', 'name': 'createTime', 'index': 'createTime'},
            {'label': '创建人', 'name': 'createByName', 'index': 'createByName','width':'100px'}
        ];
    };
    // 点击查询，获取查询所需要的条件，重新加载表格
    function getGridParamJson() {
        //设备类型
        var equipmentType = $("#equipmentType").attr("data-value");
        if (equipmentType == "") {
            equipmentType = 0;
        }
        //任务状态
        var status = $("#status").attr("data-value");
        if (status == "") {
            status = 0;
        }
        var postPrams = {
            "areaId":$("#area").attr("data-value"),
            "constructionId":$(".site .dropdown-text").attr("data-value"),
            appointmentId: userId,
            status:status,
            equipmentType:equipmentType,
            type:2,
            "page":1,
            "rowNum":10

        };
        return postPrams;
    };

    //获取Url
    function getGridUrl() {
        return ctx + "/taskAppointment/queryList";
    };


</script>


</body>
</html>
