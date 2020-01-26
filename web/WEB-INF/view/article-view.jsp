<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="article: ${article.articleTitle}">
    <title>${article.articleTitle} by ${author.username}</title>

    <link href='<c:url value="https://unpkg.com/startbootstrap-resume/vendor/fontawesome-free/css/all.min.css"/>'
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,600,700,800&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lora:400,400i&display=swap" rel="stylesheet">

    <link rel="stylesheet" href='<c:url value="/assets/layout${author.layoutID}.css"/>'/>
    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'/>

    <script src='<c:url value="/js/customized-styling.js"/>' type="text/javascript"></script>
    <script type="text/javascript">
        window.addEventListener("load", function () {
            applyThemeColor(`${author.themeColor}`);
        })
    </script>

</head>

<body>

<c:choose>
    <c:when test="${loggedUser == null}">
        <jsp:include page="visitor-bar.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="user-bar.jsp"/>
    </c:otherwise>
</c:choose>

<div class="head-container">
    <div id="article-title-container" class="primary-h1-container">
        <h1 id="article-title">${article.articleTitle}</h1>
    </div>
    <div class="author-intro-container">
        <span>by <img class="inline-avatar" src='<c:url value="/images/avatar/${author.avatarPath}"/>'
                      alt="author avatar"> ${author.username}</span>
    </div>
</div>

<div id="article-content-container">
    <p>
        ${article.articleContent}
    </p>
</div>

<div id="visitor-options-container">
        <button class="link-button">
            <a href='<c:url value="/blog-view?authorID=${author.userID}"/>'>
            Back to ${author.username}'s blog  &nbsp;&#8594;
            </a>
        </button>
</div>


</body>
</html>
