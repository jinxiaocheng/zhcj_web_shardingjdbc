var biyueList = {
    query:function () {
        return {
            startDate: $.trim($('[name="startTime"]').val()),
            endDate: $.trim($('[name="endTime"]').val()),
            constructionId: $('[name="site"]').val(),
            belongArea: $('[name="area"]').val()
        }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList(), form = layui.form;
        table.loadTable({
            url:"/attendaceCountController/queryList"
            , upData: _self.query()
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'areaName', title: '所属区域',width:120}
                , {field: 'constructionName', title: '工地名称',width:240}
                , {field: 'teamName', title: '班组名称'}
                , {field: 'attendanceDate', title: '考勤日期'}
                , {field: 'count', title: '出勤人数'}
                , {fixed: 'right', title:'操作',width:100, align:'center', toolbar: '#barDemo'}
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
        //编辑
        layui.table.on('tool(table-list)', function(obj){
            var data = obj.data
                ,layEvent = obj.event;
            if(layEvent==="edit"){
                biyue.open({
                    title:'编辑',
                    url:'/attendaceCountController/toedit?type=edit&id='+data.id
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
                url:'/attendaceCountController/tosave'
            })
        });
        //导出
        $("#export_link").click(function () {
            biyue.open({
                title:'导出',
                url:'/attendaceCountController/toExport'
            })
        });
    }
};