<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>安全培训-查看详情</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet"/>
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" rel="stylesheet"/>
    <link type="text/css" href="${ctx}/resources/static/css/tableform.css?v=${v}" rel="stylesheet"/>
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

        .person_null {
            text-align: center;
            margin: 0;
            height: 100px;
            line-height: 100px;
            color: #a2a2a2;
            letter-spacing: 2px;
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
                <dt>主题:</dt>
                <dd>
                    <p class="v_theme">${vo.theme}</p>
                </dd>
            </dl>

        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>开始时间:</dt>
                <dd>
                    <p class="v_start_date">${vo.startDate}</p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>结束时间:</dt>
                <dd>
                    <p class="v_end_date">${vo.endDate}</p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <c:if test="${type eq 1}">
                <dl class="info-item">
                    <dt>讲师:</dt>
                    <dd>
                        <p class="v_contact_person">${vo.contactPerson}</p>
                    </dd>
                </dl>
            </c:if>
            <dl class="info-item">
                <c:if test="${type eq 1}">
                    <dt>培训地点:</dt>
                </c:if>
                <c:if test="${type eq 2}">
                    <dt>演习地点:</dt>
                </c:if>
                <dd>
                    <p class="v_place">${vo.place}</p>
                </dd>
            </dl>
        </div>
    </div>

    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item v_person_list">
                <dt>参与人员：</dt>
                <p class="v_persons">
                    <c:if test="${empty vo.personList}">无人员信息</c:if>
                    <c:if test="${not empty vo.personList}">
                        <c:forEach items="${vo.personList}" var="person" varStatus="var">
                            <c:if test="${var.last}">
                                ${person.get("name")}
                            </c:if>
                            <c:if test="${not var.last}">
                                ${person.get("name")},
                            </c:if>
                        </c:forEach>
                    </c:if>
                </p>
            </dl>
        </div>
    </div>

    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item v_image_list">
                <c:if test="${vo.isAttach == 0}">无照片信息</c:if>
                <c:if test="${vo.isAttach == 1}">
                    <dt>附件信息(点击可放大图片)：</dt>
                    <dd>
                    <c:forEach var="filePathVo" items="${vo.picList}">
                        <dd class="v_images" name="${filePathVo.filePath}">
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
                <dt>备注:</dt>
                <dd>
                    <p class="v_remark">${vo.remark}</p>
                </dd>
            </dl>
        </div>
    </div>
</div>
<div style="height: 40px"></div>
<div class="image_big"></div>

<div class="footer_btn">
    <a class="btn btn_submit btn-default" id="com-close">关闭</a>
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
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script>
    $(function () {

        //点击放大图片
        $(".v_images").click(function () {
            var len = $(".v_images").length, filePath = [];
            var index = $(this).index() - 2;
            for (var i = 0; i < len; i++) {
                filePath[i] = {src: $(".v_images").eq(i).attr("name")}
            }
            parent.parent.photos(filePath, index);

        });

        biyue.popDefault();

        /*关闭弹出框口*/
        function layer_close() {
            var url = ctx + "/safeStudy/queryByConstructionId";
            parent.jQuery("#gridTable").jqGrid('setGridParam', {url: url}).trigger("reloadGrid");
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        };

    });
</script>
</body>
</html>




