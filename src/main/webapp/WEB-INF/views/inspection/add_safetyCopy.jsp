<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>安全检查-新增</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.min.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/scrollbar.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/dropdown.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/fileInput/fileinput.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/add.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/accident/add.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/ieControl.css?v=${v}">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
</head>
<body>
<form class="form-inline add_form" id="form_data">
    <h2 class="add_title">
        <p>基本信息</p>
    </h2>
    <div class="row add_list ">
        <div class="form-group col-xs-4 area" kk-contral="require">
            <label  class="col-xs-4">区域</label>
            <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="areaId" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                请选择区域
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu3">
            </ul>
        </div>
        <div class="form-group col-xs-4 site" kk-contral="require">
            <label  class="col-xs-4">工地</label>
            <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="constructionid" id="dropdownMenu4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                请选择工地
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu4">
            </ul>
        </div>
    </div>

    <div class="row add_list">
        <div class="form-group col-xs-4 date_time" kk-contral="require">
            <label for="add_date" class="col-xs-4">检查日期</label>
            <input type="text" readonly class="form-control col-xs-8" kk-prop="value" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="add_date" placeholder="请选择日期" required>
        </div>
        <div class="form-group col-xs-4 es" >
            <label class="col-xs-4">检查部位</label>
            <button onclick="showMenu(event); return false;" type="button"  class=" col-xs-8 btn btn-default dropdown-toggle" type="button" id="construction" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                <label>请选择检查部位</label>
                <span class="caret"></span>
            </button>
            <ul id="tree" class="k_dropdown ztree" aria-labelledby="dropdownMenu1">
            </ul>
        </div>
        <div class="form-group col-xs-4 sub" kk-contral="require">
            <label class="col-xs-4">所属公司</label>
            <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="sub_id" type="button" id="company" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                请选择公司名称
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
            </ul>
        </div>
    </div>

    <div class="row add_list ">
        <div class="form-group col-xs-4 item" >
            <label  class="col-xs-4">班组</label>
            <button class="btn btn-default dropdown-toggle col-xs-8" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                请选择班组
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
            </ul>
        </div>
        <div class="form-group col-xs-4 username row" style="margin: 0" >
            <label for="add_person" class="col-xs-4">责任人</label>
            <input type="text" class="form-control col-xs-8"  id="add_person" readonly>
        </div>
        <div class="form-group col-xs-4 usertel">
            <label for="add_tel" class="col-xs-4">责任人电话</label>
            <input type="email" class="form-control col-xs-8" id="add_tel"  readonly>
        </div>
    </div>

    <h2 class="add_title">
        <p>检查信息</p>
    </h2>
    <div class="form-group col-xs-4 row queryCondition" kk-contral="require" >
        <label class=" col-xs-4" style="margin-left: -12px">检查项目</label>
        <button style="margin-left: -1px" class="btn btn-default dropdown-toggle col-xs-7" kk-prop="data-value" data-toggle="dropdown" aria-expanded="false"
                data-value="" id="check_item">
            --请选择--
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu check_item" style="min-width:108px;max-height:250px;overflow:auto;margin-left: -11px">

        </ul>
    </div><br/>
    <div class="personName row check_result" kk-contral="require" style="margin-top: 34px">
        <label class="col-xs-2">检查项及结果</label>
        <div class="col-xs-10" kk-prop="data-value" data-value="">
            <i class="alert alert-info alert-dismissible check_result_btn" role="alert">
                <span><label class="fa fa-plus"></label>点击选择检查项及结果</span>
            </i>
        </div>
    </div>
    <div class="result form-group row" kk-contral="require">
        <label >处理意见</label>
        <ul kk-result="" kk-prop="kk-result">
            <li r-id="1">通过</li>
            <li r-id="2">警告</li>
            <li r-id="3">整改</li>
        </ul>
    </div>
    <div class="Abarbeitung">
        <div class="form-group Abarbeitung_time" kk-contral="">
            <label for="Abarbeitung_add_date" class="col-xs-5">限期整改时间</label>
            <input type="text" readonly class="form-control col-xs-7" kk-prop="value" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="Abarbeitung_add_date" placeholder="请选择日期" required>
        </div>
        <div class="form-group row all_block Abarbeitung_check_result" kk-contral="">
            <label >整改意见</label>
            <textarea class="form-control" rows="3" kk-prop="value" maxlength="500" placeholder="请填写整改意见(最多输入500字)"  kk-RE="textLength/0-500" kk-name="整改意见"></textarea>
        </div>
    </div>
    <div class="form-group submit_img">
        <input id="file" type="file" class="file" data-upload-url="#" multiple>
    </div>


</form>
<div style="height: 60px;width: 100%"></div>
<footer>
    <a class="btn btn_submit btn-show-blue" id="submit">提&nbsp;交</a>
</footer>
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
<script>
    var ctx = "${ctx}";
    var type = "${type}";
    var userId = "${userId}";
    var cr_treeObj="",cr_data=[],my_open="",cr_load="";
</script>
<%--检查项及结果弹出层--%>
<div id="check_result_tree">
    <div id="resultZtree" class="ztree">
        <p>暂无检查项及结果</p>
    </div>
    <a class="btn btn_submit btn-show-blue" id="check_result_submit">确&nbsp;定</a>
