//----------新增
var addNvr={
    //获取通道数据
    sendData:function(){
        var cameraEntityList=[];
        $('.channelList .count').each(function (index) {
            var dom=$(this);
            cameraEntityList.push({
                name:dom.find('.channelName').val(),
                flag:dom.find('input[type="radio"]:checked').val(),
                channelNo:dom.find('.channelNum').val(),
                id:dom.attr("data-value")
            })
        });
        return {
            id:getSearch().id||"",
            name:$('.site button').text(),
            constructionId:$('.site button').attr('constructionid'),
            ip:$("#address").val(),
            port:$("#port").val(),
            appPort:$("#appPort").val(),
            userName:$("#userName").val(),
            password:$("#password").val(),
            cameraEntityList:cameraEntityList
        }
    }
    //复制页面
    ,initData:function(){
        $('.area button').html('请选择区域<span class="caret" style="margin-left:6px"></span>');
        $('.area button').attr('areaid',"");
        $('.site button').html('请选择工地<span class="caret" style="margin-left:6px"></span>');
        $('.site button').attr('constructionid',"");
        $('.site ul').html("");
        $("#address").val("");
        $("#port").val("");
        $("#appPort").val("");
        $("#userName").val("");
        $("#password").val("");
        $('.channelList').html("");
    }
    //创建通道列表
    ,createList:function(i){
        var $list='<div class="form-group count row list_'+i+'" data-channelNo="'+i+'" data-value="">\n' +
            '                    <label class="col-xs-2">'+i+'.通道名称</label>\n' +
            '                    <input type="text" kk-prop="value"  placeholder="请填写通道名称" class="form-control col-xs-3 channelName">\n' +
            '                    <input type="text" kk-prop="value"  placeholder="请填写通道号" class="form-control col-xs-2 channelNum">\n' +
            '                    <label class="col-xs-1">云台控制</label>\n' +
            '                    <span style="padding-left: 10px" class="col-xs-2">\n' +
            '                        <label class="radio-inline">\n' +
            '                            <input type="radio" name="if_'+i+'" id="yer_'+i+'" kk-prop="value" value="1" >是\n' +
            '                        </label>\n' +
            '                        <label class="radio-inline">\n' +
            '                            <input type="radio" name="if_'+i+'" id="no_'+i+'" value="0" checked="checked"> 否\n' +
            '                        </label>\n' +
            '                    </span>\n' +
            '                    <a class="fa fa-times removeList" style="margin-top: 10px ;color: red" aria-hidden="true"></a>\n' +
            '                </div>';
        return $list;
    }
    //新增通道
    ,addChannel:function () {
        var i =1,self=this;
        $('#items .check_items_btn').click(function () {
            var itemsNum=$("[data-channelno]:last").attr("data-channelno");
            if(itemsNum&&i===1){
                i=parseInt(itemsNum)+1;
            }
            $('.channelList').append(self.createList(i));
            i++;
        });

        $('.channelList').on('click','.removeList',function () {
            $(this).parent().remove();
        })
    }
    //上传数据
    ,uploadData:function () {
        var self=this,index=layer.load();
        $.ajax({
            url: ctx + "/video/saveNvrDeploy",         //后台请求地址
            data: JSON.stringify(self.sendData()),                            //自定义参数
            processData: false,
            contentType: "application/json",
            type: "post",                              //当要提交自定义参数时，这个参数要设置成post
            dataType : "json",                          //服务器返回的数据类型
            success: function (data) {                 //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                layer.close(index);
                if (data.status == 1) {
                    layer.alert("提交成功！",function () {
                        layer_close();
                    });

                } else {
                    layer.alert(data.msg);
                }
            },
            error: function (data, status, e) {      //提交失败自动执行的处理函数
                layer.close(index);
                layer.alert(String(e));
            }
        });
    }
};

//----------修改
var edit={
    getData:function(url,data){
        var index=layer.load(),self=this;
        $.ajax({
            url: url,         //后台请求地址
            type: "post",                              //当要提交自定义参数时，这个参数要设置成post
            data:data,
            dataType : "json",                          //服务器返回的数据类型
            success: function (data) {                 //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                console.log(data);
                layer.close(index);
                if (data.status == 1) {
                    self.setData(data.value);
                } else {
                    layer.alert(data.msg);
                }
            },
            error: function (data, status, e) {      //提交失败自动执行的处理函数
                layer.close(index);
                layer.alert(String(e));
            }
        });
    }
    ,setData:function (data) {
        $('.area button').html(data.areaName+'<span class="caret" style="margin-left:6px"></span>');
        $('.area button').attr('areaid',data.areaId);
        $(".area  button").attr('disabled',true);
        $(".site  button").attr('disabled',true);
        $('.site button').html(data.constructionName+'<span class="caret" style="margin-left:6px"></span>');
        $('.site button').attr('constructionid',data.constructionId);
        $('.site button').attr("readonly",true);
        $("#address").val(data.ip);
        $("#port").val(data.port);
        $("#appPort").val(data.appPort);
        $("#userName").val(data.userName);
        $("#password").val(data.password);
        $("#items>div").attr("data-value",data.vos.length);
        for(var i in data.vos){
            var id= data.vos[i].id;
            var channelNo =data.vos[i].channelNo;
            $('.channelList').append(addNvr.createList(parseInt(i)+1));
            $('.channelList>div:last').attr('data-value',data.vos[i].id);
            $('.channelList>div:last').find(".channelName").val(data.vos[i].name);
            $('.channelList>div:last').find(".channelNum").val(channelNo);
            $('.channelList>div:last').find('input:radio[value='+data.vos[i].flag+']').attr("checked",true);
        }
    }
};
/*关闭弹出框口*/
function layer_close(){
    var url = ctx + "/video/getNvrList";
    parent.jQuery("#gridTable").jqGrid('setGridParam',{url:url}).trigger("reloadGrid");
    parent.layer.closeAll();
}