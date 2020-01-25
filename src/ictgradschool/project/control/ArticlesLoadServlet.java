package ictgradschool.project.control;

import ictgradschool.project.util.DBConnectionUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

    @WebServlet(name = "ArticleServlet", urlPatterns = {"/load-articles"})
    public class ArticlesLoadServlet extends HttpServlet {

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            int authorID = Integer.parseInt(request.getParameter("authorID"));

            try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
                System.out.println("cool servlet");


            } catch (SQLException e) {
                e.printStackTrace();
            }

//            request.getRequestDispatcher(jsp file here).forward(request, response);
        }
    }

