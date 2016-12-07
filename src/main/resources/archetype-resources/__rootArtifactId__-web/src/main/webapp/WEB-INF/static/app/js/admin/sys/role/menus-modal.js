$(function () {

    var $form = $('#modal-form');
    var $modal = $form.parents('.modal');

    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };

    $.fn.zTree.init($("#tree"), setting, zNodes);

    var showNotify = function (response) {
        if (!response) {
            Notify.error("菜单设置失败。");
        } else {
            $modal.modal('hide');
            Notify.success("菜单设置成功。");
        }
    };

    var str = "";
    $("#modal-submit").click(function () {
        $form.ajaxSubmit({
            beforeSubmit: function (arr) {
                var zTree = $.fn.zTree.getZTreeObj("tree");
                var nodes = zTree.getCheckedNodes(true);
                for (var i = 0; i < nodes.length; i++) {
                    if (str != "") {
                        str += ",";
                    }
                    str += nodes[i].code;
                }
                arr[0].value = str;
            },
            dataType: 'json',
            success: showNotify,
            error: function () {
                $modal.modal('hide');
                Notify.error("服务器内部错误，请稍后再试。");
            }
        });
        return false;
    });
});