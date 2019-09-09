var biyueList = {
    createTable: function () {
        var _self = this, table = biyue.tableList();
        table.loadTable({
            url:"/projectCompany/listData"
            , upData: {
                name: $.trim($('[name="name"]').val()),
                type: $('[name="type"]').val(),
                constructionId:$('[name="site"]').val(),
                areaId: $('[name="area"]').val()
            }
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'name', title: '公司名称'}
                , {field: 'type', title: '公司类型',templet: '#status'}
                , {field: 'terrName', title: '所属区域',width:180}
                , {field: 'constructionName', title: '工地名称',width:240}
                , {fixed: 'right', title:'操作',width:100, align:'center', toolbar: '#barDemo'}
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
                    type: $('[name="type"]').val(),
                    constructionId:$('[name="site"]').val(),
                    areaId: $('[name="area"]').val()
                }
            })
        };
        //编辑
        layui.table.on('tool(table-list)', function(obj){
            var data = obj.data
                ,layEvent = obj.event;
            if(layEvent==="edit"){
                biyue.open({
                    title:'编辑',
                    url:'/projectCompany/toAdd?type=edit&id='+data.id
                })
            }
        })
    },
    toolsBtn: function () {
        //新增
        $("#add_link").click(function () {
            biyue.open({
                title:'新增',
                url:'/projectCompany/toAdd'
            })
        });
    }
};