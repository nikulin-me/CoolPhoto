<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <h4>
        <b>${userChannel}</b>
    </h4>
    <#if username!=userChannel>
        <#if isSubscribe>
            <form method="post" action="/user/unsubscribe?username=${userChannel}">
                <input type="submit" value="Unfollow"/>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </form>
        <#else>
            <form method="post" action="/user/subscribe?username=${userChannel}">
                <input type="submit" value="Follow"/>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </form>
        </#if>
    </#if>
    <#if username==userChannel>
        <a href="/user/profile">Change me</a>
    </#if>
    <div class="container">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Subscriptions</div>
                        <h4 class="card-text">
                            <a href="/user/subscriptions/${userChannel}">${subscriptionsCount}</a>
                        </h4>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Subscribers</div>
                        <div class="card-text">
                            <h4 class="card-text">
                                <a href="/user/subscribers/${userChannel}">${subscribersCount}</a>
                            </h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div>
        <#if error??>
            ${error}
        <#else>
            <#include "parts/photosList.ftlh">
        </#if>

    </div>
</@c.page>