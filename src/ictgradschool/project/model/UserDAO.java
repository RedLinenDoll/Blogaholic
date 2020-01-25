package ictgradschool.project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {


    private static User createLoggedUserFromResultSet(ResultSet rs) throws SQLException {
        User visitor = new User(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3)
        );
        return visitor;
    }

    private static User createBlogAuthorFromResultSet(ResultSet rs) throws SQLException {
        User author = new User(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getInt(7)
        );
        return author;
    }

    public static User getAuthorById(Connection connection, int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT user_id, username, avatar_path, " +
                        "blog_name, blog_description, theme_color, layout_id " +
                        "FROM users_db " +
                        "WHERE user_id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createBlogAuthorFromResultSet(resultSet);
                } else
                    return null;
            }
        }
    }

    public static User getLoggedUserById(Connection connection, int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT user_id, username, avatar_path " +
                "FROM users_db WHERE user_id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createLoggedUserFromResultSet(
                            resultSet
                    );
                } else
                    return null;
            }
        }
    }
}
