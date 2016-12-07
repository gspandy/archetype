$(function () {
    $("#ADMIN_MESSAGE").addClass('active');

    $(document).on("click", "a.message-content-action-show", function () {
        $(this).parents("td").addClass("hidden");
        $(this).parents("tr").find("td.message-content-hide").removeClass("hidden");
    });

    $(document).on("click", "a.message-content-action-hide", function () {
        $(this).parents("td").addClass("hidden");
        $(this).parents("tr").find("td.message-content-show").removeClass("hidden");
    });

    $(document).on("click", "a.message-title-action-show", function () {
        $(this).parents("td").addClass("hidden");
        $(this).parents("tr").find("td.message-title-hide").removeClass("hidden");
    });

    $(document).on("click", "a.message-title-action-hide", function () {
        $(this).parents("td").addClass("hidden");
        $(this).parents("tr").find("td.message-title-show").removeClass("hidden");
    });
});