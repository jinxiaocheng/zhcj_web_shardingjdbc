var  constructionId=top.constructionId||"";
if(top.constructionId){
    window.cBool=true;
}

//控制条件
function areaConditions(fun) {
    if(top.globalData.userType==3){
        $(".area").hide();
        $(".site").hide();
        $("button[kk-prop=\"areaId\"]").attr("areaId",top.globalData.areaId);
        $("button[kk-prop=\"constructionid\"]").attr("constructionid",top.globalData.constructionId).html(top.globalData.constructionName+'<span class="caret"></span>');
        constructionId=top.globalData.constructionId;
        if(fun){fun(constructionId)}
        return true;
    }else{
        return false;
    }
}

//区域与工地加载
function init(obj) {
    //当工地端登录时隐藏区域工地
    if(areaConditions(obj)){
        return;
    }
    var $init_list1="";
    var my_json={
        "userId":userId
    };
    // 判断是否存在，并将其过滤
    if(!obj){
        obj=function () {}
    }

    $.ajax({
        type: "post",
        url: ctx + '/territory/queryAuthAreaByUserId',
        data: my_json,
        success: function (data) {
            if(data.status == 1){
                if(data.value){
                    if(data.value.length==1){
                        $(".area>button").attr("areaId",data.value[0].areaId).html(data.value[0].areaName+'<span class="caret" style="margin-left:6px"></span>');
                        site(obj,data.value[0].data);
                    }else if(!top.areaId){
                        $(".area>button").attr("areaId","").html('请选择区域 <span class="caret" style="margin-left:6px"></span>');
                        constructionId="";
                    }

                    for(var i in data.value){
                        //企业切换工地后，所有区域与工地同步
                        if(data.value.length>1&&top.areaId&&top.areaId===data.value[i].areaId){
                            $(".area>button").attr("areaId",data.value[i].areaId).html(data.value[i].areaName+'<span class="caret" style="margin-left:6px"></span>');
                            site(obj,data.value[i].data);
                        }
                        $init_list1+='<li><a areaId="'+data.value[i].areaId+'">'+data.value[i].areaName+'</a></li>';
                    }

                    $(".area>ul").html($init_list1);
                    $(".area>ul>li").click(function () {
                        var index=$(this).index();
                        $(".area>button").attr("areaId",data.value[index].areaId).html(data.value[index].areaName+'<span class="caret" style="margin-left:6px"></span>');
                        site(obj,data.value[index].data);
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

function site(obj,data){
    var $init_list2="";

    if(data.length==1){
        $(".site>button").attr("constructionId",data[0].constructionId).html(data[0].constructionName+'<span class="caret" style="margin-left:6px"></span>');
        constructionId=data[0].constructionId;
    }else if(!window.cBool){
        constructionId="";
        $(".site>button").attr("constructionId","").html('请选择工地<span class="caret" style="margin-left:6px"></span>');
    }

    for(var i in data){
        //企业切换工地后，所有区域与工地同步
        if(data.length>1&&window.cBool&&top.constructionId===data[i].constructionId){
            window.cBool=false;
            $(".site>button").attr("constructionId",data[i].constructionId).html(data[i].constructionName+'<span class="caret" style="margin-left:6px"></span>');
            constructionId=data[i].constructionId;
        }
        $init_list2+='<li><a constructionId="'+data[i].constructionId+'">'+data[i].constructionName+'</a></li>';
    }
    obj(constructionId);
    $(".site>ul").html($init_list2);
    $(".site>ul>li").click(function () {
        var index=$(this).index();
        $(".site>button").attr("constructionId",data[index].constructionId).html(data[index].constructionName+'<span class="caret" style="margin-left:6px"></span>');
        constructionId=data[index].constructionId;
        obj(constructionId)

    })
}

//区域与工地加载 (修改)
function initCh(obj) {
    //当工地端登录时隐藏区域工地
    if(areaConditions(obj)){
        return;
    }
    var $init_list1="";
    var my_json={
        "userId":userId
    };
    // 判断是否存在，并将其过滤
    if(!obj){
        obj=function () {}
    }

    $.ajax({
        type: "post",
        url: ctx + '/territory/queryAuthAreaByUserId',
        data: my_json,
        success: function (data) {
            if(data.status == 1){
                if(data.value){
                    if(data.value.length==1&&changeType!=1){
                        $(".area>button").attr("areaId",data.value[0].areaId).html(data.value[0].areaName+'<span class="caret" style="margin-left:6px"></span>');
                        siteCh(obj,data.value[0].data);
                    }else if(!top.areaId&&changeType!=1){
                        $(".area>button").attr("areaId","").html('请选择区域 <span class="caret" style="margin-left:6px"></span>');
                        constructionId="";
                    }

                    for(var i in data.value){
                        if(data.value[i].areaId==changeAreaId){
                            siteCh(obj,data.value[i].data)
                        }
                        if(!changeAreaId&&data.value.length>1&&top.areaId&&top.areaId===data.value[i].areaId){
                            $(".area>button").attr("areaId",data.value[i].areaId).html(data.value[i].areaName+'<span class="caret" style="margin-left:6px"></span>');
                            siteCh(obj,data.value[i].data);
                        }
                        $init_list1+='<li><a areaId="'+data.value[i].areaId+'">'+data.value[i].areaName+'</a></li>';
                    }
                    $(".area>ul").html($init_list1);

                    $(".area>ul>li").click(function () {
                        changeType=0;
                        var index=$(this).index();
                        $(".area>button").attr("areaId",data.value[index].areaId).html(data.value[index].areaName+'<span class="caret" style="margin-left:6px"></span>');
                        siteCh(obj,data.value[index].data);
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
function siteCh(obj,data){
    var $init_list2="";

    if(data.length==1&&changeType!=1){
        $(".site>button").attr("constructionId",data[0].constructionId).html(data[0].constructionName+'<span class="caret" style="margin-left:6px"></span>');
        constructionId=data[0].constructionId;
    }else if(!window.cBool&&changeType!=1){
        constructionId="";
        $(".site>button").attr("constructionId","").html('请选择工地<span class="caret" style="margin-left:6px"></span>');
    }

    for(var i in data){
        //企业切换工地后，所有区域与工地同步
        if(changeType!=1&&data.length>1&&window.cBool&&top.constructionId===data[i].constructionId){
            window.cBool=false;
            $(".site>button").attr("constructionId",data[i].constructionId).html(data[i].constructionName+'<span class="caret" style="margin-left:6px"></span>');
            constructionId=data[i].constructionId;
        }
        $init_list2+='<li><a constructionId="'+data[i].constructionId+'">'+data[i].constructionName+'</a></li>';
    }
    obj(constructionId)
    $(".site>ul").html($init_list2);
    $(".site>ul>li").click(function () {
        var index=$(this).index();
        $(".site>button").attr("constructionId",data[index].constructionId).html(data[index].constructionName+'<span class="caret" style="margin-left:6px"></span>');
        constructionId=data[index].constructionId;
        obj(constructionId);
    })
}

//点击下拉列表获取值
function selectEvent(elementId) {
    $("." + elementId + ">ul>li").click(function() {
        var index = $(this).index();
        var dataValue = $(this).children("a").attr("data-value");
        var dataName = $(this).children("a").html();
        $("." + elementId + ">button").attr(elementId,dataValue).html(dataName+'<span class="caret" style="margin-left:6px"></span>');
    });
}