<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>文档管理</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqgrid/css/ui.jqgrid.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.structure.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/qualityList.css?v=${v}">
    <style>
        .k_dropdown {
            position: absolute;
            top: 35px;
            z-index: 2147483647;
            float: left;
            min-width: 160px;
            max-height: 400px;
            overflow: auto;
            padding: 5px 0;
            margin: 2px 0 0;
            font-size: 14px;
            text-align: left;
            list-style: none;
            background-color: #fff;
            -webkit-background-clip: padding-box;
            background-clip: padding-box;
            border: 1px solid #ccc;
            border: 1px solid rgba(0, 0, 0, 0.15);
            border-radius: 4px;
            -webkit-box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175);
            display: none;
        }
        .dropdown_title{
            max-width: 140px;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }
    </style>
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
    <!--更多查询条件-->

    <div class="form-inline response-head titlePanel" id="listTools">
        <button type="button" class="btn openSearchBtn" data-toggle="collapse"
                data-target="#search-input">
            查询条件 <span class="glyphicon glyphicon-menu-down"></span>
        </button>
        <div id="search-input" class="collapse">
            <div class="ui-filter area">
                <label>区域:</label>
                <div class="btn-group queryCondition">
                    <a class="btn btn-default dropdown-text dropdown_title" data-toggle="dropdown" aria-expanded="false"
                       data-value="" id="area">请选择</a>
                    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                            class="caret"></span></a>
                    <ul class="dropdown-menu" style="min-width:108px;">
                    </ul>
                </div>
            </div>
            <div class="ui-filter site">
                <label>工地:</label>
                <div class="btn-group queryCondition">
                    <a class="btn btn-default dropdown-text dropdown_title" data-toggle="dropdown" aria-expanded="false"
                       data-value="" id="site">请选择</a>
                    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
                            class="caret"></span></a>
                    <ul class="dropdown-menu" style="min-width:108px;">
                    </ul>
                </div>
            </div>

            <div class="ui-filter">
                <label>文档类型:</label>
                <div class="btn-group queryCondition">
                    <a class="btn btn-default dropdown-text ztreeContent dropdown_title" data-toggle="dropdown" aria-expanded="false"
                       data-value="" id="d_type">请选择</a>
                    <a class="btn btn-default dropdown-toggle d_type" data-toggle="dropdown" aria-expanded="true"><span
                            class="caret"></span></a>
                    <ul  id="tree" class="k_dropdown ztree selectBox ztree" style="min-width:108px;">
                    </ul>
                </div>
            </div>
            <div class="ui-filter" style="margin:10px 15px;">
                <a id="search_link" class="btn btn-primary" data-toggle="collapse" data-target="#search-input"><i class="fa fa-search"></i>查询</a>
            </div>
        </div>
        <div class="tools-btn" id="tools-btn">
            <div class="btn-group">
                <a id="add_link" class="btn btn-info"><i class="fa fa-plus"></i>录入</a>
            </div>
            <div class="btn-group">
                <a id="view_link" class="btn btn-uisblue"><i class="fa fa-search"></i>查看</a>
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
    <%--弹出层--%>
    <div id="popPage">
        <div id="pop">

        </div>
    </div>
</div>
<script>
    var ctx = "${ctx}";
    var userId = "${userId}";
