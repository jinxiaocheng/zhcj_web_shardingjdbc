<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>任务预约详情</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet"/>
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" rel="stylesheet"/>
    <link type="text/css" href="${ctx}/resources/static/css/tableform.css?v=${v}" rel="stylesheet"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/icons/icono.min.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/fileInput/fileinput.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/ieControl.css?v=${v}">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
    <style>
        body{
            padding: 0 24px;
            box-sizing: border-box;
        }
        .body_bottom {
            margin-bottom: 0;
        }
        .view_title {
            border-bottom: 1px solid #dfdfdf;
            font-size: 16px;
        }
        .view_title p {
            border-left: 3px solid #36a1da;
            margin: 5px 0;
            text-indent: 0.5em;
            line-height: 24px;
        }

        .info-item p {
            text-indent: 0.5em;
        }

        .detail-infos .info-item dd {
            margin-left: 0;
        }

        .detail-infos .info-item dd.v_images {
            width: 100px;
            height: 100px;
            border: 1px solid #ddd;
            float: left;
            box-sizing: border-box;
            margin: 7.5px;
            cursor: pointer;
        }

        .detail-infos .info-item dd.v_images img {
            width: 100px;
            height: 100px;
            margin: 0;
        }

        .image_null {
            text-align: center;
            margin: 0;
            height: 100px;
            line-height: 100px;
            color: #a2a2a2;
            letter-spacing: 2px;
        }

        .image_big {
            position: fixed;
            left: 50%;
            top: 40%;
            -webkit-transform: translate(-50%, -50%);
            -moz-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            -o-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
            display: none;
        }
        [class*="icono-"]{
            color:#07fb1b;
            -webkit-transform: scale(0.6) rotate(-45deg);
            -moz-transform: scale(0.6) rotate(-45deg);
            -ms-transform: scale(0.6) rotate(-45deg);
            -o-transform: scale(0.6)rotate(-45deg);
            transform: scale(0.6)rotate(-45deg);
        }
        .view_title1{
            padding: 0 13px;
            margin-top: -8px;
        }
        .detail-infos .info-item dt{
            font-weight: normal;
            font-size: 14px;
        }
    </style>
</head>

<body class="body_bottom">
<div class="main-info-table">
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>任务紧急：</dt>
                <dd>
                    <p class="isUrgentTask"></p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>预约时间：</dt>
                <dd>
                    <p class="date"></p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>检查部位：</dt>
                <dd>
                    <p class="structure"></p>
                </dd>
            </dl>

        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>劳务公司：</dt>
                <dd>
                    <p class="company"></p>
                </dd>
            </dl>

        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>处理人：</dt>
                <dd>
                    <p class="handle"></p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>预约人：</dt>
                <dd>
                    <p class="appointment"></p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>任务说明：</dt>
                <dd>
                    <p class="remark"></p>
                </dd>
            </dl>
        </div>
    </div>

    <h2 class="view_title"> <p>处理记录</p></h2>
    <c:forEach var="vo" items="${list}" varStatus="status">

        <c:choose>
            <c:when test="${vo.taskStatus == 1}">
                <div class="view_title1"><span class="icono-checkCircle" aria-hidden="true"></span>待处理</div>
            </c:when>
            <c:when test="${vo.taskStatus == 2}">
                <div class="view_title1"><span class="icono-checkCircle" aria-hidden="true"></span>待审核</div>
            </c:when>
            <c:when test="${vo.taskStatus == 3}">
                <div class="view_title1"><span class="icono-checkCircle" aria-hidden="true"></span>处理中</div>
            </c:when>
            <c:when test="${vo.taskStatus == 4}">
                <div class="view_title1"><span class="icono-checkCircle" aria-hidden="true"></span>审核不通过</div>
            </c:when>
            <c:when test="${vo.taskStatus == 5}">
                <div class="view_title1"><span class="icono-checkCircle" aria-hidden="true"></span>已完成</div>
            </c:when>
        </c:choose>

        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>备注信息：</dt>
                    <dd><p>${vo.remark}<p></dd>
                </dl>
            </div>
        </div>

        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>预约人：</dt>
                    <dd><p>${vo.appointmentName}</p></dd>
                </dl>

                <dl class="info-item">
                    <dt>预约人时间：</dt>
                    <dd><p>${vo.appointmentDate}</p></dd>
                </dl>

            </div>
        </div>

    </c:forEach>
</div>
<div style="height: 60px;width: 100%"></div>

</div>

<script>
    var ctx = "${ctx}";

</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/fileInput/fileinput.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/fileInput/fileinput_locale_de.js?v=${v}"></script>
<script>
    var parentData = window.parent.check_data();
    $(function () {
        //任务状态
        $(".isUrgentTask").html(parentData.isUrgentTask);
        $(".date").html(parentData.appointmentDate);
        //检查部位
        $(".structure").html(parentData.projectStructureName);
        //预约人
        $(".appointment").html(parentData.appointmentName);
        //分包公司
        $(".company").html(parentData.projectCompanyName);
        //处理人
        $(".handle").html(parentData.handlePerson);
        //任务状态
        if(parentData.isUrgentTask==1){
            $(".isUrgentTask").html('紧急');
        }else if(parentData.isUrgentTask==0){
            $(".isUrgentTask").html('不紧急');
        }
        //备注信息
        $(".remark").html(parentData.remark);

        //点击关闭
        $(".delClose").on("click", function () {
            layer_close();
        });

    });
    /*关闭弹出框口*/
    function layer_close() {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);

    };

</script>
</body>
</html>




