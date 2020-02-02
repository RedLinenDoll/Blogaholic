package ictgradschool.project.control;

import ictgradschool.project.model.CommentDAO;
import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "like-dislike", urlPatterns = "/like-dislike")
public class LikeDislikeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int targetID = Integer.parseInt(request.getParameter("target-id"));
        String targetType = request.getParameter("target-type");
        boolean isLike = Boolean.parseBoolean(request.getParameter("is-like"));
        boolean isPlus = Boolean.parseBoolean(request.getParameter("is-plus"));

        try(Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            CommentDAO.likeOrDislike(connection, targetID, targetType, isLike, isPlus);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
