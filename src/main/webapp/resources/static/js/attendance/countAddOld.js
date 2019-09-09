//显示
function showMenu(e) {
    if (e && e.stopPropagation) {
        e.stopPropagation();
    } else {
        window.event.cancelBubble = true;
    }
    if (bool) {
        bool = false;
        $("#tree").fadeIn("fast");
        $("body").bind("mousedown", onBodyDown);
    } else {
        hideMenu();
    }
}

//隐藏
function hideMenu() {
    bool = true;
    $("#tree").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}

//点击window隐藏
function onBodyDown(event) {
    if (!(event.target.id == "construction" || event.target.id == "tree" || $(event.target).parents("#tree").length > 0)) {
        hideMenu();
    }
}

//通过工地ID获取班组
function my_data1() {
    var $list = "";
    $.ajax({
        type: "post",
        url: ctx + '/team/getDefaultTeamName',
        //data: my_json,
        success: function (data) {
            if (data.status == 1) {
                if (data.value) {
                    items_ztree(data.value)
                    item(data.value)
                }
            } else {
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.alert(e);
        }
    });
}
 //班组分配数据
 function item(data) {
     if (!data) return;
     var len = data.length, $list = "";
     if (data[0] == undefined) {
         $(".item>button").html("无班组");
     } else {
         $(".item>button").html(data[0] + '<span class="caret" style="margin-left:6px"></span>');
         for (var i = 0; i < len; i++) {
             $list += '<li><a>' + data[i] + '</a></li>'
         }
         $(".item>ul").html($list);
         $(".item>ul>li").click(function () {
             var index = $(this).index();
             $(".item>button").html(data[index]+ '<span class="caret" style="margin-left:6px"></span>')

         })
     }
 }

//提示框
$(".close").click(function () {
    $(".error").fadeOut("fast");
});
function my_error(text) {
    $(".error").fadeIn("fast");
    $(".error span").html(text);
    setTimeout(function(){
        $(".error").fadeOut("fast");
    },1000)
}
//修改提交
function my_ajax() {
    var teamName = $(".item>button").text(),//班组名
        add_date = $(".date_time>input").val(),//考勤日期
        count = $(".count>input").val()//获取班组考勤人数
    var formData = new FormData($("#form_data")[0]);
    formData.append("constructionId", constructionId)
    formData.append("teamName", teamName)
    formData.append("attendanceDate", add_date)
    formData.append("count", count)
    $.ajax({
        url: ctx + "/attendaceCountController/saveOrUpdate",         //后台请求地址
        data: formData,                            //自定义参数
        processData: false,
        contentType: false,
        type: "post",                              //当要提交自定义参数时，这个参数要设置成post
        dataType: 'json',                          //服务器返回的数据类型
        success: function (data) {                 //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
            if (data.status == 1) {
                layer.alert("提交成功！",function(){
                    layer_close()
                    $(".my_load").hide();
                });
            } else {
                layer.alert(data.msg);
                $(".my_load").hide();
            }
            $(".my_load").hide();
        },
        error: function (data, status, e) {      //提交失败自动执行的处理函数
            layer.alert(e);
            $(".my_load").hide();
        }
    });

}

