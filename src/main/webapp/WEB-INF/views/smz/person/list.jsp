<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<%@ include file="/common/common_tag.jsp"%>--%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>实名制-人员管理</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqgrid/css/ui.jqgrid.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/smz/person/list.css?v=${v}">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
</head>
<body>

<div class="container-full">
    <div class="menu-left-tree  fl">
        <div id="orgTree" class="ztree"></div>
    </div>
    <div class="l-r-content">
        <div class="ui-report">
            <!--更多查询条件-->
            <div class="form-inline response-head titlePanel" id="listTools">
                <button type="button" class="btn openSearchBtn" data-toggle="collapse"
                        data-target="#search-input">
                    查询条件 <span class="glyphicon glyphicon-menu-down"></span>
                </button>
                <div id="search-input" class="collapse">
                    <div class="ui-filter">
                        <label>姓名/身份证号:</label>
                        <input id="personName" placeholder="请输入姓名或身份证号" class="form-control" style="height: 28px" type="text" />
                    </div>
                    <div class="ui-filter">
                        <label>工种:</label>
                        <div class="btn-group queryCondition">
                            <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
                               data-value="" id="work">请选择</a>
                            <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                                    class="caret"></span></a>
                            <ul class="dropdown-menu work" >
                                <li><a data-value="">请选择</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="ui-filter">
                        <label>登记日期从:</label>
                        <input id="startDate" class="Wdate form-control w-110" type="text" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})"/> 到
                        <input id="endDate" class="Wdate form-control w-110" type="text" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})"/>
                    </div>
                    <div class="ui-filter">
                        <label>培训情况:</label>
                        <div class="btn-group queryCondition">
                            <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
                               data-value="0" id="train">请选择</a>
                            <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                                    class="caret"></span></a>
                            <ul class="dropdown-menu train" >
                                <li><a data-value="0">请选择</a></li>
                                <li><a data-value="1">已参加培训</a></li>
                                <li><a data-value="2">未参加培训</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="ui-filter" style="margin:10px 10px;">
                        <a id="search_link" class="btn btn-primary" data-toggle="collapse" data-target="#search-input"><i class="fa fa-search"></i>查询</a>
                    </div>
                    <div class="ui-filter" style="margin:10px 0">
                        <a id="search_refresh" class="btn btn-primary" data-toggle="collapse" data-target="#search-input"><i class="fa fa-refresh"></i> 全部</a>
                    </div>
                </div>
                <div class="tools-btn" id="tools-btn">
                    <div class="btn-group">
                        <a id="export_link" class="btn btn-uirole"><i class="fa fa-download"></i>导出</a>
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
    </div>
</div>

<script>
    var ctx = "${ctx}";
    var type = "scheduledPlan";
</script>
<script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script src="${ctx}/resources/static/plugins/jqgrid/js/jquery.jqGrid.src.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/list_area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/smz/person/list.js?v=${v}"></script>
<script>
    $(function(){
        smzPlist.createZtree();
        smzPlist.createTable();
        smzPlist.getWork();
        biyue.select();
        biyue.control();
        biyue.searchInit({
            select:{
                train:{
                    value:"0"
                }
            }
        });
    })
</script>
</body>
</html>
