package ictgradschool.project.control;
import ictgradschool.project.model.UserDAO;
import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet (name="edit-user", urlPatterns = "/edit-user")
public class ChangeUserProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter("user-id"));
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        Date dateOfBirth = Date.valueOf(request.getParameter("date-of-birth"));
        String selfIntroduction = request.getParameter("self-intro-input");
        boolean toShare = request.getParameter("share-real-name-info")!=null;


        try(Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            UserDAO.editUserRealNameInfo(connection, userID, firstName, lastName, dateOfBirth, selfIntroduction, toShare);
            if (request.getSession().getAttribute("existingUser") == null) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/setup-user-avatar.jsp");
                requestDispatcher.forward(request, response);
            } else {
                response.sendRedirect("./user-profile?user-id="+userID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
