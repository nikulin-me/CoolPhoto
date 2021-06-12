<#import  "parts/common.ftlh" as c>
<@c.page>
    <h2>Change yourself, ${username}</h2>
     <form method="post">
         <div >
             <label >Username :</label>
             <div>
                 <label>
                     <input type="text" name="username" placeholder="Username" />
                 </label>
                 <#if usernameError??>
                     <div style="color: crimson">
                         ${usernameError}
                     </div>
                 </#if>
             </div>
         </div>
         <div>
             <label>Password:</label>
             <div>
                 <label>
                     <input type="password" name="password" class="form-control" placeholder="Password" />
                 </label>
             </div>
         </div>
         <input type="hidden" name="_csrf" value="${_csrf.token}" />
         <button type="submit">Save change</button>
     </form>
    <br/>
    <form method="post" action="/profile/delete">
        <input type="submit" value="Delete">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>


    <a href="/">Back</a>
</@c.page>