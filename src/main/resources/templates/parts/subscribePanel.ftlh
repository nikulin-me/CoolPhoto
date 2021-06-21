<div>
    <#list users as user>
        <a href="/user?username=${user.username}">${user.username}</a>
        <br/>
    </#list>
</div>