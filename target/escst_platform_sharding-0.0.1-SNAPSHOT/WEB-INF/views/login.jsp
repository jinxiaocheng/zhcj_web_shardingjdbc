<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>登录页面</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge chrome=1"/>
    <meta name="renderer" content="webkit">
    <link rel="icon" href="${ctx}/resources/static/images/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/waves/waves.min.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/css/login/loginNew.css">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
        if(window != top) {
            top.location.href = location.href;
        }
    </script>
</head>
<body>
<div class="content">
    <!-- 二维码 -->
    <div class="ewm">
        <div class="android">
            <img src="${ctx}/resources/static/images/login/android.png">
            <p>安卓APP下载</p>
        </div>
        <div class="ios">
            <img src="${ctx}/resources/static/images/login/ios.png">
            <p>苹果APP下载</p>
        </div>
    </div>
    <!-- 主体部分 -->
    <div class="login-t">
        <img class="loginImg" src="${ctx}/resources/static/images/login/logo.png">
        <div class="title">保障工地一份安全，还给工地一片蓝天 </div>
        <div class="form_c">
            <form method="post" action="javascript:void(0);" >
                <div class="inputText">
                    <i class="fa fa-user-o"></i>
                    <input type="text" placeholder="用户名" id="uname" name="username" autocomplete="off" value="<shiro:principal/>" class="uname"/>
                </div>
                <div class="inputText">
                    <i class="fa fa-unlock"></i>
                    <input type="password" name="password" id="pwd" placeholder="密码" class="pwd" autocomplete="off"  />
                </div>
                <div class="ckbox">
                    <input type="checkbox" name="rememberMe" value="true" checked id="my_check">
                    <label for="my_check" class="fa">记住账号</label>
                </div>
                <div class="prompt"></div>
                <button class="btn" type="submit" id="btn">登&nbsp;&nbsp;录</button>
            </form>
        </div>
    </div>
    <!-- 登录表单 -->
</div>
<script>ctx="${ctx}";</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/waves/waves.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/login/login.js"></script>
<script>
    $(function(){
        KK_cookie("my_check","uname","pwd","btn","un","pw");
        //按钮点击特效
        Waves.init({
            duration: 300,
            delay: 100
        });
        Waves.attach("#btn", ['waves-button','waves-light']);
        $("#btn").click(my_submit);
    });
</script>
</body>
</html>
