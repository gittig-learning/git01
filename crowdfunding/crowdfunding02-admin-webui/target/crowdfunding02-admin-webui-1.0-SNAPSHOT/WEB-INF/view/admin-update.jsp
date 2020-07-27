<%--
  Created by IntelliJ IDEA.
  User: Simle
  Date: 2020/7/6
  Time: 0:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/view/include-head.jsp"%>
<body>
<%@include file="/WEB-INF/view/include-nav.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/view/include-sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="admin/to/main/page">首页</a></li>
                <li><a href="/admin/page">数据列表</a></li>
                <li class="active">修改</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <form role="form" action="admin/update" method="post">
                        <input type="hidden" name="id" value="${requestScope.updateAdmin.id}"/>
                        <input type="hidden" name="pageNum" value="${param.pageNum}"/>
                        <input type="hidden" name="keyword" value="${param.keyword}">
                        <div class="form-group">
                            <label for="exampleInputPassword1">用户昵称</label>
                            <input type="text" name="userName" class="form-control" id="exampleInputPassword1" value="${requestScope.updateAdmin.userName}">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">邮箱</label>
                            <input type="email" name="emial" class="form-control" id="exampleInputEmail1" value="${requestScope.updateAdmin.emial}">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> 修改</button>
                        <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
