(function () {
    var tableBoxH=parseInt($("#tableBox").height());
    var pageNum=parseInt(tableBoxH/30)-2;
    var queryParams=function () {
        return {
            "constructionId": constructionId,
            rowNum: pageNum,
            name:$("#name").val()
        };
    };
    $('#treeTable').treegrid({
        url: ctx + "/projectSchedule/loadTree",
        method: 'post',
        lines: true,
        idField: 'id',
        treeField: 'name',
        fit: true,
        pagination: true,
        queryParams: queryParams(),
        columns: [[
            {field: 'id', title: 'id', width: 0, hidden: true},
            {field: 'name', title: '名称', width: "30%"},
            {field: 'constructionName', title: '工地名称', width: "20%"},
            {field: 'planDays', title: '计划工期', width: "10%"},
            {field: 'realDays', title: '实际工期', width: "10%"},
            {field: 'percent', title: '工程进度', width: "20%",formatter: function(value,row,index){

                if (value!==undefined){
                    if(row.status==2){
                        return '<div class="progress" title="'+value+'%">\n' +
                            '  <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" title="'+value+'%" aria-valuenow="'+value+'" aria-valuemin="0" aria-valuemax="100" style="width: '+value+'%">\n' +
                            value +
                            '  %</div>\n' +
                            '</div>'
                    }else{
                        return '<div class="progress" title="'+value+'%">\n' +
                            '  <div class="progress-bar progress-bar-info progress-bar-striped" role="progressbar"  aria-valuenow="'+value+'" aria-valuemin="0" aria-valuemax="100" style="width: '+value+'%">\n' +
                            value +
                            '  %</div>\n' +
                            '</div>'
                    }
                }
            }},
            {field: 'status', title: '状态', width: "11%",formatter: function(value,row,index){
                if (value==0){
                    return '<span style="color: #afafaf">未开始</span>';
                } else if(value==1) {
                    return '<span style="color: #57b0ff">进行中</span>';
                }else if(value==2) {
                    return '<span style="color: #169d16">已完成</span>';
                }
            }}
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
        , onBeforeExpand: function (node) {
            $('#treeTable').treegrid('options').queryParams = {
                "constructionId": constructionId,
                rowNum: pageNum,
                id:node.id,
                name:$("#name").val()
            };
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
            $('#treeTable').treegrid('options').queryParams = {
                "constructionId": constructionId,
                rowNum: pageNum,
                name:$("#name").val()
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
    //查询
    $("#search_link").click(function () {
        $('#treeTable').treegrid('load',queryParams());
    });
})();

//表格刷新
function tableReload(pid,type) {
    console.log(pid);
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

//修改工程进度
$("#edit_link").on("click",function(){
    var url = $("#add-edit");
    var title = '修改进度计划';
    var node = $('#treeTable').treegrid('getSelected');
    console.log(node);
    if (node == null) {
        layer.msg('请选择列表数据后操作', {time: 2000, icon: 0});
        return;
    }
    if(node.status==2){
        layer.msg('该工程已完工，禁止修改工程进度!', {time: 2000, icon: 0});
        return;
    }
    $("#add-edit").attr("data-value",node.pId+'*'+node.id);
    layer_show(title, url, '680px', '200px',1);
});
//修改工程进度
function addEdit() {
    var uploadData=new Object();
    var node= $('#treeTable').treegrid('getSelected');
    var url =  ctx + '/projectSchedule/update';
    var nodeId=$("#add-edit").attr("data-value").split('*')[0];
    var successText="修改工程进度成功";
    uploadData={
        id:$("#add-edit").attr("data-value").split('*')[1],
        percent:$("#precentNum").val(),
        constructionId:constructionId,
        state:node.state
    };
    var index=layer.load();
    $.ajax({
        type: "post",
        url: url,
        data:uploadData,
        success: function (data) {
            layer.close(index);
            if (data.status == 1) {
                if(!nodeId) nodeId=0;
                tableReload(nodeId);
                layer.alert(successText,function(){
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

//确认完成
$("#confirm_link").click(function () {
    var node= $('#treeTable').treegrid('getSelected');
    if(!node){
        layer.msg('您没有选中任何数据请选择后操作', {time: 2000, icon: 0});
        return;
    }
    layer.confirm('确认修改状态为已完成？', {
        btn: ['是','否'] //按钮
    }, function(){
        var index=layer.load();
        $.ajax({
            type: "post",
            url: ctx + '/projectSchedule/confirmFinish',
            data:{
                id:node.id,
                constructionId:constructionId,
                state:node.state
            },
            success: function (data) {
                layer.close(index);
                if (data.status == 1) {
                    tableReload(node.pId);
                    layer.closeAll();
                    layer.msg('修改成功！', {icon: 1});

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
    $("#precentNum").val("");
}