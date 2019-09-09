<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>实时数据-历史数据</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
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
<div class="biyue_body">
    <div class="biyue_search layui-form">
        <label class="layui-form-label">测点</label>
        <div class="layui-input-inline">
            <select name="survey" lay-search lay-filter="survey">
                <option value="">选择测点</option>
            </select>
        </div>
        <button class="layui-btn layui-btn-primary search" id="search"><i class=" layui-icon"></i> 搜索</button>
        <button class="layui-btn layui-btn-primary more-hide" id="search-more">更多查询条件 <i
                class="fa  fa-angle-double-down"></i></button>
        <div class="search-more-box layui-anim layui-anim-upbit">
            <div class="layui-form-item type">
                <label class="layui-form-label">预警类型</label>
                <div class="layui-input-inline">
                    <select name="type" lay-search lay-filter="type">
                        <option value=0>选择预警类型</option>
                        <option value=2>预警</option>
                        <option value=3>报警</option>
                        <option value=4>控制</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">开始时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="startTime" placeholder="请输入开始时间" lay-verify="required" autocomplete="off"
                           class="layui-input time1" id="start">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">结束时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="endTime" placeholder="请输入结束时间" lay-verify="required" autocomplete="off"
                           class="layui-input time2" id="end">
                </div>
            </div>
        </div>
    </div>
    <div class="biyue_tool">
        <button class="layui-btn layui-btn-primary" id="export_link"><i class="fa fa-download"></i>导出</button>
    </div>
    <div class="biyue_main">
        <table id="table-list" lay-filter="table-list"></table>
    </div>
    <button class="layui-btn layui-btn-primary" type="button" style="
                    position: absolute;right: 15px;bottom: 16px;height: 30px;line-height: 30px;" id="com-close">关闭</button>
</div>
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
<script type="text/html" id="yAngle">
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
    var ctx = "${ctx}", constructionId = "${constructionId}";
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/gzm/nowDataWarningData.js?v=${v}"></script>
<script>
    $(function () {
        biyue.popDefault();
        biyue.listCommon();
        biyueList.getTime(function (data) {
            var dataV = data.value;
            if(!dataV){dataV=[]}
            $('[name="startTime"]').val(dataV.startTime||"");
            $('[name="endTime"]').val(dataV.endTime||"");
            biyue.timeSD({
                format: "yyyy-MM-dd HH",
                valueS: dataV.startTime||"",
                valueE: dataV.endTime||""
            });
            biyueList.createTable();
        });
        biyueList.survey();

    })
</script>
</body>
</html>
