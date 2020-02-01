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

@WebServlet(name = "change-blog-preference", urlPatterns = "/change-blog-preference")
public class ChangeUserBlogPreferenceServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String themeColor = request.getParameter("theme-color");
        int layoutID = Integer.parseInt(request.getParameter("layout"));
        String blogName = request.getParameter("blog-name");
        String blogDescription = request.getParameter("blog-description");
        Object user = request.getSession().getAttribute("newUser");
        if (user == null) {
            user = request.getSession().getAttribute("loggedUser");
        }
        if (user == null) {
            response.sendRedirect("./index.jsp");
            return;
        }
        int userID = ((User) user).getUserID();

        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            UserDAO.setBlogPreference(connection, userID, blogName, blogDescription, layoutID, themeColor);
                request.getSession().setAttribute("loggedUser", user);
                response.sendRedirect("./blog-view?authorID=" + ((User) user).getUserID());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
