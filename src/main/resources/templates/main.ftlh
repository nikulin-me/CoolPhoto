<#include "parts/security.ftlh">
<#import "parts/loginpage.ftlh" as l>
<#import "parts/common.ftlh" as c>
<@c.page>

    <#if isRegistrationForm>
        <#include "parts/loginpage.ftlh">
    <#else>

        <br/>
        <br/>
        <a href="/create_photo">Create photo!</a>

        <br/>
        <#include "parts/photosList.ftlh">
    </#if>
</@c.page>