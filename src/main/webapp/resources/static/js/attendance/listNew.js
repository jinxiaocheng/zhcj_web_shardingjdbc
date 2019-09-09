var biyueCount = {
    query:function () {
        return {
            startDate:$("[name=\"startTime\"]").val(),
            endDate:$("[name=\"endTime\"]").val(),
            constructionId:$('[name="site"]').val()
        }
    },
    eCharts: function (obj) {
        var color = ['#fd9173', '#f36f8a', '#5f71d2', '#42a4eb', '#4ac7f5','rgb(164, 216, 194)','rgb(243, 217, 153)'];
        var option = {
            backgroundColor: '#fff',
            grid: {
                left:'50px',
                right:'50px',
                bottom: '35px'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                top:'10px',
                data: ['总人数','出勤人数','在场人数','出勤率']
            },
            calculable: true,
            xAxis: [{
                type: 'category',
                data:  obj.x
            }],
            yAxis: [{
                name:'人数(人)',
                type: 'value',
                minInterval: 1,
                axisLabel: {
                    formatter: '{value}人'
                },
                axisLine: {
                    lineStyle: {
                        color: color[4]
                    }
                }
            },{
                name:'出勤率(%)',
                type: 'value',
                axisLabel: {
                    formatter: '{value}%'
                },
                axisLine: {
                    lineStyle: {
                        color: color[1]
                    }
                },
                splitLine:{
                    show:false
                }
            }],
            series: [
                {
                    name: '总人数',
                    type: 'bar',
                    barMaxWidth:40,
                    itemStyle: {
                        normal: {
                            color: color[0],
                            label: {
                                show: true,
                                position: 'top',
                                textStyle: {
                                    fontSize: 14
                                }
                            }
                        }
                    },
                    data: obj.allNum
                }, {
                    name: '出勤人数',
                    type: 'bar',
                    barMaxWidth:40,
                    itemStyle: {
                        normal: {
                            color: color[4],
                            label: {
                                show: true,
                                position: 'top',
                                textStyle: {
                                    fontSize: 14
                                }
                            }
                        }
                    },
                    data: obj.cqNum
                }, {
                    name: '在场人数',
                    type: 'bar',
                    barMaxWidth:40,
                    itemStyle: {
                        normal: {
                            color: color[3],
                            label: {
                                show: true,
                                position: 'top',
                                textStyle: {
                                    fontSize: 14
                                }
                            }
                        }
                    },
                    data: obj.nowNum
                }
                , {
                    name: '出勤率',
                    type: 'line',
                    barMaxWidth:40,
                    yAxisIndex: 1,
                    itemStyle: {
                        normal: {
                            color: color[1],
                            label: {
                                show: true,
                                position: 'top',
                                textStyle: {
                                    fontSize: 14
                                }
                            }
                        }
                    },
                    label:{
                        formatter:'{c}%'
                    },
                    data: obj.cqlNum
                }]
        };
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
    createTable: function () {
        var _self = this, table = biyue.tableList();
        table.loadTable({
            url: '/attendance/selectAttendanceByUserId'
            , where: _self.query()
            , cols: [[
                {type: 'numbers'}
                , {field: 'areaName', title: '所属区域',width:180}
                , {field: 'constructionName', title: '工地名称',width:240}
                , {field: 'companyName', title: '公司', width: 250}
                , {field: 'totalNum', title: '总人数'}
                , {field: 'turnOutNum', title: '出勤人数'}
                , {field: 'persenceNum', title: '在场人数'}
                , {field: 'attendancePercent', title: '出勤率'}
                , {fixed: 'right', title:'操作',width:90, align:'center', toolbar: '#barDemo'}
            ]]
            , limit: 7
            , height: 360
        });
        //编辑
        layui.table.on('tool(table-list)', function(obj){
            var data = obj.data
                ,layEvent = obj.event;
            if(layEvent==="view"){
                biyue.open({
                    title:'查看明细',
                    url:'/attendance/toDetail?type=view&id='+data.companyId+'&constructionId='+$('[name="site"]').val(),
                    area:{
                        w:'80%',
                        h:'80%'
                    }
                })
            }
        });
        window.searchIf=function () {
            table.search({
                where: _self.query()
            })
        };
    },
    lineData:function (id) {
        var _self = this;
        biyue.ajax({
            url: "/attendance/createChart",
            data: {
                id:$("[name=\"company\"]").val(),
                startDate:$("[name=\"startTime\"]").val(),
                endDate:$("[name=\"endTime\"]").val()
            },
            fun: function (data) {
                var type = $("#type").attr("data-value");
                var dataV = data.value;
                var echart= {
                    x:[],
                    allNum:[],
                    cqNum:[],
                    nowNum:[],
                    cqlNum:[]
                };

                for(var i in dataV){
                    for(var j in dataV[i]){
                        echart.x.push(j);
                        echart.allNum.push(dataV[i][j][0]);
                        echart.cqNum.push(dataV[i][j][1]);
                        echart.nowNum.push(dataV[i][j][2]);
                        echart.cqlNum.push(parseFloat(dataV[i][j][3]));
                    }
                }
                _self.eCharts(echart);
            }
        });
    },
    company:function (fun) {
        var form=layui.form;

        //获取合作方
        biyue.ajax({
            url:'/projectCompany/selectCompany',
            data:{
                constructionId:$('[name="site"]').val()
            },
            fun:function (data) {
                var $list=""
                    ,dataV=data.value;

                for(var i=0;i<dataV.length;i++){
                    $list+='<option value="'+dataV[i].id+'">'+dataV[i].name+'</option>';
                }

                $("[name='company']").html($list);
                form.render('select');
                fun&&fun();
            }
        })
    },
    time:function () {
        //开始-结束时间
        ;(function () {
            var date = new Date();
            var laydate= layui.laydate,form=layui.form;
            var endTime = biyue.timeText(date) + '23:59:59';
            var startTime = biyue.timeText(new Date(date.setDate(date.getDate()))) + '00:00:00';
            $("#startTime").val(startTime);
            $("#endTime").val(endTime);
            var endDate = laydate.render({
                elem:"#endTime"
                ,type: 'datetime'
                ,value:endTime
                ,done: function(value, date) {
                    if(value !==""){
                        date.month = date.month - 1;
                        startDate.config.max = date;
                    }
                }
            });
            var startDate = laydate.render({
                elem:"#startTime"
                ,value:startTime
                ,type: 'datetime'
                ,done: function(value, date) {
                    if(value !==""){
                        date.month = date.month - 1;
                        endDate.config.min = date;
                    }
                }
            });
        })();
    },
    init: function () {
        var _self = this,form=layui.form;
        _self.time();
        $("#search").click(function () {
            _self.lineData();
            searchIf();
            _self.company(function () {
                _self.lineData();
            });
        });
        _self.company(function () {
            _self.lineData();
        });
        form.on('select(company)', function(data){
            _self.lineData();
        });
    }
};