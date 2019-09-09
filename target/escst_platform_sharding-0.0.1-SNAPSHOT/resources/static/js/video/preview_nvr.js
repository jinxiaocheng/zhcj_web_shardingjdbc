
// 全局保存当前选中窗口
var g_iWndIndex = 0; // 可以不用设置这个变量，有窗口参数的接口中，不用传值，开发包会默认使用当前选择窗口
var g_flag={ //用于控制视屏是枪机还是球机
    index:1,
    data:[]
};
var g_channelNo = 0;
var g_index=0;
var g_channel=[]; //窗口号对应的通道号
$(function() {
    // 检查插件是否已经安装过
    if (-1 == WebVideoCtrl.I_CheckPluginInstall()) {
        alert("您还未安装过插件,请先下载插件");
        var url ='/public/WebComponentsKit.exe';
        window.location.href = url;
        return;
    }
    // 初始化日期时间
    var szCurTime = dateFormat(new Date(), "yyyy-MM-dd");
    $(".start input").val(szCurTime + " 00:00:00");
    $(".end input").val(szCurTime + " 23:59:59");


    // 初始化插件参数及插入插件
    WebVideoCtrl.I_InitPlugin("100%", "100%", {
        iWndowType : 2,
        cbSelWnd : function(xmlDoc) {
            g_iWndIndex = $(xmlDoc).find("SelectWnd").eq(0).text();
            g_flag.index=parseInt(g_iWndIndex)+1;
            setTimeout(function () {
                var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);
                g_channelNo= oWndInfo.iChannelID-32;
                for(var i in g_flag.data){
                    if(g_flag.data[i].channelNo==g_channelNo){
                        console.log(g_flag.data[i].flag);
                        if(g_flag.data[i].flag){
                            console.log(1);
                            $(".orientation").show();
                            $(".YT_speed").show();
                        }else{
                            $(".orientation").hide();
                            $(".YT_speed").hide();
                        }
                    }
                }
                var szInfo = "当前选择的窗口编号：" + g_iWndIndex;
                showCBInfo(szInfo);
                if(!video_sharpness[g_iWndIndex]){
                    video_sharpness[g_iWndIndex]=g_iWndIndex;
                }
                con_sharpness();//判断每个的清晰度显示情况
                $(".video_download").html(""); //清空回放搜索内容
                $(".start input").val(szCurTime + " 00:00:00");
                $(".end input").val(szCurTime + " 23:59:59");
            },10)
        }
    });

    WebVideoCtrl.I_InsertOBJECTPlugin("divPlugin");

    /*// 检查插件是否最新
    if (-1 == WebVideoCtrl.I_CheckPluginVersion()) {
        alert("检测到新的插件版本，请升级！");
        location.href = '../../../WebComponents.exe';
        return;
    }*/

    // 窗口事件绑定
    $(window).bind({
        resize : function() {
            var $Restart = $("#restartDiv");
            if ($Restart.length > 0) {
                var oSize = getWindowSize();
                $Restart.css({
                    width : oSize.width + "px",
                    height : oSize.height + "px"
                });
            }
        }
    });


    var xmlDoc = WebVideoCtrl.I_GetLocalCfg();
    var reg = /\\CaptureFiles/;
    var str = $(xmlDoc).find("CapturePath").eq(0).text();
    $("#path").html(str.replace(reg,''));

});

// 显示操作信息
function showOPInfo(szInfo) {
    // var index = layer.alert(szInfo,{
    //     offset: 'rb', //右下角弹出
    //     shift:2,
    //     time: 2000, //2秒后自动关闭
    //     shade:0
    // });
    // $("#layui-layer"+index).find(".layui-layer-title").append('<iframe src="about:blank" style="border: currentColor; border-image: none; left: 0px; width: 100%; height: 100%; position: absolute; z-index: -1;"></iframe>');
}

// 显示回调信息
function showCBInfo(szInfo) {
    szInfo = "<div>" + dateFormat(new Date(), "yyyy-MM-dd hh:mm:ss") + " "
        + szInfo + "</div>";
    $("#cbinfo").html(szInfo + $("#cbinfo").html());
}

