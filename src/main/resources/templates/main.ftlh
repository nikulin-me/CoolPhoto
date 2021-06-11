<#include "parts/security.ftlh">
<#import "parts/loginpage.ftlh" as l>
<#import "parts/common.ftlh" as c>
<@c.page>

    <#if isRegistrationForm || username=="unknown">
        <a href="/registration">Please, sign up!</a>
        <br/>
        <a href="/login">Already exists?Sign in!</a>
    <#else>
        <form method="post" action="/profile/{username}">
            <input type="submit" value="Find creator">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>

        <a href="/profile">Change me</a>
        <@l.logout>
            <a href="/logout">Log out!</a>
        </@l.logout>

        <br/>
        <#if isAdmin>
            <a href="/users">Snow users</a>
        </#if>
        <br/>
        <a href="/create_photo">Create photo!</a>

        <#list photos as photo>
            <b>${photo.author}</b>
            <span>${photo.message}</span>
            <div>
                ${photo.filename}
            </div>
            <br/>
            <i>${photo.tag}</i>
        </#list>
    </#if>
</@c.page>