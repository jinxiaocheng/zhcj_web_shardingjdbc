<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>新增工程信息</title>
    <link type="text/css" href="${ctx}/resources/static/css/public.css?v=${v}" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/scrollbar.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/static/plugins/DatePicker/skin/WdatePicker.css?v=${v}" />
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/fileInput/fileinput.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/quality/add.css?v=${v}">
    <link rel="stylesheet" href="${ctx}/resources/static/css/common/mapBox.css?v=${v}">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->
    <script src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}" type="text/javascript"></script>
    <style>
        form{
            padding:15px;
            box-sizing: border-box;
            width: 100%;
        }
        .form-group>label{
            padding: 6px 0;
            text-align: right;
            padding-right: 20px;
        }
        .form-horizontal .form-group{
            margin-right:0px;
            margin-left:0px;
        }
        #tree{
            max-height: 300px;
        }
        .btn.btn-default.kv-fileinput-upload.fileinput-upload-button{
            display: none;
        }
    </style>
</head>
<script type="text/javascript">

</script>
<body>
<form id="constructionForm" class="form-horizontal add_form">
    <div class="row" >
        <div class="form-group row">
            <label class="col-xs-2">施工许可证号</label>
            <div class="col-xs-10">
                <input type="text" class="form-control" kk-prop="value" id="constructLicenseNo" name="constructLicenseNo">
            </div>

        </div>
    </div>
    <div class="row">
        <div class="form-group row" kk-contral="require"    >
            <label class="col-xs-2">工程名称</label>
            <div class="col-xs-10">
                <input type="text" class="form-control" kk-prop="value" id="projectName" name="projectName">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-xs-6">
            <label class="col-xs-4">建设地点</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" id="developeSite" name="developeSite">
            </div>
        </div>
        <div class="form-group col-xs-6 queryCondition" kk-contral="require">
            <label class="col-xs-4">区域</label>
            <button onclick="showMenu(event); return false;" kk-prop="kk-areaId" kk-areaId=""  class=" col-xs-8 btn btn-default dropdown-toggle" type="button" id="handlePerson" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                请选择区域
                <span class="caret"></span>
            </button>
            <ul id="tree" class="k_dropdown ztree" aria-labelledby="dropdownMenu1">
            </ul>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-xs-6" >
            <label class="col-xs-4">建设单位</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" kk-prop="value" id="development" name="development">
            </div>
        </div>
        <div class="form-group col-xs-6" >
            <label class="col-xs-4">施工单位</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" kk-prop="value" id="building" name="building">
            </div>
        </div>

    </div>
    <div class="row">
        <div class="form-group col-xs-6">
            <label class="col-xs-4">设计单位</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" id="designing" name="designing">
            </div>
        </div>
        <div class="form-group col-xs-6" >
            <label class="col-xs-4">监理单位</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" kk-prop="value" id="supervision" name="supervision">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-xs-6">
            <label class="col-xs-4">勘察单位</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" kk-prop="value" id="prospecting" name="prospecting">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-xs-6">
            <label class="col-xs-4">勘察单位负责人</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" id="prospectingHeader" name="prospectingHeader">
            </div>
        </div>
        <div class="form-group col-xs-6" >
            <label class="col-xs-4">项目经理</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" kk-prop="value" id="projectManager" name="projectManager">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-xs-6">
            <label class="col-xs-4">设计单位负责人</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" id="designingHeader" name="designingHeader">
            </div>
        </div>
        <div class="form-group col-xs-6">
            <label class="col-xs-4">监理总监</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" id="superviseDirector" name="superviseDirector">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-xs-6">
            <label class="col-xs-4">建设规模(平方米)</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" kk-RE="int" kk-name="建设规模(平方米)" id="developeScale" name="developeScale">
            </div>
        </div>
        <div class="form-group col-xs-6">
            <label class="col-xs-4">合同价格(万元)</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" kk-RE="num" kk-name="合同价格(万元)" id="contractPrice" name="contractPrice">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-xs-6" kk-contral="require">
            <label class="col-xs-4">合同开工日期</label>
            <div class="col-xs-8">
                <input type="text" class="form-control w-110" kk-prop="value" id='contractStartDate' name='contractStartDate' readonly="readonly"
                       onclick="WdatePicker({maxDate:'#F{$dp.$D(\'contractEndDate\')}'})" placeholder="选择合同开工日期" dategroup="true" />
            </div>
        </div>
        <div class="form-group col-xs-6"  kk-contral="require">
            <label class="col-xs-4">合同竣工日期</label>
            <div class="col-xs-8">
                 <input type="text" class="form-control w-110" kk-prop="value" id='contractEndDate' name='contractEndDate' readonly="readonly"
                        onclick="WdatePicker({minDate:'#F{$dp.$D(\'contractStartDate\')}'})" placeholder="选择合同竣工日期" dategroup="true" />
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-xs-6"  kk-contral="require">
            <label class="col-xs-4">计划开工日期</label>
            <div class="col-xs-8">
                <input type="text" class="form-control w-110" kk-prop="value" id='planStartDate' name='planContractStartDate' readonly="readonly"
                       onclick="WdatePicker({maxDate:'#F{$dp.$D(\'planEndDate\')}'})" placeholder="选择计划开工日期" dategroup="true" />
            </div>
        </div>
        <div class="form-group col-xs-6"  kk-contral="require">
                <label class="col-xs-4">计划竣工日期</label>
            <div class="col-xs-8">
                <input type="text" class="form-control w-110" kk-prop="value" id='planEndDate' name='planContractEndDate' readonly="readonly"
                       onclick="WdatePicker({minDate:'#F{$dp.$D(\'planStartDate\')}'})" placeholder="选择计划竣工日期" dategroup="true" />
            </div>
        </div>
    </div>

    <div class="row">
        <div class="form-group col-xs-6" >
            <label class="col-xs-4">发证日期</label>
            <div class="col-xs-8">
                 <input type="text" class="form-control w-110" kk-prop="value" id='licenseDate' name='licenseDate' auto_color_flag="true" datatype="*" readonly="readonly"
						onclick="WdatePicker()" placeholder="选择开始时间" dategroup="true" />
            </div>
        </div>
        <div class="form-group col-xs-6">
            <label class="col-xs-4">发证机关</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" id="licenseIssue" name="licenseIssue">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-xs-6" kk-contral="require">
            <label class="col-xs-4">工地经度</label>
            <div class="col-xs-8 input-group">
                <input type="text" class="form-control"  kk-prop="value" id="longitude" name="lng">
                <div class="input-group-addon openMap" style="cursor: pointer">点击获取经纬度</div>
            </div>
        </div>
        <div class="form-group col-xs-6" kk-contral="require">
            <label class="col-xs-4">工地纬度</label>
            <div class="col-xs-8 input-group">
                <input type="text" class="form-control"  kk-prop="value" id="latitude" name="lat">
                <div class="input-group-addon openMap" style="cursor: pointer">点击获取经纬度</div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group row">
            <label class="col-xs-2">备注</label>
            <div class="col-xs-10">
                <textarea class="form-control" rows="3" id="remark" name="remark" maxlength="500" placeholder="请填写备注(最多输入500字)"  kk-RE="textLength/0-500" kk-name="备注"></textarea>
            </div>

        </div>
    </div>
    <div class="row" >
        <div class="form-group row submit_img">
            <label class="col-xs-2">工地图标</label>
            <div class="col-xs-10  form-group">
                <input id="file" type="file" class="file"  >
            </div>
        </div>
    </div>
    <%--<div class="form-group submit_img">
        <input id="file" type="file" class="file">
    </div>--%>
    <div style="height: 60px;width: 100%"></div>

