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
            <div>
                <label>
                    <input type="password" name="password"
                    placeholder="Password"/>
                </label>
            </div>
            <#if passwordError??>
                <div style="color: crimson">
                    ${passwordError}
                </div>
            </#if>
        </div>
        <#if isRegisterForm>
        <div>
            <label>Retype password:</label>
            <div>
                <label>
                    <input type="password" name="passwordConfirm"
                           placeholder="Retype Password"/>
                </label>
            </div>
            <#if passwordConfirmError??>
                <div style="color: crimson">
                    ${passwordConfirmError}
                </div>
            </#if>
            <div>
                <label>Email:</label>
                <div>
                    <label>
                        <input type="email" name="email"
                               placeholder="some@mail.com"/>
                    </label>
                </div>
                <#if emailError??>
                    <div style="color: crimson">
                        ${emailError}
                    </div>
                </#if>
            </div>
            <div class="col-sm-6">
                <div class="g-recaptcha" data-sitekey="6LfbGXAaAAAAAKypDsDqsFBibxaeqePMlJHE4FX3"></div>
                <#if captchaError??>
                    <div class="alert alert-danger" role="alert">
                        ${captchaError}
                    </div>
                </#if>
            </div>
        </div>
        </#if>
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