var biyueList = {
    createTable: function () {
        var _self = this, table = biyue.tableList();
        table.loadTable({
            url: '/flow/queryListByConstructionId'
            , upData: {
                name: $('[name="name"]').val(),
                userId:userId,
                constructionId: $('[name="site"]').val()
            }
            , cols: [[
                {type: 'numbers'}
                , {field: 'constructionName', title: '工地名称'}
                , {field: 'name', title: '流水段名称'}
                , {field: 'startTime', title: '开始测量时间',width:180}
                , {field: 'endTime', title: '结束测量时间',width:180}
                , {field: 'qty', title: '采集仪数量',width:120}
                , {field: 'status', title: '状态', templet: '#status',width:100}
                , {fixed: 'right', title:'操作',width:170, align:'center', toolbar: '#barDemo'}
            ]]
            , height: 'full-90'
        });
        //搜索
        $("#search").on('click', function () {
            searchIf();
        });
        window.searchIf = function () {
            table.search({
                where: {
                    name: $('[name="name"]').val(),
                    constructionId: $('[name="site"]').val()
                }
            })
        };
        //编辑
        layui.table.on('tool(table-list)', function(obj){
            var data = obj.data
                ,layEvent = obj.event;
            if(layEvent==="view"){
                biyue.open({
                    title:'查看',
                    url:'/route/gzm/lsdView?type=view&id='+data.id
                })
            }else if(layEvent==="edit"){
                biyue.open({
                    title:'编辑',
                    url:'/route/gzm/lsdAdd?type=edit&id='+data.id
                })
            }else if(layEvent==="close"){
                biyue.open({
                    type: 1,
                    title: "结束测量",
                    content: $(".endCd"),
                    btn:['确定','关闭'],
                    area:{
                        w:'400px',
                        h:'260px'
                    },
                    yes:function (index,layero) {
                        var end = $("#end").val(),start = $("#start").val();
                        var stdt=new Date(start.replace("-","/"));
                        var etdt=new Date(end.replace("-","/"));

                        if(!start){
                            layer.msg('请选择开始测量时间！', {time: 2000, icon: 5});
                            return;
                        }else if(!end){
                            layer.msg('请选择结束测量时间！', {time: 2000, icon: 5});
                            return;
                        }else if(stdt>etdt){
                            layer.msg('结束时间需大于开始时间！', {time: 2000, icon: 5});
                            return;
                        }
                        switchBtn(start,end,data.id);
                        layer.close(index);
                    },
                    cancel:function () {
                        $(".endCd").hide();
                    },
                    end:function () {
                        $(".endCd").hide();
                    },
                    success:function () {
                        biyue.ajax({
                            url:"/flow/getTime",
                            data:{
                                id:data.id
                            },
                            fun:function (data) {
                                var dataV = data.value;
                                biyue.timeSD({
                                    valueS:dataV.startTime,
                                    valueE:dataV.endTime||new Date()
                                })
                            }
                        });

                    }
                });

            }
        })
        //启用/停用
        function switchBtn(start,end,id){
            biyue.ajax({
                url:"/flow/save",
                data:{
                    status:2,
                    id:id,
                    startTime:start,
                    endTime:end
                },
                fun:function () {
                    table.search({
                        pageBool:true
                    })
                }
            })
        }
    },
    toolsBtn: function () {
        var laydate = layui.laydate;
        //新增
        $("#add_link").click(function () {
            biyue.open({
                title:'新增',
                url:'/route/gzm/lsdAdd?type=add'
            })
        });
    }
};