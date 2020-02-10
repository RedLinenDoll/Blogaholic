package ictgradschool.project.authentication;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
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

@WebServlet(urlPatterns = {"/google-login"})
public class GoogleLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idToken = request.getParameter("idtoken");
            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
            String email = payLoad.getEmail();
            String username = (String) payLoad.get("name");
            String familyName = (String) payLoad.get("family_name");
            String givenName = (String) payLoad.get("given_name");

            // We maintain a pairing relationship between google email address and user ID.
            try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
                int googleUserID = UserDAO.getUserIDByGoogleEmail(connection, email);
                if (googleUserID == -1) {
                    User user = new User();
                    user.setFirstName(givenName);
                    user.setLastName(familyName);
                    user.setUsername(username);
                    User newUser = UserDAO.insertGoogleUser(connection, user, email);
                    if (newUser == null) {
                        response.sendRedirect("./login.html#please-try-again");
                    }
                    request.getSession().setAttribute("newUser", newUser);
                    request.getRequestDispatcher("/WEB-INF/view/set-google-user-password.jsp").forward(request, response);
                } else {
                    User googleUser = UserDAO.getLoggedGoogleUserByID(connection, googleUserID);
                    request.getSession().setAttribute("loggedUser", googleUser);
                    response.sendRedirect("./welcome-view.jsp");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}