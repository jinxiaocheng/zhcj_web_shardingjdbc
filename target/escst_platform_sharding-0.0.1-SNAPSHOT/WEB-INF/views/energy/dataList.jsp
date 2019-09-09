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
    <title>每天的数据</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqgrid/css/ui.jqgrid.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.structure.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}"/>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}"></script>
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
    </style>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
</head>
<body class="body_bg">
        <input type="hidden" id="constructionId" value="${constructionId}"/>
<div class="ui-report">
    <div class="response-head titlePanel">
        <div class="ui-filter">
            <label>类型:</label>
            <div class="btn-group queryCondition">
                <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
                   data-value="7" id="equipmentType">电</a>
                <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                        class="caret"></span></a>
                <ul class="dropdown-menu" style="min-width:108px;">
                    <li><a data-value="7">电</a></li>
                    <li><a data-value="8">水</a></li>
                </ul>
            </div>
        </div>
        <div class="ui-filter">
            <label>开始时间:</label>
            <input type="text" id="startDate" class="form-control w-110" value="${startDate}" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'})"/>
        </div>
        <div class="ui-filter">
            <label>结束时间:</label>
            <input type="text" id="endDate" class="form-control w-110" value="${endDate}" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'})"/>
        </div>
        <div class="ui-filter" style="margin:0px 15px;">
            <a id="search_link" class="btn btn-primary"><i class="fa fa-search"></i>查询</a>
        </div>
        <div class="win-box">
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
        <%--加载--%>
        <div class="my_load">
            <img src="${ctx}/resources/static/images/ajax-loader.gif" alt="">
        </div>
<script>
    var ctx = "${ctx}",unit="kwh";
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
<script type="text/javascript" src="${ctx}/resources/static/plugins/jqgrid/js/jquery.jqGrid.src.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/tablecommon.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/popup.js?v=${v}"></script>
<script type="text/javascript">
    // 设置表格
    function getGridHead() {

        return [
            {'label': 'id', 'name': 'id', 'index': 'id', 'hidden': true},
            {'label': '设备名称', 'name': 'name', 'index': 'name'},
            {'label': '时间', 'name': 'date', 'index': 'date', 'align' : 'center'},
            {'label': '消耗值('+unit+')', 'name': 'qty', 'index': 'qty', 'align' : 'right'}
        ];
    };
    //查询按钮
    $("#search_link").click(function () {
        var type=$('#equipmentType').attr('data-value');
        if(type==7){
            unit="kwh"
        }else if(type==8){
            unit="m³"
        }
        $("#jqgh_gridTable_qty").html("消耗值("+unit+")")
    });
    // 点击查询，获取查询所需要的条件，重新加载表格
    function getGridParamJson() {
    	var params = new Object();
    	params['constructionId'] = $('#constructionId').val();
    	params['equipmentType'] = $('#equipmentType').attr('data-value');
    	params['startDate'] = $('#startDate').val();
    	params['endDate'] = $('#endDate').val();
        return params;
    };
    //获取Url
    function getGridUrl() {
        return ctx + "/energyMonitor/queryDataList";
    };
    $(function(){
    	//导出
        $("#export_link").click(function () {
            $(".my_load").show();
        	$.ajax({
                type: "post",
                url: ctx + '/energyMonitor/exportExcel',
                data: getGridParamJson(),
                success: function (data) {
                    if(data.status == 1){
                        var filePath = data.value;
                        var link = '<%=path%>/' + filePath;
                        window.location.href=link;
                    } else {
                    	alert(data.msg);
                    }
                    $(".my_load").hide();
                },
                error: function (data, status, e) {   //提交失败自动执行的处理函数
                    alert(e);
                    $(".my_load").hide();
                }
            });
        });
    });
</script>

</body>
</html>