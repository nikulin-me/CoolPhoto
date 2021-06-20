<#include "parts/security.ftlh">
<#import "parts/loginpage.ftlh" as l>
<#import "parts/common.ftlh" as c>
<@c.page>
    <a>Hello</a>
    <#if error??>
        ${error}
    </#if>
    <#if isRegistrationForm || username=="unknown">
        <a href="/login">Please, sign in or sign up</a>
    <#else>

        <br/>
        <#include "parts/photosList.ftlh">
    </#if>
</@c.page>