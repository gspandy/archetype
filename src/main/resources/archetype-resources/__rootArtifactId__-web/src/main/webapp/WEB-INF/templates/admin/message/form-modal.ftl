<#assign modal_title="推送消息" />
<#assign action="admin/message/save" />

<@override name="modal-body">

<link rel="stylesheet" href="${ctx}/static/ace/dist/css/select2.min.css"/>
<div class="row">
    <div class="row form-group">
        <div class="col-md-3 control-label">
            <label>消息类型<span class="red">*</span></label>
        </div>
        <div class="col-md-7 controls">
            <select name="type" class="form-control">
                <#list enum as e>
                    <option value="${e.getType()}">${e.getName()}</option>
                </#list>
            </select>
        </div>
    </div>
</div>

<div class="row">
    <div class="row form-group">
        <div class="col-md-3 control-label">
            <label>消息标题<span class="red">*</span></label>
        </div>
        <div class="col-md-7 controls">
            <input name="title" type="text" class="form-control" placeholder="消息标题" required/>
        </div>
    </div>
</div>

<div class="row">
    <div class="row form-group">
        <div class="col-md-3 control-label">
            <label>消息内容<span class="red">*</span></label>
        </div>
        <div class="col-md-7 controls">
            <textarea name="content" class="width-100" rows="5" placeholder="消息内容" required></textarea>
        </div>
    </div>
</div>

<div class="row">
    <div class="row form-group">
        <div class="col-md-3 control-label">
            <label>接收人</label>
        </div>
        <div class="col-md-7 controls">
            <select multiple="" name="receiverUsers" class="select2 width-100" data-placeholder="不选则推送给所有用户">
                <#list users as user>
                    <option value="${user.id}">${user.nickName}</option>
                </#list>
            </select>
        </div>
    </div>
</div>
</@override>

<@override name="modal-actions">
<input type="submit" class="btn btn-success" data-loading-text="正在提交..."
       value="<@spring.message "app.button.save"/>"/>

<script src="${ctx}/static/ace/dist/js/select2.min.js"></script>
<script src="${ctx}/static/app/js/admin/message/form-modal.js"></script>
</@override>

<@extends name="../../form-modal-layout.ftl"/>