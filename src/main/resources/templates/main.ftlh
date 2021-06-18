<#include "parts/security.ftlh">
<#import "parts/loginpage.ftlh" as l>
<#import "parts/common.ftlh" as c>
<@c.page>

    <#if isRegistrationForm || username=="unknown">
        <a href="/login">Please, sign in or sign up</a>
    <#else>

        <br/>
        <#include "parts/photosList.ftlh">
    </#if>
</@c.page>