//全局变量申明
var childMenus = new Array();//左边小菜单数据

//自适应
$(window).resize(function () {
    resizefun();
    menuMin();
});

$(function () {
    // 初始化菜单
    initMenu();
    // 为菜单添加事件
    addClickmenu();

    /*内置滚动条*/
    $(".sidebar-menu").niceScroll({
        touchbehavior: false,
        cursoropacitymax: 0,
        //滚动条的透明度
        cursorwidth: 0,
        //滚动条的宽度
        horizrailenabled: false,
        zindex: '999999999',
        cursorborderradius: 0,
        //滚动轴的圆角
        autohidemode: true,
        //自动隐藏滚动条
    });

    resizefun();

    /*公用*/
    $(".dropdown-hover").mouseover(function () {
        $(this).addClass('open');
    }).mouseout(function () {
        $(this).removeClass('open');
    });

    /*收缩*/
    $("body").on("click", ".pngfix",
        function () {
            obj = $(this);
            displaynavbar(obj);
        });


});

/*左侧菜单-隐藏显示*/
function displaynavbar(obj) {
    if ($(obj).hasClass("open")) {
        $(obj).removeClass("open");
        $("body").removeClass("big-page");
    } else {
        $(obj).addClass("open");
        $("body").addClass("big-page");
    }
};

/*左侧菜单折叠收缩*/
function tree() {
    var b = this,
        c = 500;
    $(document).off("click", ".treeview a").on("click", ".treeview a",
        function () {
            var d = $(this),
                e = d.next();

            var link = d.attr('link');
            if (link) {
                $("#mainframe").attr("src", ctx + link);
            }

            $('.pull-right-container i').attr('class', 'fa fa-angle-right');
            if (e.is(".treeview-menu") && e.is(":visible")) e.slideUp(c);
            //e.parent("li").removeClass("active");
            else if (e.is(".treeview-menu") && !e.is(":visible")) {
                var f = d.parents("ul").first(),
                    g = f.find("ul:visible").slideUp(c);
                d.parents(".treeview").find('.pull-right-container i').attr('class', 'fa fa-angle-down');
                var h = d.parent("li");
                e.slideDown(c,
                    function () {
                        f.find("li.active").removeClass("active"),
                            h.addClass("active")
                    })
            }
        });
};

function resizefun() {
    var pingHeight = document.documentElement.clientHeight;
    var pingWidth = document.documentElement.clientWidth;
    $(".sidebar-menu").css({
        "height": pingHeight - 106
    });
};

//获取菜单列表数据，同时对首页进行加载
function initMenu() {
    $.ajax({
        type: "post",
        url: ctx + "/menu/queryAuthAllMenu",
        dataType: "json",
        success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
            if (data.status == 0) {
                alert(data.msg);
                return;
            }
            var menus = data.value;
            var menuData = new Array();
            var hasVideoPage = false; // 判断是否存在视频监控页，可通过返回url中的video进行判断
            var videoPageUrl = ''; // 视频监控页url路径

            for (var i = 0; i < menus.length; i++) {
                var url = menus[i].url;

                var menu = new Object();
                menu['name'] = menus[i].id;
                menu['link'] = url ? (url + '?operationAuthority=' + menus[i].operationAuthority + '&level=' + level) : '';
                menu['title'] = menus[i].name;
                menu['icon'] = menus[i].icon;

                childMenus[menu['name']] = menus[i].childMenus;
                menuData.push(menu);

                // 判断是否存在视频监控页,同时获取路径
                if (menu['link'].split('/')[1] === 'video') {
                    hasVideoPage = true;
                    videoPageUrl = menu['link'];
                }
            }
            createMenu(menuData);//创建菜单

            // 加载初始页面
            if (menus[0].childMenus) {
                $("#mainframe").attr("src", ctx + menus[0].childMenus[0].url);
                $("#left").show();
                $(".footer").hide();
                $(".Hui-article-box").removeClass("Hui-default");
                $(".pngfix").removeClass("open");
                $("body").removeClass("big-page");
                leftList(menus[0].childMenus);
                tree();
            } else {
                // 当window url中存在#video同时存在视频监控页面时，页面刷新直接跳转到视频监控页面
                if (hasVideoPage && window.location.hash === '#video') {
                    $("#mainframe").attr("src", ctx + videoPageUrl); // 跳转视频监控页面
                    $(".main_menu[link='" + videoPageUrl + "']").parent('.li-col').addClass("active")
                        .siblings().removeClass("active");
                } else {
                    $("#mainframe").attr("src", ctx + menuData[0].link); // 跳转首页
                }
            }

        },
        error: function (data, status, e) {
            alert(e);
        }
    });
};

