var biyue = new biYue();
function id(a) {}
$("#file").fileinput({
    language: 'zh',
    theme: 'fa'
});
function createFile(initialPreview) {
    $('#file').fileinput('destroy');
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
            showDrag: false
        },
        initialPreview: initialPreview
    });
    var ccc=  $(".file-preview-thumbnails").html(),bool=true;
    $("#file").on('fileimageloaded',function (event,previewId) {
        if(bool){
            $(".file-preview-thumbnails").prepend(ccc);
            bool=!bool;
        }
        imgDel();
    });
    $("#file").on('filecleared',function (event,previewId) {
        ccc="";
    });
    function imgDel() {
        $(".a_image").parents(".file-preview-frame").find(".kv-file-remove").click(function () {
            $(this).parents(".file-preview-frame").remove();
            ccc="";
            $(".a_image").each(function () {
                ccc+=$(this).parents(".file-preview-frame")[0].outerHTML;
            })
        })
    }
    imgDel();

}
function my_ajax() {
    //通过form表单方式提交
    var formData = new FormData($("#form_data")[0]);
    var type = biyue.urlSearch().type;
    var id = biyue.urlSearch().id;
    if(type==="view"){
        var filePathList=[];
        if(!fileId) fileId=[];
        $(".file-drop-zone .a_image").each(function () {
           var id = $(this).attr("data-id");
           if(id){
               for(var i=0;i<fileId.length;i++){
                   if(fileId[i]===id){
                       fileId[i]=false;
                   }
               }
           }
       })
        for(var j=0;j<fileId.length;j++){
            if(fileId[j]){
                filePathList.push(fileId[j]);
            }
        }
        formData.append("filePathList",filePathList);
        formData.append("id",id);
    }

    //进场日期
    var approachDate = $("#approachDate").val();
    //材料
    var materialId = $('#selectMaterial').attr("data-value");
    //生产厂家
    var manufacturer = $("#manufacturer").val();
    //使用部位
    var usePosition = $("#usePosition").val();
    //数量
    var quantity = $("#quantity").val();
    var modelName=$("#modelName").val();
    var modelId=$("#modelName").attr("data-value");
    var unit=$("#unit").val();
    var reportNum = $("#reportNum").val();
    var verificationResults = $("#verificationResults").val();
    var remark = $("#remark").val();
    var weighbridgeId = $("#weighbridgeId").val();
    //得到文件
    var files=$('#file').fileinput('getFileStack');
    //现场验证结果
    var siteResult=$('[name="siteResult"]:checked').val();
    //试验结果
    var experimentResult=$('[name="experimentResult"]:checked').val();

    formData.append("siteResult",siteResult);
    formData.append("experimentResult",experimentResult);
    formData.append("constructionId",$(".site button").attr("constructionid"));
    formData.append("materialId",materialId);
    formData.append("manufacturer",manufacturer);
    formData.append("approachDate",approachDate);
    formData.append("usePosition",usePosition);
    formData.append("weighbridgeId",weighbridgeId);
    formData.append("quantity",quantity);
    formData.append("reportNum",reportNum);
    formData.append("modelName",modelName);
    formData.append("modelId",modelId);
    formData.append("unit",unit);
    formData.append("verificationResults",verificationResults);
    formData.append("remark",remark);

    for(var i=0;i<files.length;i++){
        formData.append("files",files[i]);
    }
   /* $(".a_image").each(function () {
        var src=$(this).attr("src").split("data:image/gif;base64,")[1];
        console.log(src);
        formData.append("base64ImageList",src);
    });*/

    var url = ctx + "/materialApproach/addMaterialApproachInfo";
    $.ajax({
        url:url,                              //后台请求地址
        data: formData,                            //自定义参数
        processData: false,
        contentType: false,                         //自定义参数
        type:"post",                          //当要提交自定义参数时，这个参数要设置成post
        dataType: 'json',                     //服务器返回的数据类型
        success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
            if(data.status == 1){
                if(type==="view"){
                    layer.alert("编辑材料进场成功！",function(){
                        layer_close();
                    });
                }else{
                    layer.alert("新增材料进场成功！",function(){
                        layer_close();
                    });
                }

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
var material = {
    getData:function () {
        $(".area button").attr('disabled',true);
        $(".site button").attr('disabled',true);
        var id = biyue.urlSearch().id;
        biyue.ajax({
            url:'/materialApproach/approachDetailInfo',
            data:{
                id:id
            },
            fun:function (data) {
                if(data.value){
                    data=data.value;
                    $(":input[name='siteResult'][value="+data.siteResult+"]").prop("checked",true);
                    $(":input[name='experimentResult'][value="+data.experimentResult+"]").prop("checked",true);
                    $("#selectMaterial").val(data.NAME).attr("data-value",data.materialId);
                    $("#time").val(data.createTime);
                    $("#remark").val(data.remark||"");
                    $("#manufacturer").val(data.manufacturer);
                    $("#approachDate").val(data.approachDate);
                    $("#usePosition").val(data.usePosition);
                    $("#quantity").val(data.quantity);
                    $("#modelName").val(data.model).attr("data-value",data.moderId);
                    $("#unit").val(data.unit);
                    $("#reportNum").val(data.reportNum);
                    $("#verificationResults").html(data.verificationResults||"");
                    $("#dropdownMenu3").html(data.territoryName+'<span class="caret"></span>').attr("areaId",data.territoryId);
                    $("#dropdownMenu4").html(data.constructionName+'<span class="caret"></span>').attr("constructionid",data.constructionId);

                    var initialPreview = [],fileId=[];
                    if(data.filePathList){
                        for(var i=0;i<data.filePathList.length;i++){
                            fileId.push(data.filePathList[i].id);
                            initialPreview.push('<img class="a_image" style="width: 100%;height: 100%;" data-id="'+data.filePathList[i].id+'" src="'+data.filePathList[i].name+'" alt="">')
                        }
                    }
                    window.fileId=fileId;
                    createFile(initialPreview)
                }
            }
        })
    }
}
/*关闭弹出框口*/
function layer_close(){
    var url = ctx + "/materialApproach/materialApproachList";
    parent.jQuery("#gridTable").jqGrid('setGridParam', {url: url}).trigger("reloadGrid");
    parent.layer.closeAll();
};