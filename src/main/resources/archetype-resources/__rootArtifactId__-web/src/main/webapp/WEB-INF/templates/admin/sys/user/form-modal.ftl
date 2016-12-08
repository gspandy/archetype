<#assign modal_title="${user.id???string('编辑用户', '添加新用户')}" />
<#assign action="admin/sys/user/${user.id???string('update', 'save')}" />

<@override name="modal-body">
    <#if user.id??>
    <input type="hidden" name="id" value="${user.id}"/>
    </#if>
<div class="row">
    <div class="row form-group">
        <div class="col-md-3 control-label">
            <label>用户名<span class="red">*</span></label>
        </div>
        <div class="col-md-7 controls">
            <input id="username" name="username" type="text" value="${user.username!''}" class="form-control"
                   placeholder="用户名"
                   required minlength="5"/>
            <input type="hidden" id="old-username" value="${user.username!''}"/>
        </div>
    </div>
</div>
    <#if !user.id??>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>密码<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <input name="password" type="text" class="form-control" placeholder="密码" required minlength="6"/>
            </div>
        </div>
    </div>
    </#if>
<div class="row">
    <div class="row form-group">
        <div class="col-md-3 control-label">
            <label>昵称</label>
        </div>
        <div class="col-md-7 controls">
            <input name="nickName" type="text" value="${user.nickName!''}" class="form-control" placeholder="昵称" required/>
        </div>
    </div>
</div>
</@override>

<@override name="modal-actions">
<input type="submit" class="btn btn-success" data-loading-text="正在提交..."
       value="<@spring.message "app.button.save"/>"/>

<script src="${ctx}/static/app/js/admin/sys/user/form-modal.js"></script>
</@override>

<@extends name="../../../form-modal-layout.ftl"/>