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

@WebServlet (name="blog-view", urlPatterns="/blog-view")
public class BlogViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authorID = request.getParameter("authorID");
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            User author = UserDAO.getAuthorById(connection, Integer.parseInt(authorID));
            request.setAttribute("author", author);
            request.getRequestDispatcher("WEB-INF/view/blog-view.jsp").forward(request, response);
        } catch (SQLException e) {
            response.setStatus(500);
//            resp.sendRedirect("WEB-INF/error-view/500.jsp");
            e.printStackTrace();
        }
    }
}
