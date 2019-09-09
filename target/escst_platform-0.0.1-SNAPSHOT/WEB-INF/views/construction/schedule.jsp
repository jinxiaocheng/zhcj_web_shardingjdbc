<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>总体进度计划</title>
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
    <div class="form-inline response-head titlePanel" id="listTools">
        <button type="button" class="btn openSearchBtn" data-toggle="collapse"
                data-target="#search-input">
            查询条件 <span class="glyphicon glyphicon-menu-down"></span>
        </button>
        <div id="search-input" class="collapse">
            <div class="ui-filter">
                <label>进度名称:</label>
                <input type="text" id="name" class="form-control w-110" value=""/>
            </div>
            <div class="ui-filter" style="margin:10px 15px;">
                <a id="search_link" class="btn btn-primary" data-toggle="collapse" data-target="#search-input"><i class="fa fa-search"></i>查询</a>
            </div>
        </div>
        <div class="tools-btn" id="tools-btn">
            <div class="btn-group">
                <a id="add_link" class="btn btn-info"><i class="fa fa-plus"></i>录入</a>
            </div>
            <div class="btn-group">
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
</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}" ></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script src="${ctx}/resources/static/plugins/jqgrid/js/jquery.jqGrid.src.js?v=${v}"></script>
<script src="${ctx}/resources/static/js/tablecommon.js?v=${v}" type="text/javascript"></script>
<script type="text/javascript">
    //添加
    $("#add_link").click(function () {
        var url = ctx + '/construction/toConstructionAdd'
        var title = '新增工程';
        //var iframeWidth = '780px';
        //var iframeHeight = '500px';
        //parent.layer_show(_title, _url, iframeWidth, iframeHeight);
        parent.layer_showDouble(title, url);
    });

    //查看
    $("#view_link").click(function () {
        viewLink()
    });

    function viewLink() {
        var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
        var constructLicenseId = $("#gridTable").jqGrid('getRowData',gr).constructLicenseId;
        var url = ctx + '/construction/toConstructionDetail?constructLicenseId='+constructLicenseId;
        var title = '工程查看';
        //var iframeWidth = '780px';
        //var iframeHeight = '500px';
        if (gr != null) {
            //parent.layer_show(_title, _url, iframeWidth, iframeHeight);
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
            {'label': 'id', 'name': 'constructLicenseId', 'index': 'constructLicenseId', 'hidden': true},
            {'label': '进度名称', 'name': 'name', 'index': 'name'},
            {'label': '工地名称', 'name': 'constructionName', 'index': 'constructionName'},
            {'label': '开始日期', 'name': 'startDate', 'index': 'startDate','align':'center'},
            {'label': '结束日期', 'name': 'endDate', 'index': 'endDate','align':'center'}
        ];
    };
    // 点击查询，获取查询所需要的条件，重新加载表格
    function getGridParamJson() {
        //人员名称
        var name = $.trim($("#name").val());

        var postPrams = {
        		name: name
        };
        return postPrams;
    };
    //获取Url
    function getGridUrl() {
        return ctx + "/scheduledPlan/list";
    };
</script>
</body>
</html>