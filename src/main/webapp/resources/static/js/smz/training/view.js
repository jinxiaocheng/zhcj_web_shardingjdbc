var smzTraining={
    createTable:function () {
        var _self = this ;
        var url = ctx+"/train/listTrainRecordPerson";
        biyue.jqTable({
            url:url,
            postData:{
                recordID:biyue.urlSearch().id
            },
            tableJson:[
                {'label': 'id', 'name': 'id', 'index': 'id', 'hidden': true},
                {'label': '姓名', 'name': 'personName', 'index': 'personName','width':'260px'},
                {'label': '成绩', 'name': 'sGrade', 'index': 'sGrade','width':'150px',formatter:function(cellvalue, options, rowObject){
                    var $list="";
                    if(cellvalue==-1){
                        $list='<span style="">无考试</span>'
                    }else{
                        $list='<span style="">'+cellvalue+'</span>'
                    }
                    return $list
                }},
                {'label': '身份证号', 'name': 'identifyID', 'index': 'identifyID','width':'200px'},
                {'label': '单位', 'name': 'trainDepart', 'index': 'trainDepart','width':'200px'},
                {'label': '是否合格', 'name': 'isPass', 'index': 'isPass','width':'100px',formatter:function(cellvalue, options, rowObject){
                    var $list="";
                    if(cellvalue==1){
                        $list='<span style="color: green">合格</span>'
                    }else{
                        $list='<span style="color: red">不合格</span>'
                    }
                    return $list
                }}
            ]
        });}
}