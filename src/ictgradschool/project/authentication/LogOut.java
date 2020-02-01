package ictgradschool.project.authentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "log-out", urlPatterns = "/logout")
public class LogOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("loggedUser", null);
        req.getSession().setAttribute("existingUser", null);
        req.getSession().setAttribute("newUser", null);

        resp.sendRedirect("index.jsp");
    }
}
