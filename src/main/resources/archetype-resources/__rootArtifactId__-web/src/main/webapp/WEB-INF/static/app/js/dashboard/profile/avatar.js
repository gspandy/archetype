$(function () {
    $("#DASHBOARD_PROFILE").addClass('active open');
    $("#DASHBOARD_PROFILE_AVATAR").addClass('active');

    var $form = $('#user-avatar-form');
    var file_input = $form.find('input[type=file]');

    file_input.ace_file_input({
        style: 'well',
        btn_choose: '点击这里添加图片',
        btn_change: null,
        no_icon: 'ace-icon fa fa-picture-o',
        droppable: false,
        allowExt: ["jpeg", "jpg", "png", "gif"],
        allowMime: ["image/jpeg", "image/jpg", "image/png", "image/gif"],
        maxSize: 2097152,//bytes
        thumbnail: 'fit'
    });

    file_input.on('file.error.ace', function(event, info) {
        if(info.error_count['size']) Notify.warning('超出最大上传限制。');
        if(info.error_count['ext'] || info.error_count['mime']) Notify.warning('不合法的文件类型。');
        event.preventDefault();
    });

    $form.on('submit', function() {
        var files = file_input.data('ace_input_files');
        if (!files || files.length == 0) {
            Notify.warning('请先选择要上传的头像');
            return false;
        }

        return true;
    });
});