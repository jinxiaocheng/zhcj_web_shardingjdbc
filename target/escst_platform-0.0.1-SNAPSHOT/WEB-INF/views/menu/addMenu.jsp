<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>菜单录入</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/validform.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/tableform.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/add.css?v=${v}">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
    <script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}" type="text/javascript"></script>
    <style>
        .require:before {
            left: -9px;
            top: 2px;
        }
    </style>
</head>

<body class="body_bottom">

<div class="table-responsive form-table">
    <form id="personform2">
        <table class="table">
            <tbody>
            <tr kk-contral="require">
                <td class="formTitle"><label>菜单名称</label></td>
                <td class="formValue">
                    <input  type="text" class="form-control inputxt" kk-prop="value" id="name" placeholder="菜单名称" maxlength="8" auto_color_flag="true" datatype="zh2-8" errormsg="长度为2-8位中文" />
               		<input hidden="true" id="nodeId" name="nodeId" value="${nodeId}">
                </td>
            </tr>
            <tr>
            	<td class="formTitle">菜单地址</td>
                <td class="formValue">
					<input  type="text" class="form-control inputxt" id="url"/>
                </td>
            </tr>
            <tr>
                <td class="formTitle">图片名称</td>
                <td class="formValue">
                    <input  type="text" class="form-control inputxt" id="icon"/>
                </td>
            </tr>
            <tr>
                <td class="formTitle">菜单顺序</td>
                <td class="formValue inputxt">
                    <input  type="text" class="form-control inputxt" id="sortNum"/>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="footer_btn"><a class="btn btn_submit btn-show-blue" id="menuForm_btn">提交</a></div>       
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
<script>var ctx = "${ctx}";</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
<script type="text/javascript">

var orgId=biyue.urlSearch().orgId;
	
	$(function(){
        $("#personform2").kk_tableForm("#menuForm_btn",my_ajax);
	});

    function my_ajax() {

        $.ajax({
            type: "post",
            url: ctx + "/menu/addMenu",
            data: {
                orgId:orgId,
                parentId:$("#nodeId").val(),
                name:$("#name").val(),
                url:$("#url").val(),
                icon:$("#icon").val(),
                sortNum:$("#sortNum").val()
            },
            dataType: "json",
            success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                if(data.status == 1){
                    layer.alert("新增菜单成功！",function(){
                        layer_close();parent.restart_tree();
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
	
	/*关闭弹出框口
    function layer_close(){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    };*/
    /*刷新列表*/
    function layer_close(){
    	var url = ctx + "/menu/queryByNodeId"; 
    	parent.jQuery("#gridTable").jqGrid('setGridParam',{url:url}).trigger("reloadGrid");
    	var index = parent.layer.getFrameIndex(window.name);
    	parent.layer.close(index);
    };
</script>
</body>
</html>




