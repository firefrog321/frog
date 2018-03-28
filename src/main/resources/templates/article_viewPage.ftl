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
            </div>
        ${article.content}
        </div>
    </div>
<#include "footer.ftl">
</body>
</html>