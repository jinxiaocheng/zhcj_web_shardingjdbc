<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>机构管理-机构录入</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/build.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/role/add.css?v=${v}">


    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
</head>

<body class="body_bg">
<div class="ui-report">

    <div class="form-inline response-head titlePanel" id="listTools" >
        <div class="ui-filter">
            <label>机构名称:</label>
            <input type="text" id="name" placeholder="定义您的机构名称" class="form-control w-110" value=""/>
        </div>
        <div class="ui-filter">
            <label>父机构名称:</label>
            <input type="text" id="org" readonly class="form-control w-110" value="${org.name }"/>
        </div>
    </div>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#tableBox" aria-controls="tableBox" role="tab" data-toggle="tab">平台权限</a></li>
        <li role="presentation"><a href="#tableBoxApp" aria-controls="tableBoxApp" role="tab" data-toggle="tab">app权限</a></li>
    </ul>

    <div class="tab-content">
        <div role="tabpanel" id="tableBox" class="tableBox tab-pane checkbox checkbox-success active">
            <div class="tableHeader">
                <table lay-filter="demo">
                    <thead>
                    <tr>
                        <th data-head="1"><input type="checkbox" id="web"><label for="web">一级菜单</label></th>
                        <th data-head="2">二级菜单</th>
                        <th data-head="3">权限</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="tableBody">
                <table lay-filter="demo">
                    <tbody></tbody>
                </table>
            </div>
        </div>
        <div role="tabpanel" id="tableBoxApp" class="tableBox tab-pane checkbox checkbox-success">
            <div class="tableHeader">
                <table lay-filter="demo">
                    <thead>
                    <tr>
                        <th data-head="1"><input type="checkbox" id="app"><label for="app">一级菜单</label></th>
                        <th data-head="2">二级菜单</th>
                        <th data-head="3">权限</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="tableBody">
                <table lay-filter="demo">
                    <tbody></tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<footer>
    <a class="btn btn_submit btn-show-blue" id="submit">提&nbsp;交</a>
</footer>
<script>
    var ctx = "${ctx}";
    var orgId = '${org.id}';
</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/role/add.js?v=${v}"></script>
<script type="text/javascript">
    $(function () {
        var user=new userAdd();
        user.getData({
            url:ctx + "/menu/fetchAllMenuNodeList",
            data:{
                orgId:orgId
            },
            type:'post',
            status:'add'
        });
        user.sendData({
            url: ctx + "/org/addOrg",
            successText:'录入机构成功',
            refreshUrl: ctx + "/org/queryByNodeId"
        });
    })
</script>
</body>
</html>