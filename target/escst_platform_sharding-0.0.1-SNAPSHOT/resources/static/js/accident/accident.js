var biyueList = {
    query:function () {
        return {
            constructionId: $('[name="site"]').val(),
            areaId: $('[name="area"]').val(),
            projectCompanyId: $('[name="company"]').val(),
            type:type
        }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList(), form = layui.form;
        table.loadTable({
            url:"/accidentReport/queryByConstructionId"
            , upData: _self.query()
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'areaName', title: '所属区域',width:120}
                , {field: 'constructionName', title: '工地名称'}
                , {field: 'projectCompanyName', title: '所属公司'}
                , {field: 'projectStructureName', title: '检查部位'}
                , {field: 'contactName', title: '责任人'}
                , {field: 'contactMobile', title: '责任人电话'}
                , {field: 'teamName', title: '班组'}
                , {field: 'businessDate', title: '事故日期'}
                , {field: 'items', title: '事故简述'}
                , {field: 'results', title: '初步处理意见'}
                , {field: 'status', title: '事故等级',templet: '#status'}
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
            if(layEvent==="view"){
                biyue.open({
                    title:'查看',
                    url:'/accidentReport/toView?type= ' + type +'&id=' + data.id+"&constructionId=" + data.constructionId
                })
            }
        })
    },
    getCompany:function (id) {
        var form = layui.form;
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
        //新增
        $("#add_link").click(function () {
            biyue.open({
                title:'新增',
                area:{w:'920px',h:'560px'},
                url:'/accidentReport/toAdd?type=' + type
            })
        });
    }
};