<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>任务派发-查看</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/layui/css/layui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/view.css?v=${v}"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
</head>
<body>
<div class="biyue-bg">
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>派发单号：</dt>
                <dd>
                    <p class="orderNo"></p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>任务状态：</dt>
                <dd>
                    <p class="status"></p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>检查部位：</dt>
                <dd>
                    <p class="projectStructureName"></p>
                </dd>
            </dl>

        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>所属公司：</dt>
                <dd>
                    <p class="projectCompanyName"></p>
                </dd>
            </dl>

        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>处理人：</dt>
                <dd>
                    <p class="contactsName"></p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>处理人电话：</dt>
                <dd>
                    <p class="mobile"></p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>派发人：</dt>
                <dd>
                    <p class="examinerName"></p>
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
                <div class="view_title1"><span class="icono-checkCircle" aria-hidden="true"></span>待受理</div>
            </c:when>
            <c:when test="${vo.taskStatus == 2}">
                <div class="view_title1"><span class="icono-checkCircle" aria-hidden="true"></span>处理中</div>
            </c:when>
            <c:when test="${vo.taskStatus == 3}">
                <div class="view_title1"><span class="icono-checkCircle" aria-hidden="true"></span>待检查</div>
            </c:when>
            <c:when test="${vo.taskStatus == 4}">
                <div class="view_title1"><span class="icono-checkCircle" aria-hidden="true"></span>检查不通过</div>
            </c:when>
            <c:otherwise>
                <div class="view_title1"><span class="icono-checkCircle" aria-hidden="true"></span>已完成</div>
            </c:otherwise>
        </c:choose>
        <div class="detail-block">
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
        </div>
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
                    <dt>处理人：</dt>
                    <dd><p>${vo.contactsName}</p></dd>
                </dl>

                <dl class="info-item">
                    <dt>处理时间：</dt>
                    <dd><p>${vo.createTime}</p></dd>
                </dl>

            </div>
        </div>

    </c:forEach>
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

        //填写结果
        ;(function () {
            var dataV = parent.check_data;
            for(var i in dataV){
                if(i==="status"){
                    var statusTask = "";
                    if(dataV[i]===1){
                        statusTask = "处理中";
                    }else if(dataV[i]===2){
                        statusTask = "待检查"
                    }else if(dataV[i]===3){
                        statusTask = "检查不通过"
                    }else if(dataV[i]===4){
                        statusTask = "检查不通过"
                    }else if(dataV[i]===5){
                        statusTask = "已完成"
                    }
                    $('.status').html(statusTask);
                }else{
                    $('.'+i).html(dataV[i]);
                }
            }
        })();

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
