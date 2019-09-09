var door={
    biyue:new biYue(),
    createTable:function () {
        var _self=this,biyue=_self.biyue,table=biyue.tableList();
        table.loadTable({
            url:'/acsControl/queryList'
            ,upData:{
                constructionId:$('[name="site"]').find('option:selected').val()
            }
            ,cols:[[
                {type:'numbers'}
                ,{field:'name', title:'设备名称',width:200}
                ,{field:'number', title:'设备编号'}
                ,{fixed: 'right', title:'操作',width:120, align:'center', toolbar: '#barDemo'}
            ]]
            ,height:'full-90'
        });
        //搜索
        $("#search").on('click',function () {
            searchIf();
        });
        window.searchIf=function () {
            var constructionId=$('[name="site"]').find('option:selected').val();
            table.search({
                where:{
                    constructionId:constructionId
                }
            })
        };
        //编辑
        layui.table.on('tool(table-list)', function(obj){
            var data = obj.data
                ,layEvent = obj.event;
            if(layEvent==="edit"){
                biyue.open({
                    title:'编辑设备',
                    url:'/door/toAddEquipment?type=edit&id='+data.id+'&name='+data.name
                })
            }
        })
    }
};
