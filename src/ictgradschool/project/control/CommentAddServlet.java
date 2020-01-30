package ictgradschool.project.control;

import ictgradschool.project.model.*;
import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "add-comment", urlPatterns = "/add-comment")
public class CommentAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            Comment comment = new Comment();
            User loggedUser = (User) request.getSession().getAttribute("loggedUser");
            int commenterID = loggedUser.getUserID();
            comment.setCommentBody(request.getParameter("body"));
            int commentID = CommentDAO.addCommentToComment(connection,commenterID,comment);

            response.sendRedirect("./article-view");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}