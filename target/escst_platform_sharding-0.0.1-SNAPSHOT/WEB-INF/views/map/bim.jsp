<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html style="height: 100%">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge chrome=1"/>
    <title>bim模型</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="icon" href="${ctx}/resources/static/images/logo.ico" type="image/x-icon">
    <link href="${ctx}/resources/static/plugins/bim/sdk/third/css/bootstrap.min.css?v=${v}" rel="stylesheet" type="text/css" />
    <!-- THEME CSS -->
    <link href="${ctx}/resources/static/plugins/bim/sdk/third/css/essentials.css?v=${v}" rel="stylesheet" />
    <link href="${ctx}/resources/static/plugins/bim/sdk/third/css/layout-dark.css?v=${v}" rel="stylesheet" />
    <link href="${ctx}/resources/static/plugins/bim/sdk/third/jstree/themes/proton/style.min.css?v=${v}" rel="stylesheet" />
    <link href="${ctx}/resources/static/plugins/bim/sdk/viz/ui/default.css?v=${v}" rel="stylesheet" type="text/css" />
    <link href="${ctx}/resources/static/plugins/bim/sdk/third/css/jquery-ui-1.10.4.custom.css?v=${v}" rel="stylesheet" />
    <link href="${ctx}/resources/static/plugins/bim/sdk/third/toolbar/jquery.toolbar.css?v=${v}" rel="stylesheet" />

    <script src="${ctx}/resources/static/plugins/bim/sdk/third/jquery-1.10.2.min.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/third/jquery-ui.min.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/third/jquery-dialog.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/third/bootstrap.min.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/third/jstree/jstree.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/third/jquery-slider/jquery-ui-slider-pips.min.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/third/toolbar/jquery.toolbar.js?v=${v}"></script>

    <script src="${ctx}/resources/static/plugins/bim/sdk/viz/bimviz.js?v=${v}"></script>

    <script src="${ctx}/resources/static/plugins/bim/sdk/viz/ui/DefaultMessageControl.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/viz/ui/DefaultToolBar.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/viz/ui/DefaultFileControl.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/viz/ui/DefaultTreesVisibilityControl.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/viz/ui/DefaultDomainControl.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/viz/ui/DefaultPropertyControl.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/viz/ui/DefaultSearchControl.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/viz/ui/DefaultMarkControl.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/viz/ui/DefaultMarkSimpleListControl.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/viz/ui/DefaultBuildingStoreyControl.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/viz/ui/DefaultRoamingSettingControl.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/viz/ui/DefaultTreesHighlightControl.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/viz/ui/DefaultSelectionSetControl.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/viz/ui/DefaultDatabaseQueryControl.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/viz/ui/DefaultCameraBookmarkControl.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/viz/ui/DefaultFixedClipControl.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/viz/ui/DefaultMeasureControl.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/viz/ui/DefaultRectSelectControl.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/bim/sdk/viz/ui/DefaultHtmlMarkerControl.js?v=${v}"></script>

    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
    <![endif]-->

</head>
<body >

<div id="viewport"> </div>
<div id="messages"></div>

<script>

    var url = window.location.href.toLowerCase();
    var https = url.indexOf("https") >= 0;

    var projId = getQueryString("pid");

    var bimEngine = new BIMVIZ.RenderEngine({
        projectId: projId,
        renderDomId: 'viewport',
        ip: "cloud.bimviz.io",
        port:10001,
        key:'6879D622-88E5-46EC-8A00-87731FAA734E',
        resizeMode: 'fullpage',
        resourcePath:'${ctx}/resources/static/plugins/bim/sdk/viz/data/',
        https: 'https'
    });

    // 消息输出控件，包括调试信息，进度信息
    var msgControl = new BIMVIZ.UI.DefaultMessageControl(bimEngine, 'messages');

    // 工具栏
    var toolbar = new BIMVIZ.UI.DefaultToolBar(bimEngine);

    // 添加文件控件
    var fileControl = new BIMVIZ.UI.DefaultFileControl("文件", "fa-file-o");
    toolbar.addControl(fileControl);

    // 添加场景树控件
    var spaceControl = new BIMVIZ.UI.DefaultTreesHighlightControl("场景树", "fa-tree");
    toolbar.addControl(spaceControl);

    // 添加类型控件
    var domainControl = new BIMVIZ.UI.DefaultDomainControl("类型", "fa-map-o");
    toolbar.addControl(domainControl);

    // 添加属性控件
    var propertyControl = new BIMVIZ.UI.DefaultPropertyControl("属性", "fa-info-circle");
    toolbar.addControl(propertyControl);

    // 添加搜索控件
    var searchControl = new BIMVIZ.UI.DefaultSearchControl("搜索", "fa-search");
    toolbar.addControl(searchControl);

    // 添加标注控件
    // 或者可以调用带编辑功能的版本 BIMVIZ.UI.DefaultMarkControl
    var markControl = new BIMVIZ.UI.DefaultMarkControl("标注", "fa-map-marker");
    toolbar.addControl(markControl);

    // 添加楼层控件
    var storeyControl = new BIMVIZ.UI.DefaultBuildingStoreyControl("楼层", "fa-list");
    toolbar.addControl(storeyControl);

    // 场景浏览参数设置
    var roamingControl = new BIMVIZ.UI.DefaultRoamingSettingControl("漫游控制", "fa-send");
    toolbar.addControl(roamingControl);

    // 数据查询分析
    var queryControl = new BIMVIZ.UI.DefaultDatabaseQueryControl("分析查询", "fa-database");
    toolbar.addControl(queryControl);

    // 添加场景树控件
    var visibleControl = new BIMVIZ.UI.DefaultTreesVisibilityControl("可见树", "fa-sitemap");
    toolbar.addControl(visibleControl);

    // 添加选择集控件
    var selectionSetControl = new BIMVIZ.UI.DefaultSelectionSetControl("选择集", "fa-th-large");
    toolbar.addControl(selectionSetControl);

    // 相机书签控件
    var cameraBookmarkControl = new BIMVIZ.UI.DefaultCameraBookmarkControl("相机书签", "fa-camera");
    toolbar.addControl(cameraBookmarkControl);

    // 常规剖切控件
    var fixedClipControl = new BIMVIZ.UI.DefaultFixedClipControl("常规剖切", "fa fa-columns");
    toolbar.addControl(fixedClipControl);

    //测量控件
    var measureControl = new BIMVIZ.UI.DefaultMeasureControl("测量", "fa fa-object-ungroup");
    toolbar.addControl(measureControl);

    // 框选控件
    var rectSelectControl = new BIMVIZ.UI.DefaultRectSelectControl("框选", "fa fa-hand-stop-o");
    toolbar.addControl(rectSelectControl);

    // HTML标记控件
    var htmlMarkerControl = new BIMVIZ.UI.DefaultHtmlMarkerControl("HTML标记","fa fa-flag");
    toolbar.addControl(htmlMarkerControl);

    // 开始加载数据
    bimEngine.start(bimEngine);


</script>
</body>
</html>