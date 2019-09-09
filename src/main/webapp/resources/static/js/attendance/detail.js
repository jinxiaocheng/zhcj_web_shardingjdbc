var biyueList = {
    query:function () {
        return {
            personName: $.trim($('[name="name"]').val()),
            companyId:biyue.urlSearch().id,
            positionWorktype: $('[name="job"]').attr("data-value")||"",
            startDate: $('[name="startTime"]').val(),
            endDate: $('[name="endTime"]').val()
        }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList(), form = layui.form;
        table.loadTable({
            url:"/attendance/queryList"
            , upData: _self.query()
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'personName', title: '姓名'}
                , {field: 'companyName', title: '所属公司',width:180}
                , {field: 'positionWorktype', title: '岗位/工种'}
                , {field: 'teamName', title: '班组'}
                , {field: 'attendanceDate', title: '打卡时间',width:100}
                , {field: 'type', title: '进场/出场', templet:'#status'}
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

    }
};