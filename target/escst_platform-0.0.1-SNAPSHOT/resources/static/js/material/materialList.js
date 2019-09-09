var biyueList = {
    query:function () {
        return {
            approachDateStart: $.trim($('[name="startTime"]').val()),
            approachDateEnd: $.trim($('[name="endTime"]').val()),
            constructionId: $('[name="site"]').val(),
            belongArea: $('[name="area"]').val(),
            name: $.trim($('[name="name"]').val())
        }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList(), form = layui.form;
        table.loadTable({
            url:"/materialApproach/materialApproachList"
            , upData: _self.query()
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'approachDate', title: '进场日期'}
                , {field: 'name', title: '材料名称'}
                , {field: 'model', title: '规则型号'}
                , {field: 'unit', title: '计量单位'}
                , {field: 'quantity', title: '数量'}
                , {field: 'manufacturer', title: '生产厂家'}
                , {field: 'usePosition', title: '使用部位'}
                , {field: 'areaName', title: '所属区域',width:120}
                , {field: 'constructionName', title: '工地名称',width:240}
                , {fixed: 'right', title:'操作',width:130, align:'center', toolbar: '#barDemo'}
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
        //编辑
        layui.table.on('tool(table-list)', function(obj){
            var data = obj.data
                ,layEvent = obj.event;
            if(layEvent==="edit"){
                biyue.open({
                    title:'编辑',
                    url:'/materialApproach/addMaterialApproach?type=view&id='+data.id
                })
            }else if(layEvent==="view"){
                biyue.open({
                    title:'查看',
                    url:'/materialApproach/toApproachDetailInfo?id='+data.id
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
                url:'/materialApproach/addMaterialApproach'
            })
        });
    }
};