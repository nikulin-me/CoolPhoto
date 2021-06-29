<#import "parts/common.ftlh" as c>
 <@c.page>
     <div>
         <ul class="list-group">
             <#list users as user>
                 <li class="list-group-item">
                     <a href="/user?username=${user.username}">${user.username}</a>
                 </li>
                 <br/>
             </#list>
         </ul>
     </div>
 </@c.page>