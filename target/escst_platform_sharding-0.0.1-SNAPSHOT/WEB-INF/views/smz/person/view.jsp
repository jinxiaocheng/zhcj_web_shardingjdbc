<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<%@ include file="/common/common_tag.jsp"%>--%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>实名制-人员管理-查看</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/view.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/smz/person/view.css?v=${v}" />
        <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
</head>
<body class="biyue-body">
    <h2 class="min-title"  data-toggle="collapse" data-target="#personInfo" aria-expanded="true"><p>人员信息</p><span class="glyphicon glyphicon-menu-down"></span></h2>
    <div class="biyue-bg collapse in" id="personInfo" aria-expanded="true" >
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>姓名：</dt>
                    <dd>
                        <p class="personName">--</p>
                    </dd>
                </dl>
                <dl class="info-item">
                    <dt>性别：</dt>
                    <dd>
                        <p class="sex">--</p>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>人员头像：</dt>
                    <dd class="personChartFile">

                    </dd>
                </dl>
            </div>
        </div>
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>籍贯：</dt>
                    <dd>
                        <p class="native">--</p>
                    </dd>
                </dl>
                <dl class="info-item">
                    <dt>学历：</dt>
                    <dd>
                        <p class="degrees">--</p>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>民族：</dt>
                    <dd>
                        <p class="nation">--</p>
                    </dd>
                </dl>
                <dl class="info-item">
                    <dt>积分：</dt>
                    <dd>
                        <p class="integral">--</p>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>岗位 ：</dt>
                    <dd>
                        <p class="station">--</p>
                    </dd>
                </dl>
                <dl class="info-item">
                    <dt>工种：</dt>
                    <dd>
                        <p class="category">--</p>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>等级：</dt>
                    <dd>
                        <p class="categoryLevel">--</p>
                    </dd>
                </dl>
                <dl class="info-item">
                    <dt>门禁卡号：</dt>
                    <dd>
                        <p class="readCardId">--</p>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>登记日期：</dt>
                    <dd>
                        <p class="registerDate">--</p>
                    </dd>
                </dl>
                <dl class="info-item">
                    <dt>证件类型：</dt>
                    <dd>
                        <p class="standby7">--</p>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>证件编号：</dt>
                    <dd>
                        <p class="identifyID">--</p>
                    </dd>
                </dl>
                <dl class="info-item">
                    <dt>其他证件编号：</dt>
                    <dd>
                        <p class="passport">--</p>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>联系电话：</dt>
                    <dd>
                        <p class="telPhone">--</p>
                    </dd>
                </dl>
                <dl class="info-item">
                    <dt>施工区域：</dt>
                    <dd>
                        <p class="buildArea">--</p>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>员工工号：</dt>
                    <dd>
                        <p class="traID">--</p>
                    </dd>
                </dl>
                <dl class="info-item">
                    <dt>培训负责人：</dt>
                    <dd>
                        <p class="traPrincipal">--</p>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>出生日期：</dt>
                    <dd>
                        <p class="birthDay">--</p>
                    </dd>
                </dl>
                <dl class="info-item">
                    <dt>入场日期：</dt>
                    <dd>
                        <p class="entranceDate">--</p>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="detail-block">
            <div class="detail-infos">

                <dl class="info-item">
                    <dt>总培训次数：</dt>
                    <dd>
                        <p class="trainCount">--</p>
                    </dd>
                </dl>
                <dl class="info-item">
                    <dt>总培训学时：</dt>
                    <dd>
                        <p class="trainHour">--</p>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>是否离场：</dt>
                    <dd>
                        <p class="isOut">--</p>
                    </dd>
                </dl>
                <dl class="info-item">
                    <dt>健康情况：</dt>
                    <dd>
                        <p class="isPhoto">--</p>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>是否购买保险：</dt>
                    <dd>
                        <p class="isCard">--</p>
                    </dd>
                </dl>
                <dl class="info-item">
                    <dt>婚姻状况：</dt>
                    <dd>
                        <p class="maritalStatus">--</p>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>户口性质：</dt>
                    <dd>
                        <p class="registeredType">--</p>
                    </dd>
                </dl>
                <dl class="info-item">
                    <dt>血型：</dt>
                    <dd>
                        <p class="bloodType">--</p>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>紧急联系人：</dt>
                    <dd>
                        <p class="emergencyContacts">--</p>
                    </dd>
                </dl>
                <dl class="info-item">
                    <dt>联系人电话：</dt>
                    <dd>
                        <p class="emergencyContactsTel">--</p>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="detail-block">
            <div class="detail-infos">

                <dl class="info-item">
                    <dt>合同编号：</dt>
                    <dd>
                        <p class="contractNumber">--</p>
                    </dd>
                </dl>
                <dl class="info-item">
                    <dt>单位：</dt>
                    <dd>
                        <p class="unitName">--</p>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="detail-block">
            <div class="detail-infos">
                <dl class="info-item">
                    <dt>详细地址：</dt>
                    <dd>
                        <p class="address">--</p>
                    </dd>
                </dl>
            </div>
        </div>
    </div>
    <h2 class="min-title"  data-toggle="collapse" data-target="#trainTable" aria-expanded="true"><p>培训信息</p><span class="glyphicon glyphicon-menu-down"></span></h2>
    <div id="trainTable" class="collapse in"  aria-expanded="true">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th></th>
                <th style="width: 100px">培训单位</th>
                <th>培训名称</th>
                <th style="width: 100px">培训类型</th>
                <th>培训时间</th>
                <th style="width: 80px">培训学时</th>
                <th style="width: 80px">成绩</th>
                <th style="width: 80px">是否合格</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
    <script>
    var ctx = "${ctx}";
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/smz/person/view.js?v=${v}"></script>
<script>
    $(function(){
        biyue.scroll("html",{
            autohidemode:false
        });
        smzPerson.getData();
    })
</script>
</body>
</html>
