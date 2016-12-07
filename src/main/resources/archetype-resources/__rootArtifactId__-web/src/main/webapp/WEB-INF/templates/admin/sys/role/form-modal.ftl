<#assign modal_title="${role.id???string('编辑角色', '添加新角色')}" />
<#assign action="admin/sys/role/${role.id???string('update', 'save')}" />

<@override name="modal-body">
    <#if role.id??>
    <input type="hidden" name="id" value="${role.id}"/>
    </#if>
<div class="row">
    <div class="row form-group">
        <div class="col-md-3 control-label">
            <label>角色编码<span class="red">*</span></label>
        </div>
        <div class="col-md-7 controls">
            <input id="code" name="code" type="text" value="${role.code!''}" class="form-control"
                   placeholder="角色编码"
                   required/>
            <input type="hidden" id="old-code" value="${role.code!''}"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="row form-group">
        <div class="col-md-3 control-label">
            <label>角色名称<span class="red">*</span></label>
        </div>
        <div class="col-md-7 controls">
            <input name="name" type="text" value="${role.name!''}" class="form-control" placeholder="角色名称" required/>
        </div>
    </div>
</div>
<div class="row">
    <div class="row form-group">
        <div class="col-md-3 control-label">
            <label>角色描述</label>
        </div>
        <div class="col-md-7 controls">
            <input name="description" type="text" value="${role.description!''}" class="form-control" placeholder="角色描述"/>
        </div>
    </div>
</div>
</@override>

<@override name="modal-actions">
<input type="submit" class="btn btn-success" data-loading-text="正在提交..."
       value="<@spring.message "app.button.save"/>"/>

<script src="${ctx}/static/app/js/admin/sys/role/form-modal.js"></script>
</@override>

<@extends name="../../../form-modal-layout.ftl"/>