<#assign bodyClass="login-layout light-login">

<@override name="app-style">
    <@block name="style"/>
</@override>

<@override name="app-content">
<div class="row">
    <div class="col-lg-4 col-md-3 col-sm-2 hidden-xs">
        <@block name="content-left"/>
    </div>

    <div class="col-lg-4 col-md-6 col-sm-8 col-xs-12">
        <@block name="content"/>
    </div>

    <div class="col-lg-4 col-md-3 col-sm-2 hidden-xs">
        <@block name="content-right"/>
    </div>
</div>
</@override>

<@override name="app-script">
    <@block name="script"/>
</@override>

<@extends name="../layout.ftl"/>
