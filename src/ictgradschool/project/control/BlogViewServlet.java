package ictgradschool.project.control;

import ictgradschool.project.model.User;
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

@WebServlet(name = "blog-view", urlPatterns = "/blog-view")
public class BlogViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int authorID;
        try {
            authorID = Integer.parseInt(request.getParameter("authorID"));
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "the blog you are looking for does not exist.");
            request.getRequestDispatcher("WEB-INF/view/error-redirect-view.jsp").forward(request, response);
            return;
        }
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            User author = UserDAO.getAuthorById(connection, authorID);
            if (author == null) {
                request.setAttribute("errorMessage", "the blog you are looking for does not exist.");
                request.getRequestDispatcher("WEB-INF/view/error-redirect-view.jsp").forward(request, response);
                return;
            }
            request.setAttribute("author", author);
            request.getRequestDispatcher("WEB-INF/view/blog-view.jsp").forward(request, response);
        } catch (SQLException e) {
            response.setStatus(500);
            e.printStackTrace();
        }
    }
}
