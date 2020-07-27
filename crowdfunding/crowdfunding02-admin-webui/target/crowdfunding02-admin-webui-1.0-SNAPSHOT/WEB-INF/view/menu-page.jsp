<%--
  Created by IntelliJ IDEA.
  User: Simle
  Date: 2020/7/10
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/view/include-head.jsp"%>
<link rel="stylesheet" href="ztree/zTreeStyle.css">
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="crowdfunding/menu-page.js" charset="UTF-8"></script>
<%@ include file="/crowdfunding/menuEditModals.jsp"%>
<%@ include file="/crowdfunding/menuRemoveModals.jsp"%>
<%@ include file="/crowdfunding/menuAddModals.jsp"%>
<script type="text/javascript">

    $(function () {
        generateMenuPage();
        editMenuRequest();
        removeNode();
        addNode();
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
                    <i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal">
                        <i class="glyphicon glyphicon-question-sign"></i>
                    </div>
                </div>
                <div class="panel-body">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
