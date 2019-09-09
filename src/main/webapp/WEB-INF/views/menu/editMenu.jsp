<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>编辑菜单</title>
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
    <form id="editMenuform">
         <table class="table">
            <tbody>
            <tr>
                <td class="formTitle">菜单名称</td>
                <td class="formValue">
                	<input  type="hidden" class="form-control inputxt" id="id" value="${menuEntity.id }" />
                    <input  type="text" class="form-control inputxt" id="name" value="${menuEntity.name }" />
                </td>
            </tr>
            <%--<tr>--%>
            	<%--<td class="formTitle">菜单地址</td>--%>
                <%--<td class="formValue">--%>
					<%--<input  type="text" class="form-control inputxt" id="url" value="${menuEntity.url }"/>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td class="formTitle">图片名称</td>--%>
                <%--<td class="formValue">--%>
                    <%--<input  type="text" class="form-control inputxt" id="icon" value="${menuEntity.icon }"/>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <tr>
                <td class="formTitle">菜单顺序</td>
                <td class="formValue inputxt">
                    <input  type="text" class="form-control inputxt" id="sortNum" value="${menuEntity.sortNum }" />
                </td>
            </tr>
            </tbody>
        </table>
        <div class="footer_btn"><a class="btn btn_submit btn-show-blue" id="editMenuform_btn">编辑</a></div>
    </form>
</div>

<%--<script src="${ctx}/resources/static/js/lib/require.js?v=${v}" data-main="${ctx}/resources/static/js/tablemain"></script>--%>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript">
var ctx = "${ctx}";



$(function(){
    var biyue=new biYue(),orgId=biyue.urlSearch().orgId;
	$("#editMenuform_btn").on("click",function(){
         $.ajax({
             type: "post",
             url: ctx + "/menu/editMenu",
             data: {
            	 id:$("#id").val(),
            	 name:$("#name").val(),
            	 url:$("#url").val(),
            	 icon:$("#icon").val(),
            	 sortNum:$("#sortNum").val(),
                 orgId:orgId
            	 },
             dataType: "json",
             success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
             	if(data.status == 1){
                    layer.alert("修改菜单成功！",function(){
                        layer_close();parent.restart_tree();
                    });

                 } else {
                    layer.alert(data.msg);
                 }
             },
             error: function (data, status, e) {   //提交失败自动执行的处理函数
                 layer.alert(e);
             }
         });
    });
});

/*关闭弹出框口*/
function layer_close(){
	var url = ctx + "/menu/queryByNodeId";
	parent.jQuery("#gridTable").jqGrid('setGridParam',{url:url}).trigger("reloadGrid");
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
};

</script>

</body>
</html>




