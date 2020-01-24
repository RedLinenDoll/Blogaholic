package ictgradschool.project.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {
    //some methods here


    public static Article createArticleFromResultSet(ResultSet rs) throws SQLException{

    Article article= new Article(
            rs.getInt(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            rs.getInt(6),
            rs.getInt(7)
    );

return article;
    }

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