// 格式化时间
function dateFormat(oDate, fmt) {
    var o = {
        "M+" : oDate.getMonth() + 1, // 月份
        "d+" : oDate.getDate(), // 日
        "h+" : oDate.getHours(), // 小时
        "m+" : oDate.getMinutes(), // 分
        "s+" : oDate.getSeconds(), // 秒
        "q+" : Math.floor((oDate.getMonth() + 3) / 3), // 季度
        "S" : oDate.getMilliseconds()
        // 毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (oDate.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    }
    for ( var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
                : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}

// 获取窗口尺寸
function getWindowSize() {
    var nWidth = $(this).width() + $(this).scrollLeft(), nHeight = $(this).height()+ $(this).scrollTop();

    return {
        width : nWidth,
        height : nHeight
    };
}

// 打开选择框 0：文件夹 1：文件
function clickOpenFileDlg(id, iType) {
    var szDirPath = WebVideoCtrl.I_OpenFileDlg(iType);

    if (szDirPath != -1 && szDirPath != "" && szDirPath != null) {
        $("#" + id).val(szDirPath);
    }
}

// 获取本地参数
function clickGetLocalCfg() {
    var xmlDoc = WebVideoCtrl.I_GetLocalCfg();

    $("#netsPreach").val($(xmlDoc).find("BuffNumberType").eq(0).text());
    $("#wndSize").val($(xmlDoc).find("PlayWndType").eq(0).text());
    $("#rulesInfo").val($(xmlDoc).find("IVSMode").eq(0).text());
    $("#captureFileFormat").val(
        $(xmlDoc).find("CaptureFileFormat").eq(0).text());
    $("#packSize").val($(xmlDoc).find("PackgeSize").eq(0).text());
    $("#recordPath").val($(xmlDoc).find("RecordPath").eq(0).text());
    $("#downloadPath").val($(xmlDoc).find("DownloadPath").eq(0).text());
    $("#previewPicPath").val($(xmlDoc).find("CapturePath").eq(0).text());
    $("#playbackPicPath").val($(xmlDoc).find("PlaybackPicPath").eq(0).text());
    $("#playbackFilePath").val($(xmlDoc).find("PlaybackFilePath").eq(0).text());
    $("#protocolType").val($(xmlDoc).find("ProtocolType").eq(0).text());

    showOPInfo("本地配置获取成功！");
}

// 设置本地参数
function clickSetLocalCfg() {
    var arrXml = [], szInfo = "";

    arrXml.push("<LocalConfigInfo>");
    arrXml.push("<PackgeSize>" + $("#packSize").val() + "</PackgeSize>");
    arrXml.push("<PlayWndType>" + $("#wndSize").val() + "</PlayWndType>");
    arrXml.push("<BuffNumberType>" + $("#netsPreach").val()
        + "</BuffNumberType>");
    arrXml.push("<RecordPath>" + $("#recordPath").val() + "</RecordPath>");
    arrXml
        .push("<CapturePath>" + $("#previewPicPath").val()
            + "</CapturePath>");
    arrXml.push("<PlaybackFilePath>" + $("#playbackFilePath").val()
        + "</PlaybackFilePath>");
    arrXml.push("<PlaybackPicPath>" + $("#playbackPicPath").val()
        + "</PlaybackPicPath>");
    arrXml
        .push("<DownloadPath>" + $("#downloadPath").val()
            + "</DownloadPath>");
    arrXml.push("<IVSMode>" + $("#rulesInfo").val() + "</IVSMode>");
    arrXml.push("<CaptureFileFormat>" + $("#captureFileFormat").val()
        + "</CaptureFileFormat>");
    arrXml
        .push("<ProtocolType>" + $("#protocolType").val()
            + "</ProtocolType>");
    arrXml.push("</LocalConfigInfo>");

    var iRet = WebVideoCtrl.I_SetLocalCfg(arrXml.join(""));

    if (0 == iRet) {
        szInfo = "本地配置设置成功！";
    } else {
        szInfo = "本地配置设置失败！";
    }
    showOPInfo(szInfo);
}

// 窗口分割数
function changeWndNum(iType) {
    iType = parseInt(iType, 10);
    WebVideoCtrl.I_ChangeWndNum(iType);
}

// 登录
function clickLogin(nvrInfo,treeNode) {
    var szIP = nvrInfo.ip, szPort = nvrInfo.port, szUsername = nvrInfo.userName, szPassword = nvrInfo.password;

    if ("" == szIP || "" == szPort) {
        return;
    }
    var iRet = WebVideoCtrl.I_Login(szIP, 1, szPort, szUsername, szPassword, {
        success : function(xmlDoc) {

            showOPInfo("登录成功！");
            setTimeout(function() {
                $("#ip").val(szIP);
                getChannelInfo(szIP);
                //开始预览视频
                StartRealPlay(szIP,treeNode);
            }, 10);
        },
        error : function() {
            showOPInfo(szIP + " 登录失败！");
        }
    });

    if (-1 == iRet) {
        //(szIP + " 已登录过！");
    }
}

// 退出
function clickLogout(ip) {
    var szIP = ip, szInfo = "";

    if (szIP == "") {
        return;
    }

    var iRet = WebVideoCtrl.I_Logout(szIP);
    if (0 == iRet) {
        szInfo = "退出成功！";
        $("#ip").val("");
        $("#channels").empty();
//		getChannelInfo(szIP);
    } else {
        szInfo = "退出失败！";
        showOPInfo(szIP + " " + szInfo);
    }

}

// 获取设备信息
function clickGetDeviceInfo() {
    var szIP = $("#ip").val();

    if ("" == szIP) {
        return;
    }

    WebVideoCtrl.I_GetDeviceInfo(szIP, {
        success : function(xmlDoc) {
            var arrStr = [];
            arrStr.push("设备名称：" + $(xmlDoc).find("deviceName").eq(0).text()
                + "\r\n");
            arrStr.push("设备ID：" + $(xmlDoc).find("deviceID").eq(0).text()
                + "\r\n");
            arrStr.push("型号：" + $(xmlDoc).find("model").eq(0).text() + "\r\n");
            arrStr.push("设备序列号：" + $(xmlDoc).find("serialNumber").eq(0).text()
                + "\r\n");
            arrStr.push("MAC地址：" + $(xmlDoc).find("macAddress").eq(0).text()
                + "\r\n");
            arrStr.push("主控版本："
                + $(xmlDoc).find("firmwareVersion").eq(0).text() + " "
                + $(xmlDoc).find("firmwareReleasedDate").eq(0).text()
                + "\r\n");
            arrStr.push("编码版本：" + $(xmlDoc).find("encoderVersion").eq(0).text()
                + " " + $(xmlDoc).find("encoderReleasedDate").eq(0).text()
                + "\r\n");

            //showOPInfo(szIP + " 获取设备信息成功！");
        },
        error : function() {
            showOPInfo(szIP + " 获取设备信息失败！");
        }
    });
}
// 获取通道
function getChannelInfo(ip) {
    var szIP = ip,
        oSel = $("#channels").empty(),
        nAnalogChannel = 0;

    if ("" == szIP) {
        return;
    }

    // 模拟通道
    WebVideoCtrl.I_GetAnalogChannelInfo(szIP,
        {
            async : false,
            success : function(xmlDoc) {
                var oChannels = $(xmlDoc).find("VideoInputChannel");
                nAnalogChannel = oChannels.length;

                $.each(oChannels,function(i) {
                    var id = parseInt($(this).find("id").eq(0).text(), 10),
                        name = $(this).find("name").eq(0).text();
                    if ("" == name) {
                        name = "Camera " + (id < 9 ? "0" + id : id);
                    }
                    oSel.append("<option value='" + id + "' bZero='false'>" + name + "</option>");
                });
                //showOPInfo(szIP + " 获取模拟通道成功！");
            },
            error : function() {
                //showOPInfo(szIP + " 获取模拟通道失败！");
            }
        });
    // 数字通道
    WebVideoCtrl.I_GetDigitalChannelInfo(szIP, {
        async : false,
        success : function(xmlDoc) {
            var oChannels = $(xmlDoc).find("InputProxyChannelStatus");
            var boxIndex=0;

            $.each(oChannels,function(i) {

                var id = parseInt($(this).find("id").eq(0).text(), 10),
                    name = $(this).find("name").eq(0).text(),
                    online = $(this).find("online").eq(0).text();
                if ("false" == online) {// 过滤禁用的数字通道
                    return true;
                }

                video_sharpness[boxIndex]=i;
                boxIndex++;
                if ("" == name) {
                    if (id <= chnname.length) {
                        name = chnname[id - 1];
                    } else {
                        name = "未定义通道" + id;
                    }
                }
                oSel.append("<option value='" + id + "' bZero='false'>" + name + "</option>");
            });
            //showOPInfo(szIP + " 获取数字通道成功！");
        },
        error : function() {
            showOPInfo(szIP + " 获取数字通道失败！");
        }
    });

    // 零通道
    WebVideoCtrl.I_GetZeroChannelInfo(szIP, {
        async : false,
        success : function(xmlDoc) {
            var oChannels = $(xmlDoc).find("ZeroVideoChannel");

            //showOPInfo(szIP + " 获取零通道成功！");
        },
        error : function() {
            //showOPInfo(szIP + " 获取零通道失败！");
        }
    });
}

// 获取数字通道
function clickGetDigitalChannelInfo() {
    var szIP = $("#ip").val(), iAnalogChannelNum = 0;

    $("#digitalchannellist").empty();

    if ("" == szIP) {
        return;
    }

    // 模拟通道
    WebVideoCtrl.I_GetAnalogChannelInfo(szIP, {
        async : false,
        success : function(xmlDoc) {
            iAnalogChannelNum = $(xmlDoc).find("VideoInputChannel").length;
        },
        error : function() {

        }
    });

    // 数字通道
    WebVideoCtrl
        .I_GetDigitalChannelInfo(
            szIP,
            {
                async : false,
                success : function(xmlDoc) {
                    var oChannels = $(xmlDoc).find(
                        "InputProxyChannelStatus");

                    $.each(oChannels,function() {
                        var id = parseInt($(this).find(
                            "id").eq(0).text(), 10), ipAddress = $(
                            this).find("ipAddress")
                            .eq(0).text(), srcInputPort = $(
                            this).find(
                            "srcInputPort").eq(0)
                            .text(), managePortNo = $(
                            this).find(
                            "managePortNo").eq(0)
                            .text(), online = $(
                            this).find("online")
                            .eq(0).text(), proxyProtocol = $(
                            this).find(
                            "proxyProtocol").eq(0)
                            .text();

                        var objTr = $(
                            "#digitalchannellist")
                            .get(0).insertRow(-1);
                        var objTd = objTr.insertCell(0);
                        objTd.innerHTML = (id - iAnalogChannelNum) < 10 ? "D0"
                            + (id - iAnalogChannelNum)
                            : "D"
                            + (id - iAnalogChannelNum);
                        objTd = objTr.insertCell(1);
                        objTd.width = "25%";
                        objTd.innerHTML = ipAddress;
                        objTd = objTr.insertCell(2);
                        objTd.width = "15%";
                        objTd.innerHTML = srcInputPort;
                        objTd = objTr.insertCell(3);
                        objTd.width = "20%";
                        objTd.innerHTML = managePortNo;
                        objTd = objTr.insertCell(4);
                        objTd.width = "15%";
                        objTd.innerHTML = "true" == online ? "在线"
                            : "离线";
                        objTd = objTr.insertCell(5);
                        objTd.width = "25%";
                        objTd.innerHTML = proxyProtocol;
                    });
                    //	showOPInfo(" 获取数字通道成功！");
                },
                error : function() {
                    //showOPInfo(szIP + " 没有数字通道！");
                }
            });
}

// 开始预览
function clickStartRealPlay() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
        szIP = $("#ip").val(),
        iStreamType = parseInt($(".con_sharpness").attr("kk-value"), 10),
        iChannelID = parseInt(video_sharpness[g_iWndIndex],10)+1,
        bZeroChannel = $("#channels option").eq($("#channels").get(0).selectedIndex).attr("bZero") == "true" ? true : false,
        szInfo = "";
    if ("" == szIP) {
        return;
    }

    if (oWndInfo != null) {// 已经在播放了，先停止
        WebVideoCtrl.I_Stop();
    }
    var iRet = WebVideoCtrl.I_StartRealPlay(szIP, {
        iStreamType : iStreamType,
        iChannelID : g_channelNo,
        bZeroChannel : bZeroChannel
    });


    if (0 == iRet) {
        szInfo = "开始预览成功！";
    } else {
        szInfo = szIP+ " " + "开始预览失败！";
    }
    //showOPInfo( szInfo);
}

//点击摄像头预览
function loginRealPlay(nvrInfo,channelNo) {
    var szIP = nvrInfo.ip, szPort = nvrInfo.port, szUsername = nvrInfo.userName, szPassword = nvrInfo.password;

    if ("" == szIP || "" == szPort) {
        return;
    }

    var iRet = WebVideoCtrl.I_Login(szIP, 1, szPort, szUsername, szPassword, {
        success : function(xmlDoc) {

            showOPInfo(" 登录成功！");

            setTimeout(function() {
                $("#ip").val(szIP);
                //开始预览视频
                startRealPlayCamera(szIP,channelNo);
            }, 10);
        },
        error : function() {
            showOPInfo(szIP + " 登录失败！");
        }
    });

    if (-1 == iRet) {
        //showOPInfo(szIP + " 已登录过！");
        //开始预览视频
        startRealPlayCamera(szIP,channelNo);
    }
}
function startRealPlayCamera(ip,channelNo) {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
        szIP = ip,
        iStreamType = parseInt($("#streamtype").val(), 10),
        iChannelID = channelNo,
        bZeroChannel = false,
        szInfo = "";

    if ("" == szIP) {
        return;
    }

    if (oWndInfo != null) {// 已经在播放了，先停止
        WebVideoCtrl.I_Stop();
    }

    var iRet = WebVideoCtrl.I_StartRealPlay(szIP, {
        iStreamType : iStreamType,
        iChannelID : iChannelID,
        bZeroChannel : bZeroChannel
    });

    if (0 == iRet) {
        szInfo = "开始预览成功！";
    } else {
        szInfo = "开始预览失败！";
    }

    //showOPInfo(szIP + " " + szInfo);
}

// 停止预览
function clickStopRealPlay() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), szInfo = "";
    if (oWndInfo != null) {
        var iRet = WebVideoCtrl.I_Stop();
        if (0 == iRet) {
            szInfo = "停止预览成功！";
        } else {
            szInfo = "停止预览失败！";
        }
        showOPInfo(oWndInfo.szIP + " " + szInfo);
    }
}

