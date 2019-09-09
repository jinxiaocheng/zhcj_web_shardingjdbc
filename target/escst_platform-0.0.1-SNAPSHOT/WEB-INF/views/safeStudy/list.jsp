<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>安全培训</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/layui/css/layui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/layui_list.css?v=${v}"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
</head>

<body class="body_bg">
<div class="biyue_body">
    <div class="biyue_search layui-form">
        <div class="layui-inline area">
            <label class="layui-form-label" style="line-height: 43px;width: 40px;">区域</label>
            <div class="layui-input-inline">
                <select name="area" lay-search lay-filter="area">
                    <option value="">选择区域</option>
                </select>
            </div>
        </div>
        <div class="layui-inline site">
            <label class="layui-form-label" style="line-height: 43px;width: 40px;">工地</label>
            <div class="layui-input-inline">
                <select name="site" lay-search lay-filter="site">
                    <option value="">选择工地</option>
                </select>
            </div>
        </div>
        <button class="layui-btn layui-btn-primary search area" id="search"><i class=" layui-icon"></i> 搜索</button>
    </div>
    <div class="biyue_tool">
        <button class="layui-btn layui-btn-primary" id="add_link"><i class="fa fa-plus"></i>新增</button>
    </div>
    <div class="biyue_main">
        <table id="table-list" lay-filter="table-list"></table>
    </div>
</div>
<script type="text/html" id="barDemo">
    {{# var operationAuthority = biyue.urlData.operationAuthority.split(","),num=0}}
    {{#  var type = d.status}}
    {{# for(var i in operationAuthority){}}
    {{# if(operationAuthority[i]==="view"){num++}}
    <a class="layui-btn layui-btn-xs" lay-event="view">查看</a>
    {{# }}}
    {{# }}}
    {{# if(num===0){}}
    <a class="layui-btn layui-btn-disabled layui-btn-xs" lay-event="none"><i class="layui-icon layui-icon-tips"></i>无操作权限</a>
    {{# }}}
</script>
<script type="text/html" id="status">
    {{#  var type = d.status}}
    {{#  if(type === 1){}}
    <span style="">培训地点</span>
    {{#  } else if(type === 2){ }}
    <span style="">演习地点</span>
    {{#  } }}
</script>
<script>
    var ctx = "${ctx}", userId = "${userId}", type = "${type}";
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/safeStudy/safeStudy.js?v=${v}"></script>
<script type="text/javascript">
    $(function () {
        var area = biyue.area();
        area.area({
            type: "table",
            complete: function () {
                biyueList.createTable();
            }
        });
        biyue.listCommon();
        biyue.biyueControl();
        biyueList.toolsBtn();
    });
</script>

</body>
</html>