$(function () {

    /**
     * 校验菜单代码是否重复
     */
    function validateMenuCode() {
        $.get(ctx + "/validate/menu?code=" + $("#code").val() + "&oldCode=" + $("#old-code").val(), function (data, status) {
            if (status == "success") {
                if (data == "true") {
                    document.getElementById("code").setCustomValidity("菜单代码已存在");
                } else {
                    document.getElementById("code").setCustomValidity("");
                }
            }
        });
    }

    // 校验
    $("#code").keyup(function () {
        validateMenuCode();
    });

});