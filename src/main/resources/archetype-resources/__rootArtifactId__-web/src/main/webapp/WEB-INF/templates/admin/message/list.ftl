<#assign title="消息">
<#assign ptitle="消息">

<#assign title2 = RequestParameters.title!'' />
<#assign type = RequestParameters.type!'' />

<@override name="actions">
<a href="${ctx}/admin/message/create" class="btn btn-sm btn-primary" data-backdrop="static" data-toggle="modal"
   data-target="#myModal">推送</a>
</@override>

<@override name="content">
<form class="form-inline items-form" method="get" novalidate>
    <div class="form-group">
        <select class="form-control" name="type">
            <option value="">全部消息</option>
            <#list enum as e>
                <option value="${e.getType()}" <#if type=='${e.getType()}'>selected</#if>>${e.getName()}</option>
            </#list>
        </select>
    </div>

    <div class="form-group">
        <input type="text" class="form-control" name="title" value="${title2}" placeholder="消息标题"/>
    </div>

    <button class="btn btn-sm btn-purple">
        搜索
        <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
    </button>
</form>

<div class="space-10"></div>

<table id="message-table" class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th>ID</th>
        <th>消息类型</th>
        <th>发送人</th>
        <th width="33%">消息标题</th>
        <th width="33%">消息内容</th>
        <th>发送时间</th>
    </tr>
    </thead>
    <tbody>
        <#if page.list?size gt 0>
            <#list page.list as message>
                <#include "table-tr.ftl"/>
            </#list>
        <#else>
        <tr>
            <td colspan="20">
                <div class="empty">暂无查询记录</div>
            </td>
        </tr>
        </#if>
    </tbody>
</table>
    <@c.pagination url="${ctx}/admin/message" param="title=${title2}&type=${type}"/>
</@override>

<@override name="script">
<script src="${ctx}/static/app/js/admin/message/list.js"></script>
</@override>

<@extends name="../layout.ftl"/>