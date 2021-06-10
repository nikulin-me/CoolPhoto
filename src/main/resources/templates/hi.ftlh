<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>
    Hello, ${username}
    <br/>
    <a href="/profile">Change yourself!</a>
</@c.page>