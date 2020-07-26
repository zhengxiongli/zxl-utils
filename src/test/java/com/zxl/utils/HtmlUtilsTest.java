package com.zxl.utils;


import org.junit.Assert;
import org.junit.Test;


public class HtmlUtilsTest {
    @Test
    public void replaceHtmlTagAttrTest() {
        StringBuffer content = new StringBuffer();
        content.append("<ul class=\"imgBox\"><li><img id=\"160424\" src=\"src0\" class=\"src_class\"></li>");
        content.append("<li><img id=\"150628\" src=\"src1\" class=\"src_class\"></li></ul>sdfsfsf");
        String str = HtmlUtils.replaceHtmlTagAttr(content.toString(), "img", "src", (src, index) -> {
            return "http://www.baidu.com/" + index;
        });
        Assert.assertEquals(content.toString().replace("src0", "http://www.baidu.com/0")
                .replace("src1", "http://www.baidu.com/1"), str);
    }

}
