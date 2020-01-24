package ictgradschool.project;

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
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getInt(6),
                rs.getString(7),
                rs.getString(8)

        );
        return user;
    }
}
