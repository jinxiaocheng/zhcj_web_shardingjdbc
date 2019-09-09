<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>施工安全分项检查-新增</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.min.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/scrollbar.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/dropdown.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/fileInput/fileinput.min.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/add.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/accident/add.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/biyue_skewing.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/tree.css?v=${v}">
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
            <ul class="dropdown-menu selectBox" aria-labelledby="dropdownMenu4">
            </ul>
        </div>
    </div>
    <div class="row add_list ">
        <div class="form-group col-xs-4 date_time" kk-contral="require">
            <label for="add_date" class="col-xs-4">检查日期</label>
            <input type="text" readonly class="form-control col-xs-8" kk-prop="value" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="add_date" placeholder="请选择日期" required>
        </div>
        <div class="form-group col-xs-4 username row" kk-contral="require" style="margin: 0" >
            <label for="add_person" class="col-xs-4">责任人</label>
            <input type="text" class="form-control col-xs-8 selectList" kk-prop="data-value"  placeholder="点击选择责任人"  id="add_person" readonly>
        </div>
        <div class="form-group col-xs-4 usertel">
            <label for="add_tel" class="col-xs-4">责任人电话</label>
            <input type="email" class="form-control col-xs-8 selectList" placeholder="点击选择责任人电话" id="add_tel"  readonly>
        </div>
    </div>

    <div class="row add_list">
        <div class="form-group col-xs-4 sub" >
            <label class="col-xs-4">所属公司</label>
            <input type="text" class="form-control col-xs-8 selectList"  placeholder="点击选择所属公司"  id="sub" readonly>
        </div>
        <div class="form-group col-xs-4 item" >
            <label  class="col-xs-4">班组</label>
            <input type="text" class="form-control col-xs-8 selectList" placeholder="点击选择班组"  id="item" readonly>
        </div>
    </div>

    <h2 class="add_title">
        <p>检查项及结果</p>
    </h2>
    <div class="row add_list biyue-skewing " kk-contral="require" style="margin: 0;padding: 0">
        <div class="col-xs-11 treeResult" style="width: 100%" kk-prop="data-value" data-value="">
            <label class="col-xs-1" style="display: none">检查项及结果</label>
            <i class="require-icon">*</i>
            <i class="alert alert-info alert-dismissible getSkewResult" role="alert">
                <span class=""><label class="fa fa-plus"></label>点击选择检查项及结果</span>
            </i>
            <div id="treeResult" class="ztree"  style="display: none;"></div>
            <div id="treeShowResult" class="ztree listBox" style="display: none;"></div>
        </div>
    </div>
    <div class="form-group all_block" style="width: 100%" >
        <textarea class="form-control " id="otherItems" style="width: 100%; " maxlength="500" placeholder="请输入检查项及结果,最多500字(可不填)" kk-RE="textLength/0-500" kk-name="检查项及结果"  rows="3"></textarea>
    </div>
    <h2 class="add_title">
        <p>检查结果</p>
    </h2>
    <div class="form-group all_block" style="width: 100%" >
        <textarea class="form-control " id="results" style="width: 100%; " maxlength="500" placeholder="最多输入500字" kk-RE="textLength/0-500" kk-name="检查结果"  rows="3"></textarea>
    </div>

    <h2 class="add_title">
        <p >备注</p>
    </h2>
    <div class="form-group all_block" style="width: 100%" >
        <textarea class="form-control remark" style="width: 100%" maxlength="500" placeholder="最多输入500字" kk-RE="textLength/0-500" kk-name="备注"  rows="3"></textarea>
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
            <textarea class="form-control" rows="3" maxlength="500" placeholder="最多输入500字" kk-prop="value" kk-RE="textLength/0-500" kk-name="整改意见"></textarea>
        </div>
    </div>
    <h2 class="add_title">
        <p>告知人</p>
    </h2>
    <div class=" all_block personName patient">
        <div style="width: 100%">
            <i class="alert alert-info alert-dismissible person_btn" role="alert">
                <span><label class="fa fa-plus"></label>点击选择人员</span>
            </i>
        </div>
    </div>
    <h2 class="add_title">
        <p>添加图片</p>
    </h2>
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

<%--检查项及结果弹出层--%>
<div id="check_result_tree">
    <div id="resultZtree" class="ztree">
        <p>请选择检查项</p>
    </div>
</div>

