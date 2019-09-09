var item_data=[],//班组数据
    bool=true,
    files=[];//图片文件
var setting = {
    view: {
        showLine: true,
        selectedMulti: false //不可多选
    },
    data: {
        simpleData: {
            enable:true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: 0
        }
    },
    async:{
        autoParam:["id=catalogID"],
        otherParam:["type","M"],
        enable:true,
        dataType:"json",
        type:"get",
        url:"${ctx}/document/queryCatalogTree"
    },
    check: {
        enable: false
    },
    callback: {
        beforeClick: beforeClick,
        onClick: onClick
    }
};

function beforeClick(treeId, treeNode) {
    var check = (treeNode && !treeNode.isParent);
    return check;
}

function onClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("tree"),
        nodes = zTree.getSelectedNodes(),
        v = "",
        k_id="";
    nodes.sort(function compare(a,b){return a.id-b.id;});
    for (var i=0, l=nodes.length; i<l; i++) {
        v += nodes[i].name + ",";
    }
    if (v.length > 0 ) v = v.substring(0, v.length-1);
    var cityObj = $("#construction>label");
    cityObj.html(v);
    cityObj.attr("k_id",treeNode.id);
    hideMenu();
    my_data1(constructionId,treeNode.id);
}

//证书类别
function initCertificateType() {
    $.ajax({
        type: "post",
        url: ctx + "/dictionary/queryByType",
        data:JSON.stringify({"type":"certificateType"}),
        dataType: "json",
        contentType:'application/json',
        success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
            if(data.status == 1){
                var $list='<li><a data-value="">请选择</a></li>';
                for(var i in data.value){
                    $list+='<li><a data-value="'+data.value[i].value+'">'+data.value[i].name+'</a></li>'
                }
                $(".certificateType").html($list);
                //下拉选择
                $(".certificateType").parent().Bsselect();
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.alert(e);
        }
    });
}
//获取公司
function selectConstruction(constructionId) {
    if(!constructionId||constructionId=="") {
        if(changeType!=1) {
            $("#company").attr("data-value","").html("请选择<span class=\"caret\"></span>");
            $(".company").html("");
            getTeam("")
        }
        return;
    }
    $.ajax({
        type: "post",
        url: ctx + "/team/getProjectCompanyList",
        data: {constructionId:constructionId},
        dataType: "json",
        success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
            if(data.status == 1){
                if(data.value.length==1){
                    if(changeType!=1){
                        $("#company").attr("data-value",data.value[0].id).html(data.value[0].name+'<span class="caret"></span>');
                        getTeam(data.value[0].id)
                    }
                }else{
                    if(changeType!=1) {
                        $("#company").attr("data-value","").html("请选择"+'<span class="caret"></span>');
                        getTeam("")
                    }
                }
                var $list='';
                for(var i in data.value){
                    $list+='<li><a data-value="'+data.value[i].id+'">'+data.value[i].name+'</a></li>'
                }
                $(".company").html($list);
                $(".company").parent().Bsselect();
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.alert(e);
        }
    });
}

//得到班组
function getTeam(id) {
    if(!id||id=="") {
        if(changeType!=1) $("#team").attr("data-value","").html("请选择班组<span class=\"caret\"></span>")
        $(".team").html("");
        return;
    }
    $.ajax({
        type: "post",
        url: ctx + "/person/listTeam",
        data: {projectCompanyId:id},
        dataType: "json",
        success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
            if(data.status == 1){
                if(data.value.length==1){
                    if(changeType!=1) $("#team").attr("data-value",data.value[0].teamId).html(data.value[0].teamName+'<span class="caret"></span>');
                }else{
                    if(changeType!=1) $("#team").attr("data-value","").html("请选择班组"+'<span class="caret"></span>');
                }
                var $list='';
                for(var i in data.value){
                    $list+='<li><a data-value="'+data.value[i].teamId+'">'+data.value[i].teamName+'</a></li>'
                }
                $(".team").html($list);
                $(".team").parent().Bsselect();
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.alert(e);
        }
    });
}

//图片上传前
$('#file').on('filepreupload', function(event, data, previewId, index) {
    files=data.files;
});

