<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html style="height: 100%">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=0">

	<link rel="stylesheet" href="${ctx}/resources/static/plugins/bootstrap/css/bootstrap.css?v=${v}"/>
	<link rel="stylesheet" href="${ctx}/resources/static/css/style.css?v=${v}"/>
	<link rel="stylesheet" href="${ctx}/resources/static/css/report/index.css?v=${v}">
	<!--[if lt IE 9]>
	<script type="text/javascript" src="${ctx}/resources/static/js/lib/html5.js?v=${v}"></script>
	<script type="text/javascript" src="${ctx}/resources/static/js/lib/respond.min.js?v=${v}"></script>
	<script type="text/javascript" src="${ctx}/resources/static/js/lib/PIE_IE678.js?v=${v}"></script>
	<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-1.9.1.min.js?v=${v}"></script>
	<![endif]-->
	<title>统计报表</title>
</head>

<body>
<div class="main-body">
	<header>
		<div class="response-head titlePanel form-inline">
			<div class="ui-filter area">
				<label>区域:</label>
				<div class="btn-group queryCondition">
					<a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
					   data-value="" id="area">请选择</a>
					<a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
							class="caret"></span></a>
					<ul class="dropdown-menu" style="min-width:108px;">
					</ul>
				</div>
			</div>
			<div class="ui-filter site">
				<label>工地:</label>
				<div class="btn-group queryCondition">
					<a class="btn btn-default dropdown-text" data-toggle="dropdown" aria-expanded="false"
					   data-value="" id="site">请选择</a>
					<a class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><span
							class="caret"></span></a>
					<ul class="dropdown-menu" style="min-width:108px;">
					</ul>
				</div>
			</div>
			<div class="ui-filter" style="margin:0px 15px;">
				<a id="search_link" class="btn btn-primary"><i class="fa fa-search"></i>查询</a>
			</div>

		</div>
	</header>
	<section>
		<div class="charts"><div id="safetyInspectionChart"></div></div>
		<div class="charts"><div id="qualityInspectionChart"></div></div>
		<div class="charts"><div id="workTypeChart"></div></div>
		<div class="charts"><div id="areaConstructionChart"></div></div>
	</section>
</div>
</body>
<script>
    var ctx = "${ctx}";
    var userId = "${userId}";  //当前用户ID
    var type = "${type}";
</script>
<!--[if gte IE 9]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]-->
<![if !IE]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js?v=${v}"></script>
<![endif]>
<script type="text/javascript" src="${ctx}/resources/static/plugins/bootstrap/js/bootstrap.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/layer/layer.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/jquery.nicescroll.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/lib/echarts.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/list_area.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/common/listCommon.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/resources/static/js/report/index.js?v=${v}"></script>
<script>

	$(function() {
        init();
        my_data();
        winResize();
		$("html").niceScroll({
			styler : "fb",
			cursorcolor : "#000",
			cursorwidth : '5',
			cursorborderradius : '5px',
			background : '',
			spacebarenabled : false,
			cursorborder : '0',
			zindex : '1000'
		});

        ;(function () {
            if(top.globalData.userType==3){
                $('.main-body').css('padding-top','10px');
                $("header").hide();
            }
        })();

	});
	$("#search_link").on("click",function () {
        my_data()
    })
	function my_data() {
        // 各工种人数（柱状图）
        request('/person/queryWorkTypePersonQty',{
            areaId:$("#area").attr("data-value")||"",
            constructionId:$("#site").attr("data-value")||""
        }, function(data){
            if (data.status == 1) {
                loadWorkTypeChart(data.value);
            }
        });
        // 质量检查数、整改单月趋势图（线状图）
        request('/inspection/quality/queryInspectionMonthQtyList',{
            areaId:$("#area").attr("data-value")||"",
            constructionId:$("#site").attr("data-value")||""
        }, function(data){
            if (data.status == 1) {
                loadQualityInspectionChart(data.value);
            }
        });
        // 安全检查数、整改单月趋势图（线状图）
        request('/inspection/safety/queryInspectionMonthQtyList',{
            areaId:$("#area").attr("data-value")||"",
            constructionId:$("#site").attr("data-value")||""
        }, function(data){
            if (data.status == 1) {
                loadSafetyInspectionChart(data.value);
            }
        });
        // 出勤统计（线状图）
        request('/person/queryAttendanceNumQty',{
            areaId:$("#area").attr("data-value")||"",
            constructionId:$("#site").attr("data-value")||""
        }, function(data){
            if (data.status == 1) {
				var yAxisData=[];
				for(var i in data.value.yDataVo){
                    yAxisData[data.value.yDataVo[i].name]=data.value.yDataVo[i].data;
				}
                loadAreaConstructionChart(data.value.xdata,yAxisData);
            }
        });
    }
	function request(url,upload_json, func) {
		$.ajax({
			url : '${ctx}' + url,
			type : "post",
			data:JSON.stringify(upload_json),
			contentType : "application/json",
			success : func
		});
	}
	// 总劳动力统计（柱状图）
	function loadWorkTypeChart(data){
	    var xdata=[],ydata=[];
	    for(var i=0;i<data.length;i++){
            xdata[i]=data[i].name;
            ydata[i]=data[i].qty;
		}
        chart_1_option.xAxis[0].data=xdata;
        chart_1_option.series[0].data=ydata;
        chart_1.setOption(chart_1_option);
	}
	// 质量检查数、整改单月趋势图（线状图）
	function loadQualityInspectionChart(data){
        var xdata=[],ydata1=[],ydata2=[];
        for(var i=0;i<data.length;i++){
            xdata[i]=data[i].date;
            ydata1[i]=data[i].qty;
            ydata2[i]=data[i].correctiveQty;
        }
        chart_2_option.xAxis[0].data=xdata;
        chart_2_option.series[0].data=ydata1;
        chart_2_option.series[1].data=ydata2;
        chart_2.setOption(chart_2_option);
	}
	// 安全检查数、整改单月趋势图（线状图）
	function loadSafetyInspectionChart(data){
        var xdata=[],ydata1=[],ydata2=[];
        for(var i=0;i<data.length;i++){
            xdata[i]=data[i].date;
            ydata1[i]=data[i].qty;
            ydata2[i]=data[i].correctiveQty;
        }
        chart_3_option.xAxis[0].data=xdata;
        chart_3_option.series[0].data=ydata1;
        chart_3_option.series[1].data=ydata2;
        chart_3.setOption(chart_3_option);
	}
    // 出勤统计（线状图）
	function loadAreaConstructionChart(xAxisData,yAxisData){
        var xdata=xAxisData,legendData=[],selectedObj={},num=0;
        chart_4_option.series=new Array();
        for(var i in yAxisData){
            legendData.push(i);//添加图例
           /* if(num>3){
                selectedObj[i]=false;//控制图例是否激活
			}*/
			num++;
            chart_4_option.series.push({ //添加数据
                name:i,
                type:'line',
                data:yAxisData[i],
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                }
            })
        }

        var yAxisDataBool=true;
        for (var j in yAxisData){
            yAxisDataBool=false;
            break;
		}
		if(yAxisDataBool){
            var yAxisDataNo=[];
            yAxisDataNo[xdata.length-1]=0;
            chart_4_option.series.push({ //添加数据
                name:i,
                type:'line',
                data:yAxisDataNo
            })
		}
        chart_4_option.xAxis[0].data=xdata;
        chart_4_option.legend.data=legendData;
/*        chart_4_option.legend.selected=selectedObj;*/
        chart_4=echarts.init(document.getElementById("areaConstructionChart"));
        chart_4.setOption(chart_4_option);
	}
</script>
</html>