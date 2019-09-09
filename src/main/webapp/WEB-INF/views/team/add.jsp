<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>班组信息维护-新增</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.min.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/validform.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/tableform.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/add.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/accident/add.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
    <script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}" type="text/javascript"></script>
</head>

<body class="body_bottom">

<div class="">
    <form id="personform" class="form-inline add_form">
        <h2 class="add_title">
            <p>基本信息</p>
        </h2>
        <div class="row add_list ">
            <div class="form-group col-xs-6 area" kk-contral="require">
                <label  class="col-xs-4">区域</label>
                <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="areaId" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    请选择区域
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu3">
                </ul>
            </div>
            <div class="form-group col-xs-6 site" kk-contral="require">
                <label  class="col-xs-4">工地</label>
                <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="constructionid" id="dropdownMenu4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    请选择工地
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu selectBox" aria-labelledby="dropdownMenu4">
                </ul>
            </div>

        </div>
        <div class="row add_list ">
            <div class="form-group col-xs-6 person queryCondition"  kk-contral="require">
                <label class=" col-xs-4">所属公司</label>
                <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="data-value" data-toggle="dropdown" aria-expanded="false"
                        data-value="" id="selectProjectCompany">
                    请选择所属公司
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu selectProjectCompany" style="min-width:108px;max-height: 250px;overflow: auto">
                </ul>
            </div>
        </div>
        <h2 class="add_title">
            <p>班组类别</p>
        </h2>
        <div class="personName check_result" kk-contral="require" style="margin-left: 10px">
            <label class="" style="display: none">班组类别</label>
            <i class="require-icon">*</i>
            <div  kk-prop="data-value" data-value="" style="width: 100%;">
               <%-- <i class="alert alert-info alert-dismissible check_result_btn" role="alert">
                    <span><label class="fa fa-plus"></label>点击选择班组类别</span>
                </i>--%>
               <div class="btn-group" role="group" style="width: 100%;margin-bottom: 20px;" aria-label="...">
                   <button type="button" class="btn btn-primary check_result_btn"><label class="fa fa-check-square-o"></label> 选择班组模板</button>
                   <button type="button" class="btn btn-info add_result_btn">新建班组类别 <label class="fa fa-plus"></label></button>
               </div>
            </div>
        </div>

        <div class="footer_btn"><a class="btn btn_submit btn-show-blue" id="team_btn">提交</a></div>
    </form>
</div>
 <%--警告--%>
<div class="alert alert-danger alert-dismissable error">
    <button type="button" class="close" aria-hidden="true">
        &times;
    </button>
    <span>警告！请不要提交。</span>
</div>
<%--加载--%>
<div class="my_load">
    <img src="${ctx}/resources/static/images/ajax-loader.gif" alt="">
</div>
<%--班组类别弹出层--%>
<div id="check_result_tree">
    <div id="itemsZtree" class="ztree">
        <p>请先选择所属公司</p>
    </div>
</div>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.excheck-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
<script type="text/javascript">
var ctx = "${ctx}",cr_data=[],job_data=[],userId="${userId}";

//提示框
$(".close").click(function(){
    $(".error").fadeOut("fast");
});

$(function(){
    $("#personform").kk_tableForm("#team_btn",my_ajax);
    check_result_btn();
    check_result();
    init(selectConstruction);
});

$(".personName").on('click','.close',function () {
    var jobData=[];
    $("#personform .alert.alert-warning>span").each(function () {
        jobData.push($(this).html());
    })
    if(jobData.length==1){
        $(".personName>div").attr("data-value","");
    }
})


/*关闭弹出框口*/
function layer_close(){
    var url = ctx + "/team/listData";
    parent.jQuery("#gridTable").jqGrid('setGridParam',{url:url}).trigger("reloadGrid");
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
};

function selectConstruction(constructionId) {
    $("#selectProjectCompany").attr("data-value", "").attr("title","请选择").html( "请选择"+"<span class=\"caret\"></span>");
    $.ajax({
        type: "post",
        url: ctx + "/team/getProjectCompanyList",
        data: {constructionId:constructionId},
        dataType: "json",
        success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
            if(data.status == 1){
                var divshow = $(".selectProjectCompany");
                divshow.empty();
                var arr = data.value;
                var ui = '';
                divshow.append(ui);
                if (arr && arr.length > 0) {
                    var dataArr = new Array();
                    if(arr.length==1){
                        $("#selectProjectCompany").attr("data-value", arr[0].id).attr("title", arr[0].name).html( arr[0].name+"<span class=\"caret\"></span>");
                    }
                    for (var i = 0; i < arr.length; i++) {
                        var id = arr[i].id;
                        var name = arr[i].name;
                        ui = "<li ><a data-value="+id+">"+name+"</a></li>";
                        divshow.append(ui);
                    }
                }
                $(".selectProjectCompany").parent().Bsselect();
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.alert(String(e));
        }
    });
}

