<#assign title="消息列表">
<#assign ptitle="消息">

<#assign type = RequestParameters.type!'' />
<#assign isRead = RequestParameters.isRead!'' />

<@override name="content">
<div class="tabbable">
    <ul id="inbox-tabs" class="inbox-tabs nav nav-tabs padding-16 tab-size-bigger tab-space-1">
        <li <#if type==''>class="active"</#if>>
            <a href="${ctx}/dashboard/message">
                <i class="blue ace-icon fa fa-envelope-o bigger-130"></i>
                <span class="bigger-110">所有消息</span>
            </a>
        </li>

        <li <#if isRead=='0'>class="active"</#if>>
            <a href="${ctx}/dashboard/message?isRead=0">
                <i class="blue ace-icon fa fa-bell-o bigger-130"></i>
                <span class="bigger-110">未读消息</span>
            </a>
        </li>

        <li <#if type=='SYS_MESSAGE'>class="active"</#if>>
            <a href="${ctx}/dashboard/message?type=SYS_MESSAGE">
                <i class="blue ace-icon fa fa-bullhorn bigger-130"></i>
                <span class="bigger-110">系统消息</span>
            </a>
        </li>

        <li <#if type=='SERVICE_MESSAGE'>class="active"</#if>>
            <a href="${ctx}/dashboard/message?type=SERVICE_MESSAGE">
                <i class="blue ace-icon fa fa-comments-o bigger-130"></i>
                <span class="bigger-110">服务消息</span>
            </a>
        </li>
    </ul>

    <div class="tab-content no-border no-padding">
        <div id="all" class="tab-pane in active">
            <div class="message-container">
                <div id="id-message-list-navbar" class="message-navbar clearfix">
                    <div class="message-bar">
                        <div class="message-toolbar hide">
                            <button type="button" id="mark-read" class="btn btn-xs btn-white btn-primary">
                                <i class="ace-icon fa fa-eye bigger-125 orange"></i>
                                <span class="bigger-110">标记为已读</span>
                            </button>
                            <button type="button" id="delete-message" class="btn btn-xs btn-white btn-primary">
                                <i class="ace-icon fa fa-trash-o bigger-125"></i>
                                <span class="bigger-110">删除</span>
                            </button>
                        </div>
                    </div>

                    <div>
                        <div class="messagebar-item-left">
                            <label class="inline middle">
                                <input type="checkbox" id="id-toggle-all" class="ace">
                                <span class="lbl"></span>
                            </label>
                        </div>

                        <div class="nav-search minimized">
                            <form class="form-search">
                                <span class="input-icon">
                                    <input type="text" autocomplete="off"
                                           class="input-small nav-search-input"
                                           placeholder="查找消息 ...">
                                    <i class="ace-icon fa fa-search nav-search-icon"></i>
                                </span>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="message-list-container">
                    <div class="message-list" id="message-list">
                        <#if page.list?size gt 0>
                            <#list page.list as message>
                                <#include "message-item.ftl"/>
                            </#list>
                        <#else>
                            <div class="empty-message">没有消息才是最好的消息!</div>
                        </#if>
                    </div>
                </div>

                <#if page.list?size gt 0>
                    <div class="message-footer message-footer-style2 clearfix">
                        <div class="pull-left">共${page.total}条</div>

                        <div class="pull-right">
                            <div class="inline middle">第${page.startRow + 1}-${page.endRow + 1}条</div>

                            &nbsp; &nbsp;
                            <ul class="pagination middle">
                                <#if page.hasPreviousPage>
                                    <li>
                                        <a href="${ctx}/dashboard/message?p=${page.nextPage}&type=${type}&isRead=${isRead}">
                                            <i class="ace-icon fa fa-angle-left bigger-150"></i>
                                        </a>
                                    </li>
                                <#else>
                                    <li class="disabled">
                                        <span>
                                            <i class="ace-icon fa fa-angle-left bigger-150"></i>
                                        </span>
                                    </li>
                                </#if>
                                <#if page.hasNextPage>
                                    <li>
                                        <a href="${ctx}/dashboard/message?p=${page.nextPage}&type=${type}&isRead=${isRead}">
                                            <i class="ace-icon fa fa-angle-right bigger-150"></i>
                                        </a>
                                    </li>
                                <#else>
                                    <li class="disabled">
                                        <span>
                                            <i class="ace-icon fa fa-angle-right bigger-150"></i>
                                        </span>
                                    </li>
                                </#if>
                            </ul>
                        </div>
                    </div>
                </#if>
            </div>
        </div>
    </div>
</div>

</@override>

<@override name="script">
<script src="${ctx}/static/app/js/dashboard/message/index.js"></script>
</@override>

<@extends name="../layout.ftl"/>