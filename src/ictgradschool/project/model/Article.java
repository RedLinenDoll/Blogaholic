package ictgradschool.project.model;

import java.sql.Timestamp;

public class Article {
    private int articleID;
    private String articleTitle;
    private String articleContent;
    private String articleBrief;
    private Timestamp timeCreated;
    private Timestamp timeEdited;
    private int likesCount;
    private int dislikesCount;

    public Article() {
    }

    // full article fetch
    public Article(int articleID, String articleTitle, String articleContent, String articleBrief, Timestamp timeCreated, Timestamp timeEdited, int likesCount, int dislikesCount) {
        this.articleID = articleID;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleBrief = articleBrief;
        this.timeEdited = timeEdited;
        this.timeCreated = timeCreated;
        this.likesCount = likesCount;
        this.dislikesCount = dislikesCount;
    }

    // article creation without brief
    public Article(String articleTitle, String articleContent) {
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.likesCount = 0;
        this.dislikesCount = 0;
    }

    // brief article
    public Article(int articleID, String articleTitle, String articleBrief, Timestamp timeCreated, Timestamp timeEdited, int likesCount, int dislikesCount) {
        this.articleID = articleID;
        this.articleTitle = articleTitle;
        this.articleBrief = articleBrief;
        this.timeEdited = timeEdited;
        this.timeCreated = timeCreated;
        this.likesCount = likesCount;
        this.dislikesCount = dislikesCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getDislikesCount() {
        return dislikesCount;
    }

    public void setDislikesCount(int dislikesCount) {
        this.dislikesCount = dislikesCount;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }


    public String getArticleBrief() {
        return articleBrief;
    }

    public void setArticleBrief(String articleBrief) {
        this.articleBrief = articleBrief;
    }

    public Timestamp getTimeEdited() {
        return timeEdited;
    }

    public void setTimeEdited(Timestamp timeEdited) {
        this.timeEdited = timeEdited;
    }

    public Timestamp getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Timestamp timeCreated) {
        this.timeCreated = timeCreated;
    }
}
