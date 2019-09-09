var biyueList = {
    query:function () {
      return{
          name: $.trim($('[name="name"]').val()),
          constructionId:$('[name="site"]').val(),
          belongArea: $('[name="area"]').val()
      }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList();
        table.loadTable({
            url:"/equipment/queryMaintenanceList"
            , upData:_self.query()
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'name', title: '设备名称'}
                , {field: 'number', title: '设备编号'}
                , {field: 'maintenanceDate', title: '维修保养日期',width:170}
                , {field: 'remark', title: '备注信息'}
                , {field: 'type', title: '设备状态', templet:'#type'}
                , {field: 'createTime', title: '创建时间',width:170}
                , {field: 'createBy', title: '创建人'}
                , {field: 'belongAreaName', title: '所属区域',width:180}
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
        //工具栏
        layui.table.on('tool(table-list)', function(obj){
            var data = obj.data
                ,layEvent = obj.event;
            window.check_data=data;
            if(layEvent==="view"){
                biyue.open({
                    title:'查看',
                    url:'/equipment/toView?type=3&equipmentId='+data.id
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
                url:'/equipment/toAdd?type=2'
            })
        });
    }
};