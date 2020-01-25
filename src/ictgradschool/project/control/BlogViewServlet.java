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

@WebServlet (name="blog", urlPatterns="/blog")
public class BlogViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authorID = req.getParameter("authorID");
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            User author = UserDAO.getAuthorById(connection, Integer.parseInt(authorID));
            req.setAttribute("author", author);
            req.getRequestDispatcher("WEB-INF/view/blog-view.jsp").forward(req, resp);
        } catch (SQLException e) {
            resp.setStatus(500);
//            resp.sendRedirect("WEB-INF/error-view/500.jsp");
            e.printStackTrace();
        }
    }
}
