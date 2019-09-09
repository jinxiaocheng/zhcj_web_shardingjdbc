var biyueList = {
    query:function () {
        return {
            personName: $.trim($('[name="name"]').val()),
            cardNum: $.trim($('[name="cardNumber"]').val()),
            positionWorkId: $('[name="job"]').attr("data-value")||"",
            projectCompanyId: $('[name="company"]').val(),
            constructionId: $('[name="site"]').val(),
            startDate: $('[name="startTime"]').val(),
            endDate: $('[name="endTime"]').val(),
            areaId: $('[name="area"]').val()
        }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList(), form = layui.form;
        table.loadTable({
            url:"/attendance/queryList"
            , upData: _self.query()
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'personName', title: '人员名称'}
                , {field: 'cardNum', title: '卡号'}
                , {field: 'attendanceDate', title: '打卡时间'}
                , {field: 'type', title: '进场/出场', templet:'#status'}
                , {field: 'positionWorktype', title: '岗位/工种'}
                , {field: 'teamName', title: '班组'}
                , {field: 'companyName', title: '所属公司',width:180}
                , {field: 'areaName', title: '所属区域',width:100}
                , {field: 'constructionName', title: '工地名称',width:180}
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
    getCompany:function (id) {
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
    toolsBtn: function () {
        var _self = this , table = biyue.tableList();
        //导出
        $("#export_link").click(function () {
            var link = ctx + '/attendance/export?';
            var prop = _self.query();
            for(var i in prop){
                link+=i+"="+prop[i]+"&";
            }
            window.location.href = link;
        });

    }
};