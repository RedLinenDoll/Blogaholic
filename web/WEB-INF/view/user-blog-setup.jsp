<%--
  Created by IntelliJ IDEA.
  User: anran
  Date: 30/01/20
  Time: 8:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Birthplace of ${newUser.username}'s awesome blog</title>
</head>
<body>
<div class="page-content-container">
    <div class="head-container">
        <h1>
            Now let's create your blog.
        </h1>
        <p>and give it your personal touch!</p>
    </div>
    <div class="body-container">
        <form id="blog-setup-form">
            <%--        TODO submit it to blog-creation servlet --%>

            <label for="blog-name-input">
                Name for your blog:
            </label>
            <input type="text" id="blog-name-input" name="blog-name" required>
            <label for="blog-description-input">
                Write a few lines to introduce your blog (maximum 256 characters):
            </label>
            <textarea id="blog-description-input" name="blog-description" maxlength="256" rows="3">
            </textarea>

<%--            TODO finish this form--%>
        </form>
    </div>
</div>

</body>
</html>
