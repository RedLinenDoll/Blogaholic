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

    // TODO get method, load article basic information, as well as author information, so as to have a consistent look.
    //  JSP need author and article object.

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int articleID = Integer.parseInt(request.getParameter("articleID"));
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            Article fullArticle = ArticleDAO.getFullArticleByArticleID(connection, articleID);

            Object author = request.getAttribute("author");
            if (author == null)
                author = UserDAO.getAuthorByArticleId(connection, articleID);

            request.setAttribute("article", fullArticle);
            request.setAttribute("author", author);

            request.getRequestDispatcher("WEB-INF/view/article-view.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






    //TODO post method, article editing or deleting.
}
