package ictgradschool.project.control;

import ictgradschool.project.model.Article;
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

@WebServlet(name = "article-feed", urlPatterns = "/article-feed")
public class ArticleFeedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Article> feedList;
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {





        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
