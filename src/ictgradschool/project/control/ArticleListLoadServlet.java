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

@WebServlet(name = "ArticleServlet", urlPatterns = {"/load-articles"})
public class ArticleListLoadServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int authorID = Integer.parseInt(request.getParameter("authorID"));

        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            List<Article> articles = ArticleDAO.getBriefArticleListByAuthorID(connection, authorID);

            JSONResponse.send(response, articles);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

