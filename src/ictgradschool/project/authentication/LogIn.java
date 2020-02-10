package ictgradschool.project.authentication;

import ictgradschool.project.model.User;
import ictgradschool.project.model.UserDAO;
import ictgradschool.project.util.AuthenticationUtils;
import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/*if authentication succeeds, redirects user to welcome page, where they are fed with articles authored
 * by people they are following;
 * Otherwise, redirect back to log in page with an alert. */

@WebServlet(name = "log-in", urlPatterns = "/login")
public class LogIn extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            User existingUser = UserDAO.getLoggedUserByUsername(connection, username);
            boolean isAuthenticated = AuthenticationUtils.authenticateUser(existingUser, password);
            if (isAuthenticated) {
                existingUser.setPasswordHashBase64(null);
                req.getSession().setAttribute("loggedUser", existingUser);
                resp.sendRedirect("./welcome-view.jsp");
            } else {
                resp.sendRedirect("./login.html#please-try-again");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
