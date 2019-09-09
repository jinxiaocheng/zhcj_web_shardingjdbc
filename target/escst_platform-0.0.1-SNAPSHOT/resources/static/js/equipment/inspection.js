var biyueList = {
    query:function () {
      return {
          results: $.trim($('[name="results"]').val()),
          number: $.trim($('[name="number"]').val()),
          constructionId:$('[name="site"]').val(),
          areaId: $('[name="area"]').val()
      }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList();
        table.loadTable({
            url:"/equipment/queryEquipmentInspectionList"
            , upData:_self.query()
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'equipmentName', title: '设备名称'}
                , {field: 'equipmentNumber', title: '设备编号'}
                , {field: 'equipmentModel', title: '设备型号'}
                , {field: 'manufacturer', title: '厂家'}
                , {field: 'leasingCompany', title: '租赁公司'}
                , {field: 'results', title: '结果',templet: '#results'}
                , {field: 'areaName', title: '所属区域',width:180}
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
            if(layEvent==="view"){
                biyue.open({
                    title:'查看',
                    area:{w:'80%',h:'80%'},
                    url:'/equipment/inspection/toView?id=' + data.id
                })
            }
        })
    },
    toolsBtn: function () {

    }
};