</form>
<footer>
    <a class="btn btn_submit btn-show-blue" id="constructionButton">提&nbsp;交</a>
</footer>
 <%--警告--%>
<div class="alert alert-danger alert-dismissable error">
    <button type="button" class="close" aria-hidden="true">
        &times;
    </button>
    <span>警告！请不要提交。</span>
</div>
<%--加载--%>
<div class="my_load">
    <img src="${ctx}/resources/static/images/ajax-loader.gif" alt="">
</div>
<%--检查项及结果弹出层--%>
<div id="mapBox">
    <div class="form-inline searchEle">
        <label for="cityName">地点</label>&nbsp;
        <input type="text" class="form-control" id="cityName" placeholder="请输入地点">
        <button type="submit" style="color: #a1a1a1" class="btn btn-primary searchCity">查询</button>
    </div>
    <div id="map" class="map">
        <p>加载中。。。</p>
    </div>
    <div class="form-inline ">
        <div class="form-group">
            <label for="cityName">经度</label>
            <input type="text" class="form-control" id="lng">
        </div>&nbsp;
        <div class="form-group">
            <label for="cityName">纬度</label>
            <input type="text" class="form-control" id="lat">
        </div>
    </div>

</div>

<script>
    var ctx = "${ctx}",bool=true,files=[],
        toolsType="${type}", // 当type =1 时，代表修改
        constructionId=""; // 工地id
