<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>车辆进出查看</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet"/>
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" rel="stylesheet"/>
    <link type="text/css" href="${ctx}/resources/static/css/tableform.css?v=${v}" rel="stylesheet"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
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
                <dt>车牌号码:</dt>
                <dd>
                    <p class="v_date" id="plateNo"></p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>进出场:</dt>
                <dd>
                    <p class="v_construction" id="direction"></p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>通行时间:</dt>
                <dd>
                    <p class="v_company" id="passTime"></p>
                </dd>
            </dl>
        </div>
    </div>
<%--    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item v_image_list">
	            <dt>附件信息：</dt>
	            <dd id="fileList"></dd>
            </dl>

        </div>
    </div>--%>
</div>
<div style="height: 40px"></div>
<div class="image_big"></div>
<div class="footer_btn"><a class="btn btn_submit btn-show-blue delClose">关闭</a></div>
<script>
    var ctx = "${ctx}";
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script>
    $(function () {

        //点击关闭
        $(".delClose").on("click", function () {
            layer_close();
        });
        
        loadData();
    })
    //点击放大图片
    function potots() {
        $(".v_images").click(function () {
            var len=$(".v_images").length,filePath=[];
            var index=$(this).index();
            for(var i=0 ;i<len;i++){
                filePath[i]={src:$(".v_images").eq(i).attr("name")}
            }
            parent.parent.photos(filePath,index);

        });
    }
    /*关闭弹出框口*/
    function layer_close() {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    };
    function loadData() {
    	var id = biyue.urlSearch().id;
    	biyue.ajax({
            url:'/carPass/queryDetail',
            data:{
                id:id
            },
            fun:function (data) {
                var dataV = data.value;
                $("#plateNo").html(dataV.plateNo);
                $("#passTime").html(dataV.passTime);
                $("#direction").html(dataV.direction===0?"进场":"出场");

                var fileHtml = '<dd class="v_images" name = "' + dataV.carPath + '"><img src="'+dataV.carPath + '" alt=""></dd>';
                $('#fileList').html(fileHtml);
                potots();
            }
        })
    }
</script>
</body>
</html>




