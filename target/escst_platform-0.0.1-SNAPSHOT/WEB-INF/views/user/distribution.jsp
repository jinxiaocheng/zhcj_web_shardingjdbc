<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>用户管理(分配角色)</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqgrid/css/ui.jqgrid.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.structure.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
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
    </style>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
</head>

<body class="body_bg">

<div class="ui-report">
    <div class="jqGrid_wrapper">
    <input type="hidden" id="orgId" value="${orgId }">
    <input type="hidden" id="userId" value="${userId }">
    <input type="hidden" id="checkedIds" value="${checkedIds}">
        <table class="gridTable table-condensed" id="gridTable" aa="1"></table>
        <div class="gridPage" id="gridPager" style="bottom: 45px">

        </div>
    </div>
</div>
<button type="button" class="btn btn_submit btn-show-blue" id="distribution_form" style="position: absolute;right:20px;bottom:5px;z-index:102" >提交</button>
<%--加载--%>
<div class="my_load">
    <img src="${ctx}/resources/static/images/ajax-loader.gif" alt="">
</div>
<script>
    var ctx = "${ctx}";
 	var kk_array=[123]; //新建一个数组 
	var kk_bool=true;

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
<script type="text/javascript" src="${ctx}/resources/static/js/tablecommon.js?v=${v}" ></script>
<script type="text/javascript">   
    // 设置表格
    function getGridHead() {
        return [
                {'label': '角色名', 'name': 'name', 'index': 'name',align:'center'},
                {'label': '查看权限', 'name': 'gobal_buyer', 'index': 'gobal_buyer',align:'center',formatter:function(cellvalue, options, rowObject){
                    var rowId=options.rowId;
                	var temp = "<button class='btn btn-success btn-xs lookPage' data-id='"+rowId+"'>查看</button>";
                    return temp;
                }}
        ];
    };
    //点击查看
    ;(function () {
        $(".body_bg").on('click',".lookPage",function (e) {
            var id = $(this).attr("data-id");
            window.parent.kk_lookPage(id);
        })
    })();

    // 点击查询，获取查询所需要的条件，重新加载表格
    function getGridParamJson() {
		var orgId = $("#orgId").val();
        var postPrams = {
            orgId: orgId
        };
        return postPrams;
    };
    //获取Url
    function getGridUrl() {
        return ctx + "/user/distribution";
    };
    //需求控制
    function getGridExtendParam(){
    	return {
    		multiselect:true,
    		selectArray:[],
    		myOnPaging:function(){
    	    	kk_bool=false;
    		},
    		myOnSelectRow:function (rowid,status){
    			 var ids=jQuery("#gridTable").jqGrid('getGridParam','selarrrow');

    			 if(status&&kk_bool){
    				 kk_array[rowid]=rowid;
    			 }else if(!status&&kk_bool){
    				 delete kk_array[rowid]
    			 }
    		 },
    		 myOnSelectAll:function (aRowids,status){
    			 if(status){
    				 for( var i in aRowids){
    					 if(!kk_array[aRowids[i]]){
    						 kk_array[aRowids[i]]=aRowids[i];
    					 }
    				 }
    			 }else{
    				 for( var i in aRowids){
    					 if(kk_array[aRowids[i]]){
    						 delete kk_array[aRowids[i]] 
    					 }
    				 }
    			 }
    		 },
    		 myOnCellSelect:function (rowid,iCol,cellcontent,e){
    			 kk_bool=true;
    		 }
        }
    }
    //加载完后执行
    function gridLoadComplete() {	
    	for(var i in kk_array){
    		 jQuery("#gridTable").jqGrid('setSelection',kk_array[i]);
    	 }
    };
    //初始进入是选中对象
    function frist_checked(){
    	var check_array=$("#checkedIds").val().replace(/[\[\]]/g,"").split(",");
    	for(var j in check_array){
    		check_array[j]=$.trim(check_array[j]);
    		kk_array[check_array[j]]=check_array[j];
    	}
    }

    //分配角色
    $(function(){
    	frist_checked();
    	$("#distribution_form").on("click",function(){
            $(".my_load").show();
    		var data = getParamJson(kk_array);
             $.ajax({
                 type: "post",
                 url: ctx + "/user/addRoleForUser",
                 data:data,
                 traditional:true,
                 dataType: "json",
                 success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                 	if(data.status == 1){
                        var url = ctx + "/user/userList";
                        parent.jQuery("#gridTable").jqGrid('setGridParam',{url:url}).trigger("reloadGrid");
                         layer.alert("分配角色成功！",function () {
                             layer_close();
                         });
                     } else {
                         alert(data.msg);
                     }
                     $(".my_load").hide();
                 },
                 error: function (data, status, e) {   //提交失败自动执行的处理函数
                     alert(e);
                     $(".my_load").hide();
                 }
             });
        });
    });

    function getParamJson(a) {
    	var listName=new Array();
    	for(var i in a){
    		if(a[i])listName.push(a[i]);
    	}
        var postPrams = {
        		"userId":$("#userId").val(),
        		"idList":listName
        };
        return postPrams;
    };

</script>
</body>
</html>