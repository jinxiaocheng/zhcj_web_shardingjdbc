var biyueList = {
    createTable: function () {
        var _self = this, table = biyue.tableList();
        table.loadTable({
            url:"/team/listData"
            , upData: {
                name: $.trim($('[name="itemName"]').val()),
                projectCompanyId: $('[name="company"]').val(),
                constructionId:$('[name="site"]').val(),
                areaId: $('[name="area"]').val()
            }
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'name', title: '班组名称'}
                , {field: 'projectCompanyName', title: '所属公司'}
                , {field: 'terrName', title: '所属区域',width:180}
                , {field: 'constructionName', title: '工地名称',width:240}
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
                where: {
                    name: $.trim($('[name="itemName"]').val()),
                    projectCompanyId: $('[name="company"]').val(),
                    constructionId:$('[name="site"]').val(),
                    areaId: $('[name="area"]').val()
                }
            })
        };
        //编辑
        layui.table.on('tool(table-list)', function(obj){
            var data = obj.data
                ,layEvent = obj.event;
            if(layEvent==="edit"){
                biyue.open({
                    title:'编辑',
                    url:'/team/toEdit?type=edit&id='+data.id
                })
            }
        })
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
        //新增
        $("#add_link").click(function () {
            biyue.open({
                title:'新增',
                url:'/team/toEdit?type=add'
            })
        });
    }
};