/*
 *人员管理
 **/
//人员管理录入
//有两列数据
function layer_showDouble(title,url,area) {
	if (title == null || title == '') {
		title=false;
	};
	if (url == null || url == '') {
		url="404.html";
	};
	var w, h;
	if(area == null || area == ''){
		w = '780px';
		h = '500px';
	} else {
		w = area.w;
		h = area.h;
	}
	layer.open({
		type: 2,
		title: "|&nbsp;"+title,
		maxmin: true,
		area: [w,h],
		shadeClose: false, //点击遮罩关闭
		content:url
	});
};
//数据较少
function layer_showOdd(title,url,area) {
	if (title == null || title == '') {
		title=false;
	};
	if (url == null || url == '') {
		url="404.html";
	};
//	if (w == null || w == '') {
//		w='800px';
//	};
//	if (h == null || h == '') {
//		h=$(window).height() - 50;
//	};
	var w, h;
	if(area == null || area == ''){
		w = '400px';
		h = '300px'
	} else {
		w = area.w;
		h = area.h;
	}
	layer.open({
		type: 2,
		title: "|&nbsp;"+title,
		maxmin: true,
		area: [w,h],
		shadeClose: false, //点击遮罩关闭
		content:url
	});
};

//人员管理删除
function memberdel(pageGrid) {
	layer.confirm('确认删除该条记录吗？', {
		title: '提示',
		icon:2,
		btn: ['确定','关闭'],
		yes:function(){
			layer.msg('删除成功', {icon: 1,time: 1000});
			//$(".show_iframe").find("iframe")[0].contentWindow.delrow();//方法一：调用iframe中的函数
		}
	});
}