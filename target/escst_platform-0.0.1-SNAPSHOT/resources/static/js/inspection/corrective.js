var biyueList = {
    query: function () {
        return {
            correctStartDate: $.trim($('[name="startTime"]').val()),
            correctEndDate: $.trim($('[name="endTime"]').val()),
            constructionId: $('[name="site"]').val(),
            areaId: $('[name="area"]').val(),
            correctiveStatus: $('[name="status"]').val(),
            projectCompanyId: $('[name="company"]').val(),
            processingOpinion: 3,
            contactId: userId,
            type: type
        }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList(), form = layui.form;
        table.loadTable({
            url: "/inspection/queryByConstructionId"
            , upData: _self.query()
            , cols: [[
                {type: 'numbers', fixed: 'left'}
                , {field: 'correctiveCompletionDate', title: '限期整改日期'}
                , {field: 'orderNo', title: '单号'}
                , {field: 'items', title: '检查项'}
                , {field: 'contactName', title: '责任人'}
                , {field: 'teamName', title: '班组'}
                , {field: 'projectCompanyName', title: '所属公司'}
                , {field: 'projectStructureName', title: '检查部位'}
                , {field: 'createUserName', title: '检查人', width: 120}
                , {field: 'businessDate', title: '检查日期', width: 120}
                , {field: 'correctiveStatus', title: '整改状态', width: 100, templet: '#correctiveStatus'}
                , {fixed: 'right', title: '操作', width: 170, align: 'center', toolbar: '#barDemo'}
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
        //操作
        layui.table.on('tool(table-list)', function (obj) {
            var data = obj.data
                , layEvent = obj.event;
            if (layEvent === "view") {
                biyue.open({
                    title: '查看',
                    url: '/inspection/toCorrectiveHandle?type=' + type + '&inspectionId=' + data.id
                    + '&correctiveStatus=' + data.correctiveStatus
                    + "&id=" + data.id
                    + "&constructionId=" + data.constructionId
                    + "&typePage=view"
                })
            } else if (layEvent === "export") {
                window.location.href = ctx + '/inspection/exportWord?id=' + data.id + '&type=' + type;
            } else if (layEvent === "deal") {
                window.check_data = data;
                biyue.open({
                    title: '处理',
                    url: '/inspection/toCorrectiveHandle?type=' + type + '&inspectionId=' + data.id + '&correctiveStatus=' + data.correctiveStatus + "&id=" + data.id + "&constructionId=" + data.constructionId
                })
            }else if (layEvent === "disabled") {
                layer.msg('无权限处理该条信息!', {time: 2000, icon: 0});
            }
        })
    },
    getCompany: function (id) {
        var form = layui.form;
        //获取合作方
        biyue.ajax({
            url: '/team/getProjectCompanyList',
            data: {
                constructionId: id
            },
            fun: function (data) {
                var $list = "<option value=\"\">选择公司</option>"
                    , dataV = data.value;

                for (var i = 0; i < dataV.length; i++) {
                    $list += '<option value="' + dataV[i].id + '">' + dataV[i].name + '</option>';
                }

                $("[name='company']").html($list);
                form.render('select');
            }
        })
    },
    getItems: function (fun) {
        var form = layui.form;
        biyue.ajax({
            url: '/inspection/querySafetyCheckItems?type=' + type,
            fun: function (data) {
                var $list = " <option value=\"\">选择检查项</option>"
                    , dataV = data.value;
                for (var i = 0; i < dataV.length; i++) {
                    $list += '<option value="' + dataV[i].id + '">' + dataV[i].name + '</option>';
                }

                $("[name='check_item']").html($list);
                form.render('select');
                fun && fun();
            }
        })
    },
    toolsBtn: function () {
        var _self = this, table = biyue.tableList();
        //新增
        $("#add_link").click(function () {
            biyue.open({
                title: '新增',
                url: '/inspection/toAdd?type=' + type
            })
        });
    }
};