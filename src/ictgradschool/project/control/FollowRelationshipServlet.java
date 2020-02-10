package ictgradschool.project.control;

import ictgradschool.project.model.User;
import ictgradschool.project.model.UserDAO;
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

/*This servlet deals with following relationship among users.*/
/* doGet is responsible for providing information about a users' followers and people followed by them; doPost is
 * responsible for adding or removing following relationship. */

@WebServlet(name = "follow-relationship", urlPatterns = "/follow-relationship")
public class FollowRelationshipServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestOption = request.getParameter("request-option");
        int targetUserID = Integer.parseInt(request.getParameter("target-user-id"));

        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            switch (requestOption) {
                case "get-follower-list":
                    List<User> followers = UserDAO.getFollowerListByUserID(connection, targetUserID);
                    JSONResponse.send(response, followers);
                    return;
                case "get-publisher-list":
                    List<User> publishers = UserDAO.getPublisherListByUserID(connection, targetUserID);
                    JSONResponse.send(response, publishers);
                    return;
                case "get-follower-number":
                    int followerNumber = UserDAO.getFollowerNumberByUserID(connection, targetUserID);
                    JSONResponse.send(response, followerNumber);
                    return;
                case "check-if-following":
                    int publisherID = Integer.parseInt(request.getParameter("publisher-id"));
                    boolean following = UserDAO.checkIfFollowing(connection, targetUserID, publisherID);
                    JSONResponse.send(response, following);
                    return;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int followerID = Integer.parseInt(request.getParameter("follower-id"));
        int publisherID = Integer.parseInt(request.getParameter("publisher-id"));
        boolean adding = Boolean.parseBoolean(request.getParameter("adding"));
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            if (adding)
                UserDAO.addFollowingRelationship(connection, followerID, publisherID);
            else
                UserDAO.removeFollowingRelationship(connection, followerID, publisherID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
