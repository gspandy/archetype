<#assign modal_title="修改密码" />
<#assign action="admin/sys/user/password" />

<@override name="modal-body">
<input type="hidden" name="id" value="${user.id}"/>
<div class="row">
    <div class="row form-group">
        <div class="col-md-3 control-label">
            <label>用户名</label>
        </div>
        <div class="col-md-7 controls">
            <input type="text" class="form-control readonly" value="${user.username}" readonly/>
        </div>
    </div>
</div>
<div class="row">
    <div class="row form-group">
        <div class="col-md-3 control-label">
            <label>新密码<span class="red">*</span></label>
        </div>
        <div class="col-md-7 controls">
            <input name="password" type="text" class="form-control" placeholder="新密码" required minlength="6"/>
        </div>
    </div>
</div>

</@override>

<@override name="modal-actions">
<input type="submit" class="btn btn-success" data-loading-text="正在提交..."
       value="<@spring.message "app.button.save"/>"/>
</@override>

<@extends name="../../../form-modal-layout.ftl"/>