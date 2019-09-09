<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>查看详情</title>
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/scrollbar.css?v=${v}">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
    <style>
        body{
            padding: 15px;
        }
        table{
            width: 100px;
            box-sizing: border-box;
        }
        .table th, .table td {
            vertical-align: middle!important;
        }
        table tr td:nth-of-type(1){
            text-align: right;
        }
        table tr td:nth-of-type(3){
            text-align: right;
        }
        img{
            width: 80px;
            height: 80px;
        }
    </style>
</head>

<body class="body_bottom">
<table class="table table-bordered">
    <tr>
        <td >施工许可证:</td>
        <td colspan="3" >${entity.constructLicenseNo }</td>
    </tr>
    <tr>
        <td>工程名称:</td>
        <td colspan="3" >${entity.projectName }</td>
    </tr>
    <tr>
        <td>建设地点:</td>
        <td colspan="3" >${entity.developeSite }</td>
    </tr>
    <tr>
        <td width="20%">建设单位:</td>
        <td width="30%">${entity.development }</td>
        <td width="20%">施工单位</td>
        <td width="30%">${entity.building }</td>
    </tr>
    <tr>
        <td>设计单位:</td>
        <td>${entity.designing }</td>
        <td>监理单位</td>
        <td>${entity.supervision }</td>
    </tr>
    <tr>
        <td>勘察单位:</td>
        <td>${entity.prospecting }</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>勘察单位负责人:</td>
        <td>${entity.prospectingHeader }</td>
        <td>项目经理:</td>
        <td>${entity.projectManager }</td>
    </tr>
    <tr>
        <td>设计单位负责人:</td>
        <td>${entity.designingHeader }</td>
        <td>监理总监:</td>
        <td>${entity.superviseDirector }</td>
    </tr>
    <tr>
        <td>建设规模:</td>
        <td>${entity.developeScale }平方米</td>
        <td>合同价格:</td>
        <td>${entity.contractPrice }万元</td>
    </tr>
    <tr>
        <td>合同开工日期:</td>
        <td>${entity.contractStartDate }</td>
        <td>合同竣工日期:</td>
        <td>${entity.contractEndDate }</td>
    </tr>
    <tr>
        <td>计划开工日期:</td>
        <td>${entity.planContractStartDate }</td>
        <td>计划竣工日期:</td>
        <td>${entity.planContractEndDate }</td>
    </tr>
    <tr>
        <td>发证日期:</td>
        <td>${entity.licenseDate }</td>
        <td>发证机关:</td>
        <td>${entity.licenseIssue }</td>
    </tr>
    <tr>
        <td>经度:</td>
        <td>${entity.lng }</td>
        <td>纬度:</td>
        <td>${entity.lat }</td>
    </tr>
    <tr>
        <td valign="middle" >备注:</td>
        <td colspan="3" style="white-space: pre-line;">${entity.remark }</td>
    </tr>
    <tr>
        <td valign="middle" >工地图标:</td>
        <td colspan="3">
            <c:if test="${ entity.picList == null}">无照片信息</c:if>
                <c:if test="${entity.picList !=null}">
                    <dd >
                        <img class="v_images"  src="${entity.picList}" alt="">
                    </dd>
            </c:if>
        </td>
    </tr>
</table>
<script>var ctx = "${ctx}";</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}" ></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script>
    $(function(){
        //点击放大图片
        $(".v_images").click(function () {
            var imgElement=$(this).parent().children('.v_images');
            var len=imgElement.length,filePath=[];
            var index=$(this).index();
            for(var i=0 ;i<len;i++){
                filePath[i]={src:imgElement.eq(i).attr("src")}
            }
            top.photos(filePath,index);
        });
    })
</script>
</body>
</html>




