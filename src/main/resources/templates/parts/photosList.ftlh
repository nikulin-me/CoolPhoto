<#include "security.ftlh">
<#import  "pager.ftlh" as p>
<@p.pager url photos/>
<div class="card-columns" id="photos-list">
    <#list photos.content as photo>
        <div class="card my-3" data-id="${photo.id}">
            <style>
                .line {
                    border: 1px #4b4bcd solid
                }
            </style>

            <div class="card-footer text-muted container">
                <div class="row">
                    <a class=" col align-self-center " href="/user?username=${photo.author.username}">
                        ${photo.author.username}
                    </a>
                    <#if photo.author.username==username>
                        <a class="col align-self-right"
                           href="/update_photo/${photo.author.id}?photo=${photo.id}">Edit</a>
                    </#if>
                </div>
            </div>
            <div class="line"></div>
            <div>
                <#if photo.filename??>
                    <img src="/img/${photo.filename}" class="card-img-top"/>
                </#if>
            </div>
            <div class="card-footer text-muted container">
                <div class="row">
                    <a href="#" class="col align-self-center">
                        <#if photo.isLiked>
                            <i class="fas fa-heart"></i>
                        <#else>
                            <i class="far fa-heart"></i>
                        </#if>
                        ${photo.likes}
                    </a>
                </div>
            </div>
            <span>${photo.message}</span>
            <br/>
            <i>Tags: #${photo.tag}</i>
            <div class="line"></div>
        </div>
    </#list>
</div>
<@p.pager url photos/>