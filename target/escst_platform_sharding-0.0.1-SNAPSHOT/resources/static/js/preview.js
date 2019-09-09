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

            var $orgnaizeTree = $("#orgnaizeTree");
            var previewOcx = document.getElementById("previewocx");
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
                    onDblClick : function(event, treeId, treeNode) {
                        nodeClick(event, treeId, treeNode);
                    }
                }
            };

            // 发送获取树节点请求
            var initTreeWidget = function(){
                var url = ctx + "/video/preview/fetchTreeNodeList";
                // alert(url);
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
                if(treeNode && treeNode.pId!=0) {
                    var nodeId = treeNode.id;
                    $.ajax({
                        url: ctx + "/video/preview/fetchStartPreviewParam",
                        data: {
                             cameraId: nodeId
                        },
                        success: function(data) {
                            if(data.status == 1){
                                var previewXml = data.value;
                                previewOcx.PVX_StartPreview(previewXml);
                            }
                        }
                    });
                }
            }

        }
    }
});














