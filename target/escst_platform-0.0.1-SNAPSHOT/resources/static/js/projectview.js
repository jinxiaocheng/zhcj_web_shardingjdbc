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

        //树形菜单
        videotree:function(){

            var $orgnaizeTree = $("#projectTree");
            var menuviewocx = document.getElementById("menuviewocx");
            var zTreeObj;

            var setting = {
                view: {
                    dblClickExpand: false
                },
                data: {
                    simpleData: {
                        enable: true
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
                var url = ctx + "/projectStructure/projectStructureList?constructionId="+constructionId;
                $.post(url,function(data){
                    if(data.status == 1){
                        var json = data.value;
                        zTreeObj = $.fn.zTree.init($orgnaizeTree, setting, json);
                    } else {
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
                   var link=ctx + "/projectStructure/fetchStartPreviewParam?parentId="+nodeId;
                    $("#orgviewocx").attr("src", link);

                    //  var link=ctx + "/org/fetchStartPreviewParam?nodeId="+treeNode.id;
                    //  $("#orgviewocx").attr("src", link);
                    //   $.ajax({
                    //      url: ctx + "/projectTask/addTask",
                    //      data: {
                    //          projectStrutureId: nodeId,
                    //
                    //      },
                    //      success: function(data) {
                    //
                    //          var previewXml = data.value;
                    //          alert(previewXml);
                    //          //orgviewocx.PVX_StartPreview(previewXml);
                    //      }
                    //  });
                }
            }




        },


    }

});














