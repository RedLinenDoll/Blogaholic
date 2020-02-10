package ictgradschool.project.control;

import ictgradschool.project.model.Article;
import ictgradschool.project.model.ArticleDAO;
import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(name = "edit-article", urlPatterns = "/edit-article")
public class ArticleEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            int articleID = Integer.parseInt(request.getParameter("article-id"));
            Article oldArticle = ArticleDAO.getFullArticleByArticleID(connection, articleID);
            if (oldArticle == null) {
                response.sendRedirect("index.jsp");
                return;
            }
            request.setAttribute("oldArticle", oldArticle);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/edit-article-view.jsp");
            requestDispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String articleContent = request.getParameter("content");
        String articleTitle = request.getParameter("title");
        int articleID = Integer.parseInt(request.getParameter("article-id"));

        Article article = new Article();
        article.setArticleContent(articleContent);
        article.setArticleTitle(articleTitle);
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            ArticleDAO.editArticle(connection, article, articleID);
            response.sendRedirect("./article-view?articleID=" + articleID);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
