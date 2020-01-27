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

@WebServlet(name = "sign-up", urlPatterns = "/signup")
public class SignUp extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = AuthenticationUtils.createUser(username,password);
        try(Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")){
            UserDAO.insertUser(connection,user);
            resp.sendRedirect("./index.jsp");


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    // TODO do post, create user and set blog settings as well.

    //  TODO Will need to deal with avatar upload.


}
