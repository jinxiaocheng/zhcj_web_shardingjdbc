require.config({
    paths:{
        jquery:"./lib/jquery-2.2.3.min",
        nicescroll:"./lib/jquery.nicescroll",
        tree:"../plugins/zTree/v3/js/jquery.ztree.core-3.5.min",
        userview:"userview"
    }
})
require(['jquery','nicescroll','tree','userview'],function($,nell,tree,userview){
    //页面架构
    userview.mScontains();
    userview.videotree();

});

