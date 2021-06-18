<#import "parts/common.ftlh" as c>
<@c.page>
    <h4>
        <b>${username}</b>
    </h4>
    <div>
        <#if error??>
            ${error}
        <#else>
           <#include "parts/photosList.ftlh">
        </#if>

    </div>
</@c.page>