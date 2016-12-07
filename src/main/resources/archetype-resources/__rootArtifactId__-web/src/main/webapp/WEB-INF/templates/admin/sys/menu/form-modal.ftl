<#assign ctx="${(rca.contextPath)!''}">
<#assign modal_title="${menu.id???string('修改菜单', '添加菜单')}" />
<#assign action="${menu.id???string('admin/sys/menu/update', 'admin/sys/menu/save')}" />

<@override name="modal-body">
<#if menu.id??>
    <input type="hidden" name="id" value="${menu.id}"/>
</#if>
<input type="hidden" name="pcode" value="${(parent_menu.code)!''}">
<input type="hidden" name="type" value="${parent_menu.type}"/>
<div class="row">
    <div class="row form-group">
        <div class="col-md-3 control-label">
            <label for="id">上级菜单</label>
        </div>
        <div class="col-md-7 controls">
            <input readonly type="text" class="form-control readonly" id="parent_name"
                   value="${parent_menu.name}"/>
        </div>
    </div>

    <div class="row form-group">
        <div class="col-md-3 control-label">
            <label for="description">菜单名称</label>
        </div>
        <div class="col-md-7 controls">
            <input name="name" type="text" value="${menu.name!''}" class="form-control" placeholder="菜单名称" required/>
        </div>
    </div>

    <div class="row form-group">
        <div class="col-md-3 control-label">
            <label for="name">菜单代码</label>
        </div>
        <div class="col-md-7 controls">
            <input id="code" name="code" type="text" value="${menu.code!''}" class="form-control" placeholder="菜单代码" required/>
            <input type="hidden" id="old-code" value="${menu.code!''}">
        </div>
    </div>

    <div class="row form-group">
        <div class="col-md-3 control-label">
            <label for="url">地址</label>
        </div>
        <div class="col-md-7 controls">
            <input name="url" type="text" value="${menu.url!''}" class="form-control" placeholder="地址" required/>
        </div>
    </div>

    <div class="row form-group">
        <div class="col-md-3 control-label">
            <label for="sort">排序</label>
        </div>
        <div class="col-md-7 controls">
            <input name="sort" type="text" value="${menu.sort!'0'}" class="form-control" placeholder="排序" required/>
        </div>
    </div>

    <div class="row form-group">
        <div class="col-md-3 control-label">
            <label for="icon">图标</label>
        </div>
        <div class="col-md-7 controls">
            <input name="icon" type="text" value="${menu.icon!''}" class="form-control" placeholder="图标"/>
        </div>
    </div>

</div>
</@override>

<@override name="modal-actions">
<input type="submit" class="btn btn-success" data-loading-text="正在提交..."
       value="<@spring.message "app.button.save"/>"/>

<script src="${ctx}/static/app/js/admin/sys/menu/form-modal.js"></script>
</@override>

<@extends name="../../../form-modal-layout.ftl"/>