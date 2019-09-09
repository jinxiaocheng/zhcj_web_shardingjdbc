<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>文档新增</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.min.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/scrollbar.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/dropdown.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/fileInput/fileinput.min.css?v=${v}">
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
    <h2 class="add_title">
        <p>基本信息</p>
    </h2>
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
    <div class="row add_list" >
        <div class="form-group col-xs-8 skewing " kk-contral="require" >
            <label class="col-xs-2">文档类型</label>
            <button  type="button" id="documentType" kk-prop="data-value" class=" col-xs-10 btn btn-default dropdown-toggle"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                <label class="ztreeContent">请选择文档类型</label>
                <span class="caret"></span>
            </button>
            <ul id="tree" class="k_dropdown ztree selectBox documentType" aria-labelledby="dropdownMenu1">
            </ul>
        </div>
    </div>

    <h2 class="add_title">
        <p>添加附件 (必选)</p>
    </h2>
    <div class="form-group submit_img">
        <input id="file" type="file" class="file" data-allowed-file-extensions='["bmp", "jpg", "jpeg", "png", "gif", "office", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "pdf"]' data-upload-url="#" multiple>
    </div>

    <h2 class="add_title">
        <p>备注</p>
    </h2>
    <div class="form-group all_block check_item" style="width: 100%">
        <textarea class="form-control" style="width: 100%" rows="3" maxlength="500" placeholder="描述文档备注(最多输入500字)"  kk-RE="textLength/0-500" kk-name="备注" id="add_remark"></textarea>
    </div>

</form>
<div style="height: 60px;width: 100%"></div>
<footer>
    <a class="btn btn_submit btn-show-blue" id="submit">提&nbsp;交</a>
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
    var userId = "${userId}"
</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}" ></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/fileInput/fileinput.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/fileInput/zh.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.excheck-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/ztreeDropDownBox.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/document/add.js?v=${v}"></script>
<script>
    $(function(){
        function id(a) {
            //a代表工地id，将你们需要执行的函数放入其中
            check_result(a);
        }
        init(id);
        $("#form_data").kk_tableForm("#submit",my_ajax);
        //点击关闭
        $(".delClose").on("click", function () {
            layer_close();
        });
    })

    var documentType=new ztreeDropDownBox('documentType'); // 初始化树形图下拉框（文档类型）

    $("#documentType").click(function (event) {
        documentType.showMenu(event);
    })

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
        allowedFileExtensions: ["bmp", "jpg", "jpeg", "png", "gif", "office", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "pdf"],
        fileActionSettings:{
            showUpload: false,
            showZoom: false,
            showDrag: false,
        }
    });

    /*关闭弹出框口*/
    function layer_close() {
        var url = ctx + "/document/queryByConstructionId";
        parent.jQuery("#gridTable").jqGrid('setGridParam', {url: url}).trigger("reloadGrid");
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    };

    function ztreeSet(){
        return {
            view: {
                showLine: false,
                selectedMulti: false
            },
            check: {
                enable: false
            },
            data: {
                simpleData: {
                    enable:true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: 0
                }
            },
            callback: {
                beforeClick: documentType.beforeClick,
                onClick: documentType.onClick
            }
        }
    }
    //点击文档类型
    function check_result(id) {
        $.ajax({
            type: "post",
            data:{
              constructionId:id
            },
            url: ctx + '/document/docTypeTree',
            success: function (data) {
                if(data.status == 1){
                    if(data.value){
                        var cr_data=data.value;
                        $.fn.zTree.init($("#tree"), ztreeSet(),cr_data);
                    }
                } else {
                    console.log(data.msg);
                }
            },
            error: function (data, status, e) {   //提交失败自动执行的处理函数
                layer.alert(String(e));
            }
        });
    }

</script>
</body>
</html>




