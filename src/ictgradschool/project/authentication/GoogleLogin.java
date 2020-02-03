package ictgradschool.project.authentication;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import ictgradschool.project.model.User;
import ictgradschool.project.model.UserDAO;
import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(urlPatterns = {"/google-login"})
public class GoogleLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idToken = request.getParameter("idtoken");
            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
            String name = (String) payLoad.get("name");
            String familyName = (String) payLoad.get("family_name");
            String givenName = (String) payLoad.get("given_name");
            String pictureUrl = (String) payLoad.get("picture");
            System.out.println("User name: " + name);
            System.out.println("Picture found at " + pictureUrl);

            User user = new User();
            user.setFirstName(givenName);
            user.setLastName(familyName);
            user.setAvatarPath(pictureUrl);
            user.setUsername("New Google User");




//            HttpSession session = request.getSession(true);
//            session.setAttribute("loggedUser.username", name);
//            session.setAttribute("loggedUser.avatarPath",pictureUrl);
            try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
                User newUser = UserDAO.insertGoogleUser(connection,user);
                System.out.println("user added to db");
//                request.getSession().setAttribute("newUser", newUser);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome-view.jsp");
                dispatcher.forward(request, response);
                System.out.println("sent dispatch");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
