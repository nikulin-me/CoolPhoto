<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <h4>
        <b>${userChannel}</b>
    </h4>
    <#include "parts/subscribeInProfile.ftlh">
    <div>
        <br/>
        <#if error??>
            ${error}
        <#else>
            <div>
                <a href="/user/oldPhotos/${userChannel}">Show all photos</a>
            </div>
            <br/>
            <#include "parts/staticUserPhotos.ftlh">
        </#if>

    </div>
</@c.page>