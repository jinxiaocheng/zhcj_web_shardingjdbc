<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>机构录入</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/validform.css" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/tableform.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css" />
	<link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.excheck-3.5.js"></script>
</head>
<script type="text/javascript">
var setting = {
		check: {
			enable: true,
			chkboxType: {"Y":"", "N":""}
		},
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeClick,
			onCheck: onCheck
		}
	};
	
	function beforeClick(treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("menuTree");
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}
	
	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("menuTree"),
		nodes = zTree.getCheckedNodes(true),
		v = "";
		id = "";
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
			id += nodes[i].id +",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		if (id.length > 0 ) id = id.substring(0, id.length-1);
		var menuObj = $("#menuSel");
		var menuHiddenObj = $("#menuHidden");
		menuObj.attr("value", v);
		menuHiddenObj.attr("value", id);
	}

	function showMenu() {
		var cityObj = $("#menuSel");
		var cityOffset = $("#menuSel").offset();
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "menuSel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}
	
	$(document).ready(function(){
		var url = ctx + "/menu/fetchAllMenuNodeList";
        $.post(url,function(data){
            if(data.status == 1){
                var json = data.value;
                zTreeObj = $.fn.zTree.init($("#menuTree"), setting, json);
            } else{
                alert(data.msg)
            }
        });
	});
</script>
<body class="body_bottom">

<div class="table-responsive form-table">
    <form id="personform">
        <table class="table">
            <tbody>
            <tr>
                <td class="formTitle">角色名称</td>
                <td class="formValue">
                    <input  type="text" class="form-control inputxt" id="name" placeholder="角色名称" maxlength="8" auto_color_flag="true" datatype="*" />
                	<input hidden="true" id="orgId" value="${orgId }">
                </td>
            </tr>
            <tr>
            	<td class="formTitle">描述</td>
                <td class="formValue">
					<input  type="text" class="form-control inputxt" id="remark"/>
                </td>
            </tr>
             <tr>
            	<td class="formTitle">菜单</td>
                <td class="formValue">
					<ul class="list">
                        <li class="title"><input id="menuSel" type="text" readonly value="" style="width:120px;"/>
                        <input id="menuHidden" hidden="true" value=""/>
                            &nbsp;<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择菜单</a></li>
                    </ul>
                </td>
            </tr>
            <tr>
            	<td style="display:none; position: absolute; z-index: 9999 ;background:white" id="menuContent" class="menuContent">
                    <ul id="menuTree" class="ztree" style="margin-top:0; width:160px;"></ul>
                </td>
	             <td class="videoplay">
			     </td>
            </tr>
            </tbody>
        </table>
        <div class="footer_btn"><a class="btn btn_submit btn-show-blue" id="roleForm_btn">提交</a></div>       
    </form>
</div>

<script type="text/javascript">
var ctx = "${ctx}";

	
	$(function(){
		$("#roleForm_btn").on("click",function(){
	         $.ajax({
	             type: "post",
	             url: ctx + "/role/addRole",
	             data: {
	            	 name:$("#name").val(),
	            	 remark:$("#remark").val(),
	            	 orgId:$("#orgId").val(),
	            	 menuSel:$("#menuHidden").val()
	            	 },
	             dataType: "json",
	             success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
	             	if(data.status == 1){
	                     alert("新增角色成功！");
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
	});
	
    function layer_close(){
    	var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    };
    
</script>
</body>
</html>
