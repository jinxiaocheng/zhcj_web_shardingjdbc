<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>设备管理</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqgrid/css/ui.jqgrid.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.structure.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}"/>
    <style>
        #gridTable_cb #cb_gridTable{
            display: none;
        }
    </style>
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
    <div class="response-head titlePanel form-inline">
        <div class="ui-filter">
            <label>名称:</label>
            <input type="text" id="name" class="form-control w-110" value=""/>
            <input hidden="true" >
        </div>
        <div class="ui-filter" style="margin:0px 15px;">
            <a id="search_link" class="btn btn-primary"><i class="fa fa-search"></i>查询</a>
        </div>
        <div class="ui-filter" style="margin:0px 15px;">
            <a id="distribution_form" class="btn btn_submit btn-show-blue">确认</a>
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
    var type="${type}";
    var userId="${userId}";
    var constructionId="${constructionId}";
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
<script src="${ctx}/resources/static/js/equipment/table.js?v=${v}" type="text/javascript"></script>
<script type="text/javascript">
    // 设置表格
    function getGridHead() {
        return [
            {'label': '设备Id', 'name': 'id', 'index': 'id',align:'center','hidden': true},
            {'label': '设备名', 'name': 'name', 'index': 'name',align:'center'},
            {'label': '编号', 'name': 'number', 'index': 'number','hidden': true},
            {'label': '型号', 'name': 'model', 'index': 'model','hidden': true},
            {'label': '租赁厂家', 'name': 'leasingCompany', 'index': 'leasingCompany','hidden': true},
            {'label': '设备状态', 'name': 'type', 'index': 'type','formatter': 'select', 'editoptions': {value: "1:进场;2:出场"},'hidden': true},
            {'label': '创建时间', 'name': 'createTime', 'index': 'createTime','hidden': true},
            {'label': '责任人', 'name': 'personId', 'index': 'personId','hidden': true},
            {'label': '厂家', 'name': 'manufacturer', 'index': 'remark','hidden': true},
            {'label': '备注信息', 'name': 'remark', 'index': 'remark','hidden': true}

        ];
    };

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

    // 点击查询，获取查询所需要的条件，重新加载表格
    function getGridParamJson() {
        var postPrams = {
            type:type,
            constructionId:constructionId,
            name: $.trim($("#name").val()),
            "page":1,
            "rowNum":10
        };
        return postPrams;
    };
    //获取Url
    function getGridUrl() {
        return ctx + "/equipment/queryListById";
    };



    //需求控制
    function getGridExtendParam(){
        return {
            multiselect: true,
            multiboxonly:true,

        }
    }

    $(function(){
        //得到选中行的数据
        function check_data() {
            var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
            return $("#gridTable").jqGrid('getRowData',gr);
        }

        $("#distribution_form").on("click",function(){
            var data = check_data();
            parent.child(data);
            layer_close();

        });
    });
</script>
</body>
</html>