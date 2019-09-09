var item_data=[],//班组数据
    bool=true,
    files=[];//图片文件
var constructionId="";//工地ID
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
    if ( e && e.stopPropagation ) {
        e.stopPropagation();
    } else {
        window.event.cancelBubble = true;
    }
    if(bool){
        bool=false;
        $("#tree").fadeIn("fast");
        $("body").bind("mousedown", onBodyDown);
    }else{
        hideMenu();
    }
}
//隐藏
function hideMenu() {
    bool=true;
    $("#tree").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
//点击window隐藏
function onBodyDown(event) {
    if (!(event.target.id == "construction" || event.target.id == "tree" || $(event.target).parents("#tree").length>0)) {
        hideMenu();
    }
}
//检查部位
function my_day0(id){
    var my_json={
        "constructionId":id
    };
    $.ajax({
        type: "post",
        url: ctx + '/projectStructure/projectStructureList',
        data: my_json,
        success: function (data) {
            if(data.status == 1){
                if(data.value){
                    //生成树形图
                    $.fn.zTree.init($("#tree"), setting, data.value);
                    treeObj = $.fn.zTree.getZTreeObj("tree");
                    if(data.value == '')
                    {
                        console.log('未创建个人目录 !');
                    }
                }
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.alert(String(e));
        }
    });
}

//预约设备
$(".equipmentType li a").on("click",function () {
    var equipmentType=$(this).attr('data-value');
    positionWorkType(constructionId,equipmentType)
})

//处理意见
$(".result li").click(function () {
    $(".Abarbeitung").fadeOut();
    var r_id= $(this).attr("r-id")
    $(".result ul").attr("result-id",r_id)
    $(this).addClass("focus").siblings().removeClass("focus");
    if($(this).index()==2){
        $(".Abarbeitung").fadeIn();
    }
});


function my_ajax(){
    var isUrgentTask=$(".isUrgentTask").attr("result-id"),//是否紧急
        equipmentType=$("#equipmentType").attr("data-value"),//预约设备类型
        handlePerson=$("#handle").attr("data-value"),//处理人
        add_es=$(".es>button>label").attr("k_id"),//获取工程id
        add_sub=$("#sub").attr("data-value"),//分包公司
        add_item=$("#item").attr("data-value"),//班组
        add_un= $("#add_person").attr("data-value"),//责任人id
        add_date=$("#add_date").val(),//预约时间
        add_ut=$("#add_tel").val(),//联系电话
        add_remark=$("#remark>textarea").val();//备注信息

    var formData = new FormData($("#form_data")[0]);
    formData.append("constructionId",constructionId)
    if(add_es!=""&&add_es!=null&&add_es!=undefined){
        formData.append("projectStructureId",add_es)
    }
    formData.append("isUrgentTask",isUrgentTask)
    formData.append("equipmentType",equipmentType)
    formData.append("handlePerson",handlePerson)
    formData.append("remark",add_remark)
    formData.append("appointmentDate",add_date)
    formData.append("projectCompanyId",add_sub)
    formData.append("mobile",add_ut);
    formData.append("appointmentId",add_un)
    if(add_item!=""&&add_item!=null&&add_item!=undefined){
        formData.append("teamId",add_item)
    }

    $.ajax({
        url: ctx+"/taskAppointment/add",                              //后台请求地址
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
            layer.alert(String(e));
            $(".my_load").hide();
        }

    });
}

//得到工地上的塔吊和升降机司机
function positionWorkType(id,type){
    var my_json={
        "constructionId":id
    };
    $.ajax({
        type: "post",
        url: ctx + '/person/queryWorkTypeByConstructionId',
        data: my_json,
        success: function (data) {
            if(data.status == 1){
                if(data.value){
                    if(data.value){
                        console.log(data.value);
                        var $list='';
                        $("#handle").attr("sub_id","").html('请选择'+'<span class="caret" style="margin-left:6px"></span>');
                        for(var i in data.value){
                            $list+=' <li ><a data-value="'+data.value[i].userId+'">'+data.value[i].name+'</a></li>'
                        }
                        $(".handle").html($list);
                        $(".handle").parent().Bsselect();
                    }
                }
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.alert(e);
        }
    });
}

/*关闭弹出框口*/
function layer_close() {
   biyue.layui_close();
};

