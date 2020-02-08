package ictgradschool.project.util;

import org.jsoup.Jsoup;

/* In our website, users are only allowed to use html marks to style the CONTENT of their article, with support of TinyMCE */
/* Therefore, this class is responsible for
1) create a clean, plain-text brief for TinyMCE-generated article content Html.
2) remove html tags and scripts from article title, blog name, blog description, to give a consistent look and avoid XSS attack.
*/

public class HtmlProcessUtil {
    public static String generateBriefFromHtml (String innerHtml) {
        if(innerHtml == null) return "";

        String plainText= Jsoup.parse(innerHtml).text();
        int length = plainText.length();
        int briefLength = Math.min(length, 96);

        return plainText.substring(0, briefLength);

    }

    public static String generateTextFromHtml (String innerHtml) {
        if (innerHtml == null) return "";

        return Jsoup.parse(innerHtml).text();

    }

}
