package ictgradschool.project.model;

import ictgradschool.project.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

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
