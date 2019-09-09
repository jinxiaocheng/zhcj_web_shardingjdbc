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
//获取联系人、分包公司、班组、联系电话、岗位/工种
$(".selectList").click(function () {
    var url = ctx + '/person/toSelectList?constructionId='+constructionId;
    var title = '选择人员';
    layer_showOdd(title, url, {w:'900px',h:'460px'});
});

//获得选中的数据
function selectList(data) {
    $("#sub").val(data.projectCompanyName||"").attr("data-value",data.projectCompanyId||"");//分包公司
    $("#item").val(data.teamName||"").attr("data-value",data.teamId||"");//班组
    $("#add_person").val(data.name||"").attr("data-value",data.userId||"");//责任人
    $("#add_tel").val(data.mobile||"");//联系电话
}
// 选择人员数据初始化
function selectListInit() {
    $("#sub").val("").attr("data-value","");//分包公司
    $("#item").val("").attr("data-value","");//班组
    $("#add_person").val("").attr("data-value","");//责任人
    $("#add_tel").val("");//联系电话
}
window.selectList=selectList;


//获取材料名称(材料进场)
$(".getMaterial").click(function () {
    var url = ctx + '/materialApproach/toGetMaterialName';
    var title = '选择材料';
    layer_showOdd(title, url, {w:'90%',h:'90%'});
});

//获得选中的材料数据
function getMaterialData(data) {
    $("#selectMaterial").val(data.materialName||"").attr("data-value",data.materialId||"");//材料名称
    $("#modelName").val(data.modelName||"").attr("data-value",data.modelId||""); //型号
    $("#unit").val(data.unit||""); //单位
}

// 选择人员数据初始化
function materialDataInit() {
    $("#selectMaterial").val("").attr("");//材料名称
    $("#modelName").val("").attr(""); //型号
    $("#unit").val(""); //单位
}
window.getMaterialData=getMaterialData;



//获取材料信息(材料领用)
$(".getMaterialInfo").click(function () {
    var constructionId=$('.site button').attr('constructionid');
    var url = ctx + '/materialAcquisition/toGetMaterialData?constructionId='+constructionId;
    var title = '选择材料';
    layer_showOdd(title, url, {w:'90%',h:'90%'});
});

//获得选中的材料信息
function getMaterialInfo(data) {
    $("#selectMaterial").val(data.materialName||"").attr("data-value",data.materialId||"");//材料名称
    $("#approachId").attr("data-value",data.id||""); //进场id
    $("#model").val(data.modelName||""); //规格型号
    $("#measurementUnit").val(data.unit||""); //计量单位
    $("#manufacturer").val(data.manufacturer||""); //生产厂家
    $("#usePosition").val(data.usePosition||""); //使用部位
    $("#availableQuantity").val(data.qty||""); //可用量

}

// 选中的材料信息初始化
function materialInfoInit() {
    $("#selectMaterial").val("").attr("data-value","");//材料名称
    $("#model").val(""); //规格型号
    $("#measurementUnit").val(""); //计量单位
    $("#manufacturer").val(""); //生产厂家
    $("#usePosition").val(""); //使用部位
    $("#availableQuantity").val(""); //可用量

}
window.materialInfoInit=materialInfoInit;