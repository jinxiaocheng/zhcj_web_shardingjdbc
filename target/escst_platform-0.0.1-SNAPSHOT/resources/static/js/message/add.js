/**
 * @desc 消息新增
 * @param null
 * @return
 * @author kz
 * @date 2018/2/6 15:45
 */
var personData=null;
$(".addPerson").on('click',function () {
    var _url = ctx + '/message/toAddPerson';
    var _title = '选择人员';
    var iframeWidth = '760px';
    var iframeHeight = '450px';
    window.sentData=personData;
    layer_showDouble(_title, _url, {w:iframeWidth,h:iframeHeight});
});

function getPersonData(data) {
    personData=clone(data);
    var $list="",$listCopy="";
    for(var i in personData){
        var $listBool=false;
        $listCopy="";
        $listCopy+='<div class="listBox_next" kk-id="'+i+'">\n<h2>'+i.split("$")[1]+'</h2>\n' ;
          for(var j in personData[i]){
              $listBool=true;
              $listCopy+= '<i class="alert alert-warning alert-dismissible" role="alert">'
                      +'<button type="button" class="close" data-dismiss="alert" aria-label="Close" kk-id="'+personData[i][j].id+'"><span aria-hidden="true">&times;</span></button>'
                      +'<span>'+personData[i][j].name+'</span> </i>'
          }
        $listCopy+='</div>';
        if($listBool){
            $list+=$listCopy;
            $("#addPerson>div").attr('data-value','1');
        }
    }
    $('#addPerson .listBox').html($list);

}

$("#addPerson").on('click','.close',function(){
    var kkId=$(this).attr('kk-id');
    var kkPid=$(this).parent().parent().attr('kk-id');
    var bool=false;
    /*var btn_num=$(this).parent().siblings().length;
    if(btn_num==1){
        var btn_parent_num=$(this).parent().parent('.listBox_next').siblings().length;
        if(btn_parent_num==1){
            $(this).parent().parent().parent().remove();
            $(".check_result>div").attr("data-value","");
        }else{
            $(this).parent().parent().remove();
        }
    }*/
    delete personData[kkPid][kkId];
    for(var i in personData){
        for (var j in personData[i]){
            bool=true;
        }
    }
    if(!bool){
        $("#addPerson>div").attr('data-value','');
    }
})

window.getPersonData=getPersonData;

//深度克隆
function clone(Obj) {
    var buf;
    if (Obj instanceof Array) {
        buf = [];  // 创建一个空的数组
        var i = Obj.length;
        while (i--) {
            buf[i] = clone(Obj[i]);
        }
        return buf;
    } else if (Obj instanceof Object){
        buf = {};  // 创建一个空对象
        for (var k in Obj) {  // 为这个对象添加新的属性
            buf[k] = clone(Obj[k]);
        }
        return buf;
    }else{
        return Obj;
    }
}