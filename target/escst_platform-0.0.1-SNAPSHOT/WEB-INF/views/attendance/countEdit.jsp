<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>出勤登记-新增-编辑</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/layui/css/layui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/pop.css?v=${v}"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
</head>
<body class="biyue-bg">
<form class="layui-form pop-box" lay-filter="biyueOpen" action="#" method="post" enctype="multipart/form-data">
    <h2 class="title area"><p>区域工地</p></h2>
    <h2 class="title area_edit" style="display: none"><p>区域工地</p></h2>
    <div class="layui-form-item">
        <div class="layui-inline area_edit">
            <label class="layui-form-label ">区域</label>
            <div class="layui-input-inline ">
                <input type="text" value="${entity.areaName}" name="areaName" lay-verify="required" class="layui-input" readonly
                       placeholder="区域名称">
                <input type="text" name="areaId" value="${entity.areaId}" class="layui-input" readonly style="display: none">
            </div>
        </div>
        <div class="layui-inline site_edit">
            <label class="layui-form-label">工地</label>
            <div class="layui-input-inline site_edit">
                <input type="text" value="${entity.constructionName}" name="constructionName" lay-verify="required" class="layui-input" readonly
                       placeholder="工地名称">
                <input type="text" name="constructionId" value="${entity.constructionId}" class="layui-input" readonly style="display: none">
            </div>
        </div>
    </div>
    <h2 class="title"><p>基本信息</p></h2>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">考勤日期</label>
            <div class="layui-input-inline">
                <input type="text" value="${entity.attendanceDate}" name="attendanceDate" lay-verify="required" placeholder="请输入考勤日期" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">班组</label>
            <div class="layui-input-inline">
                <select name="teamName" lay-search lay-verify="required" lay-filter="teamName">
                    <option value="">选择班组</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">出勤人数</label>
            <div class="layui-input-inline">
                <input type="text" name="count" value="${entity.count}" lay-verify="required" placeholder="请输入出勤人数" autocomplete="off"
                       class="layui-input">
                <input type="text" name="id" value="${entity.id}" placeholder="" autocomplete="off"
                       class="layui-input" style="display: none">
            </div>
        </div>
    </div>
    <div class="biyue_footer">
        <div class="control-btn">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="add" id="submit">确定</button>
            <button class="layui-btn layui-btn-primary" type="button" id="com-close">关闭</button>
        </div>
    </div>
</form>

<script>
    var ctx = "${ctx}", userId = "${userId}", teamName = "${entity.teamName}";
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/attendance/countChange.js?v=${v}"></script>
<script>
    $(function () {
        biyue.verify();
        biyue.popDefault();
        biyueEdit.updata(); //数据上传
        biyueEdit.getTeam(teamName);
        biyueEdit.event();
    })
</script>
</body>
</html>
