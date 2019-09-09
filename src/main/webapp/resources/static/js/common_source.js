$(window).resize(function() {
    resizefun();
    menuMin();
});

$(function() {
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
    $(".dropdown-hover").mouseover(function() {
        $(this).addClass('open');
    }).mouseout(function() {
        $(this).removeClass('open');
    });

   
    
    
    /*收缩*/
    $("body").on("click", ".pngfix",
    function() {
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
    function() {    	
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
            function() {
                f.find("li.active").removeClass("active"),
                h.addClass("active")
            })
        }
    });
};

function resizefun() {
    pingHeight = document.documentElement.clientHeight;
    pingWidth = document.documentElement.clientWidth;
    $(".sidebar-menu").css({
        "height": pingHeight - 106
    });
};
function initMenu() {
	$.ajax({
        type: "post",
        url: ctx + "/menu/queryMenu",
        dataType: "json",
        success: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
        	if(data.status == 0){
                alert(data.msg);
        		return;
            }
        	var menus = data.value;
        	var menuData = new Array();
        	for (var i = 0; i < menus.length; i++) {
        		var url = menus[i].url;
        		var menu = new Object();
        		menu['name'] = menus[i].id;
        		menu['link'] = url ? url : '';
        		menu['title'] = menus[i].name;
        		menu['icon'] = menus[i].icon;
        		menuData.push(menu);
        	}
        	createMenu(menuData);
        },
        error: function (data, status, e) {
            alert(e);
        }
    });
};
function createMenu(menuDatas){
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
    $(".more-menu").hover(function() {
        $(this).find(".more-box").stop(true).slideDown(200);
    },
    function() {
        $(this).find(".more-box").stop(true).slideUp(100);
    });
    /*选中下拉菜单  去掉一级菜单选中样式*/
    $(".main_menu").click(function() {
        $(".li-col").removeClass('active');
    });
}
/*菜单限制*/
function menuMin(){
    var width = $(window).width();
    var len = $(".li-col").length;
    var index = Math.floor((width - $('.logo').width() - $('.navbar-custom-menu').width())/124);
    if (index >= len) {
    	$(".li-col").show();
    	$(".more-menu").hide();
    	
    }else {
    	var idx = index - 2;
    	$(".li-col:lt("+(index - 1)+")").show();
    	$(".li-col:gt("+(index - 2)+")").hide();
    	$(".data-a:lt("+(index+1)+")").hide();
    	$(".data-a:gt("+(index-3)+")").show();
    	$(".more-menu").show();
    	
    	/*最少显示4个*/
    	if(index < 4 ){
    		$(".li-col:lt(3)").show();
        	$(".li-col:gt(2)").hide();
            $(".data-a:lt(2)").hide();
            $(".data-a:gt(1)").show();
            $(".more-menu").show();
        }
    }
};
function addClickmenu() {
    $("#left").hide();
    //点击父菜单切换左边的子菜单
    $("body").on("click", ".main_menu",
    function() {
    	//左侧菜单
        tree();
        var link = $(this).attr("link");
        var url = ctx + link;
        var id = $(this).attr("name");
        //所有节点不选中
        $(this).parent('.li-col').addClass("active").siblings().removeClass("active");
        if (link != "") { // 首页状态
            $("#mainframe").attr("src", url);
            $("#left").hide();
            $(".footer").show();
            $(".Hui-article-box").addClass("Hui-default");
        } else { // 其他页面
            $("#left").show();
            $(".footer").hide();
            $(".Hui-article-box").removeClass("Hui-default");
            $(".pngfix").removeClass("open");
            $("body").removeClass("big-page");

            //清空子菜单
            $(".sidebar-menu").empty();
            if(id==""){
                $("#left").hide();
                $("#mainframe").attr("src", "");
                return;
            }
            //点击父菜单执行ajax查询子菜单数据
            var url = ctx + "/menu/queryByParentId";
            var data = {
                "parentId": id
            };
            $.post(url, data,
            function(data) {
                if (data.status == 1) {
                    var value = data.value;
                    if (!value || value.length == 0) {
                    	return;
                    }
                    var lis = "";
                    for (var i = 0; i < value.length; i++) {
                    	var id = value[i].id;
                        var name = value[i].name;
                        var url = value[i].url;
                        url = url ? url : "";
                        var icon = value[i].icon;
                        lis += "<li class='treeview'>";
                        lis += "	<a href='javascript:void(-1)' link = '" + url + "'>";
                        lis += "		<i class='fa fa-address-card-o'></i>";
                        lis += "		<span>" + name + "</span>";
                        //lis += "		<span class='pull-right-container'><i class='fa fa-angle-down'></i></span>";
                        lis += "	</a>";
                        lis += "</li>";
                    }
                    $(".sidebar-menu").append(lis);
                    
                    var item = $(".sidebar-menu li");
                    $(".treeview").click(function(){
                    	$(this).addClass("active").siblings().removeClass("active");
                    })
                    /*
                    var name = value[0].name;
                    var lis = "";
                    lis += "<li class='treeview active'>";
                    lis += "	<a>";
                    lis += "		<i class='fa fa-address-card-o'></i>";
                    lis += "		<span>" + name + "</span>";
                    lis += "		<span class='pull-right-container'><i class='fa fa-angle-down'></i></span>";
                    lis += "	</a>";
                    lis += "	<ul class='treeview-menu'>";
                    for (var i = 1; i < value.length; i++) {
                        var id = value[i].id;
                        var name = value[i].name;
                        var url = ctx + value[i].url;
                        var icon = value[i].icon;
                        if (i == 1) {
                            lis += "<li class='active curr'>";
                        } else {
                            lis += "<li>";
                        }
                        lis += "<a href='javascript:void(-1)' class='leaf_menu' link = '" + url + "' name='" + id + "'><i class='fa fa-circle'></i>" + name + "</a></li>";
                    }
                    lis += "	</ul>";
                    lis += "</li>";

                    $(".sidebar-menu").append(lis);
                    */
                    //默认选中第一个菜单
                    var first_menu_url = $(".treeview  a").attr("link");
                    //alert(first_menu_url);
                    $("#mainframe").attr("src", ctx + first_menu_url);
                    $("li.treeview:first-child").addClass("active").siblings().removeClass("active");
                }
            })

        }
    });

    //点击子节点菜单切换iframe子页面
    $("body").on("click", ".leaf_menu",
    function() {
        var link = $(this).attr("link");
        $("#mainframe").attr("src", link);
        $(this).parent('li').addClass("active curr").siblings().removeClass("active curr");
    });
};