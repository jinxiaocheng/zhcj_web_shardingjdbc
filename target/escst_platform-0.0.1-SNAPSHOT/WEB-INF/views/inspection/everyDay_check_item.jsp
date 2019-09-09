<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<%@ include file="/common/common_tag.jsp"%>--%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>日常检查项维护</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/easyui/easyui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/add.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/projectStructure/schedulePlanList.css?v=${v}"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
</head>
<style>
    html,body{
        width:100%;
        height:100%;
    }
</style>
<body>

<div class="container-full" data-value="3">

    <div class="menu-left-tree  fl">
        <ul id="orgTree" class="ztree"></ul>
    </div>
    <div class="videoplay">
        <div class="ui-report">
            <div class="form-inline response-head titlePanel" id="listTools">
               <%-- <button type="button" class="btn openSearchBtn" data-toggle="collapse"
                        data-target="#search-input">
                    查询条件 <span class="glyphicon glyphicon-menu-down"></span>
                </button>
                <div id="search-input" class="collapse">
                    <div class="ui-filter">
                        <label>名称:</label>
                        <input type="text" id="name" class="form-control w-110" value=""/>
                    </div>
                    <div class="ui-filter" style="margin:10px 15px;">
                        <a id="search_link" class="btn btn-primary" data-toggle="collapse" data-target="#search-input"><i
                                class="fa fa-search"></i>查询</a>
                    </div>
                </div>--%>
                <div class="tools-btn" id="tools-btn">
                    <div class="btn-group">
                        <a id="add_link" class="btn btn-info"><i class="fa fa-plus"></i>录入</a>
                    </div>
                    <div class="btn-group">
                        <a id="edit_link" class="btn btn-success"><i class="fa fa-pencil-square-o"></i>编辑</a>
                    </div>
                    <div class="btn-group">
                        <a id="delete_link" class="btn btn-uired"><i class="fa fa-trash-o"></i>删除</a>
                    </div>
                </div>
            </div>
            <div id="tableBox">
                <table class="" id="treeTable" aa="1"></table>
                <p class="tableError">没有找到您要的相关数据!</p>
            </div>
        </div>
    </div>
</div>
<div id="add-edit" class="form-horizontal" data-value="" data-type=0>
    <div class="form-group" kk-contral="require">
        <label class="col-sm-2 control-label">名称</label>
        <div class="col-sm-9" >
            <input type="text" kk-prop="value" class="form-control" id="planName" placeholder="名称">
        </div>
    </div>
    <div class="form-group" kk-contral="require">
        <label class="col-sm-2 control-label">序号</label>
        <div class="col-sm-9">
            <input type="number" kk-prop="value" kk-RE="int" kk-name="序号" class="form-control" id="sortNum" placeholder="序号">
        </div>
    </div>
    <footer>
        <a class="btn btn_submit btn-show-blue" id="submit">确&nbsp;定</a>
    </footer>
    <%--警告--%>
    <div class="alert alert-danger alert-dismissable error">
        <button type="button" class="close" aria-hidden="true">
            &times;
        </button>
        <span>警告！请不要提交。</span>
    </div>
</div>
<script>
    var ctx = "${ctx}";
    var type = "scheduledPlan";
</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/easyui/jquery.easyui.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/easyui/easyui-lang-zh_CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/list_area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/inspection/subentry_check_item.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/listCommon.js?v=${v}"></script>
<script>
    $(function(){
        treeInit(createTable);
        $("#add-edit").kk_tableForm("#submit",addEdit);
    })

</script>
</body>
</html>
