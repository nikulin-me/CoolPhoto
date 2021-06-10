<#macro login path isRegisterForm>
    <form action="${path}" method="post">

        <div>
            <label>User Name :</label>
            <div>
                <label>
                    <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                           placeholder="User name" />
                </label>
            </div>
            <#if usernameError??>
                <div style="color: crimson">
                    ${usernameError}
                </div>
            </#if>
        </div>
        <div>
            <label>Password:</label>
            <div class="col-sm-6">
                <label>
                    <input type="password" name="password"
                    placeholder="Password"/>
                </label>

            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button  type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
    </form>
</#macro>
<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Sign Out</button>

    </form>
</#macro>