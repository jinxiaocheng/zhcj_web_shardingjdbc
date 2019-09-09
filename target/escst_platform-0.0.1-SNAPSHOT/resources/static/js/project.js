require.config({
    paths:{
        jquery:"./lib/jquery-2.2.3.min",
        nicescroll:"./lib/jquery.nicescroll",
        tree:"../plugins/zTree/v3/js/jquery.ztree.core-3.5.min",
        projectview:"projectview"
    }
})
require(['jquery','nicescroll','tree','projectview'],function($,nell,tree,projectview){
    //页面架构
    projectview.mScontains();
    projectview.videotree();

});