function my_ajax() {
    var index=layer.load($(".clientUserId").val());
    var constructionId=$("#site").attr("constructionid"),//工地id
        userName=$("#username").val(),//姓名
        IDcard=$("#IDcard").val(),//身份证号码
        telNum=$("#telNum").val(),//手机号码
        company=$("#company").attr("data-value"),//公司id
        job=$("#job").attr("data-value"),//岗位/工种
        certificateType=$("#certificateType").attr("data-value"),//证书类别
        certificateNum=$("#certificateNum").val(),//证书编号
        issuingDate=$("#issuingDate").val(),//签发日期
        effectiveDate=$("#effectiveDate").val(),//有效日期
        authority=$("#authority").val(),//发证机关
        projectCompanyName=$("#company").text(),//公司名称
        positionWorkTypeName=$("#job").val(),//岗位名称
        headFilePath=$("[name=\"headFile\"]").val()||"",
        headPortraitId=$("#photoShow").attr("data-value"),
        clientUserId=$(".clientUserId").val()||"",
        teamId=$("#team").attr("data-value")||"",
        teamName=$("#team").text()||"",
        bankCard=$("#bankCard").val()||"",
        cardNum=$("#cardNum").val().replace(/^[0]+/,"");//卡号
    var formData = new FormData($("#form_data")[0]);
    if(personId){   formData.append("id",personId);formData.append("userId",userId);
                    formData.append("certificateId",certificateId||"")};
    formData.append("constructionId",constructionId);//工地id
    formData.append("headFilePath",headFilePath);//图片路径
    formData.append("name",userName);//姓名
    formData.append("teamId",teamId);
    formData.append("headPortraitId",headPortraitId);
    formData.append("teamName",teamName);
    formData.append("bankCard",bankCard);
    formData.append("clientUserId",clientUserId);
    formData.append("projectCompanyName",projectCompanyName);//公司名称
    formData.append("positionWorkTypeName",positionWorkTypeName);//岗位名称
    formData.append("idCard",IDcard);//身份证号码
    formData.append("mobile",telNum);//手机号码
    formData.append("cardNumber",cardNum);//卡号
    formData.append("projectCompanyId",company);//公司id
    formData.append("positionWorkId",job);//岗位/工种
    if(certificateType)formData.append("certificateType",certificateType||"");//证书类别
    formData.append("certificateNumber",certificateNum||"");//证书编号
    formData.append("certificateIssueDate",issuingDate||"");//签发日期
    formData.append("certificateValidDate",effectiveDate||"");//有效日期
    formData.append("certificateIssueOrgans",authority||"");//发证机关
   $(".fileinput-upload-button").click();
    for(var i=0;i<files.length;i++){
        formData.append("files",files[i]);
    }
    $.ajax({
        url: ctx+"/person/addOrUpdatePerson",                              //后台请求地址
        data: formData,                            //自定义参数
        processData: false,
        contentType: false,
        type: "post",                          //当要提交自定义参数时，这个参数要设置成post
        dataType: 'json',                     //服务器返回的数据类型
        success: function (data) {            //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
            layer.close(index);
            if (data.status == 1) {
                layer.alert("提交成功！",function(){
                    layer_close()
                });
            } else if(data.status === 1001){
                $("#cardNum").addClass("kk_error");
                layer.msg(data.msg, {icon: 2,shift: 6,time:1500});
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {      //提交失败自动执行的处理函数
            layer.close(index);
            layer.alert(e);
        }
    });
}

//读取身份证信息
function getPersonInfo() {
    function getData() {
        var index=layer.load();
        $.ajax({
            url: ctx+"/person/getPersonByIdCard",                              //后台请求地址
            type: "post",                          //当要提交自定义参数时，这个参数要设置成post
            dataType: 'json',                     //服务器返回的数据类型
            success: function (data) {            //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                layer.close(index);
                if (data.status == 1) {
                    var dataV=data.value;
                    $('#username').val(dataV.name);
                    $('#IDcard').val(dataV.idCard);
                    $('#layer-photos-demo img').attr('src','data:image/jpeg;base64,'+dataV.headFilePath);
                    $(":input[name='sex'][value="+dataV.sex+"]").prop("checked",true);//性别
                } else {
                    layer.alert(data.msg);
                }
            },
            error: function (data, status, e) {      //提交失败自动执行的处理函数
                layer.close(index);
                layer.alert(e);
            }
        });
    }

    $("#getPersonInfo").click(getData);
}

//上传图片
function uploadImg() {
    var upload=layui.upload;
    upload.render({
        elem: '#test8'
        ,field:'headFile'
        ,auto: false
        ,accept:'images'
        ,multiple: false
        ,bindAction: '#test9'
        ,done: function(res){
        }
        ,choose: function(obj){
            //清除图片id
            $("#photoShow").attr('data-value',"");
            //支持ie的上传图片预览
            var browser=navigator.appName
            var b_version=navigator.appVersion
            var version=b_version.split(";");
            var trim_Version=version[1] ? version[1].replace(/[ ]/g,"") : '';
            if (browser=="Microsoft Internet Explorer" && (trim_Version=="MSIE8.0"||trim_Version=="MSIE9.0")) { // IE
                var obj = $('.layui-upload-file')[0];
                var imgSrc=getFileUrl(obj);
                // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
                $(".personImg img").css({"filter": "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + imgSrc + "\")"});
                $(".personImg img").attr("src","");
            }else{
                //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
                obj.preview(function(index, file, result){
                    $(".personImg img").attr("src",result);
                    // $("#name .layui-upload").append(' <span class="layui-inline layui-upload-choose">'+file.name+'<i class="layui-icon">&#x1006;</i></span>');

                });
            }
        }
    });
}


