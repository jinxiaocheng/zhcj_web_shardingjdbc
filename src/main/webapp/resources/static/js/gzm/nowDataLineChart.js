var biyueView = {
    echarts:function (obj) {
        var charts = echarts.init(document.getElementById(obj.id));
        option = {
            title:{
                text:obj.title,
                textStyle:{
                    fontSize:16
                }
            },
            legend:{
                data:obj.legend,
                top:26
            },
            grid:{
                top:90,
                left:50,
                bottom:60,
                right:10
            },
            tooltip : {
                trigger: "axis"
            },
            xAxis:[
                {
                    type: 'category',
                    boundaryGap: false,
                    axisLine:{
                        show:true,
                        lineStyle:{
                            color:["#9f9f9f"]
                        }
                    },
                    data: obj.dataX,
                    axisTick:{
                        show:true,
                        lineStyle:{
                            color:["#9f9f9f"]
                        }
                    },
                    splitLine:{
                        show:false,
                        lineStyle:{
                            color:["#dfdfdf"]
                        }
                    }
                }
            ] ,
            yAxis : [
                {
                    name:obj.nameY,
                    type: 'value',
                    minInterval: 0.1,
                    boundaryGap: [0, '100%'],
                    axisLine:{
                        show:true,
                        lineStyle:{
                            color:["#9f9f9f"]
                        }
                    },
                    splitLine:{
                        show:false,
                        lineStyle:{
                            color:["#dfdfdf"]
                        }
                    },
                    axisTick:{
                        show:true,
                        lineStyle:{
                            color:["#9f9f9f"]
                        }
                    }
                }
            ],
            dataZoom: [{
                type: 'inside',
                start: 0,
                height: 22,
                end: 100
            }, {
                height: 24,
                bottom:0,
                start: 0,
                end: 100,
                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                handleSize: '70%',
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                }
            }],
            series:obj.series
        };
        charts.setOption(option);
        window.onresize = charts.resize;
    },
    getData:function () {
        var _self = this;
        biyue.ajax({
            url:'/highformworkRealTimeController/queryTrend',
            data: JSON.stringify({
                flowId: biyue.urlSearch().id,
                constructionId: biyue.urlSearch().constructionId
            }),
            contentType:'application/json',
            fun: function (data) {
                var dataV = data.value,x=[],
                    yw={},//位移
                    yy={};//压力
                for(var i in dataV){
                    var time = dataV[i].createTime.split(" ");
                    x.push(time[1]+'\n'+time[0]);
                    for(var j in dataV[i].list){
                        var list =dataV[i].list[j];
                        if(!yw[list.equipmentName]){yw[list.equipmentName]=[]}
                        yw[list.equipmentName].push(list.displace);
                        if(!yy[list.equipmentName]){yy[list.equipmentName]=[]}
                        yy[list.equipmentName].push(list.kpa);
                    }
                }
                //制作图表
                var color= ['#fd9173', '#f36f8a', '#5f71d2', '#42a4eb', '#4ac7f5','rgb(164, 216, 194)','rgb(243, 217, 153)'];
                var num =0;
                //位移
                var ywObj={
                    id:"displace",
                    title:"位移趋势图",
                    nameY:"mm",
                    dataX:x,
                    legend:[],
                    series:[]
                };
                for(var w in yw){
                    ywObj.legend.push(w);
                    ywObj.series.push({
                        name:w,
                        type:'line',
                        smooth: true,
                        itemStyle: {
                            normal: {
                                color: color[num]
                            }
                        },
                        data: yw[w]
                    });
                    num++;
                }
                _self.echarts(ywObj);
                //压力
                num=0;
                var yyObj={
                    id:"kpa",
                    title:"压力趋势图",
                    nameY:"KN",
                    dataX:x,
                    legend:[],
                    series:[]
                };
                for(var y in yy){
                    yyObj.legend.push(y);
                    yyObj.series.push({
                        name:y,
                        type:'line',
                        smooth: true,
                        itemStyle: {
                            normal: {
                                color: color[num]
                            }
                        },
                        data: yy[y]
                    });
                    num++;
                }
                _self.echarts(yyObj);
            }
        })
    }
}