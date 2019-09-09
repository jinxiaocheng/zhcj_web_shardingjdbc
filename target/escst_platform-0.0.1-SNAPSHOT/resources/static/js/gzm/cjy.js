var biyueList = {
    createTable: function () {
        var _self = this, table = biyue.tableList();
        table.loadTable({
            url: '/collector/queryCollectorList'
            , upData: {
                name: $('[name="name"]').val(),
                userId:userId,
                constructionId: $('[name="site"]').val()
            }
            , cols: [[
                {type: 'numbers'}
                , {field: 'name', title: '采集仪名称'}
                , {field: 'number', title: '采集仪编号'}
                , {field: 'status', title: '状态', templet: '#status'}
                , {fixed: 'right', title:'操作',width:80, align:'center', toolbar: '#barDemo'}
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
            if(layEvent==="edit"){
                biyue.open({
                    type: 1,
                    title: "编辑",
                    content: $(".editCJY"),
                    btn:['确定','关闭'],
                    area:{
                        w:'400px',
                        h:'260px'
                    },
                    yes:function (index,layero) {
                        var name = $("#name").val(),number = $("#number").val();

                        if(!name){
                            layer.msg('请输入采集仪名称！', {time: 2000, icon: 5});
                            return;
                        }else if(!number){
                            layer.msg('请输入采集仪编号！', {time: 2000, icon: 5});
                            return;
                        }
                        switchBtn(name,number,data.id);
                        layer.close(index);
                    },
                    cancel:function () {
                        $(".editCJY").hide();
                    },
                    end:function () {
                        $(".editCJY").hide();
                    },
                    success:function () {
                        $("#name").val(data.name);
                        $("#number").val(data.number);
                    }
                });
            }
        })
        //启用/停用
        function switchBtn(name,number,id){
            biyue.ajax({
                url:"/collector/disableOrEnable",
                data:{
                    name:name,
                    number:number,
                    id:id
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
        //新增
        $("#add_link").click(function () {
            biyue.open({
                title:'新增',
                url:'/route/gzm/cjyAdd?type=add'
            })
        });

    }
};