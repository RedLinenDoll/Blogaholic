package ictgradschool.project.model;

import java.sql.Timestamp;
import java.util.List;

public class Comment {
    // TODO This structure needs to be reviewed and
    //  improved to support arbitrary levels of comment: maintain a comment arraylist.

    private int commentID;
    private String content;
    String commenterUsername;
    String avatarPath;
    private Timestamp timeCreated;
    private Timestamp timeEdited;
    private int likesCount;
    private int dislikesCount;
    List<Comment> childComments;

    





}
