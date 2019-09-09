//数据提交
var biyueAdd = {
    certificateId: "",//证书id
    //上传数据
    updata: function () {
        var form = layui.form;
        var _self = this, searchObj = biyue.urlSearch(), upData = {};
        form.on('submit(add)', function (data) {
            var msg = "", url = "";
            //判断是否上传头像
            var file = $('[type="file"]').val();

            if (searchObj.type === 'edit') {
                msg = '编辑成功！';
                upData.id=searchObj.id;
                upData.userId=userId;
                upData.certificateId= _self.certificateId || "";
            } else {
                var type = $("input[name='attendanceType']:checked").val();
                if ((type === "2" || type === "3") && !file) {
                    layer.msg("请上传图片", {icon: 5, anim: 6, time: 1500});
                    $(".personImg").addClass("error");
                    return false
                }
                msg = '新增成功！';
                upData.constructionId=$('[name="site"]').val();
                upData.constructionName=$("[name=\"site\"]  option:selected").text();
            }
            upData.teamName=$("[name=\"teamId\"] option:selected").html();
            upData.positionWorkId=$("[name=\"positionWorkTypeName\"]").attr("data-value");
            upData.positionWorkTypeName=$("[name=\"positionWorkTypeName\"]").val();
            biyue.ajax({
                url: '/person/addOrUpdatePerson'
                ,data:upData
                ,form:true
                ,ele:".layui-form"
                , fun: function (data) {
                    parent.layer.msg(msg, {time: 2000, icon: 1});
                    biyue.layui_close();
                }
            })
            return false;
        })
    },
    //得到数据
    getData:function (fun) {
        var objSearch=biyue.urlSearch(),form = layui.form,_self = this;
        biyue.ajax({
            url:"/person/detailInfo?personId="+objSearch.id,
            fun:function (data) {
                var dataV = data.value,
                    teamId = "", //用来班组id
                    companyId = "",
                    constructionId = "",
                    certificateType = "";
                for(var i in dataV){
                    if(i==="headFilePath"){
                        $(".personImg").attr("src",dataV[i]);
                    } else if(i==="sex"||i==="isLeader"){
                        $('input[name="'+i+'"][value="'+dataV[i]+'"]').attr('checked',true);
                    } else if(i==="attendanceType"){
                        $('input[name="'+i+'"][value="'+dataV[i]+'"]').attr('checked',true);
                        _self.attendanceType(dataV[i]);
                    } else if(i==="certificateId"){
                        _self.certificateId=dataV[i];
                    } else if(i==="projectCompanyId"){
                        companyId = dataV[i];
                        $('[name="'+i+'"]').val(dataV[i]);
                    } else if(i==="teamId"){
                        teamId = dataV[i];
                    } else if(i==="certificateType"){
                        certificateType = dataV[i];
                    } else if(i==="constructionId"){
                        constructionId = dataV[i];
                        $('[name="'+i+'"]').val(dataV[i]);
                    } else if(i==="positionWorkId"){
                        $("[name=\"positionWorkTypeName\"]").attr("data-value",dataV[i])
                    }else{
                        $('[name="'+i+'"]').val(dataV[i]);
                    }
                }
                //加载公司
                _self.getCompany(constructionId,function () {
                    $('[name="projectCompanyId"]').val(companyId);
                });
                //加载班组
                _self.getTeam(companyId,function () {
                    $('[name="teamId"]').val(teamId);
                });
                //证书类别
                _self.getType(function () {
                    $('[name="certificateType"]').val(certificateType);
                });
                fun&&fun();
                form.render();
            }
        })
    },
    //公司
    getCompany: function (id,fun) {
        var form = layui.form, _self = this;
        biyue.ajax({
            url: '/team/getProjectCompanyList',
            data: {
                constructionId: id
            },
            fun: function (data) {
                var $list = "<option value=\"\">选择公司</option>"
                    , dataV = data.value;

                for (var i = 0; i < dataV.length; i++) {
                    $list += '<option value="' + dataV[i].id + '">' + dataV[i].name + '</option>';
                }

                $("[name='projectCompanyId']").html($list);
                fun&&fun();
                form.render('select');
                form.on('select(company)', function (data) {
                    var id = data.value;
                    _self.getTeam(id);
                });
            }
        })
    },
    //班组
    getTeam: function (id,fun) {
        var form = layui.form;
        biyue.ajax({
            url: '/person/listTeam',
            data: {
                projectCompanyId: id
            },
            fun: function (data) {
                var $list = "<option value=\"\">选择班组</option>"
                    , dataV = data.value;

                for (var i = 0; i < dataV.length; i++) {
                    $list += '<option value="' + dataV[i].teamId + '">' + dataV[i].teamName + '</option>';
                }
                $("[name='teamId']").html($list);
                fun&&fun();
                form.render('select');
            }
        })
    },
    //证书类别
    getType: function (fun) {
        var form = layui.form;
        biyue.ajax({
            url: '/dictionary/queryByType',
            data: JSON.stringify({"type": "certificateType"}),
            contentType: 'application/json',
            fun: function (data) {
                var $list = "<option value=\"\">选择证书类别</option>"
                    , dataV = data.value;

                for (var i = 0; i < dataV.length; i++) {
                    $list += '<option value="' + dataV[i].value + '">' + dataV[i].name + '</option>';
                }
                $("[name='certificateType']").html($list);
                fun&&fun();
                form.render('select');
            }
        })
    },
    //上传图片配置
    upImg: function () {
        var upload = layui.upload;
        //普通图片上传
        var uploadInst = upload.render({
            elem: '#personImg'
            , url: ''
            , auto: false
            , field: 'file'
            , accept: 'images'
            , multiple: false
            , bindAction: '#test9'
            , choose: function (obj) {
                $(".personImg").removeClass("error");
                $("[name='file']").attr("name", "headFile");
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('.personImg').attr('src', result); //图片链接（base64）
                });
            }
            , done: function (res) {
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败');
                }
                //上传成功
            }
            , error: function () {

            }
        });
    },
    //识别方式
    attendanceType : function (val) {
        val = String(val);
        //初始化
        $("#personImg .req").remove();
        $("[name=\"cardNumber\"]").removeAttr("lay-verify")
            .parents(".layui-inline").find(".req").remove();
        //判断类型
        if (val === '1') { // 刷卡
            $("[name=\"cardNumber\"]").attr("lay-verify", "required")
                .parents(".layui-inline").find(".layui-form-label")
                .prepend('<i class="req">*</i>');
        } else if (val === '2') { // 人脸识别
            $("#personImg").prepend('<i class="req">*</i>');
        } else if (val === '3') { // 刷卡/人脸识别
            $("#personImg").prepend('<i class="req">*</i>');
            $("[name=\"cardNumber\"]").attr("lay-verify", "required")
                .parents(".layui-inline").find(".layui-form-label")
                .prepend('<i class="req">*</i>');
        }
    },
    //相应事件
    event: function () {
        var laydate = layui.laydate, form = layui.form , _self = this;
        laydate.render({
            elem: '[name="certificateIssueDate"]' //签发日期
        });
        laydate.render({
            elem: '[name="certificateValidDate"]' //有效日期
        });
        //卡号失去焦点是去除前面的0
        $("[name=\"cardNumber\"]").blur(function () {
            var cardNum = $("[name=\"cardNumber\"]").val().replace(/^[0]+/, "");
            $("[name=\"cardNumber\"]").val(cardNum)
        });
        //识别方式
        form.on('radio(attendanceType)', function (data) {
            var val = data.value;
           _self.attendanceType(val)
        });
    }
};
