<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>采集仪维护-新增</title>
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
<form class="layui-form pop-box pop-box-special" action="javascript:void(0)">
    <h2 class="title area"><p>区域工地</p></h2>
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
                <select name="site" lay-search  lay-verify="required" lay-filter="site">
                    <option value="">选择工地</option>
                </select>
            </div>
        </div>
    </div>
    <h2 class="title"><p>采集仪信息</p></h2>
    <button type="button" class="layui-btn" id="add-channel"><i class="layui-icon layui-icon-add-circle-fine" style="display: inline-block"></i>新增采集仪</button>
    <table class="layui-table channel">
        <colgroup>
            <col width="50">
            <col >
            <col >
            <col width="60">
        </colgroup>
        <thead>
        <tr>
            <th></th>
            <th>采集仪名称</th>
            <th>采集仪编号</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <!--<tr>
            <td>1</td>
            <td><input type="text" style="width: 100%" name="channelName"></td>
            <td><input type="text" style="width: 100%" name="channelNum"></td>
            <td><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a></td>
        </tr>-->
        </tbody>
    </table>
    <div class="biyue_footer">
        <div class="control-btn">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="add" id="submit">确定</button>
            <button class="layui-btn layui-btn-primary" type="button" id="com-close">关闭</button>
        </div>
    </div>
</form>

<script>
    var ctx = "${ctx}",userId = "${userId}";
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/gzm/cjyAdd.js?v=${v}"></script>
<script>
    $(function () {
        biyue.verify();
        var area =biyue.area();
        area.area();
        biyue.popDefault();
        biyueAdd.addList();
        biyueAdd.updata();

    })
</script>
</body>
</html>
