<#import "parts/common.ftlh" as c>
<#import "parts/loginpage.ftlh" as l>
<@c.page>
    ${message!""} <#--отображение сообщений-->
     <div>Sign in</div>
    <@l.login "/login" false/>
    <a>Does not in system?</a>
    <a href="/registration">Sign up!</a>
</@c.page>