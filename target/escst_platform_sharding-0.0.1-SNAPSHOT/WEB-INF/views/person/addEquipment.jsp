<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>新增门</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/layui/css/layui.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/layui_open.css?v=${v}.1"/>
</head>
<body class="body_bg layui-form" lay-filter="biyueOpen">
    <div class="biyue_body">
        <div class="layui-form-item">
            <label class="layui-form-label">选择工地</label>
            <div class="layui-input-inline area">
                <select name="area" lay-search lay-filter="area">
                    <option value="">选择区域</option>
                </select>
            </div>
            <div class="layui-input-inline site">
                <select name="site" lay-search lay-filter="site" lay-verify="required">
                    <option value="">选择工地</option>
                </select>
            </div>
            <div class="layui-input-inline area_edit">
                <input type="text" name="areaName" class="layui-input" readonly  placeholder="工地名称">
                <input type="text" name="areaId" class="layui-input" readonly style="display: none">
            </div>
            <div class="layui-input-inline site_edit">
                <input type="text" name="constructionName" lay-verify="required" class="layui-input" readonly  placeholder="区域名称">
                <input type="text" name="constructionId" class="layui-input" readonly style="display: none">
            </div>

        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">设备名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" lay-verify="required" autocomplete="off" placeholder="请输入设备名称" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">设备编号</label>
            <div class="layui-input-block">
                <input type="text" name="number" lay-verify="required" autocomplete="off" placeholder="请输入设备编号" class="layui-input">
            </div>
        </div>
    </div>
    <div class="biyue_footer">
        <button class="layui-btn layui-btn-normal" lay-submit lay-filter="add" id="submit">提交</button>
    </div>
<script>var ctx='${ctx}',userId='${userId}';</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/person/addEquipment.js?v=${v}"></script>
<script>
    $(function () {
        var element = layui.element;
        var biyue=new biYue();
        var searchObj=biyue.urlSearch();
        addDoor.upData();

        //编辑时
        if(searchObj.type==='edit'){
            $(".area,.site").remove();
            $(".area_edit,.site_edit").show();
            addDoor.getEdit();
        }else{ //新增
            var area =biyue.area();
            $(".area_edit,.site_edit").remove();
            area.area();
        }
        biyue.verify();
    })
</script>
</body>
</html>
