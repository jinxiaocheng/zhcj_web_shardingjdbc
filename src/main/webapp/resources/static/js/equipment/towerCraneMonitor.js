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
                            '                    <h2 class="titleBox"><i></i><span>塔吊信息</span><u class='+(dataB[j].warning?"warmImg":"")+'></u></h2>\n' +
                            '                    <ul class="info">\n' +
                            '                        <li><label>风速:</label><span><i class='+(dataB[j].windSpeedWarning?"biyue-red":"")+'>' + dataB[j].windSpeed + '</i>m/s</span></li>\n' +
                            '                        <li><label>力矩百分比:</label><span><i class='+(dataB[j].percentWarning?"biyue-red":"")+'>' + dataB[j].percent + '</i>%</span></li>\n' +
                            '                        <li><label>载重:</label><span><i class='+(dataB[j].weightWarning?"biyue-red":"")+'>' + dataB[j].weight + '</i>吨 (t)</span></li>\n' +
                            '                        <li><label>幅度:</label><span><i class='+(dataB[j].extentWarning?"biyue-red":"")+'>' + dataB[j].extent + '</i>米 (m)</span></li>\n' +
                            '                        <li><label>高度:</label><span><i class='+(dataB[j].heightWarning?"biyue-red":"")+'>' + dataB[j].height + '</i>米 (m)</span></li>\n' +
                            '                        <li><label>回旋角度:</label><span><i class='+(dataB[j].angleWarning?"biyue-red":"")+'>' + dataB[j].angle + '</i>度 (°)</span></li>\n' +
                            '                        <li style="border: none"><label>倾斜角度:</label><span><i class='+(dataB[j].obliquityWarning?"biyue-red":"")+'>' + dataB[j].obliquity + '</i>度 (°)</span></li>\n' +
                            '                    </ul>\n' +
                            '                </div>\n' +
                            '            </div>\n' +
                            '        </div>\n' +
                            '    </section>';
                        $(".bodyBox>.clearfix").before($list);
                        self.tower[dataB.constructionName + "_" + j] = new towerAnimate("#myCanvas_" + dataA[i].constructionId + "_" + j);
                        self.tower[dataB.constructionName + "_" + j].init(dataB[j].extent, dataB[j].height);
                    }
                }
            }
        });
    }
};

//塔吊配置
function towerAnimate(id, maxH, maxE) {
    this.c = document.querySelector(id);
    this.maxH = maxH || 60;//塔吊的最大高度（默认：60米）
    this.maxE = maxE || 60;//塔吊的最大幅度（默认：60米）
    this.cxt = this.c.getContext('2d');
    this.bodyImg = new Image();
    this.bodyImg.src = ctx + '/resources/static/images/tower/body.png';
    this.objectImg = new Image();
    this.objectImg.src = ctx + '/resources/static/images/tower/object.png';
    this.stockImg = new Image();
    this.stockImg.src = ctx + '/resources/static/images/tower/stock.png';
}
towerAnimate.prototype = {
    index: null, //轮询变量
    dataSwitch: function (x, y) {
        var self = this;
        if(x&&y){
            return {
                x: parseInt(x * (self.tower.max.x - self.tower.min.x) / self.maxE + self.tower.min.x)
                , y: parseInt(self.tower.max.y - y * (self.tower.max.y - self.tower.min.y) / self.maxH)
            }
        }else{
            return{
                x: self.tower.init.x
                , y: self.tower.init.y
            }
        }
    },
    tower: {
        init: {x: 202, y: 160}
        , min: {x: 202, y: 160}
        , max: {x: 500, y: 440}
        , speed: 8
        , body: {
            x: 0,
            y: 0,
            w: 604,
            h: 494
        }
        , stock: {
            x: 0,
            y: 144,
            w: 57,
            h: 10
        }
        , object: {
            x: 0,
            y: 0,
            w: 38,
            h: 53,
            errorDistance: 10
        }
    }
    //图片全部加载完成后执行程序
    , init: function (endX, endY) {
        var imgLoadNum = 0, self = this, xy = self.dataSwitch(endX, endY);
        endX = xy.x;
        endY = xy.y;
        self.bodyImg.onload = function () {
            imgLoadNum++;
            if (imgLoadNum === 3) {
                job()
            }
        };
        self.objectImg.onload = function () {
            imgLoadNum++;
            if (imgLoadNum === 3) {
                job()
            }
        };
        self.stockImg.onload = function () {
            imgLoadNum++;
            if (imgLoadNum === 3) {
                job()
            }
        };

        function job() {
            self.moveBox(self.tower.init.x, self.tower.init.y);
            self.imgAnimate(endX, endY);
        }
        //修改物品位置
        return function (endX, endY) {
            var xy = self.dataSwitch(endX, endY);
            endX = xy.x;
            endY = xy.y;
            self.moveBox(self.tower.init.x, self.tower.init.y);
            self.imgAnimate(endX, endY);
        }
    }
    //打印图片
    , moveBox: function (x, y) {
        var cxt = this.cxt, c = this.c, bodyImg = this.bodyImg, stockImg = this.stockImg, objectImg = this.objectImg,
            tower = this.tower;
        cxt.clearRect(0, 0, c.offsetWidth, c.offsetHeight);
        cxt.drawImage(bodyImg, tower.body.x, tower.body.y, tower.body.w, tower.body.h);
        cxt.drawImage(stockImg, x, tower.stock.y, tower.stock.w, tower.stock.h);
        cxt.drawImage(objectImg, x + tower.object.errorDistance, y, tower.object.w, tower.object.h);
        //第一条线
        cxt.beginPath();
        cxt.strokeStyle = "#bbbbbb";
        cxt.shadowOffsetX = 1;
        cxt.shadowOffsetY = 1;
        cxt.shadowBlur = 1;
        cxt.shadowColor = "rgba(0,0,0,0.5)";
        cxt.moveTo(x + 18, tower.stock.y + 9);
        cxt.lineTo(x + 18, y + 6);
        cxt.stroke();
        //第二条线
        cxt.moveTo(x + 35, tower.stock.y + 9);
        cxt.lineTo(x + 35, y + 6);
        cxt.stroke();
        cxt.closePath();

    }
    //动画
    , imgAnimate: function (endX, endY) {
        var tower = this.tower, self = this;
        var x = tower.init.x, y = tower.init.y, speed = tower.speed;
        self.index = setInterval(function () {
            if (endX > tower.max.x) {
                endX = tower.max.x;
            } else if (endX < tower.min.x) {
                endX = tower.min.x;
            }
            if (endY > tower.max.y + 50) {
                endY = tower.max.y + 50;
            } else if (endY < tower.min.y) {
                endY = tower.min.y;
            }
            if (x < endX) {
                x = x + 1;
            } else if (x > endX) {
                x = x - 1;
            }
            if (y < endY) {
                y = y + 1;
            } else if (y > endY) {
                y = y - 1;
            }
            tower.init.x = x;
            tower.init.y = y;
            if (x === endX && y === endY) {
                clearInterval(self.index);
            } else {
                self.moveBox(x, y);
            }
        }, speed)
    }

};

