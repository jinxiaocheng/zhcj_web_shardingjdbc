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
                            personName:$("#personName").val(),
                            category:$("#work").attr("data-value")
                        };
                        if(treeNode.level===0){

                        }else if(treeNode.level===1){
                            data.ownerDeptID=treeNode.id
                        }else{
                            data.unitID=treeNode.id
                        }
                        //清除Table条件
                        var postData = $('#gridTable').jqGrid("getGridParam", "postData");
                        $.each(postData, function (k, v) {
                            delete postData[k];
                        });

                        $("#gridTable").jqGrid("setGridParam", {
                            url:  ctx+"/thridPerson/getAllPerson",
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
        var url = ctx+"/thridPerson/getAllPerson";
        biyue.jqTable({
            url:url,
            postData:{
                startDate:$("#startDate").val(),
                endDate:$("#endDate").val(),
                personName:$("#personName").val(),
                train:$("#train").attr("data-value"),
                category:$("#work").attr("data-value")
            },
            tableJson:[
                {'label': 'id', 'name': 'id', 'index': 'id', 'hidden': true},
                {'label': '姓名', 'name': 'personName', 'index': 'personName','width':'150px'},
                {'label': '性别', 'name': 'sex', 'index': 'sex','width':'100px'},
                {'label': '籍贯', 'name': 'natives', 'index': 'natives','width':'100px'},
                {'label': '身份证号', 'name': 'identifyID', 'index': 'identifyID','width':'260px'},
                {'label': '民族', 'name': 'nation', 'index': 'nation','width':'100px'},
                {'label': '所属项目部', 'name': 'departName', 'index': 'departName','width':'200px'},
                {'label': '工种', 'name': 'category', 'index': 'category','width':'200px'},
                {'label': '单位', 'name': 'unitName', 'index': 'unitName','width':'200px'},
                {'label': '登记日期', 'name': 'registerDate', 'index': 'registerDate','width':'260px'}
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
                    personName:$("#personName").val(),
                    train:$("#train").attr("data-value"),
                    category:$("#work").attr("data-value")
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
                    url:'/route/smz/person/toView?id='+gr
                })
            } else {
                layer.msg('您没有选中任何数据请选择后操作', {time: 2000, icon: 0});
            }

        });
        //导出
        $("#export_link").click(function () {
            var treeObj= $.fn.zTree.getZTreeObj("orgTree");
            var treeNode = treeObj.getSelectedNodes(true);
            var upData = {
                startDate:$("#startDate").val(),
                endDate:$("#endDate").val(),
                personName:$("#personName").val(),
                train:$("#train").attr("data-value"),
                category:$("#work").attr("data-value")
            }
            if(treeNode.length>0){
                if(treeNode[0].level===1){
                    upData.ownerDeptID=treeNode[0].id
                }else{
                    upData.unitID=treeNode[0].id
                }
            }

            biyue.ajax({
                url:'/thridPerson/exportExcel',
                data:upData,
                fun: function (data) {
                    var filePath = data.value;
                    var link = ctx +'/'+ filePath;
                    window.location.href=link;
                }
            });
        });
    },
    getWork:function () {
        biyue.ajax({
            url:"/thridPerson/getCode",
            data:{
                type:3
            },
            fun:function (data) {
                var dataV = data.value,$list='<li><a data-value="">请选择</a></li>';
                for(var i in dataV){
                    $list+='<li><a data-value="'+dataV[i]+'">'+dataV[i]+'</a></li>'
                }
                $(".work").html($list);
            }
        })
    }
}