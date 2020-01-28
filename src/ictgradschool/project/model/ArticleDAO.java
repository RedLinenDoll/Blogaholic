package ictgradschool.project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {

    //TODO delete article by article ID

    //TODO add new article, given author ID and new article object

    //TODO edit article, given article ID and new article object
    public static List<Article> getRecentBriefArticleList(Connection connection) throws SQLException {
        List<Article> articles = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT article_id, title, brief, created_time, edit_time, number_of_likes, number_of_dislikes\n" +
                "FROM article_db\n" +
                "ORDER BY created_time DESC\n" +
                "LIMIT 10;")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    articles.add(createBriefArticleFromResultSet(resultSet));
                }
            }
        }
        return articles;
    }

    public static Article getFullArticleByArticleID(Connection connection, int articleID) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT article_id, title, content, brief, created_time, edit_time, number_of_likes, number_of_dislikes " +
                        "FROM article_db " +
                        "WHERE article_id = ?;")) {
            statement.setInt(1, articleID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createFullArticleFromResultSet(resultSet);
                } else return null;
            }
        }
    }

    private static Article createFullArticleFromResultSet(ResultSet resultSet) throws SQLException {
        return new Article(
                resultSet.getInt(1), // article ID
                resultSet.getString(2), // article title
                resultSet.getString(3), // article content
                resultSet.getString(4), // article brief
                resultSet.getTimestamp(5), // time edited
                resultSet.getTimestamp(6), // time created
                resultSet.getInt(7), // like count
                resultSet.getInt(8)  // dislike count
        );
    }

    public static List<Article> getBriefArticleListByAuthorID(Connection connection, int authorID) throws SQLException {
        List<Article> articles = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement
                ("SELECT article_id, title, brief, created_time, edit_time, number_of_likes, number_of_dislikes " +
                        "FROM article_db " +
                        "WHERE author_id = ?")) {
            statement.setInt(1, authorID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    articles.add(createBriefArticleFromResultSet(resultSet));
                }
            }
        }
        return articles;
    }

    private static Article createBriefArticleFromResultSet(ResultSet resultSet) throws SQLException {
        return new Article(
                resultSet.getInt(1), //articleID
                resultSet.getString(2), //articleTitle
                resultSet.getString(3), //articleBrief
                resultSet.getTimestamp(4), //timeCreated
                resultSet.getTimestamp(5), //timeEdited
                resultSet.getInt(6), //likeCount
                resultSet.getInt(7) //dislikeCount
        );
    }
}
