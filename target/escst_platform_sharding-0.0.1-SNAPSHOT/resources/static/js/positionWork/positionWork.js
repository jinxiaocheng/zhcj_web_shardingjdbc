var biyueList = {
    query:function () {
      return  {
          name: $.trim($('[name="name"]').val()),
          type: $('[name="type"]').val(),
          constructionId:$('[name="site"]').val(),
          areaId: $('[name="area"]').val()
      }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList();
        table.loadTable({
            url:"/positionWork/list"
            , upData: _self.query()
            , cols: [[
                {type: 'numbers',fixed: 'left'}
                , {field: 'name', title: '岗位/工种名称'}
                , {field: 'type', title: '类型',templet: '#status'}
                , {field: 'areaName', title: '所属区域',width:180}
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
                where:_self.query()
            })
        };
        //编辑
        layui.table.on('tool(table-list)', function(obj){
            var data = obj.data
                ,layEvent = obj.event;
            if(layEvent==="edit"){
                window.editData=data;
                biyue.open({
                    title:'编辑',
                    url:'/positionWork/toAdd?type=edit&id='+data.id
                })
            }
        })
    },
    toolsBtn: function () {
        //新增
        $("#add_link").click(function () {
            biyue.open({
                title:'新增',
                url:'/positionWork/toAdd'
            })
        });
        //数据初始化
        $("#refresh_link").click(function () {
            var constructionId = $('[name="site"]').val();
            if(!constructionId){
                layer.msg('请选择一个工地！', {icon: 7});
                return;
            }
            var index = layer.confirm('是否初始化数据?', {
                title: '提示',
                icon: 3
            }, function () {
                biyue.ajax({
                    url:'/positionWork/initData',
                    data:{
                        constructionId:constructionId
                    },
                    fun:function (data) {
                        searchIf();
                        layer.close(index);
                    }
                })
            });

        })
    }
};