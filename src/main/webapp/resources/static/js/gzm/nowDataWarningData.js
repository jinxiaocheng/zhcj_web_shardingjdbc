var biyueList = {
    createTable: function () {
        var _self = this, table = biyue.tableList();
        var type = biyue.urlSearch().type,warning=0;
        if(type === "warningData"){
            warning=1;
        }
        table.loadTable({
            url:'/warning/listData'
            , upData: {
                startTime: $('[name="startTime"]').val(),
                endTime: $('[name="endTime"]').val(),
                type: $('[name="type"]').val(),
                equipmentId: $('[name="survey"]').val(),
                flowId: biyue.urlSearch().flowId,
                warning:warning
            }
            , cols: [[
                {type: 'numbers'}
                , {field: 'equipmentName', title: '测点名称'}
                , {field: 'number', title: '测点编号'}
                , {field: 'createTime', title: '测量时间',width:150}
                , {field: 'xAngle', title: 'X倾角(°)', templet: '#xAngle'}
                , {field: 'yAngle', title: 'Y倾角(°)', templet: '#yAngle'}
                , {field: 'kpa', title: '压力(KN)', templet: '#kpa'}
                , {field: 'displace', title: '位移(mm)', templet: '#displace'}
                , {field: 'electric', title: '可用电量(%)', templet: '#electric'}
                , {field: 'temperature', title: '温度(℃)', templet: '#temperature'}
            ]]
            , height: 'full-90'
        });
        //搜索
        $("#search").on('click', function () {
            searchIf();
        });
        window.searchIf = function () {
            table.search({
                where: {
                    startTime: $('[name="startTime"]').val(),
                    endTime: $('[name="endTime"]').val(),
                    type: $('[name="type"]').val(),
                    equipmentId: $('[name="survey"]').val()
                }
            })
        };

        //导出
        $("#export_link").click(function () {
            window.location.href = ctx + '/warning/exportExcel?flowId='+biyue.urlSearch().flowId +
                '&startTime='+$('[name="startTime"]').val()+
                '&endTime='+$('[name="endTime"]').val()+
                '&equipmentId='+$('[name="survey"]').val() +
                '&type='+$('[name="type"]').val();
        });
    },
    getTime:function (fun) {
        //获取测点
        biyue.ajax({
            url:'/warning/getTime',
            data: {
                flowId: biyue.urlSearch().flowId
            },
            fun: function (data) {
                fun&&fun(data);
            }
        })
    },
    survey: function (fun) {
        var form = layui.form;
        //获取测点
        biyue.ajax({
            url:'/warning/listMeasurePoint',
            data: {
                flowId: biyue.urlSearch().flowId
            },
            fun: function (data) {
                var $list = "<option value=\"\">选择测点</option>"
                    , dataV = data.value;

                for (var i = 0; i < dataV.length; i++) {
                    $list += '<option value="' + dataV[i].equipmentId + '">' + dataV[i].equipmentName + '</option>';
                }

                $("[name='survey']").html($list);
                form.render('select');
            }
        })
    }
};