package ictgradschool.project.util;

import org.jsoup.Jsoup;

public class ArticleContentUtil {
    public static String generateBriefFromHtml (String innerHtml) {

        String plainText= Jsoup.parse(innerHtml).text();
        return plainText.substring(0, Math.min(96, plainText.length()));


    }
}
