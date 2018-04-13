<!DOCTYPE html>
<html lang="zh">
<head>
    <#include "header.ftl">
    <!--editor.md css-->
    <link rel="stylesheet" href="/editormd/css/editormd.min.css"/>
    <#--<link rel="stylesheet" href="/editormd/css/editormd.css"/>-->
</head>
<body>

<#include "nav.ftl">


    <form action="/article/save" method="post">

        <div style="margin-top: 80px">
            <#--<input type="hidden" name="articleId" value="">-->
            <label>标题</label>
            <input type="text" name="title">
            <label>标签</label>
            <input type="text" name="tags" id="tags" data-role="tagsinput">
            <input type="submit" value="Save">
        </div>

        <div id="layout">
            <div id="editormd">
                <textarea name = "content" style="display:none;"></textarea>
            </div>
        </div>
    </form>


<#include "footer.ftl">
<script src="/editormd/editormd.js"></script>
<script type="text/javascript">
    var testEditor;

    $(function () {
        testEditor = editormd("editormd", {
            width: "100%",
            height: 540,
            syncScrolling: "single",
            path: "/editormd/lib/"
        });
    });
</script>

</body>
</html>