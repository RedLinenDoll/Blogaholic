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

@WebServlet(name="user-profile", urlPatterns = "/user-profile")
public class UserProfileViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter("user-id"));
        User profileOwner;
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            profileOwner = UserDAO.getProfileOwnerInfoByID(connection, userID);
            request.setAttribute("profileOwner", profileOwner);
            request.getRequestDispatcher("WEB-INF/view/user-profile-view.jsp").forward(request, response);

        } catch (SQLException e) {
            response.setStatus(500);
            e.printStackTrace();
        }


    }
}
