var biyueList = {
    roleId:{},
    query:function () {
        return {
            name: $.trim($('[name="name"]').val()),
            cardNumber: $.trim($('[name="cardNumber"]').val()),
            positionWorkId: $('[name="job"]').attr("data-value")||"",
            projectCompanyId: $('[name="company"]').val(),
            constructionId: $('[name="site"]').val(),
            areaId: $('[name="area"]').val()
        }
    },
    createTable: function () {
        var _self = this, table = biyue.tableList(), form = layui.form;
        table.loadTable({
            url:"/person/personList"
            , upData: _self.query()
            , cols: [[
                {type: 'checkbox',fixed: 'left'}
                , {type: 'numbers',fixed: 'left',title:"序号"}
                , {field: 'name', title: '人员名称'}
                , {field: 'cardNumber', title: '卡号'}
                , {field: 'createTime', title: '办卡日期'}
                , {field: 'mobile', title: '手机号码'}
                , {field: 'positionWorkTypeName', title: '岗位/工种'}
                , {field: 'teamName', title: '班组'}
                , {field: 'projectCompanyName', title: '所属公司',width:180}
                , {field: 'terrName', title: '所属区域',width:100}
                , {field: 'constructionName', title: '工地名称',width:180}
                , {fixed: 'right', title:'操作',width:220, align:'center', toolbar: '#barDemo'}
            ]]
            , done:function (res, curr, count) {
                done(res, curr, count)
            }
            , height: 'full-90'
        });
        //搜索
        $("#search").on('click', function () {
            searchIf();
        });
        window.searchIf = function () {
            table.search({
                where: _self.query()
                , done:function (res, curr, count) {
                    done(res, curr, count)
                }
            })
        };
        //加载完后执行事件
        function done(res, curr, count) {
            $("[lay-filter=\"layTableAllChoose\"]").parent().html("");
            var dataV = res.value;
            for(var j in dataV){
                if(dataV[j].userId){
                    var id = dataV[j].id;
                    $(".layui-table-fixed-l .layui-table-body tr[data-index='"+j+"'] [type=\"checkbox\"]").attr("disabled","");
                }
            }
            for(var i in _self.roleId){
                if(_self.roleId[i]){
                    var index = $("[data-id='"+i+"']").parents("tr").attr("data-index");
                    $(".layui-table-fixed-l .layui-table-body tr[data-index='"+index+"'] .layui-form-checkbox").click();
                }
            }
            form.render('checkbox');
        }
        //编辑
        layui.table.on('tool(table-list)', function(obj){
            var data = obj.data
                ,layEvent = obj.event;
            if(layEvent==="edit"){
                biyue.open({
                    title:'编辑',
                    url:'/person/toAdd?type=edit&id='+data.id
                })
            }else if(layEvent==="view"){
                biyue.open({
                    title:'查看',
                    url:'/person/toView?type=view&id='+data.id
                })
            }else if(layEvent==="del"){
                layer.confirm('是否删除？', {icon: 2}, function(index){
                    biyue.ajax({
                        url:'/person/delPerson?personId=' + data.id+'&constructionId='+data.constructionId+'&clientUserId='+(data.clientUserId||"")+'&personName='+encodeURI(encodeURI(data.name)),
                        fun:function (data) {
                            layer.msg('删除成功!', {icon: 1});
                            searchIf();
                        }
                    })
                });
            }else if(layEvent==="allotDoor"){
                biyue.open({
                    title:'分配门',
                    url:'/door/toAllotDoor?type=allotDoor&id='+data.id
                })
            }
        })
    },
    getCompany:function (id) {
        var form = layui.form;
        //获取合作方
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
    toolsBtn: function () {
        var _self = this , table = biyue.tableList();
        //新增
        $("#add_link").click(function () {
            biyue.open({
                title:'新增',
                url:'/person/toAdd'
            })
        });
        //生成用户
        $("#createUser").click(function () {
            var ids=[];
            for(var i in _self.roleId){
                ids.push(i);
            }
            if(ids.length==0){
                layer.msg('请勾选列表左边的选项框后再进行操作！', {time: 2000, icon: 0});
                return;
            }
            layer.confirm('是否生成用户？<br/>账号默认为"手机号"，密码默认为"123456"。<br/>(如无手机号，账号则默认为"卡号"！)', function(index){
                biyue.ajax({
                    url:'/person/generateUser',
                    data:{ids:ids},
                    traditional:true,
                    fun:function (data) {
                        layer.msg('生成用户成功！!', {icon: 1});
                        searchIf();
                    }
                })
            });
        });
        //导入
        $("#import_link").click(function () {
            biyue.open({
                title:'导入',
                url:'/person/toImport'
            })
        });
        //导出
        $("#export_link").click(function () {
            biyue.ajax({
                url:'/person/exportExcel',
                data:_self.query(),
                fun:function (data) {
                    var filePath = data.value;
                    window.location.href = path+'/'+ filePath;
                }
            })
        });
        //下载模版
        $("#template_link").click(function () {
            window.location.href = path+'/resources/static/download/person_template.xls';
        });
        //列表多选框选择事件
        layui.table.on('checkbox()', function (obj) {
            if(obj.type==='one'){
                if(obj.checked){
                    _self.roleId[obj.data.id]=true;
                }else{
                    _self.roleId[obj.data.id]=false;
                }
            }else {
                var checkStatus = layui.table.checkStatus("id");
                if(checkStatus.isAll){
                    for(var i =0;i<checkStatus.data.length;i++){
                        _self.roleId[checkStatus.data[i].id]=checkStatus.isAll;
                    }
                }else{
                    $(".layui-table [data-id]").each(function () {
                        var id = $(this).attr("data-id");
                        _self.roleId[id]=checkStatus.isAll;
                    })
                }

            }
        });
    }
};