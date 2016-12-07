$(function () {
    // 有滚动条时才显示回到顶部按钮
    window.onscroll = function () {
        if (document.documentElement.scrollTop + document.body.scrollTop > 100) {
            document.getElementById("btn-scroll-up").style.display = "block";
        } else {
            document.getElementById("btn-scroll-up").style.display = "none";
        }
    };

    // 防重复提交
    $(document).on('click', 'button[data-loading-text],input[data-loading-text]', function (e) {
        var btn = $(this).button('loading');
        setTimeout(function () {
            btn.button('reset');
        }, 2000);
    });

    // 提示框
    $.messager.model = {
        cancel: {text: "取消", classed: 'btn-default'},
        ok: {text: "确定", classed: 'btn-success'}
    };

    // 关闭时清除模态框的数据
    $(document).on('hidden.bs.modal', '.modal', function () {
        $(this).removeData('bs.modal');
    });

    $(document).on("submit", '[data-toggle=modal-form]', function () {
        var $modal = $(this).parents('.modal');
        $(this).ajaxSubmit({
            dataType: 'json',
            success: function (response) {
                if (response.responseState == "SUCCESS") {
                    $modal.modal('hide');
                    window.location.reload();
                } else {
                    Notify.error(response.errMsg);
                }
            },
            error: function () {
                Notify.error("服务器内部错误，请稍后再试。");
            }
        });

        return false;
    });

});

// 提示信息
var last_gritter;
var showMessage = function (type, message) {
    if (last_gritter) {
        $.gritter.remove(last_gritter);
    }
    last_gritter = $.gritter.add({
        title: '通知',
        text: message,
        class_name: type
    });
};
var Notify = {
    success: function (message) {
        showMessage('gritter-success', message);
    },

    warning: function (message) {
        showMessage('gritter-warning', message);
    },

    error: function (message) {
        showMessage('gritter-error', message);
    },

    info: function (message) {
        showMessage('gritter-info', message);
    }
};
