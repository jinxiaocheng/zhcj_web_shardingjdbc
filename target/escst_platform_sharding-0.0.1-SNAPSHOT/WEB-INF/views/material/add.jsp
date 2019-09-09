<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>材料维护</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.min.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/validform.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/tableform.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/add.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/ieControl.css?v=${v}">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
    <script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}" type="text/javascript"></script>
</head>
<body class="body_bottom">

<form class="form-inline add_form" id="materialForm">
    <div class="row add_list ">
        <div class="form-group row col-xs-10" kk-contral="require">
            <label class=" col-xs-4">材料名称</label>
            <input type="text" class="form-control col-xs-8" kk-prop="value" id="name" placeholder="请输入名称">
        </div>
    </div>

    <div class="row add_list ">
        <div class="form-group row col-xs-10" kk-contral="require">
            <label class=" col-xs-4">型号</label>
            <input type="text" class="form-control col-xs-8" kk-prop="value" id="modelName" placeholder="请输入型号">
        </div>
    </div>

    <div class="row add_list ">
        <div class="form-group row col-xs-10" kk-contral="require">
            <label class=" col-xs-4">计量单位</label>
            <input type="text" class="form-control col-xs-8" kk-prop="value" id="unit" placeholder="请输入计量单位">
        </div>
    </div>

    <div class="footer_btn"><a class="btn btn_submit btn-show-blue" id="materialApproachform_btn">提交</a></div>
</form>
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
<script type="text/javascript" src="${ctx}/resources/static/js/common/area.js?v=${v}"></script>
<script type="text/javascript">
var ctx = "${ctx}";
var userId= "${userId}";

//提示框
$(".close").click(function(){
    $(".error").fadeOut("fast");
});

function my_error(text) {
    $(".error").fadeIn("fast");
    $(".error span").html(text);
    setTimeout(function(){
        $(".error").fadeOut("fast");
    },1000)
}
$(function(){
    $("#materialForm").kk_tableForm("#materialApproachform_btn",my_ajax);
    /*关闭弹出框口*/
    function layer_close(){
    	var url = ctx + "/materialAcquisition/materialAcquisitionList"; 
    	parent.jQuery("#gridTable").jqGrid('setGridParam',{url:url}).trigger("reloadGrid");
    	var index = parent.layer.getFrameIndex(window.name);
    	parent.layer.close(index);
    };
});

function my_ajax() {
	var name = $("#name").val();
	var unit = $("#unit").val();
	 
    var url = ctx + "/material/add";
    $.ajax({
    	type: "post",
        url:url,                              //后台请求地址
        data: {
            name:$("#name").val(),
            unit:$("#unit").val(),
            modelName:$("#modelName").val()
        },
        dataType: "json",                     //服务器返回的数据类型
        success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
            if(data.status == 1){
                layer.alert("新增材料成功！",function(){
                    layer_close();
                });

            } else {
                layer.alert(data.msg);
            }
            $(".my_load").hide();
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.alert(e);
            $(".my_load").hide();
        }
    });
}

/*关闭弹出框口*/
function layer_close(){
    var url = ctx + "/material/listData";
    parent.jQuery("#gridTable").jqGrid('setGridParam', {url: url}).trigger("reloadGrid");
    parent.layer.closeAll();
};
</script>
</body>
</html>

