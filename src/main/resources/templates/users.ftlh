<#import "parts/common.ftlh" as c>
 <@c.page>
  <div>
   <table>
    <tr>
     <th>ID</th>
     <th>USERNAME</th>
     <th>Delete</th>
    </tr>
    <#list users as user>
         <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>
                <form method="post" action="/users/delete/${user.id}">
                    <input type="submit" value="Delete">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                </form>
            </td>
         </tr>
    </#list>
   </table>
  </div>
 </@c.page>