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
                var url = ctx + "/org/fetchOrgNodeList";
                $.post(url,function(data){
                    if(data.status == 1){
                        var json = data.value;
                        zTreeObj = $.fn.zTree.init($orgnaizeTree, setting, json);
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
                    var link = "";
                    if(type=='org'){
                    	link=ctx + "/org/fetchStartPreviewParam?nodeId="+treeNode.id;
                    }else if(type=='role'){
                    	link=ctx + "/role/fetchStartPreviewParam?nodeId="+treeNode.id;
                    } else if(type=='user'){
                    	link=ctx + "/user/fetchStartPreviewParam?orgId="+treeNode.id;
                    }
                    $("#orgviewocx").attr("src", link);
                }
            }

        }
    }
});














