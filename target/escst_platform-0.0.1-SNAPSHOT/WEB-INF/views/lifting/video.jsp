<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>可视化监测</title>
    <link rel="stylesheet" href="${ctx}/resources/static/plugins/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}" />
    <script>
        document.write("<link type='text/css' href='${ctx}/resources/static/css/video/video.css?version=" + new Date().getTime() + "' rel='stylesheet' />");
    </script>
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.min.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/video/newVideo.css?v=${v}" />
    <!--[if lt IE 9]>
    <link rel="stylesheet" href="${ctx}/resources/static/css/video/videoIe.css?v=${v}"/>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="pngfix/lib/PIE_IE678.js?v=${v}"></script>
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
      background: #fff;
    }

    .container-full {
     position: absolute;
      left: 0;
      top:0;
      right: 0;
      bottom: 0;
      padding-left: 300px;
      padding-right: 370px;

    }

    @media ( max-width : 1024px) {
      .container-full {
        margin-top: 3px;
      }
    }

    .menu-left-tree {
      position: absolute;
      left: 0;
      top:0;
      width: 290px;
      height: 100%;
      padding: 10px;
      padding-right: 0;
      box-sizing: border-box;
    }

    #video_contorl{
      width:100%;
    }
    .video_contorl{
      position: absolute;
      width:350px;
      right: 0;
      top:0;
      bottom: 0;
      padding: 10px;
      padding-left: 0;
      box-sizing: border-box;
    }

    .ztree {
      overflow: auto;
      margin-right: 10px;
      height:100%;
      background: #fff;
      border: 1px solid #cfcfcf;
      border-radius: 6px;
      box-sizing: border-box;
      box-shadow: 0 0 4px #dfdfdf;
    }

    /*菜单收缩切换*/
    .pngfix {
      position: absolute;top: 50%;left:-17px;margin-top: -60px;
      background: url("${ctx}/resources/static/images/icon_arrow.png") no-repeat 0 0;
      display: block;
      width: 17px;
      height: 61px;
      transform:rotate(180deg);
      -ms-transform:rotate(180deg); 	/* IE 9 */
      -moz-transform:rotate(180deg); 	/* Firefox */
      -webkit-transform:rotate(180deg); /* Safari 和 Chrome */
      -o-transform:rotate(180deg); 	/* Opera */
    }
    .pngfix.close{
      background-position:0 -61px ;
      opacity: 1;
    }
    .pngfixA {
      position: absolute;top: 50%;right:-9px;margin-top: -60px;
      background: url("${ctx}/resources/static/images/icon_arrow.png") no-repeat 0 0;
      display: block;
      width: 17px;
      height: 61px;
    }
    .pngfixA.close{
      background-position:0 -61px ;
      opacity: 1;
    }

    #divPlugin{
      padding: 10px;
      box-sizing: border-box;
    }
    #divPlugin embed{
      box-shadow: 0 0 10px #262626;
    }

  </style>
