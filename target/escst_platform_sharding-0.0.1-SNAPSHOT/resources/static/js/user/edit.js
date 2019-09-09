
$("#userName").blur(function(){
    judgeUser(true);
});

//判断用户是否存在 bool适用于控制是否异步，只有同步是返回才有效
function judgeUser(bool) {
    var userName=$("#userName").val();
    var userNameOld=$("#userName").attr('data-name');
    var userBoll=true;
    if(userName==null || userName==''||userName===userNameOld){
        return userBoll;
    }
    $.ajax({
        type: "post",
        url: ctx + "/user/checkUserName",
        async:bool,
        data:{'userName':userName},
        dataType: "json",
        success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
            if(data.status == 1){
                if(data.value==0){
                    my_error("用户名已存在，请重新输入！");
                    $("#userName").addClass("kk_error");
                    userBoll=false;
                }
            } else {
                alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            alert(e);
        }
    });
    return userBoll;
}

$("#checkPassword").blur(function(){
    judgePassword()
});

function judgePassword(){
    var userPassword = $("#userPassword").val();
    var checkPassword = $("#checkPassword").val();
    if($.trim(userPassword) != $.trim(checkPassword)){
        my_error("两次输入的密码不一致，请重新输入！");
        $(".password input").addClass("kk_error");
        return false;
    }else{
        return true;
    }
}

function layer_close(){
    var url = ctx + "/user/userList";
    parent.jQuery("#gridTable").jqGrid('setGridParam',{url:url}).trigger("reloadGrid");
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function my_ajax(){
    if(!judgeUser(false)){
        $(".my_load").hide();
        return;
    }
    if(!judgePassword()){
        $(".my_load").hide();
        return;
    }
    var formData = new FormData($("#userAndPersonform")[0]);
    var data = getQueryJson();
    $.ajax({
        type: "post",
        url: ctx + "/user/edit",
        data:data,
        dataType: "json",
        success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
            if(data.status == 1){
                layer.alert("修改用户成功！",function () {
                    layer_close();
                });
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

function getQueryJson() {
    var userName=$("#userName").val();
    var userPassword=$("#userPassword").val();
    var name=$("#name").val();
    var id=$("[name='id']").val();
    var sex=$("[name='sex']:checked").val();
    var age=$("#age").val();
    var mobile=$("#mobile").val();
    var idCard=$("#idCard").val();
    var email=$("#email").val();
    var postPrams = {
        id:id,
        userName :userName,
        userPassword :userPassword,
        name :name,
        sex :sex,
        age :age,
        mobile :mobile,
        idCard :idCard,
        email :email,
        orgId :$("#orgId").val(),
        positionWorkType:$("#job").attr("data-value"),
        company:$("#company").attr("data-value"),
        userId:$("#id").val()
        //team:$("#team").attr("data-value"),
        //bloodType:$("#bloodType").attr("data-value"),
        //iceContactName:$("#iceContactName").val(),
        //iceContactMobile:$("#iceContactMobile").val(),
    };
    return postPrams;
}

//点击显示密码
$(".password .input-group-addon").click(function () {
    var self=$(this);
    if(self.find('.fa-eye-slash').length>0){
        self.find('.fa-eye-slash').addClass("fa-eye").removeClass('fa-eye-slash');
        self.parent().find('input').attr("type",'text');
    }else{
        self.find('.fa-eye').addClass("fa-eye-slash").removeClass('fa-eye');
        self.parent().find('input').attr("type",'password');
    }
});