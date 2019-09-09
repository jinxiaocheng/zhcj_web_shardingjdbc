<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<%@ include file="/common/common_tag.jsp"%>--%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>实名制-统计查询</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/smz/count/list.css?v=${v}">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
</head>
<body>

<div class="container-full">
    <div class="ui-report">
        <!--更多查询条件-->
        <div class="form-inline response-head titlePanel" id="listTools">
            <div class="ui-filter">
                <label><span style="font-weight: bold">当前数据</span>&nbsp;&nbsp;&nbsp;日期从:</label>
                <input id="startDate" class="Wdate form-control w-110" type="text" value="2018-01-01" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})"/> 到
                <input id="endDate" class="Wdate form-control w-110" type="text" value="2018-06-30" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})"/>
            </div>
            <div class="ui-filter">
                <a id="search_link" class="btn btn-primary" data-toggle="collapse" data-target="#search-input"><i class="fa fa-search"></i>查询</a>
            </div>
        </div>
        <div class="jqGrid_wrapper">
            <div class="pxData data-table">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th colspan="5" class="title">培训数据</th>
                        </tr>
                        <tr>
                            <th>总人数</th>
                            <th>累计培训人数</th>
                            <th>累计培训人次</th>
                            <th>人均培训次数</th>
                            <th>人均培训学时</th>
                        </tr>
                    </thead>
                    <tbody> </tbody>
                </table>
            </div>
            <div class="ksData data-table">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th colspan="5" class="title">考试数据</th>
                        </tr>
                        <tr>
                            <th>考试人次</th>
                            <th>合格人次</th>
                            <th>考试合格率</th>
                        </tr>
                    </thead>
                    <tbody> </tbody>
                </table>
            </div>
            <div class="allData data-table">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th></th>
                            <th>单位</th>
                            <th >现有总人数</th>
                            <th >历史总人数</th>
                            <th >培训人数</th>
                            <th >培训率</th>
                            <th >培训人次</th>
                            <th>累计学时</th>
                            <th >人均学时</th>
                            <th >考试人次</th>
                            <th >合格人次</th>
                            <th >考试合格率</th>
                        </tr>
                    </thead>
                    <tbody> </tbody>
                </table>
            </div>
        </div>
        <!--更多查询条件-->
        <div class="form-inline response-head titlePanel" id="echartsTools">
            <div class="ui-filter unit">
                <label>统计单位:</label>
                <div class="btn-group queryCondition ">
                    <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
                       data-value="" id="unit">请选择</a>
                    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                            class="caret"></span></a>
                    <ul class="dropdown-menu " style="min-width:108px;max-height:250px;overflow: auto">
                    </ul>
                </div>
            </div>
            <div class="ui-filter target">
                <label>统计指标:</label>
                <div class="btn-group queryCondition">
                    <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
                       data-value="0" id="target">员工人数</a>
                    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                            class="caret"></span></a>
                    <ul class="dropdown-menu " style="min-width:108px;max-height:250px;overflow: auto">
                        <li><a data-value="0">员工人数</a></li>
                        <li><a data-value="1">培训人数</a></li>
                        <li><a data-value="2">培训率</a></li>
                        <li><a data-value="3">培训人次</a></li>
                        <li><a data-value="4">人均次数</a></li>
                        <li><a data-value="5">累计学时</a></li>
                        <li><a data-value="6">人均学时</a></li>
                        <li><a data-value="7">考试人次</a></li>
                        <li><a data-value="8">合格人次</a></li>
                        <li><a data-value="9">考试合格率</a></li>
                    </ul>
                </div>
            </div>
            <div class="ui-filter">
                <label>日期时间段:</label>
                <input id="startTime" class="Wdate form-control w-110" type="text" value="2018-01-01" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})"/>~
                <input id="endTime" class="Wdate form-control w-110" type="text" value="2018-06-30" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})"/>
            </div>
            <div class="ui-filter">
                <a id="search_link_echarts" class="btn btn-primary" data-toggle="collapse" data-target="#search-input"><i class="fa fa-search"></i>查询</a>
            </div>
        </div>
        <div class="jqGrid_wrapper">
            <div class="echarts-box" id="mainPie"></div>
        </div>
    </div>
</div>

<script>
    var ctx = "${ctx}";
    var type = "scheduledPlan";
</script>
<script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/echarts.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/smz/count/list.js?v=${v}"></script>
<script>
    $(function(){
        var date=new Date();
        $("#endDate").val(biyue.timeText(date));
        $("#startDate").val(date.getFullYear()+'-01-01');
        $("#endTime").val(biyue.timeText(date));
        $("#startTime").val(date.getFullYear()+'-01-01');
        count.default();
        count.getData();
        count.getUnit();
        biyue.select();
    })
</script>
</body>
</html>
