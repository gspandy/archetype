<#assign title="个人资料">
<#assign ptitle="我的">

<@override name="content">

<form action="${ctx}/dashboard/profile/info" method="post" class="form-horizontal">
    <input type="hidden" name="id" value="${user.id}"/>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right">昵称</label>
        <div class="col-xs-12 col-sm-5">
            <input type="text" name="nickName" value="${user.nickName}" class="form-control"/>
        </div>
    </div>

    <div class="clearfix form-actions">
        <div class="center">
            <input type="submit" class="btn btn-info width-10" data-toggle="form-submit" data-loading-text="正在提交..."
                   value="<@spring.message "app.button.save"/>"/>
        </div>
    </div>
</form>
</@override>

<@override name="script">
    <script src="${ctx}/static/app/js/dashboard/profile/info.js"></script>
</@override>

<@extends name="../layout.ftl"/>