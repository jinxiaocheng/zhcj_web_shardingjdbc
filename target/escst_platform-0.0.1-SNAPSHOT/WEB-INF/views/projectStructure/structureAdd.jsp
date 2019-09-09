<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>新增工程结构</title>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/layui/css/layui.css?v=${v}">
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/validform.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/tableform.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/projectStructure/structureAdd.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
</head>
<body>
<div class="list">
    <p class="hint">（<span style="color: #666666">操作指南：</span>选择 <span>区域、工地</span>会生成相应的 <span>工程结构</span>，
                    <span>鼠标</span>放置在<span>检查部位名称</span>之上，则会出现相应的<span>操作按钮</span>（如：新增，编辑，删除））
    </p>
    <div class="list_name">
        <div class="ui-filter area">
            <label>区域:</label>
            <c:choose>
                <c:when test="${requestScope.type == 1}">
                    <div class="btn-group queryCondition">
                        <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
                           data-value="" id="area">请选择</a>
                        <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                                class="caret"></span></a>
                        <ul class="dropdown-menu" style="min-width:108px;max-height:448px;overflow: auto">
                        </ul>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="btn-group queryCondition">
                        <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
                           data-value="" >${areaName}</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="ui-filter site">
            <label>工地:</label>
            <div class="btn-group queryCondition">
                <c:choose>
                <c:when test="${requestScope.type == 1}">
                    <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
                       data-value="" id="site">请选择</a>
                    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                            class="caret"></span></a>
                    <ul class="dropdown-menu" style="min-width:108px;max-height:448px;overflow: auto">
                    </ul>
                </c:when>
                <c:otherwise>
                    <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
                       data-value="" id="site">${constructionName}</a>
                </c:otherwise>
                </c:choose>
            </div>
        </div>
        <p>检查部位：</p>
    </div>
    <div class="list_content">
        <div class="Tree" style="width: 500px; height: 250px; float: left;">
            <ul id="tree" class="ztree"></ul>
        </div>
    </div>
</div>
<div class="content">
    <form id="roleForm">
        <div class="footer_btn">
            <a class="btn btn_submit btn-show-blue" id="my_ture">提交</a>
        </div>
    </form>
</div>
<%--加载--%>
<div class="my_load">
    <img src="${ctx}/resources/static/images/ajax-loader.gif" alt="">
</div>

<script>
    var ctx = "${ctx}";
    var userId="${userId}";
    var cId = "${constructionId}";
    var type="${type}";

</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.excheck-3.5.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.exedit-3.5.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/list_area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/projectStructure/structureAdd.js?v=${v}"></script>
<script type="text/javascript">
    $(document).ready(function(){
        init(treeAjax);
        if(type==2){
            treeAjax(cId);
        }
    });
</script>

</body>
</html>