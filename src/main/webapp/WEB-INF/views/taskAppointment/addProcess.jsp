<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>处理任务预约</title>

    <link type="text/css" href="${ctx}/resources/static/css/public.css" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/validform.css" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/tableform.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js" />
    <script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js" type="text/javascript"></script>
</head>

<body class="body_bottom">

<div class="table-responsive form-table">
    <form id="personform">
        <table class="table">
            <tbody>
            <tr>
                <td class="formTitle"  id="menuviewocx" name="menuviewocx" >紧急任务
                <td class="formValue">
                    <c:choose>
                        <c:when test="${list.isUrgentTask== 1}">
                            <input type="text" class="form-control inputxt" name='taskStatus' id='statusValue' value="是" readonly/>
                        </c:when>
                        <c:when test="${list.isUrgentTask== 0}">
                            <input type="text" class="form-control inputxt" name='taskStatus' id='statusValue' value="否" readonly/>
                        </c:when>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td class="formTitle">工程结构</td>
                <td class="formValue">
                    <input  type="text" class="form-control inputxt" id='projectStructure' value="${list.projectStructureName}" readonly/>
                    <input  type="hidden" class="form-control inputxt" id='taskAppointmentId' value="${list.id}" readonly/>
                </td>
            </tr>
            <tr>
                <td class="formTitle">预约设备</td>
                <td class="formValue">
                    <c:choose>
                        <c:when test="${list.equipmentType==1}">
                            <input type="text" class="form-control inputxt" id='equipmentType' value="塔吊" readonly/>
                        </c:when>
                        <c:when test="${list.isUrgentTask== 2}">
                            <input type="text" class="form-control inputxt"  id='equipmentType' value="升降机" readonly/>
                        </c:when>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td class="formTitle">预约时间</td>
                <td class="formValue">
                    <input  type="text" class="form-control inputxt" id='appointmentDate' value="${list.appointmentDate}" readonly/>
                </td>
            </tr>
            <tr>
                <td class="formTitle">预约说明</td>
                <td class="formValue inputxt">
                    <textarea rows="3" cols="70" class="textarea.form-control" id='remark' readonly>${list.remark}</textarea>
                </td>
            </tr>
            <tr>
                <td class="formTitle">分包公司</td>
                <td class="formValue inputxt">
                    <input  type="text" class="form-control inputxt" id='projectCompangName' value="${list.projectCompanyName}" readonly/>
                </td>
            <tr>
                <td class="formTitle">班组</td>
                <td class="formValue">
                    <input  type="text" class="form-control inputxt" id='teanName' value="${list.teamName}" readonly />
                </td>
            </tr>
            <tr>
                <td class="formTitle">预约人</td>
                <td class="formValue">
                    <input  type="text" class="form-control inputxt" id='appointmentName' value="${list.personName}" readonly/>
                    <input  type="hidden" class="form-control inputxt" id='appointmentId' value="${list.userId}" readonly/>
                </td>
            </tr>
            <tr>
                <td class="formTitle">联系电话</td>
                <td class="formValue">
                    <input  type="text" class="form-control inputxt" id='appointmentMobile' value="${list.personMobile}" readonly />
                </td>
            </tr>
            <tr>
                <td class="formTitle">处理说明</td>
                <td class="formValue inputxt">
                    <textarea rows="3" cols="70" class="textarea.form-control" id='remarkProcess'>

                    </textarea>
                </td>
            </tr>

            </tbody>
        </table>
    </form>
    <a href="javascript:void(0)" onclick="AddAttachment()">[点击添加附件]</a>
    <table cellpadding="0" cellspacing="3" id="id_attachmentpanel"></table>
    <div class="footer_btn"><a class="btn btn_submit btn-show-blue" id="materialApproachform_btn">提交</a></div>
</div>
<script src="${ctx}/resources/static/js/lib/require.js" data-main="${ctx}/resources/static/js/structure"></script>
<script src="${ctx}/resources/static/js/lib/require.js" data-main="${ctx}/resources/static/js/tablemain"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script type="text/javascript">
    var ctx = "${ctx}";
    $(function() {
        $("#materialApproachform_btn").on("click", function () {
            var url = ctx + "/taskAppointment/addProjectTaskAppointmentProcess";
            var data = getQueryJson();
            alert(data);
            $.ajax({
                url: url,                              //后台请求地址
                data: data,                            //自定义参数
                type: "post",                          //当要提交自定义参数时，这个参数要设置成post
                dataType: 'json',                     //服务器返回的数据类型
                success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                    if (data.status == 1) {
                        alert("新增任务成功！");
                        layer_close();
                    } else {
                        alert(data.msg);
                    }
                },
                error: function (data, status, e) {   //提交失败自动执行的处理函数
                    alert(e);
                }
            });

        });

    })

    function getQueryJson() {
        //任务说明
        var remark = $("#remarkProcess").val();
        //任务Id
        var taskAppointmentId=$("#taskAppointmentId").val();
        //预约人
        var appointmentId=$("#appointmentId").val();
        var postPrams = {
            appointmentId:"appointmentId",
            remark:remark,
            taskAppointmentId:taskAppointmentId,
            appointmentId:appointmentId,
            taskStatus:3
        };
        return postPrams;
    };
    function AddAttachment() {
        var objTable = $("#id_attachmentpanel");
        var intCount = $("#id_attachmentpanel tr").children().size() / 2 + 1;
        if (intCount > 5) { alert("附件不能超过5个"); return; }
        objTable.append("<tr><td>" + intCount + ". <input type='file" + intCount + "' id='files' name='files" + intCount + "' onchange='FileExtChecking(this,1)' /></td><td><a href='javascript:void(0)' onclick='AddAttachment()'>[增加]</a><a href='javascript:void(0);' onclick='DisposeTr(this)'>[取消]</a></td></tr>");
        $("#id_attachmentpanel a").hide();
        $("#id_attachmentpanel a").last().show();
        if (intCount < 5) { $("#id_attachmentpanel a").last().prev().show(); }
    }
    function DisposeTr(arg_obj_item) {
        var objTr = $(arg_obj_item).parent().parent();
        objTr.remove();
        $("#id_attachmentpanel a").last().show();
        $("#id_attachmentpanel a").last().prev().show();
    }
    /*关闭弹出框口*/
    function layer_close(){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    };

</script>
</body>
</html>




