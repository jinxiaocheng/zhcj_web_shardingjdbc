var biyueList = {
    query:function () {
        return {
            startDate: $.trim($('[name="startTime"]').val()),
            endDate: $.trim($('[name="endTime"]').val()),
            constructionId: $('[name="site"]').val(),
            areaId: $('[name="area"]').val(),
            items: $('[name="check_item"]').val(),
            projectCompanyId: $('[name="company"]').val(),
            processingOpinion:0,
            type:type
        }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList(), form = layui.form;
        table.loadTable({
            url:"/inspection/queryByConstructionId"
            , upData: _self.query()
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'businessDate', title: '检查日期'}
                , {field: 'orderNo', title: '单号'}
                , {field: 'items', title: '检查项'}
                , {field: 'contactName', title: '责任人'}
                , {field: 'teamName', title: '班组'}
                , {field: 'projectCompanyName', title: '所属公司'}
                , {field: 'areaName', title: '所属区域',width:120}
                , {field: 'constructionName', title: '工地名称',width:240}
                , {field: 'correctiveStatus', title: '整改状态',templet: '#correctiveStatus'}
                , {field: 'status', title: '处理意见',templet: '#status'}
                , {fixed: 'right', title:'操作',width:120, align:'center', toolbar: '#barDemo'}
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
                    url:'/inspection/toView?type=' + type + '&id='+data.id
                })
            }else if(layEvent==="export"){
                window.location.href = ctx + '/inspection/exportWord?id='+data.id+'&type=' + type;
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
    getItems:function (fun) {
        var form=layui.form;
        biyue.ajax({
            url:'/inspection/querySafetyCheckItems?type='+type,
            fun:function (data) {
                var $list=" <option value=\"\">选择检查项</option>"
                    ,dataV=data.value;
                for(var i=0;i<dataV.length;i++){
                    $list+='<option value="'+dataV[i].id+'">'+dataV[i].name+'</option>';
                }

                $("[name='check_item']").html($list);
                form.render('select');
                fun&&fun();
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
                url:'/inspection/toAdd?type=2'
            })
        });
    }
};