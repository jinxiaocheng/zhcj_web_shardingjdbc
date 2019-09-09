<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>视频显示</title>
<body>
  <div id="divPlugin" class="left" style="width: 100%; height: 100%;"></div>
  <input type="button" id="import_btn" value="导入通道数据"/>

  <div style="" class="video_contorl">
    <div id="" class="left" style="position: relative;display: none">
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
        <legend>窗口布局</legend>
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
                       onmouseup="mouseUpPTZControl();" />
              <input type="button"
                     class="btn" value="下" onmousedown="mouseDownPTZControl(2);"
                     onmouseup="mouseUpPTZControl();" />
              <input type="button"
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
      <fieldset class="playback" style="">
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
            <td colspan="2">
              <input type="button" class="btn2"
                     value="开始回放" onclick="clickStartPlayback();" /> <input
                    type="button" class="btn2" value="停止回放"
                    onclick="clickStopPlayback();" />
              <input type="button"
                     class="btn" value="倒放" onclick="clickReversePlayback();" /> <input
                    type="button" class="btn" value="单帧" onclick="clickFrame();" />
              <input id="transstream" type="checkbox" class="vtop" />&nbsp;启用转码码流
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

  <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
  <script type="text/javascript" src="${ctx}/resources/static/js/lib/webVideoCtrl.js?v=${v}" id="reloadJs"></script>

  <script type="text/javascript">

        //通道数据存在数组中
        var channelArr = new Array()
            ,dataNum=0;

        $(function(){


            // 检查插件是否已经安装过
            if (-1 == WebVideoCtrl.I_CheckPluginInstall()) {
                var url = ctx + '/resources/WebComponents.exe';
                window.location.href = url;
                return;
            }


            // 初始化插件参数及插入插件
            WebVideoCtrl.I_InitPlugin("100%", "98%", {
                iWndowType : 2,
                cbSelWnd : function(xmlDoc) {
                    g_iWndIndex = $(xmlDoc).find("SelectWnd").eq(0).text();
                    var szInfo = "当前选择的窗口编号：" + g_iWndIndex;
                    console.log(szInfo);
                }
            });

            WebVideoCtrl.I_InsertOBJECTPlugin("divPlugin");

            var url = "${ctx}/video/escst/queryNvrList";
            $.post(url,function(data) {
                if (data.status == 1) {
                    var nvrList = data.value;
                    dataNum=nvrList.length;
                    for (var i = 0;i < nvrList.length;i++) {
                        var nvrInfo = nvrList[i];
                        clickLogin(nvrInfo);
                    }
                }
            });

        });

        $("#import_btn").click(function(){
            console.log(channelArr);
            var paramData ="";
            if (channelArr.length > 0) {
                paramData = JSON.stringify(channelArr);
            }
            var data = {"data":paramData};
            $.ajax({
                type: "post",
                url: '${ctx}/video/escst/saveNvr',
                data:data,
                success: function (data) {
                    if(data.status == 1){
                        alert("success");
                    } else {
                        alert(data.msg);
                    }
                },
                error: function (data, status, e) {   //提交失败自动执行的处理函数
                    alert(e);
                }
            });
        });

        // 登录
        function clickLogin(nvrInfo) {
            var szIP = nvrInfo.ip, szPort = nvrInfo.port, szUsername = nvrInfo.userName, szPassword = nvrInfo.password;

            if ("" == szIP || "" == szPort) {
                return;
            }
            var iRet = WebVideoCtrl.I_Login(szIP, 1, szPort, szUsername, szPassword, {
                success : function(xmlDoc) {

                    console.log(szIP + " 登录成功！");

                    setTimeout(function() {
                        getChannelInfo(nvrInfo);
                    }, 100);
                },
                error : function() {
                    console.log(--dataNum)
                    console.log(szIP + " 登录失败！");
                }
            });

            if (-1 == iRet) {
                console.log(szIP + " 已登录过！");
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
            } else {
                szInfo = "退出失败！";
            }
            console.log(szIP + " " + szInfo);
        }

        // 获取通道
        function getChannelInfo(nvrInfo) {
            var szIP = nvrInfo.ip,
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
                        });
                        console.log(szIP + " 获取模拟通道成功！");
                    },
                    error : function() {
                        console.log(szIP + " 获取模拟通道失败！");
                    }
                });

            // 数字通道
            WebVideoCtrl.I_GetDigitalChannelInfo(szIP, {
                async : false,
                success : function(xmlDoc) {
                    var oChannels = $(xmlDoc).find("InputProxyChannelStatus");

                    $.each(oChannels,function(i) {
                        var id = parseInt($(this).find("id").eq(0).text(), 10),
                            name = $(this).find("name").eq(0).text(),
                            online = $(this).find("online").eq(0).text();

//                        alert(name);
                        var status = 1;
                        if ("false" == online) {// 过滤禁用的数字通道
                            status = 0;
                        }
                        if ("" == name) {
                            name = "未定义通道" + id;
                        }
                        channelArr.push({
                            constructionId: nvrInfo.constructionId
                            ,constructionName: nvrInfo.constructionName
                            ,channelNo:id
                            ,name:name
                            ,supplier:1
                            ,status:status
                        });
                    });
                    console.log(szIP + " 获取数字通道成功！");
                    if(--dataNum==0){
                        console.log(channelArr)
                    }
                    console.log(dataNum)
                },
                error : function() {
                    console.log(szIP + " 获取数字通道失败！");
                    if(--dataNum==0){
                        console.log(channelArr)
                    }
                    console.log(dataNum)
                }
            });

            // 零通道
            WebVideoCtrl.I_GetZeroChannelInfo(szIP, {
                async : false,
                success : function(xmlDoc) {
                    var oChannels = $(xmlDoc).find("ZeroVideoChannel");
                    console.log(szIP + " 获取零通道成功！");
                },
                error : function() {
                    console.log(szIP + " 获取零通道失败！");
                }
            });
        }
    </script>
</body>
</html>












