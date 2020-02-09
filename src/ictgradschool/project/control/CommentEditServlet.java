package ictgradschool.project.control;

import ictgradschool.project.model.Comment;
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

@WebServlet(name = "edit-comment", urlPatterns = "/edit-comment")
public class CommentEditServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Comment updatedComment = new Comment();
        updatedComment.setCommentID(Integer.parseInt(request.getParameter("comment-id")));
        updatedComment.setCommentBody(request.getParameter("new-comment-body"));
        int articleID = Integer.parseInt(request.getParameter("article-id"));
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            CommentDAO.editComment(connection, updatedComment);
            response.sendRedirect("./article-view?articleID=" + articleID + "#comment" + updatedComment.getCommentID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
