function removeIdShow(roleArray) {
    // 清空数据
    $("#showRoleName").empty();
    window.rolesIdArray=[];
    // 打开模态框
    $("#myRemoveModal").modal("show");
    // 显示数组中role对象的roleName属性
    for(var i=0;i<roleArray.length;i++){
        var role=roleArray[i];
        var roleName=role.roleName;
        $("#showRoleName").append(roleName+"<br>");
        var roleId=role.id;
        window.rolesIdArray.push(roleId);
    }
}

function generateRolePage() {
    // 全选款翻页初始化
    $("#allBoxChecked").prop("checked",false);
    var pageInfo=getPageInfo();
    fillTableBody(pageInfo);
}

function getPageInfo() {
    var ajaxResult=$.ajax({
        url:"role/get/page",
        data:{
            pageNum:window.pageNum,
            pageSize:window.pageSize,
            keyword:window.keyword,
        },
        type:"post",
        async: false,
        dataType:"json"
    })
    if(ajaxResult.status!=200){
        layer.msg("statusCode:"+ajaxResult.status+";"+ajaxResult.statusText);
        return null;
    }
    var resultEntity=ajaxResult.responseJSON;
    var result=resultEntity.result;
    if (result=="FAILED"){
        layer.msg(resultEntity.message);
        return null;
    }
    var pageInfo=resultEntity.data;
    return pageInfo;
}

function fillTableBody(pageInfo) {
    //清空数据
    $("#rolePageTbody").empty();
    // 当查询不到数据时，清空页码导航栏
    $("#roleNavigator").empty();
    //
    $("#emptyData").empty();
    // 判断pageInfo是否有效
    if (pageInfo == null || pageInfo == undefined || pageInfo.list == null || pageInfo.list.length == 0) {
        $("#emptyData").append("抱歉，查询不到数据!");
        return null;
    }
    // 填充tbody
    for (var i = 0; i < pageInfo.list.length; i++) {
        var role = pageInfo.list[i];
        var roleId = role.id;
        var roleName = role.roleName;
        var nunber = "<td>" + roleId + "</td>";
        var checkbox = "<td><input id='"+roleId+"' class='remove_checkbox'  type=\"checkbox\"></td>";
        var name = "<td>" + roleName + "</td>";
        var button = "<td><button type='button' id='"+roleId+"' class='btn btn-success btn-xs role_auth_button'><i class=' glyphicon glyphicon-check'></i>" +
            "</button> <button type='button'  onclick='editRole(\""+roleName+"\","+roleId+")' class='btn btn-primary btn-xs'><i class='glyphicon glyphicon-pencil'></i>" +
            "</button> <button type='button' id='"+roleId+"' class='btn btn-danger btn-xs remove_Role_Button'><i class='glyphicon glyphicon-remove'></i></button> </td>";
        var tr="<tr>"+nunber+checkbox+name+button+"</tr>";

        $("#rolePageTbody").append(tr);
    }
    generateNavigator(pageInfo);
}
function generateNavigator(pageInfo) {
    var list=pageInfo.list;
    var pageNumbs=pageInfo.navigatepageNums;
    var prePage=pageInfo.prePage;
    var nextPage=pageInfo.nextPage;

    //$("#roleNavigator").empty(); 当查询数据为空的时候，无法执行清空操作
    var pre="<li onclick='pageCallback("+prePage+")'><a>上一页</a></li>";
    $("#roleNavigator").append(pre);
    for(var i=0;i<pageNumbs.length;i++){
        var page=pageNumbs[i];
        var pageNumb="<li onclick='pageCallback("+page+")'><a>"+page+"</a></li>";
        $("#roleNavigator").append(pageNumb);
    }

    var next="<li onclick='pageCallback("+nextPage+")'><a>下一页</a></li>";
    $("#roleNavigator").append(next);

}

function pageCallback(page) {
    window.pageNum=page;

    generateRolePage();
}

function showRoleAuthTree(){
     var ajaxReturn=$.ajax({
        url:"assign/get/treeData",
        type:"post",
        dataType:"json",
        async:false
    })
    if(ajaxReturn.status!= 200) {
        layer.msg(" 请 求 处 理 出 错 ！ 响 应 状 态 码 是 ： "+ajaxReturn.status+" 说 明 是 ："+ajaxReturn.statusText);
        return ;
    }
    var authList = ajaxReturn.responseJSON.data;
    var setting = {
        data: {
            simpleData: {
            // 开启简单 JSON 功能
                "enable": true, // 使用 categoryId 属性关联父节点，不用默认的 pId 了
                "pIdKey": "categoryId"
            },
            key: {
            // 使用 title 属性显示节点名称，不用默认的 name 作为属性名了
                name: "title"
            }
        },
        check: {
            enable: true
        }
    };
    $.fn.zTree.init($("#authTreeDemo"), setting, authList);
    // 获取 zTreeObj 对象
    var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");
    // 调用 zTreeObj 对象的方法，把节点展开
    zTreeObj.expandAll(true);
    // 将角色已有的权限回显
    ajaxReturn=$.ajax({
        url:"assign/get/roleAuth",
        type:"post",
        data:{
            "roleId":window.roleId
        },
        dataType:"json",
        async:false
    })
    if (ajaxReturn.status!=200){
        layer.msg(" 请 求 处 理 出 错 ！ 响 应 状 态 码 是 ： "+ajaxReturn.status+" 说 明 是 ："+ajaxReturn.statusText);
        return ;
    }
    var authArr=ajaxReturn.responseJSON.data;
    // 6.根据 authArr 把树形结构中对应的节点勾选上
    // ①遍历 authArr
    for(var i = 0; i < authArr.length; i++) {
        var authId = authArr[i];
    // ②根据 id 查询树形结构中对应的节点
        var treeNode = zTreeObj.getNodeByParam("id", authId);
    // ③将 treeNode 设置为被勾选
    // checked 设置为 true 表示节点勾选
        var checked = true;
    // checkTypeFlag 设置为 false，表示不“联动”，不联动是为了避免把不该勾选的勾选上
        var checkTypeFlag = false;
    // 执行
        zTreeObj.checkNode(treeNode, checked, checkTypeFlag);
    }
}

function setRoleAuth(){
    var authIdArr=[];
    var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");
    var checkedNodes = zTreeObj.getCheckedNodes();
    for(var i = 0; i < checkedNodes.length; i++) {
        var checkedNode = checkedNodes[i];
        var authId = checkedNode.id;
        authIdArr.push(authId);
    }
    var requestBody={
        roleId:[window.roleId],
        authIdArr:authIdArr
    }
    requestBody = JSON.stringify(requestBody);
    $.ajax({
        url:"assign/set/roleAuth",
        type:"post",
        data:requestBody,
        contentType:"application/json;charset=UTF-8",
        dataType:"json",
        success:function (response) {
            var result=response.result;
            if (result=="SUCCESS"){
                layer.msg("添加成功");
            }
            if (result=="FAIlED"){
                layer.msg("添加失败");
            }
        },
        error:function (response) {
            layer.msg("请求失败:"+response.status+response.statusText);
        }
    })
    $("#myRoleAuthModal").modal("hide");
}