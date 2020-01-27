package ictgradschool.project.model;

public class User {
    private Integer userID;
    private String username;
    private String passwordHashBase64;
    private String saltHashBase64;
    private int saltLength;
    private int iterationNum;
    private String avatarPath;
    private String blogName;
    private String blogDescription;
    private String themeColor;
    private int layoutID;

    // user authentication with ID
    public User(Integer userID, String username, String passwordHashBase64, String saltHashBase64, int saltLength, int iterationNum) {
        this.userID = userID;
        this.username = username;
        this.passwordHashBase64 = passwordHashBase64;
        this.saltHashBase64 = saltHashBase64;
        this.saltLength = saltLength;
        this.iterationNum = iterationNum;
    }

    // user full creation, database will assign a user ID.
    public User(String username, String passwordHashBase64, String saltHashBase64, int saltLength, int iterationNum, String avatarPath, String blogName, String blogDescription, String themeColor, int layoutID) {
        this.username = username;
        this.passwordHashBase64 = passwordHashBase64;
        this.saltHashBase64 = saltHashBase64;
        this.saltLength = saltLength;
        this.iterationNum = iterationNum;
        this.avatarPath = avatarPath;
        this.blogName = blogName;
        this.blogDescription = blogDescription;
        this.themeColor = themeColor;
        this.layoutID = layoutID;
    }

    // logged in visitor info
    public User(Integer userID, String username, String avatarPath) {
        this.userID = userID;
        this.username = username;
        this.avatarPath = avatarPath;
    }

    // blog author
    public User(int userID, String username, String avatarPath, String blogName, String blogDescription, String themeColor, int layoutID) {
        this.userID = userID;
        this.username = username;
        this.avatarPath = avatarPath;
        this.blogName = blogName;
        this.blogDescription = blogDescription;
        this.themeColor = themeColor;
        this.layoutID = layoutID;
    }

    public User() {

    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHashBase64() {
        return passwordHashBase64;
    }

    public void setPasswordHashBase64(String passwordHashBase64) {
        this.passwordHashBase64 = passwordHashBase64;
    }

    public String getSaltHashBase64() {
        return saltHashBase64;
    }

    public void setSaltHashBase64(String saltHashBase64) {
        this.saltHashBase64 = saltHashBase64;
    }

    public int getSaltLength() {
        return saltLength;
    }

    public void setSaltLength(int saltLength) {
        this.saltLength = saltLength;
    }

    public int getIterationNum() {
        return iterationNum;
    }

    public void setIterationNum(int iterationNum) {
        this.iterationNum = iterationNum;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
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

    public int getLayoutID() {
        return layoutID;
    }

    public void setLayoutID(int layoutID) {
        this.layoutID = layoutID;
    }
}
