<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getScheme() + "://" + request.getHeader("Host") + request.getContextPath();
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>添加材料名称</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqgrid/css/ui.jqgrid.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.structure.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}"/>
    <script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}" type="text/javascript"></script>
    <style>
        .my_load {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.06);
            z-index: 1000;
            display: none;
        }
        .my_load img {
            -webkit-transform: translate(-50%, -50%);
            -moz-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            -o-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
            position: absolute;
            top: 50%;
            left: 50%;
        }
        #gridTable_cb #cb_gridTable{
            display: none;
        }
        #gridPager {
            position: fixed;
            bottom: 40px;
        }
    </style>
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
            <label>材料名称:</label>
            <input type="text" id="name" name='name' class="form-control w-110" value=""/>
        </div>
        <div class="ui-filter" style="margin:0px 15px;">
            <a id="search_link" class="btn btn-primary"><i class="fa fa-search"></i>查询</a>
        </div>
    </div>
    <div class="jqGrid_wrapper">
        <table class="gridTable table-condensed" id="gridTable" aa="1"></table>
        <div class="gridPage" id="gridPager">
        </div>
    </div>
</div>
<div class="btn-group distribution_form" style="position:fixed;bottom: 5px ;right: 18px;color:#fff">
    <a id="distribution_form" class="btn btn_submit">提交</a>
</div>

<%--加载--%>
<div class="my_load">
    <img src="${ctx}/resources/static/images/ajax-loader.gif" alt="">
</div>
<script>
    var ctx = "${ctx}",constructionId="${constructionId}";
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
    $(function () {

        //得到选中行的数据
        function check_data() {
            var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
            return $("#gridTable").jqGrid('getRowData',gr);
        }

        $("#distribution_form").on("click",function(){
            var data = check_data();
            parent.getMaterialData(data);
            layer_close();
        });
    });
    //需求控制
    function getGridExtendParam(){
        return {
            multiselect: true,
            multiboxonly:true
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
            {'label': 'materialId', 'name': 'materialId', 'index': 'materialId', 'hidden': true},
            {'label': 'modelId', 'name': 'modelId', 'index': 'modelId', 'hidden': true},
            {'label': '材料名称', 'name': 'materialName', 'index': 'materialName','width':'200px'},
            {'label': '型号', 'name': 'modelName', 'index': 'modelName','width':'200px'},
            {'label': '计量单位', 'name': 'unit', 'index': 'unit','width':'80px'}
        ];
    };
    // 点击查询，获取查询所需要的条件，重新加载表格
    function getGridParamJson() {
        //人员名称
        var postPrams = {
            name:$('#name').val()
        };
        return postPrams;
    };
    //获取Url
    function getGridUrl() {
        return ctx + "/material/queryBaseMaterial";
    };


</script>
</body>
</html>