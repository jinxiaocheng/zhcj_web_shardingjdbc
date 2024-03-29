<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>安全整改-查看详情</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet"/>
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" rel="stylesheet"/>
    <link type="text/css" href="${ctx}/resources/static/css/tableform.css?v=${v}" rel="stylesheet"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/icons/icono.min.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/fileInput/fileinput.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/file_input.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/tree.css?v=${v}">
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
        /*修改style.css*/
        .detail-infos .info-item dd img{
            margin-left: 0px;
        }
        .result_con p{
            padding-left: 70px;
        }
        .detail-infos .info-item dd p {
            padding-left: 22px;
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
    <h2 class="view_title"><p>基本信息</p></h2>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>检查日期：</dt>
                <dd>
                    <p class="corrective_date">${vo.businessDate}</p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>单号:</dt>
                <dd>
                    <p class="v_date">${vo.orderNo}</p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>所属公司：</dt>
                <dd>
                    <p class="project_company">${vo.projectCompanyName}</p>
                </dd>
            </dl>
        </div>
    </div>

    <div class="detail-block">
        <div class="detail-infos">

            <dl class="info-item">
                <dt>班组：</dt>
                <dd>
                    <p class="team">${vo.teamName}</p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>责任人：</dt>
                <dd>
                    <p class="contact">${vo.contactName}</p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>责任人电话：</dt>
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
                    <dt>检查结果：</dt>
                    <dd><p class="v_result newline tableNewLine">${vo.results}</p></dd>
                </c:if>
                <c:if test="${type == 2}">
                    <dt>检查项及结果：</dt>
                    <dd>
                        <div style="margin-top: 30px">
                                <%--对检查项进行循环--%>
                            <c:forEach var="items" items="${vo.treeVOList}">
                                <p class="inspectItems" style="">${items.name}</p>
                                <c:forEach var="resultp" items="${items.data}">
                                    <p class="inspectResultsParent" style="">${resultp.name}</p>
                                    <c:forEach var="checkResult" items="${resultp.data}">
                                        <p class="inspectResults">${checkResult.name}</p>
                                    </c:forEach>
                                </c:forEach>
                            </c:forEach>
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
                <dt>检查结果:</dt>
                <dd><p class="v_result newline tableNewLine">${vo.results}</p></dd>
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
    <c:if test="${type == 2}">
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

    <h2 class="view_title"> <p>现场图片</p></h2>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item v_image">
                <c:if test="${vo.isAttach == 0}">无照片信息</c:if>
                <c:if test="${vo.isAttach == 1}">
                    <dt>附件信息：</dt>
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
    <h2 class="view_title"> <p>整改日期及要求</p></h2>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>限期整改时间：</dt>
                <dd>
                    <p class="corrective_completion_date">${vo.correctiveCompletionDate}</p>
                </dd>
            </dl>
        </div>
    </div>
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


    <h2 class="view_title"> <p>处理记录</p></h2>
    <c:forEach var="vo" items="${list}" varStatus="status">
        <div class="detail-block" style="padding-top: 10px">
            <c:choose>
                <c:when test="${vo.correctiveStatus == 1}">
                    <div class="view_title1"><span class="icono-checkCircle" aria-hidden="true"></span>提交整改</div>
                </c:when>
                <c:when test="${vo.correctiveStatus == 2}">
                    <div class="view_title1"><span class="icono-checkCircle" aria-hidden="true"></span>提交复检</div>
                </c:when>
                <c:when test="${vo.correctiveStatus == 3}">
                    <div class="view_title1"><span class="icono-checkCircle" aria-hidden="true"></span>复检不通过</div>
                </c:when>
                <c:otherwise>
                    <div class="view_title1"><span class="icono-checkCircle" aria-hidden="true"></span>整改通过</div>
                </c:otherwise>
            </c:choose>
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>备注信息：</dt>
                    <dd><p class="tableNewLine">${vo.remark}<p></dd>
                </dl>
            </div>
            <div class="detail-infos">
                <dl class="info-item">
                    <c:if test="${vo.isAttach == 0}">无照片信息</c:if>
                    <c:if test="${vo.isAttach == 1}">
                        <dt>附件信息：</dt>
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
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>整改提交人：</dt>
                    <dd><p>${vo.createBy}</p></dd>
                </dl>

                <dl class="info-item">
                    <dt>操作时间：</dt>
                    <dd><p>${vo.createTimeStr}</p></dd>
                </dl>

            </div>
        </div>

    </c:forEach>

</div>
<div style="height: 40px"></div>
<div class="image_big"></div>

<div class="footer_btn">
    <a class="btn btn_submit btn-show-blue delClose">关闭</a>
</div>

<script>
    var ctx = "${ctx}";
    var userId = "${userId}";
    var inspectionId = "${inspectionId}";
    var correctiveStatus = "${correctiveStatus}";
    var type = "${type}"
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
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/inspection/view.js?v=${v}"></script>
<script>
    $("#files").fileinput({
        uploadExtraData: {kvId: '10'}
    });
    files=[];//定义上传图片数组
    $(function () {
        biyue_view.getData();

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

        //整改提交
        $(".submit").on("click", function () {
            var url = ctx + "/inspection/saveCorrectiveProcess";
            var data = getQueryJson();
            if(!data){
                return;
            }
            $.ajax({
                url: url,                              //后台请求地址
                data: data,                            //自定义参数
                processData: false,
                contentType: false,
                type: "post",                          //当要提交自定义参数时，这个参数要设置成post
                dataType: 'json',                     //服务器返回的数据类型
                success: function (data) {            //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                    if (data.status == 1) {
                        alert("提交成功！");
                        layer_close();
                    } else {
                        alert(data.msg);
                    }
                },
                error: function (data, status, e) {      //提交失败自动执行的处理函数
                    alert(e);
                }
            });

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
        };

    });

    //图片上传前
    $('#files').on('filepreupload', function(event, data, previewId, index) {
        files=data.files;
    });

    //提交整改时获取整改表单数据
    function getQueryJson() {
        var status = 0;
        if(correctiveStatus == 1) {
            status = 2;
        } else if(correctiveStatus == 2) {
            var checkValue = $("input[type='radio']:checked").val();
            if(checkValue == "" || checkValue == undefined) {
                alert("请选择复查结果!");
                return false;
            } else {
                status = checkValue;
            }
        } else if(correctiveStatus == 3) {
            status = 2;
        }
        $("#correctiveStatus").val(status);
        var formData = new FormData($("#form_id")[0]);
        $(".fileinput-upload-button").click();
        for(var i=0;i<files.length;i++){
            formData.append("files",files[i]);
        }
        return formData;
    };

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

</script>
</body>
</html>




