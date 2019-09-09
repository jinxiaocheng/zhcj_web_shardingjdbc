<%--
  Created by IntelliJ IDEA.
  User: dwj
  Date: 2017/3/18
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人员结构----=---</title>
</head>

<body>
<script>
    var ctx = "${ctx}";
</script>
<script type="text/javascript" src="${ctx}/resources/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${ctx}/resources/static/chart/echarts.js" type="text/javascript"></script>
<script src="${ctx}/resources/static/chart/chart.js" type="text/javascript"></script>
<div>
     <div id="mychart" style="width: 30%;height:400px;float:left;text-align: center "></div>
    <div id="main" style="width: 30%;height:400px; float:left;text-align: center "></div>
    <div id="test" style="width: 30%;height:400px;float:left ;text-align: center "></div>
 </div>
<script type="text/javascript">
    $(function(){
        function createChart(){
            var data=[{value:335, name:'木工'},
                      {value:310, name:'钢筋工'},
                      {value:234, name:'水泥工'},
                      {value:135, name:'油漆工'},
                      {value:1548, name:'水电工'}];
            var title='人员结构图';
              createPie(title,data);
        }
        createChart();
    });

</script>

</body>
</html>
