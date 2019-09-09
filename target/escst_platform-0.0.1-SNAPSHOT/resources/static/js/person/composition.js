var biyueList = {
    id:"",
    constructionId:"",
    getZtree: function () {
        var _self = this,table = biyue.tableList();
        biyue.ajax({
            url:'/construction/queryAuthConstructionTree',
            data: {
                userId: userId
            },
            fun: function (data) {
                var dataV = data.value;
                zTreeObj = $.fn.zTree.init($("#menuTree"), biyue.ztreeSet({
                    click:function (event, treeId, treeNode) {
                        _self.id = treeNode.id;
                        _self.constructionId = treeNode.pId;
                        searchIf();
                    },
                    beforeClick:function (treeId, treeNode, clickFlag) {
                        if(treeNode.isParent){
                            return false;
                        }
                    }
                }), dataV);
                var node = zTreeObj.getNodes()[0];
                //获取第一个字节点
                function filterNode(data) {
                    if(data.children){
                        filterNode(data.children[0])
                    }else{
                        node = data;
                    }
                }
                filterNode(node);
                zTreeObj.selectNode(node);
                _self.id = node.id;
                _self.constructionId = node.pId;
                _self.createTable();
            }
        })
    },
    createTable: function () {
        var _self = this, table = biyue.tableList();
        table.loadTable({
            url:'/person/queryWorkTypePersonQtyPage'
            , upData: {
                id: _self.id
            }
            , cols: [[
                {type: 'numbers'}
                , {field: 'name', title: '岗位/工种'}
                , {field: 'qty', title: '人数'}
            ]]
            , height: 'full-20'
        });
        window.searchIf = function () {
            table.search({
                where: {
                    id: _self.id
                }
            })
        };
    }
};