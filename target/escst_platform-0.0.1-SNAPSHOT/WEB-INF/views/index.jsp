<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>首页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="icon" href="${ctx}/resources/static/images/logo.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css"
          href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/waves/waves.min.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/css/index/index.css?v=${v}"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
    <script type="text/javascript">
        var ctx = "${ctx}";
        var level = "${level}";
    </script>
</head>
<body class="biyue-media-min">
<header class="biyue-main-header">
    <h1 class="biyue-logo">智慧城建 <a href=""><img src="${ctx}/resources/static/images/${logoName}" alt=""></a></h1>
    <div class="biyue-navbar">
        <div class="biyue-min nav-icons pull-left" data-toggle="tooltip" data-placement="bottom" title="缩小/放大左边菜单">
            <i class="fa fa-reorder "></i>
        </div>
        <div class="header-info"><p>江岸区智慧城建管理平台</p></div>
        <a href="logout" class="user-layout nav-icons pull-right" data-toggle="tooltip" data-placement="bottom"
           title="注销">
            <i class="fa fa-power-off "></i>
        </a>
        <div class="set-password nav-icons pull-right" data-toggle="tooltip" data-placement="bottom" title="修改密码">
            <i class="fa fa-cog "></i>
        </div>
        <div class="refresh nav-icons pull-right" data-toggle="tooltip" data-placement="bottom" title="刷新当前页">
            <i class="fa fa-repeat "></i>
        </div>
    </div>
</header>
<aside class="biyue-left-aside">
    <div class="biyue-header-aside">
        <div class="user-img"><img src="${ctx}/resources/static/images/user.png" class="user-image"/></div>
        <h2 class="user-name" data-toggle="modal" data-target="#myModal">${name} <i class="fa  fa-bookmark-o"></i></h2>
        <ul class="dropdown-menu flipInY flipOutY">
            <button type="button" class="close">
                ×
            </button>
            <h2><i class="fa fa-user-o"></i>${name}</h2>
            <li><a class="set-password" href="javascript:void(0);"><i class="fa fa-cog"></i>修改密码</a></li>
            <li class="divider"></li>
            <li><a href="logout"><i class="fa fa-power-off"></i>退出登录</a></li>
        </ul>
    </div>

    <div class="biyue-nav-aside ">
        <ul class="biyue-left-menu">
        </ul>
    </div>
    <div class="biyue-footer-aside">
        <div class="set-password bf" data-toggle="tooltip" data-placement="top" title="修改密码">
            <a class="fa fa-cog fa-spin"></a>
        </div>
        <div class="user-layout bf" data-toggle="tooltip" data-placement="top" title="退出登录">
            <a href="logout" class="fa fa-power-off"></a>
        </div>
    </div>
</aside>

<section class="biyue-main-section ">
    <div class="layui-tab layui-tab-card" lay-allowclose="true" lay-filter="demo">
        <div class="tab-tools ">
            <span class="tab-tools-hide"><i class="fa fa-gears"></i>操作</span><i class="fa fa-caret-down"></i>
            <ul class="tab-tools-list layui-anim layui-anim-upbit">
                <li class="refresh"><i class="fa fa-repeat"></i> 刷新当前页</li>
                <li class="closeOther"><i class="fa fa-ban"></i> 关闭其他页面</li>
                <li class="closeAll"><i class="fa fa-remove "></i> 关闭全部页面</li>
            </ul>
        </div>
        <ul class="layui-tab-title">
        </ul>
        <div class="layui-tab-content">
        </div>
    </div>
</section>

<form class="form-horizontal" id="amendPasWin" style="display: none;">
    <iframe src="about:blank"
            style="border: currentColor; border-image: none; left: 0px; width: 100%; height: 100%; position: absolute; z-index: -1;"></iframe>
    <div class="form-group">
        <label for="pasOne" class="col-sm-4 control-label">新密码</label>
        <div class="col-sm-8">
            <input type="password" class="form-control" id="pasOne" placeholder="请输入密码" autocomplete="off"/>
        </div>
    </div>
    <div class="form-group">
        <label for="pasTwo" class="col-sm-4 control-label">确认密码</label>
        <div class="col-sm-8">
            <input type="password" class="form-control" id="pasTwo" placeholder="请再次输入密码" autocomplete="off"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label"></label>
        <div class="col-sm-8">
            <span class="text-danger error-text"></span>
        </div>
    </div>
</form>

<%--loading--%>
<div class="preloader" state="true" style="display: none">
    <svg class="circular" viewBox="25 25 50 50">
        <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10"/>
    </svg>
</div>
<script>var ctx = '${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/waves/waves.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/index/jquery.slimscroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/popup.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/index/index.js?v=${v}"></script>
<script>

    $(function () {
        by_index.init();
        by_index.leftMenu();
        by_index.minMenu();
        by_index.setPassword();
        by_index.loading();
        window.globalData = {};
        window.globalData.userType = "${level}";
        window.globalData.constructionId = "${constructionId}";
        window.globalData.constructionName = "${constructionName}";
        window.globalData.areaId = "${areaId}";
        window.globalData.areaName = "${areaName}";
    })
</script>
</body>
</html>