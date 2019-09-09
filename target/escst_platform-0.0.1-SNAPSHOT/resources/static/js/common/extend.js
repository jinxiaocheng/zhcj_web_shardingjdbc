/*扩展*/
$.fn.extend({
    /*下拉框*/
    Bsselect: function () {
        var obj = this, Selectval = obj.find(".dropdown-toggle"), Selectoption = obj.find(".dropdown-menu");
        Selectoption.find("li").on("click",function () {
            var option = $(this).text();
            var dataValue = $(this).children("a").attr("data-value");
            Selectval.html(option+'<span class="caret"></span>');
            Selectval.attr("data-value",dataValue).attr("title",option);
        });
    },
    //表单提交;
    kk_tableForm:function (id,ajax) {
        var obj=this,selectObj=obj.find('[kk-contral]'),bool=true;
        selectObj.children("label").prepend('<i style="color: red;position: relative;top: 3px;left: -5px">*</i>');
        $("[kk-prop]").on("click",function () {
            if($(".kk_error")) $(".kk_error").removeClass("kk_error");
        });
        $(id).on("click",function(){
            bool=true;
            //获取所有部位空的对象
            selectObj=obj.find('[kk-contral=require]');
            //获取所有需要条件判断的对象
            RE_obj=obj.find('[kk-RE]');

            if($(".kk_error")) $(".kk_error").removeClass("kk_error");
            //不能为空
            selectObj.each(function (index) {
                var menu=$(this),
                    propObj=menu.find('[kk-prop]'),
                    kkProp=propObj.attr("kk-prop"),
                    error_text=menu.find("label").text().replace("*","");
                //对必填项进行判断
                if(kkProp==="value"){
                    if(propObj.val()==""||propObj.val()==null||propObj.val()==undefined){
                        my_error("请输入"+error_text+"!");
                        propObj.addClass("kk_error");
                        bool=false;
                        return bool;
                    }
                }else{
                    if(propObj.attr(kkProp)==""||propObj.attr(kkProp)==null||propObj.attr(kkProp)==undefined){
                        my_error("请选择"+error_text+"!");
                        propObj.addClass("kk_error");
                        bool=false;
                        return bool;
                    }
                }
            });
            if(!bool) return bool;
            //条件控制
            RE_obj.each(function () {
                var RE=$(this),
                    RE_value=RE.val(),//获取值
                    RE_conditions=RE.attr("kk-RE"),//获取判断条件
                    RE_data=null,//接受正则返回的数组
                    RE_errorText=null;//接受报错的语句

                if(RE_value!="") {
                    RE_data=kk_RegExp(RE_value,RE_conditions),
                    RE_errorText=RE.attr("kk-name")+RE_data[1];
                    bool=RE_data[0];
                    if(!bool){
                        my_error(RE_errorText+"!");
                        RE.addClass("kk_error");
                        return bool
                    }
                }
            });

            if(bool){
                //loading
                $(".my_load").show();
                ajax();
            }
        })
        
    },
    //在隐藏和显示所需控制的表单
    kk_showTable:function (bool) {
        var Selectval=$(this).find('[kk-contral]');
        Selectval.each(function(){
            var obj=$(this);
            if(bool){
                obj.attr("kk-contral","require");
            }else{
                obj.attr("kk-contral","");
            }
        })
    }
    //获取当前时间
    ,nowTime:function () {
        var Selectval=$(this).find('[kk-time]');
        var date=new Date()
            ,year=date.getFullYear()
            ,month=date.getMonth()+1
            ,day=date.getDate()
            ,hour=date.getHours()<10?"0"+date.getHours():date.getHours()
            ,minute=date.getMinutes()<10?"0"+date.getMinutes():date.getMinutes()
            ,seconds=date.getSeconds()<10?"0"+date.getSeconds():date.getSeconds();
        var times={
            days:year+'-'+month+'-'+day,
            minutes:hour+':'+minute+':'+seconds
        }
        Selectval.each(function(){
            var obj=$(this)
                ,attrVal=$(this).attr("kk-time")||'day'; //day:2017-10-01   date:2017-12-28 11:40:00
            if(attrVal=='day'){
                obj.val(times.days);
            }else if(attrVal=='date'){
                obj.val(times.days+" "+times.minutes);
            }

        })
    }
});
//下拉选择
$(".queryCondition").each(function () {
    $(this).Bsselect();
});
//错误预警
function my_error(text) {
    if(layer){
        layer.msg(text, {icon: 2,shift: 6,time:1500});
    }else{
        $(".error").fadeIn("fast");
        $(".error span").html(text);
        setTimeout(function(){
            $(".error").fadeOut("fast");
        },2000)
    }
}
//正则判断
function kk_RegExp(value,n) {
    var c=n.split("/")[0],//获取条件
        num=n.split("/")[1]?n.split("/")[1].split("-"):[],//获取控制的数量
        RE="", //正则;
        str="",//报错语句
        bool=true;//返回bool值
    //正则表达式
    switch (c){
        case "num":
            RE=/^\d+(\.\d+)?$/;
            str='格式不正确，请输入正数';
            break;
        case "precentNum":
            RE= /^(?:0|[1-9][0-9]?|100)$/;
            str='请输入0~100内的正整数！';
            break;
        case "int":
            RE=/^[0-9]*$/;
            str='格式不正确，请输入正整数';
            break;
        case 'email':
            RE=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
            str='格式不正确';
            break;
        case 'tel':
            RE=/^((13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}|(\d{3,4}-)?\d{7,8})$/;
            str='格式不正确';
            break;
        case  'url':
            RE=/^([\w-]+\.)+[\w-]+(\/[\w-./?%&=]*)?$/;
            str='格式不正确';
            break;
        case  'IDcard'://身份证
            RE=/(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}[0-9Xx]$)/;
            str='格式不正确';
            break;
        case  'textLength':// 设置文字长度
            RE=new RegExp('^.{'+num[0]+','+num[1]+'}$','m');
            str='最多输入'+num[1]+'字';
    }

    bool=RE.test(value);
    return [bool,str];
}

//获取路径后的参数
function getSearch() {
    var search=window.location.search.replace("?","").split("&");
    var searchObj={};
    for(var i in search){
        searchObj[search[i].split("=")[0]]=search[i].split("=")[1];
    }
    return searchObj;
}

//滚动条美化
if($("html").niceScroll){
    $("html").niceScroll({
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
}
