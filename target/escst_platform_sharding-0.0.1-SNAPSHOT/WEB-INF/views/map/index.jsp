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
    <link rel="icon" href="${ctx}/resources/static/images/logo.ico?v=${v}" type="image/x-icon">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/css/map/map.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/css/map/constructionMap.css?v=${v}" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}
"></script>
    <![endif]-->
</head>
<body>
<div id="leftBox">
    <div id="progressOfWorks">
        <h2>工程进度</h2>
        <div class="progressBar">
            <p>0%</p>
            <p class="bar"><i style="width: 0%"></i></p>
        </div>
        <div class="progressTime">
            <p class="startTime">
                <span>-<br>- - -</span>
                <label>开工日期</label>
            </p>
            <p class="endTime">
                <span>-<br>- - -</span>
                <label>竣工日期</label>
            </p>
            <p class="surplusTime">
                <span><%--3 0 3--%></span>
                <label>剩余时间(天)</label>
            </p>
        </div>
    </div>
    <div id="infoOfWorks">
        <h2>工程信息</h2>
        <div class="m1 me"><i></i><label>工程名称 :</label><span></span></div>
        <div class="m2 me"><i></i><label>建设单位 :</label><span></span></div>
        <div class="m3 me"><i></i><label>施工单位 :</label><span></span></div>
        <div class="m4 me"><i></i><label>监理单位 :</label><span></span></div>
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
<div id="conImgBox">
    <h2>工地平面图</h2>
    <div id="conImg">
        <div id="imgBox">

<%--
            <a  class="" style="position:absolute;left:200px;top: 200px;display: block;background: red;width: 100px;height: 100px;"></a>
--%>
        </div>
    </div>
    <p><c:if test="${currentUserInfo.userName ne 'test'}">湖北恒信国通版权所有</c:if></p>
</div>
<div id="rightBox">
    <div class="allInfo">
        <div class="time">
            <p>00:00:00</p>
        </div>
        <div class="weather">
            <span class="temperature"> <label  class="w1 w"></label>-- -℃</span>
            <span class="week">星期</span>
        </div>
        <div class="personNum">
            <p class="nowNum">
                <span>--</span>
                <label>在场人数</label>
            </p>
            <p class="todayNum">
                <span>--</span>
                <label>今日出勤</label>
            </p>
        </div>
    </div>
    <div id="modelBox">
        <ul class="modelBox">
            <li class="modelList">
                <i class="m l1" title="摄像头"></i>
                <div>
                    <p class="m1">
                        <span>--</span>
                        <label>在线</label>
                    </p>
                    <p class="m2">
                        <span>--</span>
                        <label>离线</label>
                    </p>
                </div>
            </li>
            <li class="modelList">
                <i class="m l2" title="塔吊"></i>
                <div>
                    <p class="m1">
                        <span>--</span>
                        <label>在线</label>
                    </p>
                    <p class="m2">
                        <span>--</span>
                        <label>离线</label>
                    </p>
                </div>
            </li>
            <li class="modelList">
                <i class="m l3" title="升降机"></i>
                <div>
                    <p class="m1">
                        <span>--</span>
                        <label>在线</label>
                    </p>
                    <p class="m2">
                        <span>--</span>
                        <label>离线</label>
                    </p>
                </div>
            </li>
            <li class="modelList">
                <i class="m l6" title="环境"></i>
                <div>
                    <p class="m1">
                        <span>--</span>
                        <label>在线</label>
                    </p>
                    <p class="m2">
                        <span>--</span>
                        <label>离线</label>
                    </p>
                </div>
            </li>
            <li class="modelList">
                <i class="m l4" title="质量整改"></i>
                <div>
                    <p class="m1">
                        <span>--</span>
                        <label>待整改</label>
                    </p>
                    <p class="m2">
                        <span>--</span>
                        <label>已检查</label>
                    </p>
                </div>
            </li>
            <li class="modelList">
                <i class="m l5" title="安全整改"></i>
                <div>
                    <p class="m1">
                        <span>--</span>
                        <label>待整改</label>
                    </p>
                    <p class="m2">
                        <span>--</span>
                        <label>已检查</label>
                    </p>
                </div>
            </li>
        </ul>
    </div>
</div>

<%--摄像头人数显示框--%>
<div class="cameraBox">
    <h5>---</h5>
    <p>当前该区域总人数: <span>-</span>人</p>
    <i class="fa fa-chevron-right"></i>
</div>
<%--工地人员轨迹--%>
<div class="personTrackBox">
    <div class="personList">
        <h1>工地人员 <span>-人</span></h1>
        <ul>
            <%--<li>
                <u></u>
                <h5>张三</h5>
                <p>2018-11-12 12:11:44</p>
                <i class="fa fa-chevron-right"></i>
            </li>--%>
        </ul>
    </div>
    <div class="personTrackImg">
        <div class="imgBox">
            <img src="" alt=""><%--${ctx}/resources/static/images/map/testImg.png--%>
        </div>
    </div>
</div>


</body>
<script> var ctx="${ctx}",constructionId="${currentUserInfo.constructionId}";</script>
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
    $(function(){
        getImg();
        info();
        setDate();
        personTrackBox();
        setInterval(setDate,1000);
        progressOfWorks();
        trendsOfWorks(1,0);
        clickShow();
        cameraInfo();
        setMapHeight();  // 如果是ie8的话重新设置高度
    })

</script>
</html>