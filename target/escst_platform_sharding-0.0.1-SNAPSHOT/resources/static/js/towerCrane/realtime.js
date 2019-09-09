var biyueList = {
    query:function () {
      return {
          equipmentId: $.trim($('#equipmentId').val()),
          startDate: $.trim($('#startDate').val()),
          endDate: $.trim($('#endDate').val())
      }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList();
        table.loadTable({
            url:"/towerCrane/queryRealtimeList"
            , upData:_self.query()
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'time', title: '采集时间'}
                , {field: 'angle', title: '角度(°)'}
                , {field: 'extent', title: '幅度(m)'}
                , {field: 'height', title: '高度(m)'}
                , {field: 'weight', title: '重量(t)'}
                , {field: 'windSpeed', title: '风速(m/s)'}
                , {field: 'obliquity', title: '倾角(°)'}
                , {field: 'percent', title: '力矩(%)'}
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
    },
    toolsBtn: function () {
        //查看预警数据
        $("#warning_data").click(function () {
            biyue.open({
                url:'/towerCrane/historyList?type=warning&id='+$("#equipmentId").val(),
                area:{w:'80%',h:'80%'},
                title:'预警数据'
            })
        });
        //查看历史数据
        $("#history_data").click(function () {
            biyue.open({
                url:'/towerCrane/historyList?id='+$("#equipmentId").val(),
                area:{w:'80%',h:'80%'},
                title:'历史数据'
            })
        });
        //导出
        $("#export_link").click(function () {
            window.location.href = ctx + '/equipment/exportExcel?type=3&startDate='+$('#startDate').val()+'&endDate='+$('#endDate').val()+'&equipmentId='+$('#equipmentId').val();
        });
    }
};