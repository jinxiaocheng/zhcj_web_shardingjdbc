//数据提交
var biyueAdd={
    updata:function () {
        var form = layui.form;
        var _self = this,searchObj=biyue.urlSearch(),upData={};
        form.on('submit(add)', function(data){
            var msg="",url="";
            if(searchObj.type==='edit'){
                url = '/positionWork/edit';
                msg='编辑成功！';
                upData.id=searchObj.id;
                upData.constructionId=$('[name="constructionId"]').val();
                upData.constructionName=$("[name=\"constructionName\"]").val();
            }else{
                url = '/positionWork/add';
                msg='新增成功！';
                upData.constructionId=$('[name="site"]').val();
                upData.constructionName=$("[name=\"site\"]  option:selected").text();
            }
            upData=biyue.getFormData({
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
    getData:function () {
        var form=layui.form
            ,data = parent.editData;
        for(var i in data){
            $('[name="'+i+'"]').val(data[i]);
        }
        form.render();
    }
};
