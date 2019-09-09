<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>人员维护-新增-编辑</title>
    <link rel="stylesheet" href="${ctx}/resources/static/css/public.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/layui/css/layui.css?v=${v}"/>
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/pop.css?v=${v}"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
</head>
<body class="biyue-bg">
<form class="layui-form pop-box pop-box-special" lay-filter="biyueOpen" action="#" method="post" enctype="multipart/form-data">    <h2 class="title"><p>考勤识别方式</p></h2>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <input type="radio" name="attendanceType" lay-filter="attendanceType" value="0" title="无识别方式">
            <input type="radio" name="attendanceType" lay-filter="attendanceType" value="1" title="刷卡" checked>
            <input type="radio" name="attendanceType" lay-filter="attendanceType" value="2" title="人脸识别">
            <input type="radio" name="attendanceType" lay-filter="attendanceType" value="3" title="刷卡/人脸识别">
        </div>
    </div>
    <h2 class="title area"><p>区域工地</p></h2>
    <h2 class="title area_edit" style="display: none"><p>区域工地</p></h2>
    <div class="layui-form-item">
        <div class="layui-inline area">
            <label class="layui-form-label">区域</label>
            <div class="layui-input-inline">
                <select name="area" lay-search lay-verify="required" lay-filter="area">
                    <option value="">选择区域</option>
                </select>
            </div>
        </div>
        <div class="layui-inline site">
            <label class="layui-form-label">工地</label>
            <div class="layui-input-inline">
                <select name="site" lay-search lay-verify="required" lay-filter="site">
                    <option value="">选择工地</option>
                </select>
            </div>
        </div>
        <div class="layui-inline area_edit" style="display: none">
            <label class="layui-form-label ">区域</label>
            <div class="layui-input-inline ">
                <input type="text" name="areaName" lay-verify="required" class="layui-input" readonly
                       placeholder="区域名称">
                <input type="text" name="areaId" class="layui-input" readonly style="display: none">
            </div>
        </div>
        <div class="layui-inline site_edit" style="display: none">
            <label class="layui-form-label">工地</label>
            <div class="layui-input-inline site_edit">
                <input type="text" name="constructionName" lay-verify="required" class="layui-input" readonly
                       placeholder="工地名称">
                <input type="text" name="constructionId" class="layui-input" readonly style="display: none">
            </div>
        </div>
    </div>
    <h2 class="title"><p>基本信息</p></h2>
    <div class="layui-form-item">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" placeholder="请输入名称" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-inline">
                <button type="button" class="layui-btn" id="personImg">上传头像</button>
                <img src="" class="personImg" style="position: absolute;width: 80px;max-height: 100px;top: 0;left: 110px;" alt="">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-inline">
                    <input type="radio" name="sex" value="1" title="男" checked>
                    <input type="radio" name="sex" value="2" title="女" >
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">手机号码</label>
                <div class="layui-input-inline">
                    <input type="text" name="mobile" placeholder="请输入手机号码" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline company">
                <label class="layui-form-label">所属公司</label>
                <div class="layui-input-inline">
                    <select name="projectCompanyId" lay-search lay-verify="required" lay-filter="company">
                        <option value="">选择公司</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="">岗位/工种</label>
                <div class="layui-input-inline job" style="position: relative">
                    <input type="text" id="job" name="positionWorkTypeName" lay-verify="required" readclick="true" readonly placeholder="点击选择岗位/工种" autocomplete="off" class="layui-input" >
                    <i class="layui-icon layui-icon-close-fill" id="workDel"
                       style="position: absolute;top: 12px;right: 6px;cursor: pointer;z-index: 1000;display: none"></i>
                </div>
            </div>
            <div class="layui-inline team">
                <label class="layui-form-label">班组</label>
                <div class="layui-input-inline">
                    <select name="teamId" lay-search lay-verify="required" lay-filter="team">
                        <option value="">选择班组</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">班组长</label>
                <div class="layui-input-inline">
                    <input type="radio" name="isLeader" value="1" title="是" >
                    <input type="radio" name="isLeader" value="0" title="否" checked>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">身份证号码</label>
                <div class="layui-input-inline">
                    <input type="text" name="idCard" placeholder="请输入身份证号码" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">卡号</label>
                <div class="layui-input-inline">
                    <input type="text" lay-verify="required" name="cardNumber" placeholder="请输入卡号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">银行卡号</label>
                <div class="layui-input-inline">
                    <input type="text" name="bankCard" placeholder="请输入银行卡号" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
    </div>
    <h2 class="title"><p>资质信息</p></h2>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">证书类别</label>
            <div class="layui-input-inline">
                <select name="certificateType" lay-search lay-filter="certificateType">
                    <option value="">选择证书类别</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">证书编号</label>
            <div class="layui-input-inline">
                <input type="text" name="certificateNumber" placeholder="请输入证书编号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">签发日期</label>
            <div class="layui-input-inline">
                <input type="text" name="certificateIssueDate" placeholder="请输入签发日期" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">有效日期</label>
            <div class="layui-input-inline">
                <input type="text" name="certificateValidDate" placeholder="请输入有效日期" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">发证机关</label>
            <div class="layui-input-inline">
                <input type="text" name="certificateIssueOrgans" placeholder="请输入发证机关" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="biyue_footer">
        <div class="control-btn">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="add" id="submit">确定</button>
            <button class="layui-btn layui-btn-primary" type="button" id="com-close">关闭</button>
        </div>
    </div>
</form>

<script>
    var ctx = "${ctx}", userId = "${userId}";
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/jquery.form.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layui/layui.all.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/job.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/person/personAdd.js?v=${v}"></script>
<script>
    $(function () {
        biyue.verify();
        biyue.popDefault();
        biyueAdd.event();
        var area = biyue.area();
        area.area({
            type: "pop",
            fun: function (id) {
                biyueAdd.getCompany(id);
            }
        });
        biyueAdd.updata(); //数据上传
        if (biyue.urlData.type === 'edit') {
            biyueAdd.getData(); //编辑获取数据
        }else{
            biyueAdd.getType();
        }
        biyueAdd.upImg();

        jobBtn();
    })
</script>
</body>
</html>
