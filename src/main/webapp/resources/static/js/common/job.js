/**
 * @desc 岗位和工种弹出窗
 * @param null
 * @return
 * @author kz
 * @date 2018/1/31 16:07
 */

//岗位工种
function jobBtn(w,h,cId) {
    $("#job").click(function () {
        var id = cId||$("#site").attr("data-value")||$("#site").attr("constructionid")||$("[name=\"site\"]").val()||$("[name=\"constructionId\"]").val();
        var workId = $("#job").attr("data-value");
        if(id){
            var _url = ctx + '/person/toWorkTable?id='+id+'&workId='+workId;
            var _title = '岗位/工种';
            var iframeWidth = w||'680px';
            var iframeHeight = h||'430px';
            layer.open({
                type: 2,
                title: "|&nbsp;"+_title,
                maxmin: true,
                area: [iframeWidth,iframeHeight],
                shadeClose: false, //点击遮罩关闭
                content:_url
            });
        }else{
            layer.msg('请选择工地后进行操作', {time: 2000, icon: 0});
        }

    });
}
$("#workDel").click(function () {
    $("#job").val("").attr("data-value","");
})

$(".job").hover(function () {
    $("#workDel").show();
},function () {
    $("#workDel").hide();
})

//获取岗位/工种
function get_job(name,id) {
    $("#job").val(name).attr("data-value",id);
}
window.get_job=get_job;
