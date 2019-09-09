/**
 * @desc  整体外框控制
 * @param null
 * @return
 * @author kz
 * @date 2018/5/8 13:19
 */
var by_index = {
    biyue: new biYue(),
    init: function () {
        var _self = this;
        $('[data-toggle="tooltip"]').tooltip();

        //图片浏览
        function photos(data, index) {
            layer.photos({
                photos: {
                    "title": "集体照", //相册标题
                    "id": "photos", //相册id
                    "start": index, //初始显示的图片序号，默认0
                    "data": data
                },
                closeBtn: 2,
                shift: "",
                anim: "",
                shade: [0.6, '#393D49'],
                maxHeight: 200
            });
        }

        window.photos = photos;

        //父子转换
        function convert(rows) {
            function exists(rows, pId) {
                for (var i = 0; i < rows.length; i++) {
                    if (rows[i].id == pId) return true;
                }
                return false;
            }

            var nodes = [];
            // get the top level nodes
            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                if (!exists(rows, row.pId)) {
                    var obj = {};
                    for (var j in row) {
                        obj[j] = row[j];
                    }
                    nodes.push(obj);
                }
            }

            var toDo = [];
            for (var i = 0; i < nodes.length; i++) {
                toDo.push(nodes[i]);
            }
            while (toDo.length) {
                var node = toDo.shift();	// the parent node
                // get the children nodes
                for (var i = 0; i < rows.length; i++) {
                    var row = rows[i];
                    if (row.pId == node.id) {
                        var child = {};
                        for (var j in row) {
                            child[j] = row[j];
                        }
                        if (node.children) {
                            node.children.push(child);
                        } else {
                            node.children = [child];
                        }
                        toDo.push(child);
                    }
                }
            }
            return nodes;
        }

        window.convert = convert;

        //权限控制
        function powerText(prop) {
            switch (prop) {
                case "list":
                    return "查询";
                    break;
                case "action":
                    return "云台控制";
                    break;
                case "view":
                    return "查看";
                    break;
                case "add":
                    return "新增";
                    break;
                case "save":
                    return "录入";
                    break;
                case "edit":
                    return "编辑";
                    break;
                case "allot":
                    return "分配角色";
                    break;
                case "allotVideo":
                    return "分配视频";
                    break;
                case "allotDoor":
                    return "分配门";
                    break;
                case "update":
                    return "修改";
                    break;
                case "reset":
                    return "重置密码";
                    break;
                case "delete":
                    return "删除";
                    break;
                case "audit":
                    return "审核";
                    break;
                case "submit":
                    return "提交";
                    break;
                case "print":
                    return "打印";
                    break;
                case "import":
                    return "导入";
                    break;
                case "export":
                    return "导出";
                    break;
                case "affirm":
                    return "确认完成";
                    break;
                case "distribution":
                    return "生成用户";
                    break;
                case "download":
                    return "下载模板";
                    break;
                case "task":
                    return "历史任务";
                    break;
                case "chart":
                    return "趋势图";
                    break;
                case "assign":
                    return "分配工地";
                    break;
                case "refresh":
                    return "数据初始化";
                    break;
                case "historyData":
                    return "历史数据";
                    break;
                case "warningData":
                    return "预警数据";
                    break;
                case "trendChart":
                    return "趋势图";
                    break;
                case "pointChart":
                    return "测点部署图";
                    break;
                case "viewThreshold":
                    return "查看阈值";
                    break;
                default:
                    return ""
            }
        }

        window.powerText = powerText;
        _self.getMenuData();
        //点击用户图像弹出模态框
        $(".user-name,.user-img").click(function () {
            $(".biyue-header-aside .dropdown-menu").show();
        });
        $(".biyue-header-aside .close").click(function () {
            $(".biyue-header-aside .dropdown-menu").hide();
        });
        $(".biyue-header-aside .set-password").click(function () {
            $(".biyue-header-aside .dropdown-menu").hide();
        });

        //点击tab操作
        ;(function () {
            var setT = "";
            $(".tab-tools").hover(function () {
                if (setT) {
                    clearTimeout(setT);
                }
                $(".tab-tools .fa-caret-down").addClass("active");
                $(".tab-tools-list").addClass('layui-show');
            }, function () {
                setT = setTimeout(function () {
                    $(".tab-tools .fa-caret-down").removeClass("active");
                    $(".tab-tools-list").removeClass('layui-show');
                }, 300);
            });
        })();


        //按钮点击特效
        Waves.init({
            duration: 300,
            delay: 100
        });
        Waves.attach(".nav-icons", ['waves-block']);
        Waves.attach(".set-password", ['waves-block']);
        Waves.attach(".user-layout", ['waves-block']);
        Waves.attach(".biyue-logo", ['waves-block']);
        Waves.attach(".user-name", ['waves-block']);

        //刷新当前页面
        $(".refresh").click(function () {
            if (document.querySelector('.layui-tab-item.layui-show iframe')) {
                document.querySelector('.layui-tab-item.layui-show iframe').contentWindow.location.reload(true);
            }
        });
        //关闭其他页
        $(".closeOther").click(function () {
            $('.layui-tab-card .layui-tab-title .layui-this').siblings().each(function () {
                var id = $(this).attr("lay-id");
                _self.tab().del(id);
            })
        });
        //关闭全部页
        $(".closeAll").click(function () {
            $('.layui-tab-card .layui-tab-title li').each(function () {
                var id = $(this).attr("lay-id");
                _self.tab().del(id);
            })
        });
    },
    leftMenu: function () {
        var _self = this;
        var biyue = new biYue();
        //点击 切换左侧菜单
        $(".biyue-nav-aside").on('click', '.biyue-left-menu>li', function () {
            $(this).addClass('active').siblings().removeClass('active');
            $(this).addClass('active').siblings().find(".biyue-down-list").css('height', '0px');
            var url = $(this).children('a').attr("biyue-link");
            var id = $(this).children('a').attr("biyue-id");
            var name = $(this).find('span').html();
            var liHeight = $(this).find(".biyue-down-list li").outerHeight() + 10;
            liHeight = liHeight === 0 ? 34 : liHeight;
            var liNum = $(this).find(".biyue-down-list li").length;
            $(this).find(".biyue-down-list").css('height', (liNum * liHeight) + 'px');

            if (!!url) {
                $(".biyue-nav-aside .biyue-down-list>li").removeClass('active');
                _self.tab().tabAdd({
                    url: url,
                    name: name,
                    id: id
                })
            }

        });
        $(".biyue-nav-aside").on('click', '.biyue-left-menu>li.active', function () {
            $(this).removeClass('active');
            $(this).find(".biyue-down-list").css('height', '0px');
        });
        $(".biyue-nav-aside").on('click', '.biyue-down-list>li', function (e) {
            biyue.stopBubble(e);
            $(".biyue-nav-aside .biyue-down-list>li").removeClass('active');
            $(this).addClass('active');
            $(this).parent().parent().addClass('active').siblings().removeClass();
            var url = $(this).attr("biyue-link");
            var id = $(this).attr("biyue-id");
            var name = $(this).text();
            _self.tab().tabAdd({
                url: url,
                name: name,
                id: id
            })
        });
        //左侧菜单点击禁止冒泡
        $(".biyue-nav-aside").on('click', '.biyue-down-list', function (e) {
            biyue.stopBubble(e);
        })
        //左侧菜单滚动条
        var myScroll = $(".biyue-left-menu").slimScroll({
            position: 'left'
            , size: "5px"
            , height: '100%'
            , color: '#838383'
            , disableFadeOut: true
            , wheelStep: '6px'
        })

    },
    minMenu: function () { //缩小菜单
        var type = 0;
        $(".biyue-min").click(function () {
            var bool = $("body").hasClass('biyue-small');
            var width = $(".biyue-main-header").width();
            if (type === 0 && width < 1200) {
                bool = true;
                type = 1;
            }
            if (bool) {
                $("body").removeClass("biyue-small");
                $("body").removeClass("biyue-media-min");
            } else {
                $("body").addClass("biyue-small");
                $("body").addClass("biyue-media-min");
            }
        })
    },
    getMenuData: function () { //获取左侧菜单数据并加载；
        var _self = this;
        var biyue = _self.biyue;
        biyue.ajax({
            url: "/menu/queryAuthAllMenu",
            fun: function (data) {
                var dataV = data.value, $list = "";
                for (var i = 0, len = dataV.length; i < len; i++) {
                    var bool = !!dataV[i].childMenus;
                    var active = "",height=0;
                    if (i === 0) {
                        active = "active";
                        if(dataV[i].childMenus){
                            height = dataV[i].childMenus.length*34 //34为列表高度
                        }
                    }
                    //判断是否有下级
                    if (!bool) {
                        var url = ctx + dataV[i].url,
                            name = dataV[i].name,
                            id = dataV[i].id,
                            icon = dataV[i].icon;
                        if (url.indexOf("?") > 0) {
                            url = url + '&operationAuthority=' + dataV[i].operationAuthority + '&level=' + window.globalData.userType;
                        } else {
                            url = url + '?operationAuthority=' + dataV[i].operationAuthority + '&level=' + window.globalData.userType;
                        }
                        //如果是引入外部网站时
                        if(dataV[i].operationAuthority==="1"){
                            url = dataV[i].url;
                        }
                        $list += ' <li class="' + active + '">\n' +
                            '               <a biyue-id="' + id + '" biyue-link="' + url + '"><i class="' + icon + '"></i> <span>' + name + '</span></a>\n' +
                            '    </li>';
                    } else {
                        var name = dataV[i].name, icon = dataV[i].icon;
                        $list += ' <li class="' + active + '">\n' +
                            '                <a><i class="' + icon + '"></i> <span>' + name + '</span> <label class=" fa fa-angle-left"></label></a>\n' +
                            '                <ul class="biyue-down-list" style="height: '+height+'px;">\n';
                        for (var j = 0; j < dataV[i].childMenus.length; j++) {
                            var dataC = dataV[i].childMenus[j],
                                url = ctx + dataC.url,
                                id = dataC.id;
                            if (url.indexOf("?") > 0) {
                                url = url + '&operationAuthority=' + dataC.operationAuthority + '&level=' + window.globalData.userType;
                            } else {
                                url = url + '?operationAuthority=' + dataC.operationAuthority + '&level=' + window.globalData.userType;
                            }
                            //如果是引入外部网站时
                            if(dataV[i].operationAuthority==="1"){
                                url = dataV[i].url;
                            }
                            if (j === 0) {
                                $list += '<li class="fa fa-id-card-o ' + active + '" biyue-id="' + id + '" biyue-link="' + url + '">' + dataC.name + '</li>';
                            } else {
                                $list += '<li class="fa fa-id-card-o" biyue-id="' + id + '" biyue-link="' + url + '">' + dataC.name + '</li>';
                            }
                        }
                        $list += '                </ul>\n' +
                            '            </li>';
                    }
                }
                $(".biyue-left-menu").html($list);
                var link = $(".biyue-left-menu>.active>a").attr("biyue-link");
                var name = $(".biyue-left-menu>li.active>a>span").text();
                var tab_id = $(".biyue-left-menu>.active>a").attr("biyue-id");
                if (!link) {
                    link = $(".biyue-down-list>.active").attr("biyue-link");
                    name = $(".biyue-down-list>.active").text();
                    tab_id = $(".biyue-down-list>.active").attr("biyue-id");
                }
                _self.tab().tabAdd({
                    url: link,
                    name: name,
                    id: tab_id
                });
                Waves.attach(".biyue-left-menu>li>a", ['waves-button']);
            }
        })

    },
    setPassword: function () {
        //修改密码
        $(".set-password").on('click', function () {
            var iframeStr = '<iframe src="about:blank" style="border: currentColor; border-image: none; left: 0px; width: 100%; height: 100%; position: absolute; z-index: -1;"></iframe>'
            //捕获页
            layer.open({
                type: 1,
                title: '修改密码',
                id: 'password',
                area: ['400px', '250px'],
                btn: ['提交'],
                content: $('#amendPasWin'),
                success: function () {
                    $(".error-text").html('');
                    $(".layui-layer-page .layui-layer-title").append(iframeStr);
                    $(".layui-layer-page  .layui-layer-setwin").append(iframeStr);
                },
                yes: function () {
                    var pasValOne = $("#pasOne").val()
                        , pasValTwo = $("#pasTwo").val();

                    $(".error-text").html('');
                    if (pasValOne == "" || pasValTwo == "") {
                        $(".error-text").html('密码不能为空！');
                        return;
                    }
                    if (pasValOne == pasValTwo) {
                        $.ajax({
                            type: "post",
                            url: ctx + "/user/updatePwd",
                            data: {
//                            userPassword:md5(pasValOne)
                                userPassword: pasValOne
                            },
                            dataType: "json",
                            success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                                if (data.status == 1) {
                                    layer.closeAll();
                                    top.location.href = 'logout';
                                } else {
                                    layer.alert(data.msg);
                                }
                            },
                            error: function (data, status, e) {   //提交失败自动执行的处理函数
                                layer.alert(String(e));
                            }
                        });
                    } else {
                        $(".error-text").html('2次密码输入不相同，请重新输入！');
                        return;
                    }
                },
                cancel: function () {
                    $("#amendPasWin").hide();
                }
            });
        })
    },
    loading: function () {
        //监听加载状态改变
        document.onreadystatechange = completeLoading;

        //加载状态为complete时移除loading效果
        function completeLoading() {
            if (document.readyState == "complete") {
                $(".preloader").fadeOut();
            }
        }
    },
    tab: function () {
        var element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
        //触发事件
        var active = {
            //新增一个Tab项
            tabAdd: function (obj) {
                var len = $('[lay-id="' + obj.id + '"]').length;
                if (len > 0) {
                    element.tabChange('demo', obj.id);
                } else {
                    element.tabAdd('demo', {
                        title: obj.name
                        ,
                        content: '<iframe class="mainframe" name="mainframe" src="' + obj.url + '" allowfullscreen="true" scrolling="no" width="100%" height="100%" frameborder="0"></iframe>\n'
                        ,
                        id: obj.id
                    });
                    element.tabChange('demo', obj.id);
                }

            }
            //切换一个Tab项
            , tabChange: function (demo) {
                element.tabChange('demo', demo);
            }
            , del: function (demo) {
                element.tabDelete('demo', demo);
            }
        };
        return active;
    }

};