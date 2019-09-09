<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>考勤统计</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/layui/css/layui.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/attendance/listNew.css?v=${v}"/>
</head>
<body class="body_bg">
<div class="biyue_body">
    <div class="biyue_search layui-form">
        <div class="layui-inline">
            <label class="layui-form-label" style="line-height: 45px;">开始时间</label>
            <div class="layui-input-inline">
                <input type="text" name="startTime" placeholder="请输入开始时间" lay-verify="required" autocomplete="off" class="layui-input time1" id="startTime">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label" style="line-height: 45px;">结束时间</label>
            <div class="layui-input-inline">
                <input type="text" name="endTime" placeholder="请输入结束时间" lay-verify="required" autocomplete="off" class="layui-input time2" id="endTime">
            </div>
        </div>
        <button class="layui-btn layui-btn-primary search" id="search"><i class=" layui-icon"></i>  搜索</button>
        <button class="layui-btn layui-btn-primary more-hide" id="search-more">更多查询条件 <i
                class="fa  fa-angle-double-down"></i></button>
        <div class="search-more-box layui-anim layui-anim-upbit">
            <div class="layui-form-item area">
                <label class="layui-form-label">区域</label>
                <div class="layui-input-inline">
                    <select name="area" lay-search lay-filter="area">
                        <option value="">选择区域</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item site">
                <label class="layui-form-label">工地</label>
                <div class="layui-input-inline">
                    <select name="site" lay-search lay-filter="site">
                        <option value="">选择工地</option>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="biyue-main-box">
        <div class="tableData" style="margin-top: 5px;">
            <div class="table-box" style="margin-top: 0px;min-height: 360px">
                <div class="tableList" id="table-list" lay-filter="table-list">

                </div>
            </div>
        </div>
        <div class="lineData" style="margin-top: 15px">
            <div class="title-box layui-form">
                <label class="layui-form-label">公司</label>
                <div class="layui-input-inline">
                    <select name="company" lay-search lay-filter="company">
                        <option value="">选择公司</option>
                    </select>
                </div>
            </div>
            <div class="lineDataEcharts e-box">
                <div id="mainPie"></div>
            </div>
        </div>

    </div>
</div>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="view">查看明细</a>
</script>
<script>var ctx='${ctx}',userId='${userId}';</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/echarts.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/echarts.theme.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/attendance/listNew.js?v=${v}"></script>
<script>
    $(function () {
        var area =biyue.area();
        area.area({
            type:"table",
            complete:function () {
                biyueCount.createTable();
                biyueCount.init();
            }
        });
        biyue.listCommon();
        biyue.biyueControl();
      //  biyue.scroll(".biyue-main-box");

    })
</script>
</body>
</html>
