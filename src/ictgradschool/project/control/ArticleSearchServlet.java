package ictgradschool.project.control;

import ictgradschool.project.model.Article;
import ictgradschool.project.model.ArticleDAO;
import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "article-search", urlPatterns = "/article-search")
public class ArticleSearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("search-keyword");
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            List<Article> articles = ArticleDAO.searchArticleByKeyword(connection, keyword);
            request.setAttribute("articles", articles);
            request.setAttribute("keyword", keyword);
            request.getRequestDispatcher("WEB-INF/view/search-result-view.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
