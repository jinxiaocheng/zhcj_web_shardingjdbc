define(function(require){
    return{
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
                var url = ctx + "/territory/queryAreaTreeByUserId";
                $.post(url,function(data){
                    if(data.status == 1){
                        var json = data.value;
                        zTreeObj = $.fn.zTree.init($orgnaizeTree, setting, json);
                    } else{
                        alert(data.msg)
                    }
                });
            };

            $(document).ready(function(){
                initTreeWidget();
            });
            
            var nodeClick = function(event, treeId, treeNode) {
                if(treeNode && treeNode.id!=0) {
                    var nodeId = treeNode.id;
                    var link = "";
                    if(type=='scheduledPlan'){
                    	link = ctx + "/scheduledPlan/toScheduledPlanList?constructionId="+treeNode.id;
                    }else if(type=='projectStructure'){
                    	link=ctx + "/projectStructure/toProjectStructureList?constructionId="+treeNode.id;
                    }else if(type=='projectSchedule'){
                    	link=ctx + "/projectSchedule/toProjectScheduleList?constructionId="+treeNode.id;
                    }
                    
                    $("#terrtoryviewocx").attr("src", link);
                }
            }

        }
    }
});

