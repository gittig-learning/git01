<%--
  Created by IntelliJ IDEA.
  User: Simle
  Date: 2020/7/11
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/view/include-head.jsp"%>
<script type="text/javascript">
    $(function () {
        $("#toRightBnt").click(function () {
            $("select:eq(0)>option:selected").appendTo($("select:eq(1)"))
        })
        $("#toLeftBnt").click(function () {
            $("select:eq(1)>option:selected").appendTo($("select:eq(0)"))
        })
        $("#admin_role-button").click(function () {
            $("select:eq(1)>option").prop("selected","selected");
        })

    })

</script>
<body>
<%@include file="/WEB-INF/view/include-nav.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/view/include-sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form role="form" action="assign/set/role?pageNum=${param.pageNum}&keyword=${param.keyword}&id=${admin.id}" class="form-inline" method="post">
                        <input  type="hidden" value="${param.id}" name="adminId">
                        <div class="form-group">
                            <label>未分配角色列表</label><br>
                            <select  class="form-control" multiple="" size="10" style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.adminHasNotRole }" var="role">
                                    <option value="${role.id }">${role.roleName }</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="toRightBnt" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li id="toLeftBnt" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label>已分配角色列表</label><br>
                            <select name="roleId" class="form-control" multiple="" size="10" style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.adminHasRole }" var="role">
                                    <option value="${role.id }">${role.roleName }</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button id="admin_role-button" style="width: 80px;margin: 10px 240px " type="submit" class="btn btn-sm btn-success btn-block" >确认提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
