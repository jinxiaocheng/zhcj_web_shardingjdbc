<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>人员工时统计</title>
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
    <style>
        .infoShow{
            position: absolute;
            left: 130px;
            top:18px;
        }
        .infoShow p{
            display: inline-block;
            margin-right: 20px;
        }
        .infoShow p label{
            margin-right: 10px;
            color: #949494;
        }

    </style>
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
            <div class="ui-filter">
                <label>姓名:</label>
                <input id="name" class=" form-control w-110" type="text"/>
            </div>
            <div class="ui-filter" style="margin:10px 15px;">
                <a id="search_link" class="btn btn-primary" data-toggle="collapse" data-target="#search-input"><i class="fa fa-search"></i>查询</a>
            </div>
        </div>
        <div class="infoShow">
           <p class="area">
               <label>区域</label><span>${areaName}</span>
           </p>
            <p class="site">
                <label>工地</label><span>${constructionName}</span>
            </p>
            <p class="date">
                <label>日期</label><span>${time}</span>
            </p>
            <p class="teams">
                <label>班组</label><span>${teamName}</span>
            </p>
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
    //得到选中行的数据
    function check_data() {
        var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
        return $("#gridTable").jqGrid('getRowData', gr);
    }

    window.check_data = check_data;

    //表格扩展
    function getGridExtendParam(){
        return {
            //双击时间
            myDblClickRow:function (rowid,iRow,iCol,e){
                $("#view_link").click();
            }
        }
    }

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

    //导出
    $("#export_link").click(function () {
        var search=window.location.search.replace("?","").split("&");
        var searchObj={};
        for(var i in search){
            searchObj[search[i].split("=")[0]]=search[i].split("=")[1];
        }
        $(".my_load").show();
        window.location.href = ctx + '/teamTime/exportPersonTimeExcel?personName='+$("#name").val()+'&constructionId='+searchObj.constructionId+
            '&time='+searchObj.time+'&teamId='+searchObj.teamId;
        $(".my_load").hide();
    });
    // 点击查询，获取查询所需要的条件，重新加载表格
    function getGridParamJson() {
        var search=window.location.search.replace("?","").split("&");
        var searchObj={};
        for(var i in search){
            searchObj[search[i].split("=")[0]]=search[i].split("=")[1];
        }
        var postPrams = {
            id:searchObj.id,
            time:searchObj.time,
            teamId:searchObj.teamId,
            constructionId:searchObj.constructionId,
            personName:$("#name").val(),
            "page": 1
        }
        return postPrams;
    };


    // 设置表格
    function getGridHead() {
        return [
            {'label': 'id', 'name': 'id', 'index': 'id', 'hidden': true, width: "0px"},
            {'label': 'personId', 'name': 'personId', 'index': 'personId', 'hidden': true, width: "0px"},
            {'label': '姓名', 'name': 'personName', 'index': 'personName', width: "70px"},
            {'label': '进场时间', 'name': 'upTime', 'index': 'upTime', width: "70px",'align':'center'},
            {'label': '出场时间', 'name': 'downTime', 'index': 'downTime', width: "70px"},
            {'label': '工时（小时）', 'name': 'workTime', 'index': 'workTime',width:"60px"},
            {'label': '操作', 'name': 'status', 'index': 'status','width':'40px',  'align':'center',formatter:function(cellvalue, options, rowObject) {
                var temp = "<button class='btn btn-info btn-xs ' style='background-color: transparent' data-personId="+rowObject.personId+" data-id="+rowObject.id+" onclick = 'toHandle(this)'>查看</button>";
                return temp;
            }}
        ];
    };

    //获取Url
    function getGridUrl() {
        return ctx + "/teamTime/personWorkTimeData";
    };

    //点击弹出整改受理页面
    function toHandle(element) {
        var id = $(element).attr("data-id");
        var personId = $(element).attr("data-personId");
        var search=window.location.search.replace("?","").split("&");
        var searchObj={};
        for(var i in search){
            searchObj[search[i].split("=")[0]]=search[i].split("=")[1];
        }
        var _url = ctx + '/teamTime/toAttendanceDetail?id='+id+'&constructionId='+searchObj.constructionId+'&personId='+personId;
        var _title='人员考勤明细';
        layer_showDouble(_title, _url, {w:'920px',h:'500px'});
    }

    init();
</script>
</body>
</html>