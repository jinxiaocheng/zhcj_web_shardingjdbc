<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>工程进度录入</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/validform.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/css/tableform.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/add.css?v=${v}">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
</head>

<body class="body_bottom">

<div class="table-responsive form-table">
    <form id="prjectSchedule">
        <table class="table">
            <tbody>
             <div class="row add_list ">
        <div class="form-group col-xs-4 area">
            <label  class="col-xs-4">区域</label>
            <button class="btn btn-default dropdown-toggle col-xs-8" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                请选择区域
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu3">
            </ul>
        </div>
        <div class="form-group col-xs-4 site">
            <label  class="col-xs-4">工地</label>
            <button class="btn btn-default dropdown-toggle col-xs-8" id="dropdownMenu4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                请选择工地
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu4">
            </ul>
        </div>

    </div>
            <tr>
                <td class="formTitle">工程进度计划</td>
                <td class="formValue">
                	<select class="form-control inputxt" id='scheduledPlanId'>
                        <option value="">--请选择--</option>
                        <c:forEach items="${list }" var="t">
                        	<option id="${t.id }" value="${t.id }">${t.name }</option>
                        </c:forEach>
                	</select>
                </td>
            </tr>
            <tr>
                <td class="formTitle">检查部位</td>
                <td class="formValue">
                	 <div class="form-group col-xs-4 es">
			            <button onclick="showMenu(event); return false;" type="button"
			                    class=" col-xs-8 btn btn-default dropdown-toggle" type="button" id="construction"
			                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
			                <label>请选择检查部位</label>
			                <span class="caret"></span>
			            </button>
			            <ul id="tree" class="k_dropdown ztree" aria-labelledby="dropdownMenu1">
			            </ul>
			        </div>
                </td>
            </tr>
            <tr>
                <td class="formTitle">开始时间</td>
                <td class="formValue">
                    <input type="text" class="form-control w-110" id='startTime' name='startTime' auto_color_flag="true" datatype="*" readonly="readonly"
                           onclick="WdatePicker()" placeholder="选择开始时间" dategroup="true" />
                </td>
            </tr>
            <tr>
                <td class="formTitle">结束时间</td>
                <td class="formValue">
                    <input type="text" class="form-control w-110" id='endTime' name='endTime' auto_color_flag="true" datatype="*" readonly="readonly"
                           onclick="WdatePicker()" placeholder="选择结束时间" dategroup="true" />
                </td>
            </tr>
            </tbody>
        </table>
        <div class="footer_btn"><a class="btn btn_submit btn-show-blue" id="menuForm_btn">提交</a></div>
    </form>
</div>
<%--警告--%>
<div class="alert alert-danger alert-dismissable error">
    <button type="button" class="close" aria-hidden="true">
        &times;
    </button>
    <span>警告！请不要提交。</span>
