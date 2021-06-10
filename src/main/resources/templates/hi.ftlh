<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<#import "parts/loginpage.ftlh" as l>
<@c.page>
    Hello, ${username}
    <br/>
    <a href="/profile">Change yourself!</a>
    <@l.logout>
        <a href="/logout">Log out!</a>
    </@l.logout>
</@c.page>