//设置树形图
function ztreeSet(){
    return {
        view: {
            showLine: true,
            selectedMulti: true
        },
        check: {
            enable: true
        },
        callback: {
            onAsyncSuccess:function () {

            },
            onClick: function (e, treeId, treeNode, clickFlag) {
                $.fn.zTree.getZTreeObj("itemsZtree").checkNode(treeNode, !treeNode.checked, true);
            }
        }
    }
}
// 点击选择班组按钮
function check_result_btn() {
    $(".check_result_btn").on("click",function(){
        treeObj=$.fn.zTree.getZTreeObj("itemsZtree");
        treeObj.checkAllNodes(false);
        my_open=layer.open({
            type: 1,
            anim:5,
            title:'选择班组',
            content: $('#check_result_tree'),
            area: 'auto',
            btn:['确定'],
            btnAlign:'r',
            shade:false,
            yes:function () {
                if(!treeObj) {  layer.close(my_open); return}
                cr_data=[];
                job_data=[];
                var nodes = treeObj.getCheckedNodes(true);
                for(var i=0;i<nodes.length;i++){
                    if(!nodes[i].isParent){
                        cr_data.push({
                            name:nodes[i].name,
                            id:nodes[i].id
                        })
                        job_data.push({
                            teamName:nodes[i].name,
                            positionWorkType:nodes[i].id
                        })
                    }
                }
                add_cr(cr_data);
                if(cr_data.length>0){
                    $(".personName>div").attr("data-value",1);
                }
                layer.closeAll();
            }
        });
        $("#check_result_tree").show();
    })
}
//班组初始化
function cr_int() {
    cr_data=[];
    $(".personName>div").attr("data-value","");
    $(".alert-warning").remove();
}
//点击获取班组
function check_result() {
    $.ajax({
        type: "post",
        url: ctx + "/team/getDefaultTeamList",
        dataType: "json",
        success: function (data) {
            if(data.status == 1){
                if(data.value){
                    var cr_data=[];
                    for(var i in data.value){
                        cr_data[i]={
                            id:data.value[i].positionWorkType,
                            name:data.value[i].name
                        }
                    }
                    $.fn.zTree.init($("#itemsZtree"), ztreeSet(),cr_data);
                }
            } else {
                console.log(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            alert(e);
        }
    });
}
//生成班组
function add_cr(data) {
    var $list='';
    for(var i=0;i<data.length;i++){
        $list+='<i class="alert alert-warning alert-dismissible" role="alert">' +
            '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>' +
            '<span>'+data[i].name+'</span> </i>'
    }
    $(".personName>div").append($list);
    check_result_btn()
}

//编辑班组
;(function edit() {
    $(".personName").on('click','.alert.alert-warning>span',function(){
        if($(this).find("input").val()) return;
        var personNameVal=$(this).html();
        $(this).html('<input type="text" value="'+personNameVal+'">');
        $(this).find('input').select();
        $(".personName .alert.alert-warning>span input").blur(function(){
            var personNameVal=$(this).val();
            $(this).parent().html(personNameVal);
        })
    })
})();

//新建班组
$(".personName").on('click','.add_result_btn',function(){
    $(".personName>div").attr("data-value",1);
    $(".personName>div").append('<i class="alert alert-warning alert-dismissible" role="alert">' +
        '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>' +
        '<span>新建班组</span> </i>');
})

//提交
function getQueryJson() {
    var jobData=[];
    $("#personform .alert.alert-warning>span").each(function () {
        jobData.push($(this).html());
    });
    jobData=arr(jobData);
    var postPrams = {
        teamNames:jobData,
        constructionId:constructionId,
        projectCompanyId:$("#selectProjectCompany").attr("data-value")
    };
    return  JSON.stringify(postPrams);
}

//数组去重
function arr(arr) {
    var result=[];
    for(var i=0; i<arr.length; i++){
        if(result.indexOf(arr[i])==-1){
            result.push(arr[i])
        }
    }
    return result;
}

function my_ajax() {
    var url = ctx + "/team/batchAddTeam";
    var data = getQueryJson();
    $.ajax({
        url:url,                              //后台请求地址
        data:data,                            //自定义参数
        type:"post",                          //当要提交自定义参数时，这个参数要设置成post
        dataType: 'json',                     //服务器返回的数据类型
        contentType:'application/json',
        success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
            $(".my_load").hide();
            if(data.status == 1){
                layer.alert("新增班组成功！",function () {
                    layer_close();
                });
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            $(".my_load").hide();
            layer.alert(String(e));
        }
    });
}

</script>
</body>
</html>




