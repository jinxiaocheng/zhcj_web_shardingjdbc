<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>添加材料进场</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.min.css?v=${v}" rel="stylesheet"/>
    <link type="text/css" href="${ctx}/resources/static/css/validform.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/tableform.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/add.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/fileInput/fileinput.min.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/ieControl.css?v=${v}">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
    <script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}" type="text/javascript"></script>
</head>

<body class="body_bottom">

<div class="table-responsive form-table">
    <form id="materialApproachForm"  action="javascript:void(0)">
        <div class="row add_list " >
            <div class="form-group col-xs-4 area " kk-contral="require">
                <label class=" col-xs-4">区域</label>
                <button class="btn btn-default dropdown-toggle col-xs-8 " kk-prop="areaId"  id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    请选择区域
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu3">
                </ul>
            </div>
            <div class="form-group col-xs-4 site" kk-contral="require">
                <label class=" col-xs-4">工地</label>
                <button class="btn btn-default dropdown-toggle col-xs-8" kk-prop="constructionid"  id="dropdownMenu4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    请选择工地
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu4">
                </ul>
            </div>

        </div>

        <div class="row add_list  " >
            <div class="form-group col-xs-4 " kk-contral="require">
                <label class=" col-xs-4">进场日期</label>
                <input  type="text" class="form-control inputxt col-xs-8" kk-prop="value" kk-time="day" id="approachDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  placeholder="请选择日期" readonly />
            </div>
            <div class="form-group col-xs-4 ">
                <label class=" col-xs-4">进场数量</label>
                <input  type="number" kk-prop="value"  class="col-xs-8 form-control inputxt " id='quantity'  />
                <input  type="text" class="col-xs-8 form-control inputxt " id='weighbridgeId' readonly style="display: none"/>
            </div>
            <div class="form-group col-xs-4 " kk-contral="require">
                <label class=" col-xs-4">材料名称</label>
                <input  type="text" kk-prop="value" class="col-xs-8 form-control inputxt getMaterial" placeholder="点击选择材料名称" id='selectMaterial' readonly/>
            </div>
        </div>
        <div class="row add_list ">
            <div class="form-group col-xs-4 " >
                <label class=" col-xs-4">型号</label>
                <input  type="text"  class="col-xs-8 form-control inputxt " id='modelName' readonly/>
            </div>
            <div class="form-group col-xs-4 ">
                <label class=" col-xs-4">单位</label>
                <input  type="text" class="col-xs-8 form-control inputxt " id='unit' readonly/>
            </div>

            <div class="form-group col-xs-4 " kk-contral="require">
                <label class=" col-xs-4">生产厂家</label>
                <input  type="text" kk-prop="value" class="col-xs-8 form-control inputxt " id='manufacturer'/>
            </div>


        </div>
        <div class="row add_list ">
            <div class="form-group col-xs-4 " >
                <label class=" col-xs-4">使用部位</label>
                <input  type="text" class="col-xs-8 form-control inputxt " id='usePosition'/>
            </div>
            <div class="form-group col-xs-4 ">
                <label class=" col-xs-4">报告编号</label>
                <input  type="number" kk-condition="num/5-10" class="col-xs-8 form-control inputxt " id='reportNum'/>
            </div>

        </div>
        <h2 class="add_title">
            <p>试验结果</p>
        </h2>
        <div class="row add_list ">
            <div class="form-group col-xs-4 "  style="margin: 0" >
                <label  class="col-xs-5">现场验证结果</label>
                <span style="padding-left: 10px">
                    <label class="radio-inline">
                        <input type="radio" name="siteResult" kk-prop="value" value=1  /> 合格
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="siteResult" value=2 checked="checked"> 不合格
                    </label>
                </span>
            </div>
            <div class="form-group col-xs-4 " style="margin: 0" >
                <label  class="col-xs-4">试验结果</label>
                <span style="padding-left: 10px">
                    <label class="radio-inline">
                        <input type="radio" name="experimentResult" kk-prop="value" value=1 /> 合格
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="experimentResult" value=2 checked="checked"> 不合格
                    </label>
                </span>
            </div>
        </div>
        <h2 class="add_title">
            <p >备注</p>
        </h2>
        <div class="form-group all_block" style="width: 100%" >
            <textarea class="form-control remark" style="width: 100%" maxlength="500" placeholder="最多输入500字" kk-RE="textLength/0-500" kk-name="备注"  rows="4" id='remark'></textarea>
        </div>
        <h2 class="add_title">
            <p >上传附件</p>
        </h2>
         <div class="form-group submit_img">
	        <input id="file" type="file" class="file" data-upload-url="#" multiple>
	    </div>
        <div class="footer_btn"><a class="btn btn_submit btn-show-blue" id="materialApproachform_btn">提交</a></div>

    </form>
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
</div>
<script>
    var ctx = "${ctx}";
    var userId= "${userId}";
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
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script  type="text/javascript" src="${ctx}/resources/static/js/common/selectList.js?v=${v}"></script>
<script  type="text/javascript" src="${ctx}/resources/static/js/common/area.js?v=${v}"></script>
<script  type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script  type="text/javascript" src="${ctx}/resources/static/js/material/addMaterialApproach.js?v=${v}"></script>
<script type="text/javascript">
    $(function(){
        var type = biyue.urlSearch().type;
        if(type!=="view"){
            createFile([]);
            init(function (id) {});
            $("#materialApproachForm").nowTime(); //加入当前时间
        }else{
            material.getData();
        }
        $("#materialApproachForm").kk_tableForm("#materialApproachform_btn",my_ajax);

        /*关闭弹出框口*/
        function layer_close(){
            biyue.layui_close();
        };
    });
</script>
</body>
</html>

