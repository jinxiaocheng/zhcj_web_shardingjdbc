define(function(require){
    return{
        //核心
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

        //树形菜单
        videotree:function(){

            var $usernaizeTree = $("#userTree");
            var userviewocx = document.getElementById("userviewocx");
            var orgId = document.getElementById("orgId");
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
                        rootPId:"",
                        orgId:orgId
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
                var url = ctx + "/user/fetchUserNodeList?orgId="+orgId.value;
                $.post(url,function(data){
                    if(data.status == 1){
                        var json = data.value;
                        zTreeObj = $.fn.zTree.init($usernaizeTree, setting, json);
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
                    var orgId = treeNode.id;
                    var link=ctx + "/user/fetchStartPreviewParam?orgId="+treeNode.id;
                    $("#userviewocx").attr("src", link);
                }
            }

        }
    }
});














