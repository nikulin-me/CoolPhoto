<#import "parts/common.ftlh" as c>
<@c.page>
    Edit Photo
    <form>
        <label>
            Message:
            <input type="text" name="message" placeholder="Message">
        </label>
        <label>
            Tag:
            <input type="text" name="tag" placeholder="Tag">
        </label>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="hidden" name="id" value="<#if photo??>${photo.id}</#if>"/>
        <button type="submit" class="btn btn-primary">Save message</button>
    </form>
</@c.page>