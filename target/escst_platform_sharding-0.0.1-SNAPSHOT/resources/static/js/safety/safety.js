var biyueList = {
    query:function () {
        return {
            constructionId: $('[name="site"]').val(),
            areaId: $('[name="area"]').val()
        }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList(), form = layui.form;
        table.loadTable({
            url:"/riskOperation/listData"
            , upData: _self.query()
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'areaName', title: '所属区域',width:120}
                , {field: 'constructionName', title: '工地名称'}
                , {field: 'category', title: '类别'}
                , {field: 'title', title: '标题'}
                , {field: 'createTime', title: '时间'}
                , {field: 'content', title: '内容'}
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
                where: _self.query()
            })
        };
        //操作
        layui.table.on('tool(table-list)', function(obj){
            var data = obj.data
                ,layEvent = obj.event;
            if(layEvent==="view"){
                biyue.open({
                    title:'查看',
                    url:'/riskOperation/toView?id='+data.id
                })
            }else if(layEvent==="del"){
                layer.confirm('是否删除这条记录?', {
                    title: '提示',
                    icon:2,
                    btn: ['确定','关闭'],
                    yes:function(){
                        biyue.ajax({
                            url: '/riskOperation/delete?id=' + data.id,
                            fun:function () {
                                layer.closeAll();
                                layer.msg('删除成功', {time: 2000, icon: 0});
                                searchIf();
                            }
                        })
                    }
                });
            }else if(layEvent==="edit"){
                biyue.open({
                    title:'编辑',
                    area:{w:'920px',h:'560px'},
                    url:'/riskOperation/toAdd?id='+data.id+"&type=edit"
                })
            }
        })
    },
    toolsBtn: function () {
        var _self = this , table = biyue.tableList();
        //新增
        $("#add_link").click(function () {
            biyue.open({
                title:'新增',
                area:{w:'920px',h:'560px'},
                url:'/riskOperation/toAdd'
            })
        });
    }
};