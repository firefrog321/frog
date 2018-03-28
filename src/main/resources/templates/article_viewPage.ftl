<!DOCTYPE html>
<html lang="en">
<#include "header.ftl">
<body onload="articleHit(${article.id})">
<#include "nav.ftl">
<div class="container" style="margin: 100px auto 10px;">
    <div class="row">
        <div class="col-md-9 page-content">
            <div class="dict" style="text-align: center; margin-bottom: 0;padding: 0;">
                <h3 style="margin: 0; border: 0;">${article.title}</h3>
                <p class="pcolor" style="margin-bottom: 0;">
                <#list article.tags?split(",") as tag>
                    <a class="small" href="/tag/${tag}">${tag}</a>
                </#list>
                </p>
            </div>
            <hr style="border-top-style: dashed; margin-top:0;">
        ${article.content}

            <hr>

            <div class="" id="container">
            </div>
        </div>

        <div class="col-md-3 d-none d-md-block">
            <div id="jcker_sidebar"></div>
        </div>

        <link rel="stylesheet" href="https://unpkg.com/gitalk/dist/gitalk.css">
        <script src="https://unpkg.com/gitalk/dist/gitalk.min.js"></script>
        <script>
            const gitalk = new Gitalk({
                clientID: 'ba9cd17e63036cd3c98b',
                clientSecret: 'bd34e1ebe2e8bc26cef1c91be2d529e74bfb529e',
                repo: 'jcker',
                owner: '3-8',
                admin: ['3-8'],
                id: location.pathname,
                distractionFreeMode: false
            });

            gitalk.render('container');
        </script>
    </div>
<#include "footer.ftl">

    <script>
        hljs.initHighlightingOnLoad();
    </script>
</body>
</html>