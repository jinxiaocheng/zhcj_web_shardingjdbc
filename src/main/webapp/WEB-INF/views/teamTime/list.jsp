<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>班组工时统计</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqgrid/css/ui.jqgrid.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.structure.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}"/>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}" ></script>
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
                    <ul class="dropdown-menu" style="min-width:108px;">
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
                    <ul class="dropdown-menu" style="min-width:108px;">
                    </ul>
                </div>
            </div>
            <div class="ui-filter">
                <label>日期:</label>
                <input id="time1" class="Wdate form-control w-110" type="text" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'time2\')}'})"/>~
                <input id="time2" class="Wdate form-control w-110" type="text" onclick="WdatePicker({minDate:'#F{$dp.$D(\'time1\')}'})"/>
            </div>
            <div class="ui-filter" style="margin:10px 15px;">
                <a id="search_link" class="btn btn-primary" data-toggle="collapse" data-target="#search-input"><i class="fa fa-search"></i>查询</a>
            </div>
        </div>
        <div class="tools-btn" id="tools-btn">
            <div class="btn-group">
                <a id="export_link" class="btn btn-uirole"><i class="fa fa-download"></i>导出</a>
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
    var userId = "${userId}";  //当前用户ID
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
<script type="text/javascript" src="${ctx}/resources/static/js/popup.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/list_area.js?v=${v}"></script>
<script src="${ctx}/resources/static/js/tablecommon.js?v=${v}" type="text/javascript"></script>
<script type="text/javascript">
    var date=new Date();
    $("#time2").val(curentTime(date));
    $("#time1").val(curentTime(new Date(date.setDate(date.getDate()-30))));
    //时间设置
    function curentTime(now){
        var year = now.getFullYear();       //年
        var month = now.getMonth() + 1;     //月
        var day = now.getDate();            //日

        var clock = year + "-";

        if(month < 10)
            clock += "0";

        clock += month + "-";

        if(day < 10)
            clock += "0";

        clock += day + " ";
        return(clock);
    }
    //得到选中行的数据
    function check_data() {
        var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
        return $("#gridTable").jqGrid('getRowData', gr);
    }

    window.check_data = check_data;

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
            {'label': 'teamId', 'name': 'teamId', 'index': 'teamId', 'hidden': true},
            {'label': '日期', 'name': 'time', 'index': 'time', width: "70px",'align':'center'},
            {'label': '班组名称', 'name': 'name', 'index': 'name'},
            {'label': '出勤人数', 'name': 'total', 'index': 'total'},
            {'label': '总工时（小时）', 'name': 'totalTime', 'index': 'totalTime'},
            {'label': '操作', 'name': 'status', 'index': 'status','width':'80px',  'align':'center',formatter:function(cellvalue, options, rowObject) {
                var temp = "<button class='btn btn-info btn-xs ' style='background-color: transparent'" +
                            " data-constructionId="+rowObject.constructionId+" data-teamId="+rowObject.teamId+" data-time="+rowObject.time+" data-id="+rowObject.id+" onclick = 'toHandle(this)'>查看</button>";
                return temp;
            }}
        ];
    };

    //获取Url
    function getGridUrl() {
        return ctx + "/teamTime/listData";
    };


    // 点击查询，获取查询所需要的条件，重新加载表格
    function getGridParamJson() {
        var postPrams = {
            "areaId":$("#area").attr("data-value"),
            "constructionId":constructionId,
            "page": 1,
            "rowNum": 10,
            correctStartDate:$("#time1").val(),
            correctEndDate:$("#time2").val()
        }
        return postPrams;
    };

    //导出
    $("#export_link").click(function () {
        var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
        $(".my_load").show();
        window.location.href = ctx + '/teamTime/exportTeamTimeExcel?areaId='+$("#area").attr("data-value")+'&constructionId='+constructionId+
            '&correctStartDate='+$("#time1").val()+'&correctEndDate='+$("#time2").val();
        $(".my_load").hide();
    });

    //点击弹出整改受理页面
    function toHandle(element) {
        var id = $(element).attr("data-id");
        var time = $(element).attr("data-time");
        var teamId = $(element).attr("data-teamId");
        var constructionId = $(element).attr("data-constructionId");
        var _url = ctx + '/teamTime/toPersonWorkTime?id='+id+'&teamId='+teamId+'&time='+time+'&constructionId='+constructionId;
        var _title='人员工时统计';
        layer_showDouble(_title, _url, {w:'920px',h:'560px'});
    }

    init();
</script>
</body>
</html>