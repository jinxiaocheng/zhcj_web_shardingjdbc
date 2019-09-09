var biyueList = {
    query:function () {
        return {
            startTime: $.trim($('[name="startTime"]').val()),
            endTime: $.trim($('[name="endTime"]').val()),
            equipmentId: biyue.urlSearch().id,
            type:3
        }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList(), form = layui.form,url = "";
        if(biyue.urlSearch().type==="warning"){
            url="/towerCrane/listWarningData"
        }else{
            url="/towerCrane/listHistoryData"
        }
        table.loadTable({
            url:url
            , upData: _self.query()
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

    }
};