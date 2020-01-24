package ictgradschool.project;

public class Article {
    private int articleID;
    private String title;
    private String content;
    private String timeEdited;
    private String timeCreated;
    private int likes;
    private int dislikes;


    public Article(int articleID, String title, String content, String timeCreated, String timeEdited, int likes, int dislikes) {
        this.articleID = articleID;
        this.title = title;
        this.content = content;
        this.timeEdited = timeEdited;
        this.timeCreated = timeCreated;
        this.likes = likes;
        this.dislikes = dislikes;
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

    public String getTimeEdited() {
        return timeEdited;
    }

    public void setTimeEdited(String timeEdited) {
        this.timeEdited = timeEdited;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }
}
