package ictgradschool.project.util;

public class AutoIncrementIDGenerator {
    private static int currID = 0;
    public static int getID() {
        currID ++;
        return currID;
    }

}
