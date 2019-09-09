<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>事故报告-查看</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet"/>
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" rel="stylesheet"/>
    <link type="text/css" href="${ctx}/resources/static/css/tableform.css?v=${v}" rel="stylesheet"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/tree.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
    <style>
        .body_bottom {
            margin-bottom: 0;
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
    </style>
</head>

<body class="body_bottom">
<div class="main-info-table">
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>事故时间:</dt>
                <dd>
                    <p class="corrective_date">${vo.accidentDate}</p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>劳务分包公司:</dt>
                <dd>
                    <p class="project_company">${vo.projectCompanyName}</p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>班组:</dt>
                <dd>
                    <p class="team">${vo.teamName}</p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>责任人:</dt>
                <dd>
                    <p class="contact">${vo.contactName}</p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>责任人电话:</dt>
                <dd>
                    <p class="contact_mobile">${vo.contactMobile}</p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>检查部位:</dt>
                <dd>
                    <div style="margin-top: 30px" id="tree" class="ztree ztree_a">

                    </div>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>事故简述:</dt>
                <dd>
                    <p class="items newline" >${vo.resume}</p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>初步处理意见:</dt>
                <dd>
                    <p class="results newline">${vo.firstTreatment}</p>
                </dd>
            </dl>
        </div>
    </div>

    <c:if test="${type == 2}">
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>受伤人员:</dt>
                    <dd>
                        <p>
                            <c:if test="${empty vo.injuredPersonList}">无人员信息</c:if>
                            <c:if test="${not empty vo.injuredPersonList}">
                                <c:forEach var="personVo" items="${vo.injuredPersonList}" varStatus="var">
                                <c:if test="${var.last}">
                                    ${personVo.name}
                                </c:if>
                                <c:if test="${!var.last}">
                                    ${personVo.name},
                                </c:if>
                        </c:forEach>

                        </c:if>
                        </p>
                    </dd>
                </dl>
            </div>
        </div>

        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>死亡人员:</dt>
                    <dd>
                        <p>
                            <c:if test="${empty vo.injuredPersonList}">无人员信息</c:if>
                            <c:if test="${not empty vo.injuredPersonList}">
                        <c:forEach var="personVo" items="${vo.deathPersonList}" varStatus="var">
                            <c:if test="${var.last}">
                                ${personVo.name}
                            </c:if>
                            <c:if test="${!var.last}">
                                ${personVo.name},
                            </c:if>
                        </c:forEach>
                        </c:if>
                        </p>
                    </dd>
                </dl>
            </div>
        </div>
    </c:if>

    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <c:if test="${vo.isAttach == 0}">无照片信息</c:if>
                <c:if test="${vo.isAttach == 1}">
                    <dt>附件信息:</dt>
                    <dd>
                    <c:forEach var="filePathVo" items="${vo.picList}">
                        <dd class="v_images" name = "${filePathVo.filePath}">
                            <img src="${filePathVo.smallFilePath}" alt="">
                        </dd>
                    </c:forEach>
                    </dd>
                </c:if>
            </dl>
        </div>
    </div>

    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>事故等级:</dt>
                <dd>
                    <p class="level">
                    <c:choose>
                        <c:when test="${vo.level == 1}">
                            一般
                        </c:when>
                        <c:when test="${vo.level == 2}">
                            较大
                        </c:when>
                        <c:when test="${vo.level == 3}">
                            重大
                        </c:when>
                        <c:when test="${vo.level == 4}">
                            特别重大
                        </c:when>
                    </c:choose>
                    </p>
                </dd>
            </dl>
        </div>
    </div>

</div>
<div style="height: 40px"></div>

<div class="footer_btn">
    <a class="btn btn_submit btn-default" id="com-close">关闭</a>
</div>

<script>
    var ctx = "${ctx}";
    var type = "${type}";
</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}" ></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/inspection/view.js?v=${v}"></script>
<script>
    $(function () {
        biyue.popDefault();
        //点击放大图片
        $(".v_images").click(function () {
            var imgElement=$(this).parent().children('.v_images');
            var len=imgElement.length,filePath=[];
            var index=$(this).index()-2;
            for(var i=0 ;i<len;i++){
                filePath[i]={src:imgElement.eq(i).attr("name")}
            }
            parent.parent.photos(filePath,index);
        });

        biyue_view.getData();
        //滚动条美化
        $("html").niceScroll({
            styler:"fb",
            cursorcolor:"#cccccc",
            cursorwidth: '5',
            cursorborderradius: '5px',
            background: '',
            autohidemode: false,
            spacebarenabled:false,
            cursorborder: '0',
            zindex: '1000'
        });

    });

</script>
</body>
</html>




