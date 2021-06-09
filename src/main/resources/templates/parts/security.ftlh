<#assign
known=Session.SPRING_SECURITY_CONTEXT??
>
<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    username = user.getUsername()
    isRegistrationForm=false
    >
        <#else>
        <#assign
        username = "unknown"
        isAdmin = false
        isRegistrationForm=true
        >
</#if>