//创建菜单
function createMenu(menuDatas) {
    var menuLis = "";
    for (var i = 0; i < menuDatas.length; i++) {
        if (i == 0) {
            menuLis += "<li class='li-col active'>";
        } else {
            menuLis += "<li class='li-col data-li'>";
        }
        menuLis += "<a class='main_menu' href='javascript:void(0);' link='" + menuDatas[i].link + "' name='" + menuDatas[i].name + "'>";
        menuLis += "<i class='" + menuDatas[i].icon + "'></i>";
        menuLis += "<span>" + menuDatas[i].title + "</span></a>";
    }
    menuLis += "<li class='more-menu'><a href='javascript:void(0);'><span>更多应用</span><i class='fa fa-angle-down'></i></a><div class='more-box'><iframe src='about:blank' style='left:0px;width: 100%;height: 100%;position: absolute;z-index: -1;border:none;'></iframe><div class='more-box-menu'><div class='group'><dl>";
    for (var j = 1; j < menuDatas.length; j++) {
        menuLis += "<dd><a class='data-a main_menu' href='javascript:void(0);' link='" + menuDatas[j].link + "' name='" + menuDatas[j].name + "'><i class='" + menuDatas[j].icon + "'></i><span>" + menuDatas[j].title + "</span></a>";
    }
    menuLis += "</dl></div></div></div></li>";
    $("#menu_head").append(menuLis);
    menuMin();

    /*菜单更多*/
    $(".more-menu").hover(function () {
            $(this).find(".more-box").stop(true).slideDown(200);
        },
        function () {
            $(this).find(".more-box").stop(true).slideUp(100);
        });
    /*选中下拉菜单  去掉一级菜单选中样式*/
    $(".main_menu").click(function () {
        $(".li-col").removeClass('active');
    });
}

/*菜单限制*/
function menuMin() {
    var width = $(window).width();
    var len = $(".li-col").length;
    var index = Math.floor((width - $('.logo').width() - $('.navbar-custom-menu').width()) / 124);
    if (index >= len) {
        $(".li-col").show();
        $(".more-menu").hide();

    } else {
        var idx = index - 2;
        $(".li-col:lt(" + (index - 1) + ")").show();
        $(".li-col:gt(" + (index - 2) + ")").hide();
        $(".data-a:lt(" + (index + 1) + ")").hide();
        $(".data-a:gt(" + (index - 3) + ")").show();
        $(".more-menu").show();

        /*最少显示4个*/
        if (index < 4) {
            $(".li-col:lt(3)").show();
            $(".li-col:gt(2)").hide();
            $(".data-a:lt(2)").hide();
            $(".data-a:gt(1)").show();
            $(".more-menu").show();
        }
    }
};

//点击父菜单执行子菜单数据
function leftList(data) {
    var value = data;
    if (!value || value.length == 0) {
        return;
    }
    var lis = "";
    for (var i = 0; i < value.length; i++) {
        var id = value[i].id;
        var name = value[i].name;
        var url = value[i].url;
        var result = /\?/.test(url);
        if (result) {
            url = url + '&operationAuthority=' + value[i].operationAuthority;
        } else {
            url = url + '?operationAuthority=' + value[i].operationAuthority;
        }
        url = url ? url : "";
        var icon = value[i].icon;
        lis += "<li class='treeview'>";
        lis += "	<a href='javascript:void(-1)' data-name='"+url.split('/')[1]+"' link = '" + url + "'>";
        lis += "		<i class='fa fa-address-card-o'></i>";
        lis += "		<span>" + name + "</span>";
        //lis += "		<span class='pull-right-container'><i class='fa fa-angle-down'></i></span>";
        lis += "	</a>";
        lis += "</li>";
    }
    $(".sidebar-menu").append(lis);

    var item = $(".sidebar-menu li");
    $(".treeview").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
    })

    //默认选中第一个菜单
    var first_menu_url = $(".treeview  a").attr("link");
    $("#mainframe").attr("src", ctx + first_menu_url);
    $("li.treeview:first-child").addClass("active").siblings().removeClass("active");

}

function addClickmenu() {
    $("#left").hide();
    //点击父菜单切换左边的子菜单
    $("body").on("click", ".main_menu",
        function () {
            //左侧菜单
            tree();
            var link = $(this).attr("link");
            var url = ctx + link;
            var id = $(this).attr("name");
            //所有节点不选中
            $(this).parent('.li-col').addClass("active").siblings().removeClass("active");

            //如何跳转到监控视频页时，加入特殊路由，刷新时通过路由，直接跳转到视频页
            if (link.split('/')[1] == 'video') {
                window.location.hash = 'video';
            } else {
                window.location.hash = '';//其他页面时，取消特殊路由
            }

            if (link != "") { // 首页状态
                $("#mainframe").attr("src", url);
                $("#left").hide();
                $(".footer").show();
                $(".Hui-article-box").addClass("Hui-default");

            } else { // 其他页面
                $("#mainframe").attr("src", ctx + '/loading/toView');
                $("#left").show();
                $(".footer").hide();
                $(".Hui-article-box").removeClass("Hui-default");
                $(".pngfix").removeClass("open");
                $("body").removeClass("big-page");
                //清空子菜单
                $(".sidebar-menu").empty();
                if (id == "") {
                    $("#left").hide();
                    $("#mainframe").attr("src", "");
                    return;
                }
                leftList(childMenus[id]);

            }
        });

    //点击子节点菜单切换iframe子页面
    $("body").on("click", ".leaf_menu",
        function () {
            var link = $(this).attr("link");
            $("#mainframe").attr("src", link);
            $(this).parent('li').addClass("active curr").siblings().removeClass("active curr");
        });

};

