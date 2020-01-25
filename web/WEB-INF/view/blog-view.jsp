<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="blog: ${author.blogName}">
    <title>${author.blogName}</title>

    <link href='<c:url value="https://unpkg.com/startbootstrap-resume/vendor/fontawesome-free/css/all.min.css"/>'
          rel="stylesheet">
    <link rel="stylesheet" href='<c:url value="/assets/blog-layout${author.layoutID}.css"/>'/>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,600,700,800&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lora:400,400i&display=swap" rel="stylesheet">

    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'/>

    <script src='<c:url value="/js/load-blog-articles.js"/>' type="text/javascript"></script>
    <script type="text/javascript">
        window.addEventListener("load", function () {
            loadArticleList(${author.userID});
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

<div id="head-container">
    <div id="blog-name-container">
        <h1 id="blog-name">${author.blogName}</h1>
    </div>
    <div id="blog-description-container">
        <p id="blog-description">${author.blogDescription}</p>
    </div>
    <div id="author-intro-container">
        <span>by <img class="inline-avatar" src='<c:url value="/images/avatar/${author.avatarPath}"/>'
                      alt="author avatar"> ${author.username}</span>
    </div>
</div>

<div id="post-list-container">

</div>


</body>
</html>
