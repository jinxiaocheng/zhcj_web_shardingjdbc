<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>流水段维护-查看</title>
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
                <dt>流水段名称：</dt>
                <dd>
                    <p class="name">--</p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>测点：</dt>
                <dd>
                    <p class="cds">--</p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>测点部署图：</dt>
                <dd class="cdImg">
                    <p>暂无测点部署图</p>
                </dd>
            </dl>
        </div>
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
<script type="text/javascript" src="${ctx}/resources/static/js/gzm/lsdView.js?v=${v}"></script>
<script>
    $(function () {
        biyue.scroll();
        biyue.popDefault();
        moduleView.getData();

        $(".cdImg").on('click', 'img', function () {
            var src = $(this).attr("data-value");
            top.photos([{
                "src": src
            }], 0)
        })
    });
</script>
</body>
</html>
