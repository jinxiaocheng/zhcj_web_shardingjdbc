<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>EasyNVR</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet"
          href="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/bootstrap/css/bootstrap.min.css?v=${v}">
    <link rel="stylesheet"
          href="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/font-awesome-4.7.0/css/font-awesome.min.css?v=${v}">
    <link rel="stylesheet"
          href="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/ionicons-2.0.1/css/ionicons.min.css?v=${v}">
    <link rel="stylesheet"
          href="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/dist/css/AdminLTE.min.css?v=${v}">
    <link rel="stylesheet"
          href="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/dist/css/skins/skin-green.css?v=${v}">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js?v=${v}"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js?v=${v}"></script>
    <![endif]-->

    <script src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/bootstrap/js/bootstrap.min.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/dist/js/app.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/slimScroll/jquery.slimscroll.min.js?v=${v}"></script>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/iCheck/all.css?v=${v}">
    <script src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/iCheck/icheck.min.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/md5/jquery.md5.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/input-number/jquery.inputnumber.js?v=${v}"></script>
    <link rel="stylesheet"
          href="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/gritter/jquery.gritter.css?v=${v}">
    <script src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/gritter/jquery.gritter.js?v=${v}"></script>
    <link rel="stylesheet"
          href="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/loadmask/jquery.loadmask.css?v=${v}">
    <script src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/loadmask/jquery.loadmask.min.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/validator/validator.min.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/bootbox/bootbox.min.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/cookie/jquery.cookie.min.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/easyNVR/easyui/easyloader.js?v=${v}"></script>
    <link rel="stylesheet"
          href="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/bootstrap-pagination/bootstrap-pagination.css?v=${v}"/>
    <script src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/bootstrap-pagination/bootstrap-pagination.js?v=${v}"></script>

    <link rel="stylesheet" href="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/dist/css/common.css?v=${v}">
    <script src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/dist/js/common.js?v=${v}"></script>
    <script>
        var constructionId = "rt2bfa3a5186f0d30151d3008cb40d99", ctx = "${ctx}";

        function GetQueryString(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]);
            return null;
        }

        var isIntegrate = GetQueryString("isIntegrate");
        if (isIntegrate != null && isIntegrate.toString().length > 1 && isIntegrate == "true") {
            //设置cookie
            if (GetQueryString("token") != null) {
                $.cookie("token", GetQueryString("token"));
            }
            if (GetQueryString("nvrurl") != null) {
                $.cookie("target_host", '/' + GetQueryString("nvrurl"));
            }
            if (GetQueryString("nvruser") != null) {
                $.cookie("username", GetQueryString("nvruser"));
            }
            isIntegrate = true;
        } else {
            isIntegrate = false;
        }
        var host = '192.168.10.53:10800/api/v1';
        var _url = "http://192.168.10.53:10800/api/v1";
        try {
            $.ajax({
                type: "GET",
                url: _url + "/getserverinfo",
                global: false,
                async: false,
                success: function (data) {
                    var ret = JSON.parse(data);
                    console.log(ret)
                    _url = "http://192.168.10.53:10800/api/" + ret.EasyDarwin.Body.InterfaceVersion;
                }
            });
        } catch (e) {
        }

        easyloader.theme = 'bootstrap';
        // easyloader.locale = 'zh_CN';
        easyloader.load(['form']);
    </script>
    <meta name="description" content="直播啦，小伙伴们，快来围观呀！"/>
    <link rel="stylesheet"
          href="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/video-js-5.19.2/video-js.css?v=${v}"/>
    <script src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/video-js-5.19.2/video.js?v=${v}"></script>
    <script src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/video-js-5.19.2/videojs-contrib-hls4.js?v=${v}"></script>

    <script src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/moment/moment-with-locales.js?v=${v}"></script>
    <link rel="stylesheet" type="text/css" media="all"
          href="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/daterangepicker/daterangepicker.css?v=${v}"/>
    <script type="text/javascript"
            src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/daterangepicker/moment.js?v=${v}"></script>
    <script type="text/javascript"
            src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/daterangepicker/daterangepicker.js?v=${v}"></script>
    <script type="text/javascript"
            src="${ctx}/resources/static/plugins/easyNVR/js/jquery.qrcode.min.js?v=${v}"></script>

    <script src="${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/videojs-hotkeys/videojs.hotkeys.min.js?v=${v}"></script>
    <style>
        .channel-title {
            position: absolute;
            left: 0;
            top: 0;
            right: 0;
            bottom: 0;
            color: #fff;
            text-align: center;
            padding: 0 15px;
            font-size: 20px;
            line-height: 50px;
            font-weight: 700;
        }

        #ruler {
            border: 0;
            border-top: 1px solid #333;
            padding: 0;
            margin: 0;
            background-color: transparent;
            height: 80px;
            position: relative;
        }

        #ruler .two-hour {
            border: 0;
            border-left: 1px solid #333;
            padding: 0;
            margin: 0;
            height: 30px;
        }

        #ruler .two-hour:first-child {
            border-left: 0;
        }

        #ruler .two-hour:last-child {
            border-right: 1px solid #333;
        }

        #ruler .ten-minute {
            border: 0;
            border-left: 1px solid #333;
            padding: 0;
            margin: 0;
            height: 20px;
        }

        #ruler .two-hour .ten-minute:first-child {
            border-left: 0;
        }

        #timer {
            position: absolute;
            width: 1px;
            border: 0;
            left: 0;
            height: 40px;
            overflow: auto;
            background-color: red;
        }

        #timer #handle {
            position: absolute;
            width: 100px;
            height: 30px;
            line-height: 30px;
            top: 40px;
            left: -50px;
            background-color: #00a65a;
            text-align: center;
            color: #fff;
        }

        .share {
            margin-left: 10%;
            margin-right: 10%;
        }

        .shareCode {
            margin: 10px;
        }

        .vjs-progress-control, .vjs-remaining-time-display {
            visibility: hidden;
        }

        .video-js .vjs-play-control.vjs-playing {
            visibility: hidden;
        }

        .video-js .vjs-tech {
            pointer-events: none;
        }

        /*.vjs-control-bar, .vjs-big-play-button {
            display: none !important;
        }*/
        .ipcam_control {
            /*background-color: #8CC6FF;*/
            /*margin-top: 35px;*/
            padding: 10px;
            width: 130px;
            height: 130px;
        }

        /* newshare */
        .new-share {
            width: 100px;
            height: 300px;
            position: absolute;
            top: 40%;
            right: -100px;
            border: 1px solid rgb(180, 168, 168);
            transition: all 0.5s;
            z-index: 3;
            background: #F2EFE6;
            padding: 10px 5px;
            border-radius: 10px;
        }

        .new-share .closeShare {
            position: absolute;
            left: 5px;
            bottom: 5px;
            cursor: pointer;
        }

        .newShareBox {
            width: 35px;
            font-size: 18px;
            color: #666;
            line-height: 1em;
            position: absolute;
            right: 0;
            top: 50%;
            border: 1px solid #999;
            border-radius: 10px;
            background: #F2EFE6;
            transition: all 0.5s;
        }

        .newShareBox p {
            text-align: center;
            padding: 5px 0;
        }

    </style>
