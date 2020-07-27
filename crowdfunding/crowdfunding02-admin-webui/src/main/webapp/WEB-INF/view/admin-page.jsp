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
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                        </div>
                        <div class="panel-body">
                            <form class="admin/page" role="form" style="float:left;" method="post">
                                <div class="form-group has-feedback">
                                    <div class="input-group">
                                        <div class="input-group-addon">查询条件</div>
                                        <input class="form-control has-success" type="text" placeholder="请输入查询条件" name="keyword">
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                            </form>
                            <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                            <button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='admin/to/add/page'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                            <br>
                            <hr style="clear:both;">
                            <div class="table-responsive">
                                <span style=" color:red;font-size: smaller " >${requestScope.exception.Message}</span>
                                <table class="table  table-bordered">
                                    <thead>
                                    <tr>
                                        <th width="30">#</th>
                                        <th width="30"><input type="checkbox"></th>
                                        <th>账号</th>
                                        <th>名称</th>
                                        <th>邮箱地址</th>
                                        <th width="100">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <c:if test="${!empty requestScope.pageInfo.list}">
                                            <c:forEach items="${requestScope.pageInfo.list}" var="admin" varStatus="myStatus">
                                                <tr>
                                                    <td>${myStatus.count}</td>
                                                    <td><input type="checkbox"></td>
                                                    <td>${admin.loginAcct}</td>
                                                    <td>${admin.userName}</td>
                                                    <td>${admin.emial}</td>
                                                    <td>
                                                        <a href="assign/get/role?pageNum=${param.pageNum}&keyword=${param.keyword}&id=${admin.id}">
                                                            <button type="button"  class="btn btn-success btn-xs">
                                                                <i class=" glyphicon glyphicon-check"></i>
                                                            </button>
                                                        </a>

                                                        <a href="admin/update/review?pageNum=${param.pageNum}&keyword=${param.keyword}&id=${admin.id}">
                                                            <button type="button" class="btn btn-primary btn-xs">
                                                                <i class=" glyphicon glyphicon-pencil"></i>
                                                            </button>
                                                        </a>
                                                        <a href="admin/removeAdmin?pageNum=${param.pageNum}&keyword=${param.keyword}&id=${admin.id}">
                                                            <button type="button"  class="btn btn-danger btn-xs">
                                                            <i class=" glyphicon glyphicon-remove"></i>
                                                            </button>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>

                                        <c:if test="${empty requestScope.pageInfo.list}">
                                            <tr>
                                                <td colspan="6" align="center">没有查询到相关数据</td>
                                            </tr>
                                        </c:if>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td colspan="6" align="center">
                                                <ul class="pagination">
                                                    <li><a href="admin/page?pageNum=${requestScope.pageInfo.prePage}&keyword=${param.keyword}">上一页</a></li>
                                                    <c:forEach items="${requestScope.pageInfo.navigatepageNums}" var="item">
                                                        <li  class="" onclick="clickPage(this)"><a href="admin/page?pageNum=${item}&keyword=${param.keyword}">${item}<span class="sr-only">(current)</span></a></li>
                                                    </c:forEach>
                                                    <li><a href="admin/page?pageNum=${requestScope.pageInfo.nextPage}">下一页</a></li>
                                                </ul>
                                            </td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
<script type="text/javascript">

    function clickPage(o){
        $(o).attr("class","active");
    }
</script>