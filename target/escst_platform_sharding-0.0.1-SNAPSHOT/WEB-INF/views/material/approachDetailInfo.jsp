<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>材料进场-查看</title>
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
        .detail-infos .info-item dd p {
            padding-left: 60px;
        }
        .my_load {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.06);
            z-index: 1000;
            display: none;
        }
        .my_load img {
            -webkit-transform: translate(-50%, -50%);
            -moz-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            -o-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
            position: absolute;
            top: 50%;
            left: 50%;
        }
        .detail-infos .info-item dd img{
            padding: 10px;
            margin-left: 0;
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
                    <p class="area"></p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>工地:</dt>
                <dd>
                    <p class="site"></p>
                </dd>
            </dl>

        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>进场日期:</dt>
                <dd>
                    <p class="time"></p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>进场数量:</dt>
                <dd>
                    <p class="quantity"></p>
                </dd>
            </dl>


        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>材料名称:</dt>
                <dd>
                    <p class="name"></p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>型号:</dt>
                <dd>
                    <p class="model"></p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>单位:</dt>
                <dd>
                    <p class="unit"></p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>生产厂家:</dt>
                <dd>
                    <p class="manufacturer"></p>
                </dd>
            </dl>

        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>使用部位:</dt>
                <dd>
                    <p class="usePosition"></p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>报告编号:</dt>
                <dd>
                    <p class="reportNum"></p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>现场验证结果:</dt>
                <dd>
                    <p class="siteResult"></p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>试验结果:</dt>
                <dd>
                    <p class="experimentResult"></p>
                </dd>
            </dl>

        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">

            <dl class="info-item">
                <dt>备注:</dt>
                <dd>
                    <p class="remark"></p>
                </dd>
            </dl>

        </div>
    </div>

    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item v_image_list">
                <dt>照片信息：</dt>
                <dd class="filePathList">
                    <div class="v_images" name = "" style="padding-left: 60px;white-space: initial;">
                    </div>
                </dd>
            </dl>
        </div>
    </div>
</div>
<div style="height: 40px"></div>
<div class="image_big"></div>

<div class="footer_btn"><a class="btn btn_submit btn-show-blue delClose">关闭</a></div>
<%--加载--%>
<div class="my_load">
    <img src="${ctx}/resources/static/images/ajax-loader.gif" alt="">
</div>
<script>
    var ctx = "${ctx}",image_setTimeout;;
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
<script  type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script>
    $(function () {
        var biyue =new biYue();
        nowData(biyue.urlSearch().id);
        imagesBig();
    });

    //点击关闭
    $(".delClose").on("click", function () {
        layer_close();
    });

    /*关闭弹出框口*/
    function layer_close() {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    };
    function imagesBig() {
        //点击放大图片
        $(".v_images").on('click','img',function () {
            var filePath=[],index=$(this).index();
            $(".v_images img").each(function () {
                filePath.push({src:$(this).attr("src")});
            });
            top.photos(filePath,index);
        });
    }
    function nowData(id) {
        $(".my_load").show();
        $.ajax({
            type: "post",
            url: ctx + '/materialApproach/approachDetailInfo',
            data:{
                id:id
            },
            success: function (data) {
                $(".my_load").hide();
                if(data.status == 1){

                    data=data.value;
                    $(".name").html(data.NAME);
                    $(".time").html(data.createTime);
                    $(".remark").html(data.remark||"暂无备注！");
                    $(".manufacturer").html(data.manufacturer);
                    $(".approachDate").html(data.approachDate);
                    $(".usePosition").html(data.usePosition);
                    $(".quantity").html(data.quantity);
                    $(".area").html(data.territoryName);
                    $(".site").html(data.constructionName);
                    $(".model").html(data.model);
                    $(".unit").html(data.unit);
                    $(".reportNum").html(data.reportNum);
                    $(".siteResult").html(data.siteResult===1?"合格":"不合格");
                    $(".experimentResult").html(data.experimentResult===1?"合格":"不合格");

                    if(data.filePathList){
                        if(data.filePathList.length>0){
                            var $list="";
                            for(var i=0;i<data.filePathList.length;i++){
                                $list+='<img src="'+data.filePathList[i].name+'" alt="">';
                            }
                            $(".v_images").html($list);
                        }else{
                            $(".filePathList").html("<p>暂无图片！</p>");
                        }
                    }

                } else {
                    layer.alert(data.msg);
                }
            },
            error: function (data, status, e) {   //提交失败自动执行的处理函数
                $(".my_load").hide();
                layer.alert(String(e));
            }
        });
    }

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
