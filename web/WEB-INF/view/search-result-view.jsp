<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anran
  Date: 2/02/20
  Time: 11:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Result of ${keyword}</title>
</head>
<body>
<%-- // TODO find a way to mark the keyword part A different color.
        // TODO: could be string.split, and after applying style, putting the html content back again?
  --%>
<%--TODO populate this page with search results--%>
<c:forEach var="article" items="${articles}">
    <p>
        ${article.articleContent}
    </p>

</c:forEach>
</body>
</html>
