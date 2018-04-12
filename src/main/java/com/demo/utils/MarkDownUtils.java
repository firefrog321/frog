package com.demo.utils;

import com.vdurmont.emoji.EmojiParser;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by <a href='http://jcker.org'>Alan Turing</a>
 * on 2018-02-25 at 3:48 AM
 *
 * @version 1.0
 */
public class MarkDownUtils {
    /**
     * markdown转换为html
     *
     * @param markdown
     * @return
     */
    public static String mdToHtml(String markdown) {
        if (StringUtils.isEmpty(markdown)) {
            return "";
        }

        List<Extension> extensions = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder().extensions(extensions).build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .attributeProviderFactory(context -> new LinkAttributeProvider())
                .extensions(extensions).build();

        String content = renderer.render(document);
        content = emoji(content);

        return content;
    }

    /**
     * 提取html中的文字
     *
     * @param html
     * @return
     */
    public static String htmlToText(String html) {
        if (!StringUtils.isEmpty(html)) {
            return html.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
        }
        return "";
    }

    public static String mdToText(String md) {
        return htmlToText(mdToHtml(md));
    }

    static class LinkAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            if (node instanceof Link) {
                attributes.put("target", "_blank");
            }
        }
    }

    /**
     * An :grinning:awesome :smiley:string &#128516;with a few :wink:emojis!
     * <p>
     * 这种格式的字符转换为emoji表情
     *
     * @param value
     * @return
     */
    public static String emoji(String value) {
        return EmojiParser.parseToUnicode(value);
    }


}
