package ictgradschool.project.model;

import ictgradschool.project.util.ArticleContentUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

public class ArticleDAO {

    public static boolean deleteArticle(Connection connection, int articleID) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM article_db WHERE article_id=?")) {
            preparedStatement.setInt(1, articleID);
            int rowUpdated = preparedStatement.executeUpdate();
            return rowUpdated == 1;
        }
    }

    public static int addArticle(Connection connection, int authorID, Article article) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT  INTO article_db(title, content,  number_of_likes, number_of_dislikes, author_id, brief) VALUES(?,?,?,?,?,?) ", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, article.getArticleTitle());
            preparedStatement.setString(2, article.getArticleContent());
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            preparedStatement.setInt(5, authorID);
            preparedStatement.setString(6, ArticleContentUtil.generateBriefFromHtml(article.getArticleContent()));

            int rowUpdated = preparedStatement.executeUpdate();
            if (rowUpdated !=1) return 0;
            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                keys.next();
                int articleID = keys.getInt(1);
                return articleID;
            }

        }

    }

    public static boolean editArticle(Connection connection, Article article, int articleID) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE article_db SET brief=?, title=?,content=?, edit_time=CURRENT_TIMESTAMP WHERE article_id=?")) {
            preparedStatement.setString(1, ArticleContentUtil.generateBriefFromHtml(article.getArticleContent()));
            preparedStatement.setString(2, article.getArticleTitle());
            preparedStatement.setString(3, article.getArticleContent());
            preparedStatement.setInt(4, articleID);

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
        String titleText = ArticleContentUtil.generateTextFromHtml(resultSet.getString(2));// article title
        if (titleText.length() == 0) titleText = "Untitled";

        return new Article(
                resultSet.getInt(1), // article ID
                titleText,
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
                        "WHERE author_id = ? ORDER BY created_time DESC")) {
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
                getPlainTitle(resultSet.getString(2)),
                resultSet.getString(3), //articleBrief
                resultSet.getTimestamp(4), //timeCreated
                resultSet.getTimestamp(5), //timeEdited
                resultSet.getInt(6), //likeCount
                resultSet.getInt(7) //dislikeCount
        );
    }

    public static List<Article> searchArticleByKeyword(Connection connection, String keyword) throws SQLException{
        List<Article> articles = new ArrayList<>();
       String sql = "SELECT article_id, title, content, created_time, edit_time, number_of_likes, number_of_dislikes, author_id FROM article_db WHERE (title LIKE CONCAT('%', ? , '%')) OR (content LIKE CONCAT('%', ?, '%'))";
       try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
           preparedStatement.setString(1, keyword);
           preparedStatement.setString(2, keyword);
           try(ResultSet resultSet = preparedStatement.executeQuery()) {
               while(resultSet.next()) {
                   articles.add(createSearchResultArticleFromResultSet(connection, resultSet));
               }
               return articles;
           }
       }
    }

    private static Article createSearchResultArticleFromResultSet(Connection connection, ResultSet resultSet) throws SQLException{
        Article article = new Article();
        article.setArticleID(resultSet.getInt(1));
        article.setArticleTitle(getPlainTitle(resultSet.getString(2)));
        article.setArticleContent(ArticleContentUtil.generateTextFromHtml(resultSet.getString(3)));
        article.setTimeCreated(resultSet.getTimestamp(4));
        article.setTimeEdited(resultSet.getTimestamp(5));
        article.setLikesCount(resultSet.getInt(6));
        article.setDislikesCount(resultSet.getInt(7));
        article.setAuthor(UserDAO.getAuthorById(connection, resultSet.getInt(8)));
        return article;
    }

    private static String getPlainTitle(String originalHTML) {
        String plainText = ArticleContentUtil.generateBriefFromHtml(originalHTML);
        if (plainText.length() == 0) return "Untitled";
        else return plainText;
    }
}
