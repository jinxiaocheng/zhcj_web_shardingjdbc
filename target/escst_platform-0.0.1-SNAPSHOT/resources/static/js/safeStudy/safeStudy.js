var biyueList = {
    query:function () {
        return {
            constructionId: $('[name="site"]').val(),
            areaId: $('[name="area"]').val(),
            type:type
        }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList(), form = layui.form;
        var cols =  [[
            {type: 'numbers',fixed: 'left'}
            , {field: 'areaName', title: '所属区域',width:120}
            , {field: 'constructionName', title: '工地名称'}
            , {field: 'theme', title: '主题'}
            , {field: 'startDate', title: '开始时间'}
            , {field: 'endDate', title: '结束时间'}
            , {field: 'contactPerson', title: '讲师'}
            , {field: 'place', title: (type === '1'?'培训地点':'演习地点')}
            , {field: 'remark', title: '备注'}
            , {fixed: 'right', title:'操作',width:100, align:'center', toolbar: '#barDemo'}
        ]];
        if(type !== '1'){
            cols =  [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'areaName', title: '所属区域',width:120}
                , {field: 'constructionName', title: '工地名称'}
                , {field: 'theme', title: '主题'}
                , {field: 'startDate', title: '开始时间'}
                , {field: 'endDate', title: '结束时间'}
                , {field: 'place', title: (type === '1'?'培训地点':'演习地点')}
                , {field: 'remark', title: '备注'}
                , {fixed: 'right', title:'操作',width:100, align:'center', toolbar: '#barDemo'}
            ]];
        }
        table.loadTable({
            url:"/safeStudy/queryByConstructionId"
            , upData: _self.query()
            , cols:cols
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
                    url:'/safeStudy/toView?type= ' + type +'&id=' + data.id
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
                url:'/safeStudy/toAdd?type=' + type
            })
        });
    }
};