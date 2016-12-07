<@apps>
<li class="green">
    <a data-toggle="dropdown" class="dropdown-toggle" href="#" aria-expanded="false">
        <i class="ace-icon fa fa-envelope icon-animated-vertical"></i>

        <#if messages?size gt 0>
            <span class="badge badge-green">
            ${messages?size}
            </span>
        </#if>
    </a>

    <#if messages?size gt 0>
        <ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
            <li class="dropdown-header">
                <i class="ace-icon fa fa-envelope-o"></i>
            ${messages?size}条未读消息
            </li>

            <li class="dropdown-content ace-scroll" style="position: relative;">
                <div class="scroll-track" style="display: block; height: 200px;">
                    <div class="scroll-bar" style="height: 111px; top: 0px;"></div>
                </div>
                <div class="scroll-content" style="max-height: 200px;">
                    <ul class="dropdown-menu dropdown-navbar">
                        <#list messages as message>
                            <li>
                                <a data-toggle="modal" href="${ctx}/dashboard/message/${message.id}"
                                   data-target="#myModal" class="clearfix">
                                    <#if message.senderAvatar?? && message.senderAvatar != ''>
                                        <img src="${ctx}/${message.senderAvatar}"
                                             class="msg-photo">
                                    <#else>
                                        <img src="${ctx}/static/ace/dist/avatars/profile-pic.jpg"
                                             class="msg-photo">
                                    </#if>
                                    <span class="msg-body">
                                        <span class="msg-title">
                                            <span class="blue">${message.senderNickName!'懒人'}:</span>
                                            <@c.substring str="${message.title}" len=9/>
                                        </span>

                                        <span class="msg-time">
                                            <i class="ace-icon fa fa-clock-o"></i>
                                            <span><@c.relative_date datetime=message.createdTime/></span>
                                        </span>
                                    </span>
                                </a>
                            </li>
                        </#list>
                    </ul>
                </div>
            </li>

            <li class="dropdown-footer">
                <a href="${ctx}/dashboard/message">
                    查看所有消息
                    <i class="ace-icon fa fa-arrow-right"></i>
                </a>
            </li>
        </ul>
    </#if>
</li>
</@apps>