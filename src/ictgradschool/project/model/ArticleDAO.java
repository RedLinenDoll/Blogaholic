package ictgradschool.project.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {
    //some methods here


    public static Article createArticleFromResultSet(ResultSet rs) throws SQLException {

        // there is  rs.getTimeStamp(int ) to use for time.
        rs.getTimestamp(4);
        return null;

    };


    private static List<Article>getAllArticles(Connection conn){
        List<Article> articles = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
//            add some kind of query here..
//            try(ResultSet rs = stmt.executeQuery()){
//            while(rs.next)...}
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articles;

    }


}
