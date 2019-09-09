<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>公司维护</title>
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
        <label class="layui-form-label" style="">公司名称</label>
        <div class="layui-input-inline">
            <input type="text" name="name" placeholder="请输入公司名称" autocomplete="off" class="layui-input" >
        </div>
        <button class="layui-btn layui-btn-primary search" id="search"><i class=" layui-icon"></i> 搜索</button>
        <button class="layui-btn layui-btn-primary more-hide" id="search-more">更多查询条件 <i
                class="fa  fa-angle-double-down"></i></button>
        <div class="search-more-box layui-anim layui-anim-upbit">
            <div class="layui-form-item area">
                <label class="layui-form-label">区域</label>
                <div class="layui-input-inline">
                    <select name="area" lay-search lay-filter="area">
                        <option value="">选择区域</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item site">
                <label class="layui-form-label">工地</label>
                <div class="layui-input-inline">
                    <select name="site" lay-search lay-filter="site">
                        <option value="">选择工地</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">类型</label>
                <div class="layui-input-inline">
                    <select name="type" lay-search lay-filter="type">
                        <option value="">选择类型</option>
                        <option value="1">专业分包公司</option>
                        <option value="2">劳务分包公司</option>
                        <option value="3">租赁公司</option>
                        <option value="4">建设单位</option>
                        <option value="5">施工单位</option>
                        <option value="6">监理单位</option>
                    </select>
                </div>
            </div>
        </div>
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
    {{# if(operationAuthority[i]==="edit"){num++}}
    <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="edit">编辑</a>
    {{# }}}
    {{# }}}
    {{# if(num===0){}}
    <a class="layui-btn layui-btn-disabled layui-btn-xs" lay-event="none"><i class="layui-icon layui-icon-tips"></i>无操作权限</a>
    {{# }}}
</script>
<script type="text/html" id="status">
    {{#  var type = d.type}}
    {{#  if(type === 1){}}
    专业分包公司
    {{#  } else if(type === 2){ }}
    劳务分包公司
    {{#  } else if(type === 3){ }}
    租赁公司
    {{#  } else if(type === 4){ }}
    建设单位
    {{#  } else if(type === 5){ }}
    施工单位
    {{#  } else if(type === 6){ }}
    监理单位
    {{#  } else { }}
    <span style="color: #FF5722">未知</span>
    {{#  } }}
</script>
<script>
    var ctx = "${ctx}",userId="${userId}";
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/projectCompany/projectCompany.js?v=${v}"></script>
<script type="text/javascript">
    $(function () {
        var area =biyue.area();
        area.area({
            type:"table",
            complete:function () {
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