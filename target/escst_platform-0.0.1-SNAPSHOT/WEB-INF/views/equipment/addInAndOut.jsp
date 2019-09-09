<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>添加设备进场</title>
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
</head>

<body class="body_bottom">

<div class="table-responsive form-table ">
    <form id="personform" class="form-inline add_form" action="javascript:void(0)">
        <div class="row add_list ">
            <div class="form-group col-xs-4 area " kk-contral="require">
                <label class=" col-xs-4">区域</label>
                <button class="btn btn-default dropdown-toggle col-xs-8 " kk-prop="areaId"  id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    请选择区域
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu3" style="max-height:248px;overflow: auto">
                </ul>
            </div>
            <div class="form-group col-xs-4 site" kk-contral="require">
                <label class=" col-xs-4">工地</label>
                <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="constructionid"  id="dropdownMenu4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                请选择工地
                <span class="caret"></span>
            </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu4" style="max-height:248px;overflow: auto">
                </ul>
            </div>
        </div>

        <div class="row add_list  " >
            <div class="form-group col-xs-4 " kk-contral="require">
                <label class=" col-xs-4">名称</label>
                <input type="text"  kk-prop="value"  class="form-control inputxt col-xs-8" id='name' placeholder="请输入设备名称"  />
            </div>
            <div class="form-group col-xs-4 " kk-contral="require">
                <label class=" col-xs-4">编号</label>
                <input  type="text" class="form-control inputxt col-xs-8" kk-prop="value" id='number' placeholder="请输入设备编号"  />

            </div>
        </div>

        <div class="row add_list ">
            <div class="form-group col-xs-4 " kk-contral="require">
                <label class=" col-xs-4">型号</label>
                <input  type="text" kk-prop="value" placeholder="请输入型号" class=" form-control inputxt col-xs-8" id='model'/>
            </div>
            <div class="form-group col-xs-4 " kk-contral="require">
                <label class="col-xs-4">设备提供商</label>
                <input  type="text" kk-prop="value" placeholder="请输入设备提供商" class="form-control inputxt col-xs-8" id='manufacturer' />

            </div>
            <div class="form-group col-xs-4 " >
                <label class=" col-xs-4">租赁公司</label>
                <input  type="text" class="form-control inputxt col-xs-8" placeholder="请输入租赁公司" id='lessingCompany' />

            </div>
        </div>

        <div class="row add_list" >
            <div class="form-group col-xs-4  queryCondition" kk-contral="require">
                <label class=" col-xs-4">设备状态</label>
                <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="data-value" data-toggle="dropdown" aria-expanded="false"
                   data-value="" id="type">
                    --请选择--
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" style="min-width:108px;">
                    <li ><a data-value="">--请选择--</a></li>
                    <li ><a data-value="1">进场</a></li>
                    <li ><a data-value="2">出场</a></li>
                </ul>
            </div>
            <div class="form-group col-xs-4  person" >
                <label class=" col-xs-4">责任人</label>
                <button class="btn btn-default dropdown-toggle col-xs-8" type="button" id="examinerName" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    请选择责任人
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" style="max-height: 250px;overflow: auto;">
                </ul>
            </div>
        </div>

        <div class="form-group row all_block check_result" >
            <label>备注</label>
            <textarea rows="3"  class="textarea form-control" maxlength="500" placeholder="请填写备注(最多输入500字)"  kk-RE="textLength/0-500" kk-name="备注" id='remark' ></textarea>
        </div>

        <div class="form-group submit_img">
            <input id="file" type="file" class="file"  data-upload-url="#" multiple>
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
</div>

<script>
    var ctx = "${ctx}";
    var userId="${userId}";
    var changeAreaId=null;
    var changeType=null;
    var search=window.location.search.replace("?","").split("&");
    var searchObj={};
    for(var i in search){
        searchObj[search[i].split("=")[0]]=search[i].split("=")[1];
    }