<body>

  <div class="container-full">

    <div class="menu-left-tree">
      <ul id="orgnaizeTree" class="ztree"></ul>
      <!--点击收缩-->
      <a class="pngfixA" href="javascript:void(0);"></a>
    </div>

    <div id="divPlugin" class="left" style="width: 100%; height: 100%;"></div>

    <div style="" class="video_contorl">
      <div id="video_contorl" style="position: relative;">
        <%--云台控制--%>
        <div class="PTZ">
          <span class="pull-left contorl_name">窗口布局</span>
          <div class="form-group  queryCondition pull-right" >
            <button class="dropdown-toggle"  data-toggle="dropdown" aria-expanded="false"
                    data-value="2" id="type">
              2x2
            </button>
            <ul class="dropdown-menu" style=" min-width:80px;margin-left: -50px;right: 22px;text-align: center;">
              <li><a data-value="1">1x1</a></li>
              <li><a data-value="2">2x2</a></li>
              <li><a data-value="3">3x3</a></li>
              <li><a data-value="4">4x4</a></li>
            </ul>
          </div>
        </div>
        <div class="line "></div>
        <%--清晰度--%>
        <div class="sharpness">
          <span class="contorl_name pull-left">清晰度</span>
          <div class="pull-right con_sharpness " kk-value="2">
            <div>
              <p><span class="sh2">标清&nbsp;&nbsp;</span><span class="sh1">&nbsp;&nbsp;高清</span></p>
            </div>
            <span class="on_off"></span>
          </div>
          <div class="clearfix"></div>
        </div>
        <%--方向--%>
        <div class="orientation">
          <div class="or_center" onclick="mouseDownPTZControl(9);">自动</div>
          <div class="or_0 or" onmousedown="mouseDownPTZControl(4);" onmouseup="mouseUpPTZControl();"></div>
          <div class="or_1 or" onmousedown="mouseDownPTZControl(8);" onmouseup="mouseUpPTZControl();" ></div>
          <div class="or_2 or" onmousedown="mouseDownPTZControl(2);" onmouseup="mouseUpPTZControl();"></div>
          <div class="or_3 or" onmousedown="mouseDownPTZControl(6);" onmouseup="mouseUpPTZControl();"></div>
          <div class="or_4 or" onmousedown="mouseDownPTZControl(3);" onmouseup="mouseUpPTZControl();"></div>
          <div class="or_5 or" onmousedown="mouseDownPTZControl(5);" onmouseup="mouseUpPTZControl();"></div>
          <div class="or_6 or" onmousedown="mouseDownPTZControl(1);" onmouseup="mouseUpPTZControl();"></div>
          <div class="or_7 or" onmousedown="mouseDownPTZControl(7);" onmouseup="mouseUpPTZControl();"></div>
        </div>
        <%--光圈 变焦 变倍--%>
        <ul class="row more_less">
          <li class="col-xs-4">
            <span class="ml_more" onmousedown="PTZZoomIn()" onmouseup="PTZZoomStop()"></span>
            <label>变倍</label>
            <span class="ml_less" onmousedown="PTZZoomout()" onmouseup="PTZZoomStop()"></span>
          </li>
          <li class="col-xs-4">
            <span class="ml_more" onmousedown="PTZFocusIn()" onmouseup="PTZFoucusStop()"></span>
            <label>变焦</label>
            <span class="ml_less" onmousedown="PTZFoucusOut()" onmouseup="PTZFoucusStop()"></span>
          </li>
          <li class="col-xs-4">
            <span class="ml_more" onmousedown="PTZIrisIn()" onmouseup="PTZIrisStop()"></span>
            <label>光圈</label>
            <span class="ml_less" onmousedown="PTZIrisOut()" onmouseup="PTZIrisStop()"></span>
          </li>
        </ul>
        <%--云台速度--%>
        <div class="YT_speed">
          <p>云台速度</p>
          <div class="speed_controller" sc-num="4">
            <div class="sc">
              <div class="c_p">
                <p class="c_n"></p>
              </div>
              <span class="c_c"></span>
            </div>
            <div class="scale">
              <span class="s1" ></span>
              <span class="s2" ></span>
              <span class="s3" ></span>
              <span class="s4"></span>
              <span class="s5"></span>
              <span class="s6"></span>
              <span class="s7"></span>
            </div>
          </div>
        </div>
        <%--录像--%>
        <div class="c_video">
          <div class="on_video">
            <span onclick="clickStartRecord('realplay');"></span>
            <label>开始录像</label>
          </div>
          <div class="off_video">
            <span onclick="clickStopRecord('realplay');"></span>
            <label>停止录像</label>
          </div>
          <div class="take_video">
            <span onclick="clickCapturePic();"></span>
            <label>视频抓拍</label>
          </div>
        </div>
        <div class="line"></div>
        <%--视频回放--%>
        <div class="video_playback">
          <p>视频回放</p>
        </div>
        <div class="line"></div>
        <div class="time">
          <div class="search_time">
            <p class="start">
              <label>开始时间</label><input type="text" placeholder="请输入开始时间" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
            </p>
            <div class="line"></div>
            <p class="end">
              <label>结束时间</label><input type="text" placeholder="请输入结束时间" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
            </p>
            <div class="line"></div>
          </div>
          <div class="search">
            <span onclick="clickRecordSearch(0);"></span>
          </div>
          <div class="clearfix"></div>
          <ul class="video_download">
              <%--<li>
                <p>
                  <label class="video_name"><span class="pull-left">00000000982000000 </span><span class="pull-right">asfd </span></label>
                  <label class="video_time">
                    <span class="pull-left">开始时间</span><span class="pull-right">2018-01-17 00:00:00  </span><br>
                    <span class="pull-left">结束时间</span><span class="pull-right">2018-01-17 00:00:00  </span>
                  </label>
                </p><span class="download_ico"></span>
              </li>--%>
          </ul>
        </div>
        <%--播放 停止 快进。。。--%>
        <div class="control_playback">
          <div >
            <span class="cp1" onclick="clickStartPlayback();"><i>播放</i></span>
            <span class="cp3" onclick="clickPause();"><i>暂停</i></span>
            <span class="cp4" onclick="clickResume();"><i>恢复</i></span>
            <span class="cp2" onclick="clickStopPlayback();"><i>停止</i></span>
            <span class="cp5" onclick="clickPlaySlow();"><i>慢放</i></span>
            <span class="cp6" onclick="clickPlayFast();"><i>快放</i></span>
          </div>
          <p id="path">保存路径:<br>&nbsp;&nbsp;&nbsp;&nbsp; C:\Documents and Settings\Administrator\Web</p>
        </div>

      </div>
      <!--点击收缩-->
      <a class="pngfix" href="javascript:void(0);"></a>
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
               <%-- 保存路径: <i id="path">C:\Documents and Settings\Administrator\Web</i></td>--%>
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
                                                                            class="btn2" value="开始剪辑" onclick="clickStartRecord('playback');" /> <input
                      type="button" class="btn2" value="停止剪辑"
                      onclick="clickStopRecord('playback');" /> <input type="button"
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


  </div>
