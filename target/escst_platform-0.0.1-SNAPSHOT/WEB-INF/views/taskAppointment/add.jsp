<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>新增任务预约</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.min.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/scrollbar.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/dropdown.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/fileInput/fileinput.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/add.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/ieControl.css?v=${v}">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
</head>
<body>
<form class="form-inline add_form" id="form_data">

    <div class="row add_list" >
        <div class="form-group col-xs-4 area" kk-contral="require">
            <label  class="col-xs-4">区域</label>
            <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="areaId" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                请选择区域
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu3">
            </ul>
        </div>

        <div class="form-group col-xs-4 site" kk-contral="require">
            <label  class="col-xs-4">工地</label>
            <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="constructionid" id="dropdownMenu4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                请选择工地
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu4">
            </ul>
        </div>
    </div>

    <div class="row add_list">

        <div class=" form-group col-xs-4 es queryCondition"  kk-contral="require">
            <label class=" col-xs-4">预约设备</label>
            <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="data-value" data-toggle="dropdown" aria-expanded="false"
                    data-value="" id="equipmentType">
                --请选择--
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu equipmentType" style="min-width:108px;max-height: 250px;overflow: auto">
                <li ><a data-value="">--请选择--</a></li>
                <li ><a data-value="1">塔吊</a></li>
                <li ><a data-value="2">升降机</a></li>
            </ul>
        </div>
        <div class="form-group col-xs-4 date_time"  kk-contral="require">
            <label for="add_date" class="col-xs-4">预约时间</label>
            <input type="text" readonly class="form-control col-xs-8" kk-prop="value" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00'})" id="add_date" placeholder="请选择日期" required>
        </div>

        <div class="form-group col-xs-4 person queryCondition"  kk-contral="require">
            <label class=" col-xs-4 ">处理人</label>
            <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="data-value" data-toggle="dropdown" aria-expanded="false"
                    data-value="" id="handle">
                --请选择--
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu handle" aria-labelledby="dropdownMenu5">
            </ul>
        </div>
    </div>

    <div class="row add_list">
        <div class="form-group col-xs-8 es skewing" >
            <label class="col-xs-2">工程结构</label>
            <button onclick="showMenu(event); return false;" type="button"  class=" col-xs-10 btn btn-default dropdown-toggle" type="button" id="construction" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                <label>请选择工程结构</label>
                <span class="caret"></span>
            </button>
            <ul id="tree" class="k_dropdown ztree selectBox" aria-labelledby="dropdownMenu1">
            </ul>
        </div>
        <div class="form-group col-xs-4 username row" kk-contral="require" style="margin: 0" >
            <label for="add_person" class="col-xs-4">预约人</label>
        <input type="text" class="form-control col-xs-8 selectList" data-value="${entity.userId}" kk-prop="data-value" placeholder="点击选择预约人"  id="add_person" value="${entity.name}" readonly>
        </div>
    </div>

    <div class="row add_list ">
        <div class="form-group col-xs-4 sub" >
        <label class="col-xs-4">所属公司</label>
            <input type="text" class="form-control col-xs-8 selectList"  placeholder=""  data-value="${entity.projectCompanyId}" id="sub" value="${entity.projectCompanyName}" readonly>
        </div>
        <div class="form-group col-xs-4 item" >
            <label  class="col-xs-4">班组</label>
            <input type="text" class="form-control col-xs-8 selectList" placeholder=""  data-value="${entity.teamId}" value="${entity.teamName}" id="item" readonly>
        </div>

        <div class="form-group col-xs-4 usertel">
            <label for="add_tel" class="col-xs-4">预约人电话</label>
            <input type="email" class="form-control col-xs-8 selectList" placeholder="" id="add_tel" value="${entity.mobile}" readonly>
        </div>
    </div>
    <div class="row add_list">
        <div class="result form-group col-xs-4 es" kk-contral="require">
            <label class="col-xs-4">紧急任务</label>
            <ul class="isUrgentTask" result-id="" kk-prop="result-id">
                <li r-id="1">是</li>
                <li r-id="0">否</li>
            </ul>
        </div>
    </div>

    <div class="row add_list">
        <div class="form-group row all_block check_item" id="remark">
            <label class="col-xs-4" style="width: 12%;text-indent: 1em">备注信息</label>
            <textarea class="form-control" rows="3" style="width: 88%" maxlength="500" placeholder="请填写备注(最多输入500字)"  kk-RE="textLength/0-500" kk-name="备注"></textarea>
        </div>
    </div>

</form>

<div style="height: 60px;width: 100%"></div>
<footer>
    <a class="btn btn-info " id="submit">提&nbsp;交</a>
</footer>

<%--警告--%>
<div class="alert alert-danger alert-dismissable error">
    <button type="button" class="close" aria-hidden="true">
        &times;
    </button>
    <span>警告！请不要提交。</span>
</div>
<%--加载--%>
<div class="my_load">
    <img src="${ctx}/resources/static/images/ajax-loader.gif" alt="">
</div>

<script>
    var ctx = "${ctx}";
    var userId="${userId}"
</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.excheck-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/taskAppointment/add.js?v=${v}"></script>
<script>
    //获取当前日期
    var my_date=new Date();
    var get_hour=(my_date.getHours())<10?"0"+(my_date.getHours()):(my_date.getHours());
    var get_min=(my_date.getMinutes())<10?"0"+(my_date.getMinutes()):(my_date.getMinutes());
    var my_month=(my_date.getMonth()+1)<10?"0"+(my_date.getMonth()+1):(my_date.getMonth()+1);
    var my_day=my_date.getDate()<10?"0"+my_date.getDate():my_date.getDate();
    $("#add_date").val(my_date.getFullYear()+"-"+my_month+"-"+my_day+" "+get_hour+":"+get_min)
    $(function(){
        init(function (id) {
            my_day0(id);
            positionWorkType(id);
          //  selectListInit();
        });
        $("#form_data").kk_tableForm("#submit",my_ajax);
    })
</script>
</body>
</html>




