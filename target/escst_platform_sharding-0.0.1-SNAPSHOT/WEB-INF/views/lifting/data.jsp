<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>数据监测</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/layui/css/layui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/layui_list.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/lifting/data.css?v=${v}"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
</head>
<body class="biyue-bg">
<div class="biyue-right-table biyue-box">
    <div class="biyue_body">
        <div class="biyue_search layui-form">
            <div class="layui-inline area">
                <label class="layui-form-label" style="line-height: 43px;width: 40px;">区域</label>
                <div class="layui-input-inline">
                    <select name="area" lay-search lay-filter="area">
                        <option value="">选择区域</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline site">
                <label class="layui-form-label" style="line-height: 43px;width: 40px;">工地</label>
                <div class="layui-input-inline">
                    <select name="site" lay-search lay-filter="site">
                        <option value="">选择工地</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="line-height: 43px;">设备名称</label>
                <div class="layui-input-inline">
                    <select name="hook" lay-search lay-filter="hook">
                        <option value="">选择设备名称</option>
                    </select>
                </div>
            </div>
            <button class="layui-btn layui-btn-primary search" style="margin-top: 1px;" id="search"><i
                    class=" layui-icon"></i> 搜索
            </button>
        </div>
        <div class="biyue_main">
            <div class="hookImg">
                <div class="layui-card">
                    <div class="layui-card-header">
                        <h2>设备模型</h2>
                    </div>
                    <div class="layui-card-body layadmin-takerates">
                        <canvas id="hookCanvas" width="680px" height="477px">
                            你的浏览器不支持canvas,请使用高版本浏览器;
                        </canvas>
                        <ul class="nowData">
                            <li class="nd-time"><i></i><span>数据发送时间:</span><label>0000-00-00 00:00:00</label></li>
                            <li class="nd-extent"><i></i><span>幅度:</span><label>0</label>m</li>
                            <li class="nd-height"><i></i><span>高度:</span><label>0</label>m</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="dataList">
                <div class="layui-card">
                    <div class="layui-card-header"><h2>设备数据列表
                        <i class="moreData" style="float: right;cursor: pointer;color: #1e88e5">更多数据>></i></h2></div>
                    <div class="layui-card-body layadmin-takerates">
                        <table id="table-list" lay-filter="table-list"></table>
                    </div>
                </div>
            </div>
            <div class="dataChart">
                <div class="layui-card">
                    <div class="layui-card-header"><h2>当日趋势分析</h2></div>
                    <div class="layui-card-body layadmin-takerates">
                        <div id="chartsBox"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var ctx = "${ctx}", userId = "${userId}";
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/echarts.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/echarts.theme.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/equipment/towerCraneMonitor.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lifting/data.js?v=${v}"></script>
<script>
    $(function () {
        var area = biyue.area();
        area.area({
            type: "table",
            fun: function (id) {
                biyueList.getHook(id);
            }
        });
        biyue.biyueControl();
        biyueList.init();
        biyueList.createTable();
        biyueList.createCharts();
    })
</script>
</body>
</html>
