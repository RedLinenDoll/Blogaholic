package ictgradschool.project.control;

import ictgradschool.project.model.User;
import ictgradschool.project.model.UserDAO;
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

@WebServlet(name="load-blog-list", urlPatterns = "/load-blog-list")
public class BlogListLoadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties"))
        {
            List<User> allBlogUsers = UserDAO.getAllBlogUsers(connection);
            JSONResponse.send(response, allBlogUsers);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
