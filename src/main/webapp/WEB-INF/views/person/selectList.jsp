<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
	String path = request.getScheme() + "://" + request.getHeader("Host") + request.getContextPath();
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>人员管理</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqgrid/css/ui.jqgrid.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.structure.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}"/>
    <script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}" type="text/javascript"></script>
    <style>
        .my_load {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.06);
            z-index: 1000;
            display: none;
        }
        .my_load img {
            -webkit-transform: translate(-50%, -50%);
            -moz-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            -o-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
            position: absolute;
            top: 50%;
            left: 50%;
        }
        #gridTable_cb #cb_gridTable{
            display: none;
        }
        #gridPager {
            position: fixed;
            bottom: 40px;
        }
    </style>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
</head>

<body class="body_bg">
<div class="ui-report">
    <div class="response-head titlePanel form-inline">
        <div class="ui-filter">
            <label>姓名:</label>
            <input type="text" id="name" class="form-control w-110" style="max-width: 90px;" value=""/>
        </div>
        <div class="ui-filter">
            <label>公司:</label>
            <div class="btn-group queryCondition" >
                <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false" style="max-width: 131px;overflow: hidden;text-overflow: ellipsis;"
                   data-value="" id="company">请选择</a>
                <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                        class="caret"></span></a>
                <ul class="dropdown-menu company" style="min-width:108px;max-height:228px;overflow: auto">
                    <li><a data-value="">请选择</a></li>
                </ul>
            </div>
        </div>
        <div class="ui-filter">
            <label>班组:</label>
            <div class="btn-group queryCondition">
                <a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"  style="max-width: 100px;overflow:hidden;text-overflow: ellipsis;"
                   data-value="" id="items">请选择</a>
                <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                        class="caret"></span></a>
                <ul class="dropdown-menu items" style="min-width:108px;max-height:228px;overflow: auto">
                    <li><a data-value="">请选择</a></li>
                </ul>
            </div>
        </div>
        <div class="ui-filter job">
            <label>岗位/工种:</label>
            <input type="text" id="job" class="form-control w-110" placeholder="点击选择岗位/工种" title="点击选择岗位/工种" style=" cursor: pointer;width: 150px !important;" readonly value=""/>
            <i id="workDel" class="glyphicon glyphicon-remove" ></i>
        </div>
        <div class="ui-filter" style="margin:0px 15px;">
            <a id="search_link" class="btn btn-primary"><i class="fa fa-search"></i>查询</a>
        </div>
    </div>
    <div class="jqGrid_wrapper">
        <table class="gridTable table-condensed" id="gridTable" aa="1"></table>
        <div class="gridPage" id="gridPager">
        </div>
    </div>
</div>
<div class="btn-group distribution_form" style="position:fixed;bottom: 5px ;right: 18px;color:#fff">
    <a id="distribution_form" class="btn btn_submit">确定</a>
</div>

<%--加载--%>
        <div class="my_load">
            <img src="${ctx}/resources/static/images/ajax-loader.gif" alt="">
        </div>
