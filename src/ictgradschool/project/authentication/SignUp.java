package ictgradschool.project.authentication;

import ictgradschool.project.model.User;
import ictgradschool.project.model.UserDAO;
import ictgradschool.project.util.AuthenticationUtils;
import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "sign-up", urlPatterns = "/signup")
public class SignUp extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = AuthenticationUtils.createUser(username,password);
        try(Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")){
            User newUser = UserDAO.insertUser(connection,user);
            request.getSession().setAttribute("newUser", newUser);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/user-profile-setting.jsp");
            requestDispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String testedUsername = request.getParameter("tested-username");
        try(Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            int existingNumber = UserDAO.checkUsernameCount(connection, testedUsername);
            response.getWriter().print(existingNumber);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
