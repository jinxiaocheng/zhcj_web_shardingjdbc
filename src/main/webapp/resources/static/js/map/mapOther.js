//工程进度(工地端)
function progressOfWorksT() {
    var index = layer.load(); //换了种风格
    $.ajax({
        type: "post",
        url: ctx + '/construction/queryScheduleByConstructionId?constructionId='+constructionId,
        success: function (data) {
            layer.close(index);
            if(data.status == 1){
                if(data.value){
                    var dataValue =data.value;
                    if(dataValue.constructionScheduleVo){
                        var startTime=dataValue.constructionScheduleVo.startDate.split('-');
                        var endTime=dataValue.constructionScheduleVo.endDate.split('-');
                        $(".progressBar p").eq(0).html(dataValue.constructionScheduleVo.percent);
                        $(".progressBar .bar i").css('width',dataValue.constructionScheduleVo.percent);
                        $(".endTime span").html(endTime[1] + ' - ' + endTime[2]+ '<br>' + endTime[0] );
                        $(".startTime span").html(startTime[1] + ' - ' + startTime[2]+ '<br>' + startTime[0] );
                        $(".surplusTime span").html(dataValue.constructionScheduleVo.daysRemain);
                    }
                    if(dataValue.name)$("#infoOfWorks .m1 span").html(dataValue.name);
                    if(dataValue.development)$("#infoOfWorks .m2 span").html(dataValue.development);
                    if(dataValue.building)$("#infoOfWorks .m3 span").html(dataValue.building);
                    if(dataValue.supervision)$("#infoOfWorks .m4 span").html(dataValue.supervision);

                }
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.close(index);
            layer.alert(String(e));
        }
    });
}

//人员在线情况
function personNumT(constructionId) {
    var index = layer.load(); //换了种风格
    $.ajax({
        type: "post",
        url: ctx + '/home/getHomeBaseInfo',
        data: {
            constructionId: constructionId
        },
        success: function (data) {
            layer.close(index);
            if (data.status == 1) {
                if (data.value) {
                    var dataValue = data.value;
                    $(".temperature").html('<label  class="w' + dataValue.type + ' w"></label>' + (dataValue.weather||"--") + '&nbsp;' + (dataValue.temperature||"-") + '℃');
                    $(".nowNum span").html(dataValue.locale);
                    $(".todayNum span").html(dataValue.totalNum);
                    $(".modelList").eq(0).find('.m1 span').html(dataValue.camera_online);
                    $(".modelList").eq(0).find('.m2 span').html(dataValue.carera_offline);
                    $(".modelList").eq(1).find('.m1 span').html(dataValue.towerCrane_online);
                    $(".modelList").eq(1).find('.m2 span').html(dataValue.towerCrane_offline);
                    $(".modelList").eq(2).find('.m1 span').html(dataValue.lift_online);
                    $(".modelList").eq(2).find('.m2 span').html(dataValue.lift_offline);
                    $(".modelList").eq(3).find('.m1 span').html(dataValue.environment_online);
                    $(".modelList").eq(3).find('.m2 span').html(dataValue.environment_offline);
                    $(".modelList").eq(4).find('.m1 span').html(dataValue.qualityCorrective);
                    $(".modelList").eq(4).find('.m2 span').html(dataValue.qualityChecked);
                    $(".modelList").eq(5).find('.m1 span').html(dataValue.safeCorrective);
                    $(".modelList").eq(5).find('.m2 span').html(dataValue.safeChecked);

                }
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.close(index);
            layer.alert(String(e));
        }
    });
}

//工作动态
function trendsOfWorksT(type,num) {
    var index = layer.load(); //换了种风格
    $.ajax({
        type: "post",
        url: ctx + '/workTrend/queryList?type='+type+"&number="+num,
        data:{
            constructionId:constructionId
        },
        success: function (data) {
            layer.close(index);
            if(data.status == 1){
                var $list="";
                if(data.value){
                    for(var i in data.value){
                        $list+='<div class="trendsBox">\n' +
                            '                <p>'+data.value[i].constructionContent+'</p>' +
                            '                <span>'+data.value[i].businessTime+'</span>\n' +
                            '            </div>';
                    }
                    $('.trends').append($list);
                    if(type==1){
                        num=data.value.length;
                    }else{
                        num=num+data.value.length;
                    }
                }
            } else {
                console.log(data.msg);
            }
             //trendsOfWorks(2,num);
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.close(index);
            console.log(String(e));
            //trendsOfWorks(2,num);
        }
    });
}

//点击切换到企业端
$('#conImgBox h2>a').click(function () {
    var link=top.indexUrl;
    $(window.parent.document).find('[biyue-id="29d99c0df71f11e6a0d9002590f074f1"]').attr('biyue-link',ctx?link.split(ctx)[1]:link);
    window.location.href= link;
})
// $('#conImgBox h2>a').click(function () {
//     var link=top.indexUrl;
//     $(window.parent.document).find('.li-col.active a').attr('link',ctx?link.split(ctx)[1]:link);
//     window.parent.document.getElementById("mainframe").src = link;
// })