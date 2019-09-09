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
    <title>岗位/工种</title>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/layui/css/layui.css?v=${v}">
    <style>
        #table-box {
            position: absolute;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            box-sizing: border-box;
            overflow: auto;
        }
        .layui-table-cell .layui-form-radio>i {
            margin-right: 10px;
        }

        #demo {
            position: absolute;
            left: 0;
            right: 0;
            top: 58px;
            bottom: 0;
        }

        #table-box .layui-border-box {
            width: 100%;
            box-sizing: border-box;
        }

        .layui-table-cell {
            padding: 0 10px;
        }

        #submitData {
            position: fixed;
            bottom: 0;
            background: #fff;
            width: 100%;
            height: 35px;
        }

        #submitData a {
            position: absolute;
            right: 10px;
            top: 2px;
            width: 60px;
        }

        .layui-table-body.layui-table-main {
            position: relative;
            top: -38px;
        }
    </style>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
</head>
<body>
<div id="table-box" class="layui-form">
    <div class="layui-form-item " style="margin: 10px 0">
        <label class="layui-form-label" style="width: 60px">岗位/工种</label>
        <div class="layui-input-inline">
            <input type="text" name="name" placeholder="请输入岗位/工种名称" autocomplete="off" class="layui-input">
        </div>
        <label class="layui-form-label" style="width: 40px">类型</label>
        <div class="layui-input-inline">
            <select name="type" lay-verify="required">
                <option value="0">请选择</option>
                <option value="1">岗位</option>
                <option value="2">工种</option>
            </select>
        </div>
        <button class="layui-btn layui-btn-primary search" id="search"><i class="layui-icon"></i> 搜索</button>
    </div>
    <table lay-filter="demo" class="layui-form" id="demo">
        <thead>
        <tr>
            <th lay-data="{field:'1',align:'left'}"></th>
            <th lay-data="{field:'2',align:'left'}"></th>
            <th lay-data="{field:'3',align:'left'}"></th>
            <th lay-data="{field:'4',align:'left'}"></th>
        </tr>
        </thead>
        <tbody>
        <%-- <tr>
             <td><input type="radio" name="sex" value="0" title="技术员"></td>
             <td><input type="radio" name="sex" value="0" title="技术负责人"></td>
             <td><input type="radio" name="sex" value="0" title="电工"></td>
             <td><input type="radio" name="sex" value="0" title="管理员"></td>
             <td><input type="radio" name="sex" value="0" title="技术员"></td>
             <td><input type="radio" name="sex" value="0" title="瓦工"></td>
         </tr>--%>
        </tbody>
    </table>
    <div id="submitData">
        <a class="layui-btn  layui-btn-normal layui-btn-sm">确 定</a>
    </div>
</div>

<script>  var ctx = '${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>

<script>
    var table = layui.table;
    var layer = layui.layer;
    var form = layui.form;

    init(10);

    //初始化
    function init(n) {
        form.render();
        //转换静态表格
        table.init('demo', {
            limit: n
        });
        biyue.scroll("#table-box");
        $('[name="job"][value="' + biyue.urlSearch().workId + '"]').parents(".layui-table-cell").find(".layui-form-radio").click();
    }

    //获取数据
    function getData() {
        biyue.ajax({
            url:"/positionWork/queryByConstructionId",
            data: {
                constructionId: biyue.urlSearch().id,
                name:$('[name="name"]').val(),
                type:$('[name="type"]').val()
            },
            fun: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                if (data.value) {
                    var dataV = data.value, $list = "";
                    for (var j = 0; j < dataV.length; j++) {
                        if ((j % 4) == 0) {
                            $list += '<tr> <td>' + setRadio(dataV[j]) + '</td>\n' +
                                '            <td>' + setRadio(dataV[j + 1]) + '</td>\n' +
                                '            <td>' + setRadio(dataV[j + 2]) + '</td>\n' +
                                '            <td>' + setRadio(dataV[j + 3]) + '</td></tr>'
                        }
                    }
                    $("#table-box tbody").html($list);
                    init(Math.ceil(dataV.length / 4));
                    /*//将数据分为岗位和工种
                    for(var i in data.value){
                        //1位岗位 2位工种
                        if(data.value[i].type==1){
                            postData.push(data.value[i])
                        }else{
                            jobData.push(data.value[i])
                        }
                    }

                    if(postData.length>jobData.length){
                        len=postData.length;
                    }else{
                        len=jobData.length;
                    }

                    for(var j=0;j<len;j++){
                        if((j%2)==0){
                            $list+='<tr> <td>'+setRadio(postData[j])+'</td>\n' +
                                '            <td>'+setRadio(postData[j+1])+'</td>\n' +
                                '            <td>'+setRadio(jobData[j])+'</td>\n' +
                                '            <td>'+setRadio(jobData[j+1])+'</td></tr>'
                        }
                    }
                    $("#table-box tbody").html($list);
                    init(Math.ceil(len/2));*/
                }
            }
        });
    }

    getData();

    //点击查询
    $("#search").click(function () {
        getData();
    })

    //单选框格式
    function setRadio(data) {
        if (data) {
            return '<input type="radio" name="job" value="' + data.id + '" title="' + data.name + '">'
        } else {
            return "";
        }
    }

    //点击确定
    $("#submitData").click(function () {
        var workObj = $("#table-box input[type='radio']:checked")
        var workName = workObj.attr('title');
        var workId = workObj.val();
        parent.get_job(workName, workId);
        parent.layer.closeAll();
    })

</script>
</body>
</html>