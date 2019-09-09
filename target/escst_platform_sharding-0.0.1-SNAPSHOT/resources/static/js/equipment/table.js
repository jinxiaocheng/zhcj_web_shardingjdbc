var kk_array=[];
$(function () {
    $(window).bind('resize', function () {
        tablesize();
    });
    initGrid();
    //查询按钮
    $("#search_link").click(function () {
        $("#gridTable").jqGrid("setGridParam", {
            url: getGridUrl(),
            postData: getGridParamJson(),
            page: 1
        }).trigger("reloadGrid");
    });

    $("html").niceScroll({
        styler: "fb", cursorcolor:"#cccccc",
        cursorwidth: '5',
        cursorborderradius: '5px',
        background: '',
        spacebarenabled: false,
        cursorborder: '0',
        zindex: '1000'
    });

    //下拉选择
    $(".queryCondition").each(function () {
        $(this).Bsselect();
    });

    //关闭弹窗
    $(".delClose").click(function(){
        layer_close();
    });
    tablesize();
});

/*关闭弹出框口*/
function layer_close(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
};
//完成服务器请求后，回调函数
function gridLoadComplete() {
};
function getGridExtendParam(){

};
//获取json的值
function getJsonValue(jsonObj, key, defaultValue) {
    if (jsonObj && jsonObj[key]) {
        return jsonObj[key];
    }
    return defaultValue;
}
//初始化
function initGrid() {
    var head = getGridHead();
    var param = getGridParamJson();
    var url = getGridUrl();
    var extendParam = getGridExtendParam();
    createGrid(url, head, param, extendParam);
};
//创建表格 默认
function createGrid(url, tableJson, paramData, extendParam) {
    var bool=true;
    //排序
    var sortName = getJsonValue(extendParam, 'sortName', 'index');
    //多选
    var multiselect = getJsonValue(extendParam, 'multiselect', false);
    //定义选中数组
    kk_array = getJsonValue(extendParam, 'selectArray',null);
    var myOnPaging=getJsonValue(extendParam, 'myOnPaging',function(){});
    var myOnSelectRow=getJsonValue(extendParam, 'myOnSelectRow',function(){});
    var myOnSelectAll=getJsonValue(extendParam, 'myOnSelectAll',function(){});
    var myOnCellSelect=getJsonValue(extendParam, 'myOnCellSelect',function(){});

    var colNames = new Array;
    var colModel = new Array;
    var data = JSON.stringify(paramData);
    for (var i = 0; i < tableJson.length; i++) {
        colNames.push(tableJson[i].label);
        var field = new Object();
        for(var attr in tableJson[i]) {
            if (attr =='label') {
                continue;
            };
            field[attr] = tableJson[i][attr];
        };
        colModel.push(field);
    };
    var rowNum = Math.floor(($(window).height() - $('.response-head').height()- $('.ui-corner-bottom').height()- $('.distribution_form').height() - 120)/30);
    $("#gridTable").jqGrid({
        url: url,//www,//组件创建完成之后请求数据的ur l
        postData: paramData,
        autowidth: true,
        hidegrid: true,//表格收缩,
        datatype: "json",//请求数据返回的类型。可选json,xml,txt
        colNames: colNames,
        colModel: colModel,
        prmNames: {
            page: "page",
            rows: "rowNum"
        },
        rowNum: rowNum,//一页显示多少条
        //rowList: [10, 20, 30],//可供用户选择一页显示多少条
        pager: $("#gridPager"),//表格页脚的占位符(一般是div)的id
        sortname: sortName,//初始化的时候排序的字段
        sortorder: "desc",//排序方式,可选desc,asc
        mtype: "POST",//向后台请求数据的ajax的类型。可选post,get
        viewrecords: true,
        multiselect: multiselect,
        jsonReader: {
            status: "status",
            msg: "msg",
            root: "value.rows",
            page: 'value.currentPage', // 将page修改为currPage
            total: 'value.totalPage', // 将total修改为totalPage
            records: 'value.totalRecord', // 将records修改为totals
            repeatitems: false
        },
        beforeRequest:function () {
            index_loadA=layer.load();
        },
        gridComplete:function () {
            layer.close(index_loadA);
        },
        loadComplete: gridLoadComplete,
        beforeSelectRow: function (rowId, e) {
            $(this).jqGrid("resetSelection");
            return true;
        }
    });
}
/*响应收缩*/
function tablesize() {
    var width = $('.jqGrid_wrapper').width();
    $('.jqGrid_wrapper').height($(window).height() - $('.response-head').height() - 100);
    $(".gridTable").setGridWidth(width);
    var _windowHeight = $(window).height();
    var _windowWidth = $(window).width();
    $(".gridTable").setGridHeight($(window).height() - 180);
    if (_windowHeight < 700 && _windowWidth < 1280) {
        $(".gridTable").setGridHeight($(window).height() - 200);
    } else {
        $(".gridTable").setGridHeight($(window).height() - 170);
    };
};


/*扩展*/
$.fn.extend({
    /*下拉框*/
    Bsselect: function () {
        var obj = this, Selectval = obj.find(".dropdown-text"), Selectoption = obj.find(".dropdown-menu");
        Selectoption.find("li").click(function () {
            var option = $(this).text();
            var dataValue = $(this).children("a").attr("data-value");
            Selectval.text(option);
            Selectval.attr("data-value",dataValue).attr("title",option);
        });
    }
});