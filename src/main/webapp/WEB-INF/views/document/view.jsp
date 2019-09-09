<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>查看详情</title>
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
                <dt>区域:</dt>
                <dd>
                    <p>${vo.areaName}</p>
                </dd>
            </dl>
        </div>
    </div>

    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>工地:</dt>
                <dd>
                    <p>${vo.constructionName}</p>
                </dd>
            </dl>
        </div>
    </div>

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

    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item ">
                    <dt>附件信息：</dt>
                    <dd id="documentInfo">
                        <c:if test="${vo.type eq '.png' || vo.type eq '.jpg' || vo.type eq '.jpeg' || vo.type eq '.bmp' || vo.type eq '.gif' }">
                            <dd class="" name = "">
                                <p>${vo.name} <a href="javascript:void(0)" class="v_images" fileUrl="${vo.picList}">预览</a></p>
                            </dd>
                        </c:if>
                        <c:if test="${vo.type ne '.png' && vo.type ne '.jpg' && vo.type ne '.jpeg' && vo.type ne '.bmp' && vo.type ne '.gif' }">
                            <dd class="" name = "">
                                <p>${vo.name} <a style="margin:0 10px" href="${vo.picList}" >下载</a><a href="javascript:void(0)" class="openPdf" fileUrl="${vo.previewPic}">预览</a></p>
                            </dd>
                        </c:if>
                    </dd>

            </dl>

        </div>
    </div>
</div>
<div style="height: 40px"></div>
<div class="image_big"></div>

<div class="footer_btn"><a class="btn btn_submit btn-show-blue delClose">关闭</a></div>
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
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}" ></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script>
    $(function () {
        var image_setTimeout;

        openDocumentPage()  // 打开pdf文档

        //点击关闭
        $(".delClose").on("click", function () {
            layer_close();
        });

        /*关闭弹出框口*/
        function layer_close() {
            var url = ctx + "/document/queryByConstructionId";
            parent.jQuery("#gridTable").jqGrid('setGridParam', {url: url}).trigger("reloadGrid");
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        };


    })

    // 打开pdf文档
    function openDocumentPage() {
        if($(".openPdf").length>0){
            $(".openPdf").on("click",function(){
                var url=$(this).attr("fileUrl");
                var title='文件浏览';
                parent.layer_showOdd(title, url, {w:'80%',h:'90%'});
            //    parent.showPdf(url);
            })
        }
        if($(".v_images").length>0){
            $(".v_images").on("click",function () {
                var filePath=[];
                filePath[0]={src:$(".v_images").eq(0).attr("fileUrl")}
                parent.parent.photos(filePath,0);
            });
        }
    }
</script>
</body>
</html>




