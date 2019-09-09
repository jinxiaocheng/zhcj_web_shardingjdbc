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
    <title>第一炼铁厂工地安全智能化数据监控系统</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/homepage/monitor.css?v=${v}"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
</head>

<body class="">
<div class="biyue-body">
    <div class="body-box">
        <header class="header">
            <div class="title">
                <span>第一炼铁厂工地安全智能化数据监控系统</span>
            </div>
            <div class="other">
                <div class="weather">
                    <i><img src="${ctx}/resources/static/images/monitor/duan_xian%20.png" alt=""></i>
                    <span></span>
                    <label></label>
                </div>
                <div class="time">
                    <i><img src="${ctx}/resources/static/images/monitor/time.png" alt=""></i>
                    <span>0000-00-00 00:00:00</span>
                </div>
                <i class="t"></i>
                <i class="b"></i>
            </div>
        </header>
        <div class="content">
            <div class="count">
                <div class="m-box startTime">
                    <div class="text-box">
                        <i class="icon-box"></i>
                        <p class="title-box">开工日期</p>
                        <p class="content-box">0000-00-00</p>
                    </div>
                    <i class="lt"></i>
                    <i class="rt"></i>
                    <i class="lb"></i>
                    <i class="rb"></i>
                </div>
                <div class="m-box endTime">
                    <div class="text-box">
                        <i class="icon-box"></i>
                        <p class="title-box">竣工日期</p>
                        <p class="content-box">0000-00-00</p>
                    </div>
                    <i class="lt"></i>
                    <i class="rt"></i>
                    <i class="lb"></i>
                    <i class="rb"></i>
                </div>
                <div class="m-box dayNum">
                    <div class="text-box">
                        <i class="icon-box"></i>
                        <p class="title-box">剩余天数</p>
                        <p class="content-box">0 <span>天</span></p>
                    </div>
                    <i class="lt"></i>
                    <i class="rt"></i>
                    <i class="lb"></i>
                    <i class="rb"></i>
                </div>
                <div class="m-box progress">
                    <div class="text-box">
                        <i class="icon-box"></i>
                        <p class="title-box">项目进度</p>
                        <div class="content-box">
                            <div class="pro-box">
                                <div class="pro-bar" style="width:0%"></div>
                            </div>
                            <span class="pro-num">0%</span>
                        </div>
                    </div>
                    <i class="lt"></i>
                    <i class="rt"></i>
                    <i class="lb"></i>
                    <i class="rb"></i>
                </div>
            </div>
            <div class="danger m-box">
                <div class="text-box">
                    <div class="m-title">
                        <i class="yellow"></i>
                        <p class="m-name">危险作业</p>
                        <u style="border-bottom: 20px solid #1f1b3d;">
                            <span></span>
                        </u>
                    </div>
                    <div class="main-box">
                        <div class="danger-box">

                        </div>
                        <%-- <div class="m-null">
                             <img src="${ctx}/resources/static/images/monitor/404.png" alt="">
                             <p>暂无危险作业!</p>
                         </div>--%>
                    </div>

                </div>

                <i class="lt"></i>
                <i class="rt"></i>
                <i class="lb"></i>
                <i class="rb"></i>
            </div>
            <div class="safe m-box">
                <div class="text-box">
                    <div class="m-title">
                        <i class="blue"></i>
                        <p class="m-name">安全检查</p>
                        <u>
                            <span></span>
                        </u>
                    </div>
                    <div class="main-box list-box">
                        <ul>
                        </ul>
                        <%--<div class="m-null">
                            <img src="${ctx}/resources/static/images/monitor/404.png" alt="">
                            <p>暂无安全检查!</p>
                        </div>--%>
                    </div>
                </div>

                <i class="lt"></i>
                <i class="rt"></i>
                <i class="lb"></i>
                <i class="rb"></i>
            </div>
        </div>
    </div>
</div>
<script>
    var ctx = "${ctx}";
    var type = "${type}";
    var userId="${userId}";
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/monitor/monitor.js?v=${v}"></script>
<script>
    monitor.default();
</script>
</body>
</html>