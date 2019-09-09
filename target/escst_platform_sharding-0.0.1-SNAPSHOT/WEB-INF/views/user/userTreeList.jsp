<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>用户管理</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqgrid/css/ui.jqgrid.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.structure.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}"/>
    <script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}" type="text/javascript"></script>

    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
</head>

<body class="body_bg">
<div class="ui-report">

    <div class="form-inline response-head titlePanel" id="listTools">
        <button type="button" class="btn openSearchBtn" data-toggle="collapse"
                data-target="#search-input">
            查询条件 <span class="glyphicon glyphicon-menu-down"></span>
        </button>
        <div id="search-input" class="collapse">
            <div class="ui-filter">
                <label>姓名:</label>
                <input type="text" id="name" class="form-control w-110" value=""/>
                <input hidden="true" id="orgId" value="${orgId}">
                <input hidden="true" id="constructionId" value="${constructionId }">
            </div>
            <div class="ui-filter">
                <label>登录账号:</label>
                <input type="text" id="userName" class="form-control w-110" value=""/>
            </div>

            <div class="ui-filter">
                <label>手机号码:</label>
                <input type="text" id="mobile" class="form-control w-110" value=""/>
            </div>
            <%--<div class="ui-filter max-width-margin">--%>
                <%--<label>身份证号码:</label>--%>
                <%--<input type="text" id="idCard" class="form-control w-110" value=""/>--%>
            <%--</div>--%>
            <div class="ui-filter" style="margin:10px 15px;">
                <a id="search_link" class="btn btn-primary" data-toggle="collapse" data-target="#search-input"><i class="fa fa-search"></i>查询</a>
            </div>
        </div>
        <div class="tools-btn" id="tools-btn">
            <div class="btn-group">
                <a id="add_link" class="btn btn-info"><i class="fa fa-plus"></i>录入</a>
            </div>
            <div class="btn-group">
                <a id="edit_link" class="btn btn-success"><i class="fa fa-pencil-square-o"></i>编辑</a>
            </div>
            <div class="btn-group">
                <a id="reset_pwd_link" class="btn btn-info"><i class="fa fa-plus"></i>重置密码</a>
            </div>
            <div class="btn-group">
                <a id="assign_roles" class="btn btn-uirole"><i class="fa fa-user-plus"></i>分配角色</a>
            </div>
            <div class="btn-group">
                <a id="delete_link" class="btn btn-uired"><i class="fa fa-trash-o"></i>删除</a>
            </div>
        </div>
    </div>
    <div class="jqGrid_wrapper">
        <table class="gridTable table-condensed" id="gridTable" aa="1"></table>
        <div class="gridPage" id="gridPager">

        </div>
    </div>
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
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script src="${ctx}/resources/static/plugins/jqgrid/js/jquery.jqGrid.src.js?v=${v}"></script>
<script src="${ctx}/resources/static/js/tablecommon.js?v=${v}" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/job.js?v=${v}"></script>
<script type="text/javascript">
    $(function () {
        jobBtn()
    	//分配角色
    	 $("#assign_roles").click(function () {
    		var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
    		if (gr != null) {
    			var orgId = $("#orgId").val();
                var _url = ctx + '/user/toDistribution?orgId='+orgId+'&userId='+gr;
                var _title = '分配角色';
                var iframeWidth = '780px';
                var iframeHeight = '500px';
                layer_show(_title, _url, iframeWidth, iframeHeight);
            } else {
                layer.msg('您没有选中任何数据请选择后操作', {time: 2000, icon: 0});
            }
        });
    	//查看角色详情
    	function kk_lookPage(id){
            var _url = ctx + '/role/toLookPage?roleId='+id+"&type=view";
            var _title = '查看';
            var iframeWidth = '780px';
            var iframeHeight = '500px';
            layer_show(_title, _url, iframeWidth, iframeHeight);
    	};
    	window.kk_lookPage=kk_lookPage;
    	
        //添加
        $("#add_link").click(function () {
        	var orgId = $("#orgId").val();
            var _url = ctx + '/user/toAddUser?orgId='+orgId;
            var _title = '用户录入';
            var iframeWidth = '780px';
            var iframeHeight = '500px';
            layer_show(_title, _url, iframeWidth, iframeHeight);
        });
        
        //编辑
        $("#edit_link").click(function () {
        	var orgId = $("#orgId").val();
        	var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
            var _url = ctx + '/user/toEdit?id='+gr+'&orgId='+orgId;
            var _title = '修改';
            var iframeWidth = '780px';
            var iframeHeight = '500px';
            
            if (gr != null) {
            	layer_show(_title, _url, iframeWidth, iframeHeight);
            } else {
                layer.msg('请选择一条记录', {time: 2000, icon: 0});
            }

        });
        
        //删除
        $("#delete_link").click(function () {
            var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
            if (gr != null) {
            	layer.confirm('确认删除该条记录吗？', {
            		title: '提示',
            		icon:2,
            		btn: ['确定','关闭'],
            		yes:function(){
            			 $.ajax({
            	             type: "post",
            	             url: ctx + "/user/delete",
            	             data: { id:gr },
            	             dataType: "json",
            	             success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
            	             	if(data.status == 1){
            	             		 layer.msg('删除成功', {icon: 1,time: 1000});
            	                     //alert("删除机构成功！");
            	                     layer_close();
            	                 } else {
                                    layer.alert(data.msg);
            	                 }
            	             },
            	             error: function (data, status, e) {   //提交失败自动执行的处理函数
                                 layer.alert(String(e));
            	             }
            	         });

            		}
            	});
            } else {
                layer.msg('您没有选中任何数据请选择后操作', {time: 2000, icon: 0});
            }
            /*刷新列表*/
            function layer_close(){
            	var url = ctx + "/user/userList";
            	jQuery("#gridTable").jqGrid('setGridParam',{url:url}).trigger("reloadGrid");
            };

        });

        //重置密码
        $("#reset_pwd_link").click(function () {
        	var id = $("#gridTable").jqGrid('getGridParam', 'selrow');
        	if(!id||id==null){
                layer.msg('请选择列表数据后操作', {time: 2000, icon: 0});
                return;
            }
            layer.confirm('确认将用户密码重置为123456吗?', {
                btn: ['确定', '取消']
            }, function(index, layero){
                $.ajax({
                    type: "post",
                    url: ctx + "/user/resetPwd",
                    data: {userId : id },
                    dataType: "json",
                    success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                        if(data.status == 1){
                            layer.closeAll();
                            layer.msg('重置密码成功', {icon: 1,time: 1000});

                        } else {
                            layer.alert(data.msg);
                        }
                    },
                    error: function (data, status, e) {   //提交失败自动执行的处理函数
                        layer.alert(String(e));
                    }
                });
            }, function(index){
                layer.closeAll();
            });

        });
      //查询
        $("#search_link").click(function () {
            var url = getGridUrl();
            var postParams = getGridParamJson();
            $("#gridTable").jqGrid("setGridParam", {
                url: url,
                postData: postParams,
                page: 1
            }).trigger("reloadGrid");
        });

    });

    // 设置表格
    function getGridHead() {
        return [
            {'label': 'id', 'name': 'id', 'index': 'id', 'hidden': true},
            {'label': '姓名', 'name': 'name', 'index': 'name'},
            {'label': '登录账号', 'name': 'userName', 'index': 'userName'},
            //{'label': '性别', 'name': 'sex', 'index': 'sex', 'formatter': 'select', 'editoptions': {value: "1:男;2:女"}},
            //{'label': '年龄', 'name': 'age', 'index': 'age'},
            {'label': '手机号码', 'name': 'mobile', 'index': 'mobile'},
            {'label': '身份证号码', 'name': 'idCard', 'index': 'idCard','hidden':true},
            {'label': '创建时间', 'name': 'createTime', 'index': 'createTime'},
            //{'label': '邮箱', 'name': 'email', 'index': 'email'},
            {'label': '角色', 'name': 'roleName', 'index': 'roleName'}
            // {'label': '状态', 'name': 'status', 'index': 'status', 'formatter': 'select', 'editoptions': {value: "1:有效;0:无效"}}
        ];
    };

    // 点击查询，获取查询所需要的条件，重新加载表格
    function getGridParamJson() {
        //用户名称
        var name = $.trim($("#name").val());
        var userName = $.trim($("#userName").val());
        var mobile = $.trim($("#mobile").val());
        var idCard = $.trim($("#idCard").val());
		var orgId = $("#orgId").val();
		var constructionId = $("#constructionId").val();
		
        var postPrams = {
            name: name,
            idCard: idCard,
            userName: userName,
            mobile: mobile,
            orgId: orgId,
            constructionId:constructionId,
            positionWorkType:$("#job").attr("data-value")
        };
        return postPrams;
    };

    //获取Url
    function getGridUrl() {
        return ctx + "/user/userList";
    };
    
    function layer_show(_title,_url,w,h) {
    	
    	if (_title == null || _title == '') {
    		title=false;
    	};
    	if (_url == null || _url == '') {
    		url="404.html";
    	};
    	if (w == null || w == '') {
    		w=800;
    	};
    	if (h == null || h == '') {
    		h=($(window).height() - 50);
    	};
    	layer.open({
    		type: 2,
    		title: "|&nbsp;"+_title,
    		maxmin: true,
    		area: [w,h],
    		shadeClose: false, //点击遮罩关闭
    		content:_url
    	});
    };
</script>
</body>
</html>