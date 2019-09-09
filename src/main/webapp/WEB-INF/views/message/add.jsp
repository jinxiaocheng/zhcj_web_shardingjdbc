<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>新增消息</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.min.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/add.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/accident/add.css?v=${v}">
    <link type="text/css"  rel="stylesheet" href="${ctx}/resources/static/css/tableform.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/ieControl.css?v=${v}">
    <style>
        .add_list>label{
            display: block;
            width: 10%;
            float: left;
            line-height: 35px;
        }
        .add_list>.form-control{
            display: block;
            width: 85%;
            float: left;
            margin-left: 3%;
        }
        .selectBox{
            min-height: 100px;
            padding: 6px !important;
            box-sizing: border-box;
        }
        .row {
            margin-right: 0px;
            margin-left: 0px;
        }
    </style>

    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
</head>

<body class="body_bottom">

<div class="form-table">
    <form id="personform" class="form-inline add_form">
        <div class="row add_list" kk-contral="require">
            <label class="">标题</label>
            <input type="text" class="form-control" id="title" kk-prop="value">
        </div>

        <div class=" row check_result add_list" id="addPerson" kk-contral="require" kk-contral="require">
            <label class=""  style=" width: 13%;">添加推送人</label>
            <div class="" kk-prop="data-value" data-value="" style=" width: 85%;float: left;">
                <i class="alert alert-info alert-dismissible addPerson" role="alert">
                    <span><label class="fa fa-plus"></label>选择人员</span>
                </i>
                 <div class="listBox"></div>
            </div>
        </div>

        <div class="row add_list " kk-contral="require">
            <label class="">内容</label>
            <textarea rows="6" kk-prop="value" class="textarea form-control" maxlength="500" placeholder="请填写内容(最多输入500字)" kk-re="textLength/0-500" kk-name="内容" id="content"></textarea>
        </div>
        <div class="footer_btn"><a class="btn btn_submit btn-show-blue" id="team_btn">提交</a></div>
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
<%--班组类别弹出层--%>
<div id="check_result_tree">
    <div id="itemsZtree" class="ztree">
        <p>请先选择所属公司</p>
    </div>
</div>
<script>
    var ctx = "${ctx}",cr_data=[],job_data=[],userId="${userId}";
</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/  jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/popup.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/message/add.js?v=${v}"></script>
<script type="text/javascript">
    $("#personform").kk_tableForm("#team_btn",myAjax);

    //关闭弹出框口
    function layer_close() {
        var url = ctx + "/message/listData";
        parent.jQuery("#gridTable").jqGrid('setGridParam', {url: url}).trigger("reloadGrid");
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    };

    //提交数据
    function myAjax() {
        var acceptIds=[];
        for(var i in personData){
            for(var j in personData[i]){
                acceptIds.push({
                    id:personData[i][j].id,
                    constructionId:i.split('$')[0]
                });
            }
        }
        $.ajax({
            type: "post",
            data:JSON.stringify({
                title:$('#title').val(),
                content:$("#content").val(),
                acceptIds:acceptIds
            }),
            contentType:'application/json',
            url: ctx + '/message/batchAdd',
            success: function (data) {
                if(data.status == 1){
                    layer_close();
                } else {
                    console.log(data.msg);
                }
            },
            error: function (data, status, e) {   //提交失败自动执行的处理函数
                layer.alert(e);
            }
        });
    };

</script>
</body>
</html>




