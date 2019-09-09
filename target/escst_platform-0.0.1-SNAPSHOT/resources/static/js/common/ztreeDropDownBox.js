/*
* presentation :用于下拉框中使用ztree(如：检查部位 文档类型)
* */

function ztreeDropDownBox(id,bool) {
    var zd=this;// 获取当前对象
    this.id=id;// 获取当前id字符
    this.dom=$("#"+id); // 获取当前id节点
    this.con=$('.ztreeContent');// 选择之后获取的内容
    this.titleDom=$("#"+id+">label"); // 获取标题节点
    this.openZtree=true; // 控制下拉框是否显示
    this.showBool=bool===undefined?true:bool; //控制是否显示父级

    // 隐藏
    this.hideMenu=function (e) {
        $("#tree").fadeOut("fast");
        $("body").unbind("mousedown", zd.onBodyDown);
    };

    // 点击window隐藏
    this.onBodyDown=function (e) {
        var e = event || window.event;
        var target = e.target || e.srcElement;//兼容ie7,8
        if (!(target.id == zd.id|| $(target).parents("#"+zd.id).length>0 || target.id == "tree" || $(target).parents("#tree").length>0)) {
            zd.hideMenu();
            zd.openZtree=true;
        }
    }

    // 点击生成名称
    this.onClick=function (e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("tree"),
            nodes = zTree.getSelectedNodes(),
            v = "";
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
            v += nodes[i].name;
            var nodesObj=nodes[i];
            while (nodesObj.getParentNode()){
                v=nodesObj.getParentNode().name+' / '+v;
                nodesObj=nodesObj.getParentNode();
            }
        }
        var cityObj = zd.dom,cityCon=zd.con;
        if(zd.showBool){
            cityCon.text(v);
        }else{
            cityCon.text(treeNode.name);
        }
        cityObj.attr("data-value",treeNode.id);
        zd.hideMenu();
        zd.openZtree=true;
    }
}

ztreeDropDownBox.prototype={

    // 点击及为选中
    beforeClick:function (treeId, treeNode) {
        var check = (treeNode && !treeNode.isParent);
        return check;
    },

    //显示
    showMenu:function (e) {
        var zd=this;
        var e = e || window.event;
        var target = e.target || e.srcElement;//兼容ie7,8
        if (target.id == zd.id||$(target).parents("#"+zd.id).length>0) {
            if(zd.openZtree){
                setTimeout(function () {
                    $("#tree").fadeIn("fast");
                    $("body").bind("mousedown",  zd.onBodyDown);
                    zd.openZtree=false;
                },20)

            }else{
                zd.hideMenu();
                zd.openZtree=true;
            }
        }
    }
}

