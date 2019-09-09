//时间轨迹页面
function openWin(url) {
    //点击历史轨迹 激活 时间轨迹
    $(".ui-report").on('click','.historical-route-btn',function () {
        var guid=$(this).attr('data-id'),
            constructionId = $(this).attr('data-constructionId');
        timeTrack(guid,constructionId,url);
        layer.open({
            type: 1,
            title: false,
            closeBtn: 2,
            shadeClose: true,
            skin: 'yourclass',
            area: ['80%', '80%'],
            content: $('.popoverBox'),
            cancel:function () {
                $('.popoverBox').hide();
            }
        });
    })
}

//查询人脸对比人员信息
function getFaceInfo(obj) {
    if(!obj){obj={}}
    var index = layer.load(); //换了种风格
    var biyue = new biYue();
    var correctStartDate=$("#time1").val(),
        correctEndDate=$("#time2").val(),
        equipmentId=$("#video").attr("data-value"),
        Name=$("#name").val(),
        idCard=$("#idCard").val();
    $('.wrapper .nullInfo').hide();
    $.ajax({
        type: "post",
        url: ctx + '/faceRecognition/listFaceContrast',
        data:{
            correctStartDate:correctStartDate,
            correctEndDate:correctEndDate,
            equipmentId:equipmentId,
            Name:Name,
            idCard:idCard,
            rowNum: obj.link||10,
            page: obj.curr||1
        },
        success: function (data) {
            layer.close(index);
            if (data.status == 1) {
                var $span='';
                biyue.laypage({
                    limit:10,
                    curr:data.value.currentPage,
                    num:data.value.totalRecord,
                    fun:function (data,first) {
                        if(!first){
                            getFaceInfo(data);
                        }
                    }
                });
                if(!data.value.rows||data.value.rows.length==0){
                    $('.wrapper .nullInfo').show();
                }
                if (data.value) {
                    var dataV=data.value.rows,$list="";
                    $list+='<li><div class="aisle-box">\n';
                    for(var i in dataV){
                        var dataVT=dataV[i];
                        $list+='<div class="faceContrast-box">\n' +
                            '                        <div class="imgA"><img src="'+dataVT.imageUrl+'" alt=""></div>\n' +
                            '                        <div class="imgB"><img src="'+dataVT.cardImagePath+'" alt=""></div>\n' +
                            '                        <div class="faceContrast-info">\n' +
                            '                            <p class="percent"><label>相似度:</label><span>'+dataVT.score+'</span></p>\n' +
                            '                            <p class="name"><label>姓名:</label><span>'+dataVT.name+'</span></p>\n' +
                            '                            <p class="idCard"><label>身份证:</label><span>'+dataVT.idCard+'</span></p>\n' +
                            '                            <p  class="img-time"><label>时间:</label><span>'+dataVT.timeStr+'</span></p>\n' +
                            '                            <a  class="historical-route-btn" data-constructionId="'+dataVT.constructionId+'" data-id="'+dataVT.guid+'">历史轨迹</a>\n' +
                            '                        </div>\n' +
                            '                    </div>';
                    }
                    $list+=    '                </div></li>';
                    $('.wrapper ul').html($list);
                }
            } else {
                console.log(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.close(index);
            console.log(String(e));
        }
    });
}

//获取摄像头
function getCamera() {
    $.ajax({
        type: "post",
        url: ctx + '/faceRecognition/listCamera',
        data:{
            constructionId: constructionId
        },
        success: function (data) {
            if (data.status == 1) {
                console.log(data);
                if (data.value) {
                    var dataV=data.value,$list='<li ><a data-value="">全部</a></li>';
                    for(var i in dataV){
                        $list+='<li ><a data-value="'+dataV[i].equipmentId+'">'+dataV[i].camName+'</a></li>'
                    }
                    $('.video').html($list);
                    $(".video").parent().Bsselect();
                }
            } else {
                console.log(data.msg);
            }
            //  trendsOfWorks(2, num);
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            console.log(String(e));
            // trendsOfWorks(2, num);
        }
    });
}

//查询个人时间轨迹
function timeTrack(id,cId,url) {
    var biyue = new biYue();
    var index = layer.load(); //换了种风格
    var num = faceInfoNum($(".ui-report"),$(".face-img-box"))*2;
    var correctStartDate=$("#time1").val(),
        correctEndDate=$("#time2").val();
    $('.popoverBox .aisle-box').html("");
    function ajax(obj) {
        if(!obj){obj={}}
        $.ajax({
            type: "post",
            url: ctx + url,
            data:{
                guid:id,
                constructionId: cId,
                correctStartDate:correctStartDate,
                correctEndDate:correctEndDate,
                rowNum: obj.link||num,
                page: obj.curr||1
            },
            success: function (data) {
                layer.close(index);
                if (data.status == 1) {
                    biyue.laypage({
                        limit:num,
                        elem:'laypage1',
                        curr:data.value.currentPage,
                        num:data.value.totalRecord,
                        fun:function (data,first) {
                            if(!first){
                                ajax(data);
                            }
                        }
                    });
                    if (data.value) {
                        var dataV=data.value.rows,$list="";
                        for(var i in dataV){
                            var dataVT=dataV[i];
                            $list+='<div class="face-img-box">\n' +
                                '            <div class="face-img">\n' +
                                '                            <p class="img-time">'+dataVT.timeStr+'</p>\n' +
                                '                            <img src="'+dataVT.imageUrl+' " alt="">\n' +
                                '                            <p class="aisle-name">'+dataVT.camName+'</p>\n' +
                                '            </div>\n' +
                                '        </div>'
                        }
                        $('.popoverBox .aisle-box').html($list);
                    }
                } else {
                    console.log(data.msg);
                }
            },
            error: function (data, status, e) {   //提交失败自动执行的处理函数
                layer.close(index);
                console.log(String(e));
            }
        });
    }
    ajax();
}

//时间设置
function curentTime(now){
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日

    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var ss = now.getSeconds();           //秒

    var clock = year + "-";

    if(month < 10)
        clock += "0";

    clock += month + "-";

    if(day < 10)
        clock += "0";

    clock += day + " ";

    if(hh < 10)
        clock += "0";

    clock += hh + ":";
    if (mm < 10) clock += '0';
    clock += mm + ":";

    if (ss < 10) clock += '0';
    clock += ss;
    return(clock);
}

//一页显示多少信息算法
function faceInfoNum(dom1,dom2) {
    var w1 = dom1.width()*0.8,
        w2 = 170;
    var num = Math.floor(w1/w2);
    return num ;
}