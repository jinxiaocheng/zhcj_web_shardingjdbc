var moduleView = {
    getData:function () {
        var objSearch=biyue.urlSearch();
        biyue.ajax({
            url:"/hook/getNvrDetail",
            data:{
                id:objSearch.id
            },
            fun:function (data) {
                var dataV = data.value,$list="";
                for(var i in dataV){
                    if(i === "list"){
                        var dataL = dataV[i];
                        for(var j in dataL){
                            var datak = dataL[j].list,
                                len = datak.length;
                            for(var k in datak){
                                if(k==="0"){
                                    $list+=' <tr>\n' +
                                        '                    <td rowspan="'+len+'">'+dataL[j].equipmentName+'</td>\n' +
                                        '                    <td>'+datak[k].name+'</td>\n' +
                                        '                    <td>'+datak[k].channelNo+'</td>\n' +
                                        '                    <td>'+(datak[k].flag===1?"<span style=\'color: #00d2fc\'>云台控制</span>":"<span style='color: #ff9400'>非云台控制</span>")+'</td>\n' +
                                        '                    <td>'+(datak[k].status===1?"<span style=\'color:#28e800\'>在线</span>":"<span style='color: #FF5722'>离线</span>")+'</td>\n' +
                                        '                </tr>'
                                }else{
                                    $list+=' <tr>\n' +
                                        '                    <td>'+datak[k].name+'</td>\n' +
                                        '                    <td>'+datak[k].channelNo+'</td>\n' +
                                        '                    <td>'+(datak[k].flag===1?"<span style=\'color: #00d2fc\'>云台控制</span>":"<span style='color: #ff9400'>非云台控制</span>")+'</td>\n' +
                                        '                    <td>'+(datak[k].status===1?"<span style=\'color:#28e800\'>在线</span>":"<span style='color: #FF5722'>离线</span>")+'</td>\n' +
                                        '                </tr>'
                                }
                            }
                        }
                        $(".channel tbody").html($list);
                    }else{
                        $('.'+i).html(dataV[i]);
                    }
                }
            }
        });
    }
}