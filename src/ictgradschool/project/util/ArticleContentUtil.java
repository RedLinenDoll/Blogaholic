package ictgradschool.project.util;

import org.jsoup.Jsoup;

public class ArticleContentUtil {
    public static String generateBriefFromHtml (String innerHtml) {

        String plainText= Jsoup.parse(innerHtml).text();
        int length = plainText.length();
        int briefLength = Math.min(length, 96);

        return plainText.substring(0, briefLength);


    }

    public static String generateTextFromHtml (String innerHtml) {

        return Jsoup.parse(innerHtml).text();


    }
}
