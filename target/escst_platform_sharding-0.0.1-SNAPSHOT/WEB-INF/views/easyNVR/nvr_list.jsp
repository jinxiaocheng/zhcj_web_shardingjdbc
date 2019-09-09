<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>视频配置</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqgrid/css/ui.jqgrid.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.structure.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}"/>
    <style>
        .vosName{
            display: inline-block;
            width: 70%;
            overflow: hidden;
            color: #7f7f7f;
        }
        .vosFlag{
            display: inline-block;
            width: 10%;
            overflow: hidden;
            color: #33a4d6;
            text-align: right;
        }
        .vosStatus{
            display: inline-block;
            text-align: right;
            width: 15%;
            overflow: hidden;
            color: #1dac40;
        }
        .vosStatus .error{
            color: #ff3911;
        }
    </style>
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
        <button type="button" class="btn openSearchBtn" data-toggle="collapse"
                data-target="#search-input">
            查询条件 <span class="glyphicon glyphicon-menu-down"></span>
        </button>
        <div id="search-input" class="collapse">
            <div class="ui-filter area">
                <label>区域:</label>
                <div class="btn-group queryCondition">
                    <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
                       data-value="" id="area">请选择</a>
                    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                            class="caret"></span></a>
                    <ul class="dropdown-menu" style="min-width:108px;">
                        <li><a data-value="">请选择</a></li>
                    </ul>
                </div>
            </div>
            <div class="ui-filter site">
                <label>工地:</label>
                <div class="btn-group queryCondition">
                    <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
                       data-value="" id="site">请选择</a>
                    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                            class="caret"></span></a>
                    <ul class="dropdown-menu" style="min-width:108px;">
                        <li><a data-value="">请选择</a></li>
                    </ul>
                </div>
            </div>
            <div class="ui-filter" style="margin:10px 15px;">
                <a id="search_link" class="btn btn-primary" data-toggle="collapse" data-target="#search-input"><i class="fa fa-search"></i>查询</a>
            </div>
        </div>
        <div class="tools-btn" id="tools-btn">
            <div class="btn-group">
                <a id="add_link" class="btn btn-info"><i class="fa fa-plus"></i>录入</a>
            </div>
            <div class="btn-group">
                <a id="edit_link" class="btn btn-success"><i class="fa fa-pencil-square-o"></i>编辑</a>
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
    var userId = "${userId}";  //当前用户ID;
</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}" ></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script src="${ctx}/resources/static/plugins/jqgrid/js/jquery.jqGrid.src.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/popup.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/list_area.js?v=${v}"></script>
<script src="${ctx}/resources/static/js/tablecommon.js?v=${v}" type="text/javascript"></script>
<script type="text/javascript">
    init();
    //新增
    $("#add_link").click(function () {
        var url = ctx + '/video/toNvrConfigure?type=0';
        var title = '新增';
        layer_showOdd(title, url, {w:'920px',h:'560px'});
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

    //编辑
    $("#edit_link").click(function () {
        var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
        var url = ctx + '/video/toNvrConfigure?type=1&id='+gr;
        var title = '修改';
        if (gr != null) {
            layer_showOdd(title, url, {w:'920px',h:'560px'});
        } else {
            layer.msg('请选择一条记录', {time: 2000, icon: 0});
        }
    });

    // 设置表格
    function getGridHead() {
        return [
            {'label': 'id', 'name': 'id', 'index': 'id','hidden':true},
            {'label': '工地', 'name': 'constructionName', 'index': 'constructionName'},
            {'label': 'ip地址', 'name': 'ip', 'index': 'ip',width:"100px"},
            {'label': 'WEB端口', 'name': 'port', 'index': 'port',width:"50px"},
            {'label': 'APP端口', 'name': 'appPort', 'index': 'appPort',width:"50px"},
            {'label': '用户名', 'name': 'userName', 'index': 'userName',width:"100px"},
            {'label': '密码', 'name': 'password', 'index': 'password',width:"100px"},
            {'label': '通道信息', 'name': 'vos', 'index': 'vos',formatter:function(cellvalue, options, rowObject){
                var $list="";
                for(var i in cellvalue){
                    $list+='<span class="vosName">'+(cellvalue[i].name||"未命名")+'</span>'
                        +'<span class="vosFlag">'+(cellvalue[i].flag===0?"否":"是")+'</span>'
                        +'<span class="vosStatus">'+(cellvalue[i].status===0?"<i class='error'>离线</i>":"<i>在线</i>")+'</span>'+"<br>";
                }
                return $list;
            }}
        ];
    }
    // 点击查询，获取查询所需要的条件，重新加载表格
    function getGridParamJson() {
        var postPrams = {
            areaId:$("#area").attr("data-value"),
            constructionId:$("#site").attr("data-value")
        };
        return postPrams;
    };
    //获取Url
    function getGridUrl() {
        return ctx + "/video/getNvrList";
    };



</script>
</body>

</html>