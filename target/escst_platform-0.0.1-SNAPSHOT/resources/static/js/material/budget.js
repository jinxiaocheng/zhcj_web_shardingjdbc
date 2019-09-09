var biyueList = {
    query:function () {
      return {
          name: $.trim($('[name="name"]').val()),
          constructionId:$('[name="site"]').val(),
          areaId: $('[name="area"]').val()
      }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList();
        table.loadTable({
            url:"/materialBudget/materialBudgetList"
            , upData:_self.query()
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'name', title: '材料名称'}
                , {field: 'modelName', title: '规格型号'}
                , {field: 'quantity', title: '数量'}
                , {field: 'unit', title: '单位'}
                , {field: 'amount', title: '材料总价(万元)'}
                , {field: 'updateTime', title: '更新时间'}
                , {field: 'terrName', title: '所属区域',width:180}
                , {field: 'constructionName', title: '工地名称',width:240}
                , {fixed: 'right', title:'操作',width:110, align:'center', toolbar: '#barDemo'}
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
        //工具
        layui.table.on('tool(table-list)', function(obj){
            var data = obj.data
                ,layEvent = obj.event
                ,type = data.type;
            if(layEvent==="edit"){
                biyue.open({
                    title:'编辑',
                    url:'/materialBudget/toEdit?id='+data.id
                })
            }
        })
    },
    toolsBtn: function () {
        //新增
        $("#add_link").click(function () {
            biyue.open({
                title:'新增',
                url:'/materialBudget/toAdd'
            })
        });
        //材料预算导入
        $("#import_link").click(function () {
            biyue.open({
                title:'材料预算导入',
                area:{
                    w:'600px',h:'300px'
                },
                url:'/materialBudget/toImport'
            })
        });
    }
};