require.config({
    urlArgs:'ver='+ver,
    paths:{
        jquery:"./lib/jquery-2.2.3.min",
        nicescroll:"./lib/jquery.nicescroll",
        tree:"../plugins/zTree/v3/js/jquery.ztree.core-3.5.min",
        previewJS:"preview",
        layer:'../plugins/layer/layer'
    },
    shim:{
        nicescroll:['jquery'],
        tree:['jquery'],
        layer:['jquery']
    }
});

require(['jquery','nicescroll','tree','layer','previewJS'],function($,nell,tree,layer,previewJS){
    //页面架构
    previewJS.mScontains();
    previewJS.videotree();
});

