<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<%@ include file="/common/common_tag.jsp"%>--%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>视频显示</title><!-- 武铁项目用8700平台查看数据 -->
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
  </head>
  <style>
  html,body{
    widht:100%;
    height:100%;
  }
  </style>
<body>
  <div class="container-full">
    <div class="menu-left-tree  fl">
        <ul id="orgnaizeTree" class="ztree"></ul>
    </div>
    <div class="videoplay">
         <object class="js-vss-ocx" classid="clsid:9ECD2A40-1222-432E-A4D4-154C7CAB9DE3" id="previewocx" name="previewocx" width="100%" height="100%">
         </object>
    </div>
  </div>
    <script>
    	var ctx = "${ctx}";
    </script>
</body>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.min.js?v=${v}"></script>
<script type="text/javascript">
	$(function() {
		//mScontains();
		initTreeWidget();
	});
	var $orgnaizeTree = $("#orgnaizeTree");
	var previewOcx = document.getElementById("previewocx");
	var zTreeObj;
	
	var setting = {
	    view: {
	        fontCss:{"color":"#000","font-family":"宋体"},
	        dbClickExpand:false,
	        showLine:false
	    },
	    data: {
	        simpleData: {
	            enable:true,
	            idKey:"id",
	            pIdKey:"pId",
	            rootPId:""
	        }
	    },
	    callback: {
	        onDblClick : function(event, treeId, treeNode) {
	            nodeClick(event, treeId, treeNode);
	        }
	    }
	};
	function mScontains() {
		$("html").niceScroll({
            styler:"fb",cursorcolor:"#000",
            cursorwidth: '5',
            cursorborderradius: '5px',
            background: '',
            spacebarenabled:false,
            cursorborder: '0',
            zindex: '1000'
        });
	}
	function initTreeWidget() {
		var url = ctx + "/video/escst/nvrPreview/fetchTreeNodeList";
        $.post(url,function(data){
            if(data.status == 1){
                var json = data.value;
                zTreeObj = $.fn.zTree.init($orgnaizeTree, setting, json);
            } else{
                alert(data.msg)
            }
        });
	}
	function nodeClick(event, treeId, treeNode) {
		if(treeNode && treeNode.pId!=0) {
            var previewXml = treeNode.xml;
            if (previewXml) {
	            previewOcx.PVX_StartPreview(previewXml);
            }
        }
	}
</script>
</html>