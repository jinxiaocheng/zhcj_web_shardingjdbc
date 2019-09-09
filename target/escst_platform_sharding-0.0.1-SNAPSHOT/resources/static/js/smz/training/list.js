var smzPlist={
    createZtree:function () {
        biyue.ajax({
            url:"/toolboxController/queryDepartTree",
            fun:function (data) {
                var set =biyue.ztreeSet({
                    click:function (event, treeId, treeNode) {
                        var data ={
                            startDate:$("#startDate").val(),
                            endDate:$("#endDate").val(),
                            trainName:$("#name").val(),
                            trainType:$("#trainType").attr("data-value"),
                        };
                        if(treeNode.level!==0){
                            data.departCode=treeNode.id
                        }

                        //清除Table条件
                        var postData = $('#gridTable').jqGrid("getGridParam", "postData");
                        $.each(postData, function (k, v) {
                            delete postData[k];
                        });

                        $("#gridTable").jqGrid("setGridParam", {
                            url: ctx+"/train/listTrainRecord",
                            postData: data,
                            page: 1
                        }).trigger("reloadGrid");

                    }
                });
                $.fn.zTree.init($("#orgTree"),set, data.value);
                biyue.scroll(".menu-left-tree");
            }
        })
    },
    createTable:function () {
        var _self = this ;
        var url = ctx+"/train/listTrainRecord";
        biyue.jqTable({
            url:url,
            postData:{
                startDate:$("#startDate").val(),
                endDate:$("#endDate").val(),
                trainName:$("#name").val(),
                trainType:$("#trainType").attr("data-value")
            },
            tableJson:[
                {'label': 'id', 'name': 'id', 'index': 'id', 'hidden': true},
                {'label': '培训名称', 'name': 'trainName', 'index': 'trainName','width':'260px'},
                {'label': '培训类型', 'name': 'trainType', 'index': 'trainType','width':'150px'},
                {'label': '培训时间', 'name': 'trainTime', 'index': 'trainTime','width':'200px'},
                {'label': '项目部单位', 'name': 'departName', 'index': 'departName','width':'200px'},
                {'label': '培训单位', 'name': 'trainDepart', 'index': 'trainDepart','width':'200px'},
                {'label': '状态', 'name': 'persenceNum', 'index': 'persenceNum','width':'100px',formatter:function(cellvalue, options, rowObject){
                    return '<span style="color: green">结束</span>'
                }}
            ],
            ondblClickRow:function () {
                $("#view_link").click()
            }
        });

        //查询
        $("#search_link").click(function () {
            $("#gridTable").jqGrid("setGridParam", {
                url: url,
                postData: {
                    startDate:$("#startDate").val(),
                    endDate:$("#endDate").val(),
                    trainName:$("#name").val(),
                    trainType:$("#trainType").attr("data-value")
                },
                page: 1
            }).trigger("reloadGrid");
        });

        //查看
        $("#view_link").on('click',function(){
            var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
            if (gr != null) {
                biyue.open({
                    area:{
                        w:'900px',
                        h:'500px'
                    },
                    title:'查看详情',
                    url:'/route/smz/training/toView?id='+gr
                })
            } else {
                layer.msg('您没有选中任何数据请选择后操作', {time: 2000, icon: 0});
            }

        });
    },
    trainType:function () {
        biyue.ajax({
            url:"/train/listTrainType",
            fun:function (data) {
                var dataV = data.value,$list='<li><a data-value="">请选择</a></li>';
                for(var i in dataV){
                    $list+='<li><a data-value="'+dataV[i]+'">'+dataV[i]+'</a></li>'
                }
                $(".trainType").html($list);
            }
        })
    }
}