// 打开声音
function clickOpenSound() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), szInfo = "";

    if (oWndInfo != null) {
        var allWndInfo = WebVideoCtrl.I_GetWindowStatus();
        // 循环遍历所有窗口，如果有窗口打开了声音，先关闭
        for (var i = 0, iLen = allWndInfo.length; i < iLen; i++) {
            oWndInfo = allWndInfo[i];
            if (oWndInfo.bSound) {
                WebVideoCtrl.I_CloseSound(oWndInfo.iIndex);
                break;
            }
        }

        var iRet = WebVideoCtrl.I_OpenSound();

        if (0 == iRet) {
            szInfo = "打开声音成功！";
        } else {
            szInfo = "打开声音失败！";
        }
        showOPInfo(oWndInfo.szIP + " " + szInfo);
    }
}

// 关闭声音
function clickCloseSound() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), szInfo = "";

    if (oWndInfo != null) {
        var iRet = WebVideoCtrl.I_CloseSound();
        if (0 == iRet) {
            szInfo = "关闭声音成功！";
        } else {
            szInfo = "关闭声音失败！";
        }
        showOPInfo(oWndInfo.szIP + " " + szInfo);
    }
}

// 设置音量
function clickSetVolume() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), iVolume = parseInt(
        $("#volume").val(), 10), szInfo = "";

    if (oWndInfo != null) {
        var iRet = WebVideoCtrl.I_SetVolume(iVolume);
        if (0 == iRet) {
            szInfo = "音量设置成功！";
        } else {
            szInfo = "音量设置失败！";
        }
        showOPInfo(oWndInfo.szIP + " " + szInfo);
    }
}

// 抓图
function clickCapturePic() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), szInfo = "";

    if (oWndInfo != null) {
        var szChannelID = $("#channels").val(), szPicName = oWndInfo.szIP + "_"
            + szChannelID + "_" + new Date().getTime(), iRet = WebVideoCtrl
            .I_CapturePic(szPicName);
        if (0 == iRet) {
            szInfo = "抓图成功！";
        } else {
            szInfo = "抓图失败！";
        }
        showOPInfo(oWndInfo.szIP + " " + szInfo);
    }
}

