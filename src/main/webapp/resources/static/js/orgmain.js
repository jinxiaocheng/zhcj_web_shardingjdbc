require.config({
    paths:{
        jquery:"./lib/jquery-2.2.3.min",
        nicescroll:"./lib/jquery.nicescroll",
        tree:"../plugins/zTree/v3/js/jquery.ztree.core-3.5.min",
        orgview:"orgview"
    }
})
require(['jquery','nicescroll','tree','orgview'],function($,nell,tree,orgview){
    //页面架构
    orgview.mScontains();
    orgview.videotree();

});

