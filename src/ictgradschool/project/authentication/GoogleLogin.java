package ictgradschool.project.authentication;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import ictgradschool.project.model.User;
import ictgradschool.project.model.UserDAO;
import ictgradschool.project.util.AuthenticationUtils;
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
import java.sql.ResultSet;
import java.sql.Statement;

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
            String sql = "SELECT * FROM users_db WHERE username='" + name + "'";

            try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
               try(Statement stmt = connection.createStatement()) {


                   try (ResultSet rs = stmt.executeQuery(sql)) {
                       if (rs.next()) {
                           System.out.println("User already exists");
                           User user1 = new User(rs.getInt(1), rs.getString(2), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10),
                                   rs.getString(11), rs.getString(12), rs.getString(13), rs.getDate(14), rs.getString(15), rs.getBoolean(16));
                           request.getSession().setAttribute("loggedUser", user1);
                           request.getServletContext()
                                   .getRequestDispatcher("/welcome-view.jsp").forward(request, response);

                       } else {
                           User user = new User();
                           user.setFirstName(givenName);
                           user.setLastName(familyName);
                           user.setUsername(name);
                           System.out.println("Inserting");
                           User newUser = UserDAO.insertGoogleUser(connection, user);
                           request.getSession().setAttribute("newUser", newUser);
                           System.out.println("user added to db");
                           request.getServletContext()
                                   .getRequestDispatcher("/WEB-INF/view/user-profile-setting.jsp").forward(request, response);
                           System.out.println("sent dispatch");
                       }

                   }
               }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
