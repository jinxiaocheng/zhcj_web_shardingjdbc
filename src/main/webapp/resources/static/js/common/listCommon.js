//TODO 以后所有list页面公用函数放置在此页


$(window).keydown(function() {//给输入框绑定按键事件
    if(event.keyCode == "13") {//判断如果按下的是回车键则执行下面的代码
        $("#search_link").click();
    }
});

/*扩展*/
$.fn.extend({
    /*下拉框*/
    Bsselect: function () {
        var obj = this, Selectval = obj.find(".dropdown-text"), Selectoption = obj.find(".dropdown-menu");
        Selectoption.find("li").click(function () {
            var option = $(this).text();
            var dataValue = $(this).children("a").attr("data-value");
            Selectval.text(option);
            Selectval.attr("data-value",dataValue);
        });
    }
});

//权限控制
;(function control() {
    var search=window.location.search.replace("?","").split("&");
    var searchObj={};
    for(var i in search){
        searchObj[search[i].split("=")[0]]=search[i].split("=")[1];
    }
    if(searchObj.operationAuthority!==undefined){
        searchObj.operationAuthority=searchObj.operationAuthority.split(",");
        $(".tools-btn .btn-group").addClass("conRemove");
        for(var j in searchObj.operationAuthority){
            powerText(searchObj.operationAuthority[j]);
        }
        $(".conRemove").remove();
    }
})();
function powerText(prop) {
    switch(prop)
    {
        case "list":
            $("#search_link").parent().removeClass("conRemove")   //"查询"
            break;
        case "view":
            $("#view_link").parent().removeClass("conRemove")   //"查看"
            break;
        case "save":
            $("#add_link").parent().removeClass("conRemove")   //"新增"a
            $("#add_link").parent().removeClass("conRemove")   //"录入"a
            break;
        case "add":
            $("#add_link").parent().removeClass("conRemove")   //"新增"a
            $("#add_link").parent().removeClass("conRemove")   //"录入"a
            break;
        case "edit":
            $("#edit_link").parent().removeClass("conRemove")   //"编辑"
            $("#change_link").parent().removeClass("conRemove")   //"修改"
            break;
        case "update":
            $("#edit_link").parent().removeClass("conRemove")   //"编辑"
            $("#change_link").parent().removeClass("conRemove")   //"修改"
            break;
        case "delete":
            $("#delete_link").parent().removeClass("conRemove")   //"删除"
            break;
        case "distribution":
            $("#createUser").parent().removeClass("conRemove")   //生成用户
            break;
        /*  case "audit":
              $("#view_link").parent().removeClass("conRemove")   //"审核"
              break;
          case "submit":
              $("#view_link").parent().removeClass("conRemove")   //"提交"
              break;
          case "print":
              $("#view_link").parent().removeClass("conRemove")   //"打印"
              break;*/
        case "import":
            $("#import_link").parent().removeClass("conRemove")   //"导入"
            break;
        case "affirm":
            $("#confirm_link").parent().removeClass("conRemove")   //"确认完成"
            break;
        case "export":
            $("#export_link").parent().removeClass("conRemove")   //"导出"
            break;
        case "download":
            $("#template_link").parent().removeClass("conRemove")   //"下载模板"
            break;
        case "task":
            $("#link").parent().removeClass("conRemove")   //"历史任务"
            break;
        case "chart":
            $("#chart_link").parent().removeClass("conRemove")   //"趋势图"
            break;
        case "allot":
            $("#assign_roles").parent().removeClass("conRemove")   //"分配角色"
            break;
        case "reset":
            $("#reset_pwd_link").parent().removeClass("conRemove")   //"重置密码"
            break;
        default:
            return ""
    }
}