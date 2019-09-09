var biyueList = {
    query: function () {
        return {
            status: $('[name="status"]').val(),
            equipmentType: $('[name="equipmentType"]').val(),
            constructionId: $('[name="site"]').val(),
            areaId: $('[name="area"]').val()
        }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList();
        table.loadTable({
            url: "/taskAppointment/queryList?type=1"
            , upData: _self.query()
            , cols: [[
                {type: 'numbers', fixed: 'left'}
                , {field: 'isUrgentTask', title: '任务紧急', width: 100, templet: '#isUrgentTask'}
                , {field: 'equipmentType', title: '预约设备', width: 100, templet: '#equipmentType'}
                , {field: 'status', title: '任务状态', width: 100, templet: '#status'}
                , {field: 'appointmentDate', title: '预约时间'}
                , {field: 'remark', title: '任务说明'}
                , {field: 'projectStructureName', title: '检查部位'}
                , {field: 'projectCompanyName', title: '所属公司'}
                , {field: 'handlePerson', title: '处理人'}
                , {field: 'appointmentName', title: '预约人'}
                , {field: 'moblie', title: '预约人电话'}
                , {field: 'createTime', title: '创建时间'}
                , {field: 'createByName', title: '创建人'}
                , {field: 'constructionName', title: '工地名称'}
                , {fixed: 'right', title: '操作', width: 150, align: 'center', toolbar: '#barDemo'}
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
                    url: '/taskAppointment/toView?taskAppointmentId=' + data.id
                })
            } else if (layEvent === "operation") {
                var status = data.status;
                if (status === '1') {
                    biyue.open({
                        title: '处理任务',
                        url: '/taskAppointment/toDispose?type=1&taskAppointmentId=' + data.id
                    })
                } else if (status === '2' ) {
                    biyue.open({
                        title: '审核任务',
                        url: '/taskAppointment/toDispose?type=2&taskAppointmentId=' + data.id
                    })
                } else if (status === '3') {
                    biyue.open({
                        title: '提交任务',
                        url: '/taskAppointment/toDispose?type=3&taskAppointmentId=' + data.id
                    })
                } else if (status === '4') {
                    layer.msg('审核不通过!', {time: 2000, icon: 0});
                } else if (status === '5') {
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
                url: '/taskAppointment/toAdd?constructionId='+$('[name="site"]').val()
            })
        });
        //历史任务
        $("#link").click(function () {
            biyue.open({
                title: '历史任务',
                area:{w:'80%',h:'80%'},
                url: '/taskAppointment/list?type=2'
            })
        });
    }
};