<#import "parts/common.ftlh" as c>

<@c.page>
    Edit Photo
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label>
                    <input type="text" class="form-control"
                           value="<#if photo??>${photo.message}</#if>" name="message" placeholder="Введите сообщение" />
                </label>
            </div>

            <div class="form-group">
                <label>
                    <input type="text" class="form-control"
                           value="<#if photo??>${photo.message}</#if>" name="tag" placeholder="Tag" />
                </label>
            </div>


            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Save changes</button>
            </div>
        </form>
        <form method="post" action="/update_photo/${photo.author.id}/delete/?photo=${photo.id}">
            <input type="submit" value="Delete">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>
</@c.page>