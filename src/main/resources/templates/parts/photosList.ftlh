<#include "security.ftlh">
<#import  "pager.ftlh" as p>
<@p.pager url photos/>
<div id="photos-list">
    <#list photos.content as photo>
        <div data-id="${photo.id}">
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
        </div>
    </#list>
</div>
<@p.pager url photos/>