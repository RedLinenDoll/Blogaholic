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






    @WebServlet(name = "ArticleServlet", urlPatterns = {"/articles"})
    public class ArticleServlet extends HttpServlet {

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
                System.out.println("cool servlet");


        //set attribute to user
            } catch (SQLException e) {
                e.printStackTrace();
            }

//            request.getRequestDispatcher(jsp file here).forward(request, response);
        }
    }

