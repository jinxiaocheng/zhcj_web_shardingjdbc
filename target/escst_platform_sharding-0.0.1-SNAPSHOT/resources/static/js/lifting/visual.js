var biyueList={
    biyue:new biYue(),
    createTable:function () {
        var _self=this,biyue=_self.biyue,table=biyue.tableList();
        table.loadTable({
            url:'/hook/getNvrList'
            ,upData:{
                constructionId:$('[name="site"]').val(),
                areaId: $('[name="area"]').val()
            }
            ,cols:[[
                {type:'numbers',fixed: 'left'}
                ,{field:'constructionName', title:'项目名称'}
                ,{field:'ip', title:'ip地址',width:200}
                ,{field:'webPort', title:'web端口',width:100}
                ,{field:'appPort', title:'app端口',width:100}
                ,{field:'userName', title:'用户名'}
                ,{field:'userPassword', title:'密码'}
                ,{fixed: 'right', title:'操作',width:120, align:'center', toolbar: '#barDemo'}
            ]]
            ,height:'full-90'
        });
        //搜索
        $("#search").on('click',function () {
            searchIf();
        });
        window.searchIf=function () {
            table.search({
                where:{
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
                    url:'/route/lifting/visualAdd?type=edit&id='+data.id
                })
            }else if(layEvent==="view"){
                biyue.open({
                    title:'查看',
                    url:'/route/lifting/visualView?type=view&id='+data.id
                })
            }
        })
    },
    toolsBtn:function () {
        $("#add_link").click(function () {
            biyue.open({
                title:'新增',
                url:'/route/lifting/visualAdd?type=add'
            })
        })
    }
};



