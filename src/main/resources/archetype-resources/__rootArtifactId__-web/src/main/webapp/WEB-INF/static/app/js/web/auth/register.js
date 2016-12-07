$(function () {

    /**
     * 校验用户名是否重复
     */
    function validateUsername() {
        $.get(ctx + "/validate/username?username=" + $("#username").val(), function (data, status) {
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

    // 清除错误信息
    $("#register-form input,button").click(function () {
        $("#errMsg").hide();
    });


});

function valid(e) {
    var pwd = $("#password").val();
    var rePwd = $("#re-password").val();

    if (pwd != rePwd) {
        e.setCustomValidity("两次密码不一致");
    } else {
        e.setCustomValidity("");
    }
}