<#include "parts/security.ftlh">
<#import "parts/loginpage.ftlh" as l>
<#import "parts/common.ftlh" as c>
<@c.page>
     <h4>Welcome to CoolPhoto</h4>
     <#if isRegistrationForm || username=="unknown">
          <a href="/registration">Please, sign up!</a>
          <br/>
          <a href="/login">Already exists?Sign in!</a>
          <#else>
               <a href="/profile">Change me</a>
               <@l.logout>
                    <a href="/logout">Log out!</a>
               </@l.logout>

               <br/>
               <#if isAdmin>
                    <a href="/users">Snow users</a>
               </#if>



     </#if>
</@c.page>