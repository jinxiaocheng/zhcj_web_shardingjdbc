<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>人员选择</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/font-awesome-4.7.0/css/font-awesome.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqgrid/css/ui.jqgrid.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/jqueryUI/css/jquery-ui.structure.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}" />
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
        .ztreeBox{
            position: absolute;
            left: 0%;
            right: 70%;
            top:0;
            bottom: 50px;
            overflow: auto;

        }
        .tableBox{
            position: absolute;
            left: 30%;
            right: 0%;
            top:0;
            bottom: 50px;
            width: auto !important;
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
<div class="ztreeBox">
    <ul id="orgTree" class="ztree"></ul>
</div>
<div class="ui-report tableBox">
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
    var ctx = "${ctx}";
    var constructionId = "${constructionId}";
    var userId="${userId}";
    var biyueTableBool = true;
    var orgId="";
</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}" ></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script src="${ctx}/resources/static/plugins/jqgrid/js/jquery.jqGrid.src.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/popup.js?v=${v}"></script>
<script src="${ctx}/resources/static/js/tablecommon.js?v=${v}" type="text/javascript"></script>
<script type="text/javascript">
    var kk_bool=true,kk_array=[],kk_id="",zTree="";
    if(parent.sentData){
        kk_array=parent.sentData;
    }
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
            onClick : function(event, treeId, treeNode) {
                var url = getGridUrl();
                kk_name=treeNode.name;
                kk_id=treeNode.id+'$'+kk_name;
                if(!kk_array[kk_id]){
                    kk_array[kk_id]=[];
                }

                $("#gridTable").jqGrid("setGridParam", {
                    url: url,
                    postData: {
                        orgId:treeNode.id
                    },
                    page: 1
                }).trigger("reloadGrid");

            }
        }
    };

    //加载完后执行
    function gridLoadComplete() {
        for(var i in  kk_array[kk_id]){
            $("#gridTable").jqGrid('setSelection', kk_array[kk_id][i].id);
        }
    };

    // 发送获取树节点请求
    var initTreeWidget = function(){
        var url = ctx + "/org/fetchOrgNodeList?userId="+userId;
        $.post(url,function(data){
            if(data.status == 1){
                var json = data.value,nodeNum=0,nodeId="";
                zTreeObj = $.fn.zTree.init($("#orgTree"), setting, json);
                zTree = $.fn.zTree.getZTreeObj("orgTree");
                var node = zTreeObj.getNodes()[0];
                zTreeObj.selectNode(node);

                var kk_name=node.name;
                kk_id=node.id+'$'+kk_name;
                if(!kk_array[kk_id]){
                    kk_array[kk_id]=[];
                }
             //   setting.callback.onClick(null, zTreeObj.setting.treeId, node);
                orgId=node.id;
                initGrid();
            } else{
                layer.alert(data.msg)
            }
        });
    };
    initTreeWidget();

    function parentData() {

    }
    parentData();

    //得到选中行的数据
    function check_data() {
        var gr = $("#gridTable").jqGrid('getGridParam', 'selrow');
        return $("#gridTable").jqGrid('getRowData',gr);
    }

    // 设置表格
    function getGridHead() {
        return [
            {'label': 'id', 'name': 'id', 'index': 'id', 'hidden': true},
            {'label': '姓名', 'name': 'name', 'index': 'name'},
            {'label': '手机号码', 'name': 'mobile', 'index': 'mobile'},
            {'label': '班组', 'name': 'teamName ', 'index': 'teamName ','width':"150px"}
        ];
    };

    // 点击查询，获取查询所需要的条件，重新加载表格
    function getGridParamJson() {
        //人员名称
        var postPrams = {
            orgId:orgId
        };
        return postPrams;
    };

    //获取Url
    function getGridUrl() {
        return  ctx + "/user/userList2";
    };

    //需求控制
    function getGridExtendParam(){
        return {
            multiselect:true,
            myListHeight:$('.distribution_form').height(),
            selectArray:[],
            myOnPaging:function(){
                kk_bool=false;
            },
            myOnSelectRow:function (rowid,status){
                var node=$("#gridTable").jqGrid('getRowData',rowid);
                if(status&&kk_bool){
                    if(!kk_array[kk_id]){kk_array[kk_id]=[]}
                    kk_array[kk_id][rowid]=node;
                }else if(!status&&kk_bool){
                    delete kk_array[kk_id][rowid]
                }
            },
            myOnSelectAll:function (aRowids,status){
                var ids=jQuery("#gridTable").jqGrid('getGridParam','selarrrow');
                for(var i in ids){
                    if(status&&kk_bool){
                        kk_array[kk_id][ids[i]]=jQuery("#gridTable").jqGrid('getRowData',ids[i]);
                    }else if(!status&&kk_bool){
                        delete kk_array[kk_id][ids[i]]
                    }
                }
            },
            myOnCellSelect:function (rowid,iCol,cellcontent,e){
                kk_bool=true;
            }

        }
    }

    //点击提交
    $("#distribution_form").click(function(){
        parent.getPersonData(kk_array);
        layer_close();
    })

    /*关闭弹出框口*/
    function layer_close() {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    };


</script>
</body>
</html>
