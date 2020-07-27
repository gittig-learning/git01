<%--
  Created by IntelliJ IDEA.
  User: Simle
  Date: 2020/7/6
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/view/include-head.jsp"%>
<link rel="stylesheet" href="ztree/zTreeStyle.css">
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="crowdfunding/role-page.js" charset="UTF-8"></script>
<script type="text/javascript">

    // 1.模态框弹出(更新)
    var oldRoleName=null;
    var editRoleId=100;
    function editRole(roleName,roleId){
        $('#myEditModal').modal("show");
        // 2.文本框显示编辑的roleName
        $("#editValue").val(roleName);
        editRoleId=roleId;
        oldRoleName=roleName;
    }

    $(function () {
        // 声明全局变量
        window.pageNum=1;
        window.pageSize=9;
        window.keyword="";
        // 2.调用分页函数，显示分页效果
        generateRolePage();

    // 3.搜索按钮绑定click事件，调用keywordFInd函数
    function keywordFind() {
        // pageNum 初始化
        window.pageNum=1;
        // keyword属性赋值
        var value=$("#keywordValue").val();
        window.keyword=value;
        //调用函数实现分页
        generateRolePage();
    }
    // 增加角色
    // 模态框弹出
    $("#add_role_button").click(function () {
        $("#myModal").modal("show");
    })
    // 5.点击保存按钮，向服务器发送新增请求
    $("#sava_role_button").click(function () {
        // 6.获取value值
        var roleName=$.trim($("#modifyValue").val());
        $.ajax({
            url:"role/addRole",
            data:{
                roleName:roleName
            },
            type:"post",
            dataType:"json",
            success:function (response) {
                var result=response.result;
                if (result=="SUCCESS"){
                    window.pageNum=99999;
                    generateRolePage();
                    layer.msg("添加成功")
                }else if(result=="FAILED"){
                    layer.msg("添加失败")
                }
            },
            error:function (response) {
                layer.msg(response.status+" "+response.statusText);
            }
        })
        // 关闭模态框
        $('#myModal').modal("hide");
        // 清理模态框文本框内容
        $('#modifyValue').val("");
    })
    // 更新角色（updata）
    // 3.点击更新按钮，向服务器发送新增请求
    $("#update_role_button").click(function () {
        var editRoleName=$.trim($("#editValue").val());

        if(editRoleId==null||editRoleName==null||editRoleName==""){
            layer.msg("角色不能为空");
            return null;
        }
        if(oldRoleName==editRoleName){
            layer.msg("角色不能为原名称");
            return null;
        }
        $.ajax({
            url:"role/updataRole",
            data:{
                roleName:editRoleName,
                id:editRoleId
            },
            type:"post",
            dataType:"json",
            success:function (response) {
                var result=response.result;
                if (result=="SUCCESS"){
                    generateRolePage();
                    layer.msg("更改成功")
                }else if(result=="FAILED"){
                    generateRolePage();
                    layer.msg("更改失败")
                }
            },
            error:function (response) {
                layer.msg(response.status+" "+response.statusText);
            }
        })

        // 关闭模态框
        $('#myEditModal').modal("hide");
        // 清理模态框文本框内容
        //$('#editValue').val("");
    })
/*------------------------------------------------删除----------------------------------------*/
    // 为单选复选框绑定单击事件
    $("#rolePageTbody").on("click",".remove_checkbox",function () {
        // 单选复选框全部选择，全选框选中
        var checkedNumb=$(".remove_checkbox:checked").length;
        var boxNumb=$(".remove_checkbox").length;
        $("#allBoxChecked").prop("checked",checkedNumb==boxNumb);
    })

    // 为全选复选框绑定单击事件
    $("#allBoxChecked").click(function () {
        $(".remove_checkbox").prop("checked",$("#allBoxChecked").prop("checked"));
    })

    // 多条删除按钮单击绑定
    $("#remove_roles_button").click(function () {
        var roleArray=[];
        // 获取选中的复选框
        var checkedBox=$(".remove_checkbox:checked");
        for(var i=0;i<checkedBox.length;i++){
            var role=checkedBox[i];
            var roleID=role.id;
            var roleName=$(role).parent().next().text();
            var role={id:roleID,roleName:roleName}
            roleArray.push(role);
        }
        removeIdShow(roleArray);
    })

    // 模态框确认按钮单击绑定发送请求
    $("#remove_role_button_father").on("click","#remove_role_button",function () {
        var rolesIdArr=window.rolesIdArray;
        if(rolesIdArr.length==0){
            layer.msg("至少选择一个删除内容");
            return null;
        }
        var jsonId=JSON.stringify(rolesIdArr);
        $.ajax({
            url:"role/removeRole",
            type:"post",
            data:jsonId,
            contentType:"application/json;charset=UTF-8",
            dataType:"json",
            success:function (response) {
                var result=response.result;
                if(result=="SUCCESS"){
                    generateRolePage();
                    layer.msg("删除成功");
                }else if(result=="FAILED"){
                    layer.msg("删除失败");
                }
            },
            error:function (response) {
                layer(response.status+" "+response.statusText);
            }
        })
        // 关闭模态框
        $("#myRemoveModal").modal("hide");
    })

    $("#rolePageTbody").on("click",".remove_Role_Button",function () {
        var roleId=this.id;
        var roleName=$(this).parent().prev().text();
        var roleArray=[{id:roleId,roleName:roleName}];
        removeIdShow(roleArray)
    })

    $("#rolePageTbody").on("click",".role_auth_button",function () {
        window.roleId=this.id;
        $("#myRoleAuthModal").modal("show");
        showRoleAuthTree();
    })

    $("#add_auth_button").click(function () {
        setRoleAuth();

    })
})

</script>
<body>

<%@include file="/WEB-INF/view/include-nav.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/view/include-sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline"  role="form" style="float:left;" method="post">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="keywordValue" class="form-control has-success" type="text" placeholder="请输入查询条件" name="keyword">
                            </div>
                        </div>
                        <button type="button" onclick="keywordFind()" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" id="remove_roles_button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" id="add_role_button"  class="btn btn-primary" style="float:right;" ><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered" >
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input id="allBoxChecked" type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="rolePageTbody">

                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="6" align="center">
                                        <ul class="pagination" id="roleNavigator" >
                                        </ul>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <div align="center" id="emptyData" style="font-size: medium ;color: red"></div>
                        <button type="button" id="test_button" >测试</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/crowdfunding/roleAddModals.jsp"%>
<%@include file="/crowdfunding/roleEditModals.jsp"%>
<%@include file="/crowdfunding/roleRemoveModals.jsp"%>
<%@include file="/crowdfunding/roleAuthModals.jsp"%>
</body>
</html>
