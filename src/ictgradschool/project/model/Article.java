package ictgradschool.project.model;

import java.sql.Timestamp;

public class Article {
    private int articleID;
    private String title;
    private String content;
    private String brief;
    private Timestamp timeEdited;
    private Timestamp timeCreated;
    private int likes;
    private int dislikes;


    // full article fetch
    public Article(int articleID, String title, String content, String brief, Timestamp timeEdited, Timestamp timeCreated, int likes, int dislikes) {
        this.articleID = articleID;
        this.title = title;
        this.content = content;
        this.brief = brief;
        this.timeEdited = timeEdited;
        this.timeCreated = timeCreated;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    // article creation without brief
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
        this.likes = 0;
        this.dislikes = 0;
    }


    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
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
