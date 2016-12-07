<div class="message-item" id="${message.id}">
    <label class="inline">
        <input type="checkbox" class="ace">
        <span class="lbl"></span>
    </label>

    <span class="sender" title="${message.senderNickName}">
    <a data-toggle="modal" href="${ctx}/admin/sys/user/${message.senderUserId}"
       data-target="#myModal">${message.senderNickName}</a>
    </span>
    <span class="time"><@c.relative_date datetime=message.createdTime/></span>

    <span class="summary">
        <span class="text" title="${message.title}">
        <a data-toggle="modal" href="${ctx}/dashboard/message/${message.id}"
           data-target="#myModal">${message.title}</a>
        </span>
    </span>
</div>