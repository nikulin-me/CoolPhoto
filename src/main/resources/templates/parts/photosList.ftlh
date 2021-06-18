<#include "security.ftlh">
<div>
    <#list photos as photo>
        <br/>
        <br/>
        <a href="/user/${photo.author.id}">
            <b>
                ${photo.author.username}
            </b>
            <#if photo.author.username==username>
                <a href="/update_photo/${photo.author.id}?photo=${photo.id}">Edit</a>
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
