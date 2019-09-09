var biyueList = {
    createTable: function () {
        var _self = this, table = biyue.tableList();
        table.loadTable({
            url: '/hook/getAllHookEquipmentByConstruction'
            , upData: {
                name: $.trim($('[name="name"]').val()),
                number: $.trim($('[name="number"]').val()),
                areaId: $('[name="area"]').val(),
                constructionId: $('[name="site"]').val()
            }
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'areaName', title: '区域',width:180}
                , {field: 'constructionName', title: '工地',width:180}
                , {field: 'name', title: '设备名称'}
                , {field: 'number', title: '设备编号'}
                , {field: 'manufacturer', title: '生产厂家',width:180}
                , {field: 'createTime', title: '创建时间',width:180}
                , {fixed: 'right', title:'操作',width:160, align:'center', toolbar: '#barDemo'}
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
                    name: $.trim($('[name="name"]').val()),
                    number: $.trim($('[name="number"]').val()),
                    areaId: $('[name="area"]').val(),
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
                    url:'/route/lifting/equipmentView?type=view&id='+data.id
                })
            }else if(layEvent==="edit"){
                biyue.open({
                    title:'编辑',
                    url:'/route/lifting/equipmentAdd?type=edit&id='+data.id
                })
            }else if(layEvent==="del"){
                layer.confirm('是否删除？', function(index){
                    biyue.ajax({
                        url:'/hook/saveHookEquipment',
                        data:{
                            status:2,
                            id:data.id
                        },
                        fun:function (data) {
                            layer.msg('删除成功!', {icon: 1});
                            table.search({
                                pageBool:true
                            });
                        }
                    })
                });
            }
        })
    },
    toolsBtn: function () {
        var laydate = layui.laydate;
        //新增
        $("#add_link").click(function () {
            biyue.open({
                title:'新增',
                url:'/route/lifting/equipmentAdd?type=add'
            })
        });
    }
};