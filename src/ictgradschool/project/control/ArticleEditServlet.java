package ictgradschool.project.control;

import ictgradschool.project.model.Article;
import ictgradschool.project.model.ArticleDAO;
import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(name = "edit-article", urlPatterns = "/edit-article")
public class ArticleEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            String content= request.getParameter("content");
            String title= request.getParameter("title");
            int articleID= Integer.parseInt(request.getParameter("id"));
            Article article= new Article();
            article.setArticleContent(content);
            article.setArticleTitle(title);
            ArticleDAO.editArticle(connection,article,articleID);


//            Article article = new Article();
//            article.setArticleTitle(request.getParameter("title"));
//            article.setArticleContent(request.getParameter("content"));
//
//            ArticleDAO.editArticle(connection, article, articleID);
            response.sendRedirect("./article-view?articleID="+articleID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
