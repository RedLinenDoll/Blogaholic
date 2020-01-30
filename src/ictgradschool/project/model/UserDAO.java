package ictgradschool.project.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static List<User> getAllBlogUsers(Connection connection) throws SQLException {
        List<User> allBlogUsers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT user_id, username, avatar_path, blog_name, blog_description, theme_color, layout_id FROM users_db"
        )) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    allBlogUsers.add(createBlogAuthorFromResultSet(resultSet));
                }
            }
        }
        return allBlogUsers;
    }


    private static User createLoggedUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User(
                resultSet.getInt(1), // userID
                resultSet.getString(2), // username
                resultSet.getString(3),  // hashedPassword
                resultSet.getString(4), // hashed_salt
                resultSet.getInt(5), // salt_length
                resultSet.getInt(6) //iterationNumber
        );
        user.setLayoutID(resultSet.getInt(7));
        user.setThemeColor(resultSet.getString(8));
        user.setAvatarPath(resultSet.getString(9));
        return user;
    }

    private static User createBlogAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt(1), // userID
                resultSet.getString(2), // userName
                resultSet.getString(3), // avatarPath
                resultSet.getString(4), // blog name
                resultSet.getString(5), // blog description
                resultSet.getString(6), // theme color
                resultSet.getInt(7) // layout id
        );
    }

    public static User getAuthorById(Connection connection, int userID) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT user_id, username, avatar_path, blog_name, blog_description, theme_color, layout_id " +
                        "FROM users_db " +
                        "WHERE user_id = ?")) {
            statement.setInt(1, userID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createBlogAuthorFromResultSet(resultSet);
                } else
                    return null;
            }
        }
    }

    public static User getAuthorByArticleId(Connection connection, int articleID) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement
                ("SELECT user.user_id, username, avatar_path, blog_name, blog_description, theme_color, layout_id " +
                        "FROM article_db as article, users_db as user " +
                        "WHERE article.author_id = user.user_id " +
                        "AND article.article_id = ?;")) {
            statement.setInt(1, articleID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createBlogAuthorFromResultSet(resultSet);
                } else
                    return null;
            }
        }
    }


    public static User insertUser(Connection connection, User user) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users_db (username, hashed_password, hashed_salt, salt_length, iteration_number) VALUE (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPasswordHashBase64());
            preparedStatement.setString(3, user.getSaltHashBase64());
            preparedStatement.setInt(4, user.getSaltLength());
            preparedStatement.setInt(5, user.getIterationNum());

            int rowUpdated = preparedStatement.executeUpdate();
            if (rowUpdated == 1) {
                try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                    keys.next();
                    int userID = keys.getInt(1);
                    user.setUserID(userID);
                    return user;
                }
            }
        }
        return null;
    }


    public static User getLoggedUserByUsername(Connection connection, String username) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT user_id, username, hashed_password, hashed_salt, salt_length, iteration_number, layout_id, theme_color, avatar_path FROM users_db WHERE username = ?")) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return createLoggedUserFromResultSet(resultSet);
                } else return null;
            }
        }
    }

    public static int checkUsernameCount(Connection connection, String username) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(users_db.user_id) FROM users_db WHERE username = ?")) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    if (resultSet.getInt(1) >= 1) return 1;
                }
            }
        }
        return 0;
    }

    public static boolean editUserRealNameInfo(Connection connection, int userID, String firstName, String lastName, Date dateOfBirth, String selfIntroduction, boolean toShare) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement
                ("UPDATE users_db SET first_name = ?, last_name =?, date_of_birth = ?, self_introduction = ? WHERE user_id = ?;")) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setDate(3, dateOfBirth);
            preparedStatement.setString(4, selfIntroduction);
            preparedStatement.setInt(5, userID);

            int rowUpdated = preparedStatement.executeUpdate();
            return rowUpdated == 1;
        }

    }

    public static User setUserAvatarPath(Connection connection, int userID, String avatarPath) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users_db SET avatar_path = ? WHERE user_id = ?")) {
            preparedStatement.setString(1, avatarPath);
            preparedStatement.setInt(2, userID);
            int rowUpdated = preparedStatement.executeUpdate();
            if(rowUpdated==1)
                return getAuthorById(connection, userID);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
