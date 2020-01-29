package ictgradschool.project.model;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    // TODO add comment on article
    public static boolean addCommentToArticle(Connection connection, int articleID, Comment comment) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO comment_db(target_article_id,body,number_of_likes,number_of_dislikes) VALUES (?,?,?,?)")) {
            preparedStatement.setInt(1, articleID);
            preparedStatement.setString(2, comment.getCommentBody());
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            int rowUpdated = preparedStatement.executeUpdate();
            return rowUpdated == 1;

        }
    }


    // TODO add comment on comment


    public static boolean addCommentToComment(Connection connection, int commenterID, Comment comment) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO comment_db(commenter_id,body,number_of_likes,number_of_dislikes) VALUES (?,?,?,?)")) {
            preparedStatement.setInt(1, commenterID);
            preparedStatement.setString(2, comment.getCommentBody());
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            int rowUpdated = preparedStatement.executeUpdate();
            return rowUpdated == 1;

        }

    }


    // TODO delete comment by comment ID

    public static boolean deleteCommentByID(Connection connection, int commentID) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM comment_db WHERE comment_id=?")) {
            preparedStatement.setInt(1, commentID);
            int rowUpdated = preparedStatement.executeUpdate();
            return rowUpdated == 1;

        }
    }

    // TODO edit comment, given comment ID


    public static boolean editComment(Connection connection, Comment comment, int commentID) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE comment_db SET body=? WHERE comment_id=?")) {
            preparedStatement.setString(1, comment.getCommentBody());
            preparedStatement.setInt(2, commentID);

            int rowUpdated = preparedStatement.executeUpdate();
            return rowUpdated == 1;
        }
    }


    public static List<Comment> getCommentListByArticleID(Connection connection, int articleID) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT comment.comment_id, comment.body, user.username, user.avatar_path, comment.created_time,\n" +
                "       comment.edit_time, comment.number_of_likes, comment.number_of_dislikes\n" +
                "FROM comment_db AS comment, users_db AS user\n" +
                "WHERE comment.target_article_id = ? and comment.commenter_id = user.user_id;")) {
            statement.setInt(1, articleID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
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
        try (PreparedStatement statement = connection.prepareStatement("SELECT comment.comment_id, comment.body, user.username, user.avatar_path, comment.created_time,\n" +
                "       comment.edit_time, comment.number_of_likes, comment.number_of_dislikes\n" +
                "FROM comment_db AS comment, users_db AS user\n" +
                "WHERE comment.target_comment_id = ? and comment.commenter_id = user.user_id;")) {
            statement.setInt(1, commentID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
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
