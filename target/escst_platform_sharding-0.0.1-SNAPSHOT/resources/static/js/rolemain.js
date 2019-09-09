require.config({
    paths:{
        jquery:"./lib/jquery-2.2.3.min",
        nicescroll:"./lib/jquery.nicescroll",
        tree:"../plugins/zTree/v3/js/jquery.ztree.core-3.5.min",
        roleview:"roleview"
    }
})
require(['jquery','nicescroll','tree','roleview'],function($,nell,tree,roleview){
    //页面架构
    roleview.mScontains();
    roleview.videotree();

});

