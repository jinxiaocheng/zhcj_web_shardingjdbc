var files = [];//图片文件

//提示框
$(".close").click(function () {
    $(".error").fadeOut("fast");
});

//图片上传前
$('#file').on('filepreupload', function (event, data, previewId, index) {
    files = data.files;
});

//点击提交
function  my_ajax() {

    var add_type = $("#documentType").attr("data-value"),//获取文档类型
        constructionId = $(".site>button").attr("constructionid"),//工地ID
        add_remark = $("#add_remark").val(),//备注
        files=$('#file').fileinput('getFileStack');//得到文件

    var formData = new FormData($("#form_data")[0]);
    formData.append("constructionId", constructionId)
    formData.append("typeId", add_type)

    if (add_remark != "" && add_remark != null && add_remark != undefined) {
        formData.append("remark", add_remark)
    }

    if (files == null || files.length == 0) {
        $(".error").fadeIn("fast");
        $(".error span").html("请选择上传的文件！");
        $(".my_load").hide();
        return
    }
    for (var i = 0; i < files.length; i++) {
        formData.append("files", files[i]);
    }


    $.ajax({
        url: ctx + "/document/bathSave",             //后台请求地址
        data: formData,                        //自定义参数
        processData: false,
        contentType: false,
        type: "post",                          //当要提交自定义参数时，这个参数要设置成post
        dataType: 'json',                     //服务器返回的数据类型
        success: function (data) {            //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
            if (data.status == 1) {

                layer.alert("提交成功！",function () {
                    layer_close();
                });

            } else {
                layer.alert(data.msg);
            }
            $(".my_load").hide();
        },
        error: function (data, status, e) {      //提交失败自动执行的处理函数
            layer.alert(e);
            $(".my_load").hide();
        }
    });
}
