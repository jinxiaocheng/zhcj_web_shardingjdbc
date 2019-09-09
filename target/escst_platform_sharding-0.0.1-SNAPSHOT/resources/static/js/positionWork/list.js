init();

//添加
$("#add_link").click(function () {
    var orgId = $("#orgId").val();
    var _url = ctx + '/positionWork/toAdd';
    var _title = '录入';
    layer_showDouble(_title, _url);
});


//修改
$("#change_link").click(function () {
    var gr = $("#gridTable").jqGrid('getGridParam','selrow');
    var rowData = $("#gridTable").jqGrid('getRowData',gr);
    var id = rowData.id;
    var _url = ctx + '/positionWork/toEdit?id=' + id;
    var _title = '编辑';
    if (gr != null) {
        window.editData=rowData;
        layer_showDouble(_title, _url);
    } else {
        layer.msg('您没有选中任何数据请选择后操作', {time: 2000, icon: 0});
    }
});

//查询
$("#search_link").click(function () {
    var url = getGridUrl();
    var postParams = getGridParamJson();
    $("#gridTable").jqGrid("setGridParam", {
        url: url,
        postData: postParams,
        page: 1,
    }).trigger("reloadGrid");
})

//数据初始化
$("#refresh_link").click(function () {
    biyue.ajax({
        url:'/positionWork/initData',
        data:{
            constructionId:$("#site").attr("data-value")
        },
        fun:function (data) {
            $("#search_link").click();
        }
    })
})


// 设置表格
function getGridHead() {
    return [
        {'label': 'id', 'name': 'id', 'index': 'id', 'hidden': true},
        {'label': 'areaId', 'name': 'areaId', 'index': 'areaId', 'hidden': true},
        {'label': 'constructionId', 'name': 'constructionId', 'index': 'constructionId', 'hidden': true},
        {'label': '名称', 'name': 'name', 'index': 'name','align':'left'},
        {'label': '类型', 'name': 'type', 'index': 'type','formatter': 'select', 'editoptions': {value: "1:岗位;2:工种;"}},
        {'label': '工地简称', 'name': 'constructionName', 'index': 'constructionName'},
        {'label': '区域', 'name': 'areaName', 'index': 'areaName'}

    ];
}

// 点击查询，获取查询所需要的条件，重新加载表格
function getGridParamJson() {
    var postPrams = {
        name: $.trim($("#name").val()),
        type: $("#queryType").attr("data-value"),
        "constructionId":$("#site").attr("data-value")
    };
    return postPrams;
}
//获取Url
function getGridUrl() {
    return ctx + "/positionWork/list";
}
