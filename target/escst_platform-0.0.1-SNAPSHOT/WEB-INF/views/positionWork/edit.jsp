<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>编辑岗位工种</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.min.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/validform.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/tableform.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/add.css?v=${v}">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
    <script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}" type="text/javascript"></script>
</head>

<body class="body_bottom">

<div class="form-table">
    <form id="personform" class="form-inline add_form">
        <div class="row add_list ">
            <div class="form-group col-xs-11 area" kk-contral="require">
                <label  class="col-xs-4">区域</label>
                <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="areaId" id="area" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    请选择区域
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu3">
                </ul>
            </div>
        </div>
        <div class="row add_list ">
            <div class="form-group col-xs-11 site" kk-contral="require">
                <label  class="col-xs-4">工地</label>
                <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="constructionid" id="site" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    请选择工地
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu4">
                </ul>
            </div>
        </div>
        <div class="row add_list ">
            <div class="form-group col-xs-11 date_time"  kk-contral="require">
                <label for="name" class="col-xs-4">名称</label>
                <input type="text" class="form-control col-xs-8" kk-prop="value" id="name" placeholder="请输入名称">
            </div>
        </div>
        <div class="row add_list ">
            <div class="form-group col-xs-11 person queryCondition"  kk-contral="require">
                <label class=" col-xs-4">类型</label>
                <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="data-value" data-toggle="dropdown" aria-expanded="false"
                        data-value="" id="type">
                    --请选择--
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu type" style="min-width:108px;max-height: 250px;overflow: auto">
                    <li ><a data-value="">--请选择--</a></li>
                    <li ><a data-value="1">岗位</a></li>
                    <li ><a data-value="2">工种</a></li>
                </ul>
            </div>
        </div>
        <div class="footer_btn"><a class="btn btn_submit btn-show-blue" id="submit">提交</a></div>
    </form>
</div>
<%--警告--%>
<div class="alert alert-danger alert-dismissable error">
    <button type="button" class="close" aria-hidden="true">
        &times;
    </button>
    <span>警告！请不要提交。</span>
</div>
<%--<script src="${ctx}/resources/static/js/lib/require.js?v=${v}" data-main="${ctx}/resources/static/js/tablemain"></script>--%>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script>  var ctx = "${ctx}",userId="${userId}";</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/positionWork/add.js?v=${v}"></script>
<script>
    $(function(){
        positionWork.editLoadData();
        $("#personform").kk_tableForm("#submit",positionWork.editSendData);
    })
</script>
</body>
</html>




