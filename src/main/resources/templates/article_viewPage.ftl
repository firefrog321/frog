<!DOCTYPE html>
<html lang="en">
<#include "header.ftl">
<body>
<#include "nav.ftl">
<div class="container" style="margin: 100px auto 10px;">
    <div class="row">
        <div class="col-md-9 page-content">
            <div class="dict" style="text-align: center; margin-bottom: 0;padding: 0;">
                <h3 style="margin: 0; border: 0;">${article.title}</h3>
                <p style="font-size: 20px;">
                <#list article.tags?split(",") as tag>
                    <a class="small" href="/article/page/1?queryTags=${tag}">${tag}</a>
                </#list>
                </p>
            </div>
        ${article.content}

            <div id="container"></div>
        </div>

        <div class="col-md-3 d-none d-md-block">
            <div id="frog_sidebar"></div>
        </div>
    </div>
    <link rel="stylesheet" href="/css/gitalk.css">
    <script src="/js/gitalk.min.js"></script>
    <script>
        const gitalk = new Gitalk({
            clientID: '5e2294a8c60578ace3be',
            clientSecret: '628721310f4fce70d0fbfdeddf57998445238d96',
            repo: 'frog',
            owner: 'firefrog321',
            admin: ['firefrog321'],
            id: location.pathname,
            createIssueManually:true,
            distractionFreeMode: false
        });

        gitalk.render('container');
    </script>
<#include "footer.ftl">
    <script>
        hljs.initHighlightingOnLoad();
    </script>
</body>
</html>