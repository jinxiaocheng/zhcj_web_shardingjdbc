<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>设备登记-新增</title>
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
                       placeholder="区域名称">
                <input type="text" name="areaId" class="layui-input" readonly style="display: none">
            </div>
        </div>
        <div class="layui-inline site_edit" style="display: none">
            <label class="layui-form-label">工地</label>
            <div class="layui-input-inline site_edit">
                <input type="text" name="constructionName" lay-verify="required" class="layui-input" readonly
                       placeholder="工地名称">
                <input type="text" name="constructionId" class="layui-input" readonly style="display: none">
            </div>
        </div>
    </div>
    <h2 class="title"><p>基本信息</p></h2>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">设备名称</label>
            <div class="layui-input-inline">
                <input type="text" name="name" placeholder="请输入设备名称" lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">设备编号</label>
            <div class="layui-input-inline">
                <input type="text" name="number" placeholder="请输入设备编号" lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">设备型号</label>
            <div class="layui-input-inline">
                <input type="text" name="model" placeholder="请输入设备型号"  autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">生产厂家</label>
            <div class="layui-input-inline">
                <input type="text" name="manufacturer" placeholder="请输入生产厂家"  autocomplete="off"
                       class="layui-input">
            </div>
        </div>
    </div>
    <h2 class="title"><p>备注</p></h2>
    <div class="layui-form-item">
        <textarea placeholder="请输入内容(内容最大为500字)" name="remark"  lay-verify="textarea" class="layui-textarea"></textarea>
    </div>

    <div class="biyue_footer">
        <div class="control-btn">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="add" id="submit">确定</button>
            <button class="layui-btn layui-btn-primary" type="button" id="com-close">关闭</button>
        </div>
    </div>
</form>

<script>
    var ctx = "${ctx}", userId = "${userId}";
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lifting/equipmentAdd.js?v=${v}"></script>
<script>
    $(function () {
        biyue.verify();
        var area = biyue.area();
        area.area({
            type:"pop"
        });
        if (biyue.urlData.type === "edit") {
            biyueAdd.getData();
        }
        biyue.popDefault();
        biyueAdd.updata();
    })
</script>
</body>
</html>
