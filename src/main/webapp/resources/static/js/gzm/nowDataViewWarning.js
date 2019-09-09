var biyueView = {
    getData:function () {
        var _self = this;
        biyue.ajax({
            url:'/highformworkRealTimeController/queryAlarmInfo',
            data: {
                equipmentId: biyue.urlSearch().id
            },
            fun: function (data) {
                var dataV = data.value,dataT=[],$list="";
                for(var i in dataV){
                    if(!dataT[dataV[i].equipmentId]){dataT[dataV[i].equipmentId]=[]}
                    dataT[dataV[i].equipmentId][dataV[i].type-1]=dataV[i];
                }
                for(var j in dataT){
                    var dataK = dataT[j];
                    for(var k = 0;k<dataK.length;k++){
                        if(k===0){
                            $list+=' <tr>\n' +
                                '            <td rowspan="3">'+dataK[k].flowName+'</td>\n' +
                                '            <td>预警值</td>\n' +
                                '            <td>'+dataK[k].electric+'</td>\n' +
                                '            <td>'+dataK[k].xAngle+'</td>\n' +
                                '            <td>'+dataK[k].yAngle+'</td>\n' +
                                '            <td>'+dataK[k].kpa+'</td>\n' +
                                '            <td>'+dataK[k].displace+'</td>\n' +
                                '        </tr>'
                        }else{
                            $list+=' <tr>\n' +
                                '            <td>'+(dataK[k].type===2?"报警值":"控制值")+'</td>\n' +
                                '            <td>'+dataK[k].electric+'</td>\n' +
                                '            <td>'+dataK[k].xAngle+'</td>\n' +
                                '            <td>'+dataK[k].yAngle+'</td>\n' +
                                '            <td>'+dataK[k].kpa+'</td>\n' +
                                '            <td>'+dataK[k].displace+'</td>\n' +
                                '        </tr>';
                        }
                    }
                }
                $(".channel tbody").html($list);
            }
        })
    }
}
