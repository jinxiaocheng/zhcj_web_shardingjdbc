<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>材料</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqgrid/css/ui.jqgrid.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.structure.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}"/>
</head>

<body class="body_bg">
<div class="ui-report">
    <div class="response-head titlePanel">
        <div class="ui-filter">
            <label>材料名称:</label>
            <input type="text" id="name" name='name' class="form-control w-110" value=""/>
        </div>
        <div class="ui-filter" style="margin:0px 15px;">
            <a id="search_link" class="btn btn-primary"><i class="fa fa-search"></i>查询</a>
        </div>
        <div class="win-box">
            <div class="btn-group">
                <a id="add_link" class="btn btn-info"><i class="fa fa-plus"></i>录入</a>
            </div>
          <!-- <div class="btn-group">
                <a id="edit_link" class="btn btn-success"><i class="fa fa-pencil-square-o"></i>修改</a>
            </div> -->
            <div class="btn-group">
                <a id="import_link" class="btn btn-warning"><i class="fa fa-sign-out"></i>导入</a>
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

        //添加
        $("#add_link").click(function () {
            var url = ctx + '/material/toAdd'
            var title = '新增';
            layer_show(title, url,"420px","340px");
        });

        //更新
        $("#edit_link").click(function () {
        	var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
            var url = ctx + '/material/materialEdit';
            var title = '修改';
            if (gr != null) {
            	 parent.layer_showDouble(title, url);
            } else {
                layer.msg('您没有选中任何数据请选择后操作', {time: 2000, icon: 0});
            }
           
        });

        $("#import_link").click(function () {
            var _url = ctx + '/material/toImport';
            var _title = '材料导入';
            var iframeWidth = '400px';
            var iframeHeight = '300px';
            layer_show(_title, _url, iframeWidth, iframeHeight);
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

    });

    // 设置表格
    function getGridHead() {
        return [
            {'label': 'id', 'name': 'id', 'index': 'id', 'hidden': true},
            {'label': '材料名称', 'name': 'name', 'index': 'name','width':'80px'},
            {'label': '型号', 'name': 'modelName', 'index': 'modelName','width':'200px'},
            {'label': '计量单位', 'name': 'unit', 'index': 'unit','width':'200px'}
        ];
    };
    // 点击查询，获取查询所需要的条件，重新加载表格
    function getGridParamJson() {
        //材料名称
        var name = $("#name").val();
        var postPrams = {
       		name:name
        };
        return postPrams;
    };
    //获取Url
    function getGridUrl() {
        return ctx + "/material/listData";
    };
    function layer_show(_title,_url,w,h) {

        if (_title == null || _title == '') {
            title=false;
        };
        if (_url == null || _url == '') {
            url="404.html";
        };
        if (w == null || w == '') {
            w=800;
        };
        if (h == null || h == '') {
            h=($(window).height() - 50);
        };
        layer.open({
            type: 2,
            title: "|&nbsp;"+_title,
            maxmin: true,
            area: [w,h],
            shadeClose: false, //点击遮罩关闭
            content:_url
        });
    };
</script>
</body>
</html>