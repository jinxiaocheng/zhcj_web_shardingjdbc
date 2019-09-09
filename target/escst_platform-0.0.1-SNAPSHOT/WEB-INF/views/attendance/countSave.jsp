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
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}">
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
        <div class="layui-inline area">
            <label class="layui-form-label">区域</label>
            <div class="layui-input-inline">
                <select name="area" lay-search lay-verify="required" lay-filter="area">
                    <option value="">选择区域</option>
                </select>
            </div>
        </div>
        <div class="layui-inline site">
            <label class="layui-form-label">工地</label>
            <div class="layui-input-inline">
                <select name="site" lay-search lay-verify="required" lay-filter="site">
                    <option value="">选择工地</option>
                </select>
            </div>
        </div>
        <div class="layui-inline area_edit" style="display: none">
            <label class="layui-form-label ">区域</label>
            <div class="layui-input-inline ">
                <input type="text" name="areaName" lay-verify="required" class="layui-input" readonly
                       placeholder="区域名称" value="">
                <input type="text" name="areaId" class="layui-input" readonly style="display: none">
            </div>
        </div>
        <div class="layui-inline site_edit" style="display: none">
            <label class="layui-form-label">工地</label>
            <div class="layui-input-inline site_edit">
                <input type="text" name="constructionName" lay-verify="required" class="layui-input" readonly
                       placeholder="工地名称" value="">
                <input type="text" name="constructionId" class="layui-input" readonly style="display: none">
            </div>
        </div>
    </div>
    <h2 class="title"><p>基本信息</p></h2>
    <div class="layui-form-item">
        <div class="layui-form-item">
            <label class="layui-form-label">考勤日期</label>
            <div class="layui-input-block">
                <input type="text" name="attendanceDate" lay-verify="required" placeholder="请输入考勤日期" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

    </div>
    <div class="teamList" style="width: 100%">
        <h2 class="title"><p>班组信息</p></h2>
        <button type="button" class="layui-btn  layui-btn-normal" id="add-model"><i class="layui-icon layui-icon-add-circle-fine" style="display: inline-block"></i>选择班组</button>
        <table class="layui-table channel">
            <colgroup>
                <col width="60">
                <col width="160">
                <col >
                <col width="60">
            </colgroup>
            <thead>
            <tr>
                <th>序号</th>
                <th>班组名称</th>
                <th>出勤人数(必填)</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <!--<tr>
                <td>1</td>
                <td><input type="text" style="width: 100%" name="channelName"></td>
                <td><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a></td>
            </tr>-->
            </tbody>
        </table>
    </div>
    <div class="biyue_footer">
        <div class="control-btn">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="add" id="submit">确定</button>
            <button class="layui-btn layui-btn-primary" type="button" id="com-close">关闭</button>
        </div>
    </div>
</form>
<%--班组类别弹出层--%>
<div id="check_result_tree">
    <div id="itemsZtree" class="ztree">
        <p>请先选择所属公司</p>
    </div>
</div>
<script>
    var ctx = "${ctx}", userId = "${userId}";
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.excheck-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/attendance/countAdd.js?v=${v}"></script>
<script>
    $(function () {
        biyue.verify();
        biyue.popDefault();
        var area = biyue.area();
        area.area({
            type: "pop"
        });
        biyueAdd.getTeamModel();
        biyueAdd.updata(); //数据上传
        biyueAdd.event();
        biyueAdd.addList();
    })
</script>
</body>
</html>
