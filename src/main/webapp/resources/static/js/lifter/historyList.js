var biyueList = {
    query:function () {
        return {
            startTime: $.trim($('[name="startTime"]').val()),
            endTime: $.trim($('[name="endTime"]').val()),
            equipmentId: biyue.urlSearch().id,
            type:4
        }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList(), form = layui.form,url = "";
        if(biyue.urlSearch().type==="warning"){
            url="/towerCrane/listWarningData"
        }else{
            url="/lifter/listLifterHistoryData"
        }
        table.loadTable({
            url:url
            , upData: _self.query()
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

    }
};