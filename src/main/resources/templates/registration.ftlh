<#import "parts/common.ftlh" as c>
<#import "parts/loginpage.ftlh" as l>
<@c.page>
    <div>Add new user</div>
    ${photo!""}
    <@l.login "/registration" true/>
</@c.page>