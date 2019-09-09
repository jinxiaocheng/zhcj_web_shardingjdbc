<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>安全培训-新增</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.min.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/scrollbar.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/dropdown.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/fileInput/fileinput.min.css?v=${v}">
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
        <div class="form-group col-xs-4 theme" kk-contral="require">
            <label class="col-xs-4">主题</label>
            <input type = "text" class="form-control col-xs-8" id="theme" kk-prop="value" placeholder="请填写" rows="2"/>
        </div>
        <div class="form-group col-xs-4 start_date" kk-contral="require">
            <label class="col-xs-4">开始时间</label>
            <input type="text" class="form-control col-xs-8" kk-prop="value" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="start_date" placeholder="请选择日期" required>
        </div>
        <div class="form-group col-xs-4 end_date" kk-contral="require">
            <label  class="col-xs-4">结束时间</label>
            <input type="text" class="form-control col-xs-8" kk-prop="value" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="end_date" placeholder="请选择日期" required>
        </div>
    </div>
    <div class="row add_list">
        <c:if test="${type == 1}">
            <div class="form-group col-xs-4 contact_person" kk-contral="require">
                <label class="col-xs-4">讲师</label>
                <input type="text" class="form-control col-xs-8" kk-prop="value"  id="contact_person" placeholder="请填写" required>
            </div>
        </c:if>
        <div class="form-group col-xs-4 place" kk-contral="require">
            <label class="col-xs-4"><c:if test="${type == 1}">培训地点</c:if><c:if test="${type == 2}">演习地点</c:if>
            </label>
            <input type="text" class="form-control col-xs-8"  kk-prop="value" id="place" placeholder="请填写" required>
        </div>
    </div>
    <h2 class="add_title">
        <p>添加人员</p>
    </h2>
    <div class="personName patient" style="width: 100%">
        <label class="col-xs-2" style="display: none">添加人员</label>
        <div class="" style="width: 100%">
            <i class="alert alert-info alert-dismissible person_btn" role="alert">
                <span><label class="fa fa-plus"></label>点击选择人员</span>
            </i>
        </div>
    </div>

    <h2 class="add_title">
        <p>添加附件</p>
    </h2>
    <div class="form-group submit_img">
        <input id="file" type="file" class="file" data-upload-url="#" multiple>
    </div>

    <h2 class="add_title">
        <p>备注</p>
    </h2>
    <div class="form-group all_block check_item" style="width: 100%">
        <textarea class="form-control" style="width: 100%" id = "remark" rows="3" maxlength="500" placeholder="请填写备注(最多输入500字)"  kk-RE="textLength/0-500" kk-name="备注"></textarea>
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
    var userId = "${userId}"
    var kk_data1=[],kk_id1=[],//添加人员数组
        kk_data2=[],kk_id2=[];//死亡人员数组
    var files = [];   //上传附件数组
</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/fileInput/fileinput.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/fileInput/zh.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.excheck-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/popup.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/safeStudy/add.js?v=${v}"></script>
<script>
    $(function(){
        $("#form_data").kk_tableForm("#submit",my_ajax);
        function id(a) {
        }
        init(id);
        //点击关闭
        $(".delClose").on("click", function () {
            layer_close();
        });
    });
    //受伤选择按钮事件
    function patient_layui() {
        $(".patient .person_btn").on("click",function(){
            var url = ctx + '/accidentReport/toPersonList?constructionId=' + constructionId;
            var title = '人员选择';
            layer_showOdd(title, url, {w:'900px',h:'480px'});
            window.kk_id1=kk_id1;
            window.kk_data1=kk_data1;
            window.kk_id2=kk_id2;
            window.kk_data2=kk_data2;
            window.my_type=0;//控制传输类型0：受伤，1：死亡
        });
        //点击删除控制数组
        $(".patient.personName .close").click(function(){
            var index=$(this).parent().index()-1;
            kk_data1.splice(index,1);
            kk_id1.splice(index,1);
        })
    }
    patient_layui();
    //受伤人员
    function add_patient(data,id) {
        kk_data1=data;
        kk_id1=id;
        var $list=" <i class=\"alert alert-info alert-dismissible person_btn\" role=\"alert\">\n" +
            "                    <span><label class=\"fa fa-plus\"></label>点击选择人员</span>\n" +
            "                </i>";
        for(var i=0;i<data.length;i++){
            $list+='<i class="alert alert-warning alert-dismissible" role="alert">'
                +'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
                +'<span>'+data[i]+'</span> </i>'
        }
        $(".patient.personName>div").html($list);
        patient_layui();
    }
    window.add_patient=add_patient;
    //死亡选择按钮事件
    function defunct_layui() {}
    defunct_layui();
    //受伤人员
    function add_defunct(data,id){}
    window.add_defunct=add_defunct;
    /*关闭弹出框口*/
    function layer_close() {
        biyue.layui_close();
    }
</script>
</body>
</html>




