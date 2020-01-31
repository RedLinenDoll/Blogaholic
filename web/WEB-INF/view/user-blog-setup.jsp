<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${newUser.username}'s blog setting</title>

    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'>
    <link rel="stylesheet" href='<c:url value="/assets/layout2.css"/>'>
    <link rel="stylesheet" href='<c:url value="/assets/authentication-page-style.css"/>'>
    <link rel="stylesheet" href='<c:url value="/assets/blog-setup-style.css"/>'>
    <script type="text/javascript" src='<c:url value="/js/blog-setup.js"/>'></script>
</head>
<body>

<div class="page-content-container">
    <div class="head-container">
        <h1>
            <img class="inline-avatar"
                 src='<c:url value="/images/avatar/${newUser.avatarPath}"/>' alt=" "> ${newUser.username}, let's
            make blog decisions
        </h1>
        <p id="clean-message" style="transition-duration: 2s">
            We'll make this page clean and spacious for you.
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

            <div style="height: 30px; width: 100%; border-bottom: 1px dashed var(--theme-color)"></div>
            <p>Now design decisions! Please pick a layout: </p>
            <div id="layout-options-container">
                <div id="layout1-option-container" class="layout-option-container">
                    <label for="layout-radio-1" class="layout-name">Layout 1</label>
                    <input type="radio" id="layout-radio-1" name="layout" value="2" class="layout-radio">

                    <label for="layout-radio-1"class="layout-description">Sharp and clear, suitable for technology blog (and everything else,
                        actually) </label>
                    <div class="layout-preview-container" id="layout-preview-1">
                    </div>
                    <div id="layout1-color-selection-container">
                    </div>
                </div>
                <hr style="width: 60%; ">

                <div id="layout2-option-container" class="layout-option-container">
                    <label for="layout-radio-2" class="layout-name">Layout 2</label>
                    <input type="radio" id="layout-radio-2" name="layout" value="1" class="layout-radio">
                    <label for="layout-radio-2" class="layout-description">An elegant touch in the font and cover, hosting your feelings and
                        stories</label>
                    <div class="layout-preview-container" id="layout-preview-2">
                    </div>
                    <div id="layout2-color-selection-container">
                    </div>
                </div>
            </div>

        </form>
    </div>
</div>

</body>
</html>
