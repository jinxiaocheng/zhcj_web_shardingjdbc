<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>塔吊监测</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/layui/css/layui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/equipment/towerCraneMonitor.css?v=${v}"/>
    <!--[if lt IE 9]>
        <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
        <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
        <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
</head>
<body >
<div class="biyue_search layui-form area">
    <div class="layui-inline area">
        <label class="layui-form-label">区域</label>
        <div class="layui-input-inline">
            <select name="area" lay-search lay-filter="area">
                <option value="">选择区域</option>
            </select>
        </div>
    </div>
    <div class="layui-inline site">
        <label class="layui-form-label">工地</label>
        <div class="layui-input-inline">
            <select name="site" lay-search lay-filter="site">
                <option value="">选择工地</option>
            </select>
        </div>
    </div>
    <button class="layui-btn layui-btn-primary search area" id="search"><i class=" layui-icon"></i> 搜索</button>
</div>
<div class="bodyBox">
    <div class="noData" style="display: none;position: absolute;left: 50%;top: 50%;width: 300px;height:300px;margin-left: -100px;margin-top: -150px;">
        <img src="${ctx}/resources/static/images/noData.png" style="width: 100%" alt="">
        <p style="margin-left: 80px;color: #b6b6b6;letter-spacing: 2px;margin-top: 6px;font-size: 14px;">暂无数据</p>
    </div>
 <%--   <h1 class="siteTitle">演示工地</h1>
    <section class="sectionBox">
        <div class="towerBox">
            <h1 class="title titleBox"><i></i><span>3号塔吊</span></h1>
            <div class="tower">
                <canvas id="myCanvas" width="1000px" height="500px">
                    你的浏览器不支持canvas,请使用高版本浏览器;
                </canvas>
            </div>
            <div class="towerInfoBox">
                <div class="timeBox">
                    <h2 class="titleBox"><i></i><span>数据发送时间</span></h2>
                    <p>2018-04-04 09:30:24</p>
                </div>
                <div class="towerInfo">
                    <h2 class="titleBox"><i></i><span>塔吊信息</span></h2>
                    <ul class="info">
                        <li><label>风速:</label><span><i>-</i>级</span></li>
                        <li><label>力矩百分比:</label><span><i>-</i>%</span></li>
                        <li><label>安全吊重:</label><span><i>-</i>吨 (t)</span></li>
                        <li><label>前臂长:</label><span><i>-</i>米 (m)</span></li>
                        <li><label>塔机高:</label><span><i>-</i>米 (m)</span></li>
                        <li><label>载重:</label><span><i>-</i>吨 (t)</span></li>
                        <li><label>幅度:</label><span><i>-</i>米 (m)</span></li>
                        <li><label>高度:</label><span><i>-</i>米 (m)</span></li>
                        <li><label>回旋角度:</label><span><i>-</i>度 (°)</span></li>
                        <li style="border: none"><label>倾斜角度:</label><span><i>-</i>度 (°)</span></li>

                    </ul>
                </div>
            </div>
        </div>
    </section>--%>
    <div class="clearfix"></div>
</div>

<script>
    var ctx = "${ctx}";
    var userId = "${userId}";  //当前用户ID
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/equipment/towerCraneMonitor.js?v=${v}"></script>
<script>
    $(function(){
        var area = biyue.area();
        area.area({
            type: "table",
            complete: function () {
                //得到塔吊数据
                var tower=new towerData({
                    url:'/towerCrane/getTowerCraneMonitor'
                });
                tower.data();
                $("#search").click(function () {
                    tower.data();
                })
            }
        });
        if(biyueDataA.userType){
            $(".bodyBox").css("padding-top","20px");
        }

    })
</script>
</body>
</html>