<script>
    var ctx = "${ctx}";
    var type = "${type}";
    var userId = "${userId}";
    var cr_treeObj="",
        node_data=[],//检查项及结果所有数据(选中数据)
        cr_data=[],//检查项及结果数据(原始数据)
        cr_id=[],//检查项及结果子级id(选中数据)
        my_open="",//检查项及结果弹窗对象
        kk_data1=[],kk_id1=[],kk_allData=[]; //告知人员数组(名称&id)
</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/fileInput/fileinput.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/fileInput/zh.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}" ></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.excheck-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/inspection/add.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biyue_skewing.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/selectList.js?v=${v}"></script>
<script>
    $(function(){
        init(function (id) {
            selectListInit();
            window.kk_allData=[];
            biyue_skewResult.getData(id);
            $(".patient .alert-warning").remove();
        });//获取区域与工地
        biyue_skewResult.openWin();
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
                area: ["60%","80%"],
                btn:['确定'],
                yes:function(){
                    add_cr(check_result_submit());//生成检查项及结果

                }
            });
            $("#check_result_tree").show();
        })
    }
    //告知人选择按钮事件
    function patient_layui() {
        $(".patient .person_btn").on("click",function(){
            var url = ctx + '/accidentReport/toPersonList?constructionId='+constructionId;
            var title = '告知人选择';
            layer_showOdd(title, url, {w:'900px',h:'480px'});
            window.kk_allData=kk_allData;
            window.my_type=2;//控制传输类型0：受伤，1：死亡,2:全部数据
        });
        //点击删除控制数组
        $(".patient.personName .close").click(function(){
            var kk_id=$(this).attr("kk-id");
            delete kk_allData[kk_id]
        })
    }
    patient_layui();
    //告知人
    function add_patient(allData) {
        kk_allData=$.extend(true,{},allData);
        var $list=" <i class=\"alert alert-info alert-dismissible person_btn\" role=\"alert\">\n" +
            "                    <span><label class=\"fa fa-plus\"></label>点击选择人员</span>\n" +
            "                </i>";
        for(var i in kk_allData){
            $list+='<i class="alert alert-warning alert-dismissible" role="alert">'
                +'<button type="button" class="close" data-dismiss="alert" aria-label="Close" kk-id="'+kk_allData[i].id+'"><span aria-hidden="true">&times;</span></button>'
                +'<span>'+kk_allData[i].name+'</span> </i>'
        }
        $(".patient.personName>div").html($list);
        patient_layui();
    }
    window.add_patient=add_patient;
    //交互
    function my_safetyAjax() {
        var add_time=$(".date_time>input").val(),//获取时间
            add_es=$(".es>button>label").attr("k_id"),//获取工程id
            add_sub=$("#sub").attr("data-value"),//分包公司
            add_item=$("#item").attr("data-value"),//班组
            add_un= $("#add_person").attr("data-value"),//责任人id
            add_ut=$("#add_tel").val(),//联系电话
            add_re=$(".result ul").attr("kk-result"),//处理意见
            add_ab_time=$("#Abarbeitung_add_date").val(),//整改完成时间
            add_ad_status="",//整改状态
            add_ab_ch=$(".Abarbeitung_check_result textarea").val(),//整改意见
            add_remark=$(".remark").val(),//备注
            add_constructionName=$(".site button").text(),
            add_results=$("#results").val(),
            add_otherItems=$("#otherItems").val(),
            add_contactName=$("#add_person").val(),
            files=$('#file').fileinput('getFileStack');//得到文件
        var formData = new FormData($("#form_data")[0]);
        formData.append("constructionName",add_constructionName);
        formData.append("constructionId",constructionId);
        formData.append("type",type);
        formData.append("otherItems",add_otherItems);
        formData.append("createBy",userId);
        formData.append("inspectDate",add_time);
        formData.append("results",add_results);
       // 检查项数据
        var skewResult=biyue_skewResult.getSkewing();
        formData.append("itemsVOList",JSON.stringify(skewResult.data));
        formData.append("items",skewResult.items);

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
        formData.append("remark",add_remark)
        if(Object.keys(kk_allData).length>0){
        	var person_allData=[]
        	for(var i in kk_allData){
        		person_allData.push(kk_allData[i])
        	}
            formData.append("notifyEntityList",JSON.stringify(person_allData))
        }
        //return
        $.ajax({
            url: ctx+"/inspection/save",                              //后台请求地址
            data: formData,                            //自定义参数
            processData: false,
            contentType: false,
            type: "post",                         //当要提交自定义参数时，这个参数要设置成post
            dataType: 'json',                   //服务器返回的数据类型
            success: function (data) {            //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                if (data.status == 1) {
                    layer.alert("提交成功！",function(){
                        biyue.layui_close();
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

</script>
</body>
</html>




