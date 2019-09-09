require.config({
    paths:{
        jquery:"./lib/jquery-2.2.3.min",
        nicescroll:"./lib/jquery.nicescroll",
        tree:"../plugins/zTree/v3/js/jquery.ztree.core-3.5.min",
        territoryview:"territoryview"
    }
})
require(['jquery','nicescroll','tree','territoryview'],function($,nell,tree,territoryview){
    //页面架构
	territoryview.mScontains();
	territoryview.videotree();

});

