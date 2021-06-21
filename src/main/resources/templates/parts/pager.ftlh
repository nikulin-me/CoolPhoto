<#macro pager url page>
    <#if page.TotalPages() gt 7>
        <#assign
            totalPages=page.getTotalPages()
            pageNumber=page.getNumber()+1
            head=(pageNumber>4)?then([1,-1],[1,2,3])
            tail=(pageNumber>totalPages-3)
            bodyBefore=2
            bodyAfter=2
            body=[]
            >
    <#else>
        <#assign
        body=1..page.getTotalPages()
        >
    </#if>
    <div>
        <ul class="pagination">
            <li class="page-item disabled">
                <a class="page-link" href="/" tableindex="-1">Pages</a>
            </li>
            <#list 1..page.getTotalPages() as p>
                <#if (p-1)==page.getNumber()>
                    <li class="page-item active">
                        <a class="page-link" href="#" tableindex="-1">${p}</a>
                    </li>
                <#else>
                    <li class="page-item">
                        <a class="page-link" href="${url}?page=${p-1}&size=${page.getSize()}" tableindex="-1">${p}</a>
                    </li>
                </#if>
            </#list>

        </ul>
        <ul class="pagination">
            <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1">Элементов на странице</a>
            </li>
            <#list [2,5, 10, 25, 50] as c>
                <#if c == page.getSize()>
                    <li class="page-item active">
                        <a class="page-link" href="#" tabindex="-1">${c}</a>
                    </li>
                <#else>
                    <li class="page-item">
                        <a class="page-link" href="${url}?page=${page.getNumber()}&size=${c}" tabindex="-1">${c}</a>
                    </li>
                </#if>
            </#list>
        </ul>
    </div>
</#macro>