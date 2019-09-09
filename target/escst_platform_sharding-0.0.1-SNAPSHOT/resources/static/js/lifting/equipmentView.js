var moduleView = {
    getData:function () {
        var objSearch=biyue.urlSearch();
        biyue.ajax({
            url:"/hook/getHookEquipmentDetail",
            data:{
                id:objSearch.id
            },
            fun:function (data) {
                var dataV = data.value;
                for(var i in dataV){
                    $('.'+i).html(dataV[i]);
                }
            }
        });
    }
}