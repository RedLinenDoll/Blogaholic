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
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,600,700,800&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lora:400,400i&display=swap" rel="stylesheet">

    <link rel="stylesheet" href='<c:url value="/assets/layout${author.layoutID}.css"/>'/>
    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'/>

    <script src='<c:url value="/js/load-blog-articles.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/js/customized-styling.js"/>' type="text/javascript"></script>
    <script type="text/javascript">
        window.addEventListener("load", function () {
            loadArticleList(${author.userID});
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
    <div id="blog-name-container" class="page-h1-container">
        <h1 id="blog-name">${author.blogName}</h1>
    </div>
    <div id="blog-description-container" class="page-description-container">
        <p id="blog-description">${author.blogDescription}</p>
    </div>
    <div class="author-intro-container" class="page-author-container">
        <span>by <img class="inline-avatar" src='<c:url value="/images/avatar/${author.avatarPath}"/>'
                      alt="author avatar"> ${author.username}</span>
    </div>
</div>

<div id="article-list-container" class="page-item-container">
    <%--    This part is for demoenstration of jsp structure, and will be cleared once loaded article --%>
    <div class="article-div page-item-div">
        <a class="full-article-link page-item-link" href='<c:url value="#"/>'>
            <div class="article-title-div page-item-title-div">
                <h2 class="article-title page-item-title">
                    <%--            article title shows here  --%>
                </h2>
            </div>
            <div class="article-brief-div page-item-brief-div">
                <p class="article-brief page-item-brief">
                    <%--            article brief shows here --%>
                </p>
            </div>
        </a>
        <div class="article-info-div page-item-info-div">
        <span class="article-info page-item-info">
            <%--           creation date , likes and dislikes shows here --%>
        </span>
        </div>
    </div>
</div>


</body>
</html>