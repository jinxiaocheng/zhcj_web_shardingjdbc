<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>可视化配置-查看</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/layui/css/layui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/view.css?v=${v}"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
</head>
<body class="biyue-bg">
<div class="biyue-bg">
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>区域：</dt>
                <dd>
                    <p class="areaName">--</p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>工地：</dt>
                <dd>
                    <p class="constructionName">--</p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>ip地址：</dt>
                <dd>
                    <p class="ip">--</p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>web端口：</dt>
                <dd>
                    <p class="webPort">--</p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>app端口：</dt>
                <dd>
                    <p class="appPort">--</p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>用户名：</dt>
                <dd>
                    <p class="userName">--</p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>密码：</dt>
                <dd>
                    <p class="userPassword">--</p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block" style="border: none">
        <table class="layui-table channel" style="margin: 0">
            <colgroup>
                <col>
                <col>
                <col width="100">
                <col width="140">
                <col width="100">
            </colgroup>
            <thead>
            <tr>
                <th>设备名称</th>
                <th>通道名称</th>
                <th>通道号</th>
                <th>云台控制</th>
                <th>在线情况</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
</div>
<div class="biyue_footer">
    <div class="control-btn">
        <button class="layui-btn layui-btn-primary" type="button" id="com-close">关闭</button>
    </div>
</div>

<script>
    var ctx = "${ctx}", userId = "${userId}";
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/jquery.form.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lifting/visualView.js?v=${v}"></script>
<script>
    $(function () {
        biyue.scroll();
        biyue.popDefault();
        moduleView.getData();
    });
</script>
</body>
</html>
