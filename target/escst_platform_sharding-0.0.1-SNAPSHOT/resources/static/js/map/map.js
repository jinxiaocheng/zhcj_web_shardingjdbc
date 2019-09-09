//创建地图函数
var map;
var ajaxBool=false; //得到图片生成，与摄像头生成顺序

function ininMap() {
    addInfo();
}

//滚动条美化
$("#barALLBox,.trends").slimScroll({
    position: 'right'
    , size: "5px"
    , height: '100%'
    , width: '100%'
    , color: '#20b8fd'
    , disableFadeOut: true
    , wheelStep:30
})
//为多个点添加窗口信息
function addInfo() {
    var data_info = my_ajax();
    var opts = {
        width: 450,     // 信息窗口宽度
        height: 290,     // 信息窗口高度
        enableMessage: true//设置允许信息窗发送短息
    };
    map = new BMap.Map("map");   //创建一个地图实例，HTML容器包含地图
    var point = new BMap.Point(data_info[0].lng ? data_info[0].lng : 114.28569, data_info[0].lat ? data_info[0].lat : 30.60738);
    map.centerAndZoom(point, 12);
    map.enableScrollWheelZoom();
    map.addControl(new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, offset: new BMap.Size(10, 50)})); //初始化地图控件
    map.addControl(new BMap.ScaleControl());
    map.addControl(new BMap.MapTypeControl());        //添加地图类型控件
    markInit(data_info);

    // 添加标注
    function addMarker(point, img_path) {
        if (img_path != null) {
            var myIcon = new BMap.Icon(img_path,
                new BMap.Size(31, 35), {
                    offset: new BMap.Size(15, 25)
                });
            var marker = new BMap.Marker(point, {icon: myIcon});
        } else {
            var marker = new BMap.Marker(point);
        }
        map.addOverlay(marker);
        return marker;
    }

    function markInit(data_info) {
        for (var i = 0; i < data_info.length; i++) {
            data_info[i].name = my_filter(data_info[i].name);
            data_info[i].areaName = my_filter(data_info[i].areaName);
            data_info[i].development = my_filter(data_info[i].development);
            data_info[i].building = my_filter(data_info[i].building);
            data_info[i].supervision = my_filter(data_info[i].supervision);
            data_info[i].projectManager = my_filter(data_info[i].projectManager);
            data_info[i].contractStartDate = my_filter(data_info[i].contractStartDate);
            data_info[i].contractEndDate = my_filter(data_info[i].contractEndDate);
            var $mes = '<ul id="marker">\n' +
                '        <li><label>工地名称:</label><span>' + data_info[i].name + '</span></li>\n' +
                '        <li><label>区域:</label><span>' + data_info[i].areaName + '</span></li>\n' +
                '        <li><label>建设单位:</label><span>' + data_info[i].development + '</span></li>\n' +
                '        <li><label>施工单位:</label><span>' + data_info[i].building + '</span></li>\n' +
                '        <li><label>监理单位:</label><span>' + data_info[i].supervision + '</span></li>\n' +
                '        <li><label>项目经理:</label><span>' + data_info[i].projectManager + '</span></li>\n' +
                '        <li><label>计划开工日期:</label><span>' + data_info[i].contractStartDate + '</span></li>\n' +
                '        <li><label>计划竣工日期:</label><span>' + data_info[i].contractEndDate + '</span></li>\n' +
                '    </ul>';
            $mes += '<a href="javascript:void(0);" onclick="tab(\'' + ctx + '/map/toIndexMin?constructionId=' + data_info[i].id + '&areaId='+data_info[i].areaId+'\')" link="' + ctx + '/map/toIndexMin?constructionId=' + data_info[i].id + '" class="tabMap">点击切换工地端首页</a><br>';
            if (data_info[i].bimId) {
                $mes += '<a href="' + ctx + '/map/bim?pid=' + data_info[i].bimId + '" target="view_window">点击进入bim模型展示页</a>';
            }
            data_info[i].iconPath = data_info[i].iconPath || null;
            var marker = addMarker(new BMap.Point(data_info[i].lng, data_info[i].lat), data_info[i].iconPath);  // 创建标注
            var content = $mes;
            addClickHandler(content, marker);

        }
    }

    function addClickHandler(content, marker) {
        marker.addEventListener("click", function (e) {
            openInfo(content, e)
        });
    }

    function openInfo(content, e) {
        var p = e.target;
        var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
        var infoWindow = new BMap.InfoWindow(content, opts);  // 创建信息窗口对象
        map.openInfoWindow(infoWindow, point); //开启信息窗口
    }

    // 定义一个控件类,即function
    function ZoomControl() {
        // 默认停靠位置和偏移量
        this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
        this.defaultOffset = new BMap.Size(10, 10);
    }

    // 通过JavaScript的prototype属性继承于BMap.Control
    ZoomControl.prototype = new BMap.Control();
    // 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
    ZoomControl.prototype.initialize = function (map) {
        // 创建一个DOM元素
        var div = document.createElement("div");
        var input = document.createElement("input");
        input.type = "text";
        // 设置样式
        input.placeholder = "搜索";
        input.style.cursor = "pointer";
        input.style.width = "250px";
        input.style.height = "30px";
        input.style.border = "1px solid gray";
        input.style.borderRadius = "4px";
        input.style.textIndent = "30px";
        //input.style.backgroundColor = "transparent";
        input.style.opacity = "0.5";
        input.className = 'inputName';

        var ul = document.createElement("ul");
        ul.style.width = "250px";
        ul.style.border = "1px solid gray";
        ul.style.backgroundColor = "#FFFFFF";
        ul.style.opacity = "0.8";
        //给搜索框添加事件
        input.onkeyup = function () {
            var inputValue = $(this).val();
            var dataJson = [];

            while (ul.hasChildNodes()) {
                ul.removeChild(ul.lastChild);
            }
            //模糊查询工地信息
            if (inputValue == "") return;
            for (var j in data_info) {
                var patt1 = new RegExp(inputValue);
                var result = patt1.test(data_info[j].name);
                if (result) {
                    dataJson.push(data_info[j]);
                }
            }
            for (var i = 0; i < dataJson.length; i++) {
                var li = document.createElement("li");
                li.className = 'consList';
                li.style.width = "250px";
                li.style.height = "35px";
                var latlng = dataJson[i].lng + "," + dataJson[i].lat;
         /*       var inputHidden = document.createElement("input");
                inputHidden.type = "hidden";
                inputHidden.value = i;
                li.appendChild(inputHidden);*/
                li.appendChild(document.createTextNode(dataJson[i].name));
                ul.appendChild(li);
            }
            var inputHidden = document.createElement("input");
            inputHidden.type = "hidden";
            div.appendChild(ul);
            div.appendChild(inputHidden);
        };
        // 绑定事件,点击一次放大两级
        //div.onclick = function(e){
        //map.setZoom(map.getZoom() + 2);
        //};
        var button = document.createElement("i");
        button.className += ' fa fa-search searchMap';
        button.style.position = "absolute";
        button.style.left = "8px";
        button.style.top = "4px";
        button.style.color = "#7b7b7b";
        button.style.fontSize = "20px";
        // 添加DOM元素到地图中
        div.appendChild(input);
        div.appendChild(button);

        $("#map").on("click", ".searchMap", function () {
            map.clearOverlays(); //清除地图上所有覆盖物
            var inputValue = $("input[type='text']").val();
            var dataJson = [];
            //模糊查询工地信息
            if (inputValue != "" && inputValue != null) {
                for (var j in data_info) {
                    var patt1 = new RegExp(inputValue);
                    var result = patt1.test(data_info[j].name);
                    if (result) {
                        dataJson.push(data_info[j]);
                    }
                }
                markInit(dataJson);
                map.centerAndZoom(new BMap.Point(dataJson[0].lng, dataJson[0].lat), 16);

                while (ul.hasChildNodes()) {
                    ul.removeChild(ul.lastChild);
                }
                if(div.hasChildNodes(ul)){
                   // div.removeChild(ul);
                }
            } else {
                map.centerAndZoom(point, 12);
                markInit(data_info)
            }

        });

        $("#map").on("click",'.consList',function () {
            $(".inputName").val($(this).html());
            $("#map .searchMap").click();
        });

        map.getContainer().appendChild(div);
        // 将DOM元素返回
        return div;
    };
    // 创建控件
    var myZoomCtrl = new ZoomControl();
    // 添加到地图当中
    map.addControl(myZoomCtrl);
    //点击搜索查询工地
    $('#searchMap button').click(function () {
        setPlace($("#searchMap input").val());
    })

}

