var biyueList = {
    query:function () {
      return {
          name: $.trim($('[name="name"]').val()),
          number: $.trim($('[name="number"]').val()),
          constructionId:$('[name="site"]').val(),
          areaId: $('[name="area"]').val()
      }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList();
        table.loadTable({
            url:"/equipment/queryMonitorEquipmentList"
            , upData:_self.query()
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'name', title: '设备名称'}
                , {field: 'number', title: '设备编号'}
                , {field: 'type', title: '设备类型',templet: '#type'}
                , {field: 'areaName', title: '所属区域',width:180}
                , {field: 'constructionName', title: '工地名称',width:240}
                , {fixed: 'right', title:'操作',width:150, align:'center', toolbar: '#barDemo'}
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
                var url = "";
                if (type === 3) {
                    url = '/towerCrane/toRealtime?id=' + data.id;
                }
                else if (type === 4) {
                    url = '/lifter/toRealtime?id=' + data.id;
                }
                else if (type === 12) {
                    url = '/unload/toRealtime?id=' + data.id;
                }
                else {
                    layer.msg('该设备类型暂时不支持查看实时数据!', {time: 2000, icon: 0});
                    return;
                }
                biyue.open({
                    title:'查看',
                    area:{w:'80%',h:'80%'},
                    url:url
                })
            }else if(layEvent==="faceCount"){
                biyue.open({
                    title:'人脸数据',
                    area:{w:'80%',h:'80%'},
                    url:'/equipment/toFaceList?id='+data.id
                })
            }
        })
    },
    toolsBtn: function () {

    }
};