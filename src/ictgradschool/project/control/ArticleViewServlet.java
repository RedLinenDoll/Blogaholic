package ictgradschool.project.control;

import ictgradschool.project.model.Article;
import ictgradschool.project.model.ArticleDAO;
import ictgradschool.project.model.UserDAO;
import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "article-view", urlPatterns = "/article-view")
public class ArticleViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int articleID;
        try {
            articleID = Integer.parseInt(request.getParameter("articleID"));
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "the article you are looking for does not exist.");
            request.getRequestDispatcher("WEB-INF/view/error-redirect.jsp").forward(request, response);
            return;
        }
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            Article fullArticle = ArticleDAO.getFullArticleByArticleID(connection, articleID);
            if (fullArticle == null) {
                request.setAttribute("errorMessage", "the article you are looking for does not exist.");
                request.getRequestDispatcher("WEB-INF/view/error-redirect.jsp").forward(request, response);
                return;
            }
            Object author = request.getAttribute("author");
            if (author == null)
                author = UserDAO.getAuthorByArticleId(connection, articleID);

            request.setAttribute("article", fullArticle);
            request.setAttribute("author", author);

            request.getRequestDispatcher("WEB-INF/view/article-view.jsp").forward(request, response);

        } catch (SQLException e) {
            request.setAttribute("errorMessage", "we failed to load the article from the database.");
            request.getRequestDispatcher("WEB-INF/view/error-redirect.jsp").forward(request, response);
        }
    }

}
