var towerData = function (obj) {
    this.url = obj.url;
};
towerData.prototype = {
    tower: {},//储存所有塔吊数据
    data: function () {
        var self = this;
        biyue.ajax({
            url: self.url,
            data: {
                constructionId: $('[name="site"]').val(),
                areaId: $('[name="area"]').val()
            },
            fun: function (data) {    //提交成功后自动执行的处理函数，参数data就是服务器返回的数据
                $(".siteTitle").remove();
                $(".sectionBox").remove();
                var $list = "",
                    dataA = data.value;
                if(dataA.length>0){
                    $(".noData").hide();
                }else{
                    $(".noData").show();
                }
                for (var i in dataA) {
                    var dataB = dataA[i].data;
                    $list = '<h1 class="siteTitle">' + dataA[i].constructionName + '</h1>';
                    $(".bodyBox>.clearfix").before($list);
                    for (var j in dataB) {
                        $list = '<section class="sectionBox">\n' +
                            '        <div class="towerBox">\n' +
                            '            <h1 class="title titleBox"><i></i><span>' + dataB[j].name + '</span></h1>\n' +
                            '            <div class="tower">\n' +
                            '                <canvas id="myCanvas_' + dataA[i].constructionId + '_' + j + '" width="1000px" height="600px">\n' +
                            '                    你的浏览器不支持canvas,请使用高版本浏览器;\n' +
                            '                </canvas>\n' +
                            '            </div>\n' +
                            '            <div class="towerInfoBox">\n' +
                            '                <div class="timeBox">\n' +
                            '                    <h2 class="titleBox"><i></i><span>数据发送时间</span></h2>\n' +
                            '                    <p>' + dataB[j].time + '</p>\n' +
                            '                </div>\n' +
                            '                <div class="towerInfo">\n' +
                            '                    <h2 class="titleBox"><i></i><span>升降机信息</span><u class='+(dataB[j].warning?"warmImg":"")+'></u></h2>\n' +
                            '                    <ul class="info">\n' +
                            '                        <li><label>速度:</label><span><i class='+(dataB[j].speedWarning?"biyue-red":"")+'>' + dataB[j].speed + '</i>m/s</span></li>\n' +
                            '                        <li><label>载重:</label><span><i class='+(dataB[j].weightWarning?"biyue-red":"")+'>' + dataB[j].weight + '</i>吨 (t)</span></li>\n' +
                            '                        <li><label>高度:</label><span><i class='+(dataB[j].heightWarning?"biyue-red":"")+'>' + dataB[j].height + '</i>米 (m)</span></li>\n' +
                            '                        <li style="border: none"><label>倾斜角度:</label><span><i class='+(dataB[j].obliquityWarning?"biyue-red":"")+'>' + dataB[j].obliquity + '</i>度 (°)</span></li>\n' +
                            '                    </ul>\n' +
                            '                </div>\n' +
                            '            </div>\n' +
                            '        </div>\n' +
                            '    </section>';
                        $(".bodyBox>.clearfix").before($list);
                        self.tower[dataB.constructionName + "_" + j] = new towerAnimate("#myCanvas_" + dataA[i].constructionId + "_" + j);
                        self.tower[dataB.constructionName + "_" + j].init(dataB[j].height);
                    }
                }
            }
        });
    }
};

//塔吊配置
function towerAnimate(id, maxH, maxE) {
    this.c = document.querySelector(id);
    this.maxH = maxH || 80;//塔吊的最大高度（默认：80米）
    this.maxE = maxE || 80;//塔吊的最大幅度（默认：80米）
    this.cxt = this.c.getContext('2d');
    this.bodyImg = new Image();
    this.bodyImg.src = ctx + '/resources/static/images/lift/lift.png';
    this.stockImg = new Image();
    this.stockImg.src = ctx + '/resources/static/images/lift/lift_goods.png';
}
towerAnimate.prototype = {
    index: null, //轮询变量
    dataSwitch: function (y) {
        var self = this;
        if(y !== undefined){
            return {
                 y: parseInt(self.tower.max.y - y * (self.tower.max.y - self.tower.min.y) / self.maxH)
            }
        }else{
            return{
               y: self.tower.init.y
            }
        }
    },
    tower: {
        init: {y: 50}
        , min: {y: 50}
        , max: {y: 320}
        , speed: 8
        , body: {
            x: 250,
            y: 0,
            w: 174,
            h: 470
        }
        , stock: {
            x: 285,
            y: 0,
            w: 104,
            h: 147
        }
        , object: {
            y: 0,
            w: 38,
            h: 53,
            errorDistance: 10
        }
    }
    //图片全部加载完成后执行程序
    , init: function (endY) {
        var imgLoadNum = 0, self = this, xy = self.dataSwitch(endY);
        endY = xy.y;
        self.bodyImg.onload = function () {
            imgLoadNum++;
            if (imgLoadNum === 2) {
                job()
            }
        };
        self.stockImg.onload = function () {
            imgLoadNum++;
            if (imgLoadNum === 2) {
                job()
            }
        };

        function job() {
            self.moveBox(self.tower.init.y);
            self.imgAnimate(endY);
        }
        //修改物品位置
        return function (endY) {
            var xy = self.dataSwitch(endY);
            endY = xy.y;
            self.moveBox(self.tower.init.y);
            self.imgAnimate(endY);
        }
    }
    //打印图片
    , moveBox: function (y) {
        var cxt = this.cxt, c = this.c, bodyImg = this.bodyImg, stockImg = this.stockImg,
            tower = this.tower;
        cxt.clearRect(0, 0, c.offsetWidth, c.offsetHeight);
        cxt.drawImage(bodyImg, tower.body.x, tower.body.y, tower.body.w, tower.body.h);
        cxt.drawImage(stockImg,tower.stock.x,y, tower.stock.w, tower.stock.h);
    }
    //动画
    , imgAnimate: function (endY) {
        var tower = this.tower, self = this;
        var y = tower.init.y, speed = tower.speed;
        self.index = setInterval(function () {
            if (endY > tower.max.y) {
                endY = tower.max.y;
            } else if (endY < tower.min.y) {
                endY = tower.min.y;
            }
            if (y < endY) {
                y = y + 1;
            } else if (y > endY) {
                y = y - 1;
            }
            tower.init.y = y;
            if (y === endY) {
                clearInterval(self.index);
            } else {
                self.moveBox(y);
            }
        }, speed)
    }

};

