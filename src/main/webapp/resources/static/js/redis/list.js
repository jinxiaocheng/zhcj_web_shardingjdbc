var redis={
    biyue:new biYue(),
    getData:function () {
        var index = layer.load(),_self=this;
        $.ajax({
            url:ctx+'/redis/query',
            data:{
                key:$("#key").val()
            },
            type:'post',
            success:function (data) {
                layer.close(index);
                if(data.value){
                    var dataV=data.value,dataS=[];
                    for(var i in dataV){
                        dataS.push({
                            id:dataV[i]
                        })
                    }

                    _self.table(dataS);
                }

            },
            error:function (e) {
                layer.close(index);
                console.log(e);
            }
        })
    },
    table:function (data) {
        $("#demo").html("");
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#table-list'
            ,data: data
            ,limit:data.length
            ,height: 'full-90'
            ,cols: [[ //表头
                {type:'numbers'},
                {type:'checkbox'},
                {field: 'id', title: 'key'},
                {fixed: 'right', title:'操作',width:120, align:'center', toolbar: '#barDemo'}
            ]]
        });
    },
    look:function (key) {
        var _self=this,biyue=_self.biyue;
        my_open=layer.open({
            type: 1,
            anim:5,
            title:'json数据',
            content: $('#look'),
            area:['80%','80%'],
            success:function () {
                biyue.ajax({
                    url:'/redis/queryByKey',
                    data:{
                        key:key
                    },
                    fun:function (data) {
                        $("#look").JSONView(data);
                    }
                })
            },
            cancel:function () {
                $("#look").html("");
            }
        });

    },
    del:function (key) {
        var _self=this,biyue=_self.biyue;

        if(key===""||!key){
            layer.msg('key值不能为空！', {time: 2000, icon: 0});
            return;
        }
        layer.confirm('是否删除？', {
            btn: ['删除','取消'] //按钮
        }, function(){
            biyue.ajax({
                url:'/redis/delete',
                data:{
                    keys:key
                },
                fun:function (data) {
                    layer.msg('删除成功！', {time: 2000, icon: 1});
                    redis.getData();
                }
            })
        }, function(){

        });


    },
    tool:function () {
        var table = layui.table,_self=this;
        table.on('tool(table-list)', function(obj){
            var data = obj.data
                ,layEvent = obj.event;

            if(layEvent==="del"){
                _self.del(data.id);
            }else if(layEvent==="look"){
                _self.look(data.id);
            }
        });
    }
};

$(".search").click(function () {
    redis.getData();
});

$('#key').keydown(function(e){
    if(e.keyCode==13){
        redis.getData();
    }
});

$(".del").click(function () {
    var key=[];
    $(".biyue_main .layui-table-main [data-field=\"1\"] [type=\"checkbox\"]").each(function () {
        var bool=$(this).is(':checked');
        if(bool){
            key.push($(this).parents("tr").find("[data-field=\"id\"] div").html());
        }
    });
    console.log(key.join(","));
    redis.del(key.join(","));
});

$(".biyue_main").on('dblclick','[data-field="id"]',function () {
   var key=$(this).find("div").html();
   redis.look(key);
});

redis.tool();
redis.getData();