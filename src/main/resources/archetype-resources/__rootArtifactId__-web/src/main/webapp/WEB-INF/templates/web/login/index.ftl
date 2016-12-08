<#assign title="登录">

<@override name="content">

<div class="space-28"></div>

<div id="login-box" class="login-box visible widget-box no-border">
    <div class="widget-body">
        <div class="widget-main">
            <h4 class="header blue lighter bigger">
                <i class="ace-icon fa fa-coffee green"></i>
                登录
            </h4>

            <div class="space-6"></div>

            <form action="${ctx}/login" method="post">
                <div>
                    <label for="username"> 账号
                        <small class="text-info">（用户名/邮箱/手机）</small>
                    </label>
                    <div class="input-icon input-icon-right">
                        <input type="text" name="username" value="<#if user??>${user.username!''}</#if>" class="form-control" placeholder="用户名/邮箱/手机"
                               required minlength="5">
                        <i class="ace-icon fa fa-user"></i>
                    </div>
                </div>

                <div class="space space-8"></div>

                <div>
                    <label for="password"> 密码 </label>
                    <div class="input-icon input-icon-right">
                        <input type="password" name="password" value="<#if user??>${user.password!''}</#if>" class="form-control" placeholder="密码"
                               required minlength="6">
                        <i class="ace-icon fa fa-lock"></i>
                    </div>
                </div>

                <div class="space space-8"></div>

                <div>
                    <label for="captcha"> 验证码 </label>
                    <div class="input-icon input-icon-right">
                        <input type="text" name="captcha" class="col-xs-3" placeholder="验证码" required
                               minlength="4" maxlength="4">
                        <img onclick="this.src='${ctx}/captcha?'+Math.random();" src="${ctx}/captcha">
                    </div>
                </div>

                <div class="space"></div>

                <div class="clearfix">
                    <div class="pull-left error">${errMsg!''}</div>
                    <input type="submit" id="login" class="width-35 pull-right btn btn-sm btn-primary"
                           data-loading-text="登录中..." value="登录"/>
                </div>
                <div class="space-4"></div>
            </form>
        </div>

        <div class="toolbar clearfix">
            <div>
                <a href="javascript:" class="forgot-password-link">
                    <i class="ace-icon fa fa-arrow-left"></i>
                    忘记密码？
                </a>
            </div>
            <div>
                <a href="${ctx}/register" class="user-signup-link">
                    注册
                    <i class="ace-icon fa fa-arrow-right"></i>
                </a>
            </div>
        </div>
    </div>
</div>
</@override>

<@extends name="../../auth-layout.ftl"/>