// 开始录像
var g_szRecordType = "";
function clickStartRecord(szType) {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
        szInfo = "";
    g_szRecordType = szType;

    if (oWndInfo != null) {
        var szChannelID = $("#channels").val(),
            szFileName = oWndInfo.szDeviceIdentify + "_" + szChannelID + "_" + new Date().getTime();

        WebVideoCtrl.I_StartRecord(szFileName, {
            bDateDir: true, //是否生成日期文件
            success: function () {
                if ('realplay' === szType) {
                    szInfo = "开始录像成功！";
                } else if ('playback' === szType) {
                    szInfo = "开始剪辑成功！";
                }
            },
            error: function () {
                if ('realplay' === szType) {
                    szInfo = "开始录像失败！";
                } else if ('playback' === szType) {
                    szInfo = "开始剪辑失败！";
                }
            }
        });
        showOPInfo(szInfo);

        // iRet = WebVideoCtrl
        // 		.I_StartRecord(szFileName);
        // if (0 == iRet) {
        // 	szInfo = "开始录像成功！";
        // } else {
        // 	szInfo = "开始录像失败！";
        // }
        // alert(oWndInfo.szIP + " " + szInfo);
    }
}

// 停止录像
function clickStopRecord(szType,iWndIndex) {

    if ("undefined" === typeof iWndIndex) {
        iWndIndex = g_iWndIndex;
    }
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(iWndIndex),
        szInfo = "";

    if (oWndInfo != null) {
        WebVideoCtrl.I_StopRecord({
            success: function () {
                if ('realplay' === szType) {
                    szInfo = "停止录像成功！";
                } else if ('playback' === szType) {
                    szInfo = "停止剪辑成功！";
                }
            },
            error: function () {
                if ('realplay' === szType) {
                    szInfo = "停止录像失败！";
                } else if ('playback' === szType) {
                    szInfo = "停止剪辑失败！";
                }
            }
        });
        showOPInfo(szInfo);
    }

    // var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
    // 	szInfo = "";
    //
    //
    // if (oWndInfo != null) {
    // 	var iRet = WebVideoCtrl.I_StopRecord();
    // 	if (0 == iRet) {
    // 		szInfo = "停止录像成功！";
    // 	} else {
    // 		szInfo = "停止录像失败！";
    // 	}
    // 	showOPInfo(oWndInfo.szIP + " " + szInfo);
    // }
}

// 获取对讲通道
function clickGetAudioInfo() {
    var szIP = $("#ip").val();

    if ("" == szIP) {
        return;
    }

    WebVideoCtrl
        .I_GetAudioInfo(szIP,
            {
                success : function(xmlDoc) {
                    var oAudioChannels = $(xmlDoc).find(
                        "TwoWayAudioChannel"), oSel = $(
                        "#audiochannels").empty();
                    $.each(oAudioChannels, function() {
                        var id = $(this).find("id").eq(0).text();

                        oSel.append("<option value='" + id + "'>" + id
                            + "</option>");
                    });
                    //showOPInfo(" 获取对讲通道成功！");
                },
                error : function() {
                    //showOPInfo(" 获取对讲通道失败！");
                }
            });
}

// 开始对讲
function clickStartVoiceTalk() {
    var szIP = $("#ip").val(), iAudioChannel = parseInt($("#audiochannels")
        .val(), 10), szInfo = "";

    if ("" == szIP) {
        return;
    }

    if (isNaN(iAudioChannel)) {
        showOPInfo("请选择对讲通道！");
        return;
    }

    var iRet = WebVideoCtrl.I_StartVoiceTalk(szIP, iAudioChannel);

    if (0 == iRet) {
        szInfo = "开始对讲成功！";
    } else {
        szInfo = "开始对讲失败！";
    }
    showOPInfo(szIP + " " + szInfo);
}

// 停止对讲
function clickStopVoiceTalk() {
    var szIP = $("#ip").val(), iRet = WebVideoCtrl.I_StopVoiceTalk(), szInfo = "";

    if ("" == szIP) {
        return;
    }

    if (0 == iRet) {
        szInfo = "停止对讲成功！";
    } else {
        szInfo = "停止对讲失败！";
    }
    showOPInfo(szIP + " " + szInfo);
}

// 启用电子放大
function clickEnableEZoom() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), szInfo = "";

    if (oWndInfo != null) {
        var iRet = WebVideoCtrl.I_EnableEZoom();
        if (0 == iRet) {
            szInfo = "启用电子放大成功！";
        } else {
            szInfo = "启用电子放大失败！";
        }
        showOPInfo(oWndInfo.szIP + " " + szInfo);
    }
}

// 禁用电子放大
function clickDisableEZoom() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), szInfo = "";

    if (oWndInfo != null) {
        var iRet = WebVideoCtrl.I_DisableEZoom();
        if (0 == iRet) {
            szInfo = "禁用电子放大成功！";
        } else {
            szInfo = "禁用电子放大失败！";
        }
        showOPInfo(oWndInfo.szIP + " " + szInfo);
    }
}

// 启用3D放大
function clickEnable3DZoom() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), szInfo = "";

    if (oWndInfo != null) {
        var iRet = WebVideoCtrl.I_Enable3DZoom();
        if (0 == iRet) {
            szInfo = "启用3D放大成功！";
        } else {
            szInfo = "启用3D放大失败！";
        }
        showOPInfo(oWndInfo.szIP + " " + szInfo);
    }
}

// 禁用3D放大
function clickDisable3DZoom() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), szInfo = "";

    if (oWndInfo != null) {
        var iRet = WebVideoCtrl.I_Disable3DZoom();
        if (0 == iRet) {
            szInfo = "禁用3D放大成功！";
        } else {
            szInfo = "禁用3D放大失败！";
        }
        showOPInfo(oWndInfo.szIP + " " + szInfo);
    }
}

// 全屏
function clickFullScreen() {
    WebVideoCtrl.I_FullScreen(true);
}

// PTZ控制 9为自动，1,2,3,4,5,6,7,8为方向PTZ
var g_bPTZAuto = false;
function mouseDownPTZControl(iPTZIndex) {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), bZeroChannel = $(
        "#channels option").eq($("#channels").get(0).selectedIndex).attr(
        "bZero") == "true" ? true : false, iPTZSpeed =  $(".speed_controller").attr("sc-num"), bStop = false;

    if (bZeroChannel) {// 零通道不支持云台
        return;
    }

    if (oWndInfo != null) {
        if (9 == iPTZIndex && g_bPTZAuto) {
            iPTZSpeed = 0;// 自动开启后，速度置为0可以关闭自动
            bStop = true;
        } else {
            g_bPTZAuto = false;// 点击其他方向，自动肯定会被关闭
            bStop = false;
        }

        WebVideoCtrl.I_PTZControl(iPTZIndex, bStop, {
            iPTZSpeed : iPTZSpeed,
            success : function(xmlDoc) {
                if (9 == iPTZIndex) {
                    g_bPTZAuto = !g_bPTZAuto;
                }
                showOPInfo(oWndInfo.szIP + " 开启云台成功！");
            },
            error : function() {
                showOPInfo(oWndInfo.szIP + " 开启云台失败！");
            }
        });
    }
}

// 方向PTZ停止
function mouseUpPTZControl() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(1, true, {
            success : function(xmlDoc) {
                showOPInfo(oWndInfo.szIP + " 停止云台成功！");
            },
            error : function() {
                showOPInfo(oWndInfo.szIP + " 停止云台失败！");
            }
        });
    }
}

