<#include "security.ftlh">
<#import "loginpage.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">CoolPhoto</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/users">User list</a>
                </li>
            </#if>

            <#if isRegistrationForm>
                <li class="nav-item">
                    <a class="nav-link" href="/registration">Sign Up</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/login">Sign In</a>
                </li>
            </#if>
            <#if user??>
                <li>
                    <a class="nav-link" href="/user?username=${username}">
                        Profile
                    </a>
                </li>
            </#if>
            <#if user??>
                <li>
                    <a class="nav-link" href="/create_photo">
                        Create photo
                    </a>
                </li>
            </#if>

        </ul>
        <#if !isRegistrationForm>
            <div class="navbar-taxt mr-5">
                <form action="/user/users">
                    <label>
                        <input name="reqName" placeholder="Username"/>
                    </label>
                    <label>
                        <input type="submit" value="Find creator"/>
                    </label>
                </form>
            </div>

            <div class="navbar-text mr-3">${username}</div>
            <#if user??>
                <@l.logout />
            </#if>
        </#if>

    </div>
</nav>