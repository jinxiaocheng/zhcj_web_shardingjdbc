<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>实时数据</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/layui/css/layui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/layui_list.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/gzm/nowData.css?v=${v}"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
</head>
<body class="biyue-bg">
<div class="biyue-left-ztree biyue-box">
    <ul id="menuTree" class="ztree"></ul>
</div>
<div class="biyue-right-table biyue-box"><div class="biyue_body">
    <div class="biyue_search layui-form">

    </div>
    <div class="biyue_tool">
        <button class="layui-btn layui-btn-primary" id="warningData"><i class="fa fa-warning"></i>预警数据</button>
        <button class="layui-btn layui-btn-primary" id="lineChart"><i class="fa fa-line-chart"></i>趋势图</button>
        <button class="layui-btn layui-btn-primary" id="historyData"><i class="fa fa-history"></i>历史数据</button>
        <button class="layui-btn layui-btn-primary" id="testPicture"><i class="fa fa-picture-o"></i>测点部署图</button>
        <button class="layui-btn layui-btn-primary" id="viewWarning"><i class="fa fa-search"></i>查看阈值</button>
    </div>
    <div class="biyue_main">
        <table id="table-list" lay-filter="table-list"></table>
    </div>
</div></div>
<script type="text/html" id="xAngle">
    {{#  var val = d.xAngle,type = d.xType}}
    {{#  if(type === 2){}}
    <span style="color: #1E9FFF">{{val}}</span>
    {{#  } else if(type === 3){ }}
    <span style="color: #FFB800">{{val}}</span>
    {{#  } else if(type === 4){ }}
    <span style="color: #FF5722">{{val}}</span>
    {{#  } else { }}
    <span>{{val}}</span>
    {{#  } }}
</script>
<script type="text/html" id="status">
    {{#  var val = d.yAngle,type = d.yType}}
    {{#  if(type === 2){}}
    <span style="color: #1E9FFF">{{val}}</span>
    {{#  } else if(type === 3){ }}
    <span style="color: #FFB800">{{val}}</span>
    {{#  } else if(type === 4){ }}
    <span style="color: #FF5722">{{val}}</span>
    {{#  } else { }}
    <span>{{val}}</span>
    {{#  } }}
</script>
<script type="text/html" id="kpa">
    {{#  var val = d.kpa,type = d.kpaType}}
    {{#  if(type === 2){}}
    <span style="color: #1E9FFF">{{val}}</span>
    {{#  } else if(type === 3){ }}
    <span style="color: #FFB800">{{val}}</span>
    {{#  } else if(type === 4){ }}
    <span style="color: #FF5722">{{val}}</span>
    {{#  } else { }}
    <span>{{val}}</span>
    {{#  } }}
</script>
<script type="text/html" id="displace">
    {{#  var val = d.displace,type = d.displaceType}}
    {{#  if(type === 2){}}
    <span style="color: #1E9FFF">{{val}}</span>
    {{#  } else if(type === 3){ }}
    <span style="color: #FFB800">{{val}}</span>
    {{#  } else if(type === 4){ }}
    <span style="color: #FF5722">{{val}}</span>
    {{#  } else { }}
    <span>{{val}}</span>
    {{#  } }}
</script>
<script type="text/html" id="electric">
    {{#  var val = d.electric,type = d.electricType}}
    {{#  if(type === 2){}}
    <span style="color: #1E9FFF">{{val}}</span>
    {{#  } else if(type === 3){ }}
    <span style="color: #FFB800">{{val}}</span>
    {{#  } else if(type === 4){ }}
    <span style="color: #FF5722">{{val}}</span>
    {{#  } else { }}
    <span>{{val}}</span>
    {{#  } }}
</script>
<script type="text/html" id="temperature">
    {{#  var val = d.temperature,type = d.temperatureType}}
    {{#  if(type === 2){}}
    <span style="color: #1E9FFF">{{val}}</span>
    {{#  } else if(type === 3){ }}
    <span style="color: #FFB800">{{val}}</span>
    {{#  } else if(type === 4){ }}
    <span style="color: #FF5722">{{val}}</span>
    {{#  } else { }}
    <span>{{val}}</span>
    {{#  } }}
</script>
<script>
    var ctx = "${ctx}",userId = "${userId}";
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/gzm/nowData.js?v=${v}"></script>
<script>
    $(function () {
        biyue.listCommon();
        biyue.biyueControl();
        biyueList.getZtree();
        biyueList.toolsBtn();

    })
</script>
</body>
</html>
