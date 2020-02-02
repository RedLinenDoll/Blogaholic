<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anran
  Date: 2/02/20
  Time: 11:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Result of ${keyword}</title>
    <jsp:include page="/cross-page-view/link-fonts.jsp"/>
    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'>
    <link rel="stylesheet" href='<c:url value="/assets/layout${loggedUser.layoutID}.css"/>'>

    <script src='<c:url value="/js/customized-styling.js"/>' type="text/javascript"></script>
    <script type="text/javascript" src='<c:url value="js/load-search-results.js"/>'></script>
    <script src='<c:url value="/js/like-dislike.js"/>' type="text/javascript"></script>

    <script type="text/javascript">
        window.addEventListener("load", async function () {
            applyThemeColor(`${loggedUser.themeColor}`);
            applyLayoutSpecificStyling(`${loggedUser.layoutID}`, `${loggedUser.themeColor}`);
            await loadSearchResults(${articles}, ${keyword});
            listenForLikeDislike();
        });

    </script>
</head>
<body>

<c:choose>
    <c:when test="${loggedUser == null}">
        <jsp:include page="cross-page-view/visitor-bar.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="cross-page-view/user-bar.jsp"/>
    </c:otherwise>
</c:choose>


<%-- // TODO find a way to mark the keyword part A different color.
        // TODO: could be string.split, and after applying style, putting the html content back again?
  --%>
<%--TODO populate this page with search results--%>

<div class="head-container">
    <div class="page-h1-container" id="search-title-container">
        <h1 id="search-title">
            We found ${articles.size()} articles with keyword <span class="keyword-span">${keyword}</span> for you.
        </h1>
        <a href='<c:url value="/index.jsp"/>'>
            <button class="link-button">
                Back to homepage &nbsp;&#8594;
            </button>
        </a>
    </div>
</div>
<div class="body-container">
    <c:forEach var="article" items="${articles}">

        <p>
                ${article.articleContent}
                ${article.author.username}
        </p>

    </c:forEach>
</div>
</body>
</html>
