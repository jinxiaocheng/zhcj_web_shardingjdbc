//数据提交
var biyueAdd={
    updata:function () {
        var form = layui.form;
        var _self = this,searchObj=biyue.urlSearch(),upData={};
        form.on('submit(add)', function(data){
            var msg="",url="";
            if(searchObj.type==='edit'){
                msg='编辑成功！';
                upData.id=searchObj.id;
                upData.constructionId=$('[name="constructionId"]').val();
                upData.constructionName=$("[name=\"constructionName\"]").val();
            }else{
                msg='新增成功！';
                upData.constructionId=$('[name="site"]').val();
                upData.constructionName=$("[name=\"site\"]  option:selected").text();
            }
            upData=biyue.getFormData({
                data:upData
            });
            biyue.ajax({
                url:'/projectCompany/add'
                ,data:upData
                ,fun:function (data) {
                    parent.layer.msg(msg,{time: 2000, icon: 1});
                    biyue.layui_close();
                }
            });
            return false;
        })
    },
    getData:function () {
        var objSearch=biyue.urlSearch(),form=layui.form;
        $(".channelInfo").html("");
        biyue.ajax({
            url:"/projectCompany/queryById",
            data:{
                id:objSearch.id
            },
            fun:function (data) {
                var dataV = data.value;
                for(var i in dataV){
                    $('[name="'+i+'"]').val(dataV[i]);
                }
                form.render();
            }
        })
    }
};
