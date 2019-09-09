var kk_array=[],index_loadA=0;
$(function () {
    $(window).bind('resize', function () {
        tablesize();
    });

    if((typeof biyueTableBool)==="undefined"){
        initGrid();
    }

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
	/*kk_array = getJsonValue(extendParam, 'selectArray',null);
    console.log(kk_array)*/
	var myOnPaging=getJsonValue(extendParam, 'myOnPaging',function(){});
	var myOnSelectRow=getJsonValue(extendParam, 'myOnSelectRow',function(){});
	var myOnSelectAll=getJsonValue(extendParam, 'myOnSelectAll',function(){});
	var myOnCellSelect=getJsonValue(extendParam, 'myOnCellSelect',function(){});
	var myListHeight=getJsonValue(extendParam, 'myListHeight',0);
	var myOndblClickRow=getJsonValue(extendParam, 'myDblClickRow',function(){});
    var myBeforeSelectRow=getJsonValue(extendParam, 'myBeforeSelectRow',function(){return 1});
	var colNames = new Array;
    var colModel = new Array;
    var data = JSON.stringify(paramData);

    for (var i = 0; i < tableJson.length; i++) {
        if(top.globalData.userType==3){
            if(tableJson[i].name==="constructionName"){
                tableJson[i].hidden=true;
            }
            if(tableJson[i].name==="terrName"){
                tableJson[i].hidden=true;
            }
            if(tableJson[i].name==="belongAreaName"){
                tableJson[i].hidden=true;
            }
            if(tableJson[i].name==="areaName"){
                tableJson[i].hidden=true;
            }
        }
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
    var rowNum = Math.floor(($(window).height() - $('.response-head').height()- $('.ui-corner-bottom').height()-myListHeight- 120)/30);
    /* if(my_rowNum=="all") rowNum=all;*/
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
         rownumbers: true,
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
        beforeSelectRow:function(){
    	     var myBool=myBeforeSelectRow();
    	     if(myBool==1){
                 return true;
             }else{
                 return false;
             }

        },
        gridComplete:function () {
            layer.close(index_loadA);
        },
    	 loadComplete: gridLoadComplete,
    	 onSelectRow:function(rowid,status){
    		 myOnSelectRow(rowid,status)
    	 },
    	 onSelectAll:function(aRowids,status){
    		 myOnSelectAll(aRowids,status)
    	 },
    	 onCellSelect:function(	rowid,iCol,cellcontent,e){
    		 myOnCellSelect(rowid,iCol,cellcontent,e)
    	 },
    	 onPaging:function(){
    		 myOnPaging()
    	 },
        ondblClickRow:function(rowid,iRow,iCol,e){
            myOndblClickRow(rowid,iRow,iCol,e);
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
            Selectval.attr("data-value",dataValue);
        });
    }
});

//权限控制
;(function control() {
    var search=window.location.search.replace("?","").split("&");
    var searchObj={};
    for(var i in search){
        searchObj[search[i].split("=")[0]]=search[i].split("=")[1];
    }
    if(searchObj.operationAuthority!==undefined){
        searchObj.operationAuthority=searchObj.operationAuthority.split(",");
        $(".tools-btn .btn-group").addClass("conRemove");
        for(var j in searchObj.operationAuthority){
            powerText(searchObj.operationAuthority[j]);
        }
        $(".conRemove").remove();
    }
})();

$(window).keydown(function() {//给输入框绑定按键事件
    if(event.keyCode == "13") {//判断如果按下的是回车键则执行下面的代码
        $("#search_link").click();
    }
});
$("body").on('keydown','.ui-pg-input',function(event){
    stopEvent(event);
})
function stopEvent(event){ //阻止冒泡事件
    //取消事件冒泡
    var e=arguments.callee.caller.arguments[0]||event; //若省略此句，下面的e改为event，IE运行可以，但是其他浏览器就不兼容
    if (e && e.stopPropagation) {
        // this code is for Mozilla and Opera
        e.stopPropagation();
    } else if (window.event) {
        // this code is for IE
        window.event.cancelBubble = true;
    }
}

function powerText(prop) {
    switch(prop)
    {
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
        case "historyData":
            $("#history_data").parent().removeClass("conRemove")   //历史数据
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
        case "refresh":
            $("#refresh_link").parent().removeClass("conRemove")   //"岗位工种中数据初始化"
            break;
        default:
            return ""
    }
}