<script>
    var ctx = "${ctx}",constructionId="${constructionId}";
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
<script type="text/javascript" src="${ctx}/resources/static/plugins/jqgrid/js/jquery.jqGrid.src.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/equipment/table.js?v=${v}" ></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/job.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript">
    $(function () {
        jobBtn('680px','360px');
        selectConstruction(constructionId);
        //得到选中行的数据
        function check_data() {
            var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
            return $("#gridTable").jqGrid('getRowData',gr);
        }

        $("#distribution_form").on("click",function(){
            var data = check_data();
            parent.selectList(data);
            layer_close();
        });
    });
    //需求控制
    function getGridExtendParam(){
        return {
            multiselect: true,
            multiboxonly:true
        }
    }
    //获取公司
    function selectConstruction(constructionId) {
        var my_json={
                "constructionId":constructionId,
            };
        $.ajax({
            type: "post",
            url: ctx +'/projectCompany/queryCompanyDetail',
            data:JSON.stringify(my_json),
            contentType:'application/json',
            success: function (data) {
                if(data.status == 1){
                    $("#company").attr("data-value","").html("请选择");
                    var $list='<li><a data-value="">请选择</a></li>';
                    for(var i in data.value){
                        $list+='<li><a data-value="'+data.value[i].id+'">'+data.value[i].name+'</a></li>'
                    }
                    $(".company").html($list);
                    items(data.value);
                    //下拉选择
                    $(".company").parent().Bsselect();
                } else {
                    layer.alert(data.msg);
                }
            },
            error: function (data, status, e) {   //提交失败自动执行的处理函数
                layer.alert(String(e));
            }
        });
    }
    function items(data) {
        $(".company li").on("click",function(){
            var index=$(this).index()-1;
            if(index<0){
                $("#items").html("请选择").attr("data-value","");
                $(".items").html("");
                return
            }
            var $list='<li><a data-value="">请选择</a></li>';
            for(var i in data[index].data){
                if(data[index].data[i].name){$list+='<li><a data-value="'+data[index].data[i].id+'">'+data[index].data[i].name+'</a></li>'}
            }
            $(".items").html($list);
            //下拉选择
            $(".items").parent().Bsselect();
        })
    }

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


    // 设置表格
    function getGridHead() {
        return [
            {'label': 'id', 'name': 'constructionId', 'index': 'constructionId', 'hidden': true},
            {'label': '所属公司id', 'name': 'projectCompanyId', 'index': 'projectCompanyId', 'hidden': true},
            {'label': '姓名', 'name': 'name', 'index': 'name','width':"150px"},
            {'label': '用户id', 'name': 'userId', 'index': 'userId','hidden': true},
            {'label': '电话', 'name': 'mobile', 'index': 'mobile','width':"150px"},
            {'label': '所属公司', 'name': 'projectCompanyName', 'index': 'projectCompanyName'},
            {'label': '班组', 'name': 'teamName', 'index': 'teamName'},
            {'label': '班组id', 'name': 'teamId', 'index': 'teamId','hidden': true},
            {'label': '岗位/工种', 'name': 'positionWorkTypeName', 'index': 'positionWorkTypeName'}
        ];
    };
    // 点击查询，获取查询所需要的条件，重新加载表格
    function getGridParamJson() {
        var biyue=new biYue(),searchObj=biyue.urlSearch();
        var type="";
        if(searchObj.type){
            type=2;
        }else{
            type=1;
        }
        //人员名称
        var postPrams = {
            constructionId:constructionId,
            projectCompanyId:$("#company").attr("data-value"),
            teamId:$("#items").attr("data-value"),
            name:$("#name").val(),
            type:type,
            positionWorkType:$("#job").attr("data-value")
        };
        return postPrams;
    };
    //获取Url
    function getGridUrl() {
        return ctx + "/person/constructionPersonList";
    };
    //班组分配数据
    function item(data) {
        if(!data) return;
        var len=data.length,$list="";
        if(data[0].name===undefined){
            $(".item>button").html("无班组");
        }else{
            $(".item>button").attr("item_id",data[0].id).html(data[0].name+'<span class="caret" style="margin-left:6px"></span>');
            for(var i=0;i<len;i++){
                $list+='<li><a>'+data[i].name+'</a></li>'
            }
            $(".item>ul").html($list);
            $(".item>ul>li").click(function () {
                var index=$(this).index();
                $(".item>button").attr("item_id",data[index].id).html(data[index].name+'<span class="caret" style="margin-left:6px"></span>');
                $(".username>input").val(data[index].userName);
                $(".username").attr("userName_id",data[index].userId);
                $(".usertel>input").val(data[index].mobile);
            })
        }
        $(".username>input").val(data[0].userName);
        $(".username").attr("userName_id",data[0].userId);
        $(".usertel>input").val(data[0].mobile);
    }

</script>
</body>
</html>