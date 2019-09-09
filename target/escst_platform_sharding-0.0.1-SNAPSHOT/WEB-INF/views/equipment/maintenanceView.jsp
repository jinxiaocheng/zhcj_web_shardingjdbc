<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>设备保养-查看</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/layui/css/layui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/view.css?v=${v}"/>
    <style>
        .detail-infos .info-item dd {
            margin-left: 100px;
        }
        .detail-infos .info-item dt {
            width: 90px;
        }
    </style>
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
                <dt>名称：</dt>
                <dd>
                    <p class="name"></p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>编号：</dt>
                <dd>
                    <p class="number"></p>
                </dd>
            </dl>

        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>工地名：</dt>
                <dd>
                    <p class="constructionName"></p>
                </dd>
            </dl>

        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>设备类型：</dt>
                <dd>
                    <p class="type"></p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>维修保养时间：</dt>
                <dd>
                    <p class="maintenanceDate"></p>
                </dd>
            </dl>
        </div>
    </div>

    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>创建人：</dt>
                <dd>
                    <p class="createBy"></p>
                </dd>
            </dl>
        </div>
    </div>

    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>备注信息：</dt>
                <dd>
                    <p class="remark"></p>
                </dd>
            </dl>
        </div>
    </div>

    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <c:if test="${list.isAttach == 0}">无照片信息</c:if>
                <c:if test="${list.isAttach == 1}">
                    <dt>附件信息:</dt>
                    <dd>
                    <c:forEach var="filePathVo" items="${list.picList}">
                        <dd class="v_images"  name = "${filePathVo.filePath}">
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
                <dt>创建时间：</dt>
                <dd>
                    <p class="createTime"></p>
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
<script>
    $(function () {
        biyue.scroll();
        biyue.popDefault();
        //在父级页面后去数据
        var parentData = window.parent.check_data;
        for(var i in parentData){
            if(i === "type"){
                if(parentData[i]==='1'){
                    $(".type").html('维修');
                }else if(parentData[i]==='2'){
                    $(".type").html('保养');
                }
            }else{
                $('.'+i).html(parentData[i]);
            }
        }
        //点击放大图片
        $(".v_images").click(function () {
            var len=$(".v_images").length,filePath=[];
            var index=$(this).index()-2;
            for(var i=0 ;i<len;i++){
                filePath[i]={src:$(".v_images").eq(i).attr("name")}
            }
            top.photos(filePath,index);
        });

    });
</script>
</body>
</html>
