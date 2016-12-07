<#assign ctx="${(rca.contextPath)!''}">

<tr id="user-${user.id}">
    <td>${user.id}</td>
    <td>${user.username}</td>
    <td>${user.nickName}</td>
    <td><#include "locked.ftl"></td>
    <td><#include "delete.ftl"></td>
    <td><#if user.loginTime??>${user.loginTime?datetime}</#if></td>
    <td>
        <div class="btn-group">
            <a data-toggle="modal" class="btn btn-xs btn-inverse" href="${ctx}/admin/sys/user/${user.id}"
               data-target="#myModal">详情</a>

            <button data-toggle="dropdown" class="btn btn-xs btn-inverse dropdown-toggle">
                <span class="ace-icon fa fa-caret-down icon-only"></span>
            </button>

            <ul class="dropdown-menu dropdown-menu-right dropdown-inverse">
                <li>
                    <a href="${ctx}/admin/sys/user/${user.id}/edit" data-toggle="modal" data-target="#myModal"
                       data-backdrop="static">编辑</a>
                </li>
                <li>
                    <a href="${ctx}/admin/sys/user/${user.id}/password" data-toggle="modal" data-target="#myModal"
                       data-backdrop="static">修改密码</a>
                </li>
                <li>
                    <a href="${ctx}/admin/sys/user/${user.id}/roles" data-toggle="modal" data-target="#myModal"
                       data-backdrop="static">设置角色</a>
                </li>
            </ul>
        </div>
    </td>
</tr>