var kk_ztree={},setting={};
//属性过滤
function filter(before,after) {
    return before?before:after
}

//设置树形图属性
function ztree_set() {
    setting = {
        view: {
            showLine: true,
            selectedMulti: false //不可多选
        },
        data: {
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        },
        async:{
            autoParam:["id=catalogID"],
            otherParam:["type","M"],
            enable:true,
            dataType:"json",
            type:"get",
            url:"${ctx}/document/queryCatalogTree"
        },
        check: {
            enable: true,
            chkStyle: "checkbox",
            chkboxType: { "Y": "s", "N": "ps" }
        },
        callback: {
            beforeClick: filter(kk_ztree.beforeClick,null),
            onClick: filter(kk_ztree.onClick,null),
            onCheck: filter(kk_ztree.onCheck,null)
        }
    };
}
//显示
function showMenu(e) {

    if (e && e.stopPropagation) {
        e.stopPropagation();
    } else {
        window.event.cancelBubble = true;
    }
    $("#tree").fadeIn("fast");
}
function hideMenu() {
    $("#tree").fadeOut("fast");
}
//点击window隐藏
$(window).click(function(){
    hideMenu();
})

