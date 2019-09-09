var biyueList = {
    query:function () {
        return {
            plateNo: $.trim($('[name="name"]').val()),
            constructionId: $('[name="site"]').val(),
            startTime: $('[name="startTime"]').val(),
            endTime: $('[name="endTime"]').val(),
            areaId: $('[name="area"]').val()
        }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList(), form = layui.form;
        table.loadTable({
            url:"/carPass/carList"
            , upData: _self.query()
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'plateNo', title: '车牌号码'}
                , {field: 'direction', title: '进场/出场', templet:'#status'}
                , {field: 'passTime', title: '通行时间'}
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
                where: _self.query()
            })
        };
        //工具栏
        layui.table.on('tool(table-list)', function(obj){
            var data = obj.data
                ,layEvent = obj.event;
            if(layEvent==="view"){
                biyue.open({
                    title:'查看',
                    url:'/carPass/view?type=view&id='+data.id
                })
            }
        })

    }
};