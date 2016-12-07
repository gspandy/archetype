<#assign modal_title="设置角色" />
<#assign action="admin/sys/user/${userId}/roles" />

<@override name="modal-body">
<div class="control-group">
    <#list all_roles as role>
        <div class="checkbox">
            <label>
                <input name="roles" type="checkbox" value="${role.code}"
                       class="ace" ${user_roles?seq_contains(role.code)?string("checked", "")}/>
                <span class="lbl"> ${role.name} </span>
            </label>
        </div>
    </#list>
</div>
</@override>

<@override name="modal-actions">
<input type="submit" class="btn btn-success" data-loading-text="正在提交..."
       value="<@spring.message "app.button.save"/>"/>

<script src="${ctx}/static/app/js/admin/sys/user/roles-modal.js"></script>
</@override>

<@extends name="../../../form-modal-layout.ftl"/>