<#assign ctx="${(rca.contextPath)!''}">

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h3 class="smaller lighter no-margin">
    ${modal_title}
        <@block name="modal-header"/>
    </h3>
</div>

<div class="modal-body form-modal-body">
    <form class="form-horizontal" data-toggle="${modal_form!'modal-form'}" role="form" id="modal-form" method="post" action="${ctx}/${action}">
    <@block name="modal-body"/>

        <div class="modal-footer form-model-footer">
        <@block name="modal-actions"/>
        </div>
    </form>
</div>
