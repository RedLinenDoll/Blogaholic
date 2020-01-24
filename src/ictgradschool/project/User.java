package ictgradschool.project;

public class User {
    private int userID;
    private String username;
    private String password;
    private String avatarPath;
    private String blogName;
    private String blogDescription;
    private String themeColor;
    private int layoutID;

    public User(int userID, String username, String password, String blogName, String blogDescription, int layoutID, String themeColor, String avatarPath) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.avatarPath = avatarPath;
        this.blogName = blogName;
        this.blogDescription = blogDescription;
        this.themeColor = themeColor;
        this.layoutID=layoutID;
    }

    public User(int userID, String avatarPath, String blogName, String blogDescription, String themeColor, int layoutID) {
        this.userID = userID;
        this.avatarPath = avatarPath;
        this.blogName = blogName;
        this.blogDescription = blogDescription;
        this.themeColor = themeColor;
        this.layoutID = layoutID;
    }

    public int getLayoutID() {
        return layoutID;
    }

    public void setLayoutID(int layoutID) {
        this.layoutID = layoutID;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBlogDescription() {
        return blogDescription;
    }

    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription;
    }

    public String getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(String themeColor) {
        this.themeColor = themeColor;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}
