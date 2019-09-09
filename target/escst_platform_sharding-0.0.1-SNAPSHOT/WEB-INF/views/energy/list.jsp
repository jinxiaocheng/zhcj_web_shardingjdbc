<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>能耗监测</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqgrid/css/ui.jqgrid.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.structure.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
</head>
<body class="body_bg">
<div class="ui-report">

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
                    <ul class="dropdown-menu" style="min-width:108px;max-height:448px;overflow: auto">
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
            <div class="ui-filter" style="margin:10px 15px;">
                <a id="search_link" class="btn btn-primary" data-toggle="collapse" data-target="#search-input"><i class="fa fa-search"></i>查询</a>
            </div>
        </div>
        <div class="tools-btn" id="tools-btn">
            <div class="btn-group">
                <a id="view_link" class="btn btn-uisblue"><i class="fa fa-search"></i>查看</a>
            </div>
            <div class="btn-group">
                <a id="chart_link" class="btn btn-uisblue"><i class="fa fa-search"></i>趋势图</a>
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
<script type="text/javascript" src="${ctx}/resources/static/plugins/jqgrid/js/jquery.jqGrid.src.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/list_area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/tablecommon.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/popup.js?v=${v}"></script>
<script type="text/javascript">

    // 设置表格
    function getGridHead() {
        return [
            {'label': 'id', 'name': 'id', 'index': 'id', 'hidden': true},
            {'label': '工地名称', 'name': 'name', 'index': 'name'},
            {'label': '所属城市', 'name': 'cityName', 'index': 'cityName'},
            {'label': '所属区域', 'name': 'areaName', 'index': 'areaName'}
        ];
    };
    // 点击查询，获取查询所需要的条件，重新加载表格
    function getGridParamJson() {
        return {
            areaId:$("#area").attr("data-value"),
            constructionId:$("#site").attr("data-value"),
        };
    };
    //获取Url
    function getGridUrl() {
        return ctx + "/energyMonitor/queryEnergyConstruction";
    };
    init();
    //查看
    $("#view_link").click(function () {
        viewLink()
    });

    function viewLink() {
        var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
        if (gr != null) {
            var rowData = $("#gridTable").jqGrid('getRowData',gr);
            var url = ctx + '/energyMonitor/toDataList?id=' + rowData.id;
            var title = '查看能耗明细';
            parent.layer_showDouble(title, url);
        } else {
            layer.msg('您没有选中任何数据请选择后操作', {time: 2000, icon: 0});
        }
    }
    //表格扩展
    function getGridExtendParam(){
        return {
            //双击时间
            myDblClickRow:function (rowid,iRow,iCol,e){
                $("#view_link").click();
            }
        }
    }
    //趋势图
    $("#chart_link").click(function () {
        var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
        if (gr != null) {
            var rowData = $("#gridTable").jqGrid('getRowData',gr);
            var url = ctx + '/energyMonitor/toChart?id=' + rowData.id;
            var title = '趋势图';
            parent.layer_showDouble(title, url);
        } else {
            layer.msg('您没有选中任何数据请选择后操作', {time: 2000, icon: 0});
        }
    });
</script>

</body>
</html>