<#include "security.ftlh">
<div id="photos-list">
    <#list photos as photo>
        <br/>
        <br/>
        <style>
            .line {
                border-bottom: 1px solid
            }
        </style>
        <a href="/user?username=${photo.author.username}">
            <b>
                ${photo.author.username}
            </b>
            <#if photo.author.username==username>
                <a href="/update_photo/${photo.author.id}?photo=${photo.id}">Edit</a>
            </#if>
        </a>
        <div>
            <#if photo.filename??>
                <img src="/img/${photo.filename}"/>
            </#if>
        </div>
        <br/>
        <span>${photo.message}</span>
        <br/>
        <i>#${photo.tag}</i>
        <div class="line"></div>
    </#list>
</div>
