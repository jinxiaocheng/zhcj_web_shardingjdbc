/**
 * @desc
 * @param 选择检查部位
 * @return
 * @author kz
 * @date 2018/5/14 16:46
 */
var biyue_skewing={
    openWin:function () {
        var _self=this;
        $(".biyue-skewing .getSkewing").click(function () {
            if(!$("#tree").html()){
                layer.msg('该工地暂无检查部位！', {time: 2000, icon: 0});
                return;
            }
            var my_open=layer.open({
                type: 1,
                anim:5,
                title:'检查部位',
                content: $('#tree'),
                area: ['80%','80%'],
                btn:['确定'],
                yes:function(){
                    var treeObj=$.fn.zTree.getZTreeObj("tree"),
                        nodes=treeObj.getCheckedNodes(true),
                        data=[];
                    for(var i=0;i<nodes.length;i++){
                        data[i]={
                            id:nodes[i].id
                            ,name:nodes[i].name
                            ,pId:nodes[i].pId
                        };
                    }
                    if(data.length<1){
                        $("#treeShow").hide();
                    }else{
                        $("#treeShow").show();
                        $.fn.zTree.init($("#treeShow"), _self.settingT, data);
                        var treeObjT = $.fn.zTree.getZTreeObj("treeShow");
                        treeObjT.expandAll(true);
                    }
                    layer.close(my_open);
                }
            });
        });
        //滚动条
        $("#treeShow").niceScroll({
            styler:"fb",
            cursorcolor:"#cccccc",
            cursorwidth: '5',
            cursorborderradius: '5px',
            background: '',
            autohidemode: false,
            spacebarenabled:false,
            cursorborder: '0',
            zindex: '1000'
        });
    },
    setting : {
        view: {
            showLine: true
        },
        data: {
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        },
        check: {
            enable: true,
            nocheckInherit: false
        },
        key:{
            children:'child'
        },
        callback: {
            onClick: function (e, treeId, treeNode, clickFlag) {
                $.fn.zTree.getZTreeObj("tree").checkNode(treeNode, !treeNode.checked, true);
            }
        }
    }, //弹出树形图设置
    settingT:{
        view: {
            showLine: false
        },
        data: {
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        }
    }, //选中后树形图设置
    getData:function (id) { //获取检查部位数据
        var _self=this;
        $.fn.zTree.destroy("treeShow");
        $("#treeShow").hide();
        if(!id){return;}
        $.ajax({
            type: "post",
            url: ctx + '/projectStructure/projectStructureList',
            data: {
                "constructionId":id
            },
            success: function (data) {
                if(data.status == 1){
                    if(data.value){
                        //生成树形图
                        $.fn.zTree.init($("#tree"), _self.setting, data.value);
                    }
                } else {
                    layer.alert(data.msg);
                }
            },
            error: function (data, status, e) {
                layer.alert(String(e));
            }
        });
    },
    getSkewing:function () {//获取检查部位
        var treeObj = $.fn.zTree.getZTreeObj("treeShow");
        var data=[];
        if(treeObj){
            var node = treeObj.getNodes();
            var nodes = treeObj.transformToArray(node);
            for(var i=0,j=0;i<nodes.length;i++){
                if(!nodes[i].isParent){
                    data[j]={
                        name:nodes[i].name,
                        id:nodes[i].id
                    };
                    j++;
                }
            }
        }else {
            data=null;
        }
        return data;
    }
};

var biyue_skewResult={
    openWin:function () {
        var _self=this;
        $(".biyue-skewing .getSkewResult").click(function () {
            if(!$("#treeResult").html()){
                layer.msg('该工地暂无检查项及结果！', {time: 2000, icon: 0});
                return;
            }
            var my_open=layer.open({
                type: 1,
                anim:5,
                title:'检查项及结果',
                content: $('#treeResult'),
                area: ['80%','80%'],
                btn:['确定'],
                yes:function(){
                    var treeObj=$.fn.zTree.getZTreeObj("treeResult"),
                        nodes=treeObj.getCheckedNodes(true),
                        data=[];
                    for(var i=0;i<nodes.length;i++){
                        data[i]={
                            id:nodes[i].id
                            ,name:nodes[i].name
                            ,pId:nodes[i].pId
                        };
                    }
                    if(data.length<1){
                        $("#treeShowResult").hide();
                        $(".treeResult").attr("data-value","");
                    }else{
                        $(".treeResult").attr("data-value","1");
                        $("#treeShowResult").show();
                        $.fn.zTree.init($("#treeShowResult"), _self.settingT, data);
                        var treeObjT = $.fn.zTree.getZTreeObj("treeShowResult");
                        treeObjT.expandAll(true);
                    }
                    layer.close(my_open);
                }
            });
        });
        //滚动条
        $("#treeShowResult").niceScroll({
            styler:"fb",
            cursorcolor:"#cccccc",
            cursorwidth: '5',
            cursorborderradius: '5px',
            background: '',
            autohidemode: false,
            spacebarenabled:false,
            cursorborder: '0',
            zindex: '1000'
        });
    },
    setting : {
        view: {
            showLine: true
        },
        data: {
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        },
        check: {
            enable: true,
            nocheckInherit: false
        },
        key:{
            children:'child'
        },
        callback: {
            onClick: function (e, treeId, treeNode, clickFlag) {
                $.fn.zTree.getZTreeObj("treeResult").checkNode(treeNode, !treeNode.checked, true);
            }
        }
    }, //弹出树形图设置
    settingT:{
        view: {
            showLine: false
        },
        data: {
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        }
    }, //选中后树形图设置
    getData:function (id) { //获取检查项及结果数据
        var _self=this;
        $.fn.zTree.destroy("treeShowResult");
        $("#treeShowResult").hide();
        if(!id){return;}
        $.ajax({
            type: "post",
            url:ctx + '/inspection/querySafetyCheckResultsTree',
            data: {
                type:type||"",
                "constructionId":id
            },
            success: function (data) {
                if(data.status == 1){
                    if(data.value){
                        //生成树形图
                        $.fn.zTree.init($("#treeResult"), _self.setting, data.value);
                    }
                } else {
                    layer.alert(data.msg);
                }
            },
            error: function (data, status, e) {
                layer.alert(String(e));
            }
        });
    },
    getSkewing:function () {//获取检查项及结果
        var treeObj = $.fn.zTree.getZTreeObj("treeShowResult");
        var data=[],items=[];
        if(treeObj){
            var node = treeObj.getNodes();
            var nodes = treeObj.transformToArray(node);
            for(var i=0,j=0;i<nodes.length;i++){
                if(!nodes[i].isParent){
                    data[j]={
                        name:nodes[i].name,
                        id:nodes[i].id
                    };
                    items[j]=[nodes[i].name];
                    j++;
                }
            }
        }else {
            data=null;
        }

        return {
            data:data,
            items:items.join(",")
        };
    }
};
