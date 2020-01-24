package ictgradschool.project;

public class Comments {
    private int commentID;
    private String content;
    private int likes;
    private int dislikes;
    private String timeCreated;
    private String timeEdited;


    public Comments(int commentID, String content, String timeCreated, String timeEdited,int likes,int dislikes) {
        this.commentID = commentID;
        this.content = content;
        this.likes = likes;
        this.dislikes = dislikes;
        this.timeCreated = timeCreated;
        this.timeEdited = timeEdited;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getTimeEdited() {
        return timeEdited;
    }

    public void setTimeEdited(String timeEdited) {
        this.timeEdited = timeEdited;
    }
}
