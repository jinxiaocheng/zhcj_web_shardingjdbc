//数据较少
function layer_showOdd(title,url,area) {
    if (title == null || title == '') {
        title=false;
    };
    if (url == null || url == '') {
        url="404.html";
    };
    var w, h;
    if(area == null || area == ''){
        w = '400px';
        h = '300px'
    } else {
        w = area.w;
        h = area.h;
    }
    layer.open({
        type: 2,
        title: "|&nbsp;"+title,
        maxmin: true,
        area: [w,h],
        shadeClose: false, //点击遮罩关闭
        content:url
    });
};
// 获取 材料、型号、单位
$(".getMaterialData").click(function () {
    var url = ctx + '/materialBudget/chooseMaterial?constructionId='+constructionId;
    var title = '材料、型号、单位';
    layer_showOdd(title, url, {w:'900px',h:'460px'});
});
//获得选中的数据
function selectList(data) {
    $("#materialName").val(data.name).attr("data-value",data.id);
    $("#materialModel").val(data.modelName).attr("data-value",data.id);
    $("#materialUnit").val(data.unit).attr("data-value",data.id);
}
// 选择人员数据初始化
function selectListInit() {
    $("#materialName").val("");
    $("#materialModel").val("");
    $("#materialUnit").val("");
}
window.selectList=selectList;