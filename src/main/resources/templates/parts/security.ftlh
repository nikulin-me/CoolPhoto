<#assign
known=Session.SPRING_SECURITY_CONTEXT??
>
<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    username = user.getUsername()
    isAdmin=user.isReallyAdmin()
    isRegistrationForm=false
    isActive=user.isUserActive()
    >
<#else>
    <#assign
    user="null"
    username = "unknown"
    isAdmin = false
    isRegistrationForm=true
    isActive=false
    >
</#if>