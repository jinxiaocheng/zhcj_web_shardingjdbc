//人员列表
function person(id){
    if(changeType!=1){
        $(".person>button").html('请选择责任人 <span class="caret"></span>');
    }
    changeType=0;
    var my_json={
            "constructionId":id,
        };
    $.ajax({
        type: "post",
        url: ctx + '/equipment/queryPersonByConstructionId',
        data: my_json,
        success: function (data) {
            if(data.status == 1){
                if(data.value){
                    var len=data.value.length,$list="";
                    for(var i=0;i<len;i++){
                        $list+='<li><a data-value="'+data.value[i].userId+'">'+data.value[i].name+'</a></li>'
                    }
                    $(".person>ul").html($list);
                    $(".person>ul").parent().Bsselect();
                }
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.alert(e);
        }
    });
}