</div>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}" ></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.excheck-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/inspection/add.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/fileInput/fileinput.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/fileInput/fileinput_locale_de.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/selectList.js?v=${v}"></script>
<script>

    $(function(){
        init();
        check_item();
        check_result_btn();
        $("#form_data").kk_tableForm("#submit",my_safetyAjax);
        //获取当前日期
        var my_date=new Date();
        var my_month=(my_date.getMonth()+1)<10?"0"+(my_date.getMonth()+1):(my_date.getMonth()+1);
        var my_day=my_date.getDate()<10?"0"+my_date.getDate():my_date.getDate();
        $("#add_date").val(my_date.getFullYear()+"-"+my_month+"-"+my_day);
        //点击关闭
        $(".delClose").on("click", function () {
            layer_close();
        });
    });
    //获取检查项目
    function check_item() {
        $.ajax({
            type: "post",
            url: ctx + '/inspection/querySafetyCheckItems',
            success: function (data) {
                if(data.status == 1){
                    if(data.value){
                        ci_lsit(data.value);
          /*              search(data.value);*/
                        check_result();
                        $(".queryCondition").each(function () {
                            $(this).Bsselect();
                        });
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
    function ci_lsit(data) {
        var $list='';
        for(var i in data){
            $list+='<li ><a data-value="'+data[i].id+'">'+data[i].name+'</a></li>'
        }
        $(".check_item").html($list);
    }
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
    //检查项及结果初始化
    function cr_int() {
        cr_data=[];
        $(".personName>div").attr("data-value","");
        $(".alert-warning").remove();
    }
    //点击获取检查项及结果
    function check_result() {
        $(".check_item li a").on("click",function(){
            var itemsId=$(this).attr("data-value")||null;
            var my_json={
                itemsId:itemsId
            };
            cr_int();
            $.ajax({
                type: "post",
                data:  JSON.stringify(my_json),
                contentType: "application/json",
                url: ctx + '/inspection/querySafetyCheckResults',
                success: function (data) {
                    if(data.status == 1){
                        if(data.value){
                           var cr_data=[];
                            for(var i in data.value){
                                cr_data[i]={
                                    children:data.value[i].data,
                                    id:data.value[i].id,
                                    name:data.value[i].name
                                }
                            }
                           $.fn.zTree.init($("#resultZtree"), ztreeSet(),cr_data);
                        }
                    } else {
                        layer.alert(data.msg);
                    }
                },
                error: function (data, status, e) {   //提交失败自动执行的处理函数
                    layer.alert(String(e));
                }
            });
        })
    }
    //生成检查项及结果
    function add_cr(data) {
        var $list=' <i class="alert alert-info alert-dismissible check_result_btn" role="alert">\n' +
            '                <span><label class="fa fa-plus"></label>点击选择检查项及结果</span>\n' +
            '            </i>';
        for(var i=0;i<data.length;i++){
            $list+='<i class="alert alert-warning alert-dismissible" role="alert">'
                +'<span>'+data[i].name+'</span> </i>'
        }
        $(".personName>div").html($list);
        check_result_btn()
    }
    //点击确定选中的结果
    $("#check_result_submit").on("click",function(){
        treeObj=$.fn.zTree.getZTreeObj("resultZtree");
        if(!treeObj) {    layer.close(my_open); return}
        cr_data=[];
        var nodes = treeObj.getCheckedNodes(true);
        for(var i=0;i<nodes.length;i++){
            if(!nodes[i].isParent){
                cr_data.push({
                    name:nodes[i].name,
                    id:nodes[i].id
                })
            }
        }
        add_cr(cr_data);
        if(cr_data.length>0){
            $(".personName>div").attr("data-value",1);
        }else{
            $(".personName>div").attr("data-value","");
        }
        layer.close(my_open);
    })
    /*关闭弹出框口*/
    function layer_close() {
        var url = ctx + "/inspection/queryByConstructionId";
        parent.jQuery("#gridTable").jqGrid('setGridParam', {url: url}).trigger("reloadGrid");
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
//    点击检查项及结果按钮
    function check_result_btn() {
        $(".check_result_btn").on("click",function(){
            my_open=layer.open({
                type: 1,
                anim:5,
                title:'检查项及结果',
                content: $('#check_result_tree'),
                area: 'auto'
            });
            $("#check_result_tree").show();
        })
    }
    //交互
        function my_safetyAjax() {
            var add_time=$(".date_time>input").val(),//获取时间
                add_es=$(".es>button>label").attr("k_id"),//获取工程id
                add_sub=$(".sub>button").attr("sub_id"),//分包公司
                add_item=$(".item>button").attr("item_id"),//班组
                add_un= $(".username").attr("username_id"),//联系人id
                add_ut=$(".usertel>input").val(),//联系电话
                add_ci=$("#check_item").text(),//检查项目
                add_ci_id=$("#check_item").attr("data-value"),//检查项目id
                add_cr=JSON.stringify(cr_data),//检查项及结果
                add_re=$(".result ul").attr("kk-result"),//处理意见
                add_ab_time=$("#Abarbeitung_add_date").val(),//整改完成时间
                add_ad_status="",//整改状态
                add_ab_ch=$(".Abarbeitung_check_result textarea").val();//整改意见
        var formData = new FormData($("#form_data")[0]);
        formData.append("constructionId",constructionId)
        formData.append("type",type)
        formData.append("inspectDate",add_time)
        formData.append("itemsId",add_ci_id)
        formData.append("items",add_ci)
        formData.append("resultsList",add_cr)
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
        $(".fileinput-upload-button").click();
        for(var i=0;i<files.length;i++){
            formData.append("files",files[i]);
        }
        if(add_re == 3) {
            formData.append("correctiveStatus",add_ad_status)
            formData.append("correctiveRequest",add_ab_ch)
            formData.append("correctiveCompletionDate",add_ab_time)
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
                    layer.alert("提交成功！",function(){layer_close();});

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

</script>
</body>
</html>




