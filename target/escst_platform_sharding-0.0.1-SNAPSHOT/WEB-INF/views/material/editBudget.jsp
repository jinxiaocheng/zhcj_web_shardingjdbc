<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>材料预算-编辑</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.min.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/validform.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/tableform.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/add.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/ieControl.css?v=${v}">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
</head>
<body class="body_bottom">

<form class="form-inline add_form" id="materialForm">
    <div class="row add_list ">
        <div class="form-group col-xs-4" >
            <label class=" col-xs-4">区域</label>
            <input type="text" class="form-control col-xs-8 "  id="area" placeholder="区域" value="${map.terrName}" readonly>
        </div>
        <div class="form-group col-xs-4" >
            <label class=" col-xs-4">工地</label>
            <input type="text" class="form-control col-xs-8 " value="${map.constructionName}"  id="site" placeholder="工地" readonly>
        </div>
    </div>

    <div class="row add_list ">
        <div class="form-group col-xs-4" >
            <label class=" col-xs-4">材料名称</label>
            <input type="text" class="form-control col-xs-8 " value="${map.name}" id="materialName" placeholder="材料名称" readonly>
        </div>
        <div class="form-group col-xs-4" >
            <label class=" col-xs-4">规格型号</label>
            <input type="text" class="form-control col-xs-8 " value="${map.modelName}"  id="materialModel" placeholder="规格型号" readonly>
        </div>
        <div class="form-group col-xs-4" kk-contral="require">
            <label class=" col-xs-4">数量</label>
            <input type="text" class="form-control col-xs-8 " value="${map.quantity}" kk-prop="value" id="materialNum" kk-RE="num" kk-name="数量" placeholder="请输入数量">
        </div>
    </div>

    <div class="row add_list ">
        <div class="form-group col-xs-4" >
            <label class=" col-xs-4">计量单位</label>
            <input type="text" class="form-control col-xs-8 " value="${map.unit}"  id="materialUnit" placeholder="计量单位" readonly>
        </div>
        <div class="form-group col-xs-4" kk-contral="require">
            <label class=" col-xs-4">材料总价(万元)</label>
            <input type="text" class="form-control col-xs-8" value="${map.amount}" kk-prop="value" kk-RE="num" kk-name="材料总价" id="materialPrice" placeholder="请输入材料总价">
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
<script>
    var ctx = "${ctx}";
    var userId= "${userId}";
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript">


    $(function(){
        $("#materialForm").kk_tableForm("#materialApproachform_btn",my_ajax);
    });

    function my_ajax() {
        var url = ctx + "/materialBudget/edit";
        $.ajax({
            type: "post",
            url:url,                              //后台请求地址
            data: {
                id:"${map.id}",
                quantity:$("#materialNum").val(),
                amount:$("#materialPrice").val()
            },
            dataType: "json",                     //服务器返回的数据类型
            success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                if(data.status == 1){
                    layer.alert("修改材料预算成功！",function(){
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
        biyue.layui_close();
    };
</script>
</body>
</html>

