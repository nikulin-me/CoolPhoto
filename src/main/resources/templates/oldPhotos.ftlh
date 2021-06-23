<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <h4>
        <b>${userChannel}</b>
    </h4>
    <#include "parts/subscribeInProfile.ftlh">
    <div>
        <br/>
        <#include "parts/photosList.ftlh">
    </div>
</@c.page>