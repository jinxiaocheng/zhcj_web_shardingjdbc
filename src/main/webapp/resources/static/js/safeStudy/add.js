
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
    var contactPerson = "";
    if(type == 1) {
        contactPerson = $("#contact_person").val();
    }

    var theme = $("#theme").val(),//主题
        startDate = $("#start_date").val(),// 开始时间
        endDate = $("#end_date").val(), // 结束时间
        place = $("#place").val(),  // 培训地点
        remark = $("#remark").val(),  // 备注
        files=$('#file').fileinput('getFileStack');//得到文件

    var formData = new FormData($("#form_data")[0]);
    formData.append("constructionId",constructionId)
    formData.append("type",type);
    formData.append("startDate",startDate);
    formData.append("endDate",endDate);
    formData.append("theme",theme);
    formData.append("place",place);
    for(var j in kk_id1){
        formData.append("personIds",kk_id1[j]);
    }
    for(var i=0;i<files.length;i++){
        formData.append("files",files[i]);
    }
    if(type == 1) {
        formData.append("contactPerson",contactPerson);
    }

    if(remark != "" && remark != null) {
        formData.append("remark",remark);
    }

    $.ajax({
        url: ctx+"/safeStudy/save",                //后台请求地址
        data: formData,                            //自定义参数
        processData: false,
        contentType: false,
        type: "post",                          //当要提交自定义参数时，这个参数要设置成post
        dataType: 'json',                     //服务器返回的数据类型
        success: function (data) {            //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
            if (data.status == 1) {
                layer.alert("提交成功！",function(){
                    window.layer_close();
                });

            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {      //提交失败自动执行的处理函数
            layer.alert(e);
        }
    });
}
