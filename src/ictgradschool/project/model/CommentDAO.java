package ictgradschool.project.model;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    public static boolean addComment(Connection connection, boolean isToArticle, int targetID, Comment comment) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(isToArticle ?
                "INSERT INTO comment_db(target_article_id,body,number_of_likes,number_of_dislikes, commenter_id) VALUES (?,?,?,?,?)"
                : "INSERT INTO comment_db(target_comment_id,body,number_of_likes,number_of_dislikes, commenter_id) VALUES (?,?,?,?,?)")) {

            preparedStatement.setInt(1, targetID);
            preparedStatement.setString(2, comment.getCommentBody());
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            preparedStatement.setInt(5, comment.getCommenterID());
            int rowUpdated = preparedStatement.executeUpdate();
            return rowUpdated == 1;
        }
    }


    public static boolean deleteCommentByID(Connection connection, int commentID) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM comment_db WHERE comment_id=?")) {
            preparedStatement.setInt(1, commentID);
            int rowUpdated = preparedStatement.executeUpdate();
            return rowUpdated == 1;

        }
    }


    public static boolean editComment(Connection connection, Comment comment) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE comment_db SET body=?, edit_time=CURRENT_TIMESTAMP WHERE comment_id=?")) {
            preparedStatement.setString(1, comment.getCommentBody());
            preparedStatement.setInt(2, comment.getCommentID());
            int rowUpdated = preparedStatement.executeUpdate();
            return rowUpdated == 1;
        }
    }

    public static List<Comment> getCommentListByID(Connection connection, int targetID, boolean isForArticle) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT comment.comment_id, comment.body, user.user_id," +
                "user.username, user.avatar_path, comment.created_time, comment.edit_time, " +
                "comment.number_of_likes, comment.number_of_dislikes " +
                "FROM comment_db AS comment, users_db AS user " +
                (isForArticle ? "WHERE comment.target_article_id = ? " : "WHERE comment.target_comment_id = ? ") +
                "and comment.commenter_id = user.user_id " +
                "ORDER BY comment.created_time DESC";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, targetID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Comment comment = createCommentFromResultSet(resultSet);
                    comment.setChildComments(getCommentListByCommentID(connection, comment.getCommentID()));
                    comments.add(comment);
                }
                return comments;
            }
        }

    }

    public static List<Comment> getCommentListByArticleID(Connection connection, int articleID) throws SQLException {

        return getCommentListByID(connection, articleID, true);
    }

    private static List<Comment> getCommentListByCommentID(Connection connection, int commentID) throws SQLException {
        return getCommentListByID(connection, commentID, false);
    }

    private static Comment createCommentFromResultSet(ResultSet resultSet) throws SQLException {
        return new Comment(
                resultSet.getInt(1), // commentID
                resultSet.getString(2), // commentBody
                resultSet.getInt(3), //commenterID
                resultSet.getString(4), // commenterUsername
                resultSet.getString(5), // avatarPath
                resultSet.getTimestamp(6), // create time
                resultSet.getTimestamp(7), // edit time
                resultSet.getInt(8), // like count
                resultSet.getInt(9) // dislike count
        );
    }


}
