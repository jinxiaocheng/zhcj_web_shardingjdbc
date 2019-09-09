var item_data=[],//班组数据
    openZtree=true;
var setting = {
    view: {
        showLine: true,
        selectedMulti: false //不可多选
    },
    data: {
        simpleData: {
            enable:true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: 0
        }
    },
    async:{
        autoParam:["id=catalogID"],
        otherParam:["type","M"],
        enable:true,
        dataType:"json",
        type:"get",
        url:"${ctx}/document/queryCatalogTree"
    },
    check: {
        enable: false
    },
    callback: {
        beforeClick: beforeClick,
        onClick: onClick
    }
};

function beforeClick(treeId, treeNode) {
    var check = (treeNode && !treeNode.isParent);
    return check;
}

// 设置图片
$("#file").fileinput({
    language: 'zh',
    theme: 'fa',
    uploadUrl: '#',
    showUpload:false,
    maxFileSize:51200,
    minFileSize:1,
    browseIcon:'',
    removeIcon:'',
    allowedFileExtensions: ["bmp", "jpg", "jpeg", "png", "gif"],
    fileActionSettings:{
        showUpload: false,
        showZoom: false,
        showDrag: false,
    }
});

// 点击生成检查部位名称
function onClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("tree"),
        nodes = zTree.getSelectedNodes(),
        v = "",
        k_id="";
    nodes.sort(function compare(a,b){return a.id-b.id;});
    for (var i=0, l=nodes.length; i<l; i++) {
        v += nodes[i].name;
        var nodesObj=nodes[i];
        while (nodesObj.getParentNode()){
            v=nodesObj.getParentNode().name+' / '+v;
            nodesObj=nodesObj.getParentNode();
        }
    }
    var cityObj = $("#construction>label");
    cityObj.html(v);
    cityObj.attr("k_id",treeNode.id);
    hideMenu();
    openZtree=true;
}

//显示
function showMenu(event) {
    var e = event || window.event;
    var target = e.target || e.srcElement;//兼容ie7,8
    if (target.id == "construction"||$(target).parents("#construction").length>0) {
        if(openZtree){
            setTimeout(function () {
                $("#tree").fadeIn("fast");
                $("body").bind("mousedown", onBodyDown);
                openZtree=false;
            },20)

        }else{
            hideMenu();
            openZtree=true;
        }
    }
}
//隐藏
function hideMenu() {
    $("#tree").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
//点击window隐藏
function onBodyDown(event) {
    if (!(event.target.id == "construction"|| $(event.target).parents("#construction").length>0 || event.target.id == "tree" || $(event.target).parents("#tree").length>0)) {
        hideMenu();
        openZtree=true;
    }
}

//处理意见
$(".result li").click(function () {
    $(".Abarbeitung").fadeOut();
    var r_id= $(this).attr("r-id"),bool=false;
    $(".result ul").attr("kk-result",r_id);
    $(this).addClass("focus").siblings().removeClass("focus");
    if($(this).index()==2){
        $(".Abarbeitung").fadeIn();
        bool=true;
    }
    $(".Abarbeitung").kk_showTable(bool)
});

// 设置图片
$("#file").fileinput({
    language: 'zh',
    theme: 'fa',
    uploadUrl: '#',
    showUpload:false,
    maxFileSize:51200,
    minFileSize:1,
    browseIcon:'',
    removeIcon:'',
    allowedFileExtensions: ["bmp", "jpg", "jpeg", "png", "gif"],
    fileActionSettings:{
        showUpload: false,
        showZoom: false,
        showDrag: false
    }
});

function my_ajax() {
    var add_time=$(".date_time>input").val(),//获取时间
        add_es=$(".es>button>label").attr("k_id"),//获取工程id
        add_sub=$("#sub").attr("data-value"),//分包公司
        add_item=$("#item").attr("data-value"),//班组
        add_un= $("#add_person").attr("data-value"),//责任人id
        add_ut=$("#add_tel").val(),//联系电话
        add_ci=$(".check_item textarea").val(),//检查项目
        add_cr=$(".check_result textarea").val(),//检查结果
        add_re=$(".result ul").attr("kk-result"),//处理意见
        add_ab_time=$("#Abarbeitung_add_date").val(),//整改完成时间
        add_ad_status="",//整改状态
        add_ab_ch=$(".Abarbeitung_check_result textarea").val(),//整改意见
        add_constructionName=$(".site button").text(),
        add_contactName=$("#add_person").val(),
        files=$('#file').fileinput('getFileStack');//得到文件
    var formData = new FormData($("#form_data")[0]);
    formData.append("constructionName",add_constructionName);
    formData.append("contactName",add_contactName);
    formData.append("constructionId",constructionId);
    formData.append("type",type);
    formData.append("inspectDate",add_time);
    formData.append("items",add_ci);
    formData.append("results",add_cr);
    //检查部位
    formData.append("projectStructureList",JSON.stringify(biyue_skewing.getSkewing()));
    if(add_es!=""&&add_es!=null&&add_es!=undefined){
        formData.append("projectStructureId",add_es)
    }
    formData.append("projectCompanyId",add_sub)
    formData.append("contactId",add_un)
    formData.append("contactMobile",add_ut)
    if(add_item!=""&&add_item!=null&&add_item!=undefined){
        formData.append("teamId",add_item)
    }
    formData.append("processingOpinion",add_re)

    for(var i=0;i<files.length;i++){
        formData.append("files",files[i]);
    }
    if(add_re == 3) {
        formData.append("correctiveStatus",add_ad_status)
        formData.append("correctiveRequest",add_ab_ch)
        formData.append("correctiveCompletionDate",add_ab_time)
    }
    //添加抄送人
    if(Object.keys(kk_allData).length>0){
        var person_allData=[];
        for(var i in kk_allData){
            person_allData.push(kk_allData[i])
        }
        formData.append("notifyEntityList",JSON.stringify(person_allData))
    }
    $.ajax({
        url: ctx+"/inspection/save",                              //后台请求地址
        data: formData,                            //自定义参数
        processData: false,
        contentType: false,
        type: "post",                          //当要提交自定义参数时，这个参数要设置成post
        dataType: 'json',                     //服务器返回的数据类型
        success: function (data) {            //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
            if (data.status == 1) {
                layer.alert("提交成功！",function(){
                    layer_close();
                });

            } else {
                layer.alert(data.msg);
            }
            $(".my_load").hide();
        },
        error: function (data, status, e) {      //提交失败自动执行的处理函数
            layer.alert(e);
            $(".my_load").hide();
        }
    });
}

