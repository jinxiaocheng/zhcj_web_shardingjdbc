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
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/energy/chart.css?v=${v}"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
</head>

<body class="body_bg">
<div class="mainPart" style="border: 1px solid #cfcfcf;background: #fff">
    <div class="response-head titlePanel form-inline">
        <div class="ui-filter">
            <label>类型:</label>
            <div class="btn-group queryCondition">
                <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
                   data-value="1" id="equipmentType">电</a>
                <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                        class="caret"></span></a>
                <ul class="dropdown-menu" style="min-width:108px;">
                    <li><a data-value="1">电</a></li>
                    <li><a data-value="2">水</a></li>
                </ul>
            </div>
        </div>
        <div class="ui-filter">
            <label>统计频率:</label>
            <div class="btn-group queryCondition">
                <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
                   data-value="1" id="type">日</a>
                <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                        class="caret"></span></a>
                <ul class="dropdown-menu" style="min-width:108px;">
                    <li><a data-value="1">日</a></li>
                    <li><a data-value="2">7天</a></li>
                    <li><a data-value="3">月</a></li>
                </ul>
            </div>
        </div>
        <div class="ui-filter" style="margin:0px 15px;">
            <a id="search_link" class="btn btn-primary"><i class="fa fa-search"></i>查询</a>
        </div>
        <%--<div class="win-box">
            <div class="btn-group">
                <a id="add_link" class="btn btn-info">分表</a>
            </div>
        </div>--%>

    </div>
    <div id="chart"></div>
</div>

</body>
<script>
    var ctx = "${ctx}",userId = "${userId}",constructionId="${constructionId}";
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
<script type="text/javascript" src="${ctx}/resources/static/js/lib/echarts.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/energy/chart.js?v=${v}"></script>
<script>
    $(function(){
        my_ajax(1,1);
        $("#search_link").click(function(){
            var equipmentType=$("#equipmentType").attr("data-value"),
                my_type=$("#type").attr("data-value")
            my_ajax(equipmentType,my_type);
        })

    })
</script>
</html>