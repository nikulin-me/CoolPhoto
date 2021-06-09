<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>
    Hello, ${username}
    <a href="/profile">Change yourself!</a>
</@c.page>