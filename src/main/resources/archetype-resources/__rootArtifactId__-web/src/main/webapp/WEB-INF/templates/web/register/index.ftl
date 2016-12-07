<#assign title="注册">

<@override name="content">

<div class="space-28"></div>

<div id="signup-box" class="signup-box widget-box no-border visible">
    <div class="widget-body">
        <div class="widget-main">
            <h4 class="header green lighter bigger">
                <i class="ace-icon fa fa-users blue"></i>
                新用户注册
            </h4>

            <div class="space-6"></div>

            <form id="register-form" action="${ctx}/register" method="post">
                <fieldset>
                    <label class="block clearfix">
                        <span class="block input-icon input-icon-right">
                            <input type="text" class="form-control" id="username" name="username" value="<#if user??>${user.username!''}</#if>" placeholder="用户名" required
                                   minlength="5">
                            <i class="ace-icon fa fa-user"></i>
                        </span>
                    </label>

                    <div class="space-10"></div>

                    <label class="block clearfix">
                        <span class="block input-icon input-icon-right">
                            <input type="password" id="password" name="password" value="<#if user??>${user.password!''}</#if>" class="form-control" placeholder="密码"
                                   required minlength="6">
                            <i class="ace-icon fa fa-lock"></i>
                        </span>
                    </label>

                    <div class="space-10"></div>

                    <label class="block clearfix">
                        <span class="block input-icon input-icon-right">
                            <input type="password" id="re-password" class="form-control" value="<#if user??>${user.password!''}</#if>" placeholder="确认密码" required
                                   oninput="valid(this);" minlength="6">
                            <i class="ace-icon fa fa-retweet"></i>
                        </span>
                    </label>

                    <div class="space-10"></div>

                    <label class="block clearfix">
                        <span class="block input-icon input-icon-right">
                            <input type="text" name="captcha" class="col-xs-3" placeholder="验证码" required minlength="4"
                                   maxlength="4">
                            <img onclick="this.src='${ctx}/captcha?'+Math.random();" src="${ctx}/captcha">
                        </span>
                    </label>

                    <div class="space-16"></div>

                    <label class="block">
                        <input type="checkbox" class="ace" required>
                        <span class="lbl">
                            我已阅读并同意
                            <a href="javascript:">《注册协议》</a>
                        </span>
                    </label>

                    <div class="space-14"></div>

                    <div class="error" id="errMsg">${errMsg!''}</div>

                    <div class="space-10"></div>

                    <div class="clearfix">
                        <button type="reset" id="reset" class="width-30 pull-left btn btn-sm">
                            <i class="ace-icon fa fa-refresh"></i>
                            <span class="bigger-110">重置</span>
                        </button>

                        <input type="submit" class="width-65 pull-right btn btn-sm btn-success" value="注册并登录" data-loading-text="注册中..." />
                    </div>
                </fieldset>
            </form>
        </div>

        <div class="toolbar center">
            <a href="${ctx}/login" class="back-to-login-link">
                <i class="ace-icon fa fa-arrow-left"></i>
                已有账号,去登录
            </a>
        </div>
    </div>
</div>
</@override>

<@override name="script">
<script src="${ctx}/static/app/js/web/auth/register.js"></script>
</@override>

<@extends name="../../auth-layout.ftl"/>
