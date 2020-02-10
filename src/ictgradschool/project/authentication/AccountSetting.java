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

/*
This servlet is used when users change the username or password of their account. Old username and password are checked,
if not matching, redirects user back to the form and alerts the user;
If matches, take the new username and password from the form and set it to the database.
If setting is successful, redirects to log in page and alerts the user that setting is successful and they need to re-login.
*/

@WebServlet(name = "account-setting", urlPatterns = "/account-setting")
public class AccountSetting extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            String username = request.getParameter("old-username");
            User existingUser = UserDAO.getLoggedUserByUsername(connection, username);
            if (existingUser == null) {
                settingFailed(request, response);
                return;
            }

            // If initiating the password for google user
            String googleUser = request.getParameter("google-user-initiation");
            if (googleUser != null && Boolean.parseBoolean(googleUser)) {
                String password = request.getParameter("new-password");
                User updatedUser = AuthenticationUtils.createUser(username, password);
                updatedUser.setUserID(existingUser.getUserID());
                UserDAO.changeAccountSetting(connection, updatedUser);
                request.setAttribute("newUser", updatedUser);
                request.getRequestDispatcher("/WEB-INF/view/user-profile-setting.jsp").forward(request, response);
                return;
            }
            // If changing account setting for existing user
            String password = request.getParameter("old-password");
            boolean isAuthenticated = AuthenticationUtils.authenticateUser(existingUser, password);
            if (!isAuthenticated) {
                request.getRequestDispatcher("WEB-INF/view/user-account-setting.jsp#setting-failed").forward(request, response);
                return;
            }
            String newUsername = request.getParameter("new-username");
            String newPassword = request.getParameter("new-password");
            User updatedUser = AuthenticationUtils.createUser(newUsername, newPassword);
            updatedUser.setUserID(existingUser.getUserID());
            boolean success = UserDAO.changeAccountSetting(connection, updatedUser);
            if (!success) {
                request.getRequestDispatcher("WEB-INF/view/user-account-setting.jsp#setting-failed").forward(request, response);
                return;
            }
            request.getSession().setAttribute("loggedUser", null);
            request.getSession().setAttribute("existingUser", null);
            request.getSession().setAttribute("newUser", null);
            response.sendRedirect("login.html#account-setting-successful");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void settingFailed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("setting failed!");
        request.getRequestDispatcher("WEB-INF/view/user-account-setting.jsp#setting-failed").forward(request, response);
    }
}
