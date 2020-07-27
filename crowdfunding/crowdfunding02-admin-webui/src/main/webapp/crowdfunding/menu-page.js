function generateMenuPage() {
    $(function () {
        $.ajax({
            url:"menu/get/tree",
            dataType:"json",
            type:"post",
            success:function (response) {
                var result=response.result;
                if(result=="SUCCESS"){
                    // 存储对于zNodes的设置
                    var setting={
                        view:{
                           addDiyDom:myAddDiyDOM,
                            addHoverDom:myAddHoverDom,
                            removeHoverDom:myRemoveHoverDom,
                        },
                        data:{
                            key:{
                                url:"xurll"
                            }
                        }
                    };
                    // 从响应体中获取用来生成树形结构的JSON数据
                    var zNodes=response.data;
                    // 初始化树形结构
                    $.fn.zTree.init($("#treeDemo"),setting,zNodes);
                }else if(result=="FAILED") {
                    layer.msg(response.message);
                }
            }
        })
    })
}
// 替换图标
function myAddDiyDOM(treeId,treeNode) {
    var spanId=treeNode.tId+"_ico";
    $("#"+spanId).removeClass().addClass(treeNode.icon);
}

function myAddHoverDom(treeId,treeNode){
    var btnGroupId=treeNode.tId+"_btnGrp";
    var nodeId=treeNode.id;
    window.nodeName=treeNode.name;
    window.fatherNode={
        id:treeNode.id,
        pid:treeNode.pid,
        name:treeNode.name,
        icon: treeNode.icon
    };
    // 鼠标经过节点会多次触发该函数
    if($("#"+btnGroupId).length>0){
        return;
    }
    var editButton="<a class=\"btn btn-info dropdown-toggle btn-xs \" style=\"margin-left:10px;padding-top:0px;\" title=\"编辑\">\n" +
        "&nbsp;&nbsp;<i id='"+nodeId+"' class=\"meun_edit_butn fa fa-fw fa-edit rbg  \"></i></a>";
    var removeButton="<a class=\"btn btn-info dropdown-toggle btn-xs\" style=\"margin-left:10px;padding-top:0px;\" title=\"删除\" >" +
        "&nbsp;&nbsp;<i id='"+nodeId+"' class=\"meun_remove_butn fa fa-fw fa-times rbg \"></i></a>";
    var addButton="<a class=\"btn btn-info dropdown-toggle btn-xs\" style=\"margin-left:10px;padding-top:0px;\" title='添加'>" +
        "&nbsp;&nbsp;<i id='"+nodeId+"' class=\"meun_add_butn fa fa-fw fa-plus rbg \"></i></a>";
    var level=treeNode.level;
    var buttonHtml="";
    // 为不同等级的节点提供不同功能的按钮
    if(level==0){
        buttonHtml=editButton+addButton;
    }else if (level==1){
        buttonHtml=editButton+addButton;
        if(treeNode.children.length==0){
            buttonHtml=buttonHtml+removeButton;
        }
    }else if (level==2){
        buttonHtml=editButton+""+removeButton;
    }
    $("#"+treeNode.tId+"_a").after("<span id='"+btnGroupId+"'>"+buttonHtml+"</span>");

}

function myRemoveHoverDom(treeId,treeNode){
    //拼接按钮组的id
    var btnGroupId=treeNode.tId+"_btnGrp";
    //移除对应的元素
    $("#"+btnGroupId).remove();
}
function editMenuRequest() {
    $("#treeDemo").on("click",".meun_edit_butn",function () {
        $("#menuEditModal").modal("show");
        $("#editValue").val(window.nodeName);
    })

    $("#edit_menu_button").click(function () {
        var textVaule=$("#editValue").val();
        var nodeId=$(".meun_edit_butn").attr("id");
        $.ajax({
            url:"menu/edit/nodeName",
            type:"post",
            data:{
                name:textVaule,
                id:nodeId,
            },
            dataType:"json",
            success:function (response) {
                var result=response.result;
                if(result=="SUCCESS"){
                    generateMenuPage();
                    layer.msg("更改成功");
                }
                if(result=="FAILED"){
                    layer.msg("删除失败："+response.message);
                }
            },
            error:function (response) {
                layer.msg(response.status+response.statusText);
            }
        })
        $("#menuEditModal").modal("hide");
    })
}

function removeNode(){
    $("#treeDemo").on("click",".meun_remove_butn",function () {
        $("#showRmeoveNodeName").empty();
        $("#showRmeoveNodeName").append(window.nodeName);
        $("#menuRemoveModal").modal("show");
    })
    $("#edit_remove_button").click(function () {
        var nodeId=$(".meun_remove_butn").attr("id");
        $.ajax({
            url:"menu/remove/nodeName",
            type:"post",
            data:{
                id:nodeId
            },
            dataType:"json",
            success:function (response) {
                var result=response.result;
                if(result=="SUCCESS"){
                    generateMenuPage();
                    layer.msg("删除成功");
                }
                if(result=="FAILED"){
                    layer.msg("删除失败："+response.message);
                }
            },
            error:function (response) {
                layer.msg(response.status+response.statusText);
            }
        })
        $("#menuRemoveModal").modal("hide");
    })
}
function addNode(){
    $("#treeDemo").on("click",".meun_add_butn",function () {
        $("#menuAddModal").modal("show");
    })
    $("#add_menu_button").click(function () {
        var newNodeName=$("#addValue").val();
        var icon=window.fatherNode.icon;
        var pid=window.fatherNode.id;

        $.ajax({
            url:"menu/add/node",
            type:"post",
            data:{
                pid:pid,
                name:newNodeName,
                icon:icon,
            },
            dataType:"json",
            success:function (response) {
                var result=response.result;
                if(result=="SUCCESS"){
                    generateMenuPage();
                    layer.msg("添加成功");
                }
                if(result=="FAILED"){
                    layer.msg("添加失败："+response.message);
                }
            },
            error:function (response) {
                layer.msg(response.status+response.statusText);
            }
        })
        $("#menuAddModal").modal("hide");
    })
}