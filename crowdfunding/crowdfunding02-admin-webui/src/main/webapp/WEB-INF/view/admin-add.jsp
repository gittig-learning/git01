<%--
  Created by IntelliJ IDEA.
  User: Simle
  Date: 2020/7/5
  Time: 23:13
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
                <li><a href="admin/page">数据列表</a></li>
                <li class="active">新增</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <form role="form" action="admin/addAdmin" method="post">
                        <p style="font-size: small;color: red">${requestScope.exception.message}</p>
                        <div class="form-group">
                            <label for="exampleInputLoginAcct">登陆账号</label>
                            <input type="text" name="loginAcct" class="form-control" id="exampleInputLoginAcct" placeholder="请输入登陆账号">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputLoginPassword">登陆密码</label>
                            <input type="password" name="userPswd" class="form-control" id="exampleInputLoginPassword" placeholder="请输入登陆账号">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">用户昵称</label>
                            <input type="text"  name="userName" class="form-control" id="exampleInputPassword1" placeholder="请输入用户名称">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">邮箱地址</label>
                            <input type="email" name="emial" class="form-control" id="exampleInputEmail1" placeholder="请输入邮箱地址">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                        <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
