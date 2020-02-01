
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editing article</title>
</head>
<body>

<form id="test" action="./edit-article" method="post">
    <label for="title">Title here:</label>
    <input type="text" id="title" name="title" value="${oldArticle.articleTitle}">
    <label for="content">
        Content here:
    </label>
    <input type="text" id="content" name="content" value="${oldArticle.articleContent}">
    <input type="hidden" name="articleID" value="${oldArticle.articleID}">

    <button type="submit">Submit changes</button>

</form>

</body>
</html>
