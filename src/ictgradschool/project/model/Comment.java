package ictgradschool.project.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Comment {

    private int commentID;
    private String commentBody;
    private int commenterID;
    private String commenterUsername;
    private String avatarPath;
    private Timestamp timeCreated;
    private Timestamp timeEdited;
    private int likesCount;
    private int dislikesCount;
    private List<Comment> childComments;

    public Comment() {
    }

    // for comment creation
    public Comment(int commentID, String commentBody, int commenterID, String commenterUsername, String avatarPath, Timestamp timeCreated, Timestamp timeEdited, int likesCount, int dislikesCount) {
        this.commentID = commentID;
        this.commentBody = commentBody;
        this.commenterID = commenterID;
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

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public int getCommenterID() {
        return commenterID;
    }

    public void setCommenterID(int commenterID) {
        this.commenterID = commenterID;
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

    public String getCommenterUsername() {
        return commenterUsername;
    }

    public void setCommenterUsername(String commenterUsername) {
        this.commenterUsername = commenterUsername;
    }
}