//隐藏用户设置下拉框
function hideSetBox() {
    var testiframe = document.getElementById("mainframe").contentWindow;
    testiframe.addEventListener("click", function (e) {
        if ($(".user-menu.open")) {
            $(".user-menu.open").removeClass("open");
        }
    }, false)
}

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

function photos(data,index) {
    layer.photos({
        photos: {
            "title": "集体照", //相册标题
            "id": "photos", //相册id
            "start":index, //初始显示的图片序号，默认0
            "data": data
        },
        closeBtn :2,
        shift:"",
        shade:[0.6, '#393D49'],
        maxHeight:200
    });
}
window.photos=photos;

//修改密码
$(".set-password").on('click',function(){
    var iframeStr='<iframe src="about:blank" style="border: currentColor; border-image: none; left: 0px; width: 100%; height: 100%; position: absolute; z-index: -1;"></iframe>'
    //捕获页
    layer.open({
        type: 1,
        title: '修改密码',
        id:'password',
        area:['400px','250px'],
        btn: ['提交'],
        content: $('#amendPasWin'),
        success:function () {
            $(".error-text").html('');
            $(".layui-layer-page .layui-layer-title").append(iframeStr);
            $(".layui-layer-page  .layui-layer-setwin").append(iframeStr);
        },
        yes: function(){
            var pasValOne=$("#pasOne").val()
                ,pasValTwo=$("#pasTwo").val();

            $(".error-text").html('');
            if(pasValOne==""||pasValTwo==""){
                $(".error-text").html('密码不能为空！');
                return;
            }
            if(pasValOne==pasValTwo){
                $.ajax({
                    type: "post",
                    url: ctx + "/user/updatePwd",
                    data: {
//                            userPassword:md5(pasValOne)
                        userPassword:pasValOne
                    },
                    dataType: "json",
                    success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                        if(data.status == 1){
                            layer.closeAll();
                            top.location.href='logout';
                        } else {
                            layer.alert(data.msg);
                        }
                    },
                    error: function (data, status, e) {   //提交失败自动执行的处理函数
                        layer.alert(String(e));
                    }
                });
            }else{
                $(".error-text").html('2次密码输入不相同，请重新输入！');
                return;
            }
        }
    });
})

//隐藏用户设置下拉框
$(".user-menu").click(function(){
    hideSetBox();
})

//消息推送
var mainPage = {
    ids:[],
    default:function () {
        var _self = this;
        //获取消息弹窗信息
        _self.getInfo();
        //呼出与隐藏消息弹窗
        $(".mes-content .close").click(function () {
            $(".mes-content").hide();
        });
        var bool = true;
        $(".message").click(function () {
            if(bool){
                bool=false;
                _self.sendInfo();
            }
            $(".mes-content").show();
        });
        //查看更多
        $('.panel-footer').on('click','a',function () {
            $('[name="9ccee4e39a404374aaada3d5a30ee3a0"]').click();
            setTimeout(function () {
                $('[data-name="message"]').click();
            },100);
            $(".mes-content").hide();
        });
        //滚动条美化
        biyue.scroll('.panel-content',{
            autohidemode:false
        })
        //弹出详情页
        $(".panel-content").on('click','.mes-list a',function () {
            var id = $(this).attr("data-id");
            var url ='/route/message/viewList?id='+id;
            var title = '查看详情';
            biyue.open({
                title:title,
                url:url
            })
        })

    },
    getInfo:function () {
        var _self = this;
        biyue.ajax({
            url:'/message/getNotice',
            fun:function (data) {
                if(data.value.menu){
                    $(".message").show();
                }
                var len = data.value.vos.length,vos = data.value.vos,$list="";
                if(len>0){
                    if(len>99){len=99}
                    $(".message span").show().html(len);
                }else{
                    return
                }
                for(var i in vos){
                    _self.ids.push(vos[i].id);
                    $list+=' <div class="mes-list">\n' +
                        '                            <label class="mes-name" title="'+vos[i].createBy+'">'+vos[i].createBy.substr(0,2)+'</label>\n' +
                        '                            <p>向您推送了,<a data-id="'+vos[i].id+'" href="javascript:void(0)">'+vos[i].content+'</a></p>\n' +
                        '                            <span class="mes-time">'+vos[i].createTime+'</span>\n' +
                        '                        </div>'
                }
                $(".panel-content").html($list);
            }
        })
    },
    sendInfo:function () {
        var _self = this;
        biyue.ajax({
            url:'/message/batchUpdate',
            data:{
                ids:_self.ids.join(',')
            },
            fun:function (data) {
                $(".message span").hide().html("")
            }
        })
    }
}