package ictgradschool.project.model;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    // TODO add comment on article

    // TODO add comment on comment

    // TODO delete comment by comment ID

    // TODO edit comment, given comment ID


    public static List<Comment> getCommentListByArticleID(Connection connection, int articleID)  throws SQLException{
        List<Comment> comments = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement("SELECT comment.comment_id, comment.body, user.username, user.avatar_path, comment.created_time,\n" +
                "       comment.edit_time, comment.number_of_likes, comment.number_of_dislikes\n" +
                "FROM comment_db AS comment, users_db AS user\n" +
                "WHERE comment.target_article_id = ? and comment.commenter_id = user.user_id;")) {
            statement.setInt(1, articleID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    Comment comment = createCommentFromResultSet(resultSet);
                    comment.setChildComments(getCommentListByCommentID(connection, comment.getCommentID()));
                    comments.add(comment);
                }
            }
        }
        return comments;
    }

    private static List<Comment> getCommentListByCommentID(Connection connection, int commentID) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement("SELECT comment.comment_id, comment.body, user.username, user.avatar_path, comment.created_time,\n" +
                "       comment.edit_time, comment.number_of_likes, comment.number_of_dislikes\n" +
                "FROM comment_db AS comment, users_db AS user\n" +
                "WHERE comment.target_comment_id = ? and comment.commenter_id = user.user_id;")) {
            statement.setInt(1, commentID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    Comment comment = createCommentFromResultSet(resultSet);
                    comment.setChildComments(getCommentListByCommentID(connection, comment.getCommentID()));
                    comments.add(comment);
                }
            }
        }
        return comments;
    }

    private static Comment createCommentFromResultSet(ResultSet resultSet) throws SQLException {
        return new Comment(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getTimestamp(5),
                resultSet.getTimestamp(6),
                resultSet.getInt(7),
                resultSet.getInt(8)
        );
    }




}
