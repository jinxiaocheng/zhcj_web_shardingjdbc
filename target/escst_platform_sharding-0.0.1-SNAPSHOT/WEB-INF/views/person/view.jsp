<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>人员维护-查看</title>
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
                <dt>考勤识别方式:</dt>
                <dd>
                    <p class="attendanceType"></p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>区域:</dt>
                <dd>
                    <p class="areaName"></p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>工地:</dt>
                <dd>
                    <p class="constructionName"></p>
                </dd>
            </dl>

        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>姓名:</dt>
                <dd>
                    <p class="name"></p>
                </dd>
            </dl>

            <dl class="info-item">
                <dt>性别:</dt>
                <dd>
                    <p class="sex"></p>
                </dd>
            </dl>

        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>头像:</dt>
                <dd>
                    <p class="personImg">
                        <img src="" alt="" style="width: 100px;height: 120px">
                    </p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>身份证号码:</dt>
                <dd>
                    <p class="idCard"></p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>手机号码:</dt>
                <dd>
                    <p class="mobile"></p>
                </dd>
            </dl>

        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">

            <dl class="info-item">
                <dt>所属公司:</dt>
                <dd>
                    <p class="projectCompanyName"></p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>岗位/工种:</dt>
                <dd>
                    <p class="positionWorkTypeName"></p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>班组:</dt>
                <dd>
                    <p class="teamName"></p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>班组长:</dt>
                <dd>
                    <p class="isLeader"></p>
                </dd>
            </dl>


        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>卡号:</dt>
                <dd>
                    <p class="cardNumber"></p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>办卡日期:</dt>
                <dd>
                    <p class="createDate"></p>
                </dd>
            </dl>

        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>证书类别:</dt>
                <dd>
                    <p class="certificateTypeName"></p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>证书编号:</dt>
                <dd>
                    <p class="certificateNumber"></p>
                </dd>
            </dl>

        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>签发日期:</dt>
                <dd>
                    <p class="certificateIssueDate"></p>
                </dd>
            </dl>

            <dl class="info-item">
                <dt>有效日期:</dt>
                <dd>
                    <p class="certificateValidDate"></p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="detail-block">
        <div class="detail-infos">
            <dl class="info-item">
                <dt>发证机关:</dt>
                <dd>
                    <p class="certificateIssueOrgans"></p>
                </dd>
            </dl>
            <dl class="info-item">
                <dt>银行卡号:</dt>
                <dd>
                    <p class="bankCard"></p>
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
<script type="text/javascript" src="${ctx}/resources/static/js/person/personView.js?v=${v}"></script>
<script>
    $(function () {
        biyue.scroll();
        biyue.popDefault();
        moduleView.getData();

        $(".personImg").on('click', 'img', function () {
            var src = $(this).attr("src");
            top.photos([{
                "src": src
            }], 0)
        })
    });
</script>
</body>
</html>
