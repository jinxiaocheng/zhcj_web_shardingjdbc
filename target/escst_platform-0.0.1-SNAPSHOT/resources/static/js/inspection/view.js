var biyue_view={
    biyue:new biYue(),
    getData:function () {
        var _self = this;
        var biyue =_self.biyue;
        var searchObj=biyue.urlSearch();
        biyue.ajax({
            url:'/inspection/queryInspectionProjectStructure',
            data:{
            	inspectionId:searchObj.id,
                constructionId:searchObj.constructionId
            },
            fun:function (data) {
                $.fn.zTree.init($("#tree"), _self.setting, data.value);
                var treeObjT = $.fn.zTree.getZTreeObj("tree");
                if(treeObjT){
                    treeObjT.expandAll(true);
                }
            }
        });
        biyue.ajax({
            url:'/inspection/loadCheckItemsTree',
            data:{
                id:searchObj.id,
                constructionId:searchObj.constructionId,
                type:type
            },
            fun:function (data) {
                $.fn.zTree.init($("#treeR"), _self.setting, data.value);
                var treeObjT = $.fn.zTree.getZTreeObj("treeR");
                if(treeObjT){
                    treeObjT.expandAll(true);
                }
            }
        })
    },
    setting:{
        view: {
            showLine: false
        },
        data:{
            key:{
                children:'data'
            }
        }
    }
}