<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>新增任务派发</title>
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
    <div class="row add_list">
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

        <div class="form-group col-xs-4 person" kk-contral="require">
            <label class="col-xs-4">派发人</label>
        <c:choose>
            <c:when test="${!empty map}">
            <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="person_id"  person_id="${map['id']}" type="button" id="examinerName" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                ${map['name']}
                <span class="caret"></span>
            </button>
            </c:when>
            <c:otherwise>
            <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="person_id" type="button" id="examinerName" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                请选择派送人
            <span class="caret"></span>
            </button>
            </c:otherwise>
        </c:choose>

            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" style="max-height: 300px;overflow: auto;">
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
            <label for="add_person" class="col-xs-4">处理人</label>
            <input type="text" class="form-control col-xs-8 selectPerson" kk-prop="data-value" placeholder="点击选择处理人"  id="add_person" readonly>
        </div>

    </div>
    <div class="row add_list ">
        <div class="form-group col-xs-4 sub" >
            <label class="col-xs-4">所属公司</label>
            <input type="text" class="form-control col-xs-8 "  placeholder="点击选择所属公司"  id="sub" readonly>
        </div>

        <div class="form-group col-xs-4 item" >
            <label  class="col-xs-4">班组</label>
            <input type="text" class="form-control col-xs-8 " placeholder="点击选择班组"  id="item" readonly>
        </div>

        <div class="form-group col-xs-4 usertel">
            <label for="add_tel" class="col-xs-4">电话</label>
            <input type="email" class="form-control col-xs-8 " placeholder="点击选择电话" id="add_tel"  readonly>
        </div>
    </div>
    <div class="form-group row all_block check_item" id="remark">
        <label >备注信息</label>
        <textarea class="form-control" rows="3" maxlength="500" placeholder="请填写备注(最多输入500字)"  kk-RE="textLength/0-500" kk-name="备注"></textarea>
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
    var userId = "${userId}";  //当前用户ID;
</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}" ></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.excheck-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/task/add.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/selectList.js?v=${v}"></script>
<script>
    $(function(){
        init(function (id) {
            my_day0(id);
            person(id);
            selectListInit();
        });

        //获取联系人、分包公司、班组、联系电话、岗位/工种
        $(".selectPerson").click(function () {
            var url = ctx + '/person/toSelectList?constructionId='+constructionId+'&type=2';
            var title = '选择人员';
            layer_showOdd(title, url, {w:'90%',h:'90%'});
        });

        $("#form_data").kk_tableForm("#submit",my_ajax);
    })
</script>
</body>
</html>




