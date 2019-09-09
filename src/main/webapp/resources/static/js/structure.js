require.config({
    paths:{
        jquery:"./lib/jquery-2.2.3.min",
        nicescroll:"./lib/jquery.nicescroll",
        tree:"../plugins/zTree/v3/js/jquery.ztree.core-3.5.min",
        structureview:"structureview"
    }
})
require(['jquery','nicescroll','tree','structureview'],function($,nell,tree,structureview){
    //页面架构
    structureview.mScontains();
    structureview.videotree();

});