// 设置预置点
function clickSetPreset() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), iPresetID = parseInt(
        $("#preset").val(), 10);

    if (oWndInfo != null) {
        WebVideoCtrl.I_SetPreset(iPresetID, {
            success : function(xmlDoc) {
                showOPInfo(oWndInfo.szIP + " 设置预置点成功！");
            },
            error : function() {
                showOPInfo(oWndInfo.szIP + " 设置预置点失败！");
            }
        });
    }
}

// 调用预置点
function clickGoPreset() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), iPresetID = parseInt(
        $("#preset").val(), 10);

    if (oWndInfo != null) {
        WebVideoCtrl.I_GoPreset(iPresetID, {
            success : function(xmlDoc) {
                showOPInfo(oWndInfo.szIP + " 调用预置点成功！");
            },
            error : function() {
                showOPInfo(oWndInfo.szIP + " 调用预置点失败！");
            }
        });
    }
}

// 搜索录像
var iSearchTimes = 0;
function clickRecordSearch(iType) {
    $(".video_download").html("");
    recordSearch(iType);
}
function recordSearch(iType) {
    $(".control_playback").show();
    var szIP = $("#ip").val(), iChannelID = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex).iChannelID-32, bZeroChannel = $(
        "#channels option").eq($("#channels").get(0).selectedIndex).attr(
        "bZero") == "true" ? true : false,szStartTime = $(".start input")
        .val(), szEndTime = $(".end input").val();

    if ("" == szIP) {
        return;
    }

    if (bZeroChannel) {// 零通道不支持录像搜索
        return;
    }

    if (0 == iType) {// 首次搜索
        $("#searchlist").empty();
        iSearchTimes = 0;
    }
    WebVideoCtrl
        .I_RecordSearch(
            szIP,
            iChannelID,
            szStartTime,
            szEndTime,
            {
                iSearchPos : iSearchTimes * 40,
                success : function(xmlDoc) {
                    if ("MORE" === $(xmlDoc).find("responseStatusStrg")
                            .eq(0).text()) {
                        for (var i = 0, nLen = $(xmlDoc).find(
                            "searchMatchItem").length; i < nLen; i++) {
                            var szPlaybackURI = $(xmlDoc).find(
                                "playbackURI").eq(i).text();
                            if (szPlaybackURI.indexOf("name=") < 0) {
                                break;
                            }
                            var szStartTime = $(xmlDoc).find(
                                "startTime").eq(i).text();
                            var szEndTime = $(xmlDoc).find("endTime")
                                .eq(i).text();
                            var szFileName = szPlaybackURI.substring(
                                szPlaybackURI.indexOf("name=") + 5,
                                szPlaybackURI.indexOf("&size="));
                            var startTime=(szStartTime.replace("T", " ")).replace("Z", "");
                            var endTime=(szEndTime.replace("T", " ")).replace("Z", "");
                            var $objLi = '<li id="downloadTd'+i+'">\n' +
                                '              <p>\n' +
                                '                <label class="video_name"><span class="pull-left">'+szFileName+'</span><span class="pull-right" id="downProcess'+i+'"></span></label>\n' +
                                '                	<label class="video_time">' +
                                '						<span class="pull-left">开始时间</span><span class="pull-right startTime">'+startTime+' </span><br>' +
                                '						<span class="pull-left">结束时间</span><span class="pull-right endTime">'+endTime+' </span>' +
                                '					</label>\n' +
                                '              </p><span class="download_ico" onclick="clickStartDownloadRecord('+i+')"></span>\n' +
                                '            </li>';
                            $(".video_download").append($objLi);
                            $("#downloadTd" + i).data("playbackURI",szPlaybackURI);
                        }

                        iSearchTimes++;
                        recordSearch(1);// 继续搜索
                    } else if ("OK" === $(xmlDoc).find("responseStatusStrg").eq(0).text()) {
                        var iLength = $(xmlDoc).find("searchMatchItem").length;
                        for (var i = 0; i < iLength; i++) {
                            var szPlaybackURI = $(xmlDoc).find(
                                "playbackURI").eq(i).text();
                            if (szPlaybackURI.indexOf("name=") < 0) {
                                break;
                            }
                            var szStartTime = $(xmlDoc).find(
                                "startTime").eq(i).text();
                            var szEndTime = $(xmlDoc).find("endTime")
                                .eq(i).text();
                            var szFileName = szPlaybackURI.substring(
                                szPlaybackURI.indexOf("name=") + 5,
                                szPlaybackURI.indexOf("&size="));

                            var startTime=(szStartTime.replace("T", " ")).replace("Z", "");
                            var endTime=(szEndTime.replace("T", " ")).replace("Z", "");
                            var $objLi = '<li id="downloadTd'+i+'">\n' +
                                '              <p>\n' +
                                '                <label class="video_name"><span class="pull-left">'+szFileName+'</span><span class="pull-right" id="downProcess'+i+'"></span></label>\n' +
                                '                	<label class="video_time">' +
                                '						<span class="pull-left">开始时间</span><span class="pull-right startTime">'+startTime+' </span><br>' +
                                '						<span class="pull-left">结束时间</span><span class="pull-right endTime">'+endTime+' </span>' +
                                '					</label>\n' +
                                '              </p><span class="download_ico" onclick="clickStartDownloadRecord('+i+')"></span>\n' +
                                '            </li>';
                            $(".video_download").append($objLi);
                            $("#downloadTd" + i).data("playbackURI", szPlaybackURI);
                        }
                        showOPInfo(szIP + " 搜索录像文件成功！");
                    } else if ("NO MATCHES" === $(xmlDoc).find(
                            "responseStatusStrg").eq(0).text()) {
                        setTimeout(function() {
                            showOPInfo(szIP + " 没有录像文件！");
                        }, 50);
                    }

                    $(".download_ico").click(function () {
                        $(this).addClass("download_ico_hover");
                    })
                },
                error : function() {
                    showOPInfo(szIP + " 搜索录像文件失败！");
                }
            });
}

// 开始回放
function clickStartPlayback(szStartTime,szEndTime) {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), szIP = $("#ip").val(),
        bZeroChannel = $("#channels option").eq($("#channels").get(0).selectedIndex)
            .attr("bZero") == "true" ? true : false,
        iChannelID = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex).iChannelID-32,
        szStartTime = szStartTime||$(".start input").val(), szEndTime = szEndTime||$(".end input").val(),
        szInfo = "",
        bChecked = $("#transstream").attr("checked"), iRet = -1;
    if ("" == szIP) {
        return;
    }

    if (bZeroChannel) {// 零通道不支持回放
        return;
    }

    if (oWndInfo != null) {// 已经在播放了，先停止
        WebVideoCtrl.I_Stop();
    }

    if (bChecked) {// 启用转码回放
        var oTransCodeParam = {
            TransFrameRate : "16",// 0：全帧率，5：1，6：2，7：4，8：6，9：8，10：10，11：12，12：16，14：15，15：18，13：20，16：22
            TransResolution : "2",// 255：Auto，3：4CIF，2：QCIF，1：CIF
            TransBitrate : "23"// 2：32K，3：48K，4：64K，5：80K，6：96K，7：128K，8：160K，9：192K，10：224K，11：256K，12：320K，13：384K，14：448K，15：512K，16：640K，17：768K，18：896K，19：1024K，20：1280K，21：1536K，22：1792K，23：2048K，24：3072K，25：4096K，26：8192K
        };
        iRet = WebVideoCtrl.I_StartPlayback(szIP, {
            iWndIndex:parseInt(g_iWndIndex),
            iChannelID :  iChannelID,
            szStartTime : szStartTime,
            szEndTime : szEndTime,
            oTransCodeParam : oTransCodeParam
        });
    } else {
        iRet = WebVideoCtrl.I_StartPlayback(szIP, {
            iWndIndex:parseInt(g_iWndIndex),
            iChannelID : iChannelID,
            szStartTime : szStartTime,
            szEndTime : szEndTime
        });
    }
    if (0 == iRet) {
        szInfo = "开始回放成功！";
    } else {
        szInfo = "开始回放失败！";
    }
    //showOPInfo(szIP + " " + szInfo);
}

