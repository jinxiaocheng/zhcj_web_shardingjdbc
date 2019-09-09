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
            url:"/lifter/queryRealtimeList"
            , upData:_self.query()
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'time', title: '采集时间'}
                , {field: 'height', title: '高度(m)'}
                , {field: 'weight', title: '重量(KG)'}
                , {field: 'obliquity', title: '倾斜(°)'}
                , {field: 'speed', title: '速度(m/s)'}
                , {field: 'peopleNum', title: '人数'}
                , {field: 'frontDoorLockState', title: '前门锁报警开关'}
                , {field: 'backDoorLockState', title: '后门锁报警开关'}
                , {field: 'highLimitState', title: '高限位报警开关'}
                , {field: 'lowLimitState', title: '低限位报警开关'}
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
                url:'/route/lifter/historyList?type=warning&id='+$("#equipmentId").val(),
                area:{w:'80%',h:'80%'},
                title:'预警数据'
            })
        });
        //查看历史数据
        $("#history_data").click(function () {
            biyue.open({
                url:'/route/lifter/historyList?id='+$("#equipmentId").val(),
                area:{w:'80%',h:'80%'},
                title:'历史数据'
            })
        });
        //导出
        $("#export_link").click(function () {
            window.location.href = ctx + '/equipment/exportExcel?type=4&startDate='+$('#startDate').val()+'&endDate='+$('#endDate').val()+'&equipmentId='+$('#equipmentId').val();
        });
    }
};