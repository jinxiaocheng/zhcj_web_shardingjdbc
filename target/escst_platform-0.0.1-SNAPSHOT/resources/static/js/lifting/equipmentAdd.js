var upload = layui.upload;

//数据提交
var biyueAdd={
    updata:function () {
        var form = layui.form;
        var _self = this,searchObj=biyue.urlSearch(),upData={},url="";
        form.on('submit(add)', function(data){
            if(searchObj.type==='edit'){
                url="/hook/saveHookEquipment";
                msg='编辑成功！';
                upData.id=searchObj.id;
                upData.constructionId=$('[name="constructionId"]').val();
                upData.constructionName=$("[name=\"constructionName\"]").val();
            }else{
                url="/hook/saveHookEquipment";
                msg='新增成功！';
                upData.constructionId=$('[name="site"]').val();
                upData.constructionName=$("[name=\"site\"]  option:selected").text();
            }
            upData = biyue.getFormData({
                data:upData
            });
            biyue.ajax({
                url:url
                ,data:upData
                ,fun:function (data) {
                    parent.layer.msg(msg,{time: 2000, icon: 1});
                    biyue.layui_close();
                }
            });
            return false;
        })
    },
    getData:function (fun) {
        var objSearch=biyue.urlSearch(),_self=this,form=layui.form;
        biyue.ajax({
            url:"/hook/getHookEquipmentDetail",
            data:{
                id:objSearch.id
            },
            fun:function (data) {
                var dataV = data.value,constructionId="";
                for(var i in dataV){
                    $('[name="'+i+'"]').val(dataV[i]);
                }
                form.val("biyueOpen", {
                    "areaName":dataV.areaName,
                    "areaId":dataV.areaId,
                    "constructionName":dataV.constructionName,
                    "constructionId":dataV.constructionId
                });
                form.render();
                fun&&fun();
            }
        })
    }
};
