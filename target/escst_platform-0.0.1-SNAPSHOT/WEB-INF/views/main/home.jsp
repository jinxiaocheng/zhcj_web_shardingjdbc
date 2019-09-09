<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>首页</title>
    </head>

    <body>
    	系统首页
        <p>123</p>
        <p>456</p>
        <p>789</p>

        <script src="${ctx}/resources/static/js/lib/jquery-2.2.3.min.js"></script>
        <script src="${ctx}/resources/static/js/lib/jquery.nicescroll.js"></script>
        <script>
            $(function(){
                $("html").niceScroll({styler:"fb",cursorcolor:"#000", cursorwidth: '5', cursorborderradius: '5px', background: '', spacebarenabled:false, cursorborder: '0',  zindex: '1000'});
            })
        </script>
    </body>
</html>