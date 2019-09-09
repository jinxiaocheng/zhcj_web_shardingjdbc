var addDoor={
    biyue:new biYue(),
    //得到未分配权限的门
    getDoor:function (obj) {
        var obj=obj||{}
            ,_self=this
            ,form=layui.form
            ,biyue=_self.biyue;
        //获取门
        biyue.ajax({
            url:'/acsControl/listControllerNoDoor'
            ,data:{
                constructionId:obj.id
            }
            ,fun:function (data) {
                var $list='',dataV=data.value;
                if(!!dataV&&dataV.length>0){
                    for(var i in dataV){
                        $list+='<input type="checkbox" name="door" value="'+dataV[i].id+'" title="'+dataV[i].name+'">'
                    }
                }else{
                    $list='<span style="    line-height: 38px;\n' +
                        '    color: #c4c4c4;\n' +
                        '    letter-spacing: 1px;">暂无空置控制器</span>';
                }
                $("#rights .layui-input-block").html($list);
                form.render('checkbox');
                _self.getEdit(!!dataV&&dataV.length>0);  //得到编辑数据
            }
        })
    },
    //提交数据
    upData:function () {
        var form = layui.form;
        var _self = this,biyue=_self.biyue,searchObj=biyue.urlSearch();
        form.on('submit(add)', function(data){
            var controllerIdArray=[],upData={},url="",msg='';
            $("#rights .layui-input-block [type=\"checkbox\"]:checked").each(function () {
                controllerIdArray.push($(this).val());
            });
            if(searchObj.type==='edit'){
                url='/door/updateDoor';
                upData={
                    name:$('[name="name"]').val(),
                    id:searchObj.id,
                    controllerId:controllerIdArray
                };
                msg='编辑成功！';
            }else{
                url='/door/add';
                upData={
                    name:$('[name="name"]').val(),
                    constructionId:$('[name="site"]').find('option:selected').val(),
                    constructionName:$('[name="site"]').find('option:selected').html(),
                    controllerId:controllerIdArray
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
                url:'/door/listController',
                data:{
                    doorId:searchObj.id
                },
                fun:function (data) {
                    var dataV=data.value,dataDoor=dataV.doorList,$list='';
                    if(dataDoor&&dataDoor.length>0){
                        for(var i =0;i<dataDoor.length;i++){
                            $list+='<input type="checkbox" checked name="door" value="'+dataDoor[i].id+'" title="'+dataDoor[i].name+'">'
                        }
                    }else{
                        if(!bool){
                            $list='<span style="line-height: 38px;\n' +
                                '    color: #c4c4c4;\n' +
                                '    letter-spacing: 1px;">暂无空置控制器</span>';
                        }else{
                            $list="";
                        }
                    }

                    if(!bool){
                        $("#rights .layui-input-block").html($list);
                    }else{
                        $("#rights .layui-input-block").prepend($list);
                    }

                    form.val("biyueOpen", {
                         "name": dataV.doorName,
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
