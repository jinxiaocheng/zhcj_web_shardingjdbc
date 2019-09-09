var addDoor={
    biyue:new biYue(),
    //提交数据
    upData:function () {
        var form = layui.form;
        var _self = this,biyue=_self.biyue,searchObj=biyue.urlSearch();
        form.on('submit(add)', function(data){
            var controllerIdArray=[],upData={},url="",msg='';
            $("#rights .layui-input-block [type=\"checkbox\"]:checked").each(function () {
                controllerIdArray.push($(this).val());
            });
            url='/acsControl/saveOrUpdate';
            if(searchObj.type==='edit'){
                upData={
                    name:$('[name="name"]').val(),
                    id:searchObj.id,
                    number:$('[name="number"]').val()
                };
                msg='编辑成功！';
            }else{
                upData={
                    name:$('[name="name"]').val(),
                    constructionId:$('[name="site"]').find('option:selected').val(),
                    constructionName:$('[name="site"]').find('option:selected').html(),
                    number:$('[name="number"]').val()
                };
                msg='新增成功！';
            }

            biyue.ajax({
                url:url
                ,data:JSON.stringify(upData)
                ,contentType:'application/json'
                ,fun:function (data) {
                    parent.layer.msg(msg,{time: 2000, icon: 1});
                    biyue.layui_close();
                }
            });
            return false;
        })
    },
    //得到编辑数据
    getEdit:function (bool) {
        var _self=this,biyue =_self.biyue,searchObj=biyue.urlSearch(),form=layui.form;
        if(searchObj.type==='edit'){
            biyue.ajax({
                url:'/acsControl/queryById',
                data:{
                    id:searchObj.id
                },
                fun:function (data) {
                    var dataV=data.value;
                    form.val("biyueOpen", {
                        "name":dataV.name,
                        "number":dataV.number,
                        "areaName":dataV.areaName,
                        "areaId":dataV.areaId,
                        "constructionName":dataV.constructionName,
                        "constructionId":dataV.constructionId
                    });
                     form.render('checkbox');
                }
            })
        }
    }
};
