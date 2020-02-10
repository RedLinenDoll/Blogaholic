package ictgradschool.project.control;

import ictgradschool.project.model.Article;
import ictgradschool.project.model.ArticleDAO;
import ictgradschool.project.util.DBConnectionUtils;
import ictgradschool.project.util.JSONResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "article-feed", urlPatterns = "/article-feed")
public class ArticleFeedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestOption = request.getParameter("request-option");
        int userID;
        int articleID;
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            switch (requestOption) {
                case "load-feed-article-ids": // Gives IDs of articles written by users followed by current logged in user
                    userID = Integer.parseInt(request.getParameter("user-id"));
                    List<Integer> feedArticleIds = ArticleDAO.getFeedArticleIDListByUserID(connection, userID);
                    JSONResponse.send(response, feedArticleIds);
                    return;
                case "load-more-article-ids": // Gives IDs of most recent articles, after the followed users' articles are all fed.
                    userID = Integer.parseInt(request.getParameter("user-id"));
                    List<Integer> moreArticleIds = ArticleDAO.getRecentArticleIDListExceptFeedByUserID(connection, userID);
                    JSONResponse.send(response, moreArticleIds);
                    return;
                case "load-article-by-id":  // provides feed information about one article based on the article id.
                    articleID = Integer.parseInt(request.getParameter("article-id"));
                    Article feedArticle = ArticleDAO.getFeedArticleByArticleID(connection, articleID);
                    JSONResponse.send(response, feedArticle);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
