package ictgradschool.project.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    // TODO get comment list by article ID
    public static List<Comment> getCommentListByArticleID(Connection connection, int articleID)  throws SQLException{
        return null;
    }

    // TODO get comment list by comment ID
    private static List<Comment> getCommentListByCommentID(Connection connection, int commentID) throws SQLException {
        return null;
    }

    // TODO add comment on article

    // TODO add comment on comment

    // TODO delete comment by comment ID

    // TODO edit comment, given comment ID


}
