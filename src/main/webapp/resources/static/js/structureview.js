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

            var $orgnaizeTree = $("#strutureTree");
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
                    onClick: onClick
                    }

            };
            // 发送获取树节点请求
            var initTreeWidget = function(){
                var url = ctx + "/projectTask/projectStructure?constructionId="+'8aad89d1545b00420154c1b4573a0364';
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
                    var projectStrutureId = treeNode.id
                    var projectStructureName=treeNode.name
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
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














