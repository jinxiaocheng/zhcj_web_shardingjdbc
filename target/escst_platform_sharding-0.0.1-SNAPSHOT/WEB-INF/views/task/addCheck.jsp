<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>新增任务派发</title>

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
                <td class="formTitle">任务状态</td>
                <td class="formValue">
                    <input  type="text" class="form-control inputxt" id='status' value="${list.status}" readonly/>
                    <input  type="hidden" class="form-control inputxt" id='taskId' value="${list.id}" readonly/>
                </td>
            </tr>
            <tr>
                <td class="formTitle">工程结构</td>
                <td class="formValue">
                    <input  type="text" class="form-control inputxt" id='projectStructure'value="${list.projectStructureName}" readonly/>
                </td>
            </tr>
            <tr>
                <td class="formTitle">任务说明</td>
                <td class="formValue"  colspan="3">
                    <textarea rows="3" cols="70" class="textarea.form-control" id='projectRemark' readonly>
                        ${list.remark}
                    </textarea>
                </td>
            </tr>
            <tr>
                <td class="formTitle">处理人</td>
                <td class="formValue">
                    <input  type="text" class="form-control inputxt" id='process' value=" ${list.contactsName}" readonly/>
                    <input  type="hidden" class="form-control inputxt" id='processId'value="${list.contactsId}" readonly/>
                </td>
            </tr>
            <tr>
                <td class="formTitle">提交备注</td>
                <td class="formValue"  colspan="3">
                    <textarea rows="3" cols="70" class="textarea.form-control" id='remark' >

                    </textarea>
                </td>
            </tr>
            <tr>
                <td class="formTitle">检查结果</td>
                <td class="formValue inputxt" >
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <label> <input type="radio"class="form-control inputxt" name="check" id="pass" value="5"/>合格</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <label><input type="radio"class="form-control inputxt" name="check" id="failed" value="4"/>不合格</label>
                </td>
            </tr>
               <c:forEach items="${listDetail }" var="t">
                <tr>
                <td class="formTitle">处理状态:</td>
                    <td class="formValue">
                        <c:choose>
                            <c:when test="${t.taskStatus== 1}">
                                <input type="text" class="form-control inputxt" name='taskStatus' id='statusValue' value="待受理"/>
                            </c:when>
                            <c:when test="${t.taskStatus== 2}">
                                <input type="text" class="form-control inputxt" name='taskStatus' id='statusValue' value="处理中"/>
                            </c:when>
                            <c:when test="${t.taskStatus== 3}">
                                <input type="text" class="form-control inputxt" name='taskStatus' id='statusValue' value="待检查"/>
                            </c:when>
                            <c:when test="${t.taskStatus== 4}">
                                <input type="text" class="form-control inputxt" name='taskStatus' id='statusValue' value="检查不通过"/>
                            </c:when>
                            <c:when test="${t.taskStatus== 5}">
                                <input type="text" class="form-control inputxt" name='taskStatus' id='statusValue' value="已完成"/>
                            </c:when>
                        </c:choose>

                    </td>
            </tr>
            <tr>
                <td class="formTitle">备注信息</td>
                <td class="formValue">
                 <textarea rows="3" cols="70" class="textarea.form-control" id='disscription' value="${t.remark}" readonly>
                     </textarea>
                </td>

            </tr>
                   <c:forEach items="${t.picList}" var="a">
            <tr>
                <td class="formTitle">图片</td>
                <td class="formValue" >

                          <img src="${ctx}/resources/${a}" alt="...">

                </td>
            </tr>
                   </c:forEach>
            <tr>
                <td class="formTitle">
                        ${t.personName}
                    </td>
                <td class="formTitle">
                        ${t.createTime}
                </td>
            </tr>
            </c:forEach>


            </tbody>
        </table>
        <a href="javascript:void(0)" onclick="AddAttachment()">[点击添加附件]</a>
        <table cellpadding="0" cellspacing="3" id="id_attachmentpanel"></table>
        <div class="footer_btn"><a class="btn btn_submit btn-show-blue" id="materialApproachform_btn">提交</a></div>
    </form>
</div>
<script src="${ctx}/resources/static/js/lib/require.js" data-main="${ctx}/resources/static/js/structure"></script>
<script src="${ctx}/resources/static/js/lib/require.js" data-main="${ctx}/resources/static/js/tablemain"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script type="text/javascript">
    var ctx = "${ctx}";
    $(function(){
        $("#materialApproachform_btn").on("click",function(){
            var url = ctx + "/projectTask/addProjectTaskProcess";
            var data = getQueryJson();
            alert(data);
            $.ajax({
                url:url,                              //后台请求地址
                data:data,                            //自定义参数
                type:"post",                          //当要提交自定义参数时，这个参数要设置成post
                dataType: 'json',                     //服务器返回的数据类型
                success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                    if(data.status == 1){
                        alert("检查任务成功！");
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
        /*关闭弹出框口*/
        function layer_close(){
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        };
    });
    function getQueryJson() {
        //检查人
        var personId = $("#processId").val();
        //任务说明
        var remark = $("#remark").val();
        //任务ID
        var taskId=$("#taskId").val();
        //状态
        var xuan=document.getElementsByName("check");
        var xuanid;
        for(var i = 0; i < xuan.length; i++) {
            if (xuan[i].checked)
                xuanid = xuan[i].value;
        }

        var postPrams = {
            personId:personId,
            taskStatus:xuanid,
            remark:remark,
            taskId:taskId

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
    $(document).ready(function(){
        var status=document.getElementById("status").value;
        if(status==1){
            document.getElementById("status").value="待受理";
        }else if(status==2){
            document.getElementById("status").value="处理中";
        }else if(status==3){
            document.getElementById("status").value="待检查";
        }else if(status==4){
            document.getElementById("status").value="检查不通过";
        }else if(status==5){
            document.getElementById("status").value="已完成";
        }

    });
</script>
</body>
</html>




