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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "article-list-load", urlPatterns = {"/load-articles"})
public class ArticleListLoadServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authorIDParameter = request.getParameter("authorID");
        List<Article> articles;
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            if (authorIDParameter == null || authorIDParameter.length() == 0) {
                articles = ArticleDAO.getRecentBriefArticleList(connection);
            } else {
                int authorID = Integer.parseInt(authorIDParameter);
                articles = ArticleDAO.getBriefArticleListByAuthorID(connection, authorID);
            }
            JSONResponse.send(response, articles);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

