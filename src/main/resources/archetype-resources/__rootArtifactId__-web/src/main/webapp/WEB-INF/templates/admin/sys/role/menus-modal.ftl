<#assign ctx="${(rca.contextPath)!''}">
<#assign modal_title="设置角色菜单" />
<#assign action="admin/sys/role/${roleId}/menus" />
<#assign modal_form="" />

<link rel="stylesheet" href="${ctx}/static/libs/ztree/css/zTreeStyle.css"/>

<@override name="modal-body">
<input type="hidden" name="menus"/>
<input type="hidden" name="type" value="${type}"/>
<div class="control-group">
    <div>
        <ul id="tree" class="ztree"></ul>
    </div>
</div>
</@override>

<@override name="modal-actions">
<input type="submit" id="modal-submit" class="btn btn-success" data-loading-text="正在提交..."
       value="<@spring.message "app.button.save"/>"/>

<script>
    var zNodes = [
        <#list all_menus as menu>
            {
                id:${menu.id},
                pId:${menu.pid},
                name: "${menu.name}",
                code: "${menu.code}",
                open: true
                ${(role_menus?? && role_menus?seq_contains(menu.code))?string(", checked:true", "")}
            },
        </#list>];
</script>
<script src="${ctx}/static/libs/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${ctx}/static/libs/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script src="${ctx}/static/app/js/admin/sys/role/menus-modal.js"></script>
</@override>

<@extends name="../../../form-modal-layout.ftl"/>