var item_data = [],//班组数据
    bool = true,
    files=[];//图片文件
var setting = {
    view: {
        showLine: true,
        selectedMulti: false //不可多选
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: 0
        }
    },
    async: {
        autoParam: ["id=catalogID"],
        otherParam: ["type", "M"],
        enable: true,
        dataType: "json",
        type: "get",
        url: "${ctx}/document/queryCatalogTree"
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

function onClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("tree"),
        nodes = zTree.getSelectedNodes(),
        v = "",
        k_id="";
    nodes.sort(function compare(a,b){return a.id-b.id;});
    for (var i=0, l=nodes.length; i<l; i++) {
        v += nodes[i].name
        var nodesObj=nodes[i];
        while (nodesObj.getParentNode()){
            v=nodesObj.getParentNode().name+' / '+v;
            nodesObj=nodesObj.getParentNode();
        }
    }
    if (v.length > 0 ) v = v.substring(0, v.length-1);
    var cityObj = $("#construction>label");
    cityObj.html(v);
    cityObj.attr("k_id",treeNode.id);
    hideMenu();
}

//显示
function showMenu(e) {
    if (e && e.stopPropagation) {
        e.stopPropagation();
    } else {
        window.event.cancelBubble = true;
    }
    if (bool) {
        bool = false;
        $("#tree").fadeIn("fast");
        $("body").bind("mousedown", onBodyDown);
    } else {
        hideMenu();
    }
}

//隐藏
function hideMenu() {
    bool = true;
    $("#tree").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}

//点击window隐藏
function onBodyDown(event) {
    if (!(event.target.id == "construction" || event.target.id == "tree" || $(event.target).parents("#tree").length > 0)) {
        hideMenu();
    }
}

//检查部位
function my_day0(id) {
    if(!id) {return}
    var my_json = {
        "constructionId": id
    };
    $.ajax({
        type: "post",
        url: ctx + '/projectStructure/projectStructureList',
        data: my_json,
        success: function (data) {
            if (data.status == 1) {
                if (data.value) {
                    //生成树形图
                    $.fn.zTree.init($("#tree"), setting, data.value);
                    treeObj = $.fn.zTree.getZTreeObj("tree");
                    if (data.value == '') {
                        console.log('未创建个人目录 !');
                    }
                }
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            console.error('请求错误:'+e);
        }
    });
}

//处理意见
$(".result li").click(function () {
    var r_id= $(this).attr("r-id");
    $(".result ul").attr("kk-result",r_id);
    $(this).addClass("focus").siblings().removeClass("focus");
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
        showDrag: false,
    }
});

function my_ajax() {
    var add_time = $(".date_time>input").val(),//事故时间
        add_es = $(".es>button>label").attr("k_id"),//获取工程id
        add_sub=$("#sub").attr("data-value"),//分包公司
        add_item=$("#item").attr("data-value"),//班组
        add_un= $("#add_person").attr("data-value"),//联系人id
        add_ut=$("#add_tel").val(),//联系电话
        add_ci = $(".check_item textarea").val(),//事故简述
        add_cr = $(".check_result textarea").val(),//初步处理意见
        add_re = $(".result ul").attr("kk-result"), //处理意见
        files=$('#file').fileinput('getFileStack');//得到文件

    var formData = new FormData($("#form_data")[0]);
    formData.append("constructionId", constructionId);
    formData.append("type", type);
    formData.append("accidentDate", add_time);
    formData.append("resume", add_ci);
    formData.append("firstTreatment", add_cr);
    formData.append("projectStructureId", add_es);
    formData.append("projectCompanyId", add_sub);
    formData.append("contactId", add_un);
    formData.append("contactMobile", add_ut);
    formData.append("teamId", add_item);
    formData.append("level", add_re);
    formData.append("projectStructureList",JSON.stringify(biyue_skewing.getSkewing()));    //检查部位

    for(var i in kk_id1){
        formData.append("injuredPersonIds", kk_id1[i])
    }
    for(var j in kk_id2){
        formData.append("deathPersonIds", kk_id2[j])
    }
    for (var i = 0; i < files.length; i++) {
        formData.append("files", files[i]);
    }


    $.ajax({
        url: ctx + "/accidentReport/save",         //后台请求地址
        data: formData,                            //自定义参数
        processData: false,
        contentType: false,
        type: "post",                              //当要提交自定义参数时，这个参数要设置成post
        dataType: 'json',                          //服务器返回的数据类型
        success: function (data) {                 //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
            if (data.status == 1) {
                layer.alert("提交成功！",function(){$(".my_load").hide();
                    layer_close();});

            } else {
                layer.alert(data.msg);
                $(".my_load").hide();
            }
            $(".my_load").hide();
        },
        error: function (data, status, e) {      //提交失败自动执行的处理函数
            console.error('请求错误:'+e);
            $(".my_load").hide();
        }
    });

}