</div>
</body>
<script src="${ctx}/resources/static/js/lib/require.js?v=${v}" data-main="${ctx}/resources/static/js/tablemain"></script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.excheck-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/fileInput/fileinput.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/fileInput/fileinput_locale_de.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/popup.js?v=${v}"></script>
<script  type="text/javascript"src="${ctx}/resources/static/js/common/area.js?v=${v}"></script>
<script type="text/javascript">
    var ctx = "${ctx}";
    var userId = "${userId}";
    init(id);
    function id(a) {
    	//a代表工地id，将你们需要执行的函数放入其中
    	 my_day0(a);
   	}
  
    $(function () {
     	
        //my_day0("402864e35841e6f601584cd30ac10175");
        //subCompany("402864e35841e6f601584cd30ac10175");

        //点击关闭
        $(".delClose").on("click", function () {
            layer_close();
        });
    });

  //提示框
    $(".close").click(function(){
        $(".error").fadeOut("fast");
    });
  
    $(function(){
        $("#menuForm_btn").on("click",function(){
        var add_startTime=$("#startTime").val(),
        	add_entTime = $("#endTime").val();
        if(add_startTime==""||add_startTime==null){
            $(".error").fadeIn("fast");
            $(".error span").html("请填写开始日期！");
            return
        }
        if(add_entTime==""||add_entTime==null){
            $(".error").fadeIn("fast");
            $(".error span").html("请填写结束日期！");
            return
        }
            $.ajax({
                type: "post",
                url: ctx + "/projectSchedule/add",
                data: {
                    scheduledPlanId:$("#scheduledPlanId").val(),
                    projectStructureId:$("#construction").val(),
                    constructionId:constructionId,
                    startTime:$("#startTime").val(),
                    endTime:$("#endTime").val()
                },
                dataType: "json",
                success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                    if(data.status == 1){
                        layer.alert("新增工程进度成功！",function(){
                            layer_close();
                        });

                    } else {
                        layer.alert(data.msg);
                    }
                },
                error: function (data, status, e) {   //提交失败自动执行的处理函数
                    layer.alert(String(e));
                }
            });
        });
    });

    function layer_close(){
        var url = ctx + "/projectStructure/queryByNodeId";
        parent.jQuery("#gridTable").jqGrid('setGridParam',{url:url}).trigger("reloadGrid");
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    };
    
    var item_data=[],//班组数据
    bool=true,
    files=[],
    constructionId="";//图片文件
    
    var setting = {
    	    view: {
    	        showLine: true,
    	        selectedMulti: false //不可多选
    	    },
    	    data: {
    	        simpleData: {
    	            enable:true,
    	            idKey: "id",
    	            pIdKey: "pId",
    	            rootPId: 0
    	        }
    	    },
    	    async:{
    	        autoParam:["id=catalogID"],
    	        otherParam:["type","M"],
    	        enable:true,
    	        dataType:"json",
    	        type:"get",
    	        url:"${ctx}/document/queryCatalogTree"
    	    },
    	    check: {
    	        enable: false
    	    },
    	    callback: {
    	        beforeClick: beforeClick,
    	        onClick: onClick
    	    }
    	};
    
  //检查部位
    function my_day0(id){
        var my_json={
            "constructionId":id
        };
        $.ajax({
            type: "post",
            url: ctx + '/projectTask/projectStructure',
            data: my_json,
            success: function (data) {
                if(data.status == 1){
                    if(data.value){
                        //生成树形图
                        $.fn.zTree.init($("#tree"), setting, data.value);
                        treeObj = $.fn.zTree.getZTreeObj("tree");
                        if(data.value == '')
                        {
                            console.log('未创建个人目录 !');
                        }
                    }
                } else {
                    layer.alert(data.msg);
                }
            },
            error: function (data, status, e) {   //提交失败自动执行的处理函数
                alert(e);
            }
        });
    }
  
  //显示
    function showMenu(e) {
        if ( e && e.stopPropagation ) {
            e.stopPropagation();
        } else {
            window.event.cancelBubble = true;
        }
        if(bool){
            bool=false;
            $("#tree").fadeIn("fast");
            $("body").bind("mousedown", onBodyDown);
        }else{
            hideMenu();
        }
    }
  
  //隐藏
    function hideMenu() {
        bool=true;
        $("#tree").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    //点击window隐藏
    function onBodyDown(event) {
        if (!(event.target.id == "construction" || event.target.id == "tree" || $(event.target).parents("#tree").length>0)) {
            hideMenu();
        }
    }
    function beforeClick(treeId, treeNode) {
        var check = (treeNode && !treeNode.isParent);
        return check;
    }

    function onClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("tree"),
            nodes = zTree.getSelectedNodes(),
            v = ""
            k_id="";
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
            v += nodes[i].name + ",";
        }
        if (v.length > 0 ) v = v.substring(0, v.length-1);
        var cityObj = $("#construction>label");
        cityObj.html(v);
        cityObj.attr("k_id",treeNode.id);
        hideMenu();
      	$("#construction").val(treeNode.id);
       // my_data1(constructionId,treeNode.id);
    }
    
</script>
</html>
