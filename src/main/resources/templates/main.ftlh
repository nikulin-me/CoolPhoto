<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>
     <h4>Hello, World!</h4>
     <#if isRegistrationForm>
          <a href="/registration">Please, sign up!</a>
          <br/>
          <a href="/login">Already exists?Sign in!</a>
          <#else>
               <a href="/profile">Change yourself</a>
     </#if>
</@c.page>