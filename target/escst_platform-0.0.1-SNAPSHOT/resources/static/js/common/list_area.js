var  constructionId="";
var areaId="";
//企业切换工地后，所有区域与工地同步
;(function () {
    if(top.constructionId){
        window.cBool=true;
        $(".site .dropdown-text").attr("data-value",top.constructionId);
        constructionId=top.constructionId;
    }
})();

//控制条件
function areaConditions(fun) {
    if(top.globalData.userType==3){
        var len=$("#search-input .ui-filter").length;
        if(len==3){
            $('.openSearchBtn[data-target="#search-input"]').hide();
        }
        $(".area").hide();
        $(".site").hide();
        $("#area").attr("data-value",top.globalData.areaId);
        $("#site").attr("data-value",top.globalData.constructionId);
        constructionId=top.globalData.constructionId;
        if(fun){fun(constructionId)}
        return true;
    }else{
        return false;
    }
}

//区域与工地加载
function init(fun,ref) { //fun 工地改变是执行函数  ref 区域改变是初始化数据

    //当工地端登录时隐藏区域工地
    if(areaConditions(fun)){
        return;
    }
    var $init_list1='<li><a data-value="">请选择</a></li>';
    var my_json={
        "userId":userId
    };
    $.ajax({
        type: "post",
        url: ctx + '/territory/queryAuthAreaByUserId',
        data: my_json,
        success: function (data) {
            if(data.status == 1){
                if(data.value){
                    if(data.value.length==1){
                        $(".area .dropdown-text").attr("data-value",data.value[0].areaId).html(data.value[0].areaName);
                        site(data.value[0].data,fun);
                    }
                    for(var i in data.value){
                        //企业切换工地后，所有区域与工地同步
                        if(data.value.length>1&&top.areaId&&top.areaId===data.value[i].areaId){
                            $(".area .dropdown-text").attr("data-value",data.value[i].areaId).html(data.value[i].areaName);
                            site(data.value[i].data,fun);
                        }

                        $init_list1+=' <li><a data-value="'+data.value[i].areaId+'">'+data.value[i].areaName+'</a></li>';
                    }
                    $(".area .dropdown-menu").html($init_list1);
                    $(".area .dropdown-menu>li").click(function () {
                        var index=$(this).index()-1;
                        constructionId="";
                        if(index<0){
                            $(".site .dropdown-menu").html("<li><a data-value=\"\">请选择</a></li>");
                            $(".site .dropdown-text").attr("data-value","").html("请选择");
                            $(".area .dropdown-text").attr("data-value","").html("请选择");
                            if(ref){ref()}
                        }else{
                            $(".area .dropdown-text").attr("data-value",data.value[index].areaId).html(data.value[index].areaName);
                            site(data.value[index].data,fun);
                        }

                    })
                }
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            console.error('请求错误:'+e);
        }
    });
}
function site(data,fun){
    if(data.length==1){
        $(".site .dropdown-text").attr("data-value",data[0].constructionId).html(data[0].constructionName);
        constructionId=data[0].constructionId;
    }else{
        $(".site .dropdown-text").attr("data-value","").html("请选择");
        constructionId="";
    }
    var $init_list2='<li><a data-value="">请选择</a></li>'
    for(var i in data){
        //企业切换工地后，所有区域与工地同步
        if(data.length>1&&window.cBool&&top.constructionId===data[i].constructionId){
            window.cBool=false;
            $(".site .dropdown-text").attr("data-value",data[i].constructionId).html(data[i].constructionName);
            constructionId=data[i].constructionId;
        }

        $init_list2+='<li><a data-value="'+data[i].constructionId+'">'+data[i].constructionName+'</a></li>';
    }

    $(".site .dropdown-menu").html($init_list2);
    if(fun){fun(constructionId)}
    $(".site .dropdown-menu li").click(function () {
        var index=$(this).index()-1;
        if(index<0){
            constructionId="";
            $(".site .dropdown-text").attr("data-value","").html("请选择");
        }else{
            $(".site .dropdown-text").attr("data-value",data[index].constructionId).html(data[index].constructionName);
            constructionId=data[index].constructionId;
            if(fun){fun(constructionId)}
        }

    })
}

//有默认工地
function initDefault(fun,bool) {
    if(!fun){fun=function () {}}
    //当工地端登录时隐藏区域工地
    if(areaConditions(fun)){
        return;
    }
    var $init_list1='';
    var my_json={
        "userId":userId
    };
    biyue.ajax({
        url: '/territory/queryAuthAreaByUserId',
        data: my_json,
        fun: function (data) {
            if(data.value){
                if(!top.areaId){
                    $(".area .dropdown-text").attr("data-value",data.value[0].areaId).html(data.value[0].areaName);
                    siteDefault(data.value[0].data,fun);
                }
                for(var i in data.value){
                    //企业切换工地后，所有区域与工地同步
                    if(top.areaId&&top.areaId===data.value[i].areaId){
                        $(".area .dropdown-text").attr("data-value",data.value[i].areaId).html(data.value[i].areaName);
                        siteDefault(data.value[i].data,fun);
                    }
                    $init_list1+=' <li><a data-value="'+data.value[i].areaId+'">'+data.value[i].areaName+'</a></li>';
                }
                $(".area .dropdown-menu").html($init_list1);
                $(".area .dropdown-menu>li").click(function () {
                    var index=$(this).index();
                    $(".area .dropdown-text").attr("data-value",data.value[index].areaId).html(data.value[index].areaName);
                    siteDefault(data.value[index].data,fun);
                })
            }
        }
    });
}
function siteDefault(data,fun){
    var $init_list2='';
    if(!window.cBool){
        $(".site .dropdown-text").attr("data-value",data[0].constructionId).html(data[0].constructionName);
        constructionId=data[0].constructionId;
        fun(constructionId);
    }
    for(var i in data){
        //企业切换工地后，所有区域与工地同步
        if(window.cBool&&top.constructionId===data[i].constructionId){
            window.cBool=false;
            $(".site .dropdown-text").attr("data-value",data[i].constructionId).html(data[i].constructionName);
            constructionId=data[i].constructionId;
            fun(constructionId);
        }
        $init_list2+='<li><a data-value="'+data[i].constructionId+'">'+data[i].constructionName+'</a></li>';
    }
    $(".site .dropdown-menu").html($init_list2);
    $(".site .dropdown-menu li").click(function () {
        var index=$(this).index();
        $(".site .dropdown-text").attr("data-value",data[index].constructionId).html(data[index].constructionName);
        constructionId=data[index].constructionId;
        fun(constructionId);
    })
}

//树形图区域与工地
function treeInit(func,bool) {
    if(bool===undefined){
        bool=true;
    }
    if(!func){
        func=function () {}
    }
    var $orgnaizeTree = $("#orgTree");
    var zTreeObj;
    var setting = {
        view: {
            fontCss:{"color":"#000","font-family":"宋体"},
            dbClickExpand:false,
            showLine:false
        },
        data: {
            simpleData: {
                enable:true,
                idKey:"id",
                pIdKey:"pId",
                rootPId:""
            }
        },
        callback: {
            onClick : function(event, treeId, treeNode) {
                nodeClick(event, treeId, treeNode);
            }
            , beforeClick:function(treeId, treeNode, clickFlag) {
                if(treeNode.level==0){
                    return false;
                }
            }
        }
    };
    // 发送获取树节点请求
    (function(){
        var url = ctx + "/territory/queryAreaTreeByUserId";
        $.post(url,function(data){
            if(data.status == 1){
                var json = data.value;
                zTreeObj = $.fn.zTree.init($orgnaizeTree, setting, json);
                var nodeP = zTreeObj.getNodes();//获取id为1的点
                var nodeC ="";
                if(nodeP.length>0){
                    nodeC=nodeP[0].children
                }
                if(nodeP.length==1&&nodeC.length==1){
                    $(".menu-left-tree").hide();
                    $(".videoplay").css('left',0);
                }
                zTreeObj.selectNode(nodeC[0]);//选择点
                zTreeObj.setting.callback.onClick(null, zTreeObj.setting.treeId, nodeC[0]);//调用事件
            } else{
                layer.alert(data.msg)
            }
        });
    })();

    //点击事件
    var nodeClick = function(event, treeId, treeNode) {
        if(treeNode && treeNode.level!=0) {
            var nodeId = treeNode.id;
            if(treeNode.level==0){
                areaId=nodeId;
                constructionId = "";
            }else if(treeNode.level==1){
                areaId=treeNode.getParentNode().id;
                constructionId = nodeId;
            }
            func(treeNode);
        }
    }
}

//获取所属公司
function comCompany(id,fun) {
    if(!id){id=""}
    if(!fun){fun=function(){}}
    $(".comCompany .dropdown-text").attr("data-value","").html("请选择所属公司");
    $.ajax({
        type: "post",
        url: ctx + '/projectCompany/selectCompany',
        data: {
            constructionId:id
        },
        success: function (data) {
            if(data.status == 1){
                if(data.value){
                    var $list='<li><a data-value="">全部所属公司</a></li>';
                    for(var i in data.value){
                        $list+='<li><a data-value="'+data.value[i].id+'">'+data.value[i].name+'</a></li>';
                    }
                    $(".comCompany .dropdown-menu").html($list);
                    $(".comCompany .dropdown-menu li").click(function () {
                        $(".comCompany .dropdown-text").attr("data-value",$(this).find('a').attr("data-value")).html($(this).find('a').html());
                        fun($(this).find('a').attr("data-value"));
                    })
                }
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.alert(String(e));
        }
    });
}

