package ictgradschool.project.control;

import ictgradschool.project.model.Comment;
import ictgradschool.project.model.CommentDAO;
import ictgradschool.project.util.DBConnectionUtils;
import ictgradschool.project.util.JSONResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "comment-list-load", urlPatterns = {"/load-comments"})
public class CommentListLoadServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int articleID = Integer.parseInt(request.getParameter("articleID"));

        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            List<Comment> comments = CommentDAO.getCommentListByArticleID(connection, articleID);

            JSONResponse.send(response, comments);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