function my_ajax() {
    var index = layer.load(); //换了种风格
    var my_data = "";
    $.ajax({
        type: "post",
        url: ctx + '/map/query',
        async: false,
        success: function (data) {
            layer.close(index);
            if (data.status == 1) {
                if (data.value) {
                    my_data = data.value;
                    for (var i in my_data) {
                        for (var j in  my_data[i]) {
                            if (my_data[i][j] == null || my_data[i][j] == undefined) {
                                my_data[i][j] = "";
                            }
                        }
                    }

                }
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.close(index);
            layer.alert(String(e));
        }
    });
    return my_data;
}

function my_filter(t) {
    if (t == null || t == undefined) {
        return ""
    } else {
        return t
    }
}

//点击切换为工地端首页
function tab(link) {
    top.indexUrl=window.location.href;
    $(window.parent.document).find('[biyue-id="29d99c0df71f11e6a0d9002590f074f1"]').attr('biyue-link',ctx?link.split(ctx)[1]:link);
    window.location.href=link;
}
// function tab(link) {
//     top.indexUrl=ctx+$(window.parent.document).find('.li-col.active a').attr('link');
//     $(window.parent.document).find('.li-col.active a').attr('link',ctx?link.split(ctx)[1]:link);
//     window.parent.document.getElementById("mainframe").src = link;
// }

//回车搜索
function searchvalue() {
    $(window).keydown(function () {//给输入框绑定按键事件
        if (event.keyCode == "13") {//判断如果按下的是回车键则执行下面的代码
            $("#map .searchMap").click();
        }
    })
}

//设置右边时间部分
function setDate() {
    var date = new Date()
        , year = date.getFullYear()
        , month = date.getMonth() + 1
        , day = date.getDate()
        , hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours()
        , minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()
        , seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
    month = month < 10 ? "0" + month : month;
    day = day < 10 ? "0" + day : day
    var setDate = {
        days: year + '-' + month + '-' + day,
        seconds: hour + ':' + minute + ':' + seconds
    }
    var str = "星期";

    switch (date.getDay()) {
        case 0:
            str = str + "日";
            break;
        case 1:
            str = str + "一";
            break;
        case 2:
            str = str + "二";
            break;
        case 3:
            str = str + "三";
            break;
        case 4:
            str = str + "四";
            break;
        case 5:
            str = str + "五";
            break;
        case 6:
            str = str + "六";
            break;
    }

    var days = setDate.days
        , getSeconds = setDate.seconds;
    $(".week").html(str);
    $('.time p').html(getSeconds);
    return setDate
};

//工程信息
function info() {
    var index = layer.load(); //换了种风格
    $.ajax({
        type: "post",
        url: ctx + '/construction/queryConstructionInfoByUserId',
        success: function (data) {
            layer.close(index);
            if (data.status == 1) {
                if (data.value) {
                    var dataValue = data.value;
                    if (dataValue.name) $("#infoOfWorks .m1 span").html(dataValue.name);
                    if (dataValue.development) $("#infoOfWorks .m2 span").html(dataValue.development);
                    if (dataValue.building) $("#infoOfWorks .m3 span").html(dataValue.building);
                    if (dataValue.supervision) $("#infoOfWorks .m4 span").html(dataValue.supervision);
                    personNum(dataValue.id);
                }
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.close(index);
            layer.alert(String(e));
        }
    });
}

//人员在线情况
function personNum(constructionId) {
    var index = layer.load(); //换了种风格
    $.ajax({
        type: "post",
        url: ctx + '/home/getHomeBaseInfo',
        data: {
            constructionId: constructionId
        },
        success: function (data) {
            layer.close(index);
            if (data.status == 1) {
                if (data.value) {
                    var dataValue = data.value;
                    $(".temperature").html('<label  class="w' + dataValue.type + ' w"></label>' + (dataValue.weather||"--") + '&nbsp;' + (dataValue.temperature||"-") + '℃');
                    $(".nowNum span").html(dataValue.locale);
                    $(".todayNum span").html(dataValue.totalNum);
                    $(".modelList").eq(0).find('.m1 span').html(dataValue.camera_online);
                    $(".modelList").eq(0).find('.m2 span').html(dataValue.carera_offline);
                    $(".modelList").eq(1).find('.m1 span').html(dataValue.towerCrane_online);
                    $(".modelList").eq(1).find('.m2 span').html(dataValue.towerCrane_offline);
                    $(".modelList").eq(2).find('.m1 span').html(dataValue.lift_online);
                    $(".modelList").eq(2).find('.m2 span').html(dataValue.lift_offline);
                    $(".modelList").eq(3).find('.m1 span').html(dataValue.environment_online);
                    $(".modelList").eq(3).find('.m2 span').html(dataValue.environment_offline);
                    $(".modelList").eq(4).find('.m1 span').html(dataValue.qualityCorrective);
                    $(".modelList").eq(4).find('.m2 span').html(dataValue.qualityChecked);
                    $(".modelList").eq(5).find('.m1 span').html(dataValue.safeCorrective);
                    $(".modelList").eq(5).find('.m2 span').html(dataValue.safeChecked);

                }
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.close(index);
            layer.alert(String(e));
        }
    });
}


// 如果是ie8的话重新设置高度
function setMapHeight() {
    if (navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE8.0") {
        var winHeight = $(window).height();
        $("#map").css("height", winHeight + "px");
        $(window).resize(function () {
            var winHeight = $(window).height();
            $("#map").css("height", winHeight + "px");

        })
    }
}

//点击展开
function clickShow() {
    $("body").on('click', '.clickShow.close', function () {
        $(this).parents('.clickShowP').addClass('csopen');
        $(this).removeClass("close").addClass("open");
    })
    $("body").on('click', '.clickShow.open', function () {
        $(this).parents('.clickShowP').addClass('csclose');
        $(this).parents('.clickShowP').removeClass('csopen');
        $(this).removeClass("open").addClass("close");
        setTimeout(function () {
            $(".clickShowP.csclose").removeClass('csclose');
        }, 500);
    })
}

//左边模块显示问题（企业端）
function leftBoxType() {
    var i=0;
    return function (num) {
        if(num==1){  //只显示各项工作进度
            i++;
            $("#trendsOfWorks").hide();
            $("#progressOfWorks h2 i").hide();
            $("#progressOfWorks").css({
                bottom: 0,
                overflow: 'auto',
                height: '100%'
            })
        }else if(num==0){  //只显示工作动态
            i++;
            $("#progressOfWorks").hide();
            $("#trendsOfWorks h2 i").hide();
            $("#trendsOfWorks").css({
                top: 0,
                overflow: 'auto',
                height: '100%'
            })
        }
        if(i==2){  //全部隐藏
            $("#leftBox").hide();
            $("#mapBox").css({
                left:0
            })
        }
    }
}

var leftType=leftBoxType();

//工程进度(企业端)
function progressAll() {
    var index = layer.load(); //换了种风格
    $.ajax({
        type: "post",
        url: ctx + '/construction/getConstructionSchedule',
        success: function (data) {
            layer.close(index);
            if (data.status == 1) {
                if (data.value) {
                    var $list = "";

                    if(data.value.length==0){
                        leftType(0);
                        return;
                    }
                    for (var i in data.value) {
                        $list += '<li>\n' +
                            '                <h3 class="barTitle"><i></i>' + data.value[i].name + '</h3>\n' +
                            '                <p class="remainder">剩余' + data.value[i].daysRemain + '天</p>\n' +
                            '                <span class="barNum">' + data.value[i].percent + '</span>\n' +
                            '                <u><i style="width: ' + data.value[i].percent + '"></i></u>\n' +
                            '                <p class="barTime">\n' +
                            '                    <span class="barStartTime">' + data.value[i].startDate + '</span>\n' +
                            '                    <span class="barEndTime">' + data.value[i].endDate + '</span>\n' +
                            '                </p>\n' +
                            '            </li>';
                    }
                    $("#barALLBox").html($list);
                }
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.close(index);
            layer.alert(String(e));
        }
    });
}

//工程进度(工地端)
function progressOfWorks() {
    var index = layer.load(); //换了种风格
    $.ajax({
        type: "post",
        url: ctx + '/construction/getConstructionSchedule',
        success: function (data) {
            layer.close(index);
            if (data.status == 1) {
                if (data.value) {
                    var dataValue = data.value[0]
                    var startTime = dataValue.startDate.split('-');
                    var endTime = dataValue.endDate.split('-');
                    $(".progressBar p").eq(0).html(dataValue.percent);
                    $(".progressBar .bar i").css('width', dataValue.percent);
                    $(".endTime span").html(endTime[1] + ' - ' + endTime[2]+ '<br>' + endTime[0] );
                    $(".startTime span").html(startTime[1] + ' - ' + startTime[2]+ '<br>' + startTime[0] );
                    $(".surplusTime span").html(dataValue.daysRemain);
                }
            } else {
                layer.alert(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.alert(String(e));
        }
    });
}

//工作动态（企业端）
function trendsOfWorksAll(type, num) {
    var index = layer.load(); //换了种风格
    $.ajax({
        type: "post",
        url: ctx + '/workTrend/queryList?type=' + type + "&number=" + num,
        success: function (data) {
            layer.close(index);
            if (data.status == 1) {
                var $list = "";
                if(data.value.length==0){
                    leftType(1);
                    return;
                }
                if (data.value) {
                    for (var i in data.value) {
                        $list += '<div class="trendsBox">\n' +
                            '                <p>' + data.value[i].companyContent + '</p>' +
                            '                <span>' + data.value[i].businessTime + '</span>\n' +
                            '            </div>';
                    }
                    $('.trends').append($list);
                    if (type == 1) {
                        num = data.value.length;
                    } else {
                        num = num + data.value.length;
                    }
                }
            } else {
                console.log(data.msg);
            }
           // trendsOfWorksAll(2, num);
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.close(index);
            console.log(String(e));
            //trendsOfWorksAll(2, num);
        }
    });
}

//工作动态（工地端）
function trendsOfWorks(type, num) {
    var index = layer.load(); //换了种风格
    $.ajax({
        type: "post",
        url: ctx + '/workTrend/queryList?type=' + type + "&number=" + num,
        success: function (data) {
            layer.close(index);
            if (data.status == 1) {
                var $list = "";
                if (data.value) {
                    for (var i in data.value) {
                        $list += '<div class="trendsBox">\n' +
                            '                <p>' + data.value[i].constructionContent + '</p>' +
                            '                <span>' + data.value[i].businessTime + '</span>\n' +
                            '            </div>';
                    }
                    $('.trends').append($list);
                    if (type == 1) {
                        num = data.value.length;
                    } else {
                        num = num + data.value.length;
                    }
                }
            } else {
                console.log(data.msg);
            }
          //  trendsOfWorks(2, num);
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.close(index);
            console.log(String(e));
           // trendsOfWorks(2, num);
        }
    });
}

//工地端获取图片
function getImg() {
    var index = layer.load(); //换了种风格
    $.ajax({
        type: "post",
        url: ctx + '/map/foorplan?constructionId=' + constructionId ,
        success: function (data) {
            layer.close(index);
            if (data.status == 1) {
                var $img='';
                if(data.value){
                    $img='<img src="'+data.value+'" alt="">';
                }else{
                    $img='<img src="'+ctx+'/resources/static/images/map/construction.jpg" alt="">';
                }

                $("#imgBox img").remove();
                $(".imgBox img").remove();
                $("#imgBox").append($img);
                $(".imgBox").append($img);
                $($img).load(function () {
                    if(ajaxBool){
                        imgDrag("#imgBox","#conImg");
                        imgScroll("#imgBox img","#conImg");
                    }
                    ajaxBool=true;
                })
            } else {
                console.log(data.msg);
            }
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.close(index);
            console.log(String(e));
        }
    });
}

//中间平面图拖动事件（工地端）
function imgDrag(imgB,imgP) {
    //创建小方块的jquery对象
    var $box = $(imgB);
    var $boxP = $(imgP);
    //创建小方块的鼠标点按下事件
    $box.mousedown(function (event) {
        stopDefault( event );//阻止浏览器默认行为
        //获取鼠标按下的时候左侧偏移量和上侧偏移量
        var old_left = event.pageX;//左侧偏移量
        var old_top = event.pageY;//竖直偏移量

        //获取鼠标的位置
        var old_position_left = $(this).position().left;
        var old_position_top = $(this).position().top;

        //鼠标移动
        $(document).on('mousemove',function (event) {
            var new_left = event.pageX;//新的鼠标左侧偏移量
            var new_top = event.pageY;//新的鼠标竖直方向上的偏移量

            //计算发生改变的偏移量是多少
            var chang_x = new_left - old_left;
            var change_y = new_top - old_top;

            //计算出现在的位置是多少

            var new_position_left = old_position_left + chang_x;
            var new_position_top = old_position_top + change_y;

            //如果向下的偏移量大于文档对象的高度减去自身的高度，就让它等于这个高度
            if(new_position_top<$boxP.height()-$box.height()){
                new_position_top=$boxP.height()-$box.height();
            }
            //右限制
            if(new_position_left<$boxP.width()-$box.width()){
                new_position_left=$boxP.width()-$box.width();
            }
            if(new_position_left>0){//左边的偏移量小于0的时候设置 左边的位置为0
                new_position_left=0;
            }
            //加上边界限制
            if(new_position_top>0){//当上边的偏移量小于0的时候，就是上边的临界点，就让新的位置为0
                new_position_top=0;
            }

            $box.css({
                left: new_position_left + 'px',
                top: new_position_top + 'px'
            })
        });
        $box.on("mouseout mouseup",function(){
            $(document).off("mousemove");
        })
    });
}

//中间图片滚动放大事件
function imgScroll(img,imgP) {
    //创建小方块的jquery对象
    var $img = $(img);
    var $boxP = $(imgP);
    var i=1; //放大倍数
    var minI=0;
    var boxH=parseFloat($img.height());
    var boxW=parseFloat($img.width());
    var boxPH=parseFloat($boxP.height());
    var boxPW=parseFloat($boxP.width());
    var spanLT=[];
    $boxP.find(".cameraImg").each(function (index) {
        spanLT[index]={
            x:parseFloat($(this).css("left")),
            y:parseFloat($(this).css("top"))
        }
    })

    i=parseFloat(boxPW/boxW);
    minI=i*2/3;
    constrol(i);

    $boxP.on("mousewheel DOMMouseScroll", function (e) {
        stopDefault( e );
        var delta = (e.originalEvent.wheelDelta && (e.originalEvent.wheelDelta > 0 ? 1 : -1)) ||  // chrome & ie
            (e.originalEvent.detail && (e.originalEvent.detail > 0 ? -1 : 1));              // firefox
        if (delta > 0) {  // 向上滚
            i=i+0.1;
        } else if (delta < 0) {   // 向下滚
            i=i-0.1;
            if(i<minI){i=minI}
        }
        constrol(i);
    });
    function constrol(i) {
        $img.width(boxW*i+'px');
        $img.height(boxH*i+'px');
        for(var j in spanLT){
            $boxP.find(".cameraImg").eq(j).css({
                left:spanLT[j].x*i+'px',
                top:spanLT[j].y*i+'px'
            })
        }
    }
}

//点击出现工地人员轨迹页面
function personTrackBox() {
    $(".cameraBox").click(function (e) {
        stopBubble(e);
        var equipmentId=$(this).attr('data-id');
        jqueryPerson(equipmentId);
        layer.open({
            type: 1,
            title: false,
            closeBtn: 2,
            shadeClose: true,
            skin: 'yourclass',
            area: ['80%', '80%'],
            content: $('.personTrackBox'),
            success:function () {
                imgDrag(".personTrackImg .imgBox",".personTrackImg");
                imgScroll(".personTrackImg .imgBox img",".personTrackImg");
            }
        });
    })

    //点击人员得到该人员的轨迹
    $(".personList").on('click','li',function () {
        var guid=$(this).attr('data-id');
        $(this).addClass('ac').siblings().removeClass('ac');
        getPersonTrack(guid);
    })
}

//(工地平面图)查询摄像头拍摄的人员
function jqueryPerson(equipmentId) {
    var index = layer.load(); //换了种风格
    $.ajax({
        type: "post",
        url: ctx + '/faceRecognition/listFace',
        data:{
            constructionId: constructionId,
            equipmentId:equipmentId
        },
        success: function (data) {
            layer.close(index);
            if (data.status == 1) {
                var $list=''
                if (data.value) {
                    var dataV=data.value;
                    $(".personList h1 span").html(dataV.length+'人');
                    for(var i in dataV){
                        if(i==0){getPersonTrack(dataV[i].guid)}
                        var imgUrl=ctx+"/resources/static/images/map/personImg.png";
                        if(dataV[i].headFilePath) {imgUrl=dataV[i].headFilePath}
                        $list+='<li data-id="'+dataV[i].guid+'">\n' +
                            '                <u><img src="'+imgUrl+' " alt=""></u>\n' +
                            '                <h5>'+dataV[i].name+'</h5>\n' +
                            '                <p>'+dataV[i].timeStr+'</p>\n' +
                            '                <i class="fa fa-chevron-right"></i>\n' +
                            '            </li>'
                    }
                    $(".personList ul").html($list);
                    $(".personList ul li").eq(0).addClass('ac');
                }
            } else {
                console.log(data.msg);
            }
            //  trendsOfWorks(2, num);
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.close(index);
            console.log(String(e));
            // trendsOfWorks(2, num);
        }
    });
}

//(工地平面图)点击人员，查询人员轨迹
function getPersonTrack(guid) {
    var index = layer.load(); //换了种风格
    $('.personTrackImg .cameraImg u').html("");
    $('.personTrackImg .cameraImg u').css({width:'0px'})
    $.ajax({
        type: "post",
        url: ctx + '/faceRecognition/listFaceCount',
        data:{
            constructionId: constructionId,
            guid:guid
        },
        success: function (data) {
            layer.close(index);
            if (data.status == 1) {
                if (data.value) {
                    var dataV=data.value;
                    for(var i in dataV){
                        var id=dataV[i].equipmentId;
                        var trackNum=parseFloat(i)+1;
                        $('.personTrackImg .cameraImg[data-id="'+id+'"] u').append('<i class="trackNum">'+trackNum+'</i>');
                        var w=parseFloat($('.personTrackImg .cameraImg[data-id="'+id+'"] u').width())+37;
                        $('.personTrackImg .cameraImg[data-id="'+id+'"] u').css({
                            width:w+'px',
                            left:0 // -w/2+10+'px'(居中)
                        })
                    }
                }
            } else {
                console.log(data.msg);
            }
            //  trendsOfWorks(2, num);
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.close(index);
            console.log(String(e));
            // trendsOfWorks(2, num);
        }
    });
}

//(工地平面图)查询摄像头信息
function cameraInfo() {
    var index = layer.load(); //换了种风格
    //获取摄像头数据
    $.ajax({
        type: "post",
        url: ctx + '/faceRecognition/listCamera',
        data:{
          constructionId: constructionId
        },
        success: function (data) {
            layer.close(index);
            if (data.status == 1) {
                var $span=''
                if (data.value) {
                    $("#imgBox span").remove();
                    $(".imgBox span").remove();
                    var dataV=data.value;
                    for(var i in dataV){
                        $span='<span class="cameraImg cImg_'+i+'" data-id="'+dataV[i].equipmentId+'" title="'+dataV[i].camName+'" style="left:'+dataV[i].x+'px;top: '+dataV[i].y+'px;"><u></u></span>';
                        $("#imgBox").append($span);
                        $(".imgBox").append($span);
                    }
                }
            } else {
                console.log(data.msg);
            }
            if(ajaxBool){
                imgDrag("#imgBox","#conImg");
                imgScroll("#imgBox img","#conImg");
            }
            ajaxBool=true;
        },
        error: function (data, status, e) {   //提交失败自动执行的处理函数
            layer.close(index);
            console.log(String(e));
        }
    });

    //点击摄像头出现该区域下的总人数
    $('#imgBox').on('click','.cameraImg',function (e) {
        stopBubble(e);
        var index = layer.load(); //换了种风格
        var equipmentId=$(this).attr('data-id');
        //获取摄像头数据
        $.ajax({
            type: "post",
            url: ctx + '/faceRecognition/getCount',
            data:{
                constructionId: constructionId,
                equipmentId:equipmentId
            },
            success: function (data) {
                layer.close(index);
                if (data.status == 1) {
                    if (data.value) {
                        var dataV=data.value;
                        if(dataV){
                            $(".cameraBox h5").html(dataV.camName||"---");
                            $(".cameraBox p span").html(dataV.count||"-");
                            $(".cameraBox").attr('data-id',dataV.equipmentId);
                        }

                        $(".cameraBox").show();
                        var coordinates= getMousePos(e),
                            cH=parseFloat($(".cameraBox").height()),
                            cW=parseFloat($(".cameraBox").width()),
                            cX=coordinates.x-cW-20,
                            cY=coordinates.y-cH/2;

                        $(".cameraBox").css({
                            left:cX+'px',
                            top:cY+'px'
                        })
                    }
                } else {
                    console.log(data.msg);
                }
            },
            error: function (data, status, e) {   //提交失败自动执行的处理函数
                layer.close(index);
                console.log(String(e));
            }
        });

    })
    $(window).on('click',function () {
        $(".cameraBox").hide();
        $(".cameraBox h5").html("---");
        $(".cameraBox p span").html("-");
        $(".cameraBox").attr('data-id',"");
    })
}

//阻止事件冒泡,使成为捕获型事件触发机制.
function stopBubble(e) {
//如果提供了事件对象，则这是一个非IE浏览器
    if ( e && e.stopPropagation )
    //因此它支持W3C的stopPropagation()方法
        e.stopPropagation();
    else
    //否则，我们需要使用IE的方式来取消事件冒泡
        window.event.cancelBubble = true;
}

//阻止浏览器的默认行为
function stopDefault( e ) {
    //阻止默认浏览器动作(W3C)
    if ( e && e.preventDefault )
        e.preventDefault();
    //IE中阻止函数器默认动作的方式
    else
        window.event.returnValue = false;
    return false;
}

//鼠标当前位置
function getMousePos(event) {
    var e = event || window.event;
    var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;
    var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
    var x = e.pageX || e.clientX + scrollX;
    var y = e.pageY || e.clientY + scrollY;
    //alert('x: ' + x + '\ny: ' + y);
    return { 'x': x, 'y': y };
}