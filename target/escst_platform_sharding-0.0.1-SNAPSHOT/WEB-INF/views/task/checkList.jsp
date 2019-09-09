<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>任务派发</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqgrid/css/ui.jqgrid.css"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.css"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.structure.css"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css"/>
    <script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js" type="text/javascript"></script>

    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js"></script>
    <![endif]-->
</head>

<body class="body_bg">
<div class="ui-report">
    <div class="response-head titlePanel">
        <div class="ui-filter">
            <a id="toAccept" class=""><i class=""></i></a>

        </div>
        </div>
    <!--更多查询条件-->
    <div class="toolbar group-box">
        <div class="win-box">
            <div class="btn-group">
                <a id="add_link" class="btn btn-info"><i class="fa fa-plus"></i>检查任务</a>
            </div>
            <div class="btn-group">
                <a id="view_link" class="btn btn-uisblue"><i class="fa fa-search"></i>查看</a>
            </div>
            <div class="btn-group">
                <a id="edit_link" class="btn btn-success"><i class="fa fa-pencil-square-o"></i>编辑</a>
            </div>
            <div class="btn-group">
                <a id="delete_link" class="btn btn-uired"><i class="fa fa-trash-o"></i>删除</a>
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
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js"></script>
<script src="${ctx}/resources/static/plugins/jqgrid/js/jquery.jqGrid.src.js"></script>
<script src="${ctx}/resources/static/plugins/layer/layer.js" type="text/javascript"></script>
<script src="${ctx}/resources/static/js/tablecommon.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {

        //添加
        $("#add_link").click(function () {
            var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
            var _url = ctx + '/projectTask/CheckList?taskId='+gr;
            var _title = '检查任务';
            var iframeWidth = '780px';
            var iframeHeight = '500px';
            if (gr != null) {
                parent.layer_show(_title, _url, iframeWidth, iframeHeight);
            } else {
                layer.msg('您没有选中如何数据请选择后操作', {time: 2000, icon: 0});
            }
        });

        //更新
        $("#edit_link").click(function () {
            var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
            var _url = ctx + '/material/materialEdit';
            var _title = '人员查看';
            var iframeWidth = '780px';
            var iframeHeight = '500px';
            if (gr != null) {
                parent.layer_show(_title, _url, iframeWidth, iframeHeight);
            } else {
                layer.msg('您没有选中如何数据请选择后操作', {time: 2000, icon: 0});
            }

        });
        //删除
        $("#delete_link").click(function () {
            var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
            if (gr != null) {
                parent.memberdel();
            } else {
                layer.msg('您没有选中如何数据请选择后操作', {time: 2000, icon: 0});
            }
        });

        //查看
        $("#view_link").click(function () {
            var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
            var _url = ctx + '/projectTask/projectTaskListDetail?taskId='+gr;
            var _title = '任务查看';
            var iframeWidth = '780px';
            var iframeHeight = '500px';
            if (gr != null) {
                parent.layer_show(_title, _url, iframeWidth, iframeHeight);
            } else {
                layer.msg('您没有选中如何数据请选择后操作', {time: 2000, icon: 0});
            }

        });



    });




    // 设置表格
    function getGridHead() {
        return [
            {'label': 'id', 'name': 'id', 'index': 'id', 'hidden': true},
            {'label': '状态', 'name': 'status', 'index': 'status', 'formatter': 'select',
                'editoptions': {value: "1:待受理; 2:处理中;  3:待检查 ; 4:检查不通过 ;5:已完成"}},
            {'label': '任务说明', 'name': 'remark', 'index': 'remark'},
            {'label': '工程结构', 'name': 'projectStructureName', 'index': 'projectStructureName'},
            {'label': '分包公司名', 'name': 'projectCompanyName', 'index': 'projectCompanyName'},
            {'label': '检查人', 'name': 'examinerName', 'index': 'examinerName'},
            {'label': '联系人', 'name': 'contactsName', 'index': 'contactsName'},
            {'label': '创建时间', 'name': 'createTime', 'index': 'createTime'},
            {'label': '创建人', 'name': 'createByName', 'index': 'createByName'}
        ];
    };
    // 点击查询，获取查询所需要的条件，重新加载表格
    function getGridParamJson() {
        var postPrams = {
            userId: "dc2bb2e3f71b11e6a0d9002590f074f8",
            status:3
        };
        console.log(postPrams);
        return postPrams;
    };
    //获取Url
    function getGridUrl() {
        return ctx + "/projectTask/queryList";
    };
</script>
</body>
</html>
