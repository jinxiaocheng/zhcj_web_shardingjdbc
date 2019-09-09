var biyueList = {
    query: function () {
        return {
            status: $('[name="status"]').val(),
            constructionId: $('[name="site"]').val(),
            areaId: $('[name="area"]').val()
        }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList();
        table.loadTable({
            url: "/projectTask/queryList"
            , upData: _self.query()
            , cols: [[
                {type: 'numbers', fixed: 'left'}
                , {field: 'statusTask', title: '任务状态', width: 100, templet: '#statusTask'}
                , {field: 'orderNo', title: '单号'}
                , {field: 'remark', title: '任务说明'}
                , {field: 'projectStructureName', title: '检查部位'}
                , {field: 'projectCompanyName', title: '所属公司'}
                , {field: 'examinerName', title: '派发人'}
                , {field: 'contactsName', title: '处理人'}
                , {field: 'mobile', title: '处理人电话'}
                , {field: 'createTime', title: '创建时间'}
                , {field: 'createByName', title: '创建人'}
                , {field: 'constructionName', title: '工地名称', width: 240}
                , {fixed: 'right', title: '操作', width: 140, align: 'center', toolbar: '#barDemo'}
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
        layui.table.on('tool(table-list)', function (obj) {
            var data = obj.data
                , layEvent = obj.event;
            window.check_data=data;
            if (layEvent === "view") {
                biyue.open({
                    title: '查看',
                    url: '/projectTask/toView?taskId=' + data.id
                })
            } else if (layEvent === "operation") {
                var status = data.status;
                if (status === 1) {
                    biyue.open({
                        title: '任务处理',
                        url: '/projectTask/toDispose?type=1&taskId=' + data.id
                    })
                } else if (status === 2 || status === 4) {
                    biyue.open({
                        title: '提交任务',
                        url: '/projectTask/toDispose?type=2&taskId=' + data.id
                    })
                } else if (status === 3) {
                    biyue.open({
                        title: '检查任务',
                        url: '/projectTask/toDispose?type=3&taskId=' + data.id
                    })
                } else if (status === 5) {
                    layer.msg('任务已完成!', {time: 2000, icon: 0});
                }
            }
        })
    },
    toolsBtn: function () {
        //新增
        $("#add_link").click(function () {
            biyue.open({
                title: '新增',
                area:{w:'80%',h:'80%'},
                url: '/projectTask/toAdd?type=1'
            })
        });
    }
};