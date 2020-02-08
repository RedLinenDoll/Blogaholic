package ictgradschool.project.control;

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

@WebServlet(name = "delete-user", urlPatterns = "/delete-user")
public class UserDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("confirm-username");
        String password = request.getParameter("confirm-password");
        int userID = Integer.parseInt(request.getParameter("user-id"));
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            User existingUser = UserDAO.getLoggedUserByUsername(connection, username);
            boolean isAuthenticated = AuthenticationUtils.authenticateUser(existingUser, password) && existingUser.getUserID() == userID;
            if (!isAuthenticated) {
                request.getRequestDispatcher("WEB-INF/view/user-account-setting.jsp#setting-failed").forward(request, response);
                return;
            }
            boolean success = UserDAO.deleteUserById(connection, userID);
            if (!success) {
                request.getRequestDispatcher("WEB-INF/view/user-account-setting.jsp#setting-failed").forward(request, response);
                return;
            }
            request.setAttribute("userLeft", existingUser.getUsername());
            request.getSession().setAttribute("loggedUser", null);
            request.getSession().setAttribute("existingUser", null);
            request.getSession().setAttribute("newUser", null);

            request.getRequestDispatcher("WEB-INF/view/user-goodbye-view.jsp").forward(request, response);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
