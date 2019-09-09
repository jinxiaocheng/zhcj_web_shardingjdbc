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
    <title>人脸抓拍</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/layui/css/layui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/person/face.css?v=${v}"/>
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
            <div class="ui-filter">
                <label>时间段:</label>
                <input id="time1" class="Wdate form-control w-110" style="width: 170px !important;" type="text" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'time2\')}',dateFmt:'yyyy-MM-dd H:mm:ss'})"/>~
                <input id="time2" class="Wdate form-control w-110" style="width: 170px !important;" type="text" onclick="WdatePicker({minDate:'#F{$dp.$D(\'time1\')}',dateFmt:'yyyy-MM-dd H:mm:ss'})"/>
            </div>
            <div class="ui-filter">
                <label>摄像头:</label>
                <div class="btn-group queryCondition">
                    <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
                       data-value="" id="video">全部</a>
                    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                            class="caret"></span></a>
                    <ul class="dropdown-menu video" style="min-width:108px;max-height:448px;overflow: auto">
                    </ul>
                </div>
            </div>
            <div class="ui-filter" style="margin:10px 15px;">
                <a id="search_link" class="btn btn-primary" data-toggle="collapse" data-target="#search-input"><i class="fa fa-search"></i>查询</a>
            </div>
        </div>
        <div class="tools-btn" id="tools-btn">
        </div>
    </div>
    <div class="wrapper">
        <ul>
         <%--   <li>
                <h2><i></i>恒信入口1</h2>
                <div class="aisle-box">
                    <div class="face-img-box">
                        <div class="face-img">
                            <p class="img-time">2018-11-8 11:34:44</p>
                            <img src="${ctx}/resources/static/images/test/1.jpg" alt="">
                            <p class="aisle-name">恒信入口1</p>
                        </div>
                        <a  class="historical-route-btn">历史轨迹</a>
                    </div>

                </div>
            </li>--%>
        </ul>
        <p class="nullInfo" style="color: red;text-align: center;position: absolute;top: 0;display: none;">没有找到您要的相关数据!</p>
    </div>
    <div id="laypage" class="laypage"></div>
</div>

<%--历史轨迹弹出窗=>时间轨迹--%>
<div class="popoverBox">
    <h2><i></i>时间轨迹</h2>
    <div class="aisle-box">
       <%-- <div class="face-img-box">
            <div class="face-img">
                <p class="img-time">2018-11-8 11:34:44</p>
                <img src="${ctx}/resources/static/images/test/1.jpg" alt="">
                <p class="aisle-name">恒信入口1</p>
            </div>
        </div>--%>
    </div>
</div>

<script>
    var ctx = "${ctx}",userId="${userId}";
</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/listCommon.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/list_area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/person/face.js?v=${v}"></script>
<script>
    $(function () {
        var date=new Date();
        $("#time2").val(curentTime(date));
        $("#time1").val(curentTime(new Date(date.setDate(date.getDate()-14))));
        init(function(){
            getCamera();
        });
        personInfo();
        openWin('/faceRecognition/listTrack');
        $("#search_link").click(function(){
            personInfo();
        })

    })
</script>
</body>
</html>