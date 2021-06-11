<#import "parts/common.ftlh"as c>
<@c.page>
    <form method="post" enctype="multipart/form-data">
        Photo:
        <label>
            <input name="file" type="file">
            <#if photoError??>
                <div style="color: crimson">
                    ${photoError}
                </div>
            </#if>
        </label>
        <br/>
        Message:
        <label>
            <input name="message" placeholder="Message" />
        </label>
        Tags:
        <label>
            <input name="tag" placeholder="Tags">
        </label>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div>
            <button type="submit">Create!</button>
        </div>
    </form>
</@c.page>