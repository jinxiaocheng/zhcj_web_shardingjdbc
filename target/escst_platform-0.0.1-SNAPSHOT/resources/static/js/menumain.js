require.config({
    paths:{
        jquery:"./lib/jquery-2.2.3.min",
        nicescroll:"./lib/jquery.nicescroll",
        tree:"../plugins/zTree/v3/js/jquery.ztree.core-3.5.min",
        menuview:"menuview"
    }
})
require(['jquery','nicescroll','tree','menuview'],function($,nell,tree,menuview){
    //页面架构
    menuview.mScontains();
    menuview.videotree();

});

