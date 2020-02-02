package ictgradschool.project.util;

public class ArticleContentUtil {
    public static String generateBriefFromHtml (String innerHtml) {
        String[] contents = innerHtml.split("<.*>");
        int briefLength = 0;
        StringBuffer briefBuffer = new StringBuffer();
        int i = 0;
        while(briefLength < 96 && i < contents.length) {
            briefBuffer.append(contents[i]);
            i++;
            briefLength += briefBuffer.length();
        }
        return briefBuffer.toString().substring(0,Math.min(96, briefBuffer.length()));

    }
}
