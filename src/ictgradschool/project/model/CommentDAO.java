package ictgradschool.project.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {


    public List<Comments> getAllComments(Connection conn) {
        List<Comments> comments = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
//            add some kind of query here..
//            try(ResultSet rs = stmt.executeQuery()){
//            while(rs.next)...}
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comments;

    }


    private static Comments createCommentFromResultSet(ResultSet rs) throws SQLException {

        Comments comment = new Comments(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getInt(5),
                rs.getInt(6)


        );
        return comment;
    }

}
