var biyueList={
    biyue:new biYue(),
    createTable:function () {
        var _self=this,biyue=_self.biyue,table=biyue.tableList();
        table.loadTable({
            url: '/hookData/listHistoryData'
            , upData: {
                hookId: biyue.urlSearch().id,
                startTime:$("[name=\"startTime\"]").val(),
                endTime:$("[name=\"endTime\"]").val()
            }
            , cols: [[
                {type: 'numbers', fixed: 'left'}
                , {field: 'acquisitionTime', title: '采集时间'}
                , {field: 'extent', title: '幅度(m)'}
                , {field: 'height', title: '高度(m)'}
            ]]
            ,height:'full-90'
        });
        //搜索
        $("#search").on('click',function () {
            searchIf();
        });
        window.searchIf=function () {
            table.search({
                where:{
                    hookId: biyue.urlSearch().id,
                    startTime:$("[name=\"startTime\"]").val(),
                    endTime:$("[name=\"endTime\"]").val()
                }
            })
        };

    }
};



