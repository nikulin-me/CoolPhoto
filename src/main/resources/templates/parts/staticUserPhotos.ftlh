<#include "security.ftlh">
<div class="card-columns"  id="photos-list">
    <#list photos as photo>
        <div class="card my-3" data-id="${photo.id}">
            <style>
                .line{
                    border: 1px solid
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
                    <img src="/img/${photo.filename}" class="card-img-top"/>
                </#if>
            </div>
            <br/>
            <span>${photo.message}</span>
            <br/>
            <i>#${photo.tag}</i>
            <div class="line"></div>
        </div>
    </#list>
</div>