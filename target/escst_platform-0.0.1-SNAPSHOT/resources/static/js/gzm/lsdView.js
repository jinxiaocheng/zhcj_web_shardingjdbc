var moduleView = {
    getData:function () {
        var objSearch=biyue.urlSearch();
        biyue.ajax({
            url:"/flow/querFlowDetail",
            data:{
                id:objSearch.id
            },
            fun:function (data) {
                var dataV = data.value;
                for(var i in dataV){
                    if(i==="filePath"){
                        var $list = "";
                        $list+='<img class="showImg" data-value="'+dataV[i]+'" src="'+dataV[i]+'">';
                        $(".cdImg").html($list);
                    }else if(i==="collectorVos"){
                        var $cds="";
                        for(var j in dataV[i]){
                            $cds+=dataV[i][j].name+"("+dataV[i][j].number+")&nbsp;&nbsp;&nbsp;";
                        }
                        $('.cds').html($cds);
                    }else{
                        $('.'+i).html(dataV[i]);
                    }
                }
            }
        });
    }
}