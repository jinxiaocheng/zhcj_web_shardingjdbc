//新增功能中的功能模块
function add() {

}

//功能对象
add.prototype={
    setting:function () {
        var addThis=this;
        return {
            view: {
                showLine: true,
                selectedMulti: false //不可多选
            },
            data: {
                simpleData: {
                    enable:true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: 0
                }
            },
            async:{
                autoParam:["id=catalogID"],
                otherParam:["type","M"],
                enable:true,
                dataType:"json",
                type:"get",
                url:"${ctx}/document/queryCatalogTree"
            },
            check: {
                enable: false
            },
            callback: {
                beforeClick: beforeClick,
                onClick: onClick
            }
        }
    },
    // 设置图片
    file:function () {
        $("#file").fileinput({
            language: 'zh',
            theme: 'fa',
            uploadUrl: '#',
            showUpload:false,
            maxFileSize:51200,
            minFileSize:1,
            browseIcon:'',
            removeIcon:'',
            allowedFileExtensions: ["bmp", "jpg", "jpeg", "png", "gif"],
            fileActionSettings:{
                showUpload: false,
                showZoom: false,
                showDrag: false,
            }
        });
    },

    // 点击生成检查部位名称
    getCheckPoint:function(e, treeId, treeNode){
        var zTree = $.fn.zTree.getZTreeObj("tree"),
            nodes = zTree.getSelectedNodes(),
            v = "",
            k_id="";
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
            v += nodes[i].name
            var nodesObj=nodes[i];
            while (nodesObj.getParentNode()){
                v=nodesObj.getParentNode().name+' / '+v;
                nodesObj=nodesObj.getParentNode();
            }
        }
        var cityObj = $("#construction>label");
        cityObj.html(v);
        cityObj.attr("k_id",treeNode.id);
        hideMenu();
        openZtree=true;
    }
}