</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}" ></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.excheck-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/ztreeDropDownBox.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jqgrid/js/jquery.jqGrid.src.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/popup.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/pdf/pdf.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/pdf/createPdfPage.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/list_area.js?v=${v}"></script>
<script src="${ctx}/resources/static/js/tablecommon.js?v=${v}" type="text/javascript"></script>
<script type="text/javascript">
    init();
    //得到选中行的数据
    function check_data() {
        var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
        return $("#gridTable").jqGrid('getRowData',gr);
    }

    window.check_data=check_data;

    //添加
    $("#add_link").click(function () {
        var url = ctx + '/document/toAdd?';
        var title = '新增';
        layer_showOdd(title, url, {w:'920px',h:'560px'});
    });

    //查看
    $("#view_link").click(function () {
        viewLink()
    });

    function viewLink() {
        var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
        var id = check_data().id;
        var url = ctx + '/document/toView?id=' + id;
        var title = '查看详情';
        if (gr != null) {
            layer_showOdd(title, url, {w:'920px',h:'560px'});
        } else {
            layer.msg('您没有选中任何数据请选择后操作', {time: 2000, icon: 0});
        }
    }

    //删除
    $("#delete_link").click(function () {
        var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
        var rowData = $("#gridTable").jqGrid('getRowData', gr);
        if (gr != null) {
            if(rowData.createBy!==userId){
                layer.msg('只能删除您所上传的文档！', {time: 2000, icon: 0});
                return ;
            }
            layer.confirm('是否删除该文档?', {
                title: '提示',
                icon: 2,
                btn: ['确定', '关闭'],
                yes: function () {
                    var index=layer.load();
                    $.ajax({
                        type: "post",
                        url: ctx + '/document/deleteByUserId',
                        data: {
                            id:gr
                        },
                        success: function (data) {
                            layer.close(index);
                            if (data.status == 1) {
                                layer.msg('删除成功', {time: 2000, icon: 0});
                                $("#gridTable").jqGrid('setGridParam', {url: getGridUrl()}).trigger("reloadGrid");
                            } else {
                                layer.alert(data.msg);
                            }
                        },
                        error: function (data, status, e) {   //提交失败自动执行的处理函数
                            layer.close(index);
                            layer.alert(e);
                        }
                    });
                }
            });
        } else {
            layer.msg('您没有选中任何数据请选择后操作', {time: 2000, icon: 0});
        }
    })

        //表格扩展
    function getGridExtendParam(){
        return {
            //双击时间
            myDblClickRow:function (rowid,iRow,iCol,e){
                $("#view_link").click();
            }
        }
    }

    // 设置表格
    function getGridHead() {
        return [
            {'label': 'id', 'name': 'id', 'index': 'id', 'hidden': true,width:"0px"},
            {'label': 'createBy', 'name': 'createBy', 'index': 'createBy', 'hidden': true,width:"0px"},
            {'label': 'parentName', 'name': 'parentName', 'index': 'parentName', 'hidden': true,width:"0px"},
            {'label': '区域', 'name': 'areaName', 'index': 'areaName', width: "60px"},
            {'label': '工地', 'name': 'constructionName', 'index': 'constructionName', width: "100px"},
            {'label': '文档类型', 'name': 'typeName', 'index': 'typeName', width: "100px",formatter:function(cellvalue, options, rowObject){
                var parentName = rowObject.parentName;
                var typeName = rowObject.typeName;
                if(parentName){
                    return parentName+'/'+typeName;
                }else{
                    return typeName;
                }

            }},
            {'label': '备注', 'name': 'remark', 'index': 'remark', width: "100px"},
            {'label': '创建人', 'name': 'createName', 'index': 'createName', width: "100px"}
        ];
    }

    //获取Url
    function getGridUrl() {
        return ctx + "/document/queryByConstructionId";
    }

    // 点击查询，获取查询所需要的条件，重新加载表格
    function getGridParamJson() {
        //文档类型
        var typeId =$("#d_type").attr("data-value");
        var name = $("#name").val();
        var postPrams ={
            constructionId:$("#site").attr("data-value"),
            belongArea:$("#area").attr("data-value"),
            "typeId":typeId,
            "name":name,
            "page":1,
            "rowNum":10
        };
        return postPrams;
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

    var documentType=new ztreeDropDownBox('d_type',false); // 初始化树形图下拉框（文档类型）

    $("#d_type").click(function (event) {
        documentType.showMenu(event);
    });
    $(".d_type").click(function(){
        $("#d_type").click();
    });

    //配置树形图
    function ztreeSet(){
        return {
            view: {
                showLine: false,
                selectedMulti: false
            },
            check: {
                enable: false
            },
            data: {
                simpleData: {
                    enable:true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: 0
                }
            },
            callback: {
                beforeClick: documentType.beforeClick,
                onClick: documentType.onClick
            }
        }
    }
    //点击文档类型
    function check_result() {
        $.ajax({
            type: "post",
            contentType: "application/json",
            url: ctx + '/document/docTypeTree',
            success: function (data) {
                if(data.status == 1){
                    if(data.value){
                        var cr_data=data.value;
                        $.fn.zTree.init($("#tree"), ztreeSet(),cr_data);
                    }
                } else {
                    console.log(data.msg);
                }
            },
            error: function (data, status, e) {   //提交失败自动执行的处理函数
                layer.alert(String(e));
            }
        });
    }


    check_result();
</script>
</body>
</html>