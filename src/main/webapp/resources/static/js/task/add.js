var item_data=[],//班组数据
    bool=true,
    files=[];//图片文件
var areaId="";
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
                console.log(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            console.error('请求错误:'+e);
        }
    });
}

//派送人
function person(id){
    var my_json={
            "constructionId":id,
        },
        sub_data=[];
    $.ajax({
        type: "post",
        url: ctx + '/person/queryPersonByConstructionId',
        data: my_json,
        success: function (data) {
            if(data.status == 1){
                if(data.value){
                    var len=data.value.length,$list="";
                    for(var i=0;i<len;i++){
                        sub_data.push(data.value[i].data)
                        $list+='<li><a>'+data.value[i].name+'</a></li>'
                    }
                    $(".person>ul").html($list);
                    $(".person>ul>li").click(function () {
                        var index=$(this).index();
                        $(".person>button").attr("person_id",data.value[index].userId).html(data.value[index].name+'<span class="caret" style="margin-left:6px"></span>');
                    });
                    if(top.globalData.userType!=='3'){
                        $(".person>button").attr("person_id","").html("请选择派送人"+'<span class="caret" style="margin-left:6px"></span>');
                    }
                }
            } else {
                console.log(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            console.error('请求错误:'+e);
        }
    });
}


/*关闭弹出框口*/
function layer_close() {
   biyue.layui_close();
}
//点击提交
function my_ajax() {
    add_es=$(".es>button>label").attr("k_id"),//获取工程id
        add_sub=$("#sub").attr("data-value"),//分包公司
        add_item=$("#item").attr("data-value"),//班组
        add_un= $("#add_person").attr("data-value"),//责任人id
        add_ut=$("#add_tel").val(),//联系电话
        add_person=$(".person>button").attr("person_id"),//检查人
        add_remark=$("#remark>textarea").val();//备注信息
    var formData = new FormData($("#form_data")[0]);
    formData.append("constructionId",constructionId);
    formData.append("projectStructureId",add_es);
    formData.append("userId",add_person);
    formData.append("remark",add_remark);
    formData.append("projectCompanyId",add_sub);
    formData.append("personId",add_un);
    formData.append("contactMobile",add_ut);
    formData.append("teamId",add_item);
    $.ajax({
        url: ctx+"/projectTask/addProjectTask",                              //后台请求地址
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
            console.error('请求错误:'+e);
            $(".my_load").hide();
        }
    });
}
