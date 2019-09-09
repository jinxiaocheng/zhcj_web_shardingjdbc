// 设置表格
function getGridHead() {
    return [
        {'label': 'id', 'name': 'id', 'index': 'id', 'hidden': true,width:"0px"},
        {'label': 'downloadUrl', 'name': 'downloadUrl', 'index': 'downloadUrl', 'hidden': true,width:"0px"},
        {'label': '通道名称', 'name': 'deviceId', 'index': 'deviceId','width':'100px'},
        {'label': '开始时间', 'name': 'playTime', 'index': 'playTime','width':'100px',formatter:function(cellvalue, options, rowObject){
            var pattern = /(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})/;
            var startTime = cellvalue.replace(pattern, '$1-$2-$3 $4:$5:$6');
            return startTime;
        }},
        {'label': '录像时长', 'name': 'duration', 'index': 'duration','width':'100px',formatter:function(cellvalue, options, rowObject){
            return secToTime(parseInt(cellvalue));

        }},
        {'label': '视频地址', 'name': 'playUrl', 'index': 'playUrl'},
        {'label': '操作', 'name': 'paly', 'index': 'paly','width':'50px','align':'center',formatter:function(cellvalue, options, rowObject) {
            var temp = "<button class='btn btn-info btn-xs ' data-name='"+rowObject.deviceId+"' data-url='"+rowObject.playUrl+"' style='background-color: transparent;margin-right: 10px;' onclick = 'play(this)'>播放</button>";
            temp += "<button class='btn btn-info btn-xs ' data-download='"+rowObject.downloadUrl+"' style='background-color: transparent' onclick = 'download(this)'>下载</button>";
            return temp;
        }}
    ];
};
//时间转换
function secToTime(s) {
    var t;
    if(s > -1){
        var hour = Math.floor(s/3600);
        var min = Math.floor(s/60) % 60;
        var sec = s % 60;
        if(hour < 10) {
            t = '0'+ hour + ":";
        } else {
            t = hour + ":";
        }

        if(min < 10){t += "0";}
        t += min + ":";
        if(sec < 10){t += "0";}
        t += sec;
    }
    return t;
}
//获取Url
function getGridUrl() {
    return ctx + "/easyNvr/hlsList";
};
// 点击查询，获取查询所需要的条件，重新加载表格
function getGridParamJson() {
    var postPrams ={

    }
    return postPrams;
};
//查询
$("#search_link").click(function () {
    var url = getGridUrl();
    var postParams = getGridParamJson();
    $("#gridTable").jqGrid("setGridParam", {
        url: url,
        postData: postParams,
        page: 1
    }).trigger("reloadGrid");
});

//视频播放
function play(data) {
    var videoUrl=$(data).attr('data-url');
    var name=$(data).attr('data-name');
    var player=null;
    var my_open=layer.open({
        type: 1,
        anim:5,
        maxmin: true,
        title:'视频回放('+name+')',
        content: $('#reviewPlay'),
        success:function (layero) {
            setTimeout(function() {
                $(layero).removeClass('layer-anim');
            }, 0);
            $("#reviewPlay").html($("#model").html());
            $("#reviewPlay source").attr("src",videoUrl).attr("type","application/x-mpegURL");
            $("#reviewPlay video").attr("id","videojs");
            player = videojs("videojs",{
                "techOrder": ["flash","html5"],
                autoplay : true
            });
            console.log(player)
        },
        cancel:function () {
            if(player){
                player.dispose();
                player = null;
            }
            $("#reviewPlay").html("");
        },
        area: ['80%', '80%']
    });
}

//视频下载
function download(data) {
    var downloadUrl=$(data).attr('data-download');
    window.open(downloadUrl);
}

//全屏
var fullscreen=function(elem){

}

$('body').on('click','.vjs-fullscreen-control',function () {
    var elem=$(this).parent()[0];
    fullscreen(elem);
})