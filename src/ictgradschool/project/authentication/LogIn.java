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

@WebServlet(name = "log-in", urlPatterns = "/login")
public class LogIn extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try(Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")){
            User existingUser=UserDAO.getLoggedUserByUsername(connection,username);
            boolean isAuthenticated = AuthenticationUtils.authenticateUser(existingUser, password);
            if (isAuthenticated) {
                req.getSession().setAttribute("loggedUser", existingUser);
                resp.sendRedirect("./welcome-view.jsp");
            }
            else {
                resp.sendRedirect("./login.html");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//TODO post, given username and password, if success, set user session to current user,
    // otherwise alert the user and prompt to try again.


}
