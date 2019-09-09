function createTable() {
    $("#name").val("");
    var tableBoxH=parseInt($("#tableBox").height());
    var pageNum=parseInt(tableBoxH/30)-2;
    $('#treeTable').treegrid({
        url: ctx + "/projectStructure/loadTree",
        method: 'post',
        lines: true,
        idField: 'id',
        treeField: 'name',
        fit: true,
        pagination: true,
        queryParams: queryParams(),
        columns: [[
            {field: 'id', title: 'id', width: 0, hidden: true},
            {field: 'constructionId', title: 'constructionId', width: 0, hidden: true},
            {field: 'name', title: '名称', width: "90%"},
            {field: 'sortNum', title: '序号', width: "11%"}
        ]]
        , loadFilter: function (data, parentId) {
            $(".tableError").hide();
            if(!data.value.rows){
                $(".tableError").show();
            }
            return {
                rows: data.value.rows,
                total: data.value.totalRecord
            };
        }
        ,onBeforeExpand: function (node) {
            $('#treeTable').treegrid('options').loadFilter = function (data, parentId) {
                return data.value.rows;
            };
            return true;
        }
        , onExpand: function (row) {
            $('#treeTable').treegrid('options').loadFilter = function (data, parentId) {
                return {
                    rows: data.value.rows,
                    total: data.value.totalRecord
                };
            };
            var children = $("#treeTable").treegrid('getChildren', row.id);
            if (children.length <= 0) {
                row.leaf = true;
                $("#treeTable").treegrid('refresh', row.id);
            }
        }
    });
    $('#treeTable').datagrid('getPager').pagination({
        pageNumber: 1,
        pageSize:pageNum,
        showPageList:false,
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
}

function tableReload(pid,type) {
    if(pid!=0){
        if(type==='0'){
            $("#treeTable").treegrid('append',{
                parent: pid,
                data: {
                    value:{
                        rows:[]
                    }
                }
            } );
        }
        $("#treeTable").treegrid('reload', pid);
    }else{
        $("#treeTable").treegrid('reload');
    }
}
window.tableReload=tableReload;

//查询条件
var queryParams=function () {
    var tableBoxH=parseInt($("#tableBox").height());
    var pageNum=parseInt(tableBoxH/30)-2;
    return {
        "constructionId": constructionId,
         rowNum: pageNum,
         name:$("#name").val()
    };
};

//查询
$("#search_link").click(function () {
    $('#treeTable').treegrid('load',queryParams());
});

//添加
$("#add_link").click(function () {
    var url = $("#add-edit");
    var title = '新增工程结构名称';
    var node= $('#treeTable').treegrid('getSelected');
    var planName=$('#planName').val();
    if(node!==null){
        $("#add-edit").attr("data-value",node.id);
    }/*else if(planName){
        layer.msg('搜索后，需选中一行才能新增！', {time: 2000, icon: 0});
        return;
    }*/
    console.log(node);
    $('#treeTable').treegrid('unselectAll');
    $("#add-edit").attr("data-type",0);
    layer_show(title, url, '680px', '250px',1);
});

//修改
$("#edit_link").click(function () {
    var url = $("#add-edit");
    var title = '修改进度计划';
    var node= $('#treeTable').treegrid('getSelected');
    if(node!==null){
        $("#add-edit").attr("data-value",node.pId+'*'+node.id);
    }else{
        layer.msg('您没有选中任何数据请选择后操作', {time: 2000, icon: 0});
        return;
    }
    $('#treeTable').treegrid('unselectAll');
    $("#add-edit").attr("data-type",1);
    $("#planName").val(node.name);
    $("#sortNum").val(node.sortNum);
    layer_show(title, url, '680px', '250px',1);
});

//新增||修改
function addEdit() {
    var type=$("#add-edit").attr("data-type");
    var uploadData=new Object();
    var url = "";
    var nodeId="";
    var successText="";
    uploadData={
        name:$("#planName").val()
        ,constructionId:constructionId
        ,sortNum:$("#sortNum").val()
    };
    if(type==0){
        console.log(uploadData)
        nodeId=uploadData.pId=$("#add-edit").attr("data-value");
        url=ctx+'/projectStructure/addStructure';
        successText='新增成功';
    }else if(type==1){
        uploadData.id=$("#add-edit").attr("data-value").split('*')[1];
        nodeId=uploadData.pId=$("#add-edit").attr("data-value").split('*')[0];
        url=ctx+'/projectStructure/updateStructure';
        successText='编辑成功';
    }
    var index=layer.load();
    $.ajax({
        type: "post",
        url: url,
        data:uploadData,
        success: function (data) {
            layer.close(index);
            if (data.status == 1) {
                if(!nodeId) nodeId=0;
                console.log(nodeId);
                tableReload(nodeId,type);
                layer.alert(successText,function(){
                    $("#add-edit").attr({
                        "data-value":"",
                        "data-type":0
                    });
                    $("#add-edit input").each(function () {
                        $(this).val("")
                    });
                    layer.closeAll();
                    popInit();
                });
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.close(index);
            layer.alert(String(e));
        }
    });
}

function tableReload(pid,type) {
    if(pid!=0){
        if(type==='0'){
            $("#treeTable").treegrid('append',{
                parent: pid,
                data: {
                    value:{
                        rows:[]
                    }
                }
            } );
        }
        $("#treeTable").treegrid('reload', pid);
    }else{
        $("#treeTable").treegrid('reload');
    }
}

//删除
$("#delete_link").click(function () {
    var node= $('#treeTable').treegrid('getSelected');
    if(!node){
        layer.msg('您没有选中任何数据请选择后操作', {time: 2000, icon: 0});
        return;
    }
    layer.confirm('您是否要删除该工程结构？', {
        btn: ['是','否'] //按钮
    }, function(){
        $('#treeTable').treegrid('unselectAll');
        var index=layer.load();
        $.ajax({
            type: "post",
            url: ctx + '/projectStructure/deleteStructure',
            data:{
                id:node.id,
                "constructionId": constructionId
            },
            success: function (data) {
                layer.close(index);
                if (data.status == 1) {
                    tableReload(node.pId);
                    layer.closeAll();
                    layer.msg('删除成功！', {icon: 1});

                } else {
                    layer.alert(data.msg);
                }
            },
            error: function (data, status, e) {   //提交失败自动执行的处理函数
                layer.close(index);
                layer.alert(String(e));
            }
        });
    });

});

function layer_show(_title, _url, w, h,type) {
    if (_title == null || _title == '') {
        title = false;
    }
    if (_url == null || _url == '') {
        url = "404.html";
    }
    if (w == null || w == '') {
        w = 800;
    }
    if (h == null || h == '') {
        h = ($(window).height() - 50);
    }
    var pop=layer.open({
        type: type,
        title: "|&nbsp;" + _title,
        maxmin: true,
        area: [w, h],
        shadeClose: false, //点击遮罩关闭
        content: _url,
        cancel:function () {
            popInit();
        }
    });
}
//弹窗初始化
function popInit() {
    $("#add-edit").attr("data-type",0).attr("data-value","");
    $("#planName").val("");
    $("#sortNum").val("");
}

//导入
$("#import_link").click(function () {
    var _url = ctx + '/projectStructure/toImport?constructionId=' + constructionId;
    var _title = '工程结构导入';
    var iframeWidth = '400px';
    var iframeHeight = '300px';
    layer_show(_title, _url, iframeWidth, iframeHeight,2);
});

//下载模版
$("#template_link").click(function () {
    var link = ctx+'/resources/static/download/project_structure_template.xlsx';
    window.location.href=link;
});