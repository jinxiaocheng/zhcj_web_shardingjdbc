var upload = layui.upload;

//数据提交
var biyueAdd={
    updata:function () {
        var form = layui.form;
        var _self = this,searchObj=biyue.urlSearch(),upData={};
        form.on('submit(add)', function(data){
            var beanList = [];
            $(".channel tbody tr").each(function () {
                var number = $(this).find('[name="channelNum"]').val();
                var name = $(this).find('[name="channelName"]').val();
                beanList.push({
                    number:number,
                    name:name
                })
            });
            if(beanList.length===0){
                layer.msg('请新增采集仪!',{time: 1000, icon:2})
                return;
            }
            biyue.ajax({
                url:'/collector/save',
                data:JSON.stringify({
                    vos:beanList,
                    constructionId:$('[name="site"]').val(),
                    userId:userId
                }),
                contentType : "application/json",
                fun:function (data) {
                    biyue.layui_close();
                }
            })
            return false;
        })
    },
    getData:function (fun) {
        var objSearch=biyue.urlSearch();
        biyue.ajax({
            url:"/project/getProjectDetail",
            data:JSON.stringify({
                id:objSearch.id
            }),
            contentType:'application/json',
            fun:function (data) {
                var dataV = data.value;
                for(var i in dataV){
                    if(i==="personChartPath"){
                        $("#demo1").attr("src",dataV[i]);
                    }else{
                        $('[name="'+i+'"]').val(dataV[i]);
                    }

                }
                fun&&fun();
            }
        })
    },
    addList:function () {
        var form = layui.form;
        //添加采集仪
        $("#add-channel").click(function () {
            var len = $(".channel tbody tr").length;
            var $list = ' <tr>\n' +
                '            <td>'+(len+1)+'</td>\n' +
                '            <td><input type="text" lay-verify="required" class="layui-input" style="width: 100%;height: 26px;line-height: 26px;" name="channelName"></td>\n' +
                '            <td><input type="text" lay-verify="required" class="layui-input" style="width: 100%;height: 26px;line-height: 26px;" name="channelNum"></td>\n' +
                '            <td><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a></td>\n' +
                '        </tr>';
            $(".channel tbody").append($list);
            form.render("radio");
        })
        //删除
        $(".channel").on("click",'[lay-event="del"]',function () {
            $(this).parents("tr").remove();
            $(".channel tbody tr").each(function (index) {
                $(this).find("td").eq(0).html(index+1);
                $(this).find('input[type="radio"]').eq(0).attr("name","yt_"+(index+1));
                $(this).find('input[type="radio"]').eq(1).attr("name","yt_"+(index+1));
            })
        })

    }
};
