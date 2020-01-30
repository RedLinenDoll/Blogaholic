<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'>
    <link rel="stylesheet" href='<c:url value="/assets/layout2.css"/>'>
    <link rel="stylesheet" href='<c:url value="/assets/authentication-page-style.css"/>'>

    <title>Creating ${newUser.username}'s awesome blog</title>
    <script>
        window.addEventListener("load", function () {
            setTimeout(function () {
                document.querySelector("#cover").style.backgroundColor = "rgba(256,256,256,1)";
                document.querySelector("#clean-message").style.color = "white";
                }, 1000);
        })
    </script>
</head>
<body>
<div style="margin: 0; background-color: rgba(0,0,0,0); width: 100%; height: 100%;transition:all 2s ease-in-out" id="cover">
<div class="page-content-container">
    <div class="head-container">
        <h1>
            <img class="inline-avatar"
                       src='<c:url value="/images/avatar/${newUser.avatarPath}"/>'/> ${newUser.username}, let's
            make blog decisions
        </h1>
        <p id="clean-message" style="transition-duration: 2s">
            We'll make this page clean and simple for you.
        </p>
    </div>
    <div class="body-container">
        <form id="blog-setup-form" action='<c:url value="/change-blog-preference"/>' method="post"
              style="width: 90%; line-height: 1.8em; justify-content: stretch; align-items: stretch">
            <label for="blog-name-input">
                Name for your blog:
            </label>
            <input type="text" id="blog-name-input" name="blog-name" required>
            <br>
            <label for="blog-description-input">
                Please write some descriptions of your blog (e.g. topic, target-readers):
            </label>
            <textarea id="blog-description-input" name="blog-description"
                      rows="3" cols="45">Blogaholic ${newUser.username}'s blog</textarea>

            <div style="height: 30px; width: 100%; border-bottom: 1px dashed var(--theme-color)">

<%--                TODO style options here --%>


        </form>
    </div>
</div>
</div>
</body>
</html>
