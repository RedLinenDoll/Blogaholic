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

@WebServlet(name = "delete-article", urlPatterns = "/delete-article")
public class ArticleDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            int articleID= Integer.parseInt(request.getParameter("articleID"));
            ArticleDAO.deleteArticle(connection,articleID);
            int loggedUserID = ((User)(request.getSession().getAttribute("loggedUser"))).getUserID();
            response.sendRedirect("./blog-view?authorID=" + loggedUserID);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}