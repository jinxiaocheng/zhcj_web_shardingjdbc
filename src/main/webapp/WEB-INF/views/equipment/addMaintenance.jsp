<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>添加设备维修保养</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet"/>
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.min.css?v=${v}" rel="stylesheet"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/scrollbar.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/dropdown.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/fileInput/fileinput.min.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/add.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/accident/add.css?v=${v}">
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

<div class="table-responsive form-table">
    <form id="personform" class="form-inline add_form" action="javascript:void(0)">
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
                <label class=" col-xs-4">名称</label>
                <input type="text" onclick='kk_searchPage(constructionId)' kk-prop="value"  class="form-control inputxt col-xs-8" id='name' placeholder="请选择设备名称" maxlength="4" auto_color_flag="true" datatype="zh2-4" errormsg="长度为2-4位中文" />
                <input  type="text" class="form-control inputxt" id='id' style="display:none"/>

            </div>
            <div class="form-group col-xs-4 " kk-contral="require">
                <label class=" col-xs-4">维修保养日期</label>
                <input  type="text" class="form-control inputxt col-xs-8" kk-prop="value" id="add_date"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  placeholder="请选择日期" maxlength="4" />
            </div>
            <div class="form-group col-xs-4  queryCondition" kk-contral="require">
                <label class=" col-xs-4">设备状态</label>
                <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="data-value" data-toggle="dropdown" aria-expanded="false"
                        data-value="" id="type">
                    --请选择--
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" style="min-width:108px;">
                    <li ><a data-value="">--请选择--</a></li>
                    <li ><a data-value="1">维修</a></li>
                    <li ><a data-value="2">保养</a></li>
                </ul>
            </div>
        </div>

        <div class="form-group row all_block check_result" >
            <label class="col-xs-1" style="vertical-align:middle">备注</label>
            <textarea rows="3"   class="textarea form-control" id='remark' maxlength="500" placeholder="请填写备注(最多输入500字)"  kk-RE="textLength/0-500" kk-name="备注" ></textarea>
        </div>
        <div class="form-group submit_img">
            <input id="file" type="file" class="file"  data-upload-url="#" multiple>
        </div>
    </form>
    <div style="height: 60px;width: 100%"></div>
    <footer>
        <a class="btn btn_submit btn-show-blue" id="submit">提&nbsp;交</a>
    </footer>
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

<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/fileInput/fileinput.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/fileInput/zh.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jqgrid/js/jquery.jqGrid.src.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/popup.js?v=${v}"></script>
<script type="text/javascript">
    var ctx = "${ctx}";
    var userId="${userId}";

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
    $(function(){
        init(function(){});
        $("#personform").kk_tableForm("#submit",my_ajax);
        function child(data){
            $("#name").val(data.name);
            $("#id").val(data.id);
        }
        window.child=child;

    });

    function my_ajax() {
        //设备名称
        var name = $("#name").val();
        //设备ID
        var equipmentId=$("#id").val();
        //设备状态
        var type=$("#type").attr("data-value");
        //维修保养日期
        var maintenanceDate=$("#add_date").val();
        //得到文件
        var files=$('#file').fileinput('getFileStack');
        //备注信息
        var remark = $("#remark").val();

        var formData = new FormData($("#personform")[0]);
        for(var i=0;i<files.length;i++){
            formData.append("files",files[i]);
        };
        formData.append("constructionId",constructionId)
        formData.append("createBy",userId)
        formData.append("maintenanceDate",maintenanceDate)
        formData.append("type",type)
        formData.append("equipmentId",equipmentId)
        if(remark&&remark!=""&&remark!=null){
            formData.append("remark",remark)
        }
        var url = ctx + "/equipment/saveMaintenance";
        $.ajax({
            url:url,                              //后台请求地址
            data:formData,                            //自定义参数
            processData: false,
            contentType: false,
            type:"post",                          //当要提交自定义参数时，这个参数要设置成post
            dataType: 'json',                     //服务器返回的数据类型
            success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                if(data.status == 1){
                    layer.alert("新增设备维修保养成功！",function(){
                        layer_close();
                    });

                } else {
                    layer.alert(data.msg);
                }
                $(".my_load").hide();
            },
            error: function (data, status, e) {   //提交失败自动执行的处理函数
                layer.alert(String(e));
                $(".my_load").hide();
            }
        });
    }

    //弹出选择页面界面
    function kk_searchPage(id){
        var _url = ctx + '/equipment/select?type=3&constructionId='+id;
        var _title = '请选择';
        layer_showOdd(_title, _url, {w:'500px',h:'400px'});
    };

    /*关闭弹出框口*/
    function layer_close(){
        biyue.layui_close();
    };
</script>

</body>
</html>




