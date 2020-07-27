<%--
  Created by IntelliJ IDEA.
  User: Simle
  Date: 2020/7/11
  Time: 1:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade bs-example-modal-sm" id="menuRemoveModal" tabindex="-1" role="dialog" >
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">删除权限</h4>
            </div>
            <div class="modal-body" align="center">
                <div id="showRmeoveNodeName"  style="color: red"></div>
            </div>
            <div class="modal-footer">
                <button type="button" id="edit_remove_button" class="btn btn-primary">确认删除</button>
            </div>
        </div>
    </div>
</div>
