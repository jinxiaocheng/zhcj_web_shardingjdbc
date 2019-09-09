var addDoor={
    biyue:new biYue(),
    //得到未分配权限的门
    getDoor:function (obj) {
        var obj=obj||{}
            ,_self=this
            ,form=layui.form
            ,biyue=_self.biyue
            ,searchObj=biyue.urlSearch();
        //获取门
        console.log({
            personId:searchObj.id
        })
        biyue.ajax({
            url:'/door/listDoor'
            ,data:{
                personId:searchObj.id
            }
            ,fun:function (data) {
                var $list='',dataV=data.value,cList=dataV.cList,pList=dataV.pList;
                if(!!cList&&cList.length>0){
                    for(var i in cList){
                        var bool=false;
                        for(var j in pList){
                            if(cList[i].doorId===pList[j].doorId){
                                bool=true;
                                break;
                            }
                        }
                        if(bool){
                            $list+='<input type="checkbox" checked name="door" value="'+cList[i].doorId+'" title="'+cList[i].doorName+'">'
                        }else{
                            $list+='<input type="checkbox" name="door" value="'+cList[i].doorId+'" title="'+cList[i].doorName+'">'
                        }
                    }
                }else{
                    $list='<span style="    line-height: 38px;\n' +
                        '    color: #c4c4c4;\n' +
                        '    letter-spacing: 1px;">暂无门</span>';
                }
                $("#rights .layui-input-block").html($list);
                form.render('checkbox');
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
            url='/door/allotDoor';
            upData={
                personId:searchObj.id,
                doorIds:controllerIdArray
            };
            msg='分配成功！';

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
    }
};
