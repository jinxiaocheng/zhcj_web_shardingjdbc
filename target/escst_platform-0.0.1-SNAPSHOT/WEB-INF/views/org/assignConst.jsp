<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>分配工地</title>
<link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
<link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" rel="stylesheet" />
<link type="text/css" href="${ctx}/resources/static/css/validform.css?v=${v}" rel="stylesheet" />
<link type="text/css" href="${ctx}/resources/static/css/tableform.css?v=${v}" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}" />
<link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}" type="text/css">
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<![endif]-->

</head>

<body class="body_bottom">

	<div class="table-responsive form-table">
		<form id="personform" style="position: absolute;left: 0;top: 0;right: 0;bottom: 60px;overflow: auto;">
			<table class="table">
				<tbody>
					<tr>
						<td class="formValue">
							<ul class="list">
								<li class="title">
									<input id="constructionSel" hidden="true" readonly value="" style="width: 120px;" /> 
									<input id="constructionHidden" hidden="true" value="" /> 
									<input id="nodeId" hidden="true" value="${nodeId }" /> &nbsp;
							</ul>
						</td>
						<td style="position: absolute; z-index: 9999; background: white"
							id="constructionContent" class="constructionContent">
							<ul id="constructionTree" class="ztree"
								style="margin-top: 0; width: 160px;"></ul>
						</td>
						<td class="videoplay"></td>
					</tr>
				</tbody>
			</table>
			<div class="footer_btn">
				<a class="btn btn_submit btn-show-blue" id="roleForm_btn">提交</a>
			</div>
		</form>
	</div>
	<script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}" type="text/javascript"></script>
	<!--[if gte IE 9]>
	<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
	<![endif]-->
	<![if !IE]>
	<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
	<![endif]>
	<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
	<script src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.excheck-3.5.js?v=${v}"></script>

	<script type="text/javascript">
        var ctx = "${ctx}";
        var setting = {
            async : {
                enable : true,
                autoParam : [ "id", "name=n", "level=lv" ],
                otherParam : {
                    "chk" : "chk"
                },
                dataFilter : dataFilter
            },
            check : {
                enable : true,
                autoCheckTrigger : true
            },
            data : {
                simpleData : {
                    enable : true
                }
            },
            callback : {
                onCheck : onCheck,
                onAsyncSuccess : onAsyncSuccess
            }
        };

        function dataFilter(treeId, parentNode, childNodes) {
            if (parentNode.checkedEx === true) {
                for (var i = 0, l = childNodes.length; i < l; i++) {
                    childNodes[i].checked = parentNode.checked;
                    childNodes[i].halfCheck = false;
                    childNodes[i].checkedEx = true;
                }
            }
            return childNodes;
        }
        function onCheck(event, treeId, treeNode) {
            cancelHalf(treeNode)
        }
        function onAsyncSuccess(event, treeId, treeNode, msg) {
            cancelHalf(treeNode);
        }
        function cancelHalf(treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("constructionTree");
            treeNode.halfCheck = false;
            zTree.updateNode(treeNode);

            var nodes = zTree.getCheckedNodes(true),
                v = "";
            id = "";
            for (var i=0, l=nodes.length; i<l; i++) {
                // 如果有子节点,则不是工地机构,不保存
                if (nodes[i].children && nodes[i].children.length > 0) {
                    continue;
                }
                v += nodes[i].name + ",";
                id += nodes[i].id +",";
            }
            if (v.length > 0 ) v = v.substring(0, v.length-1);
            if (id.length > 0 ) id = id.substring(0, id.length-1);
            var constructionObj = $("#constructionSel");
            var constructionHiddenObj = $("#constructionHidden");
            constructionObj.attr("value", v);
            constructionHiddenObj.attr("value", id);
        }

        function showconstruction() {
            var cityObj = $("#constructionSel");
            var cityOffset = $("#constructionSel").offset();
            $("#constructionContent").css({
                left : cityOffset.left + "px",
                top : cityOffset.top + cityObj.outerHeight() + "px"
            }).slideDown("fast");
        }

        $(document).ready(
            function() {
                var url = ctx + "/construction/fetchConstructionNodeList";
                $.post(url, function(data) {
                    if (data.status == 1) {
                        var json = data.value;
                        zTreeObj = $.fn.zTree.init($("#constructionTree"),
                            setting, json);
                    } else {
                        layer.alert(data.msg)
                    }
                });
                showconstruction();
            });


		$(function() {
			$("#roleForm_btn").on("click", function() {
				var constructionHidden = $("#constructionHidden").val();
				if (constructionHidden == null || constructionHidden == "") {
                    layer.alert("请选择工地！");
					return;
				}
				$.ajax({
					type : "post",
					url : ctx + "/org/assign",
					data : {
						constructionSel : $("#constructionHidden").val(),
						constructNames : $("#constructionSel").val(),
						parentId : $("#nodeId").val()
					},
					dataType : "json",
					success : function(data) { //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
						if (data.status == 1) {
                            layer.alert("新增机构成功！");
							layer_close();
						} else {
                            layer.alert(data.msg);
						}
					},
					error : function(data, status, e) { //提交失败自动执行的处理函数
                        layer.alert(String(e));
					}
				});
			});
		});

		function layer_close() {
			var url = ctx + "/org/queryByNodeId"; 
	    	parent.jQuery("#gridTable").jqGrid('setGridParam',{url:url}).trigger("reloadGrid");
	    	var index = parent.layer.getFrameIndex(window.name);
	    	parent.layer.close(index);
		};
	</script>
</body>
</html>
