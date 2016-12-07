<#assign modal_title="消息详情" />

<@override name="modal-body">
<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
    <tbody>
    <tr>
        <th width="25%">消息ID</th>
        <td width="75%">${message.id}</td>
    </tr>
    <tr>
        <th>发送人</th>
        <td>${message.senderNickName!'懒人'}</td>
    </tr>
    <tr>
        <th>消息类型</th>
        <td>${message.type}</td>
    </tr>
    <tr>
        <th>消息标题</th>
        <td>${message.title}</td>
    </tr>
    <tr>
        <th>消息内容</th>
        <td>${message.content}</td>
    </tr>
    <tr>
        <th>是否删除</th>
        <td>${(message.isDeleted==1)?string('是', '否')}</td>
    </tr>
    <tr>
        <th>创建时间</th>
        <td><#if message.createdTime??>${message.createdTime?datetime}</#if></td>
    </tr>
    <tr>
        <th>更新时间</th>
        <td><#if message.updatedTime??>${message.updatedTime?datetime}</#if></td>
    </tr>
    </tbody>
</table>
</@override>

<@override name="modal-footer">
<button class="btn btn-sm" data-dismiss="modal">
    <i class="ace-icon fa fa-times"></i>
    <@spring.message "app.button.close"/>
</button>
</@override>

<@extends name="../../modal-layout.ftl"/>