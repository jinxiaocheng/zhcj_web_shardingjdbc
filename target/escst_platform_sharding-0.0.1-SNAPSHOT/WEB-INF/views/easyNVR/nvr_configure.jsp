<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>新增视频配置</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet"/>
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.min.css?v=${v}" rel="stylesheet"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/scrollbar.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/add.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/accident/add.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/attendance/countSave.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/ieControl.css?v=${v}">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
    <style>
        .channelList>div{
            width: 100%;
        }
        .channelList>div .channelNum{
            width: 10%;
            margin-left: 2%;
        }
    </style>

</head>
<body>
<form class="form-inline add_form" id="form_data">
    <div class="row add_list ">
        <div class="form-group col-xs-6 area" kk-contral="require">
            <label  class="col-xs-4">区域</label>
            <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="areaId" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                请选择区域
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu3">
            </ul>
        </div>
        <div class="form-group col-xs-6 site" kk-contral="require">
            <label  class="col-xs-4">工地</label>
            <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="constructionid" id="dropdownMenu4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                请选择工地
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu4">
            </ul>
        </div>
    </div>
    <div class="row add_list ">
        <div class="form-group col-xs-6 date_time" kk-contral="require">
            <label for="address" class="col-xs-4">ip地址</label>
            <input type="text" class="form-control col-xs-8 " placeholder="请输入ip地址" id="address" kk-prop="value" required>
        </div>
    </div>
    <div class="row add_list ">
        <div class="form-group col-xs-6 date_time" kk-contral="require">
            <label for="port" class="col-xs-4">WEB端口</label>
            <input type="number" class="form-control col-xs-8 " placeholder="请输入端口" id="port" kk-prop="value" required>
        </div>
        <div class="form-group col-xs-6 date_time" kk-contral="require">
            <label for="port" class="col-xs-4">APP端口</label>
            <input type="number" class="form-control col-xs-8 " placeholder="请输入端口" id="appPort" kk-prop="value" required>
        </div>
    </div>
    <div class="row add_list ">
        <div class="form-group col-xs-6 date_time" kk-contral="require">
            <label for="userName" class="col-xs-4">用户名</label>
            <input type="text" class="form-control col-xs-8 " id="userName" placeholder="请输入用户名" kk-prop="value" required>
        </div>
        <div class="form-group col-xs-6 date_time" kk-contral="require">
            <label for="password" class="col-xs-4">密码</label>
            <input type="text" class="form-control col-xs-8 " id="password" placeholder="请输入密码" kk-prop="value" required>
        </div>
    </div>
    <div class="row add_list" id="items" >
        <div kk-prop="data-value" data-value="" style="min-height: 100px;">
            <i class="alert alert-info alert-dismissible check_items_btn" role="alert">
                <span><label class="fa fa-plus"></label> 新增通道</span>
            </i>
            <div class="channelList"  style="margin-top: 10px">

            </div>
        </div>
    </div>
</form>

<div style="height: 60px;width: 100%"></div>
<footer>
    <a class="btn btn_submit btn-show-blue" id="submit">提&nbsp;交</a>
</footer>
<%--警告--%>
<div class="alert alert-danger alert-dismissable error">
    <button type="button" class="close" aria-hidden="true">
        &times;
    </button>
    <span>警告！请不要提交。</span>
</div>

<script>
    var ctx = "${ctx}";
    var userId = "${userId}",items_data=[];
</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}" ></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/easyNvr/nvr_configure.js?v=${v}"></script>
<script>
    $(function () {
        var search=getSearch();
        var type=search.type;
        if(type==1){ //为1时为编辑
            edit.getData(ctx+'/video/getNvrById',{
                id:search.id
            });
        }else{
            init();
        }
        addNvr.addChannel();

        $("#form_data").kk_tableForm("#submit",function(){
            addNvr.uploadData();
        });
    });
</script>