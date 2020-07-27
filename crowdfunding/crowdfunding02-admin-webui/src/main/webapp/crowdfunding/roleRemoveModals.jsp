<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade bs-example-modal-sm" id="myRemoveModal" tabindex="-1" role="dialog" >
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">删除角色</h4>
            </div>
            <div id="showRoleName" align="center">

            </div>
            <div class="modal-body">
                <form class="form-inline" role="form" style="float:left;">
                    <div class="form-group has-feedback">
                        <div class="input-group">
                        </div>
                    </div>
                </form>
            </div>
            <div id="remove_role_button_father" class="modal-footer">
                <button type="button" id="remove_role_button" class="btn btn-primary">确认删除</button>
            </div>
        </div>
    </div>
</div>
