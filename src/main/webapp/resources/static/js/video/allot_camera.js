var biyue = new biYue();
var urlSearch = biyue.urlSearch();
var allot_camera={
    initData:[]
    ,ztreeSet:{
        view: {
            showLine: true,
            selectedMulti: true
        },
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        },
        callback: {
            onClick: function (e, treeId, treeNode, clickFlag) {
                $.fn.zTree.getZTreeObj("tree").checkNode(treeNode, !treeNode.checked, true);
            }
        }
    }
    ,editData:function (data) {
        for(var i=0,len=data.length;i<len;i++){
            var num=0;//用于计算是否全被选中;
            data[i].checked=true;
            if(data[i].children&&data[i].children.length>0){
                var dataV=data[i].children;
                for(var j=0,lenj=dataV.length;j<lenj;j++){
                    dataV[j].checked=!dataV[j].checked;
                    dataV[j].name= dataV[j].name+'(通道号：'+dataV[j].channelNo+')';
                    if(dataV[j].checked){
                        num++;
                    }
                }
            }
            if(num===0){
                data[i].checked=false;
            }
        }
        return data;
    }
    ,getData:function () {
        var _self = this;
        biyue.ajax({
            url:'/video/getCameraByUserId'
            ,contentType: "application/json"
            ,data:JSON.stringify({
                roleId:urlSearch.id
            })
            ,fun:function (data) {
                var dataV = _self.editData(data.value);
                $.fn.zTree.init($("#tree"), _self.ztreeSet,dataV);
                var treeObj=$.fn.zTree.getZTreeObj("tree"),
                nodes=treeObj.getCheckedNodes(false);
                for(var i=0,len=nodes.length;i<len;i++){
                    if(nodes[i].level===1){
                        _self.initData.push(nodes[i].id)
                    }
                }
            }
        })
    }
    ,sendData:function () {
        var _self = this,data=[];
        var treeObj=$.fn.zTree.getZTreeObj("tree"),
            nodes=treeObj.getCheckedNodes(false);
        for(var i=0,len=nodes.length;i<len;i++){
            if(nodes[i].level===1){
                data.push(nodes[i].id)
            }
        }
        biyue.ajax({
            url:'/video/saveRoleCamera'
            ,contentType: "application/json"
            ,data:JSON.stringify({
                roleId:urlSearch.id,
                firstCameraIds:_self.initData,
                lastCameraIds:data
            })
            ,fun:function (data) {
                layer.alert("分配视频成功！",{
                    cancel:function () {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    }
                },function () {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                })
            }
        })
    }
};