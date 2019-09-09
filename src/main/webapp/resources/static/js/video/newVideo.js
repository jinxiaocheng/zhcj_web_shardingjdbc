/**
 * Created by kongzheng on 2017/10/9.
 */
//视频监控权限控制
;(function control() {
    var search=window.location.search.replace("?","").split("&");
    var searchObj={};
    for(var i in search){
        searchObj[search[i].split("=")[0]]=search[i].split("=")[1];
    }
    if(searchObj.operationAuthority!==undefined){
        searchObj.operationAuthority=searchObj.operationAuthority.split(",");
        if(searchObj.operationAuthority[0]!='action'){
            $(".orientation").remove();
            $(".more_less").remove();
            $(".video_playback").remove();
            $(".time").remove();
            $(".YT_speed").remove();
        }
    }
})();

//variable
var cs_bool=true,//清晰度开关控制
    sc_bool=false,//云台速度
    sc_pageX=0,//云台控制器的x轴坐标
    sc_num=3, //云台速度等级
    sc_left=0, //云台控制器距离左边位置
    sc_array=[16,60,102,146,188,232,274],//刻度位置
    op_bool=true;
    op_boolA=true;

//云台控制
$(".PTZ li").click(function () {
    var ptz_num=$(".PTZ button").attr("data-value");
    changeWndNum(ptz_num);
});

// 清晰度
$(".con_sharpness").click(function () {
    var csWidth=$(this).width();
    video_num[g_iWndIndex]=video_num[g_iWndIndex]||[];
    if(video_num[g_iWndIndex][video_sharpness[g_iWndIndex]]!=false){
        video_num[g_iWndIndex][video_sharpness[g_iWndIndex]]=true;
    }
    if(video_num[g_iWndIndex][video_sharpness[g_iWndIndex]]){
        $(".con_sharpness p").stop().animate({"left":"-"+csWidth+"px"},200);
        $(".on_off").stop().animate({"right":"45px"},200,function () {
            changeChannel();
        });
        $(".con_sharpness").attr("kk-value","1")
    }else{
        $(".con_sharpness p").stop().animate({"left":"0px"},200);
        $(".on_off").stop().animate({"right":"-10px"},200,function () {
            changeChannel();
        });
        $(".con_sharpness").attr("kk-value","2");
    }
    video_num[g_iWndIndex][video_sharpness[g_iWndIndex]]=!video_num[g_iWndIndex][video_sharpness[g_iWndIndex]];
})

function con_sharpness() {
    var csWidth=$(".con_sharpness").width();
    video_num[g_iWndIndex]=video_num[g_iWndIndex]||[];
    if(video_num[g_iWndIndex][video_sharpness[g_iWndIndex]]!=false){
        video_num[g_iWndIndex][video_sharpness[g_iWndIndex]]=true;
    }
    if(!video_num[g_iWndIndex][video_sharpness[g_iWndIndex]]){
        $(".con_sharpness p").stop().animate({"left":"-"+csWidth+"px"},200);
        $(".on_off").stop().animate({"right":"45px"},200);
        $(".con_sharpness").attr("kk-value","1")
    }else{
        $(".con_sharpness p").stop().animate({"left":"0px"},200);
        $(".on_off").stop().animate({"right":"-10px"},200);
        $(".con_sharpness").attr("kk-value","2")
    }

}

//点击
$(".or").mousedown(function () {
    $(this).addClass("or_hover");
}).mouseup(function () {
    $(this).removeClass("or_hover");
}).mouseout(function () {
    $(this).removeClass("or_hover");
})
$(".or_center").mousedown(function () {
    $(this).addClass("or_center_hover");
}).mouseup(function () {
    $(this).removeClass("or_center_hover");
}).mouseout(function () {
    $(this).removeClass("or_center_hover");
})
$(".ml_more").mousedown(function () {
    $(this).addClass("ml_more_hover");
}).mouseup(function () {
    $(this).removeClass("ml_more_hover");
}).mouseout(function () {
    $(this).removeClass("ml_more_hover");
})
$(".ml_less").mousedown(function () {
    $(this).addClass("ml_less_hover");
}).mouseup(function () {
    $(this).removeClass("ml_less_hover");
}).mouseout(function () {
    $(this).removeClass("ml_less_hover");
})
$(".on_video").mousedown(function () {
    $(this).addClass("on_video_hover");
}).mouseup(function () {
    $(this).removeClass("on_video_hover");
}).mouseout(function () {
    $(this).removeClass("on_video_hover");
})
$(".off_video").mousedown(function () {
    $(this).addClass("off_video_hover");
}).mouseup(function () {
    $(this).removeClass("off_video_hover");
}).mouseout(function () {
    $(this).removeClass("off_video_hover");
})
$(".take_video").mousedown(function () {
    $(this).addClass("take_video_hover");
}).mouseup(function () {
    $(this).removeClass("take_video_hover");
}).mouseout(function () {
    $(this).removeClass("take_video_hover");
})
$(".search").mousedown(function () {
    $(this).addClass("search_hover");
}).mouseup(function () {
    $(this).removeClass("search_hover");
}).mouseout(function () {
    $(this).removeClass("search_hover");
})
$(".download_ico").click(function () {
    $(this).addClass("download_ico_hover");
})
$(".cp1").mousedown(function () {
    $(this).addClass("cp1_hover");
}).mouseup(function () {
    $(this).removeClass("cp1_hover");
}).mouseout(function () {
    $(this).removeClass("cp1_hover");
})
$(".cp2").mousedown(function () {
    $(this).addClass("cp2_hover");
}).mouseup(function () {
    $(this).removeClass("cp2_hover");
}).mouseout(function () {
    $(this).removeClass("cp2_hover");
})
$(".cp3").mousedown(function () {
    $(this).addClass("cp3_hover");
}).mouseup(function () {
    $(this).removeClass("cp3_hover");
}).mouseout(function () {
    $(this).removeClass("cp3_hover");
})
$(".cp4").mousedown(function () {
    $(this).addClass("cp4_hover");
}).mouseup(function () {
    $(this).removeClass("cp4_hover");
}).mouseout(function () {
    $(this).removeClass("cp4_hover");
})
$(".cp5").mousedown(function () {
    $(this).addClass("cp5_hover");
}).mouseup(function () {
    $(this).removeClass("cp5_hover");
}).mouseout(function () {
    $(this).removeClass("cp5_hover");
})
$(".cp6").mousedown(function () {
    $(this).addClass("cp6_hover");
}).mouseup(function () {
    $(this).removeClass("cp6_hover");
}).mouseout(function () {
    $(this).removeClass("cp6_hover");
})

