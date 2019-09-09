
var zTree,treeObj,dataArray=[];
var treeNodes="";
var newCount = 1;
var setting = {
    view: {
        addDiyDom: addDiyDom,
        addHoverDom:addHoverDom,
        removeHoverDom:removeHoverDom,
        selectedMulti: false,
        dblClickExpand: false
    },
    data: {
        simpleData: {
            enable:true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: 0
        }
    },
    edit: {
        enable: true,
        editNameSelectAll: true,
        removeTitle:'删除',
        renameTitle:'编辑',
        showRemoveBtn: showRemoveBtn,
        showRenameBtn: showRenameBtn
    },

    callback: {
        beforeDrag: beforeDrag,
        onDblClick:onDblClick,
        onRemove: onRemove,
        onRename: onRename
    }
};

function onRemove(e, treeId, treeNode) {
    if(treeNode){
        var index=dataArray[treeNode.tId].index;
        for(var i in dataArray){
           var re = new RegExp(index);
           if(re.test(dataArray[i].index)){
               delete dataArray[i];
           }
        }
        delete dataArray[treeNode.tId];
    }

}

function onRename(e, treeId, treeNode, isCancel) {
    if(treeNode){
        if(dataArray[treeNode.tId]){
            dataArray[treeNode.tId].name=treeNode.name;
        }else{
            dataArray[treeNode.tId]={
                name:treeNode.name
                ,index:treeNode.tId
                ,id:treeNode.id
                ,pId:treeNode.pId
                ,parentTId:treeNode.parentTId
            }
        }
    }
}

//拥有id则不显示删除
function showRemoveBtn(treeId, treeNode) {
    return !treeNode.id;
}

//拥有id为1则无法编辑,用于控制根目录无法编辑
function showRenameBtn(treeId, treeNode) {
    return !(treeNode.id==='1')&&(type==2)||!treeNode.id;
}

//点击新增效果
function addDiyDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
    if(type==2) {
        sObj.after("<span class='button edit' id='editBtn_" + treeNode.tId + "' title='编辑' ></span>");
        return;
    }
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='新增' onfocus='this.blur();'></span>";
    if(!treeNode.id){
        addStr += "<span class='button edit' id='editBtn_" + treeNode.tId + "' title='编辑' ></span>";
        addStr += "<span class='button remove' id='removeBtn_" + treeNode.tId + "' title='删除' ></span>";
    }
    sObj.after(addStr);
    var btn = $("#addBtn_"+treeNode.tId);
    if (btn) btn.bind("click", function(){
        var zTree = $.fn.zTree.getZTreeObj("tree");
        zTree.addNodes(treeNode, {id:"", pId:treeNode.id, name:"新增工程结构" + (newCount++)});
        //加入dataArray数组中
        if(treeNode.children){
            var childrenData=treeNode.children[treeNode.children.length-1];
            dataArray[childrenData.tId]={
                name:childrenData.name
                ,id:childrenData.id
                ,pId:childrenData.pId
                ,parentTId:childrenData.parentTId
            }
            if(dataArray[childrenData.parentTId]){
                dataArray[childrenData.tId].index=dataArray[childrenData.parentTId].index+childrenData.tId;
            }else{
                dataArray[childrenData.tId].index=childrenData.tId;
            }
        }
        return false;
    });
};

function addHoverDom(treeId, treeNode) {
    $("#editBtn_"+treeNode.tId).remove();
    $("#removeBtn_"+treeNode.tId).remove();
}
//点击移除处理
function removeHoverDom(treeId, treeNode){
    var sObj = $("#" + treeNode.tId + "_span");
    var addStr = "";
    if(!treeNode.id){
        addStr += "<span class='button edit' id='editBtn_" + treeNode.tId + "' title='编辑' ></span>";
        addStr += "<span class='button remove' id='removeBtn_" + treeNode.tId + "' title='删除' ></span>";
    }
    if(type==2){
        addStr += "<span class='button edit' id='editBtn_" + treeNode.tId + "' title='编辑' ></span>";
    }
    sObj.parent().append(addStr);
};

//禁止拖动
function beforeDrag(treeId, treeNodes) {
    return false;
}

//禁止双击
function onDblClick(event, treeId, treeNode) {
    return false;
};

//通过工地加载工程结构
$(".site .dropdown-menu").on('click','li',function(){
    var constructionId=$(this).find("a").attr("data-value");
    treeAjax(constructionId)
})

//获取树形图数据
function treeAjax(id) {
    if(!id){id=$("#site").attr("data-value");}
    if(id==""){return;}
    $.ajax({
        async : false,
        cache:false,
        type: 'GET',
        data:{
            constructionId:id
        },
        dataType : "json",
        url:ctx + "/projectStructure/queryTreeByConstructionId",
        error: function () {
            console.log('查询目录请求失败!');
        },
        success:function(data){
            if(data.status==1){
                treeNodes = data.value;
                //生成树形图
                $.fn.zTree.init($("#tree"), setting, treeNodes);
                treeObj = $.fn.zTree.getZTreeObj("tree");
                if(treeNodes == '') {
                    console.log('未创建个人目录 !');
                }
            }else{
                layer.alert(data.msg);
            }

        }
    });
}

//提交数据
$("#my_ture").click(function () {
    var dataJson=getParamJson();
    if(type==1){
        var constructionId=$("#site").attr("data-value");
    }else if(type==2){
        var constructionId=cId;
    }
    $(".my_load").show();
    $.ajax({
        type: "post",
        url: ctx + "/projectStructure/saveStructure?constructionId="+constructionId,
        data: JSON.stringify(dataJson),
        dataType: "json",
        contentType:"application/json",
        success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
            $(".my_load").hide();
            if(data.status == 1){
                layer.alert("新增工程结构成功！",function(){
                    layer_close();
                });
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.alert(String(e));
            $(".my_load").hide();
        }
    });
});

// 正则获取出现的次数;
function patch(re,s){//参数1正则式，参数2字串
    re=eval("/"+re+"/ig")//不区分大小写，如须则去掉i,改为 re=eval("/"+re+"/g")
    return s.match(re).length;
}
//提交参数
function getParamJson() {
    var ztree=[],jsonData=[];
    for(var i in dataArray){
        var index=patch('tree',dataArray[i].index)-1;
        if(!ztree[index]){
            ztree[index]=[];
        }
        if(!ztree[index][i]){
            ztree[index][i]={};
        }
        for(var m in dataArray[i]){
            ztree[index][i][m]=dataArray[i][m];
        }
    }

    for(var j=ztree.length-1;j>0;j--){
        for(var k in ztree[j]){
            var pid=ztree[j][k].parentTId;
            if(!ztree[j-1][pid].children){
                ztree[j-1][pid].children=[];
            }
            ztree[j-1][pid].children.push(ztree[j][k]);
        }
    }

    for(var n in ztree[0]){
        jsonData.push(ztree[0][n]);
    }
    return jsonData;
};

//关闭刷新
function layer_close(){
    var url = ctx + "/projectStructure/visibleConstructionList";
    parent.jQuery("#gridTable").jqGrid('setGridParam',{url:url}).trigger("reloadGrid");
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
};



