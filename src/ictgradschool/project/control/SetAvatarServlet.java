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
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "set-avatar", urlPatterns = "/set-avatar")
public class SetAvatarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean newUser = true;
        User user = (User) request.getSession().getAttribute("newUser");
        if (user == null) {
            user = (User) request.getSession().getAttribute("loggedUser");
            if (user == null) {
                response.sendRedirect("index.jsp");
                return;
            }
            newUser = false;
        }
            String avatarNumString = request.getParameter("chosen-avatar");
            int userID = user.getUserID();
            try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
                User updatedUser = UserDAO.setUserAvatarPath(connection, userID, "avatar" + avatarNumString + ".jpg");
                if (newUser) {
                    request.getSession().setAttribute("newUser", updatedUser);
                    request.getRequestDispatcher("WEB-INF/view/user-blog-setup.jsp").forward(request, response);
                }
                else response.sendRedirect("index.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
            }



    }
}
