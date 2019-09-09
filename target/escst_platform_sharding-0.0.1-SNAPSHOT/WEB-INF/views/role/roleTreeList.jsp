<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>角色管理</title>
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
         <input hidden="true" id="nodeId" value="${nodeId}">
         <input hidden="true" id="constructionId" value="${constructionId }">
         <div class="win-box tools-btn">
             <div class="btn-group">
                 <a id="add_link" class="btn btn-info"><i class="fa fa-plus"></i>录入</a>
             </div>
             <div class="btn-group">
                 <a id="edit_link" class="btn btn-success"><i class="fa fa-pencil-square-o"></i>编辑</a>
             </div>
             <div class="btn-group">
                 <a id="view_link" class="btn btn-uisblue"><i class="fa fa-search"></i>查看</a>
             </div>
             <div class="btn-group">
                 <a id="assign_video" class="btn btn-uirole"><i class="fa fa-user-plus"></i>分配视频</a>
             </div>

            <!--  <div class="btn-group">
                 <a id="delete_link" class="btn btn-uired"><i class="fa fa-trash-o"></i>删除</a>
             </div>
             <div class="btn-group">
                 <a id="export_link" class="btn btn-uirole"><i class="fa fa-download"></i>导出</a>
             </div>
             <div class="btn-group">
                 <a id="import_link" class="btn btn-warning"><i class="fa fa-sign-out"></i>导入</a>
             </div> -->
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
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script src="${ctx}/resources/static/plugins/jqgrid/js/jquery.jqGrid.src.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script src="${ctx}/resources/static/js/tablecommon.js?v=${v}" type="text/javascript"></script>
<script type="text/javascript">

    //添加
    $("#add_link").click(function () {
        var nodeId = $("#nodeId").val();
        var _url = ctx + '/role/toAdd';
        if(nodeId!=null){
            _url = ctx + '/role/toAdd?orgId='+nodeId;
        }
        var _title = '角色录入';
        var iframeWidth = '780px';
        var iframeHeight = '500px';
        layer_show(_title, _url, iframeWidth, iframeHeight);
    });


    //删除
    $("#delete_link").click(function () {
        var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
        if (gr != null) {
            parent.memberdel();
        } else {
            layer.msg('您没有选中任何数据请选择后操作', {time: 2000, icon: 0});
        }
    });



    //分配视频
    $("#assign_video").click(function () {
        var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
        if (gr != null) {
            var _url = ctx + '/video/toAllot?id='+gr;
            var _title = '分配视频';
            var iframeWidth = '780px';
            var iframeHeight = '500px';
            layer_show(_title, _url, iframeWidth, iframeHeight);
        } else {
            layer.msg('您没有选中任何数据请选择后操作', {time: 2000, icon: 0});
        }
    });

    //查看
    $("#view_link").click(function () {
        viewLink()
    });

    function viewLink() {
        var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
        if (gr != null) {
            var _url = ctx + '/role/toLookPage?roleId='+gr+'&type=view';
            var _title = '角色查看';
            var iframeWidth = '780px';
            var iframeHeight = '500px';
            layer_show(_title, _url, iframeWidth, iframeHeight);
        } else {
            layer.msg('您没有选中如何任何数据请选择后操作', {time: 2000, icon: 0});
        }
    }
    //表格扩展
    function getGridExtendParam(){
        return {
            //双击时间
            myDblClickRow:function (rowid,iRow,iCol,e){
                $("#view_link").click();
            }
        }
    }

    //编辑
    $("#edit_link").click(function () {
        var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
        if (gr != null) {
            var _url = ctx + '/role/toEdit?roleId='+gr;
            var _title = '角色编辑';
            var iframeWidth = '780px';
            var iframeHeight = '500px';
            layer_show(_title, _url, iframeWidth, iframeHeight);
        } else {
            layer.msg('您没有选中如何任何数据请选择后操作', {time: 2000, icon: 0});
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

    // 设置表格
    function getGridHead() {
        return [
            {'label': 'id', 'name': 'id', 'index': 'id', 'hidden': true},
            {'label': '角色名', 'name': 'name', 'index': 'name'},
/* 盛荣要求去掉
            {'label': '所属组织', 'name': 'orgName', 'index': 'orgName'},
*/
            {'label': '创建时间', 'name': 'createTime', 'index': 'createTime'}
        ];
    };

    // 点击查询，获取查询所需要的条件，重新加载表格
    function getGridParamJson() {
        //角色名称
        var name = $.trim($("#name").val());
        var postPrams = {
            name: name,
            orgId:$("#nodeId").val(),
            constructionId:$("constructionId").val()
        };
        return postPrams;
    };
    //获取Url
    function getGridUrl() {
        return ctx + "/role/roleList";
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