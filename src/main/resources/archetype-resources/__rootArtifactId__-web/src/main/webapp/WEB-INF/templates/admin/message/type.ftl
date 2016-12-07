<#list enum as e>
    <#if e.getType()==message.type>
    ${e.getName()}
    </#if>
</#list>