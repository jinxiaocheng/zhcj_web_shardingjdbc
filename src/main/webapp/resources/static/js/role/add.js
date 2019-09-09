var userAdd=function(){};
userAdd.prototype={
  getData:function(obj){
      var index=layer.load(),self=this;
      $.ajax({
          type: obj.type||'post',
          data:obj.data||{},
          dataType : "json",
          url:obj.url,
          error: function () {
              layer.close(index);
              layer.alert('数据请求失败!');
          },
          success:function(data){
              layer.close(index);
              if(data.status==1){
                  var dataV=null;
                  var dataS=null;
                  if(obj.status==="edit"){
                      dataV=data.value.allList;
                      dataS=data.value.ownList;
                  }else{
                      dataV=data.value;
                  }
                  dataV=top.convert(dataV);
                  self.createTable(dataV);
                  self.checkBox();
                  self.selectData(dataS);
                  var type =getSearch().type;
                  if(type==='view'){
                      $('footer').hide();
                      $('input[type="checkbox"]').attr('disabled','true');
                  }
              }else{
                  layer.alert(data.msg)
              }
          }
      });
  }
  ,createTable:function(data){
      var self=this,dataF=data[0].children,dataS=data[1].children;
      var $list=self.createTableList(dataF,'web')
          ,$listApp=self.createTableList(dataS,'app');
      $("#tableBox tbody").html($list);
      $("#tableBoxApp tbody").html($listApp);
  }
  ,createTableList:function(dataF,name){  //表格创建规则
      var $list="",self=this;
      for(var i in dataF){
            var dataS=dataF[i].children;
            if(!dataS){
                var $powerList=self.switch(dataF[i],i,0,name);
                $list+='<tr data-index="'+i+'">\n'+
                    '<td data-index="1" data-sortNum="'+dataF[i].sortNum+'" data-value="'+dataF[i].id+'"><div><input type="checkbox" id="'+(name+'_'+i)+'" ><label for="'+(name+'_'+i)+'">'+dataF[i].name+'</label></div></td><td data-index="2"><div></div></td>' +
                    '<td data-index="3" data-value="'+dataF[i].id+'"><div>'+$powerList+'</div></td></tr>';
            }else{
                var rowspan=dataS.length+1;
                $list+='<tr><td data-index="1" data-sortNum="'+dataF[i].sortNum+'" data-value="'+dataF[i].id+'" rowspan="'+rowspan+'"><div><input type="checkbox" id="'+(name+'_'+i)+'" ><label for="'+(name+'_'+i)+'">'+dataF[i].name+'</label></div></td></tr>';
                for(var j in dataS){
                    var $powerList=self.switch(dataS[j],i,j,name);
                    $list+='<tr><td data-index="2" data-sortNum="'+dataS[j].sortNum+'" data-value="'+dataS[j].id+'"><div><input type="checkbox" id="'+(name+'_'+i+'_'+j)+'"><label for="'+(name+'_'+i+'_'+j)+'">'+dataS[j].name+'</label></div></div></td>'
                        +'<td data-index="3" data-value="'+dataS[j].id+'"><div>'+$powerList+'</div></td></tr>';
                }
            }
        };
      return $list;
  }
  ,switch:function (data,i,j,name) {
      if(data.operationAuthority){
          var powerList=data.operationAuthority.split(','),$powerList="";
          for(var k in powerList){
              if(powerList[k]&&powerList[k]!=" "){
                  $powerList+='<p><input type="checkbox" id="'+(name+'_'+i+'_'+j+'_'+powerList[k])+'"><label for="'+(name+'_'+i+'_'+j+'_'+powerList[k])+'">'+top.powerText(powerList[k])+'</label></p>';
              }else{
                  $powerList+="";
              }
          }
      }else{
          var $powerList="";
      }
     return $powerList;
  }
  ,checkBox:function () {  //多选框选择公式
    $(".body_bg").on('click','input[type="checkbox"]',function () {
        var id=$(this).attr('id');
        var bool=$(this).is(':checked');
        var ids=id.split("_"),len=ids.length;
        $('input[type="checkbox"][id^="'+id+'_"]').prop('checked',bool);
        if(len===4){
            $("#"+ids[0]+"_"+ids[1]).prop('checked',true);
            $("#"+ids[0]+"_"+ids[1]+"_"+ids[2]).prop('checked',true);
        }
        if(len===3){
            $("#"+ids[0]+"_"+ids[1]).prop('checked',true);
        }
    })
  }
  ,sendData:function (obj) {
    $("#submit").click(function(){
        var menuAuthMap={};
        var name =$("#name").val();
        if(name==""){
            layer.msg('请填写名称！', {icon: 2,shift: 6,time:1500});
            return;
        }
        var index=layer.load();
        $('td[data-index="1"] input:checked').each(function () {
            var id=$(this).parents('td[data-index="1"]').attr('data-value');
            var sortNum=$(this).parents('td[data-index="1"]').attr('data-sortNum');
            var name = $(this).parents('td[data-index="1"]').find("label").html();
            menuAuthMap[id]={
                sortNum:sortNum,
                name:name,
                id:id
            };
        });
        $('td[data-index="2"] input:checked').each(function () {
            var id=$(this).parents('td[data-index="2"]').attr('data-value');
            var sortNum=$(this).parents('td[data-index="2"]').attr('data-sortNum');
            var name = $(this).parents('td[data-index="2"]').find("label").html();
            menuAuthMap[id]={
                sortNum:sortNum,
                name:name,
                id:id
            };
        });
        $('td[data-index="3"]').each(function () {
            var $list=[];
            $(this).find('input:checked').each(function () {
                $list.push($(this).attr("id").split("_")[3]);
            });
            if($list.length>0){
                var id=$(this).attr('data-value');
                menuAuthMap[id].operationAuthority=$list.join(",");
            }
        });

        var menuAuthMapData = [];
        //默认加上平台与app菜单
        menuAuthMapData.push({
                "id": "1",
                "name": "平台菜单",
                "operationAuthority": "",
                "sortNum": ""
            }
        )
        menuAuthMapData.push({
                "id": "2",
                "name": "APP菜单",
                "operationAuthority": "",
                "sortNum": ""
            }
        )
        // menuAuthMap['1']="";
        // menuAuthMap['2']="";
        for(var i in menuAuthMap){
            menuAuthMapData.push(menuAuthMap[i]);
        }
        var upData={
            orgId:orgId
            ,name:name
            ,menuAuthMap:JSON.stringify(menuAuthMapData)
        };
        if(obj.data){
            upData=obj.data(menuAuthMapData)
        }
        $.ajax({
            type: 'post',
            data:upData,
            dataType : "json",
            url: obj.url,
            error: function () {
                layer.close(index);
                layer.alert('数据提交失败!');
            },
            success:function(data){
                layer.close(index);
                if(data.status==1){
                    layer.alert(obj.successText,function () {
                        layer_close(obj.refreshUrl);
                    })
                }else{
                    layer.alert(data.msg)
                }
            }
        });
    })
  }
  ,selectData:function (data) { //--选中已有的权限
      if(!data) return;
      var self=this;
        for(var i=0,len=data.length;i<len;i++){
            var operationAuthority=data[i].operationAuthority;
            var id=data[i].id;
            $('[data-value="'+id+'"][data-index="1"] input').prop('checked',true);
            $('[data-value="'+id+'"][data-index="2"] input').prop('checked',true);
            if(operationAuthority){
                var power=operationAuthority.split(',');
                for(var j in power){
                    $('[data-value="'+id+'"]').find('[id$="'+power[j]+'"]').prop('checked',true);
                }
            }
        }
  }
};

function layer_close(url) {
    var url =url;
    parent.jQuery("#gridTable").jqGrid('setGridParam', {url: url}).trigger("reloadGrid");
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
};


//获取路径后的参数
function getSearch() {
    var search=window.location.search.replace("?","").split("&");
    var searchObj={};
    for(var i in search){
        searchObj[search[i].split("=")[0]]=search[i].split("=")[1];
    }
    return searchObj;
}






