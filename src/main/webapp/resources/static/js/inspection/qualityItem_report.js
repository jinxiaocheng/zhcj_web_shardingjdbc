var report={
    biyue:new biYue(),
    getAjax:function (obj) {
        $(".error").hide();
        var _self=this,biyue=_self.biyue;
        biyue.ajax({
            url:'/inspection/selectItemsByCompany',
            data:obj.data,
            fun:function (data) {
                if(data.value.length<1){
                    $(".error").show();
                }
                _self.createTableList(data.value);
            }
        })
    },
    createTableList:function (dataF) {
        var $list="";
        for(var i in dataF){
            var dataS=dataF[i].data;
            if(dataS){
                var rowspan=dataS.length;
                for(var j in dataS){
                    $list+='<tr>' ;
                    if(j==0){
                        $list+='<td data-index="1"  data-value="'+dataF[i].companyId+'" rowspan="'+rowspan+'"><span>'+dataF[i].companyName+'</span></td>';
                    }
                    $list+='<td data-index="2" ><span>'+dataS[j].name+'</span></td>'+
                        '<td data-index="3"><span>'+dataS[j].qty+'</span></td>' +
                        '</tr>';
                }
            }
        }
        $("#tableBox tbody").html($list);
    }
};

//滚动条美化
$(".tableBody").niceScroll({
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

$("#search-input input").keydown(function() {//给输入框绑定按键事件
    if(event.keyCode == "13") {//判断如果按下的是回车键则执行下面的代码
        $("#search_link").click();
    }
});
