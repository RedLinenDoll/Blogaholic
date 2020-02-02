package ictgradschool.project.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="article-search", urlPatterns = "/article-search")
public class ArticleSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String keyword = request.getParameter("search-keyword");

       //TODO write ArticleDAO method to search for article that contains the keyword in the title or content.
        // TODO find a way to mark the keyword part A different color.
    }
}
