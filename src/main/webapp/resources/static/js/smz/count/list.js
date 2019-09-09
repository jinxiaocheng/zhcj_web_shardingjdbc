var count={
    getData:function () {
        biyue.ajax({
            url:'/thridPerson/getCountData',
            data:{
                type:1,
                startTime:$("#startDate").val(),
                endTime:$("#endDate").val()
            },
            fun:function (data) {
                var dataV =data.value;
                if(dataV){
                    var $listpx="",$listks="";
                    if(dataV.length>0){
                        for(var j in dataV){
                            $listpx+='<tr><td>'+dataV[j].totalPersonNow+'</td>\n' +
                                '                <td>'+dataV[j].totalPersonTrain+'</td>\n' +
                                '                <td>'+dataV[j].totalTrain+'</td>\n' +
                                '                <td>'+dataV[j].avgPersonTrain+'</td>\n' +
                                '                <td>'+dataV[j].avgPersonTrainHour+'</td></tr>';
                            $listks+='<tr><td>'+dataV[j].totalPersonExam+'</td>\n' +
                                '                <td>'+dataV[j].totalExamPass+'</td>\n' +
                                '                <td>'+dataV[j].examPassPercent+'%</td></tr>';
                        }
                    }else {
                        $listpx='<tr>\n' +
                            '                    <td colspan="5">\n' +
                            '                        <p style="margin: 0;text-align: center;color:#a6a6a6;letter-spacing: 2px">暂无数据</p>\n' +
                            '                    </td>\n' +
                            '                </tr>';
                        $listks='<tr>\n' +
                            '                    <td colspan="3">\n' +
                            '                        <p style="margin: 0;text-align: center;color:#a6a6a6;letter-spacing: 2px">暂无数据</p>\n' +
                            '                    </td>\n' +
                            '                </tr>';
                    }
                    $(".pxData tbody").html($listpx);
                    $(".ksData tbody").html($listks);
                }
            }
        })
        biyue.ajax({
            url:'/thridPerson/getCountData',
            data:{
                type:2,
                startTime:$("#startDate").val(),
                endTime:$("#endDate").val()
            },
            fun:function (data) {
                var dataV =data.value;
                if(dataV){
                    var $list="";
                    if(dataV.length>0){
                        for(var j in dataV){
                            $list+='<tr><th scope="row">'+(parseFloat(j)+1)+'</th>\n' +
                                '                <td>'+dataV[j].departName+'</td>\n' +
                                '                <td>'+dataV[j].totalPersonNow+'</td>\n' +
                                '                <td>'+dataV[j].totalPersonHistory+'</td>\n' +
                                '                <td>'+dataV[j].totalPersonTrain+'</td>\n' +
                                '                <td>'+dataV[j].trainPercent+'%</td>\n' +
                                '                <td>'+dataV[j].totalTrain+'</td>\n' +
                                '                <td>'+dataV[j].totalTrainHour+'</td>\n' +
                                '                <td>'+dataV[j].avgPersonTrainHour+'</td>\n' +
                                '                <td>'+dataV[j].totalPersonExam+'</td>\n' +
                                '                <td>'+dataV[j].totalExamPass+'</td>\n' +
                                '                <td>'+dataV[j].examPassPercent+'%</td></tr>'
                        }
                    }else {
                        $list='<tr>\n' +
                            '                    <td colspan="12">\n' +
                            '                        <p style="margin: 0;text-align: center;color:#a6a6a6;letter-spacing: 2px">暂无数据</p>\n' +
                            '                    </td>\n' +
                            '                </tr>';
                    }
                    $(".allData tbody").html($list);
                }
            }
        })
    },
    default:function () {
        var _self = this;
        $("#search_link").click(function () {
            _self.getData();
        });
        $("#search_link_echarts").click(function () {
            _self.getEcharts();
        });
    },
    getUnit:function () {
        var _self = this;
        biyue.ajax({
            url:'/thridPerson/getDepart',
            fun:function (data) {
                var dataV=data.value,$list="";
                for(var i in dataV){
                    if(dataV[i].id){
                        $list+='<li><a data-value="'+dataV[i].id+'">'+dataV[i].name+'</a></li>';
                    }else{
                        $("#unit").html(dataV[i].name).attr("data-value",dataV[i].id);
                        $list='<li><a data-value="'+dataV[i].id+'">'+dataV[i].name+'</a></li>'+$list;
                    }
                }
                $(".unit ul").html($list);

                _self.getEcharts();
            }
        })
    },
    createEcharts:function (obj) {
        var option="";
        var color= ['#fd9173', '#f36f8a', '#5f71d2', '#42a4eb', '#4ac7f5','rgb(164, 216, 194)','rgb(243, 217, 153)'];
        if(obj.type==="pie"){
            option = {
                title:{
                    top: '10px',
                    text:obj.title,
                    x:'center'
                },
                backgroundColor: '#fff',
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                color: ['#fd9173', '#f36f8a', '#5f71d2', '#42a4eb', '#4ac7f5','rgb(164, 216, 194)','rgb(243, 217, 153)'],
                legend: { //图例组件，颜色和名字
                    left: '10px',
                    top: '30px',
                    orient: 'vertical',
                    itemGap: 12, //图例每项之间的间隔
                    itemWidth: 10,
                    itemHeight: 10,
                    icon: 'rect',
                    data:obj.legend,
                    textStyle: {
                        color: [],
                        fontStyle: 'normal',
                        fontFamily: '微软雅黑',
                        fontSize: 12
                    }
                },
                series: [{
                    name: obj.title,
                    type: 'pie',
                    clockwise: false, //饼图的扇区是否是顺时针排布
                    minAngle: 10, //最小的扇区角度（0 ~ 360）
                    center: ['50%', '50%'], //饼图的中心（圆心）坐标
                    radius: [74, 126], //饼图的半径
                    avoidLabelOverlap: true, ////是否启用防止标签重叠
                    itemStyle: { //图形样式
                        normal: {
                            borderColor: '#fff',
                            borderWidth: 1.5
                        }
                    },
                    "label": {
                        "normal": {
                            "show": true,
                            "formatter": "{b} : {c}人 ({d}%)"
                        },
                        "emphasis": {
                            "show": true
                        }
                    },

                    data: obj.data
                }, {
                    name: '',
                    type: 'pie',
                    clockwise: false,
                    silent: true,
                    minAngle:10, //最小的扇区角度（0 ~ 360）
                    center: ['50%', '50%'], //饼图的中心（圆心）坐标
                    radius: [0, 66], //饼图的半径
                    itemStyle: { //图形样式
                        normal: {
                            borderColor: '#fff',
                            borderWidth: 1.5,
                            opacity: 0.3
                        }
                    },
                    label: { //标签的位置
                        normal: {
                            show: false
                        }
                    },
                    data: obj.data
                }]
            };
        }else{
            option = {
                title:{
                    top: '10px',
                    text:obj.title,
                    x:'center'
                },
                backgroundColor: '#fff',
                tooltip: {
                    trigger: 'axis'
                },
                grid: {
                    left:'50px',
                    right:'50px',
                    bottom: '50px'
                },
                legend: {
                    left: 'center',
                    bottom: '6px',
                    itemGap: 12, //图例每项之间的间隔
                    itemWidth: 10,
                    itemHeight: 10,
                    icon: 'rect',
                    data:obj.legend,
                    textStyle: {
                        color: [],
                        fontStyle: 'normal',
                        fontFamily: '微软雅黑',
                        fontSize: 12
                    }
                },
                calculable: true,
                xAxis: [{
                    type: 'category',
                    data:  obj.x,
                    axisLine: {
                        lineStyle: {
                            color: "#959595"
                        }
                    }
                }],
                yAxis: [{
                    name:obj.unit,
                    type: 'value',
                    minInterval: 1,
                    axisLabel: {
                        formatter: '{value}'
                    },
                    axisLine: {
                        lineStyle: {
                            color: "#959595"
                        }
                    }
                }],
                series:obj.series
            };
        }


        var myChart = echarts.init(document.getElementById('mainPie'));

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        var indexR = "";
        window.onresize=function () {
            indexR&&clearTimeout(indexR);
            indexR = setTimeout(function () {
                myChart.resize();
            },200)
        }
    },
    getEcharts:function () {
        var _self =this;
        biyue.ajax({
            url:'/thridPerson/getChartResult',
            data:{
                departId:$("#unit").attr("data-value"),
                value:$("#target").attr("data-value"),
                startTime:$("#startTime").val(),
                endTime:$("#endTime").val()
            },
            fun:function (data) {
                var obj={},dataV=data.value;
                var type = $("#target").attr("data-value");
                obj.title=$("#target").html();
                obj.data=[];
                obj.legend=[];
                if(type==='0'){
                    //扇形图
                    obj.type="pie";
                    for(var i=0; i<dataV.length;i++){
                        obj.data[i]={
                            value:dataV[i].qty.split(":")[1],
                            name:dataV[i].qty.split(":")[0]
                        };
                        obj.legend[i]=dataV[i].qty.split(":")[0];
                    }
                    _self.createEcharts(obj);
                }else if(type==='1'){
                    //培训人数
                    obj.type="bar";
                    obj.legend=['员工人数','培训人数'];
                    obj.unit="人";
                    obj.x=[];
                    obj.dataT=[];
                    for(var i=0; i<dataV.length;i++){
                        obj.x[i]=dataV[i].name;
                        obj.data[i]=dataV[i].qty[0];
                        obj.dataT[i]=dataV[i].qty[1];
                    }
                    obj.series=[ {
                        name: '员工人数',
                        type: 'bar',
                        barMaxWidth:40,
                        itemStyle: {
                            normal: {
                                color: "#4ac7f5",
                                label: {
                                    show: true,
                                    position: 'top',
                                    textStyle: {
                                        fontSize: 14
                                    }
                                }
                            }
                        },
                        data: obj.data
                    },{
                        name: '培训人数',
                        type: 'bar',
                        barMaxWidth:40,
                        itemStyle: {
                            normal: {
                                color: "#ff8000",
                                label: {
                                    show: true,
                                    position: 'top',
                                    textStyle: {
                                        fontSize: 14
                                    }
                                }
                            }
                        },
                        data: obj.dataT
                    }]
                    _self.createEcharts(obj);
                }else{
                    //柱状图
                    obj.type="bar";
                    obj.legend=[obj.title];
                    obj.unit=judge(obj.title);
                    obj.x=[];
                    for(var i=0; i<dataV.length;i++){
                        obj.x[i]=dataV[i].name;
                        obj.data[i]=dataV[i].qty;
                    }
                    obj.series=[ {
                        name: obj.title,
                        type: 'bar',
                        barMaxWidth:40,
                        itemStyle: {
                            normal: {
                                color: "#4ac7f5",
                                label: {
                                    show: true,
                                    position: 'top',
                                    textStyle: {
                                        fontSize: 14
                                    }
                                }
                            }
                        },
                        data: obj.data
                    }]
                    _self.createEcharts(obj);
                }
            }
        });

        //图表单位判断
        function judge(val){
            if(/人数/.test(val)){
                return '人'
            }else if(/学时/.test(val)){
                return '小时'
            }else if(/次/.test(val)){
                return '次'
            }else if(/率/.test(val)){
                return '%'
            }
        }
    }
}