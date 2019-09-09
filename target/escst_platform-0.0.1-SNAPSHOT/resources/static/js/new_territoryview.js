var territoryview={
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
                },
                beforeClick:function(treeId, treeNode, clickFlag) {
                    if(treeNode.level==0){
                        return false;
                    }
                }
            }
        };
        // 发送获取树节点请求
        var initTreeWidget = function(){
            var index=layer.load();
            var url = ctx + "/territory/queryAreaTreeByUserId";
            $.post(url,function(data){
                layer.close(index);
                if(data.status == 1){
                    var json = data.value;
                    zTreeObj = $.fn.zTree.init($orgnaizeTree, setting, json);
                    var nodeP = zTreeObj.getNodes();//获取id为1的点
                    var nodeC ="";
                    if(nodeP.length>0){
                        nodeC=nodeP[0].children
                    }
                    if(nodeP.length==1&&nodeC.length==1){
                        $(".menu-left-tree").hide();
                        $(".videoplay").css('left',0);
                    }
                    zTreeObj.selectNode(nodeC[0]);//选择点
                    zTreeObj.setting.callback.onClick(null, zTreeObj.setting.treeId, nodeC[0]);//调用事件
                } else{
                    layer.alert(data.msg)
                }
            });
        };

        $(document).ready(function(){
            initTreeWidget();
        });

        var nodeClick = function(event, treeId, treeNode) {
            if(treeNode && treeNode.level!=0) {
                var nodeId = treeNode.id;
                var link = "";
                var search=window.location.search.replace("?","").split("&");
                var searchObj={};
                for(var i in search){
                    searchObj[search[i].split("=")[0]]=search[i].split("=")[1];
                }
                if(type=='scheduledPlan'){
                    link = ctx + "/scheduledPlan/toScheduledPlanList?constructionId="+treeNode.id+"&operationAuthority="+searchObj.operationAuthority;
                }else if(type=='projectStructure'){
                    link=ctx + "/projectStructure/toProjectStructureList?constructionId="+treeNode.id+"&operationAuthority="+searchObj.operationAuthority;
                }else if(type=='projectSchedule'){
                    link=ctx + "/projectSchedule/toProjectScheduleList?constructionId="+treeNode.id+"&operationAuthority="+searchObj.operationAuthority;
                }

                $("#terrtoryviewocx").attr("src", link);
            }
        }

    }
};