//点击视频回放
$(".video_download").on('click','li',function () {
    var startTime=$(this).find('.startTime').html();
    var endTime=$(this).find('.endTime').html();
    clickStartPlayback(startTime,endTime)
})


//云台速度

$(".speed_controller .c_c").mousedown(function () {
    sc_pageX=mousePosition().x;
    sc_bool=true;
})

$("body").mousemove(function () {
    if(sc_bool){
        sc_hover()
    }
})

//禁用拖拽
document.ondragstart = function() {
    return false;
}
//IE禁止文本选择
document.onselectstart = function(){
    return false;
}
$('body').mouseup(function () {
    sc_bool=false;
    for(var i=0;i<sc_array.length;i++){
        if(sc_left==sc_array[i]){
            sc_num=i;
            $(".speed_controller").attr("sc-num",sc_num+1);
            return;
        }
        if(sc_left>sc_array[i]&&sc_left<sc_array[i+1]){
            var sc_mean=(sc_array[i]+sc_array[i+1])/2;
            if(sc_left<sc_mean){
                sc_num=i;
                $(".speed_controller .c_c").animate({"left":sc_array[i]+"px"},200);
                $(".speed_controller .c_n").animate({"left":(sc_array[i]-20)+"px"},200);
                $(".speed_controller").attr("sc-num",sc_num+1);
                return;
            }else{
                sc_num=i+1;
                $(".speed_controller .c_c").animate({"left":sc_array[i+1]+"px"},200);
                $(".speed_controller .c_n").animate({"left":(sc_array[i+1]-20)+"px"},200);
                $(".speed_controller").attr("sc-num",sc_num+1);
                return;
            }
        }
    }
})

function sc_hover() {
    var cc_page=mousePosition();
    sc_left=parseFloat(cc_page.x-sc_pageX)+sc_array[sc_num];
    if(sc_left<sc_array[0]){
        sc_left=sc_array[0]
    }else if(sc_left>sc_array[6]){
        sc_left=sc_array[6]
    }
    $(".speed_controller .c_c").css("left",sc_left+"px");
    $(".speed_controller .c_n").css("left",(sc_left-20)+"px");
}
// 获取鼠标位置
function mousePosition(){
    var ev=event||window.event;
    if(ev.pageX || ev.pageY){//firefox、chrome等浏览器
        return {x:ev.pageX,y:ev.pageY};
    }
    return {// IE浏览器
        x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,
        y:ev.clientY + document.body.scrollTop - document.body.clientTop
    };
}


$(".pngfix").on("click",function () {
    var obj=$(this).parent();
    var box_obj=$(".container-full");
    if(op_bool){
        obj.animate({"right":"-345px"},300,function () {
            $(".pngfix").addClass("close");
        })
        box_obj.animate({"paddingRight":"20"},300);
    }else{
        $(".pngfix").removeClass("close");
        obj.stop().animate({"right":"0"},300);
        box_obj.animate({"paddingRight":"370px"},300);
    }
    op_bool=!op_bool;

})
$(".pngfixA").on("click",function () {
    var obj=$(this).parent();
    var box_obj=$(".container-full");
    if(op_boolA){
        obj.animate({"left":"-281px"},300,function () {
            $(".pngfixA").addClass("close");
        })
        box_obj.animate({"paddingLeft":"20px"},300);
    }else{
        $(".pngfixA").removeClass("close");
        obj.stop().animate({"left":"0"},300);
        box_obj.animate({"paddingLeft":"300px"},300);
    }
    op_boolA=!op_boolA;
});

//des
function decryptByDES(ciphertext, key) {
    var keyHex = CryptoJS.enc.Utf8.parse(key);
    var decrypted = CryptoJS.DES.decrypt({
        ciphertext: CryptoJS.enc.Base64.parse(ciphertext)
    }, keyHex, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    return decrypted.toString(CryptoJS.enc.Utf8);
}