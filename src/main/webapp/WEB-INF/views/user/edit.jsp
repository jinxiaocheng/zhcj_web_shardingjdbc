<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户修改</title>
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
<div class="table-responsive form-table">
    <form id="userAndPersonform" action="javascript:void(0)">
        <div class="row add_list ">
            <div class="form-group col-xs-5 username row" kk-contral="require" style="margin: 0">
                <label  class="col-xs-4">账号</label>
                <input type="text" class="form-control col-xs-8" kk-prop="value" id="userName" name="userName" data-name="${entity.userName}" placeholder="请输入账号" value="${entity.userName }">
                <input type="hidden" name="id" value="${entity.id }"/>
            </div>
            <div class="form-group col-xs-5 usertel" kk-contral="require">
                <label class="col-xs-4">姓名</label>
                <input type="text" class="form-control col-xs-8" kk-prop="value" id="name" name="name" placeholder="请输入姓名" value="${entity.name }">
            </div>
        </div>
        <div class="row add_list ">
            <div class="form-group col-xs-5 usertel" kk-contral="require">
                <label class="col-xs-4">密码</label>
                <div class="input-group col-xs-8 password" style="padding: 0">
                    <input type="password" class="form-control " kk-prop="value" id="userPassword" name="userPassword"  placeholder="请输入密码">
                    <div class="input-group-addon" style="cursor: pointer"><i class="fa fa-eye-slash"></i></div>
                </div>
            </div>
            <div class="form-group col-xs-5 usertel" kk-contral="require">
                <label class="col-xs-4">确认密码</label>
                <div class="input-group col-xs-8 password" style="padding: 0">
                    <input type="password" class="form-control col-xs-8" kk-prop="value" id="checkPassword" placeholder="请再次输入密码">
                    <div class="input-group-addon" style="cursor: pointer"><i class="fa fa-eye-slash"></i></div>
                </div>
            </div>
        </div>
        <div class="row add_list ">
            <div class="form-group col-xs-5 usertel">
                <label class="col-xs-4">手机</label>
                <input type="text" class="form-control col-xs-8" id="mobile" name="mobile"  placeholder="请输入手机号" value='${entity.mobile }'>
            </div>
            <div class="form-group col-xs-5 usertel">
                <label  class="col-xs-4">性别</label>
                <span style="padding-left: 10px">
               		<c:if test="${entity.sex==1 }">
	                    <label class="radio-inline">
	                        <input type="radio" name="sex" id="man" kk-prop="value" value=1 checked="checked"/> 男
	                    </label>
                        <label class="radio-inline">
	                        <input type="radio" name="sex" id="woman" value=2> 女
	                    </label>
                    </c:if>
                    <c:if test="${entity.sex==2 }">
	                    <label class="radio-inline">
	                        <input type="radio" name="sex" id="man" kk-prop="value" value=1 /> 男
	                    </label>
                        <label class="radio-inline">
	                        <input type="radio" name="sex" id="woman" value=2 checked="checked"> 女
	                    </label>
                    </c:if>
                </span>
            </div>
          <%--  <div class="form-group col-xs-5 usertel">
                <label class="col-xs-4">身份证</label>
                <input type="text" class="form-control col-xs-8" id="idCard" name="idCard" placeholder="请输入身份证" value='${entity.idCard }'>
            </div>--%>
        </div>

        <div class="row add_list ">
         <%--   <div class="form-group col-xs-5 usertel">
                <label class="col-xs-4">年龄</label>
                <input type="text" class="form-control col-xs-8" id="age" name="age" placeholder="请输入年龄" value='${entity.age }'>
            </div>--%>

        </div>

       <%-- <div class="row add_list ">
            <div class="form-group col-xs-5 usertel">
                <label class="col-xs-4">邮箱</label>
                <input type="text" class="form-control col-xs-8" id="email" name="email" placeholder="请输入邮箱" value='${entity.email }'>
            </div>
        </div>--%>

        <div style="display:none;">
            <div class="form-group col-xs-5 usertel">
                <label class="col-xs-4">所属机构</label>
                <input type="text" class="form-control col-xs-8" value="${org.name }" id="orgName" readonly="readonly" placeholder="请输入所属机构">
            </div>
        </div>
        <div class="row add_list ">

        </div>
        <div class="footer_btn"><a class="btn btn_submit btn-show-blue" id="userform_btn">提交</a></div>
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
<script>
    var ctx = "${ctx}";
</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/user/edit.js?v=${v}"></script>
<script>
    $(function(){

        $("#userAndPersonform").kk_tableForm("#userform_btn",my_ajax);

        if($("#constructionId").val()!=null&&$("#constructionId").val()!=""){
            $(".asd").show();
        }

    });
</script>
</body>
</html>




