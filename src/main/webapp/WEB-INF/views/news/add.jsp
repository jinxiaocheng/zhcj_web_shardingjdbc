<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>资讯录入</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.min.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/validform.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/tableform.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/add.css?v=${v}">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
    <script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}" type="text/javascript"></script>
</head>

<body class="body_bottom">

<div class="form-table">
    <form id="newsform" enctype="multipart/form-data" >
        <div class="row ">
            <div class="form-group col-xs-5 usertel" kk-contral="require">
                <label class="col-xs-4">资讯名称</label>
                <input type="text" class="form-control col-xs-8" kk-prop="value" id="title" placeholder="请输入资讯名称">
            </div>
        </div>
        <div class="row ">
            <div class=" form-group col-xs-5 es queryCondition"  >
                <label class=" col-xs-4">类型</label>
                <button class="btn btn-default dropdown-toggle col-xs-8"  data-toggle="dropdown" aria-expanded="false"
                        data-value="" id="type">
                    --请选择--
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" style="min-width:108px;max-height: 250px;overflow: auto">
                    <li ><a data-value="">--请选择--</a></li>
                    <li ><a data-value="3">城乡建设</a></li>
                    <li ><a data-value="4">海绵城市</a></li>
                    <li ><a data-value="5">综合管廊</a></li>
                    <li ><a data-value="6">BIM</a></li>

                </ul>
            </div>
        </div>
        <div class="row  ">
            <div class="form-group col-xs-5 usertel">
                <label class="col-xs-4">新闻链接</label>
                <input type="text" class="form-control col-xs-8" kk-prop="value" id="link" placeholder="请输入新闻链接">
            </div>
        </div>
        <div class="row  ">
            <div class="form-group row all_block check_item" id="" style="margin-top: 0">
                <label class="col-xs-4" style="width: 12%;text-indent: 1em">内容</label>
                <textarea class="form-control" rows="3" style="width: 88%" id="content"></textarea>
            </div>
        </div>
        <div class="row  ">
            <label class="col-xs-4">上传文件：</label>
            <input id="multipartFile" type="file" name="multipartFile" >
        </div>
        <div class="footer_btn"><a class="btn btn_submit btn-show-blue" id="news_btn">提交</a></div>  
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
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
<script type="text/javascript">
var ctx = "${ctx}";
$(function(){
    $("#newsform").kk_tableForm("#news_btn",my_ajax);

    function my_ajax() {
        var url = ctx + "/news/add";
        var data = getQueryJson();
        $.ajax({
            url:url,                              //后台请求地址
            data:data,                            //自定义参数
            processData: false,
            contentType: false,
            type:"post",                          //当要提交自定义参数时，这个参数要设置成post
            dataType: 'json',                     //服务器返回的数据类型
            success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                if(data.status == 1){
                    layer.alert("新增资讯成功！",function(){
                        layer_close();
                    });
                } else {
                    layer.alert(data.msg);
                }
                $(".my_load").hide();
            },
            error: function (data, status, e) {   //提交失败自动执行的处理函数
                layer.alert(String(e));
                $(".my_load").hide();
            }
        });
    }

    /*关闭弹出框口*/
    function layer_close(){
        var url = ctx + "/news/newsList"; 
    	parent.jQuery("#gridTable").jqGrid('setGridParam',{url:url}).trigger("reloadGrid");
    	var index = parent.layer.getFrameIndex(window.name);
    	parent.layer.close(index);
    };
});
		
function getQueryJson() {
    var formData = new FormData();
    if($('#multipartFile')[0].files[0]) formData.append('multipartFile', $('#multipartFile')[0].files[0]);
	formData.append('title',$("#title").val());
	if($("#type").attr("data-value")) formData.append('type',$("#type").attr("data-value"));
    if($("#content").val()) formData.append('content',$("#content").val());
    if($("#link").val()) formData.append('link',$("#link").val());
    return formData;
};		
</script>
</body>
</html>




