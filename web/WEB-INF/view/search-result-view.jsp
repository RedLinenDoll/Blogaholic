<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Result of ${keyword}</title>
    <jsp:include page="/cross-page-view/link-fonts.jsp"/>
    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'>
    <c:choose>
        <c:when test="${not empty loggedUser}">
            <link rel="stylesheet" href='<c:url value="/assets/layout${loggedUser.layoutID}.css"/>'>
        </c:when>
        <c:otherwise>
            <link rel="stylesheet" href='<c:url value="/assets/layout1.css"/>'>
        </c:otherwise>
    </c:choose>
    <script type="text/javascript" src='<c:url value="js/load-search-results.js"/>'></script>
    <script src='<c:url value="/js/like-dislike.js"/>' type="text/javascript"></script>

    <script type="text/javascript">
        window.addEventListener("load", async function () {
            <c:choose>
            <c:when test="${not empty loggedUser}">
            applyThemeColor(`${loggedUser.themeColor}`);
            applyLayoutSpecificStyling(`${loggedUser.layoutID}`, `${loggedUser.themeColor}`);
            </c:when>
            <c:otherwise>
            applyThemeColor(`#3f99ae`);
            applyLayoutSpecificStyling(`1`, `#3f99ae`);
            </c:otherwise>
            </c:choose>
            listenForLikeDislike();
        });

    </script>
    <script src='<c:url value="/js/customized-styling.js"/>' type="text/javascript"></script>
    <style>
        .keyword-span {
            text-decoration: underline;
            color: var(--theme-color);
        }

        .article-information-div {
            font-size: small;
            font-family: var(--secondary-font);
            line-height: 1.2em;
            position: relative;
            bottom: 1em;

        }


    </style>
</head>
<body>

<c:choose>
    <c:when test="${loggedUser == null}">
        <jsp:include page="/cross-page-view/visitor-bar.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="/cross-page-view/user-bar.jsp"/>
    </c:otherwise>
</c:choose>


<div class="head-container">
    <div class="page-h1-container" id="search-title-container">
        <h1 id="search-title">
            We found <span class="keyword-span">${articles.size()}</span> articles matching your keyword <span
                class="keyword-span">${keyword}</span>.
        </h1>
        <a href='<c:url value="/index.jsp"/>'>
            <button class="link-button">
                Back to homepage &nbsp;&#8594;
            </button>
        </a>
    </div>
</div>
<div class="body-container">
    <div class="results-container">
        <c:forEach var="article" items="${articles}">
            <div class="result-article-container page-item-container" id="article-container-${article.articleID}">
                <script type="text/javascript">
                    loadCurrentArticle(${article.articleID}, `${article.articleTitle}`, `${article.articleContent}`,
                        ${article.author.userID}, `${article.author.username}`, `${article.author.avatarPath}`,
                        `${article.timeCreated}`, `${keyword}`);
                </script>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
