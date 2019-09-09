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
            upData.list=[];
            $(".channelInfo .channelList").each(function (index) {
                var __self = $(this),id = __self.attr("id");
                upData.list[index]={
                    equipmentId:id,
                    list:[]
                };
                __self.find(".channel tbody tr").each(function (e) {
                    var __self = $(this);
                    upData.list[index].list.push({
                        name:__self.find('[name="channelName"]').val(),
                        channelNo:__self.find('[name="channelNum"]').val(),
                        flag:__self.find('[type="radio"]:checked').val(),
                        id:__self.attr("data-id")||""
                    })
                });
            });
            biyue.ajax({
                url:'/hook/saveNvr'
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
    getData:function () {
        var objSearch=biyue.urlSearch(),form=layui.form;
        $(".channelInfo").html("");
        biyue.ajax({
            url:"/hook/getNvrDetail",
            data:{
                id:objSearch.id
            },
            fun:function (data) {
                var dataV = data.value,projectId,projectName;
                for(var i in dataV){
                    if(i==="list"){
                        var dataL = dataV[i];
                        for(var k=0;k<dataL.length;k++){
                            var $model = $(".channelModel .channelList").clone(),
                                vos = dataL[k].list,id=dataL[k].equipmentId,$list="";
                            $($model).attr("id",id).find(".title p").html("设备信息("+dataL[k].equipmentName+")");
                            for(var j=0;j<vos.length;j++){
                                $list += ' <tr data-id="'+vos[j].id+'">\n' +
                                    '            <td>'+(j+1)+'</td>\n' +
                                    '            <td><input type="text" value="'+vos[j].name+'" lay-verify="required" class="layui-input" style="width: 100%;height: 26px;line-height: 26px;" name="channelName"></td>\n' +
                                    '            <td><input type="text" value="'+vos[j].channelNo+'" lay-verify="required" class="layui-input" style="width: 100%;height: 26px;line-height: 26px;" name="channelNum"></td>\n' +
                                    '            <td> <input type="radio" name="yt_'+id+'_'+(j+1)+'" value="1" title="是" '+(vos[j].flag===1?"checked":"")+'>\n' +
                                    '                <input type="radio" name="yt_'+id+'_'+(j+1)+'" value="0" title="否" '+(vos[j].flag===0?"checked":"")+'></td>\n' +
                                    '            <td><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a></td>\n' +
                                    '        </tr>';
                            }
                            $($model).find(".channel tbody").html($list);
                            $(".channelInfo").append($($model));
                        }
                    }else if(i==="projectId"){
                        projectId = dataV[i];
                    }else if(i==="projectName"){
                        projectName = dataV[i];
                    }else{
                        $('[name="'+i+'"]').val(dataV[i]);
                    }
                }
                $("[name='projectId']").html('<option value="'+projectId+'">'+projectName+'</option>');
                form.render();
            }
        })
    },
    channel:function () {
        var form = layui.form;
        //添加通道号
        $(".channelInfo").on("click",".add-channel",function () {
            var _self = $(this),_parent=$(this).parents(".channelList"),id=_parent.attr("id");
            var len =_parent.find(".channel tbody tr").length;
            var $list = ' <tr>\n' +
                '            <td>'+(len+1)+'</td>\n' +
                '            <td><input type="text" lay-verify="required" class="layui-input" style="width: 100%;height: 26px;line-height: 26px;" name="channelName"></td>\n' +
                '            <td><input type="text" lay-verify="required" class="layui-input" style="width: 100%;height: 26px;line-height: 26px;" name="channelNum"></td>\n' +
                '            <td> <input type="radio" name="yt_'+id+'_'+(len+1)+'" value="1" title="是">\n' +
                '                <input type="radio" name="yt_'+id+'_'+(len+1)+'" value="0" title="否" checked></td>\n' +
                '            <td><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a></td>\n' +
                '        </tr>';
            _parent.find(".channel tbody").append($list);
            form.render("radio");
        })
        //删除
        $(".channelInfo").on("click",'.channel [lay-event="del"]',function () {
            var _self = $(this),_parent=$(this).parents(".channelList"),id=_parent.attr("id");
            $(this).parents("tr").remove();
            _parent.find(".channel tbody tr").each(function (index) {
                $(this).find("td").eq(0).html(index+1);
                $(this).find('input[type="radio"]').eq(0).attr("name","yt_"+id+"_"+(index+1));
                $(this).find('input[type="radio"]').eq(1).attr("name","yt_"+id+"_"+(index+1));
            })
        })

    },
    getEqu:function (constructionId) {
        $(".channelInfo").html("");
        biyue.ajax({
            url:'/hook/getVaildHookEquipmet'
            ,data:{
                constructionId:constructionId
            }
            ,fun:function (data) {
                var dataV =data.value;
                for(var i in dataV){
                    var $list = $(".channelModel .channelList").clone();
                    $($list).attr("id",dataV[i].id).find(".title p").html("设备信息("+dataV[i].name+")");
                    $(".channelInfo").append($($list));
                }
            }
        });
    }
};
