$(function () {

    /**
     * 校验角色编码是否重复
     */
    function validateRoleCode() {
        $.get(ctx + "/validate/role?code=" + $("#code").val() + "&oldCode=" + $("#old-code").val(), function (data, status) {
            if (status == "success") {
                if (data == "true") {
                    document.getElementById("code").setCustomValidity("角色编码已存在");
                } else {
                    document.getElementById("code").setCustomValidity("");
                }
            }
        });
    }

    // 校验
    $("#code").keyup(function () {
        validateRoleCode();
    });

});