<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>视频显示</title>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}" />
    <script>
        document.write("<link type='text/css' href='${ctx}/resources/static/css/video/video.css?version=" + new Date().getTime() + "' rel='stylesheet' />");
    </script>
    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
    <![endif]-->
</head>
<style>
    html, body, .container-full {
        margin: 0;
        padding: 0;
        width: 99.9%;
        height: 99.6%;
        overflow-x: hidden;
        overflow-y: hidden;
    }

    .container-full {
        margin-top: 5px;
    }

    @media ( max-width : 1024px) {
        .container-full {
            margin-top: 3px;
        }
    }

    .menu-left-tree {
        float: left;
        width: 300px;
        overflow: auto;
        height: 99%;
    }

    #video_contorl{
        width:500px;
        margin-left: 50px;
    }

    .ztree {
        overflow: auto;
        margin-right: 10px;
        height: 98%;
    }
</style>
<body>

<div class="container-full">

    <div class="menu-left-tree">
        <ul id="orgnaizeTree" class="ztree"></ul>
    </div>

    <div id="divPlugin" class="left" style="margin-left: 20px; height: 100%"></div>

    <div id="video_contorl" class="left">
        <br>
        <br>
        <fieldset class="preview">
            <legend>通道选择</legend>
            <table cellpadding="0" cellspacing="3" border="0">
                <tr>
                    <td style="display: none"><input id="ip" type="text"/></td>
                    <td class="tt">通道列表</td>
                    <td>
                        <select id="channels" class="sel" onchange="changeChannel()">
                        </select>
                    </td>
                    <td class="tt">窗口分割数</td>
                    <td>
                        <select class="sel2" onchange="changeWndNum(this.value);">
                            <option value="1">1x1</option>
                            <option value="2" selected>2x2</option>
                            <option value="3">3x3</option>
                            <option value="4">4x4</option>
                        </select>
                    </td>
                    </td>
                </tr>
            </table>
        </fieldset>
        <br>
        <br>
        <fieldset class="preview">
            <legend>预览</legend>
            <table cellpadding="0" cellspacing="10" border="0">
                <tr>
                    <td class="tt">清晰度</td>
                    <td><select id="streamtype" class="sel"
                                onchange="changeChannel()">
                        <option value="1">高清</option>
                        <option value="2" selected="selected">流畅</option>
                    </select></td>
                </tr>
                <tr>
                    <td colspan="3">
                        <input type="button" class="btn" value="开始预览" onclick="clickStartRealPlay();" />
                        <input type="button" class="btn" value="停止预览" onclick="clickStopRealPlay();" />
                        <input type="button" class="btn" value="全屏" onclick="clickFullScreen();" />
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <input type="button" class="btn2" value="抓图" onclick="clickCapturePic();" />
                        <input type="button" class="btn2" value="开始录像" onclick="clickStartRecord();" />
                        <input type="button" class="btn2" value="停止录像" onclick="clickStopRecord();" />
                        <br>
                        保存路径: <i id="path">C:\Documents and Settings\Administrator\Web</i></td>
                </tr>
            </table>
        </fieldset>
        <br>
        <fieldset class="ptz">
            <legend>云台控制</legend>
            <table cellpadding="0" cellspacing="3" border="0" class="left">
                <tr>
                    <td><input type="button" class="btn" value="左上"
                               onmousedown="mouseDownPTZControl(5);"
                               onmouseup="mouseUpPTZControl();" />
                        <input type="button"
                               class="btn" value="上" onmousedown="mouseDownPTZControl(1);"
                               onmouseup="mouseUpPTZControl();" />
                        <input type="button"
                               class="btn" value="右上" onmousedown="mouseDownPTZControl(7);"
                               onmouseup="mouseUpPTZControl();" />
                    </td>
                </tr>
                <tr>
                    <td><input type="button" class="btn" value="左"
                               onmousedown="mouseDownPTZControl(3);"
                               onmouseup="mouseUpPTZControl();" /> <input type="button"
                                                                          class="btn" value="自动" onclick="mouseDownPTZControl(9);" /> <input
                            type="button" class="btn" value="右"
                            onmousedown="mouseDownPTZControl(4);"
                            onmouseup="mouseUpPTZControl();" /></td>
                </tr>
                <tr>
                    <td><input type="button" class="btn" value="左下"
                               onmousedown="mouseDownPTZControl(6);"
                               onmouseup="mouseUpPTZControl();" /> <input type="button"
                                                                          class="btn" value="下" onmousedown="mouseDownPTZControl(2);"
                                                                          onmouseup="mouseUpPTZControl();" /> <input type="button"
                                                                                                                     class="btn" value="右下" onmousedown="mouseDownPTZControl(8);"
                                                                                                                     onmouseup="mouseUpPTZControl();" /></td>
                </tr>
            </table>
            <table cellpadding="0" cellspacing="3" border="0" class="left">
                <tr>
                    <td class="tt">云台速度</td>
                    <td><select id="ptzspeed" class="sel">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option selected>4</option>
                        <option>5</option>
                        <option>6</option>
                        <option>7</option>
                    </select></td>
                </tr>
                <tr>
                    <td class="tt">预置点号</td>
                    <td><input id="preset" type="text" class="txt" value="1" /></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="button" class="btn" value="设置"
                                           onclick="clickSetPreset();" /> <input type="button" class="btn"
                                                                                 value="调用" onclick="clickGoPreset();" /></td>
                </tr>
            </table>
            <table cellpadding="0" cellspacing="3" border="0" class="left">
                <tr>
                    <td class="tt"><input type="button" class="btn2" value="变倍+"
                                          onmousedown="PTZZoomIn()" onmouseup="PTZZoomStop()"></td>
                    <td><input type="button" class="btn2" value="变倍-"
                               onmousedown="PTZZoomout()" onmouseup="PTZZoomStop()"></td>
                </tr>
                <tr>
                    <td class="tt"><input type="button" class="btn2" value="变焦+"
                                          onmousedown="PTZFocusIn()" onmouseup="PTZFoucusStop()"></td>
                    <td><input type="button" class="btn2" value="变焦-"
                               onmousedown="PTZFoucusOut()" onmouseup="PTZFoucusStop()"></td>
                </tr>
                <tr>
                    <td class="tt"><input type="button" class="btn2" value="光圈+"
                                          onmousedown="PTZIrisIn()" onmouseup="PTZIrisStop()"></td>
                    <td><input type="button" class="btn2" value="光圈-"
                               onmousedown="PTZIrisOut()" onmouseup="PTZIrisStop()"></td>
                </tr>
            </table>
        </fieldset>
        <fieldset class="playback" style="display: none">
            <legend>回放</legend>
            <table width="100%" cellpadding="0" cellspacing="3" border="0">
                <tr>
                    <td class="tt">开始时间</td>
                    <td><input id="starttime" type="text" class="txt"
                               value="2013-12-10 00:00:00" />（时间格式：2013-11-11 12:34:56）</td>
                </tr>
                <tr>
                    <td class="tt">结束时间</td>
                    <td><input id="endtime" type="text" class="txt"
                               value="2013-12-11 23:59:59" /> <input type="button" class="btn"
                                                                     value="搜索" onclick="clickRecordSearch(0);" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div class="searchdiv">
                            <table id="searchlist" class="searchlist" cellpadding="0"
                                   cellspacing="0" border="0"></table>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><input type="button" class="btn2"
                                           value="开始回放" onclick="clickStartPlayback();" /> <input
                            type="button" class="btn2" value="停止回放"
                            onclick="clickStopPlayback();" /> <input type="button"
                                                                     class="btn" value="倒放" onclick="clickReversePlayback();" /> <input
                            type="button" class="btn" value="单帧" onclick="clickFrame();" />
                        <input id="transstream" type="checkbox" class="vtop" checked="false"/>&nbsp;启用转码码流
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><input type="button" class="btn" value="暂停"
                                           onclick="clickPause();" /> <input type="button" class="btn"
                                                                             value="恢复" onclick="clickResume();" /> <input type="button"
                                                                                                                           class="btn" value="慢放" onclick="clickPlaySlow();" /> <input
                            type="button" class="btn" value="快放" onclick="clickPlayFast();" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><input type="button" class="btn" value="抓图"
                                           onclick="clickCapturePic();" /> <input type="button"
                                                                                  class="btn2" value="开始剪辑" onclick="clickStartRecord();" /> <input
                            type="button" class="btn2" value="停止剪辑"
                            onclick="clickStopRecord();" /> <input type="button"
                                                                   class="btn2" value="OSD时间" onclick="clickGetOSDTime();" />&nbsp;<input
                            id="osdtime" type="text" class="txt" readonly /></td>
                </tr>
            </table>
        </fieldset>
        <fieldset class="maintain" style="display: none">
            <legend>系统维护</legend>
            <table width="100%" cellpadding="0" cellspacing="3" border="0">
                <tr>
                    <td><input type="button" class="btn2" value="导出配置文件"
                               onclick="clickExportDeviceConfig();" /> <input type="button"
                                                                              class="btn2" value="检查插件版本" onclick="clickCheckPluginVersion();" />
                        <input type="button" class="btn2" value="远程配置库"
                               onclick="clickRemoteConfig();" /> <input type="button"
                                                                        class="btn2" value="恢复默认参数" onclick="clickRestoreDefault();" />
                    </td>
                </tr>
                <tr>
                    <td><input id="configFile" type="text" class="txt" />&nbsp;<input
                            type="button" class="btn" value="浏览"
                            onclick="clickOpenFileDlg('configFile', 1);" />&nbsp;<input
                            type="button" class="btn2" value="导入配置文件"
                            onclick="clickImportDeviceConfig();" /></td>
                </tr>
                <tr>
                    <td><input id="upgradeFile" type="text" class="txt" />&nbsp;<input
                            type="button" class="btn" value="浏览"
                            onclick="clickOpenFileDlg('upgradeFile', 1);" />&nbsp;<input
                            type="button" class="btn2" value="升级"
                            onclick="clickStartUpgrade();" /></td>
                </tr>
            </table>
        </fieldset>
        <fieldset class="operate" style="display: none">
            <legend>操作信息</legend>
            <div id="opinfo" class="opinfo"></div>
        </fieldset>
        <fieldset class="callback" style="display: none">
            <legend>事件回调信息</legend>
            <div id="cbinfo" class="cbinfo"></div>
        </fieldset>
    </div>
