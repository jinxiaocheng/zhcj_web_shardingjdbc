var biyueList = {
    query:function () {
        return {
            startDate: $.trim($('[name="startTime"]').val()),
            endDate: $.trim($('[name="endTime"]').val()),
            equipmentId:biyue.urlSearch().id
        }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList(), form = layui.form;
        table.loadTable({
            url:"/environment/queryRealtimeList"
            , upData: _self.query()
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'time', title: '采集时间',width:180}
                , {field: 'pm10', title: 'PM10(ug/m³)'}
                , {field: 'pm25', title: 'PM2.5(ug/m³)'}
                , {field: 'noise', title: '噪音(dB)'}
                , {field: 'temperature', title: '温度(℃)'}
                , {field: 'humidity', title: '湿度(%)'}
                , {field: 'windSpeed', title: '风速(m/s)'}
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
        var _self = this , table = biyue.tableList();
        //导出
        $("#export_link").click(function () {
            biyue.ajax({
                url:'/environment/exportExcel',
                data: _self.query(),
                fun: function (data) {
                    var filePath = data.value;
                    window.location.href=path+ '/' + filePath;
                }
            });
        });
    }
};