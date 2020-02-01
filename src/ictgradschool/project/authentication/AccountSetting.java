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

@WebServlet(name="account-setting", urlPatterns = "/account-setting")
public class AccountSetting extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("old-username");
        String password = request.getParameter("old-password");
        try(Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")){
            User existingUser= UserDAO.getLoggedUserByUsername(connection,username);
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
}
