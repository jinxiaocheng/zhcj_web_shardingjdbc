//饼图
function createPie(id, title,data){
         //基于准备好的dom，初始化echarts实例
        var myChartPie= echarts.init(document.getElementById(id));
        var optionPie = {
            title : {
                text: title,
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data:data,
                y:'30'
            },
            series : [
                {

                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:data,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
    // 使用刚指定的配置项和数据显示图表。
        myChartPie.setOption(optionPie);
    }

//柱状图
function createBar(id, title,data){
    //基于准备好的dom，初始化echarts实例
    var myChartBar = echarts.init(document.getElementById(id));
    // 指定图表的配置项和数据
    var optionBar = {
        title: {
            text: '工程进度图',
            x:'center'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            y:"30",
            data: ['2011年']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            boundaryGap: [0,0.01]
        },
        yAxis: {
            type: 'category',
            data: ['一阶段','二阶段','三阶段','四阶段','五阶段','六阶段']
        },
        series: [
            {
                name: '2011年',
                type: 'bar',
                data: [18203, 23489, 29034, 104970, 131744, 630230]
            }
        ]

    };
    // 使用刚指定的配置项和数据显示图表。
    myChartBar.setOption(optionBar);

}
//线形图
function createLine() {
    //基于准备好的dom，初始化echarts实例
    var myChartLine= echarts.init(document.getElementById('test'));
    var optionLine = {
        title: {
            text: '环境监测',
            x:'center'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['PM2.5','噪音','温度','湿度'],
            y:'30'
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                dataView: {readOnly: false},
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['8:00','9:00','10:00','11:00','12:00','13:00','14:00']
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'PM2.5',
                type:'line',
                stack: '总量',
                data:[120, 132, 101, 134, 90, 230, 210]
            },
            {
                name:'噪音',
                type:'line',
                stack: '总量',
                data:[220, 182, 191, 234, 290, 330, 310]
            },
            {
                name:'温度',
                type:'line',
                stack: '总量',
                data:[150, 232, 201, 154, 190, 330, 410]
            },
            {
                name:'湿度',
                type:'line',
                stack: '总量',
                data:[320, 332, 301, 334, 390, 330, 320]
            }
        ]
    };
// 使用刚指定的配置项和数据显示图表。
    myChartLine.setOption(optionLine);
}