</script>
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
<script type="text/javascript" src="${ctx}/resources/static/plugins/jqgrid/js/jquery.jqGrid.src.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/equipment/add.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/popup.js?v=${v}"></script>
<script type="text/javascript">

    $(function(){

        //判断新增还是修改
        if(searchObj.status){  //修改
            changeType=1;
            $('.submit_img').hide();
            var rowData = parent.check_data;
            //区域
            $(".area  button").attr('areaId',rowData.areaId)
                .html(rowData.belongAreaName+'<span class="caret"></span>');
            changeAreaId=rowData.areaId;
            $(".area  button").attr('disabled',true);
            //工地
           $(".site button").attr('constructionid',rowData.constructionId)
                .html(rowData.constructionName+'<span class="caret"></span>');
            constructionId=rowData.constructionId;
            $(".site  button").attr('disabled',true);
            //设备名称
            $("#name").val(rowData.name);
            //设备编号
            $("#number").val(rowData.number);
            //规格型号
            $("#model").val(rowData.model);
            //厂家
            $("#manufacturer").val(rowData.manufacturer);
            //租赁公司
            $("#lessingCompany").val(rowData.leasingCompany);
            //设备状态
            $("#type").parent().find('a[data-value="'+rowData.type+'"]').click();
            //备注信息
            $("#remark").val(rowData.remark);
            //责任人
            if(rowData.personId){
                $(".person button").attr('data-value',rowData.personId)
                    .html(rowData.personLiable+'<span class="caret"></span>');
            }

            initCh(function(id){
                person(id);
            })
        }else{  //新增
            init(function(id){
                person(id);
            });
        }

     $("#personform").kk_tableForm("#submit",my_ajax);
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
        //设备名称
        var name = $("#name").val();
        //设备编号
        var number = $("#number").val();
        //规格型号
        var model = $("#model").val();
        //厂家
        var  manufacturer= $("#manufacturer").val();
        //租赁公司
        var leasingCompany = $("#lessingCompany").val();
        //设备状态
        var type=$("#type").attr("data-value");
        //得到文件
        var files=$('#file').fileinput('getFileStack');
        //备注信息
        var remark = $("#remark").val();
        //责任人id
        var personId= $(".person button").attr('data-value')||"";
        var formData = new FormData($("#personform")[0]);
        for(var i=0;i<files.length;i++){
            formData.append("files",files[i]);
        };
        if(searchObj.status){
            formData.append("equipmentId", parent.rowData.equipmentId);
            formData.append("status",1)
        }else{
            formData.append("equipmentId", "");
        }
        formData.append("constructionId",constructionId)
        formData.append("createBy",userId)
        formData.append("name",name)
        formData.append("number",number)
        formData.append("model",model)
        formData.append("manufacturer",manufacturer)
        formData.append("leasingCompany",leasingCompany)
        formData.append("type",type)
        formData.append("personId",personId)
        formData.append("remark",remark)


        var url = ctx + "/equipment/saveInOutRecord";
        $.ajax({
            url:url,                              //后台请求地址
            data:formData,                            //自定义参数
            processData: false,
            contentType: false,
            type:"post",                          //当要提交自定义参数时，这个参数要设置成post
            dataType: 'json',                     //服务器返回的数据类型
            success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                if(data.status == 1){
                    if(searchObj.status){
                        layer.alert("编辑设备进出场成功！",function(){
                            layer_close();
                        });
                    }else{
                        layer.alert("新增设备进出场成功！",function(){
                            layer_close();
                        });
                    }

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

    function child(data){
        $("#name").val(data.name);
        $("#number").val(data.number);
        $("#model").val(data.model);
        $("#manufacturer").val(data.manufacturer);
        $("#lessingCompany").val(data.leasingCompany);
        $("#type").val(data.type);
        $("#personName").val(data.personId);
        $("#remark").val(data.remark);
    }
    window.child=child;

    /*关闭弹出框口*/
    function layer_close(){
        biyue.layui_close();
    };




</script>

</body>
</html>




