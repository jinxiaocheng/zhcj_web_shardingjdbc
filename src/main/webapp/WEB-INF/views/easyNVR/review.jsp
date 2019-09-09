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
    <title>视频回放</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqgrid/css/ui.jqgrid.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.structure.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/easyNvr/review.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/video-js-5.19.2/video-js.css" />
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
    <!--更多查询条件-->
    <div class="form-inline response-head titlePanel" id="listTools">
        <button type="button" class="btn openSearchBtn" data-toggle="collapse"
                data-target="#search-input">
            查询条件 <span class="glyphicon glyphicon-menu-down"></span>
        </button>
        <div id="search-input" class="collapse">
            <div class="ui-filter">
                <label>检查日期时间段:</label>
                <input id="time1" class="Wdate form-control w-110" type="text" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'time2\')}'})"/>~
                <input id="time2" class="Wdate form-control w-110" type="text" onclick="WdatePicker({minDate:'#F{$dp.$D(\'time1\')}'})"/>
            </div>
            <div class="ui-filter" style="margin:10px 15px;">
                <a id="search_link" class="btn btn-primary" data-toggle="collapse" data-target="#search-input"><i class="fa fa-search"></i>查询</a>
            </div>
        </div>
        <div class="tools-btn" id="tools-btn"></div>
    </div>
    <div class="jqGrid_wrapper">
        <table class="gridTable table-condensed" id="gridTable" aa="1"></table>
        <div class="gridPage" id="gridPager">

        </div>
    </div>
</div>

<%--//视频模块弹出窗--%>
<div id="reviewPlay">

</div>

<div id="model" style="display: none">
    <video class="video-js vjs-default-skin vjs-big-play-centered" style="width: 100%; height: 100%;" controls preload="none"
           poster="">
        <source src="" type=""></source>
        <p class="vjs-no-js">
            To view this video please enable JavaScript, and consider upgrading to a web browser that
            <a href="http://videojs.com/html5-video-support/" target="_blank">
                supports HTML5 video
            </a>
        </p>
    </video>
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
<script src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/video-js-5.19.2/video.js"></script>
<script src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/video-js-5.19.2/videojs-contrib-hls4.js"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jqgrid/js/jquery.jqGrid.src.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/popup.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/list_area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/easyNvr/review.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/tablecommon.js?v=${v}" ></script>
<script type="text/javascript"></script>
</body>
</html>