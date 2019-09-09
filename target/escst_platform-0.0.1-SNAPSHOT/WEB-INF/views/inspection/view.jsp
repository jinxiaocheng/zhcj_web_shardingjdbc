<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>日常检查-查看详情</title>
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
        .detail-infos .info-item dd p {
            padding-left: 14px;
        }
        .inspectItems{
            font-size: 14px;
            letter-spacing: 2px;
            color: #787878;
        }
        .inspectItems:before{
            font: normal normal normal 14px/1 FontAwesome;
            content: "\f054";
            font-size: 14px;
            color: #ff8e12;
            margin-right: 5px;
        }
        .inspectResultsParent{
            letter-spacing: 2px;
            color: #787878;
            text-indent: 2.5em !important;
            background-size:16px;
        }
        .inspectResultsParent:before{
            font: normal normal normal 14px/1 FontAwesome;
            content: "\f054";
            font-size: 14px;
            color: #ff8e12;
            margin-right: 5px;
        }
        .inspectResults{
            color: #000;
            letter-spacing: 2px;
            text-indent: 4.5em !important;
            background-size:14px;
        }
        .inspectResults:before{
            font: normal normal normal 14px/1 FontAwesome;
            content: "\f111";
            font-size: 12px;
            color: #ffb15c;
            margin-right: 5px
        }
    </style>
</head>

<body class="body_bottom">
<div class="main-info-table">
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>检查日期:</dt>
                <dd>
                    <p class="v_date">${vo.businessDate}</p>
                </dd>
            </dl>

            <dl class="info-item">
                <dt>单号:</dt>
                <dd>
                    <p class="v_date">${vo.orderNo}</p>
                </dd>
            </dl>


        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>所属公司:</dt>
                <dd>
                    <p class="v_company">${vo.projectCompanyName}</p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>班组:</dt>
                <dd>
                    <p class="v_group">${vo.teamName}</p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>责任人:</dt>
                <dd>
                    <p class="v_person">${vo.contactName}</p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>责任人电话:</dt>
                <dd>
                    <p class="v_tel">${vo.contactMobile}</p>
                </dd>
            </dl>
        </div>
    </div>


    <c:if test="${type!=2}">
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
    </c:if>

    <c:if test="${type == 1}">
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>检查项目:</dt>
                    <dd>
                        <p class="v_item tableNewLine">${vo.items}</p>
                    </dd>
                </dl>
            </div>
        </div>
    </c:if>

    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">

                    <c:if test="${type == 1}">
                        <dt>检查结果:</dt>
                        <dd><p class="v_result newline tableNewLine">${vo.results}</p></dd>
                    </c:if>
                    <c:if test="${type == 2 || type==3}">
                        <dt>检查项及结果:</dt>
                        <dd>
                            <div style="margin-top: 30px" id="treeR" class="ztree ztree_a">

                            </div>
                        </dd>
                    </c:if>
            </dl>
        </div>
    </div>

    <c:if test="${type == 2}">
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>检查项及结果:</dt>
                    <dd>
                        <p class="v_item tableNewLine">${vo.otherItems}</p>
                    </dd>
                </dl>
            </div>
        </div>
    </c:if>

    <c:if test="${type == 2 || type==3}">
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>检查结果:</dt>
                    <dd><p class="v_result newline tableNewLine">${vo.results}</p></dd>
                </dl>
            </div>
        </div>
    </c:if>

    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>处理意见:</dt>
                <dd>
                    <p>
                        <c:choose>
                            <c:when test="${vo.processingOption eq 1}">通过</c:when>
                            <c:when test="${vo.processingOption eq 2}">警告</c:when>
                            <c:when test="${vo.processingOption eq 3}">整改</c:when>
                        </c:choose>
                    </p>
                </dd>
            </dl>
            <c:if test="${vo.processingOption eq 3}">
                <dl class="info-item">
                    <dt>限期整改时间：</dt>
                    <dd>
                        <p class="corrective_completion_date">${vo.correctiveCompletionDate}</p>
                    </dd>
                </dl>
            </c:if>
        </div>
    </div>

    <c:if test="${vo.processingOption eq 3}">
        <div class="detail-block">
            <div class="detail-infos">

                <dl class="info-item">
                    <dt>整改要求：</dt>
                    <dd>
                        <p class="corrective_request tableNewLine">${vo.correctiveRequest}</p>
                    </dd>
                </dl>
            </div>
        </div>
    </c:if>

    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>告知人:</dt>
				<dd>
                    <p>
                        <c:forEach var="notifyEntity" items="${vo.notifyEntityList}">
                            ${notifyEntity.name}&nbsp;
                        </c:forEach>
                    </p>

				</dd>
            </dl>
        </div>
    </div>

    <c:if test="${type == 2||type==3}">
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>备注:</dt>
                    <dd>
                        <p class="tableNewLine">${vo.remark}</p>
                    </dd>
                </dl>
            </div>
        </div>
    </c:if>

    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item v_image_list">
                <dt>附件信息：</dt>
                <c:if test="${vo.isAttach == 0}">无照片信息</c:if>
                <c:if test="${vo.isAttach == 1}">
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
</div>
<div style="height: 40px"></div>
<div class="image_big"></div>

<div class="footer_btn"><a class="btn btn_submit btn-show-blue delClose">关闭</a></div>
<script>
    var ctx = "${ctx}",type="${type}";
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
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/inspection/view.js?v=${v}"></script>
<script>
    $(function () {
        var image_setTimeout;
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

        //点击关闭
        $(".delClose").on("click", function () {
            layer_close();
        });

        /*关闭弹出框口*/
        function layer_close() {
            var url = ctx + "/inspection/queryByConstructionId";
            parent.jQuery("#gridTable").jqGrid('setGridParam', {url: url}).trigger("reloadGrid");
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }

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
    })
</script>
</body>
</html>




