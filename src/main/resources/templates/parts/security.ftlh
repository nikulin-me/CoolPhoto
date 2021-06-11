<#assign
known=Session.SPRING_SECURITY_CONTEXT??
>
<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    username = user.getUsername()
    isAdmin=user.isReallyAdmin()
    isRegistrationForm=false
    >
        <#else>
        <#assign
        user="null"
        username = "unknown"
        isAdmin = false
        isRegistrationForm=true
        >
</#if>