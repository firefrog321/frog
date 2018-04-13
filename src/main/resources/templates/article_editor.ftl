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

    <div style="margin-top: 80px ">
            <#if article??>
                <input type="hidden" name="articleId" value="${article.articleId!}">
                <label>标题</label>
                <input type="text" name="title" value="${article.title!}">
                <label>标签</label>
                <input type="text" name="tags" id="tags" data-role="tagsinput" value="${article.tags!}">
                <input type="submit" value="Save">
                <div id="layout">
                    <div id="editormd">
                        <textarea name="content" style="display:none;">${article.content!}</textarea>
                    </div>
                </div>

            <#else >
                <label>标题</label>
                <input type="text" name="title" >
                <label>标签</label>
                <input type="text" name="tags" id="tags" data-role="tagsinput" >
                <input type="submit" value="Save">

                <div id="layout">
                    <div id="editormd">
                        <textarea name="content" style="display:none;"></textarea>
                    </div>
                </div>
            </#if>
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
            path: "/editormd/lib/",

            emoji: true,  //启用表情
            imageUpload: true, //开启上传图片
            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL: "/upload/mdImage"
        });
        /**
         * 将默认的github表情设置为项目中的图片，服务器的表情那个不能用了
         */
        editormd.emoji = {
            path: '/editormd/emojis/',
            ext: '.png'
        };

    });


</script>

</body>
</html>