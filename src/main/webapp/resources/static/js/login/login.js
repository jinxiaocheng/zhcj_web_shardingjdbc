//登录事件
function my_submit() {
    var layerLoad = layer.load();
    var username = $.trim($(".uname").val()),
        password = $.trim($(".pwd").val()),
        my_json = {
            "username": username,
            "password": password
        };
    $(".btn").attr("disabled");
    $.ajax({
        type: "post",
        url: ctx+'/doLogin',
        data: my_json,
        success: function (data) {
            if (data.status == 1) {
                bool = true;
                top.location.href = ctx + '/index';
            } else {
                bool = false;
                $(".prompt").html(data.msg)
            }
            $(".btn").removeAttr("disabled");
            layer.close(layerLoad);
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.close(layerLoad);
            layer.alert(e);
        }
    });
}

//写cookies
function setCookie(name, value) {
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}

//读取cookies
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");

    if (arr = document.cookie.match(reg))

        return unescape(arr[2]);
    else
        return null;
}

//删除cookies
function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null)
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}

//运用cookies
function KK_cookie(checkID, usernameID, passwordID, submitID, unName, pwName) {
    var che = document.getElementById(checkID),
        un = document.getElementById(usernameID),
        pw = document.getElementById(passwordID),
        submit = document.getElementById(submitID);

    if (getCookie(unName) && getCookie(pwName)) {

        un.value = getCookie(unName);
        pw.value = getCookie(pwName);
        che.checked = "checked";
    }

    submit.onclick = function () {
        if (che.checked) {
            setCookie(unName, un.value);
            setCookie(pwName, pw.value);
        } else {
            delCookie(unName);
            delCookie(pwName);
        }
    }
}
