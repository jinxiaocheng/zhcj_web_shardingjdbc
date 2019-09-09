/**
 * @desc  需要jQuery layui 框架支持
 * @param null
 * @return
 * @author kz
 * @date 2018/5/2 15:36
 */
function biYue() {
    this.ctx = ctx;
}

biYue.prototype = {
    load: function () {
        var timestamp = (new Date()).getTime();
        $("body").append('<div class="com-loading" id="' + timestamp + '" style="position: fixed;left: 0;bottom: 0;top: 0;right: 0;z-index: 100000">\n' +
            '    <img src="' + ctx + '/resources/static/plugins/layer/skin/default/loading-0.gif"\n' +
            '         style="position: absolute;left: 50%;top: 50%;margin-left: -30px;margin-top: -12px;" alt="">\n' +
            '</div>');
        return timestamp
    }
    , closeLoad: function (e) {
        $("#" + e).remove();
    }
    , ajax: function (obj) {  //封装好的带加载的ajax.
        var _self = this;
        if (!obj) {
            obj = {}
        }
        if (!obj.load) { //为true则无加载动画
            var index = _self.load();
        }
        if (ctx) {
            obj.url = _self.ctx + obj.url
        }
        var ajaxObj = {
            url: obj.url || "",
            data: obj.data || "",
            type: obj.type || "post",
            dataType: 'json',
            complete: function () {
                _self.closeLoad(index);
            },
            success: function (data) {
                if (data.status == 1) {
                    if (obj.fun) {
                        obj.fun(data)
                    }
                } else {
                    layer.alert(data.msg);
                }
            },
            error: function (data, status, e) {
                console.error('请求错误:' + e);
            }
        };
        if (obj.contentType) {
            ajaxObj.contentType = obj.contentType
        }
        if (obj.processData !== undefined) {
            ajaxObj.processData = obj.processData
        }
        if (obj.contentType !== undefined) {
            ajaxObj.contentType = obj.contentType
        }
        if (obj.traditional) {
            ajaxObj.traditional = obj.traditional
        }
        if (obj.form && obj.ele) {
            var form = $(obj.ele);
            form.ajaxSubmit(ajaxObj);
        } else {
            $.ajax(ajaxObj);
        }
    }
    , close: function (url) {  //关闭弹窗后刷新上一窗口列表
        parent.jQuery("#gridTable").jqGrid('setGridParam', {url: url}).trigger("reloadGrid");
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
    , layui_close: function () {  //关闭弹窗后刷新上一窗口列表
        if (parent.searchIf) {
            parent.searchIf();
        }
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
    , urlSearch: function () {  //提取路径后所带参数
        var search = window.location.search.replace("?", "").split("&");
        var searchObj = {};
        for (var i in search) {
            searchObj[search[i].split("=")[0]] = search[i].split("=")[1];
        }
        return searchObj;
    }
    , stopDefault: function (e) {  //阻止浏览器的默认行为
        if (e && e.preventDefault)
            e.preventDefault();
        //IE中阻止函数器默认动作的方式
        else
            window.event.returnValue = false;
        return false;
    }
    , stopBubble: function (e) {  //阻止事件冒泡,使成为捕获型事件触发机制.

        //如果提供了事件对象，则这是一个非IE浏览器
        if (e && e.stopPropagation)
        //因此它支持W3C的stopPropagation()方法
            e.stopPropagation();
        else
        //否则，我们需要使用IE的方式来取消事件冒泡
            window.event.cancelBubble = true;
    }
    , laypage: function (obj) {  //layui 分页
        if (layui) {
            var laypage = layui.laypage;
            laypage.render({
                elem: obj.elem || 'laypage'
                , curr: obj.curr || 1
                , limit: obj.limit || 10
                , count: obj.num || 0
                , theme: obj.color || '#20b8fd'
                , layout: ['count', 'prev', 'page', 'next', 'skip']
                , jump: function (data, first) {
                    if (obj.fun) {
                        obj.fun(data, first)
                    }
                }
            });
        }
    }
    , jqTable: function (obj) { //列表
        var colNames = [],
            _self = this,
            colModel = [],
            index = "",
            tableJson = obj.tableJson || [];
        var rowNum = Math.floor(($(".ui-report").height() - $('.response-head').height() - $('.gridPage').height() - 100) / 30);
        //表格条件进行转换
        ;(function () {
            for (var i = 0; i < tableJson.length; i++) {
                colNames.push(tableJson[i].label);
                var field = new Object();
                for (var attr in tableJson[i]) {
                    if (attr === 'label') {
                        continue;
                    }
                    field[attr] = tableJson[i][attr];
                }
                colModel.push(field);
            }
        })();
        var tableObj = {
            url: obj.url,
            postData: obj.postData,
            autowidth: true,
            width: '100%',
            height: '100%',
            hidegrid: true,//表格收缩,
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: colNames,
            colModel: colModel,
            prmNames: {
                page: "page",
                rows: "rowNum"
            },
            rowNum: obj.rowNum || rowNum,//一页显示多少条
            //rowList: [10, 20, 30],//可供用户选择一页显示多少条
            pager: $("#gridPager"),//表格页脚的占位符(一般是div)的id
            sortorder: "desc",//排序方式,可选desc,asc
            mtype: "POST",//向后台请求数据的ajax的类型。可选post,get
            rownumbers: true,
            viewrecords: true,
            multiselect: obj.multiselect === 'undefined' ? false : obj.multiselect,
            jsonReader: {
                status: "status",
                msg: "msg",
                root: "value.rows",
                page: 'value.currentPage', // 将page修改为currPage
                total: 'value.totalPage', // 将total修改为totalPage
                records: 'value.totalRecord', // 将records修改为totals
                repeatitems: false
            },
            beforeRequest: function () {
                index = _self.load();
            },
            gridComplete: function () {
                _self.closeLoad(index);
            },
            ondblClickRow: function (rowid, iRow, iCol, e) {
                obj.ondblClickRow && obj.ondblClickRow(rowid, iRow, iCol, e);
            }
        };
        $("#gridTable").jqGrid(tableObj);

        /*响应收缩*/
        function tablesize() {
            var width = $('.jqGrid_wrapper').width();
            $(".gridTable").setGridWidth(width);
        }

        $(window).bind('resize', function () {
            tablesize();
        });
    }
    //遍历form获取值
    , getFormData: function (obj) {
        if (!obj) {
            obj = {}
        }
        if (!obj.data) {
            obj.data = {}
        }
        $(obj.id || "form [name]").each(function () {
            var name = $(this).attr("name"),
                type = $(this).attr("type");
            if (name) {
                if (type === 'radio') {
                    if ($(this).prop("checked")) {
                        obj.data[name] = $(this).val();
                    }
                } else {
                    obj.data[name] = $(this).val();
                }
            }
        });
        return obj.data;
    }
    //列表
    , tableList: function (obj) {
        //导入模块
        var table = layui.table, _self = this;

        //渲染表格
        function loadTable(obj) {
            var index = layer.load();
            if (!obj.upData) {
                obj.upData = {}
            }
            table.render({
                id: 'id'
                , elem: '#table-list'
                , url: _self.ctx + obj.url
                , method: obj.type || 'post'
                , where: obj.upData
                , loading: true
                , page: { //详细参数可参考 laypage 组件文档
                    curr: 1
                    , limit: obj.limit||setTableH().limit
                    , groups: 10
                    , layout: ['prev', 'page', 'next', 'count', 'skip'] //自定义分页布局
                }
                , done: function (res, curr, count) {
                    layer.close(index);
                    obj.done&&obj.done(res, curr, count);
                }
                , request: {
                    limitName: 'rowNum'
                }
                , response: {
                    statusCode: 1
                    , dataName: 'value'
                    , statusName: 'status'
                }
                , parseData: function (res) { //res 即为原始返回的数据
                    if(!res.value||res.value.length>0){
                        return {
                            "status": res.status,
                            "msg": res.msg,
                            "count": res.count,
                            "value": res.value
                        }
                    }else{
                        return {
                            "status": res.status,
                            "msg": res.msg,
                            "count": res.value.totalRecord,
                            "value": res.value.rows
                        }
                    }

                }
                , height: obj.height || 'full-135'
                , cellMinWidth: 80
                , cols: obj.cols
            });

            //设置表格高度
            function setTableH() {
                var cellH = 38;
                var tableH = $(obj.boxEle||".biyue_main").height();
                var limit = Math.floor((tableH - 20) / cellH - 2);
                return {
                    tableH: tableH
                    , limit: limit
                }
            }
        }

        //搜搜
        function search(obj) {
            var index = layer.load();
            if (!obj) {
                obj = {}
            }
            //用于兼容ie
            if (obj.where) {
                obj.where.nowDate = new Date();
            } else {
                obj.where = {
                    nowDate: new Date()
                }
            }
            var pageNum;
            if (obj.pageBool === undefined) {
                obj.pageBool = false;
            }
            if (obj.pageBool) { //true则保持原有页面吗，false则回到1页；
                pageNum = parseInt($('#table-data .layui-laypage-curr em').eq(1).html());
            } else {
                pageNum = 1;
            }
            table.reload('id', {
                where: obj.where
                , page: {
                    curr: pageNum //重新从第 1 页开始
                }
                , done: function (res, curr, count) {
                    layer.close(index);
                    obj.done&&obj.done(res, curr, count);
                }
            });
        }

        var tableList = {
            loadTable: loadTable
            , search: search
        };
        return tableList;
    }
    , area: function () {
        window.biyueDataA = {}; //声明全局变量，存放区域工地数据
        var _self = this, form = layui.form, userType = (top.globalData.userType === "3");
        biyueDataA.userType = userType;
        if (layui) {
            //控制条件
            function areaConditions(obj) {
                if (userType) {
                    $(".area").hide();
                    $(".site").hide();
                    $(".area").parents(".layui-form-item").hide();
                    $('[name="area"]').html('<option value="' + top.globalData.areaId + '" selected>' + top.globalData.areaName + '</option>');
                    $('[name="site"]').html('<option value="' + top.globalData.constructionId + '" selected>' + top.globalData.constructionName + '</option>');
                    form.render('select');
                    if (obj.fun) {
                        obj.fun(top.globalData.constructionId)
                    }
                    window.biyueDataA.areaId = top.globalData.areaId;
                    window.biyueDataA.areaName = top.globalData.areaName;
                    window.biyueDataA.constructionId = top.globalData.constructionId;
                    window.biyueDataA.constructionName = top.globalData.constructionName;
                    //如何为表格,且更多搜索只存在区域和工地时
                    if (obj.type === "table") {
                        var len = $(".search-more-box .layui-form-item").length;
                        if (len === 2) {
                            $("#search-more").hide();
                            $(".search-more-box").hide();
                        }
                    }
                    //数据加载完后执行
                    obj.complete && obj.complete();
                    return true;
                } else {
                    return false;
                }
            };

            //区域
            function area(obj) {
                if (!obj) {
                    obj = {};
                }
                if (!obj.fun) {
                    obj.fun = function () {
                    }
                }
                //如果为弹窗模式   obj.type="pop"
                if (obj.type === "pop") {
                    if (biyue.urlData.type === "edit") {
                        $(".area").remove();
                        $(".site").remove();
                        if (!userType) {
                            $(".area_edit").show();
                            $(".site_edit").show();
                        } else {
                            $(".area_edit").parents(".layui-form-item").hide();
                        }
                        return;
                    } else {
                        $(".area_edit").remove();
                        $(".site_edit").remove();
                    }
                }

                if (areaConditions(obj)) return;
                _self.ajax({
                    url: '/territory/queryAuthAreaByUserId',
                    data: {"userId": userId},
                    fun: function (data) {
                        var dataV = data.value, $list = "<option value=\"\">选择区域</option>", dataS = [];
                        if (dataV.length === 1) {  //如只有1个是，默认选中
                            $list += '<option value="' + dataV[0].areaId + '" selected>' + dataV[0].areaName + '</option>';
                            site(dataV[0].data, obj);
                            window.biyueDataA.areaId = dataV[0].areaId;
                            window.biyueDataA.areaName = dataV[0].areaName;
                        } else {
                            for (var i = 0; i < dataV.length; i++) {
                                dataS[dataV[i].areaId] = dataV[i].data;
                                if (obj.frist && i === 0) {
                                    $list += '<option value="' + dataV[i].areaId + '" selected>' + dataV[i].areaName + '</option>';
                                } else {
                                    $list += '<option value="' + dataV[i].areaId + '">' + dataV[i].areaName + '</option>';
                                }
                            }
                        }
                        $('[name="area"]').html($list);
                        form.render('select');
                        form.on('select(area)', function (data) {
                            var id = data.value;
                            if (id) {
                                site(dataS[id], obj);
                                window.biyueDataA.areaId = id;
                                window.biyueDataA.areaName = $('.area [lay-value="' + id + '"]').html();
                            } else {
                                site([], obj);
                                window.biyueDataA.areaId = "";
                                window.biyueDataA.areaName = "";
                            }
                        });
                        //数据加载完后执行
                        obj.complete && obj.complete();
                    }
                });
            }

            //工地
            function site(data, obj) {
                var $list = "<option value=\"\">选择工地</option>";
                if (data.length === 1) {
                    $list += '<option value="' + data[0].constructionId + '" selected>' + data[0].constructionName + '</option>';
                    obj.fun(data[0].constructionId);
                    window.biyueDataA.constructionId = data[0].constructionId;
                    window.biyueDataA.constructionName = data[0].constructionName;
                } else {
                    for (var i = 0; i < data.length; i++) {
                        if (obj.frist && i === 0) {
                            $list += '<option value="' + data[i].constructionId + '" selected>' + data[i].constructionName + '</option>';
                        } else {
                            $list += '<option value="' + data[i].constructionId + '">' + data[i].constructionName + '</option>';
                        }
                    }
                }
                $('[name="site"]').html($list);
                form.render('select');
                form.on('select(site)', function (data) {
                    var id = data.value;
                    if (id) {
                        obj.fun(id);
                        window.biyueDataA.constructionId = id;
                        window.biyueDataA.constructionName = $('.site [lay-value="' + id + '"]').html();
                    } else {
                        obj.fun("");
                        window.biyueDataA.constructionId = "";
                        window.biyueDataA.constructionName = "";
                    }
                });
            }
        }
        return {
            area: area
        }
    }
    , open: function (obj) {
        var _self = this;
        if (!obj) {
            obj = {}
        }
        if (!obj.yes) {
            obj.yes = function () {
            }
        }
        var title = obj.title,
            url = _self.ctx + obj.url,
            area = obj.area;
        if (!title) {
            title = false;
        }
        if (!url) {
            url = "404.html";
        }
        var w, h;
        if (!area) {
            w = '780px';
            h = '500px';
        } else {
            w = area.w;
            h = area.h;
        }
        layer.open({
            type: obj.type || 2,
            title: title,
            btn: obj.btn || "",
            skin: 'layui-layer-rim',
            maxmin: true,
            area: [w, h],
            shadeClose: false, //点击遮罩关闭
            content: obj.content || url,
            yes: obj.yes,
            cancel: obj.cancel,
            end: obj.end,
            success: obj.success
        });
    }
    //开始与结束时间插件
    , timeSD: function (obj) {
        if (!obj) {
            obj = {}
        }
        //时间插件
        var laydate = layui.laydate, form = layui.form;
        var endObj = {
            elem: "#end"
            , type: obj.type||'datetime'
            , done: function (value, date) {
                if (value !== "") {
                    date.month = date.month - 1;
                    startDate.config.max = date;
                }
            }
        };
        var startObj = {
            elem: "#start"
            , type: obj.type||'datetime'
            , done: function (value, date) {
                if (value !== "") {
                    date.month = date.month - 1;
                    endDate.config.min = date;
                }
            }
        };
        if (obj.format) {
            startObj.format = obj.format;
            endObj.format = obj.format;
        }
        if (obj.valueS) {
            startObj.value = obj.valueS;
        }
        if (obj.valueE) {
            endObj.value = obj.valueE;
        }
        var startDate = laydate.render(startObj);
        var endDate = laydate.render(endObj);
    }
    //对时间进行组装
    , timeText: function (now) {
        var year = now.getFullYear();       //年
        var month = now.getMonth() + 1;     //月
        var day = now.getDate();            //日

        var clock = year + "-";

        if (month < 10)
            clock += "0";

        clock += month + "-";

        if (day < 10)
            clock += "0";

        clock += day + " ";
        return (clock);
    }
    , openTab: function (obj) {
        var title = obj.title,
            url = obj.url,
            area = obj.area;
        if (!title) {
            title = false;
        }
        if (!url) {
            url = "404.html";
        }
        var w, h;
        if (!area) {
            w = '780px';
            h = '500px';
        } else {
            w = area.w;
            h = area.h;
        }
        layer.open({
            type: 2,
            maxmin: true,
            area: [w, h],
            shadeClose: false, //点击遮罩关闭
            tab: obj.tab
        });
    }
    , verify: function () {
        var form = layui.form;
        form.verify({
            textarea: [
                /^.{0,500}$/m
                , '输入文字过长,请限制在500字以内!'
            ]
        });

        $("[lay-verify='required']").each(function () {
            $(this).parent().parent().find(".layui-form-label").prepend('<i class="req">*</i>');
        })
        $("[readonly]").click(function () {
            var bool = $(this).attr("readclick");
            if(bool!=="true"){
                layer.msg('该项禁止修改！', {time: 1000, icon: 0})
            }
        })
    }
    //树形图设置
    , ztreeSet: function (obj) {
        if (!obj) {
            obj = {}
        }
        var setting = {
            view: {
                fontCss: {"color": "#000", "font-family": "宋体"},
                dbClickExpand: false,
                showLine: true
            },
            check: {
                enable:  obj.check || false
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: obj.idKey || "id",
                    pIdKey: "pId",
                    rootPId: ""
                }
            },
            callback: {
                onClick: function (event, treeId, treeNode) {
                    obj.click && obj.click(event, treeId, treeNode);
                }
                , beforeClick: function (treeId, treeNode, clickFlag) {
                    if (obj.beforeClick) {
                        var bool = obj.beforeClick(treeId, treeNode, clickFlag);
                        if (bool === true) {
                            return bool;
                        } else if (bool === false) {
                            return bool;
                        }
                    }
                },
                onDblClick: function (event, treeId, treeNode) {
                    obj.onDblClick && obj.onDblClick(event, treeId, treeNode);
                },
                onCheck:function (event, treeId, treeNode) {
                    obj.onCheck && obj.onCheck(event, treeId, treeNode);
                }
            }
        };
        //引用 $.fn.zTree.init(el, set, data);
        return setting;
    }
    , scroll: function (el, obj) {
        if (!obj) {
            obj = {}
        }
        $(el || "html").niceScroll({
            styler: "fb",
            cursorcolor: "#cccccc",
            cursorwidth: '5',
            cursorborderradius: '5px',
            background: '',
            autohidemode: obj.autohidemode === false ? false : true,
            spacebarenabled: false,
            cursorborder: '0',
            zindex: '1000'
        });
    }
    , getDate: function () {
        var nowDate = new Date();
        var year = nowDate.getFullYear();
        var month = nowDate.getMonth() + 1 < 10 ? "0" + (nowDate.getMonth() + 1)
            : nowDate.getMonth() + 1;
        var day = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate
            .getDate();
        var dateStr = year + "-" + month + "-" + day;
        return dateStr;
    }
    //下拉框选择事件
    , select: function () {
        $(".btn-group").on('click', '.dropdown-menu li', function () {
            var par = $(this).parents(".btn-group");
            var name = $(this).find("a").html();
            var val = $(this).find("a").attr("data-value");
            par.find(".dropdown-text").attr("data-value", val).html(name);
        })
    }
    , control: function () {//TODO 用于旧版本兼容
        var search = window.location.search.replace("?", "").split("&");
        var searchObj = {};
        for (var i in search) {
            searchObj[search[i].split("=")[0]] = search[i].split("=")[1];
        }
        if (searchObj.operationAuthority !== undefined) {
            searchObj.operationAuthority = searchObj.operationAuthority.split(",");
            $(".tools-btn .btn-group").addClass("conRemove");
            for (var j in searchObj.operationAuthority) {
                powerText(searchObj.operationAuthority[j]);
            }
            $(".conRemove").remove();
        }

        function powerText(prop) {
            switch (prop) {
                case "list":
                    $("#search_link").parent().removeClass("conRemove")   //"查询"
                    break;
                case "view":
                    $("#view_link").parent().removeClass("conRemove")   //"查看"
                    break;
                case "save":
                    $("#add_link").parent().removeClass("conRemove")   //"新增"a
                    $("#add_link").parent().removeClass("conRemove")   //"录入"a
                    break;
                case "add":
                    $("#add_link").parent().removeClass("conRemove")   //"新增"a
                    $("#add_link").parent().removeClass("conRemove")   //"录入"a
                    break;
                case "edit":
                    $("#edit_link").parent().removeClass("conRemove")   //"编辑"
                    $("#change_link").parent().removeClass("conRemove")   //"修改"
                    break;
                case "update":
                    $("#edit_link").parent().removeClass("conRemove")   //"编辑"
                    $("#change_link").parent().removeClass("conRemove")   //"修改"
                    break;
                case "delete":
                    $("#delete_link").parent().removeClass("conRemove")   //"删除"
                    break;
                case "distribution":
                    $("#createUser").parent().removeClass("conRemove")   //生成用户
                    break;
                /*  case "audit":
                      $("#view_link").parent().removeClass("conRemove")   //"审核"
                      break;
                  case "submit":
                      $("#view_link").parent().removeClass("conRemove")   //"提交"
                      break;
                  case "print":
                      $("#view_link").parent().removeClass("conRemove")   //"打印"
                      break;*/
                case "import":
                    $("#import_link").parent().removeClass("conRemove")   //"导入"
                    break;
                case "affirm":
                    $("#confirm_link").parent().removeClass("conRemove")   //"确认完成"
                    break;
                case "export":
                    $("#export_link").parent().removeClass("conRemove")   //"导出"
                    break;
                case "download":
                    $("#template_link").parent().removeClass("conRemove")   //"下载模板"
                    break;
                case "task":
                    $("#link").parent().removeClass("conRemove")   //"历史任务"
                    break;
                case "chart":
                    $("#chart_link").parent().removeClass("conRemove")   //"趋势图"
                    break;
                case "allot":
                    $("#assign_roles").parent().removeClass("conRemove")   //"分配角色"
                    break;
                case "allotVideo":
                    $("#assign_video").parent().removeClass("conRemove")   //"分配视频"
                    break;
                case "reset":
                    $("#reset_pwd_link").parent().removeClass("conRemove")   //"重置密码"
                    break;
                case "assign":
                    $("#assign_link").parent().removeClass("conRemove")   //"分配工地"
                    break;
                case "allotDoor":
                    $("#allot_door").parent().removeClass("conRemove")   //"分配门"
                    break;
                case "warningData":
                    $("#warningData").parent().removeClass("conRemove")   //"预警数据"
                    break;
                case "trendChart":
                    $("#lineChart").parent().removeClass("conRemove")   //"趋势图"
                    break;
                case "pointChart":
                    $("#testPicture").parent().removeClass("conRemove")   //"测点部署图"
                    break;
                case "viewThreshold":
                    $("#viewWarning").parent().removeClass("conRemove")   //"查看阈值"
                    break;
                case "historyData":
                    $("#historyData").parent().removeClass("conRemove")   //"历史数据"
                    break;
                default:
                    return ""
            }
        }
    }
    , biyueControl: function () {//TODO 用于新版本权限控制
        var search = window.location.search.replace("?", "").split("&");
        var searchObj = {};
        for (var i in search) {
            searchObj[search[i].split("=")[0]] = search[i].split("=")[1];
        }
        if (searchObj.operationAuthority!==undefined) {
            searchObj.operationAuthority = searchObj.operationAuthority.split(",");
            $(".biyue_tool .layui-btn").addClass("conRemove");
            for (var j in searchObj.operationAuthority) {
                powerText(searchObj.operationAuthority[j]);
            }
            $(".conRemove").remove();
        }
        function powerText(prop) {
            switch (prop) {
                case "list":
                    $("#search_link").removeClass("conRemove")   //"查询"
                    break;
                case "refresh":
                    $("#refresh_link").removeClass("conRemove")   //"初始化数据"
                    break;
                case "view":
                    $("#view_link").removeClass("conRemove")   //"查看"
                    break;
                case "save":
                    $("#add_link").removeClass("conRemove")   //"新增"a
                    $("#add_link").removeClass("conRemove")   //"录入"a
                    break;
                case "add":
                    $("#add_link").removeClass("conRemove")   //"新增"a
                    $("#add_link").removeClass("conRemove")   //"录入"a
                    break;
                case "edit":
                    $("#edit_link").removeClass("conRemove")   //"编辑"
                    $("#change_link").removeClass("conRemove")   //"修改"
                    break;
                case "update":
                    $("#edit_link").removeClass("conRemove")   //"编辑"
                    $("#change_link").removeClass("conRemove")   //"修改"
                    break;
                case "delete":
                    $("#delete_link").removeClass("conRemove")   //"删除"
                    break;
                case "distribution":
                    $("#createUser").removeClass("conRemove")   //生成用户
                    break;
                case "import":
                    $("#import_link").removeClass("conRemove")   //"导入"
                    break;
                case "affirm":
                    $("#confirm_link").removeClass("conRemove")   //"确认完成"
                    break;
                case "export":
                    $("#export_link").removeClass("conRemove")   //"导出"
                    break;
                case "download":
                    $("#template_link").removeClass("conRemove")   //"下载模板"
                    break;
                case "task":
                    $("#link").removeClass("conRemove")   //"历史任务"
                    break;
                case "chart":
                    $("#chart_link").removeClass("conRemove")   //"趋势图"
                    break;
                case "allot":
                    $("#assign_roles").removeClass("conRemove")   //"分配角色"
                    break;
                case "allotVideo":
                    $("#assign_video").removeClass("conRemove")   //"分配视频"
                    break;
                case "reset":
                    $("#reset_pwd_link").removeClass("conRemove")   //"重置密码"
                    break;
                case "assign":
                    $("#assign_link").removeClass("conRemove")   //"分配工地"
                    break;
                case "allotDoor":
                    $("#allot_door").removeClass("conRemove")   //"分配门"
                    break;
                case "warningData":
                    $("#warningData").removeClass("conRemove")   //"预警数据"
                    break;
                case "trendChart":
                    $("#lineChart").removeClass("conRemove")   //"趋势图"
                    break;
                case "pointChart":
                    $("#testPicture").removeClass("conRemove")   //"测点部署图"
                    break;
                case "viewThreshold":
                    $("#viewWarning").removeClass("conRemove")   //"查看阈值"
                    break;
                case "historyData":
                    $("#historyData").removeClass("conRemove")   //"历史数据"
                    break;
                default:
                    return ""
            }
        }
    }
    //列表公用事件
    , listCommon: function () {
        var _self = this;

        //++++++++++++++++更多查询条件
        function hide() {
            $("#search-more .fa-angle-double-down").removeClass('active');
            $(".search-more-box").removeClass('layui-show');
            $("#search-more").removeClass('more-show').addClass('more-hide');
        }

        function show() {
            $("#search-more .fa-angle-double-down").addClass('active');
            $(".search-more-box").addClass('layui-show');
            $("#search-more").removeClass('more-hide').addClass('more-show');
        }

        $(".biyue_search").on('click', '#search-more.more-hide', function (e) {
            _self.stopBubble(e);
            show()
        }).on('click', '#search-more.more-show', function (e) {
            _self.stopBubble(e);
            hide()
        });
        $(".search-more-box").click(function (e) {
            _self.stopBubble(e);
        });
        $(window).click(function () {
            hide()
        });

        //回车查询
        $(".biyue_search input").keydown(function (event) {
            if (event.keyCode == 13) {
                $("#search").click();
            }
        })
    }
    //查询初始化
    , searchInit: function (obj) {
        if (!obj) {
            obj = {}
        }
        ;
        $("#search_refresh").click(function () {
            $("#search-input input").each(function () {
                $(this).val("");
            });
            $("#search-input .queryCondition .dropdown-text").each(function () {
                var id = $(this).attr("id");
                $(this).html("请选择").attr("data-value", "");
                if (obj.select) {
                    for (var i in obj.select) {
                        if (i === id) {
                            $(this).html("请选择").attr("data-value", obj.select[i].value || "");
                            return
                        }
                    }
                }
            });
            $("#search_link").click();
        })
    }
    //弹出窗默认事件
    , popDefault: function () {
        //取消事件
        $("#com-close").click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
    }
};
window.biyue = new biYue();
biyue.urlData = biyue.urlSearch(); //由于使用路径参数比较多,所以在此,公有化
