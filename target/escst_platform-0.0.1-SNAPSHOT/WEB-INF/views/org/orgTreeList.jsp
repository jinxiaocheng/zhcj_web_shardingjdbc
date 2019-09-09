<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>机构管理</title>
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
    <div class="response-head titlePanel">
        <input hidden="true" id="nodeId" name="nodeId" value="${nodeId }">
        <div class="win-box tools-btn" style="display: none;">
            <div class="btn-group">
                <a id="add_link" class="btn btn-info"><i class="fa fa-plus"></i>录入</a>
            </div>
            <div class="btn-group">
                <a id="edit_link" class="btn btn-success"><i class="fa fa-pencil-square-o"></i>编辑</a>
            </div>
            <div class="btn-group">
                <a id="delete_link" class="btn btn-uired"><i class="fa fa-trash-o"></i>删除</a>
            </div>
            <div class="btn-group" id="jigoudiv">
                <a id="assign_link" class="btn btn-info"><i class="fa fa-user-plus"></i>分配工地</a>
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
    var constructionId = "${constructionId}";
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
<script src="${ctx}/resources/static/js/tablecommon.js?v=${v}" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        //新增机构
        $("#add_link").click(function () {
            var _url = ctx + '/org/toAdd?nodeId=' + $("#nodeId").val();
            var _title = '机构录入';
            var iframeWidth = '780px';
            var iframeHeight = '500px';
            layer_show(_title, _url, iframeWidth, iframeHeight);
        });

        //分配
        $("#assign_link").click(function () {
            var _url = ctx + '/org/toAssign?nodeId=' + $("#nodeId").val();
            var _title = '按照工地新增机构';
            var iframeWidth = '780px';
            var iframeHeight = '500px';
            layer_show(_title, _url, iframeWidth, iframeHeight);
        });



        //机构编辑
        $("#edit_link").click(function () {
            var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
            var _url = ctx + '/org/toEdit?id=' + gr;
            var _title = '机构编辑';
            var iframeWidth = '780px';
            var iframeHeight = '500px';
            if (gr != null) {
                layer_show(_title, _url, iframeWidth, iframeHeight);
            } else {
                layer.msg('您没有选中任何数据请选择后操作', {time: 2000, icon: 0});
            }
        });

        //删除
        $("#delete_link").click(function () {
            var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
            if (gr != null) {
                layer.confirm('确认删除该条记录吗？', {
                    title: '提示',
                    icon: 2,
                    btn: ['确定', '关闭'],
                    yes: function () {
                        $.ajax({
                            type: "post",
                            url: ctx + "/org/delOrg",
                            data: {id: gr},
                            dataType: "json",
                            success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                                if (data.status == 1) {
                                    layer.msg('删除成功', {icon: 1, time: 1000});
                                    //alert("删除机构成功！");
                                    layer_close();
                                    parent.restart_tree();
                                } else {
                                    layer.alert(data.msg);
                                }
                            },
                            error: function (data, status, e) {   //提交失败自动执行的处理函数
                                layer.alert(String(e));
                            }
                        });

                    }
                });
            } else {
                layer.msg('您没有选中任何数据请选择后操作', {time: 2000, icon: 0});
            }

        });

        //刷新左边树形图
        function restart_tree() {
            parent.restart_tree();
        }

        window.restart_tree = restart_tree;
        //查看
        $("#view_link").click(function () {
            var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
            var _url = ctx + '/test/personnelSee';
            var _title = '人员查看';
            var iframeWidth = '780px';
            var iframeHeight = '500px';
            if (gr != null) {
                parent.layer_show(_title, _url, iframeWidth, iframeHeight);
            } else {
                layer.msg('您没有选中任何数据请选择后操作', {time: 2000, icon: 0});
            }

        });

        //查询
        $("#search_link").click(function () {
            var url = getGridUrl();
            var postParams = getGridParamJson();
            $("#gridTable").jqGrid("setGridParam", {
                url: url,
                postData: postParams,
                page: 1
            }).trigger("reloadGrid");
        });
        if (constructionId == '' || constructionId == null) {
            $(".tools-btn").show();
        }
    });

    // 设置表格
    function getGridHead() {
        return [
            {'label': 'id', 'name': 'id', 'index': 'id', 'hidden': true},
            {'label': '机构名称', 'name': 'name', 'index': 'name'},
            {'label': '组织编码', 'name': 'sysCode', 'index': 'sysCode'},
            {
                'label': '是否有效', 'name': 'status', 'index': 'status',
                'formatter': 'select',
                'editoptions': {value: "1:是; 0:否;"}

            }
        ];
    };

    // 点击查询，获取查询所需要的条件，重新加载表格
    function getGridParamJson() {
        var postPrams = {
            parentId: $("#nodeId").val()
        };
        return postPrams;
    };

    //获取Url
    function getGridUrl() {
        return ctx + "/org/queryByNodeId";
    };

    /*刷新列表*/
    function layer_close() {
        var url = ctx + "/org/queryByNodeId";
        jQuery("#gridTable").jqGrid('setGridParam', {url: url}).trigger("reloadGrid");
    };

    function layer_show(_title, _url, w, h) {
        if (_title == null || _title == '') {
            title = false;
        }
        ;
        if (_url == null || _url == '') {
            url = "404.html";
        }
        ;
        if (w == null || w == '') {
            w = 800;
        }
        ;
        if (h == null || h == '') {
            h = ($(window).height() - 50);
        }
        ;
        layer.open({
            type: 2,
            title: "|&nbsp;" + _title,
            maxmin: true,
            area: [w, h],
            shadeClose: false, //点击遮罩关闭
            content: _url
        });
    };
</script>
</body>
</html>