<!DOCTYPE html>
<html lang="utf-8">
<#include "header.ftl">
<body>
<#include "nav.ftl">
<div class="container" style="margin-top: 100px;">
    <div class="row">
        <div class="col-md-8 main">
            <#list articlePage.content as article>
                <div class="card mb-4 box-shadow">
                    <div class="card-body">
                        <div onclick="window.open('/article/' + ${article.articleId}, '_self');"></div>
                        <h3>
                            <a href="/article/${article.articleId}">${article.title!}</a>
                        </h3>
                        <p class="card-text">${article.preview!}</p>
                        <span class="small">${article.createDate!}</span>
                        <span class="small">&nbsp;&nbsp;by&nbsp;&nbsp;${article.author!}</span>

                        <span class="small" >&nbsp;&nbsp;&nbsp;&nbsp;浏览（${article.viewNum!'0'}）</span>

                    </div>
                </div>
            </#list>
        </div>
    </div>
</div>

<#include "footer.ftl">

</body>
</html>
