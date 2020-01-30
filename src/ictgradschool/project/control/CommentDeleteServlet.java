package ictgradschool.project.control;

import ictgradschool.project.model.CommentDAO;
import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(name = "delete-comment", urlPatterns = "/delete-comment")
public class CommentDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            int articleID = Integer.parseInt(request.getParameter("articleID"));
            int commentID = Integer.parseInt(request.getParameter("commentID"));
            boolean success = CommentDAO.deleteCommentByID(connection, commentID);
            System.out.println(success);
            response.sendRedirect("./article-view?articleID=" + articleID);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
