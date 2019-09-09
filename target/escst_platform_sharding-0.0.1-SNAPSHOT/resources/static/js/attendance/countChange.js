var biyueEdit = {
    updata: function () {
        var form = layui.form;
        var _self = this;
        form.on('submit(add)', function (data) {
            var msg = "",url = "";
            url = "/attendaceCountController/update";
            msg = '编辑成功！';
            biyue.ajax({
                url: url
                , data: $("[lay-filter=\"biyueOpen\"]").serialize()
                , fun: function (data) {
                    parent.layer.msg(msg, {time: 2000, icon: 1});
                    biyue.layui_close();
                }
            });
            return false;
        })
    },
    getTeam: function (id) {
        var form = layui.form;
        biyue.ajax({
            url: '/team/getDefaultTeamName',
            fun: function (data) {
                var $list = "<option value=\"\">选择公司</option>"
                    , dataV = data.value;
                for (var i = 0; i < dataV.length; i++) {
                    if (dataV[i] === id) {
                        $list += '<option value="' + dataV[i] + '" selected>' + dataV[i] + '</option>';
                    } else {
                        $list += '<option value="' + dataV[i] + '">' + dataV[i] + '</option>';
                    }
                }
                $("[name='teamName']").html($list);
                form.render('select');
            }
        })
    },
    event:function () {
        var laydate = layui.laydate, form = layui.form;
        laydate.render({
            elem: '[name="attendanceDate"]'
        });
    }
};