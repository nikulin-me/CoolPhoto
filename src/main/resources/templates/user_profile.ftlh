<#import "parts/common.ftlh" as c>
<@c.page>
    <h4>
        <#if errorUser??>
            ${errorUser}
        <#else>
            <b>@${username}</b>
            <div>
                <#if error??>
                    ${error}
                    <br/>
                    <a href="/">Back</a>
                <#else>
                    <#list photos as photo>
                        <span>${photo.message}</span>
                        <div>
                            <img src="/img/${photo.filename}">
                        </div>
                        <br/>
                        <i>${photo.tag}</i>
                    </#list>
                    <br/>
                    <a href="/">Back</a>
                </#if>
            </div>
        </#if>
    </h4>

</@c.page>