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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/google-login"})
public class GoogleLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idToken = request.getParameter("idtoken");
            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
            String username = (String) payLoad.get("name");
            String familyName = (String) payLoad.get("family_name");
            String givenName = (String) payLoad.get("given_name");
            String sql = "SELECT user_id, username, avatar_path, blog_name, blog_description, theme_color, layout_id FROM users_db WHERE username = ? ";

            try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, username);

                    try (ResultSet rs = preparedStatement.executeQuery()) {
                        if (rs.next()) {
                            User loggedGoogleUser = new User();
                            loggedGoogleUser.setUserID(rs.getInt(1));
                            loggedGoogleUser.setUsername(rs.getString(2));
                            loggedGoogleUser.setAvatarPath(rs.getString(3));
                            loggedGoogleUser.setBlogName(rs.getString(4));
                            loggedGoogleUser.setBlogDescription(rs.getString(5));
                            loggedGoogleUser.setThemeColor(rs.getString(6));
                            loggedGoogleUser.setLayoutID(rs.getInt(7));
                            request.getSession().setAttribute("loggedUser", loggedGoogleUser);
                            request.getRequestDispatcher("/welcome-view.jsp").forward(request, response);

                        } else {
                            User user = new User();
                            user.setFirstName(givenName);
                            user.setLastName(familyName);
                            user.setUsername(username);
                            User newUser = UserDAO.insertGoogleUser(connection, user);
                            if (newUser == null) {
                                response.sendRedirect("./login.html#please-try-again");
                            }
                            request.getSession().setAttribute("newUser", newUser);
                            request.getRequestDispatcher("/WEB-INF/view/user-profile-setting.jsp").forward(request, response);
                        }

                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
