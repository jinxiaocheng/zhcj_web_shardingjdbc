define(function (require, exports, module) {
    "require:nomunge,exports:nomunge,module:nomunge";
    require('Comm/List.min');

    TXTime.PDWeb.Main = {
        initZhu: function (dataCharts) {

            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            // 指定图表的配置项和数据

            var data = [];
            var labelData = [];
            for (var i = 0; i < 100; ++i) {
                data.push({
                    value: Math.random() * 10 + 10 - Math.abs(i - 12),
                    name: i + ':00'
                });
                labelData.push({
                    value: 1,
                    name: i ,
                    itemStyle:{
                        normal: {
                            color: "#ddd"
                        }
                    }
                });
            }
            for(var i=0;i<labelData.length;++i){
                if(labelData[i].name < dataCharts.val.toFixed(2)){
                    labelData[i].itemStyle = {
                        normal: {
                            color: "#4ac7f5"
                        }
                    }
                }
            }

            var option = {
                backgroundColor:'#ffffff',
                title: {
                    text:  dataCharts.val.toFixed(2)+'%',
                    left: '50%',
                    textAlign: 'center',
                    top: '53%',
                    textStyle:{
                        color: '#4ac7f5',
                        fontSize:24
                    }
                },
                color: ['#22C3AA'],
                series: [{
                    hoverAnimation:false,
                    type: 'pie',
                    data: labelData,
                    center: ['50%', '58%'],
                    radius: ['58%', '80%'],
                    zlevel: -2,
                    itemStyle: {
                        normal: {
                            borderWidth:2,
                            borderColor: 'white'
                        }
                    },
                    label: {
                        normal: {
                            position: 'inside',
                            show:false,
                        }
                    }
                }]
            };


            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        },
        initPie: function (data) {

            var data = [
                { value: data.yyy, name: '摇一摇' },
                { value: data.ysb, name: '钥匙包' },
                { value: data.hj, name: '呼叫' },
                { value: data.mm, name: '密码' },
                { value: data.ic, name: 'IC卡' },
                { value: data.ytj, name: '蓝牙一体机' },
                { value: data.snj, name: '室内机' }
            ];

            var option = {
                backgroundColor: '#fff',
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                color: ['#fd9173', '#f36f8a', '#5f71d2', '#42a4eb', '#4ac7f5','rgb(164, 216, 194)','rgb(243, 217, 153)'],
                legend: { //图例组件，颜色和名字
                    left: '0',
                    top: '0',
                    orient: 'vertical',
                    itemGap: 12, //图例每项之间的间隔
                    itemWidth: 10,
                    itemHeight: 10,
                    icon: 'rect',
                    data:['摇一摇', '钥匙包', '呼叫', '密码', 'IC卡','蓝牙一体机' ,'室内机'],
                    textStyle: {
                        color: [],
                        fontStyle: 'normal',
                        fontFamily: '微软雅黑',
                        fontSize: 12,
                    }
                },
                series: [{
                    name: '日各开门方式占比',
                    type: 'pie',
                    clockwise: false, //饼图的扇区是否是顺时针排布
                    minAngle: 10, //最小的扇区角度（0 ~ 360）
                    center: ['50%', '50%'], //饼图的中心（圆心）坐标
                    radius: [54, 96], //饼图的半径
                    avoidLabelOverlap: true, ////是否启用防止标签重叠
                    itemStyle: { //图形样式
                        normal: {
                            borderColor: '#fff',
                            borderWidth: 1.5,
                        },
                    },
                    "label": {
                        "normal": {
                            "show": true,
                            "formatter": "{b} : {c}次 ({d}%)"
                        },
                        "emphasis": {
                            "show": true
                        }
                    },

                    data: data
                }, {
                    name: '',
                    type: 'pie',
                    clockwise: false,
                    silent: true,
                    minAngle:10, //最小的扇区角度（0 ~ 360）
                    center: ['50%', '50%'], //饼图的中心（圆心）坐标
                    radius: [0, 49], //饼图的半径
                    itemStyle: { //图形样式
                        normal: {
                            borderColor: '#fff',
                            borderWidth: 1.5,
                            opacity: 0.3,
                        }
                    },
                    label: { //标签的位置
                        normal: {
                            show: false,
                        }
                    },
                    data: data
                }]
            };
            var myChart = echarts.init(document.getElementById('mainPie'));

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);

        },
        initHuan: function (data) {
            var dataAll= data.total+data.num;
            var dataTotal=(data.total/dataAll).toFixed(2);
            var dataNum=(data.num/dataAll).toFixed(2);
            var dataB=(data.num/data.total).toFixed(2);
            var placeHolderStyle = {
                normal: {
                    label: {
                        show: false,
                        position: "center"
                    },
                    labelLine: {
                        show: false
                    },
                    color: "#dedede",
                    borderColor: "#dedede",
                    borderWidth: 0
                },
                emphasis: {
                    color: "#dedede",
                    borderColor: "#dedede",
                    borderWidth: 0
                }
            };
            var option = {
                backgroundColor: '#fff',
                color: ['#FF8C00', '#ffcd26', '#ffcd26', '#fff', "#48D1CC"],
                legend: [{
                    orient: '',
                    icon: 'circle',
                    left: '0',
                    top: '0',
                    data: ['总人数', '总开门次数']
                }],
                series: [
                    {
                        name: '值',
                        type: 'pie',
                        clockWise: true, //顺时加载
                        hoverAnimation: false, //鼠标移入变大
                        radius: [119, 120],
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    textStyle: {
                                        fontSize: 16
                                    },
                                    position: 'outside'
                                },
                                labelLine: {
                                    show: true,
                                    length: 20,
                                    smooth: 0.5
                                },
                                borderWidth: 8,
                                shadowBlur: 40,
                                borderColor: "#FF8C00",
                                shadowColor: 'rgba(0, 0, 0, 0)' //边框阴影
                            }
                        },
                        //总人数
                        data: [{
                            value: dataTotal,
                            name: '总人数：'+data.total+'人'
                        }, {
                            value: 1-dataTotal,
                            name: '',
                            itemStyle: placeHolderStyle
                        }]
                    },
                    {
                        name: '值',
                        type: 'pie',
                        clockWise: true,
                        hoverAnimation: false,
                        radius: [89, 90],
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    textStyle: {
                                        fontSize: 16
                                    },
                                    position: 'outside'
                                },
                                labelLine: {
                                    show: true,
                                    length: 35,
                                    smooth: 0.5
                                },
                                borderWidth: 8,
                                shadowBlur: 40,
                                borderColor: "#ffcd26",
                                shadowColor: 'rgba(0, 0, 0, 0)' //边框阴影
                            }
                        },
                        //得分率数据修改
                        data: [{
                            value: dataNum,
                            name: '总开门次数：'+data.num+'次'
                        }, {
                            value: 1-dataNum,
                            name: '',
                            itemStyle: placeHolderStyle
                        }]
                    },

                    {
                        type: 'pie',
                        color: ['#FF8C00', '#ffcd26', "#ffcd26"],
                        data: [ {
                            value: '',
                            name: '总开门次数'
                        }, {
                            value: '',
                            name: '总人数'
                        }]
                    }, {
                        name: '白',
                        type: 'pie',
                        clockWise: true,
                        hoverAnimation: false,
                        radius: [100, 100],
                        label: {
                            normal: {
                                position: 'center'
                            }
                        },
                        data: [{
                            value: 1,
                            label: {
                                normal: {
                                    formatter: '人均日开门次数',
                                    textStyle: {
                                        color: '#666666',
                                        fontSize: 18
                                    }
                                }
                            }
                        }, {
                            tooltip: {
                                show: false
                            },
                            label: {
                                normal: {
                                    formatter: '\n'+dataB+'次/人',
                                    textStyle: {
                                        color: '#666666',
                                        fontSize: 18
                                    }
                                }
                            }
                        }]
                    }]
            };


            var myChart = echarts.init(document.getElementById('mainHuan'));

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);

        },
        initZhu2: function (data) {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('mainZhu2'));

            // 指定图表的配置项和数据

            var option = {
                title: {
                    text: ''
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '15%',
                    containLabel: true
                },

                xAxis: [
                    {
                        type : 'category',
                        position: 'bottom',
                        boundaryGap: true,

                        axisLabel : {
                            show:true,
                            interval: 0,
                            rotate: 60
                        },

                        //type: 'category',
                        data: ['00:00-00:59', '01:00-01:59', '02:00-02:59', '03:00-03:59', '04:00-04:59', '05:00-05:59', '06:00-06:59', '07:00-07:59', '08:00-08:59', '09:00-09:59', '10:00-10:59', '11:00-11:59', '12:00-12:59', '13:00-13:59', '14:00-14:59', '15:00-15:59', '16:00-16:59', '17:00-17:59', '18:00-18:59', '19:00-19:59', '20:00-20:59', '21:00-21:59', '22:00-22:59', '23:00-23:59'],
                        axisTick: {
                            alignWithLabel: true
                        }
                    }
                ],
                yAxis: [
                    {
                        name: '开门次数：次',
                        type: 'value'

                    }
                ],
                series: [
                    {
                        name:'时段开门次数',
                        type:'line',
                        itemStyle :{
                            normal :{
                                color:'#02b5d5'
                            }
                        },
                        data: [data.d0, data.d1, data.d2, data.d3, data.d4, data.d5, data.d6, data.d7, data.d8, data.d9, data.d10, data.d11, data.d12, data.d13, data.d14, data.d15, data.d16, data.d17, data.d18, data.d19, data.d20, data.d21, data.d22, data.d23],
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值',
                                    itemStyle :{
                                        normal :{
                                            color:'#d90051',
                                        }
                                    }
                                },
                                {type: 'min', name: '最小值',
                                    itemStyle :{
                                        normal :{
                                            color:'#01bc8f'
                                        }
                                    }
                                }
                            ]
                        },
                        label: { //设置点数据的提示
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    }
                ]
            };


            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);

        },
        init: function () {

            TXTime.getJSON({
                url: "/User/Do/GetPDStatistics",
                data: null,
                beforeSend: function () {
                    layer.msg(TXTime.CommonConst.dataLoading, { icon: 16, time: 0 });
                },
                loadObj: "#ListTbody",
                success: function (data) {
                    $('#sq').html(data.all.sq);
                    $('#yzAndsq').html(data.all.yzAndsq);
                    $('#wyzAndsq').html(data.all.wyzAndsq);
                    $('#yzAndwsq').html(data.all.yzAndwsq);
                    $('#cardNum').html(data.all.cardNum);

                    $('#tsq').html(data.today.sq +' 人');
                    $('#tyzAndsq').html(data.today.yzAndsq + ' 人');
                    $('#twyzAndsq').html(data.today.wyzAndsq + ' 人');
                    $('#tyzAndwsq').html(data.today.yzAndwsq + ' 人');
                    $('#openNum').html(data.today.openNum + ' 次');
                    $('#tcardNum').html(data.today.cardNum+' 张');



                    TXTime.PDWeb.Main.initZhu(data.rhy);
                    TXTime.PDWeb.Main.initPie(data.opendoor);
                    TXTime.PDWeb.Main.initHuan(data.opendoorpeople);
                    TXTime.PDWeb.Main.initZhu2(data.dayhouropen);
                },
                complete: function () {
                    layer.closeAll();
                }
            });
        }

    };

    module.exports = TXTime.PDWeb.Main;
});