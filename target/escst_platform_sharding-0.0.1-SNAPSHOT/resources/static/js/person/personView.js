var moduleView = {
    getData:function () {
        var objSearch=biyue.urlSearch();
        biyue.ajax({
            url:"/person/detailInfo?personId="+objSearch.id,
            fun:function (data) {
                var dataV = data.value;
                for(var i in dataV){
                    if(i==="headFilePath"){
                        $(".personImg img").attr("src",dataV[i]);
                    }else if(i==="attendanceType"){
                        var attendanceType = "";
                        if(dataV[i]===0){
                            attendanceType = "无识别方式";
                        }else if(dataV[i]===1){
                            attendanceType = "刷卡";
                        }else if(dataV[i]===2){
                            attendanceType = "人脸识别"
                        }else if(dataV[i]===3){
                            attendanceType = "刷卡/人脸识别"
                        }
                        $('.attendanceType').html(attendanceType);
                    }else if(i==="sex"){
                        var sex = "";
                        if(dataV[i]===1){
                            sex = "男";
                        }else if(dataV[i]===2){
                            sex = "女";
                        }
                        $('.sex').html(sex);
                    }else if(i==="isLeader"){
                        var isLeader = "";
                        if(dataV[i]===1){
                            isLeader = "是";
                        }else if(dataV[i]===0){
                            isLeader = "否";
                        }
                        $('.isLeader').html(isLeader);
                    }else{
                        $('.'+i).html(dataV[i]);
                    }
                }
            }
        });
    }
}