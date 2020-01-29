package ictgradschool.project.control;

import ictgradschool.project.model.Article;
import ictgradschool.project.model.ArticleDAO;
import ictgradschool.project.model.User;
import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "add-article", urlPatterns = "/add-article")
public class ArticleAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            User loggedUser = (User) request.getSession().getAttribute("loggedUser");
            int authorID = loggedUser.getUserID();
            Article article = new Article();
            article.setArticleTitle(request.getParameter("title"));
            article.setArticleContent(request.getParameter("content"));
            int createdID = ArticleDAO.addArticle(connection, authorID, article);
            response.sendRedirect("./article-view?articleID=" + createdID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
