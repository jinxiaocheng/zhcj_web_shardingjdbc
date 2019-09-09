var biyueList = {
    query:function () {
      return{
          name: $.trim($('[name="name"]').val()),
          number: $.trim($('[name="number"]').val()),
          constructionId:$('[name="site"]').val(),
          areaId: $('[name="area"]').val()
      }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList();
        table.loadTable({
            url:"/equipment/queryEnvironmentEquipmentList"
            , upData:_self.query()
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'name', title: '设备名称'}
                , {field: 'number', title: '设备编号',templet: '#status'}
                , {field: 'areaName', title: '所属区域',width:180}
                , {field: 'constructionName', title: '工地名称',width:240}
                , {fixed: 'right', title:'操作',width:120, align:'center', toolbar: '#barDemo'}
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
                    area:{
                      w:"80%",
                      h:"80%"
                    },
                    url:'/environment/toRealtime?type=edit&id='+data.id+"&operationAuthority="+biyue.urlData.operationAuthority
                })
            }
        })
    },
    toolsBtn: function () {

    }
};