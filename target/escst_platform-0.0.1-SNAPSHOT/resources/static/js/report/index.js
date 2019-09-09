//总劳动力统计（柱状图）
var chart_1=echarts.init(document.getElementById("workTypeChart"));
chart_1_option = {
    title : {
        text: '总劳动力统计',
    },
    tooltip : {
        trigger: 'axis'
    },
   /* legend: {
        data:['人天']
    },*/
    toolbox: {
        show : true,
        feature: {
            dataZoom: {},
            dataView: {readOnly: false},
            magicType: {type: ['line', 'bar']},
            restore: {},
            saveAsImage: {}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
            axisLabel:{
                rotate:-60,
                interval:0
            }
        }
    ],
    yAxis : [
        {
            type : 'value',
            minInterval: 1
        }
    ],
    series : [
        {
            name:'人天',
            type:'bar',
            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
            itemStyle: {
                normal: {
                    color: function(params) {
                        // build a color map as your need.
                        var colorList = [
                            '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                            '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                            '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
                        ];
                        return colorList[params.dataIndex]
                    }
                }
            }
        }
    ]
};


// 质量检查数、整改单月趋势图（线状图）
var chart_2=echarts.init(document.getElementById("qualityInspectionChart"));
chart_2_option = {
    title: {
        text: '质量检查数、整改单月趋势图',
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['质量检查数','质量整改数']
    },
    toolbox: {
        show: true,
        feature: {
            dataZoom: {},
            dataView: {readOnly: false},
            magicType: {type: ['line', 'bar']},
            restore: {},
            saveAsImage: {}
        }
    },
    xAxis: [{
        type: 'category',
        boundaryGap: false,
        data: ['周一','周二','周三','周四','周五','周六','周日']
    }],
    yAxis: {
        type: 'value',
        axisLabel: {
            formatter: '{value}'
        },
        minInterval: 1
    },
    series: [
        {
            name:'质量检查数',
            type:'line',
            data:[11, 11, 15, 13, 12, 13, 10],
            markPoint: {
                data: [
                    {type: 'max', name: '最大值'},
                    {type: 'min', name: '最小值'}
                ]
            },
        },
        {
            name:'质量整改数',
            type:'line',
            data:[1, -2, 2, 5, 3, 2, 0],
            markPoint: {
                data: [
                    {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
                ]
            },
        }
    ]
};

// 安全检查数、整改单月趋势图（线状图）
var chart_3=echarts.init(document.getElementById("safetyInspectionChart"));
chart_3_option = {
    title: {
        text: '安全检查数、整改单月趋势图',
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['安全检查数','安全整改数']
    },
    toolbox: {
        show: true,
        feature: {
            dataZoom: {},
            dataView: {readOnly: false},
            magicType: {type: ['line', 'bar']},
            restore: {},
            saveAsImage: {}
        }
    },
    xAxis: [{
        type: 'category',
        boundaryGap: false,
        data: ['周一','周二','周三','周四','周五','周六','周日']
    }],
    yAxis: {
        type: 'value',
        axisLabel: {
            formatter: '{value}'
        },
        minInterval: 1
    },
    series: [
        {
            name:'安全检查数',
            type:'line',
            data:[11, 11, 15, 13, 12, 13, 10],
            markPoint: {
                data: [
                    {type: 'max', name: '最大值'},
                    {type: 'min', name: '最小值'}
                ]
            },
        },
        {
            name:'安全整改数',
            type:'line',
            data:[1, -2, 2, 5, 3, 2, 0],
            markPoint: {
                data: [
                    {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
                ]
            },
        }
    ]
};
//出勤统计（线状图）
var chart_4=echarts.init(document.getElementById("areaConstructionChart"));
chart_4_option = {
    title: {
        text: '出勤统计',
    },
    tooltip: {
        trigger: 'axis'
    },
    grid:{
        top:110
    },
    legend: {
        data:[''],
        top:30
    },
    toolbox: {
        show: true,
        feature: {
            dataZoom: {},
            dataView: {readOnly: false},
            magicType: {type: ['line', 'bar']},
            restore: {},
            saveAsImage: {}
        }
    },
    xAxis: [{
        type: 'category',
        boundaryGap: false,
        data: ['周一','周二','周三','周四','周五','周六','周日']
    }],
    yAxis: {
        type: 'value',
        axisLabel: {
            formatter: '{value}'
        },
        minInterval: 1
    },
    series: [
        {
            name:'',
            type:'line',
            data:[0, 0, 0, 0, 0, 0, 0],
            markPoint: {
                data: [
                    {type: 'max', name: '最大值'},
                    {type: 'min', name: '最小值'}
                ]
            }
        }
    ]
};

window.onresize =winResize;
function winResize(){
    if(document.body.clientWidth>1500){
    /*    chart_1_option.legend.top=0;
        chart_1.setOption(chart_1_option);*/
        chart_2_option.legend.top=0;
        chart_2.setOption(chart_2_option);
        chart_3_option.legend.top=0;
        chart_3.setOption(chart_3_option);
/*        chart_4_option.legend.top=0;
        chart_4.setOption(chart_4_option);*/
    }else{
/*        chart_1_option.legend.top=25;
        chart_1.setOption(chart_1_option);*/
        chart_2_option.legend.top=25;
        chart_2.setOption(chart_2_option);
        chart_3_option.legend.top=25;
        chart_3.setOption(chart_3_option);
/*        chart_4_option.legend.top=25;
        chart_4.setOption(chart_4_option);*/
    }
    chart_1.resize();
    chart_2.resize();
    chart_3.resize();
    chart_4.resize();
}