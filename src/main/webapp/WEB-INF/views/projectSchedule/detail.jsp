<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>查看详情</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqgrid/css/ui.jqgrid.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
    <style>
        body{
            padding: 15px;
        }
        table{
            width: 100px;
            box-sizing: border-box;
        }
        .table th, .table td {
            vertical-align: middle!important;
        }
        table tr td:nth-of-type(1){
            text-align: right;
        }
        table tr td:nth-of-type(3){
            text-align: right;
        }
        #gridPager{
            bottom: 30px;
        }
        .progress_slip{
            width: 100%;
            height: 15px;
        }
        .progress_slip span{
            display: inline-block;
            position: relative;
            width: 60%;
            height: 100%;
            overflow: hidden;
        }
        .progress_slip span u{
            display: inline-block;
            background: rgba(204, 204, 204, 0.84);
            height: 100%;
            position: absolute;
            overflow: hidden;
            left: 0;
            top:0;
        }
        .progress_slip span i{
            display: inline-block;
            background: rgb(67, 227, 125);
            height: 100%;
            position: absolute;
            overflow: hidden;
            left: 0;
            top:0;
        }
        .progress_slip label{
            display: inline-block;
            width: 40px;
            height: 100%;
            position: relative;
            top: -2px;
        }
        .ui-jqgrid tr.ui-row-ltr td {
            text-align: left;
            border-right-width: 1px;
            border-right-color: #ddd;
            border-right-style: solid;
             white-space: inherit;
             overflow: inherit;
            /* word-break: keep-all; */
             word-wrap: break-word;
             word-break: normal;
        }
        .ui-jqgrid tr.jqgrow td {
            font-weight: normal;
             overflow: visible;
             white-space: normal;
            height: 30px;
            padding: 1px 8px 1px 8px;
            border-bottom-width: 1px;
            border-bottom-color: #ddd;
            border-bottom-style: solid;
        }

    </style>
</head>

<body class="body_bottom">

<%--<c:forEach items="${list }" var="vo">
<table class="table table-bordered">
    <tr>
        <td >进度名称:</td>
        <td colspan="3" >${vo.name }</td>
    </tr>
    <tr>
        <td>开始日期:</td>
        <td colspan="3" >${vo.startTime }</td>
    </tr>
    <tr>
        <td>结束日期:</td>
        <td colspan="3" >${vo.endTime }</td>
    </tr>
</table>
    </c:forEach>--%>

<div class="jqGrid_wrapper">
    <table class="gridTable table-condensed" id="gridTable" aa="1"></table>
    <div class="gridPage" id="gridPager">

    </div>
</div>


<script>var ctx = "${ctx}",parentId="${parentId}",constructionId="${constructionId}";</script>
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
<script src="${ctx}/resources/static/js/tablecommon.js?v=${v}" type="text/javascript"></script>

<script>
    // 设置表格
    function getGridHead() {
        return [
            {'label': 'id', 'name': 'id', 'index': 'id', 'hidden': true},
            {'label': '进度名称', 'name': 'name', 'index': 'name'},
            {'label': '工地名称', 'name': 'constructionName', 'index': 'constructionName'},
            {'label': '计划工期(天)', 'name': 'planDays', 'index': 'planDays'},
            {'label': '实际工期(天)', 'name': 'realDays', 'index': 'realDays'},
            {'label': '工程进度(%)', 'name': 'percent', 'index': 'percent',formatter:function(cellvalue, options, rowObject) {
                var percent = rowObject.percent||0;
                var temp = '<div class="progress_slip"><label>'+percent+'%</label><span><u style="width: '+100+'%;left:'+0+'%"><i style="width: '+percent+'%;"></i></u></span></div>';
                return temp;
            }},
            {'label': '状态', 'name': 'status', 'index': 'status' ,'formatter': 'select', 'editoptions': {value: "0:未开始;1:进行中;2:已完成"}}
        
        ];
    };
    // 点击查询，获取查询所需要的条件，重新加载表格
    function getGridParamJson() {
        var postPrams = {
            parentId:parentId,
            constructionId:constructionId
        };
        return postPrams;
    };
    //获取Url
    function getGridUrl() {
        return ctx + "/projectSchedule/projectScheduleList";
    };
</script>
</body>
</html>
