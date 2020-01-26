package ictgradschool.project.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Comment {

    private int commentID;
    private String body;
    private String commenterUsername;
    private String avatarPath;
    private Timestamp timeCreated;
    private Timestamp timeEdited;
    private int likesCount;
    private int dislikesCount;
    private List<Comment> childComments;

    // load comment
    public Comment(int commentID, String body, String commenterUsername, String avatarPath, Timestamp timeCreated, Timestamp timeEdited, int likesCount, int dislikesCount) {
        this.commentID = commentID;
        this.body = body;
        this.commenterUsername = commenterUsername;
        this.avatarPath = avatarPath;
        this.timeCreated = timeCreated;
        this.timeEdited = timeEdited;
        this.likesCount = likesCount;
        this.dislikesCount = dislikesCount;
        this.childComments = new ArrayList<>();
    }

    public void addChildComment(Comment comment) {
        childComments.add(comment);
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCommenterUsername() {
        return commenterUsername;
    }

    public void setCommenterUsername(String commenterUsername) {
        this.commenterUsername = commenterUsername;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Timestamp getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Timestamp timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Timestamp getTimeEdited() {
        return timeEdited;
    }

    public void setTimeEdited(Timestamp timeEdited) {
        this.timeEdited = timeEdited;
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

    public List<Comment> getChildComments() {
        return childComments;
    }

    public void setChildComments(List<Comment> childComments) {
        this.childComments = childComments;
    }
}
