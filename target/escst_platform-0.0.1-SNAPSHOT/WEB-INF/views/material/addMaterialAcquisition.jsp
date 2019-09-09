<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>材料领用-新增</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.min.css?v=${v}" rel="stylesheet"/>
    <link type="text/css" href="${ctx}/resources/static/css/validform.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/tableform.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/add.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/fileInput/fileinput.min.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/ieControl.css?v=${v}">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
    <script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}" type="text/javascript"></script>
</head>
<body class="body_bottom">

<div class="table-responsive form-table">
    <form id="acquisitionForm">
        <div class="row add_list " >
            <div class="form-group col-xs-4 area " kk-contral="require">
                <label class=" col-xs-4">区域</label>
                <button class="btn btn-default dropdown-toggle col-xs-8 " kk-prop="areaId"  id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    请选择区域
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu3">
                </ul>
            </div>
            <div class="form-group col-xs-4 site" kk-contral="require">
                <label class=" col-xs-4">工地</label>
                <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="constructionid"  id="dropdownMenu4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    请选择工地
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu4">
                </ul>
            </div>

        </div>

        <div class="row add_list  " >
            <div class="form-group col-xs-4 " kk-contral="require">
                <label class=" col-xs-4">领用日期</label>
                <input  type="text" class="form-control inputxt col-xs-8" kk-prop="value" kk-time="day" id="recipientsDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  placeholder="请选择日期" readonly />
            </div>
            <div class="form-group col-xs-4 " kk-contral="require">
                <label class=" col-xs-4">材料名称</label>
                <input type="text" style="display: none" id="approachId">
                <input  type="text" kk-prop="value" class="col-xs-8 form-control inputxt getMaterialInfo" placeholder="点击选择材料名称" id='selectMaterial' readonly/>

            </div>
            <div class="form-group col-xs-4 " >
                <label class=" col-xs-4">规格型号</label>
                <input  type="text" kk-prop="value" class=" form-control inputxt col-xs-8" readonly="readonly" id='model'/>
            </div>
        </div>
        <div class="row add_list ">

            <div class="form-group col-xs-4 " >
                <label class=" col-xs-4">计量单位</label>
                <input  type="text" kk-prop="value" class=" form-control inputxt col-xs-8" readonly="readonly" id='measurementUnit'/>
            </div>
            <div class="form-group col-xs-4 " >
                <label class=" col-xs-4">生产厂家</label>
                <input  type="text" kk-prop="value" class=" form-control inputxt col-xs-8" readonly="readonly" id='manufacturer'/>
            </div>
            <div class="form-group col-xs-4 " >
                <label class=" col-xs-4">使用部位</label>
                <input kk-prop="value"  class=" form-control inputxt col-xs-8" readonly="readonly" id='usePosition'/>
            </div>
        </div>
        <div class="row add_list ">

            <div class="form-group col-xs-4 " >
                <label class=" col-xs-4">可用量</label>
                <input  type="number" kk-prop="value"  class=" form-control inputxt col-xs-8" readonly="readonly" id='availableQuantity'/>
            </div>
            <div class="form-group col-xs-4 " kk-contral="require">
                <label class=" col-xs-4">领用量</label>
                <input  type="number" kk-prop="value" placeholder="请输入数量"  class=" form-control inputxt col-xs-8"  id='quantity'/>
            </div>
            <div class="form-group col-xs-4 username " kk-contral="require">
                <label for="add_person" class="col-xs-4">领用人</label>
                <input type="text" class="form-control col-xs-8 selectList" kk-prop="data-value"  placeholder="点击选择领用人"  id="add_person" readonly>
            </div>
        </div>

        <div class="form-group row all_block check_result" >
            <label>备注</label>
            <textarea rows="3"  class="textarea form-control" maxlength="500" placeholder="请填写备注(最多输入500字)"  kk-RE="textLength/0-500" kk-name="备注" id='remark' ></textarea>
        </div>

        <div class="form-group submit_img">
	        <input id="file" type="file" class="file" data-upload-url="#" multiple>
	    </div>
        <div class="footer_btn"><a class="btn btn_submit btn-show-blue" id="materialApproachform_btn">提交</a></div>  
    </form>
</div>
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
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/fileInput/fileinput.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/fileInput/zh.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/selectList.js?v=${v}"></script>
<script type="text/javascript">



$(function(){
    init(materialInfoInit);
    $("#acquisitionForm").kk_tableForm("#materialApproachform_btn",my_ajax);
    $("#acquisitionForm").nowTime(); //加入当前时间
    /*关闭弹出框口*/
    function layer_close(){
    	var url = ctx + "/materialAcquisition/materialAcquisitionList"; 
    	parent.jQuery("#gridTable").jqGrid('setGridParam',{url:url}).trigger("reloadGrid");
    	var index = parent.layer.getFrameIndex(window.name);
    	parent.layer.close(index);
    };
});

// 设置图片
$("#file").fileinput({
    language: 'zh',
    theme: 'fa',
    uploadUrl: '#',
    showUpload:false,
    maxFileSize:51200,
    minFileSize:1,
    browseIcon:'',
    removeIcon:'',
    allowedFileExtensions: ["bmp", "jpg", "jpeg", "png", "gif"],
    fileActionSettings:{
        showUpload: false,
        showZoom: false,
        showDrag: false,
    }
});


function my_ajax() {
    var availableQuantity = parseInt($("#availableQuantity").val());
    var quantity = $("#quantity").val();//数量
    var personId = $('#add_person').attr("data-value");
    var constructionId = $(".site button").attr("constructionid");
    var approachId=$('#approachId').attr("data-value");
    //领用日期
    var recipientsDate = $("#recipientsDate").val();
    //材料名称
    var name = $("#selectMaterial").val();
    var materialId = $('#selectMaterial').attr("data-value");
    //规格型号
    var model = $("#model").val();
    //计量单位
    var  measurementUnit= $("#measurementUnit").val();
    //生产厂家
    var manufacturer = $("#manufacturer").val();
    //使用部位
    var usePosition = $("#usePosition").val();
    var remark = $("#remark").val();
    //得到文件
    var files=$('#file').fileinput('getFileStack');

    //通过form表单方式提交
    var formData = new FormData($("#form_data")[0]);
    formData.append("constructionId",constructionId);
    formData.append("approachId",approachId);
    formData.append("materialId",materialId);
    formData.append("name",name);
    formData.append("manufacturer",manufacturer);
    formData.append("personId",personId);
    formData.append("recipientsDate",recipientsDate);
    formData.append("model",model);
    formData.append("measurementUnit",measurementUnit);
    formData.append("usePosition",usePosition);
    formData.append("availableQuantity",availableQuantity);
    formData.append("quantity",quantity);
    formData.append("remark",remark);
    
    for(var i=0;i<files.length;i++){
        formData.append("files",files[i]);
    }

    if(parseFloat(availableQuantity)<parseFloat(quantity)){
        my_error('领用量不能大于可用量！')
        $(".my_load").hide();
        return;
    }

    var url = ctx + "/materialAcquisition/add";
    $.ajax({
        url:url,                              //后台请求地址
        data:formData,
        processData: false,
        contentType: false, //自定义参数
        type:"post",                          //当要提交自定义参数时，这个参数要设置成post
        dataType: 'json',                     //服务器返回的数据类型
        success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
            if(data.status == 1){
                alert("材料领用成功！");
                layer_close();
            } else {
                alert(data.msg);
            }
            $(".my_load").hide();
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            alert(e);
            $(".my_load").hide();
        }
    });
}

/*关闭弹出框口*/
function layer_close(){
   biyue.layui_close();
}
</script>
</body>
</html>