</head>

<body class="skin-green layout-top-nav">
<script>
    try {
        $(document).on("transitionend", ".content-wrapper", function () {
            localStorage["sidebar-collapse"] = $("body").hasClass("sidebar-collapse") ? "sidebar-collapse" : "";
        });
        $("body").addClass(localStorage["sidebar-collapse"]);
    } catch (e) {
    }
</script>


<div class="wrapper">
    <header class="main-header">
        <nav class="navbar navbar-static-top" role="navigation">
            <div class="channel-title"></div>
            <div class="navbar-custom-menu pull-left">
            </div>
        </nav>
    </header>

    <div class="content-wrapper">
        <section class="content">
            <div class="player-wrapper" style="margin:0 auto;width:80%;position:relative;">

                <div class="row"
                     style="position:absolute;top:50%;margin-top:-120px;bottom:30px;right:0;z-index:999;overflow: hidden;">
                    <div class="col-sm-5" style="margin-left:20px;width:100%;display:none;overflow: hidden;"
                         id="ipcam_div">

                        <div id="ipcam_control" class="ipcam_control" style="float:left;display:none;">
                            <table width="auto" border="0" align="center" cellspacing="0">
                                <tbody>
                                <tr>
                                    <th colspan="3" style="	text-align: center;color:#fff">云台控制</th>
                                </tr>
                                <tr>
                                    <th colspan="3" style="	text-align: center;color:#fff">&nbsp;</th>
                                </tr>
                                <tr>
                                    <td>
                                        <a onmouseout="ptzout()"
                                           onmouseover="MM_swapImage('Image18','','${ctx}/resources/static/plugins/easyNVR/images/cut.png',1)"
                                           onmousedown="ptzcmdSubmit('zoomout')" onmouseup="ptzcmdSubmit('stop')"> <img
                                                src="${ctx}/resources/static/plugins/easyNVR/images/cut1.png"
                                                name="Image18" border="0" height="38" width="38" id="cut" alt="远"></a>
                                    </td>
                                    <td>　</td>
                                    <td>
                                        <a onmouseout="ptzout()"
                                           onmouseover="MM_swapImage('Image19','','${ctx}/resources/static/plugins/easyNVR/images/add.png',1)"
                                           onmousedown="ptzcmdSubmit('zoomin')" onmouseup="ptzcmdSubmit('stop')"> <img
                                                src="${ctx}/resources/static/plugins/easyNVR/images/add1.png"
                                                name="Image19" border="0" height="38" width="38" id="add" alt="近"></a>
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="3" style="	text-align: center;color:#fff">&nbsp;</th>
                                </tr>
                                <tr>
                                    <th colspan="3" style="color:#fff;border:1px dashed #fff;margin:0 4%;"></th>
                                </tr>
                                <tr>
                                    <th colspan="3" style="	text-align: center;color:#fff">&nbsp;</th>
                                </tr>
                                <tr>
                                    <td>　</td>
                                    <td>
                                        <a onmouseout="ptzout()"
                                           onmouseover="MM_swapImage('Image20','','${ctx}/resources/static/plugins/easyNVR/images/u.png',1)"
                                           onmousedown="ptzcmdSubmit('up')" onmouseup="ptzcmdSubmit('stop')"> <img
                                                src="${ctx}/resources/static/plugins/easyNVR/images/u1.png"
                                                name="Image20" border="0" height="38" width="38" id="up" alt="上"></a>
                                    </td>
                                    <td>　</td>
                                </tr>
                                <tr>
                                    <td>
                                        <a onmouseout="ptzout()" onmousedown="ptzcmdSubmit('left')"
                                           onmouseup="ptzcmdSubmit('stop')"
                                           onmouseover="MM_swapImage('Image22','','${ctx}/resources/static/plugins/easyNVR/images/l.png',1)">
                                            <img src="${ctx}/resources/static/plugins/easyNVR/images/l1.png"
                                                 name="Image22" border="0" height="38" width="38" id="left" alt="左"></a>
                                    </td>
                                    <td>
                                        <a onmouseout="ptzout()" onmousedown="ptzcmdSubmit('home')"
                                           onmouseover="MM_swapImage('Image23','','${ctx}/resources/static/plugins/easyNVR/images/h.png',1)">
                                            <img src="${ctx}/resources/static/plugins/easyNVR/images/h1.png"
                                                 name="Image23" border="0" height="38" width="38" id="center" alt="居中"></a>
                                    </td>
                                    <td>
                                        <a onmouseout="ptzout()" onmousedown="ptzcmdSubmit('right')"
                                           onmouseup="ptzcmdSubmit('stop')"
                                           onmouseover="MM_swapImage('Image24','','${ctx}/resources/static/plugins/easyNVR/images/r.png',1)">
                                            <img src="${ctx}/resources/static/plugins/easyNVR/images/r1.png"
                                                 name="Image24" border="0" height="38" width="38" id="right"
                                                 alt="右"></a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>　</td>
                                    <td>
                                        <a onmouseout="ptzout()" onmousedown="ptzcmdSubmit('down')"
                                           onmouseup="ptzcmdSubmit('stop')"
                                           onmouseover="MM_swapImage('Image26','','${ctx}/resources/static/plugins/easyNVR/images/d.png',1)">
                                            <img src="${ctx}/resources/static/plugins/easyNVR/images/d1.png"
                                                 name="Image26" border="0" height="38" width="38" id="down" alt="下"></a>
                                    </td>
                                    <td>　</td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                        <div style="float:left;" onclick="showCont()">
                            <img src="${ctx}/resources/static/plugins/easyNVR/images/show1.png" border="0" width="100%"
                                 id="Photo"/>
                        </div>
                    </div>
                </div>

                <div class="video-wrapper" style="padding-bottom:56.25%;position:relative;margin:0 auto;">
                    <div class="video-inner" style="position:absolute;top:0;bottom:0;left:0;right:0;">
                        <video id="videojs" class="video-js vjs-default-skin vjs-big-play-centered"
                               style="width: 100%; height: 100%;" controls preload="none"
                               poster="" x5-video-player-fullscreen=”true”，x5-video-player-type=”h5”>
                            <source src="" type=""></source>
                            <p class="vjs-no-js">
                                To view this video please enable JavaScript, and consider upgrading to a web browser
                                that
                                <a href="http://videojs.com/html5-video-support/" target="_blank">
                                    supports HTML5 video
                                </a>
                            </p>
                        </video>
                    </div>
                </div>
            </div>

            <!--评论-->
            <!--<div id="SOHUCS" sid=""></div>
                <script charset="utf-8" type="text/javascript" src="https://changyan.sohu.com/upload/changyan.js?v=${v}" ></script>
                <script type="text/javascript">
                    window.changyan.api.config({
                    appid: 'cysqM1ggz',
                    conf: 'prod_f1f4cfabeb385d8a6e5102a773eb0fc7'
                });
            </script>-->
            <!-- wrapper-->
            <script>

                videojs.options.flash.swf = '${ctx}/resources/static/plugins/easyNVR/adminlte-2.3.6/plugins/video-js-5.19.2/video-js-fixed.swf';
                videojs.options.techOrder = ['html5', 'flash'];
                var player = null;

                function showCont() {
                    if ($("#ipcam_control").is(":hidden")) {
                        $("#ipcam_control").show("fast");
                        $("#Photo").attr("src", "${ctx}/resources/static/plugins/easyNVR/images/show2.png")
                    } else {
                        $("#ipcam_control").hide("fast");
                        $("#Photo").attr("src", "${ctx}/resources/static/plugins/easyNVR/images/show1.png")
                    }
                }

                //分享模块隐藏与显示
                function shareShow() {
                    if ($("#shareBody").is(":hidden")) {
                        $("#shareBody").show("fast");
                        $(".fa-plus").attr('class', "fa fa-minus");
                    } else {
                        $("#shareBody").hide("fast");
                        $(".fa-minus").attr('class', "fa fa-plus");
                    }
                }

                (function ($) {
                    $.extend({
                        urlGet: function () {
                            var aQuery = window.location.href.split("?"); //取得Get参数
                            var aGET = new Array();
                            if (aQuery.length > 1) {
                                var aBuf = aQuery[1].split("&");
                                for (var i = 0, iLoop = aBuf.length; i < iLoop; i++) {
                                    var aTmp = aBuf[i].split("="); //分离key与Value
                                    aGET[aTmp[0]] = aTmp[1];
                                }
                            }
                            return aGET;
                        }
                    })
                })(jQuery);


                function myformatter(date) {
                    var y = date.getFullYear();
                    var m = date.getMonth() + 1;
                    var d = date.getDate();
                    return y + "-" + (m < 10 ? ('0' + m) : m) + "-" + (d < 10 ? ('0' + d) : d);
                }

                function myformatter2(date) {
                    var y = date.getFullYear();
                    var m = date.getMonth() + 1;
                    var d = date.getDate();
                    return y + "" + (m < 10 ? ('0' + m) : m) + (d < 10 ? ('0' + d) : d);
                }

                function myparser(s) {
                    if (!s) return new Date();
                    var ss = (s.split('-'));
                    var y = parseInt(ss[0], 10);
                    var m = parseInt(ss[1], 10);
                    var d = parseInt(ss[2], 10);
                    if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
                        return new Date(y, m - 1, d);
                    } else {
                        return new Date();
                    }
                }

                var search_date = new Date();
                var load_records = []; //记录加载的所有录像段
                var recordIndex; //记录当前播放的是那个录像段
                var timerLive; //观看直播保持心跳
                var timerRecord; //观看录像保持心跳
                var timerRecordSession = ''; //录像心跳session


                //判断是分享页面还是跳转来的播放页面
                var GET = $.urlGet();
                var channel = 1; //Todo GET['channel']
                if (channel == $.cookie("channel")) {
                    if ($.cookie("DeviceType") == 'ONVIF') {
                        if (isPC()) {
                            $("#ipcam_div").show();
                        }
                        // $("#ipcam_div").show();
                    }
                    player = setupPlayer($.cookie("videoUrl"), $.cookie("videoImg"));
                    $(".channel-title").text($.cookie("channelName") || "通道直播");
                } else {
                    // $(".channel-title").text("通道");
                    $.ajax({
                        type: "GET",
                        url: "http://192.168.10.53:10800/api/v1/getchannelstream",
                        data: {
                            Channel: channel,
                            Protocol: isPC() ? "RTMP" : "HLS",
                            Line: "local",
                            From: "lan"
                        },
                        success: function (data) {
                            try {
                                var ret = JSON.parse(data);
                                var DeviceType = ret.EasyDarwin.Body.DeviceType
                                var OnlyChannelName = ret.EasyDarwin.Body.ChannelName
                                $(".channel-title").text(OnlyChannelName);
                                if (DeviceType == 'ONVIF') {
                                    if (isPC()) {
                                        $("#ipcam_div").show();
                                    }
                                }
                                var videoUrl = ret.EasyDarwin.Body.URL;
                                if (!videoUrl) {
                                    throw new Error('URL is empty');
                                }
                                videoUrl = videoUrl.replace("{host}", host);
                                console.log(videoUrl);
                                player = setupPlayer(videoUrl, "");
                                return;
                            } catch (e) {
                                $.gritter.add({
                                    text: '分享地址已失效!',
                                    class_name: 'gritter-error'
                                });
                            }
                        },
                        error: function (xhr, ts, err) {
                            $.gritter.add({
                                text: '分享地址已失效!',
                                class_name: 'gritter-error'
                            });
                        },
                        complete: function () {
                            $("body").unmask();
                        }
                    });
                }
                var url = window.location.href.split("?");
                var shareUrl = url[0] + "?channel=" + channel;
                $(".shareCode").qrcode({
                    render: "table", //table方式
                    width: 100, //宽度
                    height: 100, //高度
                    text: shareUrl //任意内容
                });
                $(".new-shareCode").qrcode({
                    render: "table", //table方式
                    width: 90, //宽度
                    height: 90, //高度
                    text: shareUrl //任意内容
                });
                $(".newshareUrl").val(shareUrl);
                $("#shareUrl").val(shareUrl);
                //分享地址
                // $("#SOHUCS").attr('sid',shareUrl)
                $('.newShareBox').on("mouseover", function () {
                    $(".new-share").css("right", "0");
                    $(this).css("right", "-100px")
                })
                $('.closeShare').on("click", function () {
                    $(".newShareBox").css("right", "0");
                    $('.new-share').css("right", "-100px");
                })

                //分享地址的显示与隐藏
                function setupPlayer(videoUrl, videoImg) {
                    $.ajax({
                        type: "GET",
                        url: ctx + "/easyDarwin/getRtmpUrl?constructionId=" + constructionId,
                        global: false,
                        async: false,
                        success: function (data) {
                            if (data.status == 1) {
                                videoUrl = data.value[0];
                            }
                        }
                    });
                    //  videoUrl = "rtmp://192.168.10.53:10935/hls/stream_1" || "rtmp://121.40.50.44/live/stream_1";
                    videoImg = videoImg || "${ctx}/resources/static/plugins/easyNVR/images/snap.png";
                    $("#videojs").attr("poster", videoImg);
                    if (videoUrl.indexOf("rtmp") == 0) {
                        $("#videojs").find("source").attr("src", videoUrl).attr("type", "rtmp/mp4");
                        player = videojs("videojs", {
                            notSupportedMessage: '您的浏览器没有安装或开启Flash,戳我开启！',
                            techOrder: ["flash"],
                            autoplay: true
                        });
                        videojs('videojs').ready(function () {//双击全屏支持Esc退出全屏
                            this.hotkeys({
                                volumeStep: 0.1,
                                seekStep: 5,
                                enableVolumeScroll: false, //禁用鼠标滚轮调节问音量大小
                                enableModifiersForNumbers: false
                            });
                        });
                        player.on("error", function (e) {
                            var $e = $(".vjs-error .vjs-error-display .vjs-modal-dialog-content");
                            var $a = $("<a href='http://www.adobe.com/go/getflashplayer' target='_blank'></a>").text($e.text());
                            $e.empty().append($a);
                        })
                    } else {
                        var timeout = 10000;
                        var step = 500;
                        var cnt = 0;

                        function test() {
                            cnt += step;
                            $.ajax(videoUrl, {
                                type: "HEAD",
                                global: false,
                                complete: function (xhr, ts) {
                                    if (cnt > timeout) {
                                        $(".player-wrapper").unmask();
                                        $.gritter.add("请求数据失败");
                                        return;
                                    }
                                    //xhr.status == 0 , when cross domain request not found
                                    if (xhr.status == 404 || xhr.status == 0 || (xhr.status != 200 && !isPC())) {
                                        console.log("video is no ready, waiting...");
                                        setTimeout(test, step);
                                    } else {
                                        $(".player-wrapper").unmask();
                                        $("#videojs").find("source").attr("src", videoUrl).attr("type", "application/x-mpegURL");
                                        player = videojs("videojs", {
                                            autoplay: true
                                        });
                                    }
                                }
                            })
                        }

                        $(".player-wrapper").mask("加载中...", 100);
                        test();
                    }
                }

                function secrchRecords() {
                    var s_date = $('#searchdate').val();
                    if (s_date != '' && s_date != myformatter(search_date)) {
                        loadRecords(new Date(s_date));
                    }
                }

                function viewLive() {
                    stopRecord();
                    posTimer(new Date());
                    setupPlayer($.cookie("videoUrl"), $.cookie("videoImg"));
                }

                moment.locale("zh-cn");

                function onDrag(e) {
                    var d = e.data;
                    var range = $(d.parent).innerWidth() - 1;
                    if (d.left < 0) {
                        d.left = 0;
                    }
                    if (d.left > range) {
                        d.left = range;
                    }
                    var left = parseInt(d.left);
                    var pos = left * 10000 / range;
                    var m = moment().startOf("day");
                    var secs = 3600 * 24 * pos / 10000;
                    m.add(secs, "seconds");
                    var title = m.format("HH:mm:ss");
                    $("#handle").text(title).attr("title", title);
                    $("#timer").data("pos", pos);
                }

                function onStopDrag(e) {
                    var d = e.data;
                    var left = parseInt(d.left);
                    var havaRecordFlag = 0; //记录当前拖动的位置是否有录像
                    $.each(load_records, function (i, load_record) {
                        if (left >= load_record[0] && left <= load_record[1]) {
                            havaRecordFlag = 1;
                            clearInterval(timerLive);
                            var range = $(d.parent).innerWidth() - 1;
                            var pos = left * 10000 / range;
                            var m = moment().startOf("day");
                            var secs = 3600 * 24 * pos / 10000;
                            m.add(secs, "seconds");
                            var date_time = myformatter2(search_date) + m.format("HHmmss");
                            if (recordIndex == i) {
                                $.get(_url + "/seekplayrecord", {
                                        channel: $.cookie("channel"),
                                        sessionid: timerRecordSession,
                                        datetime: date_time
                                    },
                                    function (data) {
                                    })
                            } else {
                                stopRecord();
                                recordIndex = i;
                                $.get(_url + "/playrecord", {
                                    channel: $.cookie("channel"),
                                    datetime: date_time
                                }, function (data) {
                                    var ret = JSON.parse(data);
                                    var record_url = ret.EasyDarwin.Body.URL;
                                    timerRecordSession = ret.EasyDarwin.Body.SessionID;
                                    player = setupPlayer(record_url, '');
                                    timerRecord = setInterval(function () {
                                        $.get(_url + "/touchplayrecord", {
                                            Channel: $.cookie("channel"),
                                            sessionid: timerRecordSession
                                        }, function (data) {
                                            console.log(data);
                                        })
                                    }, 30000);
                                })
                            }
                        }
                    })
                    if (havaRecordFlag == 0) {
                        stopRecord();
                    }
                }

                function stopRecord() {
                    recordIndex = -1;
                    clearInterval(timerRecord);
                    player.stop();
                    if (timerRecordSession != '') {
                        $.get(_url + "/stopplayrecord", {
                            channel: $.cookie("channel"),
                            sessionid: timerRecordSession
                        }, function (data) {
                        })
                        timerRecordSession = '';
                    }
                }

                function posTimer(date) {
                    var m = moment(date);
                    var secs = m.hours() * 3600 + m.minutes() * 60 + m.seconds();
                    var pos = (secs * 10000) / (3600 * 24);
                    var title = m.format("HH:mm:ss");
                    $("#handle").text(title).attr("title", title);
                    $("#timer").data("pos", pos).css("left", ($("#ruler").innerWidth() - 1) * pos / 10000);
                }

                function loadRecords(date) {
                    load_records = [];
                    $(".temp_record").remove();
                    $.get(_url + "/searchrecord", {
                        channel: $.cookie("channel"),
                        date: myformatter2(date)
                    }, function (data) {
                        try {
                            var ret = JSON.parse(data);
                            $.each(ret.EasyDarwin.Body.Records, function (i, record) {
                                var mLeft = record.StartTime.split(":");
                                var mRight = record.EndTime.split(":");
                                var secLeft = parseInt(mLeft[0]) * 3600 + parseInt(mLeft[1]) * 60 + parseInt(mLeft[2]);
                                var secRight = parseInt(mRight[0]) * 3600 + parseInt(mRight[1]) * 60 + parseInt(mRight[2]);
                                var posLeft = (secLeft * 10000) / (3600 * 24);
                                var posRight = (secRight * 10000) / (3600 * 24);
                                var left = ($("#ruler").innerWidth() - 1) * posLeft / 10000;
                                var right = ($("#ruler").innerWidth() - 1) * posRight / 10000;
                                var width = right - left;
                                var v_record = "<div class=\"temp_record\" style=\"left: " + left + "px; width:" + width + "px; height:20px; position: absolute; background-color:#00a65a;\"></div>";
                                $("#ruler").append(v_record);
                                var load_record = [left, right];
                                load_records[i] = load_record;
                            })
                            search_date = date;
                        } catch (e) {
                        }
                    })
                }

                $(function () {
                    $(".vjs-tech").prop("disabled", true);
                    var date = new Date();
                    $('#searchdate').daterangepicker({
                        singleDatePicker: true,
                        startDate: moment().subtract(0, 'days'),
                        locale: {
                            format: 'YYYY-MM-DD',
                            separator: ' - ',
                            daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
                            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                            firstDay: moment.localeData().firstDayOfWeek()
                        }
                    });
                    $('#searchdate').val(myformatter(date));
                    for (var i = 0; i < 12; i++) {
                        var $minute = $("<div class='col-xs-1 row two-hour'></div>");
                        $("#ruler").append($minute);
                        for (var j = 0; j < 12; j++) {
                            var $second = $("<div class='col-xs-1 ten-minute'></div>");
                            $minute.append($second);
                        }
                    }

                    function onWindowResize() {
                        var w = $(window).width();
                        var pos = $("#timer").data("pos") || 0;
                        $("#timer").css("left", ($("#ruler").innerWidth() - 1) * pos / 10000);
                    }

                    var resizeEvt = "orientationchange" in window ? "orientationchange" : "resize";
                    window.addEventListener(resizeEvt, onWindowResize, false);
                    //posTimer(date);
                    //loadRecords(date);

                    timerLive = setInterval(function () {
                        $.get(_url + "/touchchannelstream", {
                            // Channel: $.cookie("channel"),
                            Channel: channel,
                            Protocol: isPC() ? "RTMP" : "HLS",
                            Line: "local",
                            From: "lan"
                        }, function (data) {
                            console.log(data);
                        })
                    }, 30000);

                });


                function ptzout() {
                    MM_swapImgRestore();
                }

                function MM_swapImgRestore() { //v3.0
                    var i, x, a = document.MM_sr;
                    for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++) x.src = x.oSrc;
                }

                function MM_preloadImages() { //v3.0
                    var d = document;
                    if (d.images) {
                        if (!d.MM_p) d.MM_p = new Array();
                        var i, j = d.MM_p.length,
                            a = MM_preloadImages.arguments;
                        for (i = 0; i < a.length; i++)
                            if (a[i].indexOf("#") != 0) {
                                d.MM_p[j] = new Image;
                                d.MM_p[j++].src = a[i];
                            }
                    }
                }

                function MM_findObj(n, d) { //v4.01
                    var p, i, x;
                    if (!d) d = document;
                    if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
                        d = parent.frames[n.substring(p + 1)].document;
                        n = n.substring(0, p);
                    }
                    if (!(x = d[n]) && d.all) x = d.all[n];
                    for (i = 0; !x && i < d.forms.length; i++) x = d.forms[i][n];
                    for (i = 0; !x && d.layers && i < d.layers.length; i++) x = MM_findObj(n, d.layers[i].document);
                    if (!x && d.getElementById) x = d.getElementById(n);
                    return x;
                }

                //-->

                function MM_swapImage() { //v3.0
                    var i, j = 0,
                        x, a = MM_swapImage.arguments;
                    document.MM_sr = new Array;
                    for (i = 0; i < (a.length - 2); i += 3)
                        if ((x = MM_findObj(a[i])) != null) {
                            document.MM_sr[j++] = x;
                            if (!x.oSrc) x.oSrc = x.src;
                            x.src = a[i + 2];
                        }
                }

                function ptzcmdSubmit(casename) {
                    $.ajax({
                        type: "GET",
                        url: "http://192.168.10.53:10800/api/v1/ptzcontrol",
                        data: {
                            channel: channel,
                            protocol: "onvif",
                            speed: 5,
                            command: casename,
                            actiontype: "continuous",
                        },
                        success: function (data) {
                        }
                    });
                }

                //-->
            </script>
        </section>
    </div>
    <!-- content-wrapper -->

    <footer class="main-footer">
        <div class="pull-right hidden-xs">

        </div>
        <strong>Copyright &copy; 2012-2018 <a href="http://www.easynvr.com">www.easynvr.com</a>.</strong> All rights
        reserved.
    </footer>
</div>
<!-- wrapper-->
</body>
<script type="text/javascript">
    if (isIntegrate) {
        $(".main-header").hide();
        $(".content-header").hide();
        $(".main-footer").hide();
        $(".main-sidebar").hide();
        $(".content-wrapper").css("margin-left", "0px");
    }
    //显示或隐藏重启按钮
    $.ajax({
        url: _url + "/getserverinfo",
        async: false,
        success: function (data) {
            var ret = JSON.parse(data);
            $.cookie("CurrentVersion", ret.EasyDarwin.Body.Server);
            system = $.cookie("CurrentVersion");
            system = system.substring(system.indexOf("Platform/") + 9, system.indexOf("; Release"));
            var version = ret.EasyDarwin.Body.Server;
            version = version.substring(version.indexOf("EasyNVR/") + 8, version.indexOf("(Build/"));
            $(".label-warning").html("v" + version);
            $(".hidden-xs").html("EasyNVR v" + version);
            if (system == 'Win32') {
                $('#reboot-btn').css("display", "block");
            } else {
                $('#reboot-btn').css("display", "none");
            }
        }
    })
    //显示或隐藏重启按钮
</script>

</html>