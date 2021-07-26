<#import "parts/common.ftlh" as c>
<@c.page>
    <div>
        <ul class="list-group">
            <#list users as user>
                <li class="list-group-item">
                    <a href="/user?username=${user.username}">${user.username}</a>
                </li>
                <br/>
                <#if errorPage??>
                    <div style="color: #4b4bcd">
                        ${errorPage}
                    </div>
                </#if>
            </#list>
        </ul>
    </div>
</@c.page>