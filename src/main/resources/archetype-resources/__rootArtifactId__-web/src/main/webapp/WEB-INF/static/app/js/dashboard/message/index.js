$(function () {
    $("#DASHBOARD_MESSAGE").addClass('active');

    /**
     * 已选个数
     * @type {number}
     */
    var selected_count = 0;

    var Inbox = {
        display_bar: function () {
            if (selected_count == 0) {
                $('#id-toggle-all').removeAttr('checked');
                $('#id-message-list-navbar .message-toolbar').addClass('hide');
            } else {
                $('#id-message-list-navbar .message-toolbar').removeClass('hide');
            }
        },
        select_all: function () {
            selected_count = 0;
            $('.message-item input[type=checkbox]').each(function () {
                this.checked = true;
                $(this).closest('.message-item').addClass('selected');
                selected_count++;
            });

            $('#id-toggle-all').get(0).checked = true;

            Inbox.display_bar();
        },
        select_none: function () {
            $('.message-item input[type=checkbox]').removeAttr('checked').closest('.message-item').removeClass('selected');
            $('#id-toggle-all').get(0).checked = false;
            selected_count = 0;

            Inbox.display_bar();
        }
    };

    /**
     * 全选/全不选
     */
    $('#id-toggle-all').removeAttr('checked').on('click', function () {
        if (this.checked) {
            Inbox.select_all();
        } else {
            Inbox.select_none();
        }
    });

    /**
     * 选择/不选
     */
    $(".message-item input[type='checkbox']").on("click", function () {
        if (this.checked) {
            selected_count++;
            Inbox.display_bar();
        } else {
            selected_count--;
            Inbox.display_bar();
        }
    });

    /**
     * 删除
     */
    $("#delete-message").click(function () {
        $.messager.confirm("提示", "确定删除所选消息吗?", function () {
            Notify.warning("目前暂不支持删除");
        });
    });

    /**
     * 标记已读
     */
    $("#mark-read").click(function () {

        var items = $('.message-item input[type=checkbox]:checked');

        var ids = '';
        for (var i = 0; i < items.length; i++) {
            if (i != 0) {
                ids += ',';
            }
            ids += $(items[i]).parents(".message-item").attr("id");
        }

        if (ids == '') {
            Notify.warning("请先选择要操作的消息");
            return;
        }
        $.get(ctx + "/dashboard/message/read?ids=" + ids).success(function () {
            Notify.success("操作成功");
        })
    });

});