// 停止回放
function clickStopPlayback() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), szInfo = "";

    if (oWndInfo != null) {
        var iRet = WebVideoCtrl.I_Stop();
        if (0 == iRet) {
            szInfo = "停止回放成功！";
        } else {
            szInfo = "停止回放失败！";
        }
        showOPInfo(oWndInfo.szIP + " " + szInfo);
    }
}

// 开始倒放
function clickReversePlayback() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), szIP = $("#ip")
        .val(), bZeroChannel = $("#channels option").eq(
        $("#channels").get(0).selectedIndex).attr("bZero") == "true" ? true
        : false, iChannelID = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex).iChannelID-32, szStartTime = $(".start input")
        .val(), szEndTime = $(".end input").val(), szInfo = "";

    if ("" == szIP) {
        return;
    }

    if (bZeroChannel) {// 零通道不支持回放
        return;
    }

    if (oWndInfo != null) {// 已经在播放了，先停止
        WebVideoCtrl.I_Stop();
    }

    var iRet = WebVideoCtrl.I_ReversePlayback(szIP, {
        iChannelID : iChannelID,
        szStartTime : szStartTime,
        szEndTime : szEndTime
    });

    if (0 == iRet) {
        szInfo = "开始倒放成功！";
    } else {
        szInfo = "开始倒放失败！";
    }
    showOPInfo(szIP + " " + szInfo);
}

// 单帧
function clickFrame() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), szInfo = "";

    if (oWndInfo != null) {
        var iRet = WebVideoCtrl.I_Frame();
        if (0 == iRet) {
            szInfo = "单帧播放成功！";
        } else {
            szInfo = "单帧播放失败！";
        }
        showOPInfo(oWndInfo.szIP + " " + szInfo);
    }
}

// 暂停
function clickPause() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), szInfo = "";

    if (oWndInfo != null) {
        var iRet = WebVideoCtrl.I_Pause();
        // if (0 == iRet) {
        //     szInfo = "暂停成功！";
        // } else {
        //     szInfo = "暂停失败！";
        // }
        // showOPInfo(oWndInfo.szIP + " " + szInfo);
    }
}

// 恢复
function clickResume() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), szInfo = "";

    if (oWndInfo != null) {
        var iRet = WebVideoCtrl.I_Resume();
        // if (0 == iRet) {
        //     szInfo = "恢复成功！";
        // } else {
        //     szInfo = "恢复失败！";
        // }
        // showOPInfo(oWndInfo.szIP + " " + szInfo);
    }
}

// 慢放
function clickPlaySlow() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), szInfo = "";

    if (oWndInfo != null) {
        var iRet = WebVideoCtrl.I_PlaySlow();
        // if (0 == iRet) {
        //     szInfo = "慢放成功！";
        // } else {
        //     szInfo = "慢放失败！";
        // }
        // showOPInfo(oWndInfo.szIP + " " + szInfo);
    }
}

// 快放
function clickPlayFast() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), szInfo = "";
    console.log(g_iWndIndex,oWndInfo);
    if (oWndInfo != null) {
        var iRet = WebVideoCtrl.I_PlayFast();
        // if (0 == iRet) {
        //     szInfo = "快放成功！";
        // } else {
        //     szInfo = "快放失败！";
        // }
        // showOPInfo(oWndInfo.szIP + " " + szInfo);
    }
}

// OSD时间
function clickGetOSDTime() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

    if (oWndInfo != null) {
        var szTime = WebVideoCtrl.I_GetOSDTime();
        if (szTime != -1) {
            $("#osdtime").val(szTime);
            //showOPInfo(oWndInfo.szIP + " 获取OSD时间成功！");
        } else {
            showOPInfo(oWndInfo.szIP + " 获取OSD时间失败！");
        }
    }
}

// 下载录像
var iDownloadID = -1;
var tDownloadProcess = 0;
var szArray=[];
function clickStartDownloadRecord(i) {
    var szIP = $("#ip").val(), szChannelID = $("#channels").val(), szFileName = szIP
        + "_" + szChannelID + "_" + new Date().getTime(), szPlaybackURI = $(
        "#downloadTd" + i).data("playbackURI"),szBool=true;

    if ("" == szIP) {
        return;
    }
    iDownloadID = WebVideoCtrl.I_StartDownloadRecord(szIP, szPlaybackURI,
        szFileName);
    if (iDownloadID < 0) {
        var iErrorValue = WebVideoCtrl.I_GetLastError();
        $(".download_ico").eq(i).removeClass("download_ico_hover");//移除下载效果
        if (34 == iErrorValue) {
            showOPInfo(szIP + " 已下载！");
        } else if (33 == iErrorValue) {
            showOPInfo(szIP + " 空间不足！");
        } else {
            showOPInfo(szIP + " 下载失败！");
        }
    } else {
        //防止多次重复下载
        szArray.forEach(function (v) {
            if(v===i){
                szBool=false;
            }
        })
        if(szBool){
            szArray.push(i);
        }else{
            return;
        }
        $("<div id='downProcess' class='freeze'></div>").appendTo("body");
        tDownloadProcess = setInterval("downProcess(" + i + ","+iDownloadID+")", 1000);
    }
}
// 下载进度
function downProcess(i,n) {
    var iStatus = WebVideoCtrl.I_GetDownloadStatus(n);
    if (0 == iStatus) {

        var iProcess = WebVideoCtrl.I_GetDownloadProgress(n);
        if (iProcess < 0) {
            clearInterval(tDownloadProcess);
            tDownloadProcess = 0;
            m_iDownloadID = -1;
        } else if (iProcess < 100) {
            $("#downProcess"+i).text(iProcess + "%");
        } else {
            $("#downProcess"+i).text("100%");
            setTimeout(function() {
                $("#downProcess").remove();
            }, 1000);

            WebVideoCtrl.I_StopDownloadRecord(n);
            showOPInfo("录像下载完成");
            clearInterval(tDownloadProcess);
            tDownloadProcess = 0;
            m_iDownloadID = -1;
        }
    } else {
        WebVideoCtrl.I_StopDownloadRecord(n);

        clearInterval(tDownloadProcess);
        tDownloadProcess = 0;
        iDownloadID = -1;
    }
}

