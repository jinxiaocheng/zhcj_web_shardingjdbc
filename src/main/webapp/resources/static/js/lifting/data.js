var biyueList = {
    hook: null,
    hookChange: null,
    myChart: null,
    //初始化
    init: function () {
        var _self = this;
        $("#search").click(function () {
            _self.getNowData();
            _self.getCharts();
            searchIf();
        });
        //生成吊钩图片
        _self.hook = new towerAnimate("#hookCanvas");
        _self.hookChange = _self.hook.init();
        //更多数据
        $(".moreData").click(function () {
            var hookId=$("[name=\"hook\"]").val();
            if(hookId){
                biyue.open({
                    title:'历史数据',
                    url:'/route/lifting/dataHistory?id='+hookId
                })
            }else{
                layer.msg('请选择一个设备!', {time: 2000, icon: 0});
            }
        })
    },
    //获取实时数据
    getNowData: function () {
        var _self = this;
        biyue.ajax({
            url: '/hookData/getRealTimeData',
            data: {
                hookId: $("[name=\"hook\"]").val()
            },
            fun: function (data) {
                var $list = ""
                    , dataV = data.value
                    , hook = new towerAnimate("#hookCanvas")
                    , $list = '';
                if (dataV) {
                    $list = '<h2>设备模型<small>(' + dataV.hookName+')</small></h2>';
                    $(".hookImg .layui-card-header").html($list);
                    _self.hookChange(dataV.extent, dataV.height);
                    $(".nowData .nd-time label").html(dataV.acquisitionTime);
                    $(".nowData .nd-extent label").html(dataV.extent);
                    $(".nowData .nd-height label").html(dataV.height);
                }
            }
        })
    },
    //得到设备列表
    getHook: function (id, fun) {
        var form = layui.form;
        //获取合作方
        biyue.ajax({
            url: '/hookData/listHook',
            data: {
                constructionId: id
            },
            fun: function (data) {
                var $list = ""
                    , dataV = data.value;

                for (var i = 0; i < dataV.length; i++) {
                    $list += '<option value="' + dataV[i].hookId + '">' + dataV[i].hookName + '</option>';
                }

                $("[name='hook']").html($list);
                form.render('select');
                // form.on('select(hook)', function(data){
                //     fun&&fun(data);
                // });
            }
        })
    },
    //创建数据表格
    createTable: function () {
        var _self = this, table = biyue.tableList();
        var date = new Date();
        var endTime = biyue.timeText(date) + '23:59:59';
        var startTime = biyue.timeText(new Date(date.setDate(date.getDate()-7))) + '00:00:00';
        table.loadTable({
            url: '/hookData/listHistoryData'
            , upData: {
                startTime:startTime,
                endTime:endTime,
                hookId: $("[name=\"hook\"]").val()
            }
            , cols: [[
                {type: 'numbers', fixed: 'left'}
                , {field: 'acquisitionTime', title: '采集时间'}
                , {field: 'extent', title: '幅度(m)'}
                , {field: 'height', title: '高度(m)'}
            ]]
            , height: '394px'
            , limit: 8
        });
        window.searchIf = function () {
            table.search({
                where: {
                    hookId: $("[name=\"hook\"]").val()
                }
            })
        };

    },
    //创建趋势图
    createCharts: function () {
        var _self = this;
        _self.myChart = echarts.init(document.getElementById("chartsBox"), 'biyue');
        var xdata = [],ydata = [];
        for (var i=0; i < 24; i++) {
            var l = i < 10 ? '0' + i : i;
            xdata.push(l + ':00');
            ydata.push(0);
        }
        var option = {
            tooltip: {
                trigger: "axis"
            },
            grid: {
                left: '50px',
                right: '50px',
                bottom: '25px',
                top: '30px'
            },
            legend: {
                data: ["幅度", "高度"]
            },
            xAxis: [{
                type: "category",
                boundaryGap: !1,
                data: xdata
            }],
            yAxis: [{
                type: "value",
                name: "m",
                axisLine: {
                    lineStyle: {
                        color: "#23bfb6"
                    }
                }
            }],
            series: [{
                name: "幅度",
                type: "line",
                smooth: !0,
                itemStyle: {
                    normal: {
                        areaStyle: {
                            type: "default"
                        }
                    }
                },
                data:ydata
            }, {
                name: "高度",
                type: "line",
                smooth: !0,
                itemStyle: {
                    normal: {
                        areaStyle: {
                            type: "default"
                        }
                    }
                },
                data:ydata
            }]
        };
        _self.myChart.setOption(option);
        var setTime = "";
        window.addEventListener('resize', function () {
            if (setTime) clearTimeout(setTime);
            setTime = setTimeout(function () {
                _self.myChart.resize();
            }, 200)
        });
    },
    //得到趋势图数据
    getCharts: function () {
        var _self = this;
        biyue.ajax({
            url: '/hookData/getChartData',
            data: {
                hookId: $("[name=\"hook\"]").val()
            },
            fun: function (data) {
                var dataV = data.value;
                if (dataV) {
                    _self.myChart.setOption({
                        xAxis: {
                            data: dataV.xData
                        },
                        series: [{
                            name: '幅度',
                            data: dataV.extentData
                        }, {
                            name: '高度',
                            data: dataV.heightData
                        }]
                    });
                }
            }
        })
    }
};