//新增
$("#add_link").click(function () {
    var orgId = $("#orgId").val();
    var _url = ctx + '/message/toAdd';
    var _title = '新增';
    var iframeWidth = '780px';
    var iframeHeight = '500px';
    layer_showDouble(_title, _url, {w:iframeWidth,h:iframeHeight});
});

// 设置表格
function getGridHead() {
    return [
        {'label': 'id', 'name': 'id', 'index': 'id', 'hidden': true},
        {'label': 'billId', 'name': 'billId', 'index': 'billId', 'hidden': true},
        {'label': 'auth', 'name': 'auth', 'index': 'auth', 'hidden': true},
        {'label': '标题', 'name': 'title', 'index': 'name'},
        {'label': '接收人数', 'name': 'acceptNum', 'index': 'acceptNum',formatter:function(cellvalue, options, rowObject) {
            if(rowObject.auth===false){
                return ""
            }else {
                return cellvalue;
            }
        }},
        {'label': '内容', 'name': 'content', 'index': 'constructionName'},
        {'label': '操作', 'name': 'status', 'index': 'status','width':'40px',  'align':'center',formatter:function(cellvalue, options, rowObject) {
            var temp = "<button class='btn btn-info btn-xs ' kk-id='"+rowObject.billId+"' style='background-color: transparent' onclick = 'toHandle(this)'>查看接收人</button>";
            if(rowObject.auth===false){
                return ""
            }else {
                return temp;
            }
        }}
    ];
};

// 点击查询，获取查询所需要的条件，重新加载表格
function getGridParamJson() {
    var postPrams = {}
    return postPrams;
};

//获取Url
function getGridUrl() {
    return ctx + "/message/listData";
};

//点击查看接收人
function toHandle(element) {
    var id = $(element).attr("kk-id");
    var _title = '查看接收人';
    var _url = ctx + '/message/toView?billId=' + id;
    layer_showDouble(_title, _url, {w:'920px',h:'560px'});
}

//查看
$("#view_link").click(function () {
    viewLink()
});

function viewLink() {
    var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
    var url = '/route/message/viewList?id='+gr;
    var title = '查看详情';
    if (gr != null) {
        biyue.open({
            title:title,
            url:url
        })
    } else {
        layer.msg('您没有选中任何数据请选择后操作', {time: 2000, icon: 0});
    }
}