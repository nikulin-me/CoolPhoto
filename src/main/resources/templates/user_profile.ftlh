<#import "parts/common.ftlh" as c>
<@c.page>
    <h4>
        <b>${username}</b>
    </h4>
    <div>
        <#if error??>
            ${error}
        <#else>
            <#list photos as photo>
                <span>${photo.message}</span>
                <div>
                    <img src="/img/${photo.filename}">
                </div>
                <br/>
                <i>${photo.tag}</i>
            </#list>
        </#if>

    </div>
</@c.page>