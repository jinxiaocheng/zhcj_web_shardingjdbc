var biyueList = {
    query:function () {
        return {
            constructionId: $('[name="site"]').val(),
            belongArea: $('[name="area"]').val(),
            name: $.trim($('[name="name"]').val())
        }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList(), form = layui.form;
        table.loadTable({
            url:"/materialAcquisition/materialAcquisitionList"
            , upData: _self.query()
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'recipientsDate', title: '领用日期'}
                , {field: 'name', title: '材料名称'}
                , {field: 'model', title: '规则型号'}
                , {field: 'unit', title: '计量单位'}
                , {field: 'quantity', title: '领用数量'}
                , {field: 'manufacturer', title: '生产厂家'}
                , {field: 'usePosition', title: '使用部位'}
                , {field: 'usePerson', title: '领用人'}
                , {field: 'mobile', title: '领用人电话'}
                , {field: 'availableQuantity', title: '可用数量'}
                , {field: 'areaName', title: '所属区域',width:120}
                , {field: 'constructionName', title: '工地名称',width:240}]]
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
        })
    },
    toolsBtn: function () {
        var _self = this , table = biyue.tableList();
        //新增
        $("#add_link").click(function () {
            biyue.open({
                title:'新增',
                url:'/materialAcquisition/toAdd'
            })
        });
    }
};