<%--  <!--点击收缩-->
  <a class="treePngfix" href="javascript:void(0);"></a>--%>

  <script>var ctx = "${ctx}",video_sharpness=[];video_num=[];</script>
  <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
  <script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
  <script type="text/javascript" src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}"></script>
  <script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
  <script type="text/javascript" src="${ctx}/resources/static/js/index/jquery.slimscroll.js?v=${v}"></script>
  <script type="text/javascript" src="${ctx}/resources/static/plugins/des/tripledes.js?v=${v}"></script>
  <script type="text/javascript" src="${ctx}/resources/static/plugins/des/mode-ecb-min.js?v=${v}"></script>
  <script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
  <script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
  <script type="text/javascript" src="${ctx}/resources/static/js/video/preview_nvr.js?v=${v}"  id="reloadNvrJs"></script>
  <script type="text/javascript" src="${ctx}/resources/static/js/lib/webVideoCtrl.js?v=${v}" id="reloadJs"></script>
  <script type="text/javascript" src="${ctx}/resources/static/js/video/newVideo.js?v=${v}"></script>
  <script type="text/javascript">
        //通道数据存在数组中
        var chnname = new Array();
        $(function(){
            videoWidth();
            initTreeWidget();
        });

        function videoWidth() {
            var winW=$(window).width();
            if(winW<1080){
                closeRightBox()
            }
        }

        function closeRightBox() {
            var obj=$(".pngfix").parent();
            var box_obj=$(".container-full");
            op_bool=false;
            obj.css("right","-345px")
            box_obj.css("paddingRight","20px");
            $(".pngfix").addClass("close");
        }
        function closeLeftBox() {
            var obj=$(".pngfixA").parent();
            var box_obj=$(".container-full");
            op_boolA=false;
            obj.animate({"left":"-281px"},300,function () {
                $(".pngfixA").addClass("close");
            });
            box_obj.animate({"paddingLeft":"20px"},300);
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
            var url = "${ctx}/hookVideo/queryProjectVideo";
            var param = {"cityId":""};
            $.post(url,param,function(data) {
                if(data.status == 1){
                    var json = data.value;
                    zTreeObj = $.fn.zTree.init($tree, setting, json);
                    var treeObj = $.fn.zTree.getZTreeObj("orgnaizeTree");
                    var nodes = treeObj.getNodesByParam("level", 1, null);
                    if(nodes.length==1){
                        closeLeftBox();
                        if(nodes[0].encryptNvr){
                            lookVideo(nodes[0]);
                        }else{
                            lookVideo(nodes[0].children[0]);
                        }
                    }
                } else{
                    layer.alert(data.msg);
                }
            });
        };

        //点击预览视频
        var nodeClick = function(event, treeId, treeNode) {
            lookVideo(treeNode)
        }
        //预览事件
        function lookVideo(treeNode) {
            if(treeNode && treeNode.level == 1) {
                if(treeNode.encryptNvr){
                    videoP()
                }
            } else if (treeNode && treeNode.level == 2) {
                if(treeNode.isParent){
                    videoP();
                }else{
                    videoC();
                }
            } else if (treeNode && treeNode.level == 3){
                videoC();
            }

            //点击父级菜单
            function videoP() {
                g_flag.data=treeNode.children;
                g_flag.index=0;
                video_sharpness=[];video_num=[];//初始化储存清晰度数组

                var nvrVO =desNvrVO(treeNode.encryptNvr);
                $(".PTZ button").attr("data-value","2").html("2x2");
                //获取已经登录的nvr的ip
                var ip = $("#ip").val();
                if(ip != null && ip != "") {
                    StopRealPlay();
                    clickLogout(ip);
                }

                //登录nvr
                clickLogin(nvrVO,treeNode);
            }
            //点击子级视频
            function videoC() {
                g_flag.data[g_flag.index]=treeNode;
                if(g_flag.data[g_flag.index].flag){
                    $(".orientation").show();
                    $(".YT_speed").show();
                }else{
                    $(".orientation").hide();
                     $(".YT_speed").hide();
                }
                var parentNode = treeNode.getParentNode();
                var nvrVO =desNvrVO(parentNode.encryptNvr);
                var channelNo = treeNode.channelNo;
                video_sharpness[g_iWndIndex]=parseInt(channelNo)-1;
                loginRealPlay(nvrVO,channelNo);
            }
        }
        // DES 转换
        function desNvrVO(data) {
            var list=decryptByDES(data,'escst@123').split(",");
            return {
                appPort:0,
                ip:list[0],
                password:list[3],
                port:list[1],
                userName:list[2]
            }
        }
    </script>
</body>
</html>












