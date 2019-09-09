<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>消息推送</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqgrid/css/ui.jqgrid.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.structure.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}"/>
    <script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}" type="text/javascript"></script>

    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
</head>

<body class="body_bg">
<div class="ui-report">

    <div class="form-inline response-head titlePanel" id="listTools">
        <div class="tools-btn" id="tools-btn">
            <div class="btn-group">
                <a id="add_link" class="btn btn-info"><i class="fa fa-plus"></i>录入</a>
            </div>
            <div class="btn-group">
                <a id="view_link" class="btn btn-uisblue"><i class="fa fa-search"></i>查看</a>
            </div>
        </div>

    </div>
    <div class="jqGrid_wrapper">
        <table class="gridTable table-condensed" id="gridTable" aa="1"></table>
        <div class="gridPage" id="gridPager">

        </div>
    </div>
</div>
<script>
    var ctx = "${ctx}";
    var userId = "${userId}";  //当前用户ID
    var type = "${type}";
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
<script src="${ctx}/resources/static/plugins/jqgrid/js/jquery.jqGrid.src.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/popup.js?v=${v}"></script>
<script src="${ctx}/resources/static/js/tablecommon.js?v=${v}" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/message/list.js?v=${v}"></script>
<script type="text/javascript">



</script>
</body>
</html>