</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}" ></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.excheck-3.5.js?v=${v}"></script>
<script src="http://api.map.baidu.com/getscript?v=2.0&ak=3bb801b79151585f9534ed9ed7ff280d&services=&t=20171014112628"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/fileInput/fileinput.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/biYue.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/fileInput/fileinput_locale_de.js?v=${v}"></script>

<script>
	$(function () {
	    $.ajax({
	        cache:true,
	        type: 'GET',
	        dataType : "json",
	        url:ctx + "/territory/queryCityTree",
	        error: function () {
	            console.log('查询目录请求失败!');
	        },
	        success:function(data){
	            if(data.value){
	                //生成树形图
	                $.fn.zTree.init($("#tree"), setting, data.value);
	                treeObj = $.fn.zTree.getZTreeObj("tree");
	            }
	        }
	    });

        $("#constructionForm").kk_tableForm("#constructionButton",my_ajax);   // 执行提交事件

        getLngLat(); // 获取经纬度



        if(toolsType==1){loadPageData(function () {
            createMap(); // 创建百度地图
        })}else{
            createMap(); // 创建百度地图
        } // 修改页面加载数据

	})

    // 通过url获取的属性
    function getUrlProp(){
	    var getPropObj={}
	    var getPropData=window.location.search.replace("?","").split("&");
	    for(var i=0;i<getPropData.length;i++){
	        var interimData=getPropData[i].split("=");
            getPropObj[interimData[0]]=interimData[1]||"";
        }
	    return getPropObj;
    }

    // 当是修改页面(toolsType=1时)
    function loadPageData(fun) {
        var loadIndex=layer.load();// 弹出加载框
        constructionId=getUrlProp().constructionId;
        $.ajax({
            async : false,
            cache:false,
            type: 'post',
            dataType : "json",
            data:{"constructionId":constructionId},
            url:ctx + "/construction/toConstructionById",
            error: function () {
                console.log('查询目录请求失败!');
                layer.close(loadIndex); // 关闭加载框
            },
            success:function(data){
                if(data.status==1){
                    if(data.value){
                        data=data.value;
                        $("[name=constructLicenseNo]").val(data.constructLicenseNo); // 施工许可证号
                        $("[name=projectName]").val(data.projectName); // 工程名称
                        $("[name=developeSite]").val(data.developeSite); // 建设地点
                        $("#handlePerson").html(data.areaName); // 城市名称
                        $("#handlePerson").attr("kk-areaId",data.areaId); // 区域id
                        $("#handlePerson").attr("kk-cityId",data.cityId); // 城市id
                        $("[name=development]").val(data.development); // 建设单位
                        $("[name=building]").val(data.building); // 施工单位
                        $("[name=designing]").val(data.designing); // 设计单位
                        $("[name=supervision]").val(data.supervision); // 监理单位
                        $("[name=prospecting]").val(data.prospecting); // 勘察单位
                        $("[name=prospectingHeader]").val(data.prospectingHeader); // 勘察单位负责人
                        $("[name=projectManager]").val(data.projectManager); // 项目经理
                        $("[name=designingHeader]").val(data.designingHeader); // 设计单位负责人
                        $("[name=superviseDirector]").val(data.superviseDirector); // 监理总监
                        $("[name=developeScale]").val(data.developeScale); // 建设规模(平方米)
                        $("[name=contractPrice]").val(data.contractPrice); // 合同价格(万元)
                        $("[name=contractStartDate]").val(data.contractStartDate); // 合同开工日期
                        $("[name=contractEndDate]").val(data.contractEndDate); // 合同竣工日期
                        $("[name=planContractStartDate]").val(data.planContractStartDate); // 计划开工日期
                        $("[name=planContractEndDate]").val(data.planContractEndDate); // 计划竣工日期
                        $("[name=licenseDate]").val(data.licenseDate); // 发证日期
                        $("[name=licenseIssue]").val(data.licenseIssue); // 发证机关
                        $("[name=lng]").val(data.lng); // 工地经度
                        $("[name=lat]").val(data.lat); // 工地纬度
                        $("[name=remark]").val(data.remark); // 备注
                        $(".submit_img").hide();
                    }
                    fun&&fun();
                    layer.close(loadIndex); // 关闭加载框
                }else{
                    console.error('请求错误:'+e);
                    layer.close(loadIndex); // 关闭加载框
                }
            }
        });
    }

    // 点击获取地图经纬度
    function getLngLat() {
        $(".openMap").on("click",function(){
            $("#mapBox").show();
            $("#lng").val($("#longitude").val());
            $("#lat").val($("#latitude").val());
            var my_open=layer.open({
                    type: 1,
                    anim:5,
                    title:'获取经纬度',
                    content: $('#mapBox'),
                    area: ['90%','90%'],
                    btn:['确定'],
                    yes:function(){
                        $("#longitude").val( $("#lng").val()); // 获取经度
                        $("#latitude").val( $("#lat").val()); // 获取纬度
                        $("#mapBox").hide(); // 隐藏窗口
                        layer.close(my_open);// 关闭弹窗
                    }
                });
        })

    }

    //创建地图
    function  createMap() {
        // 百度地图API功能
        var map = new BMap.Map("map");    // 创建Map实例
        map.centerAndZoom(new BMap.Point(113.897643, 30.686956), 11);  // 初始化地图,设置中心点坐标和地图级别
        // 添加地图类型控件
        map.addControl(new BMap.MapTypeControl({
            mapTypes:[
                BMAP_NORMAL_MAP,
                BMAP_HYBRID_MAP
            ]}));
        map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
        map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

        // 搜索城市位置
        $(".searchCity").click(function(){
            var cityName=$("#cityName").val();
            if(cityName != ""){
                setPlace(cityName);      // 用城市名设置地图中心点
            }
        })
        //定位
        function setPlace(value) {
            var local, point, marker = null;
            local = new BMap.LocalSearch(map, { //智能搜索
                onSearchComplete: fn
            });

            local.search(value);

            function fn() {
                //如果搜索的有结果
                if(local.getResults() != undefined) {
                    map.clearOverlays(); //清除地图上所有覆盖物
                    if(local.getResults().getPoi(0)) {
                        point = local.getResults().getPoi(0).point; //获取第一个智能搜索的结果
                        map.centerAndZoom(point, 18);
                        marker = new BMap.Marker(point); // 创建标注
                        map.addOverlay(marker); // 将标注添加到地图中
                        marker.enableDragging(); // 可拖拽
                        $("#lng").val(point.lng);
                        $("#lat").val(point.lat);
                    } else {
                        layer.msg('未匹配到精确地点!', {time: 2000, icon: 0});
                    }
                } else {
                    layer.msg('未找到搜索结果!', {time: 2000, icon: 0});
                }
            }
        }
        //设置点击点
        ;(function () {
            var lng = $("#longitude").val(),lat = $("#latitude").val();
            if(lng&&lat){
                var markerFirst = new BMap.Marker(new BMap.Point(lng,lat));
                map.addOverlay(markerFirst);
            }
        })();

        // 点击获取经纬度
        map.addEventListener("click", function(e){
            $("#lng").val(e.point.lng);
            $("#lat").val(e.point.lat);
            var marker = new BMap.Marker(new BMap.Point(e.point.lng,e.point.lat));
            map.clearOverlays();
            map.addOverlay(marker);
        });
    }

    // 文件上传设置
    function setUploadFile() {
        $("#file").fileinput({
            language: 'zh', //设置语言
            allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
            //uploadExtraData:{"id": 1, "fileName":'123.mp3'},
            uploadAsync: true, //默认异步上传
            showUpload: true, //是否显示上传按钮
            showRemove : true, //显示移除按钮
            showPreview : true, //是否显示预览
            showCaption: false,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            dropZoneEnabled: false,//是否显示拖拽区域
            //minImageWidth: 50, //图片的最小宽度
            //minImageHeight: 50,//图片的最小高度
            //maxImageWidth: 1000,//图片的最大宽度
            //maxImageHeight: 1000,//图片的最大高度
            //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
            //minFileCount: 0,
            maxFileCount: 1, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        });
    }

    // 获取图片数据
    $('#file').on('filepreupload', function(event, data, previewId, index) {
        files=[];
        files=data.files;
    });

 function my_ajax(){
     var data = getQueryJson();
     $.ajax({
         type: "post",
         url: ctx + "/construction/constructionAdd",
         data: data,
         processData: false,
         contentType: false,
         dataType: "json",
         success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
             if(data.status == 1){
                 if(biyue.urlSearch().type==='1'){
                     layer.alert("编辑成功！",function(){ layer_close();});
                 }else{
                     layer.alert("新增成功！",function(){ layer_close();});
                 }

             } else {
                 layer.alert(data.msg);
             }
             $(".my_load").hide();
         },
         error: function (data, status, e) {   //提交失败自动执行的处理函数
             layer.alert(e);
             $(".my_load").hide();
         }
     });

}
/*关闭弹出框口*/
function layer_close(){
    biyue.layui_close();
}
//表单数据
function getQueryJson() {
    var formData = new FormData($("#constructionForm")[0]),
        areaId=$("#handlePerson").attr("kk-areaId"),//区域id
        cityId=$("#handlePerson").attr("kk-cityId");//城市id
    //打包数据
    formData.append("areaId",areaId); //区域
    formData.append("cityId",cityId); //区域
    if($("#file")[0].files.length>0){
        formData.append("file",$("#file")[0].files[0]); //图片
    }
    //修改页面时，提交的id
    if(toolsType==1){
        formData.append("constructionId",constructionId);
        formData.append("id",getUrlProp().constructLicenseId);
    }

    return formData;
};
/* 对区域选择下拉框的控制*/
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
//属性图的配置
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
//点击获取属性图数据
function beforeClick(treeId, treeNode) {
    var check = (treeNode && !treeNode.isParent);
    return check;
}

function onClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("tree"),
        nodes = zTree.getSelectedNodes(),
        v = "",
        k_id="";
    nodes.sort(function compare(a,b){return a.id-b.id;});
    for (var i=0, l=nodes.length; i<l; i++) {
        v += nodes[i].name + ",";
    }
    if (v.length > 0 ) v = v.substring(0, v.length-1);
    var cityObj = $("#handlePerson");
    cityObj.html(v+'<span class="caret"></span>');
    cityObj.attr("kk-areaId",treeNode.id);
    cityObj.attr("kk-cityId",treeNode.getParentNode().id);
    hideMenu();

}

</script>
</body>
</html>
