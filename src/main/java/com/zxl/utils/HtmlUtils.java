package com.zxl.utils;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HtmlUtils {
    private HtmlUtils() {
    }

    public static String replaceHtmlTagAttr(String sourceStr, String tag, String attr, ReplaceCallback callback) {
        Pattern tagPattern = Pattern.compile(MessageFormat.format("<\\s*{0}\\s+([^>]*)\\s*>", tag), Pattern.CASE_INSENSITIVE);
        Pattern attrPattern = Pattern.compile(MessageFormat.format("({0}=\\s*\")([^\"]+)(.*)", attr), Pattern.CASE_INSENSITIVE);
        Matcher tagMatcher = tagPattern.matcher(sourceStr);
        StringBuffer retBuffer = new StringBuffer();
        int index = 0;
        while (tagMatcher.find()) {
            StringBuffer buffer = new StringBuffer();
            Matcher attrMatcher = attrPattern.matcher(tagMatcher.group());
            if (attrMatcher.find()) {
                attrMatcher.appendReplacement(buffer, MessageFormat.format("{0}{1}{2}", attrMatcher.group(1),
                        callback.buildReturnStr(attrMatcher.group(2), index), attrMatcher.group(3)));
            }
            attrMatcher.appendTail(buffer);
            tagMatcher.appendReplacement(retBuffer, buffer.toString());
            index++;
        }
        tagMatcher.appendTail(retBuffer);
        return retBuffer.toString();
    }

    public interface ReplaceCallback {
        String buildReturnStr(String replacedStr, int index);
    }
}
