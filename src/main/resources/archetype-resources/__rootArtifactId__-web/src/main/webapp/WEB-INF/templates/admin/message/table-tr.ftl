<#assign ctx="${(rca.contextPath)!''}">

<tr id="message-${message.id}">
    <td>${message.id}</td>
    <td><#include "type.ftl"/></td>
    <td>
        <a data-toggle="modal" href="${ctx}/admin/sys/user/${message.senderUserId}"
           data-target="#myModal">${message.senderNickName}</a>
    </td>
    <td class="message-title-show"><@c.substring str="${message.title}" len=23/><#if message.title?length gt 23> <a class="message-title-action-show">显示全部</a></#if></td>
    <td class="message-title-hide hidden">${message.title} <a class="message-title-action-hide">显示部分</a></td>

    <td class="message-content-show"><@c.substring str="${message.content}" len=23/><#if message.content?length gt 23> <a class="message-content-action-show">显示全部</a></#if></td>
    <td class="message-content-hide hidden">${message.content} <a class="message-content-action-hide">显示部分</a></td>
    <td><#if message.createdTime??>${message.createdTime?datetime}</#if></td>
</tr>