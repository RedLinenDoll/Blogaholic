package ictgradschool.project.model;

import ictgradschool.project.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {


    private static User createVisitorFromResultSet(ResultSet rs) throws SQLException {
        User visitor = new User (

                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getInt(6)




                );

return visitor;
    }


    private static User createUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User(
                rs.getInt(1), // user ID
                rs.getString(2), // username
                rs.getString(3), //
                rs.getString(4),
                rs.getString(5),
                rs.getInt(6),
                rs.getString(7),
                rs.getString(8)

        );
        return user;
    }

    public static User getUserById(Connection connection, int id) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement("SELECT (username, avatar_path, " +
                "blog_name, blog_description, theme_color, layout_id) FROM users_db " +
                "WHERE user_id = ?")) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // TODO needing the updated User constructor
                }

            }

        }
        return null;
    }
}
