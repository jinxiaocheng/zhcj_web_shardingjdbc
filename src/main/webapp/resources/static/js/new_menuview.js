var menuview={
    //监控视频核心
    mScontains:function(){
        $(function(){
            $("html").niceScroll({
                styler:"fb",cursorcolor:"#000",
                cursorwidth: '5',
                cursorborderradius: '5px',
                background: '',
                spacebarenabled:false,
                cursorborder: '0',
                zindex: '1000'
            });
        });
    },

    //监控视频树形菜单
    videotree:function(orgId){
        var $menunaizeTree = $("#menuTreeS");
        var menuviewocx = document.getElementById("menuviewocx");
        var zTreeObj;

        var setting = {
            view: {
                fontCss:{"color":"#000","font-family":"宋体"},
                dbClickExpand:false,
                showLine:false
            },
            data: {
                simpleData: {
                    enable:true,
                    idKey:"id",
                    pIdKey:"pId",
                    rootPId:""
                }
            },
            callback: {
                onClick : function(event, treeId, treeNode) {
                    nodeClick(event, treeId, treeNode,orgId);
                }
            }
        };

        // 发送获取树节点请求
        var initTreeWidget = function(orgId){
            var index=layer.load();
            var url = ctx + "/menu/fetchMenuNodeList?orgId="+orgId;
            $.post(url,function(data){
                layer.close(index);
                if(data.status == 1){
                    var json = data.value,nodeNum=0,nodeId="";
                    var zTreeObj = $.fn.zTree.init($menunaizeTree, setting, json);
                    var zTree = $.fn.zTree.getZTreeObj("menuTree");
                    for(var i in json){
                        if(zTree.getNodeByParam("id",json[i].id,null)){
                            if(!zTree.getNodeByParam("id",json[i].id,null).isParent){
                                nodeNum++;
                                nodeId=json[i].id;
                            }
                        }
                        if(nodeNum>1){
                            break;
                        }
                    }
                    if(nodeNum==1){
                        $(".menu-left-tree").hide();
                        $(".videoplay").css('left',0);
                        $("#menuviewocx").attr("src", ctx + "/menu/fetchStartPreviewParam?nodeId="+nodeId);
                    }
                } else{
                    alert(data.msg)
                }
            });
        };

        initTreeWidget(orgId);

        var nodeClick = function(event, treeId, treeNode,orgId) {
            if(treeNode && treeNode.id!=0) {
                var nodeId = treeNode.id;
                var search=window.location.search.replace("?","").split("&");
                var searchObj={};
                for(var i in search){
                    searchObj[search[i].split("=")[0]]=search[i].split("=")[1];
                }
                var link=ctx + "/menu/fetchStartPreviewParam?nodeId="+treeNode.id+"&orgId="+orgId+"&operationAuthority="+searchObj.operationAuthority;
                $("#menuviewocx").attr("src", link);
            }
        }

    },

    //获取机构
    getOrganization:function () {
        var _self=this;
        var $orgnaizeTree = $("#orgTree");
        var orgviewocx = document.getElementById("orgviewocx");
        var zTreeObj;

        var setting = {
            view: {
                fontCss:{"color":"#000","font-family":"宋体"},
                dbClickExpand:false,
                showLine:false
            },
            data: {
                simpleData: {
                    enable:true,
                    idKey:"id",
                    pIdKey:"pId",
                    rootPId:""
                }
            },
            callback: {
                onClick : function(event, treeId, treeNode) {
                    nodeClick(event, treeId, treeNode);
                }
            }
        };

        // 发送获取树节点请求
        var initTreeWidget = function(){
            var index=layer.load();
            var url = ctx + "/org/fetchOrgNodeList?userId="+userId;
            $.post(url,function(data){
                layer.close(index);
                if(data.status == 1){
                    var json = data.value;
                    if(top.globalData.userType==="3"){
                        _self.videotree(data.value[0].id);
                        $(".menu-left-tree.f1").hide();
                        $(".menu-left-tree.f2").css("left","10px");
                        $(".videoplay").css("left","300px");
                    }else{
                        zTreeObj = $.fn.zTree.init($("#menuTree"), setting, json);
                    }
                } else{
                    layer.alert(data.msg)
                }
            });
        };

        initTreeWidget();

        var nodeClick = function(event, treeId, treeNode) {
            if(treeNode) {
                _self.videotree(treeNode.id);
            }
        }
    }
}