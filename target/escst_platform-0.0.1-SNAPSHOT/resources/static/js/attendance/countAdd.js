//数据提交
var biyueAdd = {
    updata: function () {
        var form = layui.form;
        var _self = this, searchObj = biyue.urlSearch(), upData = {};
        form.on('submit(add)', function (data) {
            var msg = "", url = "";
            if (searchObj.type === 'edit') {

            } else {
                url = '/attendaceCountController/saveOrUpdate';
                msg = '新增成功！';
                upData.constructionId = $('[name="site"]').val();
                upData.constructionName = $("[name=\"site\"]  option:selected").text();
                upData.attendanceDate = $('[name="attendanceDate"]').val();
                upData.teamNameArray = [];
                upData.countArray = [];
                $(".channel tbody tr").each(function () {
                    var name = $(this).find("td").eq(1).html(),
                        num = $(this).find("[name=\"num\"]").val();
                    upData.teamNameArray.push(name);
                    upData.countArray.push(num);
                });
                if (upData.teamNameArray.length === 0) {
                    layer.msg("请选择班组!", {time: 2000, icon: 5});
                    return false;
                }
            }
            biyue.ajax({
                url: url
                , data: JSON.stringify(upData)
                , contentType: 'application/json'
                , fun: function (data) {
                    parent.layer.msg(msg, {time: 2000, icon: 1});
                    biyue.layui_close();
                }
            })
            return false;
        })
    },
    getTeamModel: function (id) {
        var form = layui.form;
        biyue.ajax({
            url: '/team/getDefaultTeamName',
            fun: function (data) {
                var dataZ = [];
                for (var i in data.value) {
                    dataZ[i] = {
                        id: i,
                        name: data.value[i]
                    }
                }
                $.fn.zTree.init($("#itemsZtree"), biyue.ztreeSet({
                    check: true,
                    click: function (e, treeId, treeNode, clickFlag) {
                        $.fn.zTree.getZTreeObj("itemsZtree").checkNode(treeNode, !treeNode.checked, true);
                    }
                }), dataZ);
            }
        })
    },
    addList: function () {
        var form = layui.form, _self = this;
        //添加班组
        $("#add-channel").click(function () {
            _self.createTeamList()
        });
        //删除
        $(".channel").on("click", '[lay-event="del"]', function () {
            var id = $(this).parents("tr").attr("data-id");
            var treeObj = $.fn.zTree.getZTreeObj("itemsZtree");
            var node = treeObj.getNodeByTId(id);
            treeObj.checkNode(node,false);
            $(this).parents("tr").remove();
            $(".channel tbody tr").each(function (index) {
                $(this).find("td").eq(0).html(index + 1);
            })
        });
        //选择班组模板
        $("#add-model").on("click", function () {
            var treeObj = $.fn.zTree.getZTreeObj("itemsZtree");
            my_open = layer.open({
                type: 1,
                anim: 5,
                title: '选择班组',
                content: $('#check_result_tree'),
                area: 'auto',
                offset: 'r',
                btn: ['确定', '关闭'],
                btnAlign: 'r',
                shade: false,
                yes: function () {
                    $(".channel tbody").html("");
                    var nodes = treeObj.getCheckedNodes(true);
                    for (var i = 0; i < nodes.length; i++) {
                        _self.createTeamList(nodes[i].name,nodes[i].tId)
                    }
                    layer.closeAll();
                },
                end: function () {
                    $("#check_result_tree").hide();
                },
                cancel: function () {
                    $("#check_result_tree").hide();
                }
            });
            $("#check_result_tree").show();
        })

    },
    createTeamList: function (data,id) {
        var form = layui.form;
        var len = $(".channel tbody tr").length;
        data = data || "";
        var $list = ' <tr data-id="'+id+'">\n' +
            '            <td>' + (len + 1) + '</td>\n' +
            '            <td>' + data + '</td>\n' +
            '            <td><input type="text" lay-verify="required|number" class="layui-input" style="width: 100%;height: 26px;line-height: 26px;" name="num"></td>\n' +
            '            <td><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a></td>\n' +
            '        </tr>';
        $(".channel tbody").append($list);
        form.render("radio");
    },
    event: function () {
        var laydate = layui.laydate, form = layui.form;
        laydate.render({
            value: new Date(),
            elem: '[name="attendanceDate"]'
        });
    }
};
