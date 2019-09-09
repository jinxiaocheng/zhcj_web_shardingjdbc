var smzPerson={
    getData:function () {
        biyue.ajax({
            url:'/thridPerson/getPersonDetail',
            data:{
                id:biyue.urlSearch().id
            },
            fun:function (data) {
                var dataV =data.value;
                if(dataV){
                    var personInfo =dataV.personInfo;
                    for(var i in personInfo){
                        if(i==='standby7'){
                            if(personInfo[i]==0){
                                $("."+i).html("身份证");
                            }else if(personInfo[i]==1){
                                $("."+i).html("护照");
                            }else if(personInfo[i]==2){
                                $("."+i).html("军官证");
                            }
                        }else if(i==='registeredType'){
                            if(personInfo[i]==1){
                                $("."+i).html("农业户口");
                            }else if(personInfo[i]==2){
                                $("."+i).html("城镇户口");
                            }
                        }else if(i==='maritalStatus'){
                            if(personInfo[i]==1){
                                $("."+i).html("未婚 ");
                            }else if(personInfo[i]==2){
                                $("."+i).html("已婚");
                            }
                        }else if(i==='iSPass'){
                            if(personInfo[i]==0){
                                $("."+i).html("不合格 ");
                            }else if(personInfo[i]==1){
                                $("."+i).html("合格");
                            }
                        }else if(i==='sGrade'){
                            if(personInfo[i]==-1){
                                $("."+i).html(" 无考试 ");
                            }else{
                                $("."+i).html(personInfo[i]||"");
                            }
                        }else if(i==='fileName'){
                            var imgSrc='<img src="'+ctx+'/resources/static/images/smz/default.jpg" alt="">'
                            if(personInfo[i]){
                                imgSrc ='<img src="'+personInfo[i]+'" alt="">'
                            }

                            $(".personChartFile").html(imgSrc)
                        }else {
                            $("."+i).html(personInfo[i]||"");
                        }
                    }

                    //培训信息
                    var recordPersonInfos=dataV.recordPersonInfos;
                    var $list="";
                    if(recordPersonInfos.length>0){
                        for(var j in recordPersonInfos){
                            $list+='<tr><th scope="row">'+(parseFloat(j)+1)+'</th>\n' +
                                '                <td>'+recordPersonInfos[j].trainDepart+'</td>\n' +
                                '                <td>'+recordPersonInfos[j].trainName+'</td>\n' +
                                '                <td>'+recordPersonInfos[j].trainType+'</td>\n' +
                                '                <td>'+recordPersonInfos[j].trainTime+'</td>\n' +
                                '                <td>'+recordPersonInfos[j].trainPeriod+'</td>\n' +
                                '                <td>'+(recordPersonInfos[j].sGrade==-1?"无考试":recordPersonInfos[j].sGrade)+'</td>\n' +
                                '                <td>'+(recordPersonInfos[j].iSPass==='0'?"不合格":"合格")+'</td></tr>'
                        }
                    }else {
                        $list='<tr>\n' +
                            '                    <td colspan="8">\n' +
                            '                        <p style="margin: 0;text-align: center;color:#a6a6a6;letter-spacing: 2px">暂无数据</p>\n' +
                            '                    </td>\n' +
                            '                </tr>';
                    }
                    $("#trainTable tbody").html($list);
                }
            }
        })
    }
}