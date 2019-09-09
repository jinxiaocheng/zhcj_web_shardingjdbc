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
<%--    <link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}" />--%>
    <link type="text/css" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.min.css?v=${v}" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/resources/static/css/video/newVideo.css?v=${v}" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
    <script type="text/javascript" src="pngfix/lib/PIE_IE678.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
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
      width: 100vw;
      height: 100vh;
      padding-left: 320px;
      padding-right: 440px;
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
      width: 300px;
      height: 99%;
    }

    #video_contorl{
      width:100%;
    }
    .video_contorl{
      position: absolute;
      width:420px;
      right: 0;
      top:0;
    }

    .ztree {
      overflow: auto;
      margin-right: 10px;
      height: 98%;
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
      <div id="video_contorl" style="position: relative">
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
            <span class="ml_more" onmousedown="PTZIrisIn()" onmouseup="PTZIrisStop()"></span>
            <label>光圈</label>
            <span class="ml_less" onmousedown="PTZIrisOut()" onmouseup="PTZIrisStop()"></span>
          </li>
          <li class="col-xs-4">
            <span class="ml_more" onmousedown="PTZFocusIn()" onmouseup="PTZFoucusStop()"></span>
            <label>变焦</label>
            <span class="ml_less" onmousedown="PTZFoucusOut()" onmouseup="PTZFoucusStop()"></span>
          </li>
          <li class="col-xs-4">
            <span class="ml_more" onmousedown="PTZZoomIn()" onmouseup="PTZZoomStop()"></span>
            <label>变倍</label>
            <span class="ml_less" onmousedown="PTZZoomout()" onmouseup="PTZZoomStop()"></span>
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
            <span onclick="clickStartRecord();"></span>
            <label>开始录像</label>
          </div>
          <div class="off_video">
            <span onclick="clickStopRecord();"></span>
            <label>停止录像</label>
          </div>
          <div class="take_video">
            <span onclick="clickCapturePic();"></span>
            <label>视频抓拍</label>
          </div>
        </div>
        <div class="line"></div>
        <%--录像回放--%>
        <div class="video_playback">
          <p>录像回放</p>
        </div>
        <div class="line"></div>
        <div class="time">
          <div class="search_time">
            <p class="start">
              <label>开始时间</label><input type="text" placeholder="请输入开始时间" onclick="WdatePicker({dateFmt:'yyyy-MM-dd H-mm-ss'})">
            </p>
            <div class="line"></div>
            <p class="end">
              <label>结束时间</label><input type="text" placeholder="请输入结束时间" onclick="WdatePicker({dateFmt:'yyyy-MM-dd H-mm-ss'})">
            </p>
            <div class="line"></div>
          </div>
          <div class="search">
            <span onclick="clickRecordSearch(0);"></span>
          </div>
          <ul class="video_download">
            <%--  <li>
                <p>
                  <label class="video_name"><span class="pull-left">sadf </span><span class="pull-right">asfd </span></label>
                  <label class="video_time"><span class="pull-left">asfd </span><span class="pull-right">asdf </span></label>
                </p><span class="download_ico"></span>
              </li>--%>
          </ul>
        </div>
        <%--播放 停止 快进。。。--%>
        <div class="control_playback">
          <div>
            <span class="cp1" onclick="clickStartPlayback();"></span>
            <span class="cp3" onclick="clickPause();"></span>
            <span class="cp4" onclick="clickResume();"></span>
            <span class="cp2" onclick="clickStopPlayback();"></span>
            <span class="cp5" onclick="clickPlaySlow();"></span>
            <span class="cp6" onclick="clickPlayFast();"></span>
          </div>
          <p id="path">保存路径:<br>&nbsp;&nbsp;&nbsp;&nbsp; C:\Documents and Settings\Administrator\Web</p>
        </div>

      </div>
      <!--点击收缩-->
      <a class="pngfix" href="javascript:void(0);"></a>
    </div>

    <div id="" class="left" style="display: none">
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
<%--  <!--点击收缩-->
  <a class="treePngfix" href="javascript:void(0);"></a>--%>

  <script>var ctx = "${ctx}",video_sharpness=[];video_num=[];</script>
  <!--[if gte IE 9]>
  <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
  <![endif]-->
  <![if !IE]>
  <script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
  <![endif]>
  <script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
  <script type="text/javascript" src="${ctx}/resources/static/plugins/DatePicker/wdatePicker.js?v=${v}"></script>
  <script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
  <script type="text/javascript" src="${ctx}/resources/static/js/common/extend.js?v=${v}"></script>
  <script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
  <script type="text/javascript" src="${ctx}/resources/static/plugins/zTree/v3/js/jquery.ztree.core-3.5.js?v=${v}"></script>
  <script>
    document.write('<script type="text/javascript" src="${ctx}/resources/static/js/video/preview_nvr.js?'+Math.random()+'" id="reloadNvrJs"><\/script>')
  </script>
  <script type="text/javascript" src="${ctx}/resources/static/js/lib/webVideoCtrl.js?v=${v}" id="reloadJs"></script>
  <script type="text/javascript" src="${ctx}/resources/static/js/video/newVideo.js?v=${v}"></script>
  <script type="text/javascript">
        //通道数据存在数组中
        var chnname = new Array();
        $(function(){
            videoWidth();
            initTreeWidget();
            //滚动条美化
            $("#video_contorl,.video_download,#orgnaizeTree").niceScroll({
                styler:"fb",
                cursorcolor:"#cccccc",
                cursorwidth: '5',
                cursorborderradius: '5px',
                background: '',
                autohidemode: false,
                spacebarenabled:false,
                cursorborder: '0',
                zindex: '1000'
            });

        });

        function videoWidth() {
            /*var body_w = $("body").width();
            var menu_left_tree = $(".menu-left-tree").outerWidth();
            var video_c_w = $("#video_contorl").width();
            var video_w = body_w - menu_left_tree - video_c_w-100;
            $("#divPlugin").width(video_w);*/
            var winW=$(window).width();
            if(winW<1080){
                var obj=$(".pngfix").parent();
                var box_obj=$(".container-full");
                op_bool=false;
                obj.css("right","-418px")
                box_obj.css("paddingRight","20px");
                $(".pngfix").addClass("close");
            }

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
            var url = "${ctx}/video/escst/nvrPreview/fetchTreeNodeList";
            $.post(url,function(data) {
                if(data.status == 1){
                    var json = data.value;
                    zTreeObj = $.fn.zTree.init($tree, setting, json);
                } else{
                    layer.alert(data.msg)
                }
            });
        };

        //点击预览视频
        var nodeClick = function(event, treeId, treeNode) {
            if (treeNode && treeNode.level == 1) {
                video_sharpness=[];video_num=[];//初始化储存清晰度数组
                var nvrVO = treeNode.nvrVO;
                $(".PTZ button").attr("data-value","2").html("2x2");
                //获取已经登录的nvr的ip
                var ip = $("#ip").val();
                if(ip != null && ip != "") {
                    StopRealPlay();
                    clickLogout(ip);
                }

                //登录nvr
                clickLogin(nvrVO);

            } else if (treeNode && treeNode.level == 2) {
                var parentNode = treeNode.getParentNode();
                var nvrVO = parentNode.nvrVO;
                var channelNo = treeNode.channelNo;
                video_sharpness[g_iWndIndex]=parseInt(channelNo)-1;
                loginRealPlay(nvrVO,channelNo);
            }
        }

    </script>
</body>
</html>












