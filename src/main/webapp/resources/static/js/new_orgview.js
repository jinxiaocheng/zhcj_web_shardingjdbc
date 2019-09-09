
var orgview={
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
    videotree:function(){

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
                    var json = data.value,nodeNum=0,nodeId="";
                    var search=window.location.search.replace("?","").split("&");
                    var searchObj={};
                    for(var i in search){
                        searchObj[search[i].split("=")[0]]=search[i].split("=")[1];
                    }
                    zTreeObj = $.fn.zTree.init($orgnaizeTree, setting, json);
                    var zTree = $.fn.zTree.getZTreeObj("orgTree");
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
                        if(top.globalData.userType==3){
                            $(".videoplay").css('left',0);
                            $(".menu-left-tree").hide();
                        }

                        if(type=='org'){
                            link=ctx + "/org/fetchStartPreviewParam?nodeId="+nodeId+"&operationAuthority="+searchObj.operationAuthority;
                        }else if(type=='role'){
                            link=ctx + "/role/fetchStartPreviewParam?nodeId="+nodeId+"&operationAuthority="+searchObj.operationAuthority;
                        } else if(type=='user'){
                            link=ctx + "/user/fetchStartPreviewParam?orgId="+nodeId+"&operationAuthority="+searchObj.operationAuthority;
                        }
                        $("#orgviewocx").attr("src", link);
                    }
                } else{
                    layer.alert(data.msg)
                }
            });
        };

        $(document).ready(function(){
            initTreeWidget();
        });

        var nodeClick = function(event, treeId, treeNode) {
            if(treeNode && treeNode.id!=0) {
                var nodeId = treeNode.id;
                var search=window.location.search.replace("?","").split("&");
                var searchObj={};
                for(var i in search){
                    searchObj[search[i].split("=")[0]]=search[i].split("=")[1];
                }
                var link = "";
                if(type=='org'){
                    link=ctx + "/org/fetchStartPreviewParam?nodeId="+treeNode.id+"&operationAuthority="+searchObj.operationAuthority;
                }else if(type=='role'){
                    link=ctx + "/role/fetchStartPreviewParam?nodeId="+treeNode.id+"&operationAuthority="+searchObj.operationAuthority;
                } else if(type=='user'){
                    link=ctx + "/user/fetchStartPreviewParam?orgId="+treeNode.id+"&operationAuthority="+searchObj.operationAuthority;
                }
                $("#orgviewocx").attr("src", link);
            }
        }

    }
}