// 导出配置文件
function clickExportDeviceConfig() {
    var szIP = $("#ip").val(), szInfo = "";

    if ("" == szIP) {
        return;
    }

    var iRet = WebVideoCtrl.I_ExportDeviceConfig(szIP);

    if (0 == iRet) {
        szInfo = "导出配置文件成功！";
    } else {
        szInfo = "导出配置文件失败！";
    }
    showOPInfo(szIP + " " + szInfo);
}

// 导入配置文件
function clickImportDeviceConfig() {
    var szIP = $("#ip").val(), szFileName = $("#configFile").val();

    if ("" == szIP) {
        return;
    }

    if ("" == szFileName) {
        showOPInfo("请选择配置文件！");
        return;
    }

    var iRet = WebVideoCtrl.I_ImportDeviceConfig(szIP, szFileName);

    if (0 == iRet) {
        WebVideoCtrl.I_Restart(szIP, {
            success : function(xmlDoc) {
                $("<div id='restartDiv' class='freeze'>重启中...</div>").appendTo(
                    "body");
                var oSize = getWindowSize();
                $("#restartDiv").css({
                    width : oSize.width + "px",
                    height : oSize.height + "px",
                    lineHeight : oSize.height + "px",
                    left : 0,
                    top : 0
                });
                setTimeout("reconnect('" + szIP + "')", 20000);
            },
            error : function() {
                showOPInfo(szIP + " 重启失败！");
            }
        });
    } else {
        showOPInfo(szIP + " 导入失败！");
    }
}

// 重连
function reconnect(szIP) {
    WebVideoCtrl.I_Reconnect(szIP, {
        success : function(xmlDoc) {
            $("#restartDiv").remove();
        },
        error : function() {
            setTimeout(function() {
                reconnect(szIP);
            }, 5000);
        }
    });
}

// 开始升级
m_tUpgrade = 0;
function clickStartUpgrade(szIP) {
    var szIP = $("#ip").val(), szFileName = $("#upgradeFile").val();

    if ("" == szIP) {
        return;
    }

    if ("" == szFileName) {
        showOPInfo("请选择升级文件！");
        return;
    }

    var iRet = WebVideoCtrl.I_StartUpgrade(szIP, szFileName);
    if (0 == iRet) {
        m_tUpgrade = setInterval("getUpgradeStatus('" + szIP + "')", 1000);
    } else {
        showOPInfo(szIP + " 升级失败！");
    }
}

// 获取升级状态
function getUpgradeStatus(szIP) {
    var iStatus = WebVideoCtrl.I_UpgradeStatus();
    if (iStatus == 0) {
        var iProcess = WebVideoCtrl.I_UpgradeProgress();
        if (iProcess < 0) {
            clearInterval(m_tUpgrade);
            m_tUpgrade = 0;
            showOPInfo(szIP + " 获取进度失败！");
            return;
        } else if (iProcess < 100) {
            if (0 == $("#restartDiv").length) {
                $("<div id='restartDiv' class='freeze'></div>")
                    .appendTo("body");
                var oSize = getWindowSize();
                $("#restartDiv").css({
                    width : oSize.width + "px",
                    height : oSize.height + "px",
                    lineHeight : oSize.height + "px",
                    left : 0,
                    top : 0
                });
            }
            $("#restartDiv").text(iProcess + "%");
        } else {
            WebVideoCtrl.I_StopUpgrade();
            clearInterval(m_tUpgrade);
            m_tUpgrade = 0;

            $("#restartDiv").remove();

            WebVideoCtrl.I_Restart(szIP, {
                success : function(xmlDoc) {
                    $("<div id='restartDiv' class='freeze'>重启中...</div>")
                        .appendTo("body");
                    var oSize = getWindowSize();
                    $("#restartDiv").css({
                        width : oSize.width + "px",
                        height : oSize.height + "px",
                        lineHeight : oSize.height + "px",
                        left : 0,
                        top : 0
                    });
                    setTimeout("reconnect('" + szIP + "')", 20000);
                },
                error : function() {
                    showOPInfo(szIP + " 重启失败！");
                }
            });
        }
    } else if (iStatus == 1) {
        WebVideoCtrl.I_StopUpgrade();
        showOPInfo(szIP + " 升级失败！");
        clearInterval(m_tUpgrade);
        m_tUpgrade = 0;
    } else if (iStatus == 2) {
        mWebVideoCtrl.I_StopUpgrade();
        showOPInfo(szIP + " 语言不匹配！");
        clearInterval(m_tUpgrade);
        m_tUpgrade = 0;
    } else {
        mWebVideoCtrl.I_StopUpgrade();
        showOPInfo(szIP + " 获取状态失败！");
        clearInterval(m_tUpgrade);
        m_tUpgrade = 0;
    }
}

// 检查插件版本
function clickCheckPluginVersion() {
    var iRet = WebVideoCtrl.I_CheckPluginVersion();
    if (0 == iRet) {
        showOPInfo("您的插件版本已经是最新的！");
    } else {
        showOPInfo("检测到新的插件版本！");
    }
}

// 远程配置库
function clickRemoteConfig() {
    var szIP = $("#ip").val(), iDevicePort = parseInt($("#deviceport").val(),
        10)
        || "", szInfo = "";

    if ("" == szIP) {
        return;
    }

    var iRet = WebVideoCtrl.I_RemoteConfig(szIP, {
        iDevicePort : iDevicePort,
        iLan : 1
    });

    if (-1 == iRet) {
        szInfo = "调用远程配置库失败！";
    } else {
        szInfo = "调用远程配置库成功！";
    }
    showOPInfo(szIP + " " + szInfo);
}

function clickRestoreDefault() {
    var szIP = $("#ip").val(), szMode = "basic";
    WebVideoCtrl.I_RestoreDefault(szIP, szMode, {
        success : function(xmlDoc) {
            $("#restartDiv").remove();
            showOPInfo(szIP + " 恢复默认参数成功！");
            // 恢复完成后需要重启
            WebVideoCtrl.I_Restart(szIP, {
                success : function(xmlDoc) {
                    $("<div id='restartDiv' class='freeze'>重启中...</div>")
                        .appendTo("body");
                    var oSize = getWindowSize();
                    $("#restartDiv").css({
                        width : oSize.width + "px",
                        height : oSize.height + "px",
                        lineHeight : oSize.height + "px",
                        left : 0,
                        top : 0
                    });
                    setTimeout("reconnect('" + szIP + "')", 20000);
                },
                error : function() {
                    showOPInfo(szIP + " 重启失败！");
                }
            });
        },
        error : function() {
            showOPInfo(szIP + " 恢复默认参数失败！");
        }
    });
}

function PTZZoomIn() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(10, false, {
            iWndIndex : g_iWndIndex,
            success : function(xmlDoc) {
                //showOPInfo(oWndInfo.szIP + " 调焦+成功！");
            },
            error : function() {
                showOPInfo(oWndInfo.szIP + "  调焦+失败！");
            }
        });
    }
}

