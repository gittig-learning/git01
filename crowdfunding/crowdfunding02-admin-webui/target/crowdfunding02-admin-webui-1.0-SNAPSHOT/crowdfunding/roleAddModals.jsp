<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" >
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增角色</h4>
            </div>
            <div class="modal-body">
                <form class="form-inline" role="form" style="float:left;">
                    <div class="form-group has-feedback">
                        <div class="input-group">
                            <div class="input-group-addon">名称</div>
                            <input id="modifyValue" class="form-control has-success" type="text" placeholder="请输入角色名称"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="sava_role_button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

