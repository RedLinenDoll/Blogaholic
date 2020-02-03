package ictgradschool.project.control;

import ictgradschool.project.model.User;
import ictgradschool.project.model.UserDAO;
import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "user-option", urlPatterns = "/user-option")
public class UserOptionDispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userRequest = request.getParameter("user-request");
        HttpSession session = request.getSession();
        if (session.getAttribute("loggedUser") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            User existingBlogAuthor = (User) session.getAttribute("loggedUser");
            session.setAttribute("newUser", session.getAttribute("loggedUser"));
            session.setAttribute("existingUser", true);
            RequestDispatcher requestDispatcher;
            switch (userRequest) {
                case "blog-setup":
                    existingBlogAuthor = UserDAO.getAuthorById(connection, existingBlogAuthor.getUserID());
                    request.setAttribute("existingBlogAuthor", existingBlogAuthor);
                    requestDispatcher = request.getRequestDispatcher("WEB-INF/view/user-blog-setting.jsp");
                    requestDispatcher.forward(request, response);
                    return;
                case "change-avatar":
                    requestDispatcher = request.getRequestDispatcher("WEB-INF/view/user-avatar-setting.jsp");
                    requestDispatcher.forward(request, response);
                    return;
                case "change-user-profile":
                    User existingProfileOwner = UserDAO.getProfileOwnerInfoByID(connection, existingBlogAuthor.getUserID());
                    request.setAttribute("existingProfileOwner", existingProfileOwner);
                    requestDispatcher = request.getRequestDispatcher("WEB-INF/view/user-profile-setting.jsp");
                    requestDispatcher.forward(request, response);
                    return;
                case "change-account-setting":
                    requestDispatcher = request.getRequestDispatcher("WEB-INF/view/user-account-setting.jsp");
                    requestDispatcher.forward(request, response);
                    return;
                default:
                    response.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
