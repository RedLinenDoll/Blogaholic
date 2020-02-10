package ictgradschool.project.util;

public class AutoIncrementNumberGenerator {
    private static int currUniqueNum = 0;

    // This method is used by ArticleImageUpload to ensure unique image file names for arbitrary number of images for articles.
    synchronized public static int getUniqueNum() {
        currUniqueNum++;
        return currUniqueNum;
    }

}
