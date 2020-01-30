<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'>
    <link rel="stylesheet" href='<c:url value="/assets/layout2.css"/>'>
    <link rel="stylesheet" href='<c:url value="/assets/authentication-page-style.css"/>'>

    <title>Birthplace of ${newUser.username}'s awesome blog</title>
</head>
<body>
<div class="page-content-container">
    <div class="head-container">
        <h1>
            Great <img class="inline-avatar" src='<c:url value="/images/avatar/${newUser.avatarPath}"/>'/> ${newUser.username}, now let's create
            your blog
        </h1>
    </div>
    <div class="body-container">
        <form id="blog-setup-form" action='<c:url value="/change-blog-preference"/>' method="post">
            <label for="blog-name-input">
                Name for your blog:
            </label>
            <input type="text" id="blog-name-input" name="blog-name" required>
            <label for="blog-description-input">
                Please write some descriptions of your blog (e.g. topic, target-readers):
            </label>
            <textarea id="blog-description-input" name="blog-description"
                      rows="3" cols="45">Blogaholic ${newUser.username}'s blog</textarea>



            <%--            TODO finish this form--%>
        </form>
    </div>
</div>

</body>
</html>