function PTZZoomout() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(11, false, {
            iWndIndex : g_iWndIndex,
            success : function(xmlDoc) {
                //showOPInfo(oWndInfo.szIP + " 调焦-成功！");
            },
            error : function() {
                showOPInfo(oWndInfo.szIP + "  调焦-失败！");
            }
        });
    }
}

function PTZZoomStop() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(11, true, {
            iWndIndex : g_iWndIndex,
            success : function(xmlDoc) {
                //showOPInfo(oWndInfo.szIP + " 调焦停止成功！");
            },
            error : function() {
                showOPInfo(oWndInfo.szIP + "  调焦停止失败！");
            }
        });
    }
}

function PTZFocusIn() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(12, false, {
            iWndIndex : g_iWndIndex,
            success : function(xmlDoc) {
                //showOPInfo(oWndInfo.szIP + " 聚焦+成功！");
            },
            error : function() {
                showOPInfo(oWndInfo.szIP + "  聚焦+失败！");
            }
        });
    }
}

function PTZFoucusOut() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(13, false, {
            iWndIndex : g_iWndIndex,
            success : function(xmlDoc) {
                //showOPInfo(oWndInfo.szIP + " 聚焦-成功！");
            },
            error : function() {
                showOPInfo(oWndInfo.szIP + "  聚焦-失败！");
            }
        });
    }
}

function PTZFoucusStop() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(12, true, {
            iWndIndex : g_iWndIndex,
            success : function(xmlDoc) {
                //showOPInfo(oWndInfo.szIP + " 聚焦停止成功！");
            },
            error : function() {
                showOPInfo(oWndInfo.szIP + "  聚焦停止失败！");
            }
        });
    }
}

function PTZIrisIn() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(14, false, {
            iWndIndex : g_iWndIndex,
            success : function(xmlDoc) {
                //showOPInfo(oWndInfo.szIP + " 光圈+成功！");
            },
            error : function() {
                showOPInfo(oWndInfo.szIP + "  光圈+失败！");
            }
        });
    }
}

function PTZIrisOut() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(15, false, {
            iWndIndex : g_iWndIndex,
            success : function(xmlDoc) {
                showOPInfo(oWndInfo.szIP + " 光圈-成功！");
            },
            error : function() {
                showOPInfo(oWndInfo.szIP + "  光圈-失败！");
            }
        });
    }
}

function PTZIrisStop() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(14, true, {
            iWndIndex : g_iWndIndex,
            success : function(xmlDoc) {
                showOPInfo(oWndInfo.szIP + " 光圈停止成功！");
            },
            error : function() {
                showOPInfo(oWndInfo.szIP + "  光圈停止失败！");
            }
        });
    }
}

dateFormat = function(oDate, fmt) {
    var o = {
        "M+" : oDate.getMonth() + 1, // 月份
        "d+" : oDate.getDate(), // 日
        "h+" : oDate.getHours(), // 小时
        "m+" : oDate.getMinutes(), // 分
        "s+" : oDate.getSeconds(), // 秒
        "q+" : Math.floor((oDate.getMonth() + 3) / 3), // 季度
        "S" : oDate.getMilliseconds()
        // 毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (oDate.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    }
    for ( var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
                : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};

// 切换模式
function changeIPMode(iType) {
    var arrPort = [ 0, 7071, 80 ];
    $("#serverport").val(arrPort[iType]);
}

//开始预览
function StartRealPlay(ip,node) {
    var szIP = ip,
        iStreamType = parseInt($("#streamtype").val(),10),
        iChannelID = parseInt($("#channels").val(), 10),
        len=node.children.length;

    if ("" == szIP) {
        return;
    }

    // 窗口分割 3x3
    if (len> 9) {
        changeWndNum(4);
        $("#type").attr('data-value',4).html('4x4');
    } else if (len> 4) {
        changeWndNum(3);
        $("#type").attr('data-value',3).html('3x3');
    } else if (len> 1) {
        changeWndNum(2);
        $("#type").attr('data-value',2).html('2x2');
    } else if (len == 1) {
        changeWndNum(1);
        $("#type").attr('data-value',1).html('1x1');
    }

    $("#channels option").each(function(i) {
        bZeroChannel = $(this).attr("bZero") == "true" ? true : false;
        var iChannelID=parseInt($(this).val(),10),errorData=[];
        for(var j=0;j<len;j++){
            if(iChannelID===parseInt(node.children[j].channelNo,10)){
                var iRet = WebVideoCtrl.I_StartRealPlay(szIP, {
                    iWndIndex : j,
                    iStreamType : iStreamType, //设置默认清晰度
                    iChannelID : iChannelID,
                    bZeroChannel : bZeroChannel,
                    error:function () {
                        var iChannelID=parseInt(this.iChannelID);
                        errorData.push({
                            iWndIndex:j,
                            iChannelID:iChannelID,
                            bZeroChannel:bZeroChannel
                        })
                    }
                });
            }
        }
        //报错后转换为高清
        if(errorData.length>0){
            WebVideoCtrl.I_StartRealPlay(szIP, {
                iWndIndex : errorData[0].iWndIndex,
                iStreamType : 1, //设置默认清晰度
                iChannelID : errorData[0].iChannelID,
                bZeroChannel : errorData[0].bZeroChannel,
                success:function () {
                    if(!video_num[i]){
                        video_num[i]=[];
                    }
                    video_num[i][errorData[0].iChannelID-1]=false;
                }
            });
        }


    });
}

// 获取设备IP
function clickGetDeviceIP() {
    var iDeviceMode = parseInt($("#devicemode").val(), 10), szAddress = $(
        "#serveraddress").val(), iPort = parseInt($("#serverport").val(),
        10) || 0, szDeviceID = $("#deviceid").val(), szDeviceInfo = "";

    szDeviceInfo = WebVideoCtrl.I_GetIPInfoByMode(iDeviceMode, szAddress,
        iPort, szDeviceID);

    if ("" == szDeviceInfo) {
        showOPInfo("设备IP和端口解析失败！");
    } else {
        showOPInfo("设备IP和端口解析成功！");

        var arrTemp = szDeviceInfo.split("-");
        $("#loginip").val(arrTemp[0]);
        $("#deviceport").val(arrTemp[1]);
    }
}

function changeChannel() {
    setTimeout(function() {
        clickStartRealPlay();
    }, 100);
}

function StopRealPlay() {

    var length = $("#channels option").length;
    if(length && length > 0) {
        $("#channels option").each(function(i) {
            var index = WebVideoCtrl.findWndIndexByIndex(i);
            WebVideoCtrl.I_Stop(i);
            // showOPInfo(index);
            // var oWndInfo = WebVideoCtrl.I_GetWindowStatus(index);
            // if (oWndInfo != null) {
            // // 	// showOPInfo(index);
            // WebVideoCtrl.I_Stop();
            // }
        });
        $("#channels").empty();
    }

    var allWndInfo = WebVideoCtrl.I_GetWindowStatus();
    // 循环遍历所有窗口，如果有窗口打开了，先关闭
    for (var i = 0, iLen = allWndInfo.length; i < iLen; i++) {
        var oWndInfo = allWndInfo[i];
        if (oWndInfo != null) {
            // showOPInfo(index);
            WebVideoCtrl.I_Stop(oWndInfo.iIndex);
        }
    }
}

