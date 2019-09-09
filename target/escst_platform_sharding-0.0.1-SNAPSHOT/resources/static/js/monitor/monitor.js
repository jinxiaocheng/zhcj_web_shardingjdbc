var monitor = {
    setTime:{//用于控制轮询
        danger:"",
        safe:"",
        cirWeb:""
    },
    //时间-开工日期-竣工日期-剩余天数-项目进度
    time:function () {
        var _self = this;
        biyue.ajax({
            url:"/construction/getConstructionSchedule",
            fun:function (data) {
                var dataV = data.value[0];
                var startTime = dataV.startDate,
                    endTime = dataV.endDate,
                    scale = dataV.percent,
                    date= new Date();

                var allNum = (new Date(endTime).getTime() - new Date(startTime).getTime())/1000/60/60/24;
                var surplus = Math.ceil((new Date(endTime).getTime() - new Date().getTime())/1000/60/60/24);

                if(surplus < 0){
                    surplus=0;
                }

                $(".startTime .content-box").html(startTime);
                $(".endTime .content-box").html(endTime);
                $(".dayNum .content-box").html(surplus+'<span> 天</span>');
                $(".progress .pro-num").html(scale);
                $(".progress .pro-bar").css("width",scale);
            }
        })
    },
    //时间组合
    getDate:function () {
        var nowDate = new Date();
        var year = nowDate.getFullYear();
        var month = nowDate.getMonth() + 1 < 10 ? "0" + (nowDate.getMonth() + 1)
            : nowDate.getMonth() + 1;
        var day = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate
            .getDate();
        var hour = nowDate.getHours() < 10 ? "0" + nowDate.getHours() : nowDate.getHours();
        var minute = nowDate.getMinutes() < 10 ? "0" + nowDate.getMinutes() : nowDate.getMinutes();
        var second = nowDate.getSeconds() < 10 ? "0" + nowDate.getSeconds() : nowDate.getSeconds();

        return year + "-" + month + "-" + day+" "+hour+":"+minute+":"+second;
    },
    //滚动
    scroll:function () {
        var _self=this;
      //危险作业
        function danger() {
            //初始化
            (function () {
                $(".danger-box").css("top",0);
                $(".danger-box").stop();
                if(_self.setTime.danger){clearInterval(_self.setTime.danger)}
            })();

            var mH=$(".danger .main-box").height();
            var dangerH=$(".danger-box").height();
            if(dangerH > mH){
                function ani() {
                    var boxH = $(".danger-box .list-box").eq(0).height()+41;
                    $(".danger-box").animate({top:'-'+boxH+"px"},1000,function () {
                        $(".danger-box").append($(".danger-box .list-box").eq(0));
                        $(".danger-box").css("top",0);
                    })
                }
                _self.setTime.danger=setInterval(function () {
                    ani();
                },7000);
            }
        }

        //安全检查
        function safe() {
            //初始化
            (function () {
                $(".safe .main-box ul").css("top",0);
                $(".safe .main-box ul").stop();
                if(_self.setTime.safe){clearInterval(_self.setTime.safe)}
            })();

            var mH=$(".safe .main-box").height();
            var dangerH=$(".safe .main-box ul").height();
            if(dangerH > mH){
                function ani() {
                    var boxH = $(".safe .main-box ul li").eq(0).height()+54;
                    $(".safe .main-box ul").animate({top:'-'+boxH+"px"},1000,function () {
                        $(".safe .main-box ul").append($(".safe .main-box ul li").eq(0));
                        $(".safe .main-box ul").css("top",0);
                    })
                }
                _self.setTime.safe=setInterval(function () {
                    ani();
                },10000);
            }
        }
        return {
            safe:safe,
            danger:danger
        }

    },
    //轮询
    refresh:function () {
        var _self =this;
      function danger() {
          biyue.ajax({
              url:'/riskOperation/data'
              ,load:true
              ,data:{
                  constructionId:top.globalData.constructionId,
                  type:0
              }
              ,fun:function (data) {
                  var dataV=data.value,$list='';
                  if(dataV.length>0){
                      for(var i in dataV){
                          if(dataV[i].operation===0){
                              $list+='  <div class="list-box" data-id="'+dataV[i].id+'">\n' +
                                  '                            <div class="c-title">'+dataV[i].title+'</div>\n' +
                                  '                            <ul class="c-content">\n' +
                                  '                                <li>'+dataV[i].content+'</li>\n' +
                                  '                            </ul>\n' +
                                  '                        </div>'
                          }else  if(dataV[i].operation===1){
                              var dangerDom=$(".danger-box .list-box[data-id='"+dataV[i].id+"']");
                              dangerDom.find(".c-title").html(dataV[i].title);
                              dangerDom.find(".c-content").html(dataV[i].content);
                          }
                      }
                      $(".danger-box").prepend($list);
                      _self.scroll().danger();
                  }
                  setTimeout(function () {
                      danger();
                  },10000)
              }
          })
      }
        danger();
      function safe() {
          biyue.ajax({
              url:'/inspection/listInspection'
              ,load:true
              ,data:{
                  constructionId:top.globalData.constructionId,
                  type:0
              }
              ,fun:function (data) {
                  var dataV=data.value,$list='';
                  if(dataV.length>0){
                      for(var i in dataV){
                          var correctiveStatus = "",color="";
                          if(dataV[i].correctiveStatus===0){
                              color="blue";
                              correctiveStatus = '通过';
                          }else if(dataV[i].correctiveStatus===1){
                              color="zs";
                              correctiveStatus = '待整改';
                          }else if(dataV[i].correctiveStatus===2){
                              color="yellow";
                              correctiveStatus = '待复检';
                          }else if(dataV[i].correctiveStatus===3){
                              color="red";
                              correctiveStatus = '复检不通过';
                          }else if(dataV[i].correctiveStatus===4){
                              color="green";
                              correctiveStatus = '已整改';
                          }
                          if(dataV[i].operation===0){
                              $list+='<li data-id="'+dataV[i].id+'">\n' +
                                  '                            <div class="team">\n' +
                                  '                                <span class="b-title"><i></i>检查部位</span>\n' +
                                  '                                <p class="b-content projectStructureName">'+(dataV[i].projectStructureName||"无检查部位")+'</p>\n' +
                                  '                                <span class="b-title"><i></i>检查项</span>\n' +
                                  '                                <p class="b-content items">'+(dataV[i].items||"无检查项")+'</p>\n' +
                                  '                                <span class="b-title"><i></i>检查日期</span>\n' +
                                  '                                <p class="b-content inspectionDate">'+(dataV[i].inspectionDate||"---")+'</p>\n' +
                                  '                            </div>\n' +
                                  '                            <div class="person">\n' +
                                  '                                <div class="check_person">\n' +
                                  '                                    <span class="b-title"><i></i>检查人</span>\n' +
                                  '                                    <p class="b-content">'+dataV[i].checkName+'</p>\n' +
                                  '                                </div>\n' +
                                  '                                <div class="duty_person">\n' +
                                  '                                    <span class="b-title"><i></i>责任人</span>\n' +
                                  '                                    <p class="b-content">'+dataV[i].dutyName+'</p>\n' +
                                  '                                </div>\n' +
                                  '                                <div class="type '+color+'">'+correctiveStatus+'</div>\n' +
                                  '                                <i class="line"></i>\n' +
                                  '                            </div>\n' +
                                  '                        </li>'
                          }else  if(dataV[i].operation===1){
                              var safeDom=$(".safe li[data-id='"+dataV[i].id+"']");
                              safeDom.find(".projectStructureName").html(dataV[i].projectStructureName);
                              safeDom.find(".items").html(dataV[i].items);
                              safeDom.find(".inspectionDate").html(dataV[i].inspectionDate);
                              safeDom.find(".check_person .b-content").html(dataV[i].checkName);
                              safeDom.find(".duty_person .b-content").html(dataV[i].dutyName);
                              safeDom.find(".type").html(correctiveStatus).attr('class','type '+color);
                          }

                      }
                      $(".safe .main-box ul").prepend($list);
                      _self.scroll().safe();
                  }

                  setTimeout(function () {
                      safe();
                  },10000)
              }
          })
      }
        safe();
    },
    //得到危险作业
    getDanger:function () {
        var _self =this;
        biyue.ajax({
            url:'/riskOperation/data'
            ,data:{
                constructionId:top.globalData.constructionId
            }
            ,fun:function (data) {
                var dataV=data.value,$list='';
                for(var i in dataV){
                    $list+='  <div class="list-box" data-id="'+dataV[i].id+'">\n' +
                        '                            <div class="c-title">'+dataV[i].title+'</div>\n' +
                        '                            <ul class="c-content">\n' +
                        '                                <li>'+dataV[i].content+'</li>\n' +
                        '                            </ul>\n' +
                        '                        </div>'
                }
                $(".danger-box").html($list);
                _self.scroll().danger();
            }
        })
    },
    //得到安全检查
    getSafe:function () {
        var _self =this;
        biyue.ajax({
            url:'/inspection/listInspection'
            ,data:{
                constructionId:top.globalData.constructionId
            }
            ,fun:function (data) {
                var dataV=data.value,$list='';
                for(var i in dataV){
                    var correctiveStatus = "",color="";
                    if(dataV[i].correctiveStatus===0){
                        color="blue";
                        correctiveStatus = '通过';
                    }else if(dataV[i].correctiveStatus===1){
                        color="zs";
                        correctiveStatus = '待整改';
                    }else if(dataV[i].correctiveStatus===2){
                        color="yellow";
                        correctiveStatus = '待复检';
                    }else if(dataV[i].correctiveStatus===3){
                        color="red";
                        correctiveStatus = '复检不通过';
                    }else if(dataV[i].correctiveStatus===4){
                        color="green";
                        correctiveStatus = '已整改';
                    }
                    $list+='<li data-id="'+dataV[i].id+'">\n' +
                        '                            <div class="team">\n' +
                        '                                <span class="b-title"><i></i>检查部位</span>\n' +
                        '                                <p class="b-content projectStructureName">'+(dataV[i].projectStructureName||"无检查部位")+'</p>\n' +
                        '                                <span class="b-title"><i></i>检查项</span>\n' +
                        '                                <p class="b-content items">'+(dataV[i].items||"无检查项")+'</p>\n' +
                        '                                <span class="b-title"><i></i>检查日期</span>\n' +
                        '                                <p class="b-content inspectionDate">'+(dataV[i].inspectionDate||"---")+'</p>\n' +
                        '                            </div>\n' +
                        '                            <div class="person">\n' +
                        '                                <div class="check_person">\n' +
                        '                                    <span class="b-title"><i></i>检查人</span>\n' +
                        '                                    <p class="b-content">'+dataV[i].checkName+'</p>\n' +
                        '                                </div>\n' +
                        '                                <div class="duty_person">\n' +
                        '                                    <span class="b-title"><i></i>责任人</span>\n' +
                        '                                    <p class="b-content">'+dataV[i].dutyName+'</p>\n' +
                        '                                </div>\n' +
                        '                                <div class="type '+color+'">'+correctiveStatus+'</div>\n' +
                        '                                <i class="line"></i>\n' +
                        '                            </div>\n' +
                        '                        </li>'
                }
                $(".safe .main-box ul").html($list);
                _self.scroll().safe();
            }
        })
    },
    //得到天气
    getWeather:function () {
        var _self = this;
        var weatherImg=[
            ctx+'/resources/static/images/monitor/sun.png'
            ,ctx+'/resources/static/images/monitor/cloud.png'
            ,ctx+'/resources/static/images/monitor/rian.png'
            ,ctx+'/resources/static/images/monitor/snow.png'
            ,ctx+'/resources/static/images/monitor/duan_xian%20.png'
        ];
        biyue.ajax({
            url:'/home/getHomeBaseInfo'
            ,data:{
                constructionId:top.globalData.constructionId
            }
            ,fun:function (data) {
                var dataV=data.value,$list='';
                if((typeof dataV.type)==="number"){
                    $(".weather img").attr("src",weatherImg[dataV.type]);
                    $(".weather span").html(dataV.weather||"--");
                    $(".weather label").html((dataV.temperature===undefined?"--":dataV.temperature)+'℃');
                }else{
                    $(".weather span").html(weatherImg[4]);
                    $(".weather label").html("");
                }
            }
        })
    },
    //全屏
    screen:function () {
        var setTime = "";
        $(window.parent.document).find(".Hui-article-box").css("top",0);
        $(window.parent.document).find(".Hui-header").hide();
        $('body').mousemove(function(e) {
            e = e || window.event;
            var __xx = e.pageX || e.clientX + document.body.scroolLeft;
            var __yy = e.pageY || e.clientY + document.body.scrollTop;
            if(setTime) {clearTimeout(setTime)}
            if(__yy<20){
                $(window.parent.document).find(".Hui-header").show();
                $(window.parent.document).find(".Hui-article-box").css("top","80px");
            }else{
                setTime = setTimeout(function () {
                    $(window.parent.document).find(".Hui-article-box").css("top",0);
                    $(window.parent.document).find(".Hui-header").hide();
                },100)

            }
        });
    },
    //页面大小发生变化时
    resize:function () {
        var res = "",_self=this;
        $(window).resize(function () {
            if(res){clearTimeout(res)}
            res = setTimeout(function () {
                _self.scroll().safe();
                _self.scroll().danger();
            },300)
        })
    },
    //默认执行部分
    default:function () {
        var _self = this;
        _self.time();
        setInterval(function () {
            var date = new Date();
            var hour = date.getHours();
            var minute = date.getMinutes();
            var second = date.getSeconds();
            $(".time span").html(_self.getDate());
            if(hour===0&&minute===0&&second===0){
                _self.time();
                _self.getSafe();
                _self.getDanger();
            }
        },1000);
        _self.screen();
        _self.scroll();
        _self.getSafe();
        _self.getDanger();
        _self.resize();
        _self.getWeather();
        setInterval(function () {
            _self.getWeather();
        },1000*60*60*6);
        setTimeout(function () {
            _self.refresh();
        },60000);
    }

};
