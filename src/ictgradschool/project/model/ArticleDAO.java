package ictgradschool.project.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

public class ArticleDAO {

    //TODO delete article by article ID

    public static boolean deleteArticle(Connection connection, int articleID) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM article_db WHERE article_id=?")) {
            preparedStatement.setInt(1, articleID);
            int rowUpdated = preparedStatement.executeUpdate();
            return rowUpdated == 1;
        }
    }


    //TODO add new article, given author ID and new article object


    public static int addArticle(Connection connection, int authorID, Article article) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT  INTO article_db(title, content,  number_of_likes, number_of_dislikes, author_id) VALUES(?,?,?,?,?) ", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, article.getArticleTitle());
            preparedStatement.setString(2, article.getArticleContent());
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            preparedStatement.setInt(5, authorID);

            int rowUpdated = preparedStatement.executeUpdate();
            if (rowUpdated !=1) return 0;
            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                keys.next();
                int articleID = keys.getInt(1);
                return articleID;
            }

        }

    }


    //TODO edit article, given article ID and new article object

    public static boolean editArticle(Connection connection, Article article, int articleID) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE article_db SET title=?,content=?, edit_time=CURRENT_TIMESTAMP WHERE article_id=?")) {
            preparedStatement.setString(1, article.getArticleTitle());
            preparedStatement.setString(2, article.getArticleContent());
            preparedStatement.setInt(3, articleID);

            int rowUpdated = preparedStatement.executeUpdate();
            return rowUpdated == 1;
        }
    }


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
