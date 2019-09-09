var biyueList = {
    query:function () {
        return {
            id:biyue.urlSearch().id
        }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList(), form = layui.form;
        table.loadTable({
            url:"/equipment/queryByEquipmentId"
            , upData: _self.query()
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'constructionName', title: '工地名称'}
                , {field: 'equipmentName', title: '设备名称'}
                , {field: 'userName', title: '用户名'}
                , {field: 'idCard', title: '身份证号'}
                , {field: 'result', title: '识别结果',templet: '#status'}
                , {field: 'score', title: '识别分数'}
                , {field: 'collectionTime', title: '采集时间',width:160}
            ]]
            , height: 'full-20'
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