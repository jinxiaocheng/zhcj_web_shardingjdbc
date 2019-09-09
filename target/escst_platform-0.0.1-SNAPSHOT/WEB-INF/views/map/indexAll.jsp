<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html style="height: 100%">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=10">
    <title>地图</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <script>
        //还原区域id与工地id
        ;(function () {
            if(top.constructionId){
                top.constructionId="";
            }
            if(top.areaId){
                top.areaId="";
            }
        })()
    </script>
    <link rel="icon" href="${ctx}/resources/static/images/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/css/map/map.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/css/map/companyMap.css?v=${v}" />
    <!--[if lt IE 9
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
</head>
<body>
<div id="leftBox">
    <div id="progressOfWorks" class="clickShowP">
        <h2>各项目工程进度 <i class="fa fa-caret-down clickShow close" ></i></h2>
        <ul id="barALLBox" >
           <%-- <li>
                <h3 class="barTitle"><i></i>玫瑰里项目</h3>
                <p class="remainder">剩余9天</p>
                <span class="barNum">62%</span>
                <u><i style="width: 10%"></i></u>
                <p class="barTime">
                    <span class="barStartTime">2017-12-24</span>
                    <span class="barEndTime">2018-12-24</span>
                </p>
            </li>--%>
        </ul>
    </div>
    <div id="trendsOfWorks" class="clickShowP">
        <h2>工作动态 <i class="fa fa-caret-down clickShow close" ></i></h2>
        <div class="trends">
            <%--<div class="trendsBox">
                <p>安全员周伟进行了安全维护。</p>1
                <span>2018-02-06 12:56</span>
            </div>--%>
        </div>
    </div>
</div>
<div id="mapBox">
    <div id="map"></div>
</div>
</body>
<script> var ctx="${ctx}"</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/js/index/jquery.slimscroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=3bb801b79151585f9534ed9ed7ff280d"></script>
<script type="text/javascript" src="http://api.map.baidu.com/getscript?v=2.0&ak=3bb801b79151585f9534ed9ed7ff280d&services=&t=20180201111639"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/map/map.js?v=${v}"></script>
<script>
    $("#barALLBox").on('click','li',function () {
        var name=$(this).find('h3').text();
        $(".BMap_noprint.anchorTL input").val(name);
        $(".BMap_noprint.anchorTL i").click();
    })
    $(function(){
        leftBoxType(2);
        ininMap();
        searchvalue();
        progressAll();
        trendsOfWorksAll(1,0);
        clickShow();
        setMapHeight();  // 如果是ie8的话重新设置高度
    })

</script>
</html>