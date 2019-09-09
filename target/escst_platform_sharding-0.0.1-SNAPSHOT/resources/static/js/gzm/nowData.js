var biyueList = {
    flowId:"",
    constructionId:"",
    getZtree: function () {
        var _self = this,table = biyue.tableList();
        biyue.ajax({
            url:'/warning/listFlowTree',
            data: {
                userId: userId
            },
            fun: function (data) {
                var dataV = data.value;
                zTreeObj = $.fn.zTree.init($("#menuTree"), biyue.ztreeSet({
                    click:function (event, treeId, treeNode) {
                        _self.flowId = treeNode.id;
                        _self.constructionId = treeNode.pId;
                        searchIf();
                        _self.getImg();
                    },
                    beforeClick:function (treeId, treeNode, clickFlag) {
                        if(treeNode.isParent){
                            return false;
                        }
                    }
                }), dataV);
                var node = zTreeObj.getNodes()[0];
                //获取第一个字节点
                function filterNode(data) {
                    if(data.children){
                        filterNode(data.children[0])
                    }else{
                        node = data;
                    }
                }
                filterNode(node);
                zTreeObj.selectNode(node);
                _self.flowId = node.id;
                _self.constructionId = node.pId;
                _self.createTable();
                _self.getImg();
            }
        })
    },
    getImg:function () {
        var _self = this,table = biyue.tableList();
        biyue.ajax({
            url:'/flow/queryFilePath',
            data: {
                flowId: _self.flowId
            },
            fun: function (data) {
                var dataV = data.value;
                $("#testPicture").attr("data-value",dataV);
            }
        })
    },
    createTable: function () {
        var _self = this, table = biyue.tableList();
        table.loadTable({
            url:'/highformworkRealTimeController/queryByFlowId'
            , upData: {
                flowId: _self.flowId
            }
            , cols: [[
                {type: 'numbers'}
                , {field: 'equipmentName', title: '测点名称'}
                , {field: 'number', title: '测点编号'}
                , {field: 'acquisitionTime', title: '测量时间',width:150}
                , {field: 'xAngle', title: 'X倾角(°)', templet: '#xAngle',width:100}
                , {field: 'yAngle', title: 'Y倾角(°)', templet: '#yAngle',width:100}
                , {field: 'kpa', title: '压力(KN)', templet: '#kpa',width:100}
                , {field: 'displace', title: '位移(mm)', templet: '#displace',width:100}
                , {field: 'electric', title: '可用电量(%)', templet: '#electric',width:120}
                , {field: 'temperature', title: '温度(℃)', templet: '#temperature',width:100}
            ]]
            , height: 'full-110'
        });
        window.searchIf = function () {
            table.search({
                where: {
                    flowId: _self.flowId
                }
            })
        };
    },
    toolsBtn: function () {
        var _self = this;
        //预警数据
        $("#warningData").click(function () {
            biyue.open({
                title:'预警数据',
                url:'/route/gzm/nowDataWarningData?type=warningData&flowId='+_self.flowId+"&constructionId="+ _self.constructionId,
                area:{
                    w:'80%',
                    h:'80%'
                }
            })
        });
        //趋势图
        $("#lineChart").click(function () {
            biyue.open({
                title:'趋势图',
                url:'/route/gzm/nowDataLineChart?type=lineChart&id='+_self.flowId+"&constructionId="+ _self.constructionId,
                area:{
                    w:'80%',
                    h:'80%'
                }
            })
        });
        //历史数据
        $("#historyData").click(function () {
            biyue.open({
                title:'历史数据',
                url:'/route/gzm/nowDataHistoryData?type=historyData&flowId='+_self.flowId,
                area:{
                    w:'80%',
                    h:'80%'
                }
            })
        });
        //测点部署图
        $("#testPicture").click(function () {
            var src = $(this).attr("data-value");
            top.photos([{
                "src": src
            }], 0)
        });
        //查看阈值
        $("#viewWarning").click(function () {
            biyue.open({
                title:'查看阈值',
                url:'/route/gzm/nowDataViewWarning?type=viewWarning&id='+_self.flowId,
                area:{
                    w:'80%',
                    h:'80%'
                }
            })
        })
    }
};