<!DOCTYPE html>
<html lang="utf-8"
<#include "header.ftl">
<body>
<#include "nav.ftl">
<div class="container" style="margin-top: 100px;">
    <div class="row">
        <div class="col-md-8 main">
            <!--tags查询用-->
            <input id="queryTags" type="hidden" value="${queryTags!}">
            <#list articlePage.content as article>
                <div class="card mb-4 box-shadow">
                    <div class="card-body">
                        <div onclick="window.open('/article/' + ${article.articleId}, '_self');"></div>
                        <h3>
                            <a href="/article/${article.articleId}">${article.title!}</a>
                        </h3>

                        <p style="font-size: 18px ">${article.preview!}....</p>

                        <p>
                        <div class="row" style="margin-top: 30px;font-size: 20px;">
                            <div class="col-md-4 col-sm-6">
                                <span class="small">${article.createDate!}</span>
                                <span class="small">&nbsp;&nbsp;浏览（${article.viewNum!'0'}）</span>
                            </div>
                            <div class="col-md-8 col-sm-6">
                                <#list article.tags?split(",") as tag>
                                    <a class="small"
                                       href="/article/page/1?queryTags=${tag}">${tag}</a>
                                </#list>
                            </div>
                        </div>
                        </p>

                    </div>
                </div>
            </#list>

            <ul class="pagination-sm float-sm-right" id="pagination"></ul>

        </div>

        <div class="col-md-4 d-none d-md-block">
            <div style="position: fixed;">
                <img src="/images/0.gif" class=" frog-index-jokerimage ">
            </div>
        </div>

    </div>
</div>

<#include "footer.ftl">
<script>
    //分页
    $('#pagination').twbsPagination({
        startPage: ${articlePage.number + 1},
        totalPages: ${articlePage.totalPages},
        visiblePages: 10,
        initiateStartPageClick: false,
        first: "<<",
        prev: "<",
        next: ">",
        last: ">>",
        onPageClick: function (event, page) {
            window.location.href = "/article/page/" + page + "?queryTags=" + $("#queryTags").val();
        }
    });
</script>
</body>
</html>
