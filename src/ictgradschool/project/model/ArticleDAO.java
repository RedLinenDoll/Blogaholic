package ictgradschool.project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {
    //some methods here


    public static Article createArticleFromResultSet(ResultSet rs) throws SQLException {
        return new Article(
                rs.getInt(1), //articleID
                rs.getString(2), //articleTitle
                rs.getString(3), //articleBrief
                rs.getTimestamp(4), //timeCreated
                rs.getTimestamp(5), //timeEdited
                rs.getInt(6), //likeCount
                rs.getInt(7) //dislikeCount
        );
    }


    public static List<Article> getArticleBriefListByAuthor(Connection connection, int authorID) throws SQLException {
        List<Article> articles = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement
                ("SELECT article_id, title, brief, created_time, edit_time, number_of_likes, number_of_dislikes FROM article_db WHERE author_id = ?")) {
            statement.setInt(1, authorID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    articles.add(createArticleFromResultSet(resultSet));
                }
            }

        }
        return articles;
    }
}
