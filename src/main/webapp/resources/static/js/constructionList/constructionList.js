var biyueList = {
    query: function () {
        return {
            development: $.trim($('[name="name"]').val()),
            building: $.trim($('[name="building"]').val()),
            id: $('[name="site"]').val(),
            areaId: $('[name="area"]').val()
        }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList();
        table.loadTable({
            url: "/construction/constructionList"
            , upData: _self.query()
            , cols: [[
                {type: 'numbers', fixed: 'left'}
                , {field: 'name', title: '工程名称', width: 100}
                , {field: 'type', title: '工地类型', width: 100, templet: '#type'}
                , {field: 'cityName', title: '所属市', width: 100}
                , {field: 'areaName', title: '所属区'}
                , {field: 'development', title: '建设单位'}
                , {field: 'building', title: '施工单位'}
                , {field: 'supervision', title: '监理单位'}
                , {field: 'projectManager', title: '项目经理'}
                , {field: 'planContractStartDate', title: '计划开工日期'}
                , {field: 'planContractEndDate', title: '计划竣工日期'}
                , {field: 'isOnline', title: '是否在线', templet: '#isOnline'}
                , {field: 'status', title: '状态', templet: '#status'}
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
                    url: '/construction/toConstructionDetail?constructionId=' + data.constructionId
                })
            } else if (layEvent === "edit") {
                biyue.open({
                    title: '编辑',
                    area:{w:'80%',h:'80%'},
                    url: '/construction/toConstructionAdd?type=1&constructionId='
                    + data.constructionId+"&constructLicenseId="+data.constructLicenseId
                })
            }
        })
    },
    toolsBtn: function () {
        //新增
        $("#add_link").click(function () {
            biyue.open({
                title: '新增',
                area:{w:'80%',h:'80%'},
                url: '/construction/toConstructionAdd?type=0'
            })
        });
    }
};