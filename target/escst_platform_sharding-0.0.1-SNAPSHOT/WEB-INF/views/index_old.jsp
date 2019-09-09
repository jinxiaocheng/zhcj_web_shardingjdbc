<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>首页</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="icon" href="${ctx}/resources/static/images/logo.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/css/admin.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/css/style.css?v=${v}" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
    <script>
      var ctx = "${ctx}";
      var level="${level}";
    </script>
</head>
<body>
<header class="Hui-header">
    <a href="#" class="logo">
        <span class="logo-lg"><img src="${ctx}/resources/static/images/${logoName}" /></span>
       <%-- <p class="logo-title">全国智慧工地大数据云服务平台</p>--%>
        <!--<span class="line-gradient-lr"></span>-->
    </a>
    <nav class="navbar navbar-static-top">
        <!--头部导航-->
        <div class="li_nav">
            <ul class="main_ul" id="menu_head">              
            </ul>
        </div>
        <!--头部导航-->
        <!--登陆退出-->
        <div class="navbar-custom-menu">
            <span class="line-gradient-lf"></span>
            <ul class="nav-down">
                <li class="dropdown user-menu dropdown-down">
                    <a href="#" data-toggle="dropdown">
                        <img src="${ctx}/resources/static/images/user2-160x160.jpg" class="user-image" />
                        <span class="">${name}</span>
                        <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu" style="right: 0;overflow: hidden;">
                        <li class="user-info" hidden>
                            <a class="main_menu" href="javascript:void(0);" link="" name=""><i class="fa fa-user-circle-o"></i>个人信息</a>
                        </li>
                        <li class="user-Settings" hidden>
                            <a class="main_menu" href="javascript:void(0);" link="" name=""><i class="fa fa-gear"></i>系统设置</a>
                        </li>
                        <li class="set-password">
                            <a href="javascript:void(0)"><i class="fa fa-lock"></i><span ></span>修改密码</a>
                            <iframe src="about:blank" style="border: currentColor; border-image: none; left: 0px; width: 100%; height: 100%; position: absolute; z-index: -1;"></iframe>

                        </li>
                        <li class="user-layout">
                            <a href="logout"><i class="fa fa-power-off"></i><span class="graph-layout"></span>退出</a>
                            <iframe src="about:blank" style="border: currentColor; border-image: none; left: 0px; width: 100%; height: 100%; position: absolute; z-index: -1;"></iframe>

                        </li>
                        <iframe src="about:blank" style="border: currentColor; border-image: none; left: 0px; width: 100%; height: 100%; position: absolute; z-index: -1;"></iframe>
                    </ul>
                </li>
            </ul>
            <div class="message">
                <i class="fa fa-bell-o"></i>
                <span></span>
            </div>
            <div class="mes-content">
                <div class="modal-content">
                    <div class="panel-heading">
                        <span class="text-center center-block ">通知</span>
                        <button type="button" class="close close-in-panel-heading">×</button>
                    </div>
                    <div class="panel-content">
                        <p class="panel-null text-center center-block">暂无最新通知</p>
                    </div>
                    <div class="panel-footer">
                        <a href="#" class=" center-block text-center" data-toggle="modal" data-target="#NotificationModal">查看更多</a>
                    </div>
                </div>
            </div>
        </div>
        <!--登陆退出-->
    </nav>
</header>

<div id="middle">

    <div id="left">
        <!--侧边栏-->
        <aside class="Hui-aside">
            <section class="sidebar">
                <ul class="sidebar-menu">
                </ul>
            </section>
        </aside>
        <!--侧边栏-->
        <!--点击收缩-->
        <div class="dislpayArrow"><a class="pngfix" href="javascript:void(0);"></a></div>
        <!--点击收缩-->
    </div>

    <section class="Hui-article-box Hui-default">
        <div class="show_iframe">
            <iframe id="mainframe" name="mainframe" src="" allowfullscreen="true" scrolling="no" width="100%" height="100%" frameborder="0"></iframe>
        </div>
    </section>
</div>

<%--<footer class="footer" >
    <p>湖北恒信国通版权所有</p>
</footer>--%>
</body>


<form class="form-horizontal" id="amendPasWin" style="display: none;">
    <iframe src="about:blank" style="border: currentColor; border-image: none; left: 0px; width: 100%; height: 100%; position: absolute; z-index: -1;"></iframe>
    <div class="form-group">
        <label for="pasOne" class="col-sm-4 control-label">新密码</label>
        <div class="col-sm-8">
            <input type="password" class="form-control" id="pasOne" placeholder="请输入密码" autocomplete="off">
        </div>
    </div>
    <div class="form-group">
        <label for="pasTwo" class="col-sm-4 control-label">确认密码</label>
        <div class="col-sm-8">
            <input type="password" class="form-control" id="pasTwo"  placeholder="请再次输入密码" autocomplete="off">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label"></label>
        <div class="col-sm-8">
            <span class="text-danger error-text"></span>
        </div>
    </div>
</form>

<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/md5/md5.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/popup.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common.js?v=${v}"></script>
<script>
    window.globalData={};
    window.globalData.userType="${level}";
    window.globalData.constructionId="${constructionId}";
    window.globalData.constructionName="${constructionName}";
    window.globalData.areaId="${areaId}";
    window.globalData.areaName="${areaName}";

    //消息推送
    mainPage.default();

</script>
</html>