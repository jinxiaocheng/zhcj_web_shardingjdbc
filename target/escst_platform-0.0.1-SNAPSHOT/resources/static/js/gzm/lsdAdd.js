var upload = layui.upload;

//数据提交
var biyueAdd={
    file:function () {
        //普通图片上传
        var uploadInst = upload.render({
            elem: '#test1'
            ,url: '/upload/'
            ,auto: false
            ,field:'files'
            ,accept:'images'
            ,multiple: false
            ,bindAction: '#test9'
            ,choose: function(obj){
                $("[name='files']").attr("name","file");
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
                //上传成功
            }
            ,error: function(){

            }
        });
        //放大查看图片
        $(".layui-upload-list").on('click', 'img', function () {
            var src = $(this).attr("src");
            top.photos([{
                "src": src
            }], 0)
        })
    },
    updata:function () {
        var form = layui.form;
        var _self = this,searchObj=biyue.urlSearch(),upData={},url="";
        form.on('submit(add)', function(data){
            if(searchObj.type==='edit'){
                url="/flow/updateFlow";
                msg='编辑成功！';
                upData.id=searchObj.id;
                upData.constructionId=$('[name="constructionId"]').val();
                upData.constructionName=$("[name=\"constructionName\"]").val();
            }else{
                url="/flow/save";
                msg='新增成功！';
                upData.constructionId=$('[name="site"]').val();
                upData.constructionName=$("[name=\"site\"]  option:selected").text();
            }

            upData.ids=[];
            $.each($('input:checkbox:checked'),function(){
                var __self = $(this);
                upData.ids.push(__self.val());
            });
            upData.ids= upData.ids.join(",");
            biyue.ajax({
                url:url
                ,form:true
                ,ele:".layui-form"
                ,data:upData
                ,fun:function (data) {
                    parent.layer.msg(msg,{time: 2000, icon: 1});
                    biyue.layui_close();
                }
            });
            return false;
        })
    },
    getCD:function (id,type) {
        var objSearch=biyue.urlSearch(),form = layui.form;
        biyue.ajax({
            url:"/collector/getVaildCollector",
            data:{
                constructionId:id
            },
            fun:function (data) {
                var dataV = data.value,$list="";
                for(var i in dataV){
                    $list+='<input type="checkbox" name="cd" value="'+dataV[i].id+'" title="'+dataV[i].name+"("+dataV[i].number+")"+'">';
                }
                if(type){
                    $(".cds").append($list);
                }else{
                    $(".cds").html($list);
                }
                form.render("checkbox");
            }
        })
    },
    getData:function (fun) {
        var objSearch=biyue.urlSearch(),_self=this,form=layui.form;
        biyue.ajax({
            url:"/flow/querFlowDetail",
            data:{
                id:objSearch.id
            },
            fun:function (data) {
                var dataV = data.value,constructionId="";
                for(var i in dataV){
                    if(i==="filePath"){
                        $("#demo1").attr("src",dataV[i]);
                    }else if(i==="collectorVos"){
                        var $cds="";
                        for(var j in dataV[i]){
                            $cds+='<input type="checkbox" checked name="cd" value="'+dataV[i][j].id+'" title="'+dataV[i][j].name+"("+dataV[i][j].number+")"+'">';
                        }
                        $('.cds').html($cds);
                    }else if(i==="constructionId"){
                        constructionId = dataV[i];
                    }else{
                        $('[name="'+i+'"]').val(dataV[i]);
                    }
                }
                _self.getCD(constructionId,true);
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
