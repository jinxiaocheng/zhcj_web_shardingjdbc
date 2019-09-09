<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>质量事故报告添加</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet"/>
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.min.css?v=${v}" rel="stylesheet"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/scrollbar.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/dropdown.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/fileInput/fileinput.min.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/add.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/accident/add.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/biyue_skewing.css?v=${v}">
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
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu4">
            </ul>
        </div>
    </div>
    <div class="row add_list ">
        <div class="form-group col-xs-4 date_time" kk-contral="require">
            <label for="add_date" class="col-xs-4">事故时间</label>
            <input type="text" readonly class="form-control col-xs-8" kk-prop="value" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="add_date" placeholder="请选择日期" required>
        </div>
        <div class="form-group col-xs-4 username row" kk-contral="require" style="margin: 0" >
            <label for="add_person" class="col-xs-4">责任人</label>
            <input type="text" class="form-control col-xs-8 selectList" kk-prop="data-value" placeholder="点击选择责任人"  id="add_person" readonly>
        </div>
        <div class="form-group col-xs-4 usertel">
            <label for="add_tel" class="col-xs-4">责任人电话</label>
            <input type="email" class="form-control col-xs-8 selectList" placeholder="点击选择责任人电话" id="add_tel"  readonly>
        </div>
    </div>
    <div class="row add_list">

        <div class="form-group col-xs-4 sub" >
            <label class="col-xs-4">所属公司</label>
            <input type="text" class="form-control col-xs-8 selectList" placeholder="点击选择所属公司"  id="sub" readonly>
        </div>
        <div class="form-group col-xs-4 item" >
            <label  class="col-xs-4">班组</label>
            <input type="text" class="form-control col-xs-8 selectList" placeholder="点击选择班组"  id="item" readonly>
        </div>
    </div>
    <h2 class="add_title">
        <p>检查部位</p>
    </h2>
    <div class="row add_list biyue-skewing ">
        <div class="col-xs-11" style="width: 100%" kk-prop="data-value" data-value="">
            <i class="alert alert-info alert-dismissible getSkewing" role="alert">
                <span class=""><label class="fa fa-plus"></label>点击选择检查部位</span>
            </i>
            <div id="tree" class="ztree"></div>
            <div id="treeShow" class="ztree listBox" style="display: none;"></div>
        </div>
    </div>

    <h2 class="add_title">
        <p>事故简述</p>
    </h2>
    <div class="form-group all_block check_item" kk-contral="require">
        <label  style="display: none">事故简述</label>
        <i class="require-icon">*</i>
        <textarea class="form-control col-xs-9" style="width: 100%" rows="3" maxlength="500" placeholder="请输入事故简述(最多输入500字)"  kk-RE="textLength/0-500" kk-name="事故简述" kk-prop="value"></textarea>
    </div>

    <h2 class="add_title">
        <p>初步处理意见</p>
    </h2>
    <div class="form-group all_block check_result" kk-contral="require">
        <label  style="display: none">初步处理意见</label>
        <i class="require-icon">*</i>
        <textarea class="form-control" style="width: 100%" rows="3" placeholder="请输入初步处理意见(最多输入500字)"  kk-RE="textLength/0-500" kk-name="初步处理意见"  kk-prop="value"></textarea>
    </div>

    <c:if test="${type == 2}">
        <h2 class="add_title">
            <p>受伤人员</p>
        </h2>
        <div class="personName patient">
            <div style="width: 100%">
                <i class="alert alert-info alert-dismissible person_btn" role="alert">
                    <span><label class="fa fa-plus"></label>点击选择人员</span>
                </i>
            </div>
        </div>
        <h2 class="add_title">
            <p>死亡人员</p>
        </h2>
        <div class="personName defunct">
            <div style="width: 100%">
                <i class="alert alert-info alert-dismissible person_btn" role="alert">
                    <span><label class="fa fa-plus"></label>点击选择人员</span>
                </i>
            </div>
        </div>
        <div class="row personName ">

        </div>
    </c:if>

    <div class="result form-group row" kk-contral="require">
        <label>事故等级</label>
        <ul kk-prop="kk-result" kk-result="">
            <li r-id="1">一般</li>
            <li r-id="2">较大</li>
            <li r-id="3">重大</li>
            <li r-id="4">特别重大</li>
        </ul>
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
    var kk_data1=[],kk_id1=[], //受伤人员数组
        kk_data2=[],kk_id2=[];//死亡人员数组

</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}" ></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/fileInput/fileinput.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/fileInput/zh.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.excheck-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/accident/add.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/popup.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biyue_skewing.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/selectList.js?v=${v}"></script>
<script>
    $(function () {
        init(function (id) {
            selectListInit();
            biyue_skewing.getData(id);
        });
        biyue_skewing.openWin();
        //获取当前日期
        var my_date=new Date();
        var my_month=(my_date.getMonth()+1)<10?"0"+(my_date.getMonth()+1):(my_date.getMonth()+1);
        var my_day=my_date.getDate()<10?"0"+my_date.getDate():my_date.getDate();
        $("#form_data").kk_tableForm("#submit",my_ajax);
        $("#add_date").val(my_date.getFullYear()+"-"+my_month+"-"+my_day)

    });

    /*关闭弹出框口*/
    function layer_close() {
        biyue.layui_close();
    }

    //受伤选择按钮事件
    function patient_layui() {
        $(".patient .person_btn").on("click",function(){
            var url = ctx + '/accidentReport/toPersonList?constructionId='+constructionId;
            var title = '受伤人员选择';
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
        kk_data1=""+data+"";
        kk_data1=kk_data1.split(",");
        kk_id1=""+id+"".split(",");
        kk_id1=kk_id1.split(",");
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
    function defunct_layui() {
        $(".defunct .person_btn").on("click",function(){
            var url = ctx + '/accidentReport/toPersonList?constructionId=' + constructionId;
            var title = '死亡人员选择';
            layer_showOdd(title, url, {w:'900px',h:'480px'});
            window.kk_id1=kk_id1;
            window.kk_data1=kk_data1;
            window.kk_id2=kk_id2;
            window.kk_data2=kk_data2;
            window.my_type=1;//控制传输类型0：受伤，1：死亡
        });
        //点击删除控制数组
        $(".defunct.personName .close").click(function(){
            var index=$(this).parent().index()-1;
            kk_data2.splice(index,1);
            kk_id2.splice(index,1);
        })
    }
    defunct_layui();
    //受伤人员
    function add_defunct(data,id){
        kk_data2=""+data+"";
        kk_data2=kk_data2.split(",");
        kk_id2=""+id+"";
        kk_id2=kk_id2.split(",");
        var $list=" <i class=\"alert alert-info alert-dismissible person_btn\" role=\"alert\">\n" +
            "                    <span><label class=\"fa fa-plus\"></label>点击选择人员</span>\n" +
            "                </i>";
        for(var i=0;i<data.length;i++){
            $list+='<i class="alert alert-warning alert-dismissible" role="alert">'
                +'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
                +'<span>'+data[i]+'</span> </i>'
        }
        $(".defunct.personName>div").html($list);
        defunct_layui();
    }
    window.add_defunct=add_defunct;


</script>
</body>
</html>




