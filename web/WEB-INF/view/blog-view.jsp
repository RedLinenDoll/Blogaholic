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
    <div id="blog-name-container" class="primary-h1-container">
        <h1 id="blog-name">${author.blogName}</h1>
    </div>
    <div id="blog-description-container">
        <p id="blog-description">${author.blogDescription}</p>
    </div>
    <div class="author-intro-container">
        <span>by <img class="inline-avatar" src='<c:url value="/images/avatar/${author.avatarPath}"/>'
                      alt="author avatar"> ${author.username}</span>
    </div>
</div>

<div id="post-list-container">
    <%--    This part is for demoenstration of jsp structure, and will be cleared once loaded article --%>
    <div class="articleDiv">
        <a class="full-article-link" href='<c:url value="#"/>'>
            <div class="articleTitleDiv">
                <h2 class="articleTitle">
                    <%--            article title shows here  --%>
                </h2>
            </div>
            <div class="articleBriefDiv">
                <p class="articleBrief">
                    <%--            article brief shows here --%>
                </p>
            </div>
        </a>
        <div class="articleInforDiv">
        <span class="articleInfo">
            <%--           creation date , likes and dislikes shows here --%>
        </span>
        </div>
    </div>
</div>


</body>
</html>
