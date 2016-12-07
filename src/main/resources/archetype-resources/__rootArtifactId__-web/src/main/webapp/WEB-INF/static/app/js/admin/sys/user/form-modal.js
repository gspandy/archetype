$(function () {

    /**
     * 校验用户名是否重复
     */
    function validateUsername() {
        $.get(ctx + "/validate/username?username=" + $("#username").val() + "&oldUsername=" + $("#old-username").val(), function (data, status) {
            if (status == "success") {
                if (data == "true") {
                    document.getElementById("username").setCustomValidity("用户名已存在");
                } else {
                    document.getElementById("username").setCustomValidity("");
                }
            }
        });
    }

    // 校验
    $("#username").keyup(function () {
        validateUsername();
    });

});