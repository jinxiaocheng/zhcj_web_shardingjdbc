//数据提交
var biyueAdd = {
    updata: function () {
        var form = layui.form;
        var _self = this, searchObj = biyue.urlSearch(), upData = {};
        form.on('submit(add)', function (data) {
            var msg = "", url = "";
            if (searchObj.type === 'edit') {
                url = "/team/edit";
                msg = '编辑成功！';
                upData.id = searchObj.id;
                upData.constructionId = $('[name="constructionId"]').val();
                upData.constructionName = $("[name=\"constructionName\"]").val();
                upData.name = $('[name="name"]').val();
                biyue.ajax({
                    url: url
                    , data: upData
                    , fun: function (data) {
                        parent.layer.msg(msg, {time: 2000, icon: 1});
                        biyue.layui_close();
                    }
                })
            } else {
                url = '/team/batchAddTeam';
                msg = '新增成功！';
                upData.constructionId = $('[name="site"]').val();
                upData.constructionName = $("[name=\"site\"]  option:selected").text();
                upData.teamNames = [];
                $(".channel [name=\"name\"]").each(function () {
                    upData.teamNames.push($(this).val());
                });
                if(upData.teamNames.length===0){
                    layer.msg("请新增班组!", {time: 2000, icon: 5});
                    return false;
                }
                upData.projectCompanyId = $("[name=\"company\"]").val();
                biyue.ajax({
                    url: url
                    , data: JSON.stringify(upData)
                    , contentType: 'application/json'
                    , fun: function (data) {
                        parent.layer.msg(msg, {time: 2000, icon: 1});
                        biyue.layui_close();
                    }
                })
            }
            return false;
        })
    },
    getCompany: function (id) {
        var form = layui.form;
        biyue.ajax({
            url: '/team/getProjectCompanyList',
            data: {
                constructionId: id
            },
            fun: function (data) {
                var $list = "<option value=\"\">选择公司</option>"
                    , dataV = data.value;

                for (var i = 0; i < dataV.length; i++) {
                    $list += '<option value="' + dataV[i].id + '">' + dataV[i].name + '</option>';
                }

                $("[name='company']").html($list);
                form.render('select');
            }
        })
    },
    getTeamModel: function (id) {
        var form = layui.form;
        //获取合作方
        biyue.ajax({
            url: '/team/getDefaultTeamList',
            data: {
                constructionId: id
            },
            fun: function (data) {
                var dataZ = [];
                for (var i in data.value) {
                    dataZ[i] = {
                        id: data.value[i].positionWorkType,
                        name: data.value[i].name
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
            $(this).parents("tr").remove();
            $(".channel tbody tr").each(function (index) {
                $(this).find("td").eq(0).html(index + 1);
            })
        });
        //选择班组模板
        $("#add-model").on("click", function () {
            treeObj = $.fn.zTree.getZTreeObj("itemsZtree");
            treeObj.checkAllNodes(false);
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
                    var nodes = treeObj.getCheckedNodes(true);
                    for (var i = 0; i < nodes.length; i++) {
                        _self.createTeamList(nodes[i].name)
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
    createTeamList: function (data) {
        var form = layui.form;
        var len = $(".channel tbody tr").length;
        data = data || "";
        var $list = ' <tr>\n' +
            '            <td>' + (len + 1) + '</td>\n' +
            '            <td><input type="text" value="' + data + '" lay-verify="required" class="layui-input" style="width: 100%;height: 26px;line-height: 26px;" name="name"></td>\n' +
            '            <td><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a></td>\n' +
            '        </tr>';
        $(".channel tbody").append($list);
        form.render("radio");
    }
};
