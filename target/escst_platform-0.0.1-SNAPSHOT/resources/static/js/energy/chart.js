//变量
  var my_angel={
      chart_1:"",
      chart_1_option:""
  }
// 自适应
$(window).resize(function(){
    mainPart();//主体部分高度
    my_angel.chart_1.resize();
})

/*主体部分高度*/
function mainPart() {
    var _windowHeight = $(window).height();
    $(".mainPart").css("height",_windowHeight-30);
}mainPart();
//图表
function mycharts(){
    my_angel.chart_1=echarts.init(document.getElementById("chart"));
    my_angel.chart_1_option =  {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                animation: false
            }
        },
        legend: {
            data:[],
            right:'2.4%',
            selected:{
                '汇总':false
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            containLabel: true
        },
        axisPointer: {
            link: {xAxisIndex: 'all'}
        },
        dataZoom: [
            {
                show: true,
                realtime: true,
                start:0,
                end: 100,
            }
        ],
        xAxis:  {
                type: 'category',

            data: [0]
        },
        yAxis: {
            name:"kwh",
            type: 'value',
            axisLabel: {
                formatter: '{value}'
            }
        },
        series: []
    };

}
mycharts();

//选择出发事件
function eConsole(param){
    if(param.name!=="汇总"){
        my_angel.chart_1._api.dispatchAction({
            type: 'legendUnSelect',
            // 图例名称
            name: '汇总'
        })
    }else{
        for(var i in param.selected){
            if(i!=="汇总"){
                my_angel.chart_1._api.dispatchAction({
                    type: 'legendUnSelect',
                    // 图例名称
                    name: i
                })
            }
        }

    }
}

//加载事件
function startLoad(){
}
function endLoad() {

}



/*扩展*/
$.fn.extend({
    /*下拉框*/
    Bsselect: function () {
        var obj = this, Selectval = obj.find(".dropdown-text"), Selectoption = obj.find(".dropdown-menu");
        Selectoption.find("li").click(function () {
            var option = $(this).text();
            var dataValue = $(this).children("a").attr("data-value");
            Selectval.text(option);
            Selectval.attr("data-value",dataValue);
        });
    }
});
//下拉选择
$(".queryCondition").each(function () {
    $(this).Bsselect();
});

function my_ajax(equipmentType,type){
    var my_json=JSON.stringify({
        equipmentType:equipmentType,
        type:type,
        constructionId:constructionId
    });
    var color=["#69dbee","#7434ad","#da9a22","#1d81e6","#45ee63","#eeda5c","#ee62d8"]
    $.ajax({
        type: "post",
        url: ctx + "/energyMonitor/getEnergy",
        data: my_json,
        contentType : "application/json",
        dataType: "json",
        success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
            if(data.status == 1){
                var my_legend=["汇总"],my_xdata=data.value.xdata,my_ydata=[];
                my_ydata[0]=data.value.count;
                my_angel.chart_1_option.series=[];
                my_angel.chart_1_option.series[0]={
                    name:'汇总',
                    type:'line',
                    data:data.value.count,
                    lineStyle:{normal:{'color':color[0]}},
                    itemStyle:{normal:{'color':color[0],label:{show:true}}}
                }
                for(var i in data.value.ydata){
                    my_legend.push(data.value.ydata[i].name);
                    my_angel.chart_1_option.series[parseFloat(i)+1]={
                        name:data.value.ydata[i].name,
                        type:'line',
                        data:data.value.ydata[i].data,
                        lineStyle:{normal:{'color':color[parseFloat(i)+1]}},
                        itemStyle:{normal:{'color':color[parseFloat(i)+1],label:{show:true}}}
                    }
                }

                if(data.value.ydata.length==0){
                    var nuData=[];
                    nuData[my_xdata.length-1]=0;
                    my_angel.chart_1_option.series[0]={
                        type:'line',
                        data:nuData,
                        lineStyle:{normal:{'color':'#fff'}},
                        itemStyle:{normal:{'color':'#fff'}}
                    }
                }
                if(equipmentType==1){
                    my_angel.chart_1_option.yAxis.name="kwh";
                }else if(equipmentType==2){
                    my_angel.chart_1_option.yAxis.name="m³";
                }
                my_angel.chart_1_option.legend.data=my_legend;
                my_angel.chart_1_option.xAxis.data=my_xdata;
                my_angel.chart_1=echarts.init(document.getElementById("chart"));
                my_angel.chart_1.setOption( my_angel.chart_1_option);
                my_angel.chart_1.on("legendselectchanged", eConsole);
                my_angel.chart_1.showLoading=startLoad();
                my_angel.chart_1.hideLoading=endLoad();
            } else {

            }
        },
        error: function (data, status, e) {
            console.error('请求错误:'+e);
        }
    });
}