</div>

<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery-1.4.4.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/video/preview_nvr.js?v=${v}" id="reloadNvrJs"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/webVideoCtrl.js?v=${v}" id="reloadJs"></script>

<script type="text/javascript">

    var ctx = "${ctx}";

    //通道数据存在数组中
    var chnname = new Array();

    $(function(){
        videoWidth();
        $(window).resize(function(){
            videoWidth();
        });

        initTreeWidget();

    });

    /*var reloadJs = function(id) {
        var jsObj = document.getElementById(id);
        var src = jsObj.src;
        alert(src);
        $("#" + id).remove();
        //重新加载
        var script = document.createElement("script");
        script.src = src;
        script.id = id;
        document.body.appendChild(script);

    }*/

    function videoWidth() {
        var body_w = $("body").width();
        var menu_left_tree = $(".menu-left-tree").outerWidth();
        var video_c_w = $("#video_contorl").width();

        var video_w = body_w - menu_left_tree - video_c_w-100;
        $("#divPlugin").width(video_w);
    }

    var $tree = $("#orgnaizeTree");
    var zTreeObj;

    var setting = {
        view: {
            dblClickExpand:false,
            showLine:true,
            selectedMulti:false
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
            onDblClick : function(event, treeId, treeNode) {
                nodeClick(event, treeId, treeNode);
            }
        }
    };

    //发送获取树节点请求
    var initTreeWidget = function(){
        var url = "${ctx}/video/nvrPreview/fetchTreeNodeList";
        var param = {"cityId":"${cityId}"};
        $.post(url,param,function(data) {
            if(data.status == 1){
                var json = data.value;
                zTreeObj = $.fn.zTree.init($tree, setting, json);
            } else{
                alert(data.msg)
            }
        });
    };

    //点击预览视频
    var nodeClick = function(event, treeId, treeNode) {
        if(treeNode && treeNode.level == 1) {
            var nvrVO = treeNode.nvrVO;

            //获取已经登录的nvr的ip
            var ip = $("#ip").val();
            if(ip != null && ip != "") {
                StopRealPlay();
                clickLogout(ip);
//                    $("#divPlugin").html("");
            }

//                WebVideoCtrl.I_InsertOBJECTPlugin("divPlugin");
//
//                WebVideoCtrl.I_InitPlugin("100%", "98%");

            //登录nvr
            clickLogin(nvrVO);

        } else if (treeNode && treeNode.level == 2) {
            var parentNode = treeNode.getParentNode();
            var nvrVO = parentNode.nvrVO;
//                alert(nvrVO.ip);
            var channelNo = treeNode.channelNo;
            loginRealPlay(nvrVO,channelNo);
        }
    }

</script>
</body>
</html>












