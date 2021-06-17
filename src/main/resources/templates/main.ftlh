<#include "parts/security.ftlh">
<#import "parts/loginpage.ftlh" as l>
<#import "parts/common.ftlh" as c>
<@c.page>

    <#if isRegistrationForm || username=="unknown">
        <a href="/registration">Please, sign up!</a>
        <br/>
        <a href="/login">Already exists?Sign in!</a>
    <#else>
        <form action="/user/profile/{username.id}">
            <label>
                <input name="username" placeholder="Username">
            </label>
            <label>
                <input type="submit" value="Find creator">
            </label>
        </form>

        <a href="/user/profile">Change me</a>

        <br/>
        <#--<#if isAdmin>
            <a href="/users">Snow users</a>
        </#if>-->
        <br/>
        <a href="/create_photo">Create photo!</a>

        <br/>
        <div>
            <#list photos as photo>
                <br/>
                <br/>
                <a  href="/profile/${photo.author.username}">
                    <b>
                        ${photo.author.username}
                    </b>
                    <#if photo.author.username==username>
                        <a href="/update_photo/${photo.id}">Edit</a>
                    </#if>
                </a>
                <div>
                    <#if photo.filename??>
                        <img src="/img/${photo.filename}">
                    </#if>
                </div>
                <br/>
                <span>${photo.message}</span>
                <br/>
                <i>#${photo.tag}</i>
            </#list>
        </div>
    </#if>
</@c.page>