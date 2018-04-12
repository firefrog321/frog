<!DOCTYPE html>
<html lang="utf-8">
<#include "header.ftl">
<body class="index_body">
<#include "nav.ftl">
<div class="container" style="margin: 100px auto 10px;">
    <!--SpringBoot错误视图提供了以下错误属性：
    timestamp：错误发生时间；
    status：HTTP状态吗；
    error：错误原因；
    exception：异常的类名；
    message：异常消息（如果这个错误是由异常引起的）；
    errors：BindingResult异常里的各种错误（如果这个错误是由异常引起的）；
    trace：异常跟踪信息（如果这个错误是由异常引起的）；
    path：错误发生时请求的URL路径。-->
<#-- <h1>卧槽。。。报错了！${status!}</h1>
 <hr>
 异常跟踪信息:${stackTrace!}-->
    <h1>卧槽。。。报错了！${status!}</h1>
    ${cause!}
    <hr>
    ${error!}
    <hr>
    ${message!}
    <hr>
    ${stackTrace!}


</div>
<#include "footer.ftl">
</body>
</html>