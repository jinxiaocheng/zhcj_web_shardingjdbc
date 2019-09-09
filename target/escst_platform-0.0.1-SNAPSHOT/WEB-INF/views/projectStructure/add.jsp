<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>检查部位录入</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/validform.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/tableform.css?v=${v}" rel="stylesheet" />
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

<div class="table-responsive form-table">
    <form id="personform2">
        <table class="table">
            <tbody>
            <tr>
                <td class="formTitle">工程机构名称</td>
                <td class="formValue">
                    <input  type="text" class="form-control inputxt" id="name" placeholder="工程机构名称" maxlength="50" auto_color_flag="true" datatype="zh2-8" errormsg="长度为2-8位中文" />
                    <input hidden="true" id="nodeId" name="nodeId" value="${nodeId }">
                </td>
            </tr>
            <tr>
                <td class="formTitle">开始时间</td>
                <td class="formValue">
                    <input type="text" class="form-control w-110" id='startDate' name='startDate' auto_color_flag="true" datatype="*" readonly="readonly"
                           onclick="WdatePicker()" placeholder="选择开始时间" dategroup="true" />
                </td>
            </tr>
            <tr>
                <td class="formTitle">结束时间</td>
                <td class="formValue">
                    <input type="text" class="form-control w-110" id='endDate' name='endDate' auto_color_flag="true" datatype="*" readonly="readonly"
                           onclick="WdatePicker()" placeholder="选择结束时间时间" dategroup="true" />
                </td>
            </tr>
            </tbody>
        </table>
        <div class="footer_btn"><a class="btn btn_submit btn-show-blue" id="menuForm_btn">提交</a></div>
    </form>
</div>

<script src="${ctx}/resources/static/js/lib/require.js?v=${v}" data-main="${ctx}/resources/static/js/tablemain"></script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript">
    var ctx = "${ctx}";


    $(function(){
        $("#menuForm_btn").on("click",function(){
            $.ajax({
                type: "post",
                url: ctx + "/projectStructure/add",
                data: {
                    parentId:$("#nodeId").val(),
                    name:$("#name").val(),
                    startDate:$(startDate).val(),
                    endDate:$(endDate).val(),
                    constructionId:"8aad89d1545b00420154c1b4573a0364"
                },
                dataType: "json",
                success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                    if(data.status == 1){
                        layer.alert("新增机检查部位功！",function(){
                            layer_close();
                        });

                    } else {
                        layer.alert(data.msg);
                    }
                },
                error: function (data, status, e) {   //提交失败自动执行的处理函数
                    layer.alert(String(e));
                }
            });
        });
    });

    /*关闭弹出框口
     function layer_close(){
     var index = parent.layer.getFrameIndex(window.name);
     parent.layer.close(index);
     };*/
    function layer_close(){
        var url = ctx + "/projectStructure/queryByNodeId";
        parent.jQuery("#gridTable").jqGrid('setGridParam',{url:url}).trigger("reloadGrid");
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    };
</script>
</body>
</html>




