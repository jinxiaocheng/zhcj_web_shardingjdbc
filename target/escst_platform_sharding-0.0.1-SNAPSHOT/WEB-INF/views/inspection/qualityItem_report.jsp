<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>质量整改报表</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/role/add.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/inspection/qualityItem_report.css?v=${v}">
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
        <div id="search-input" style="left: 0" class="collapse">
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
            <div class="ui-filter comCompany">
                <label>公司:</label>
                <div class="btn-group queryCondition">
                    <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
                       data-value="" id="company">请选择</a>
                    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                            class="caret"></span></a>
                    <ul class="dropdown-menu" style="min-width:108px;max-height:448px;overflow: auto">
                    </ul>
                </div>
            </div>
            <div class="ui-filter">
                <label>检查项名称:</label>
                <input id="items" class="form-control w-110" type="text" />
            </div>
            <div class="ui-filter">
                <label>日期段:</label> <input type="text" class="form-control w-110" id='startDate' name='approachDateStart'
                                           onclick="WdatePicker()" placeholder="选择开始时间" dategroup="true" />&nbsp;~&nbsp;<input
                    type="text" class="form-control w-110"  id='endDate' name='approachDateEnd' onclick="WdatePicker()"
                    placeholder="选择结束时间" dategroup="true"/>
            </div>
            <div class="ui-filter" style="margin:10px 15px;">
                <a id="search_link" class="btn btn-primary" data-toggle="collapse" data-target="#search-input"><i class="fa fa-search"></i>查询</a>
            </div>
        </div>
        <div class="tools-btn" id="tools-btn">
        </div>
    </div>

    <div class="jqGrid_wrapper">
        <div class="tab-content">
            <div role="tabpanel" id="tableBox" class="tableBox tab-pane checkbox checkbox-success active">
                <div class="tableHeader">
                    <table lay-filter="demo">
                        <thead>
                        <tr>
                            <th data-head="1">公司名称</th>
                            <th data-head="2">检查项</th>
                            <th data-head="3">数量</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <div class="tableBody">
                    <table lay-filter="demo">
                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>
            <div role="tabpanel" id="tableBoxApp" class="tableBox tab-pane checkbox checkbox-success">
                <div class="tableHeader">
                    <table lay-filter="demo">
                        <thead>
                        <tr>
                            <th data-head="1">公司</th>
                            <th data-head="2">检查项</th>
                            <th data-head="3">数量</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <div class="tableBody">
                    <table lay-filter="demo">
                        <tbody></tbody>
                    </table>
                </div>
            </div>
        </div>
        <p class="error">没有找到您要的相关数据!</p>
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
<script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/list_area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/inspection/qualityItem_report.js?v=${v}"></script>
<script type="text/javascript">
    $(function () {
        init(function (id) {
            comCompany(id);
        });
        comCompany();
        report.getAjax({
            data:{
                type:1,
                items: $.trim($("#items").val()),
                projectCompanyId:$("#company").attr("data-value"),
                constructionId:$("#site").attr("data-value"),
                startDate:$("#startDate").val(),
                endDate:$("#endDate").val()
            }
        });
        //查询
        $("#search_link").click(function () {
            report.getAjax({
                data:{
                    type:1,
                    items: $.trim($("#items").val()),
                    constructionId:$("#site").attr("data-value"),
                    projectCompanyId:$("#company").attr("data-value"),
                    startDate:$("#startDate").val(),
                    endDate:$("#endDate").val()
                }
            })
        });
    });
</script>
</body>
</html>